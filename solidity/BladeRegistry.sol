// SPDX-License-Identifier: GPL-3.0
pragma solidity ^0.8.11;

/*
 * Blade Registry Contract
 * @author Sebastian Göndör
 * @version 0.2.1a
 * @date 20.01.2022
 */

// 0.2.1a: event fix for app registrations, fixed functions registerAppName() and createApp()
// 0.2.1: ropsten 0xe6059fB31A5Ff5b9a507daD4878a3912b327dF22
// 0.2.0: ropsten 0x8708975b585762a09aa568736a5298d6845772b7

contract BladeRegistry
{
    ////////////////////////////////////////////////////////////////
    // IDENTITIES
    ////////////////////////////////////////////////////////////////
    
    mapping (address => address)    private idIdentities; // (owner => identity)
    mapping (bytes32 => address)    private idNames; // (hash(name) => id)
    mapping (address => string)     private idNameMapping; // (id => name) // remove?
    
    mapping (address => string)     private idURLs; // (id => url)
    
    mapping (address => address)    private idDelegates; // (id => delegate)
    mapping (address => address)    private idDelegatesRevoked; //(revoked => id)
    
    event IdentityCreatedEvent(address indexed id);
    event IdNameRegisteredEvent(address indexed id, string name);
    event IdDelegateRegisteredEvent(address indexed id, address delegate);
    event IdDelegateRevokedEvent(address indexed id, address delegate);
    event IdURLSetEvent(address indexed id, string url);
    
    /**
     * Creates a new identity by registering a unique name and setting a delegate and url. Reverts if an identity for the sender's address
     * already exists or if the name is aready registered.
     *
     * While names can never be changed, delegates and URLs can be changed. Delegates can change an identity's URL, but only the identity
     * itself can change a delegate.
     */
    function createIdentity(string calldata name, string calldata url, address idDelegate) public returns (bool)
    {
        if(idIdentities[msg.sender] != address(0))
            revert("Identity already registered");
        
        if(idNames[keccak256(abi.encodePacked(name))] != address(0))
            revert("Name already registered");
        
        idIdentities[msg.sender] = msg.sender;
        idNames[keccak256(abi.encodePacked(name))] = msg.sender;
        idNameMapping[msg.sender] = name;
        
        emit IdentityCreatedEvent(msg.sender);
        emit IdNameRegisteredEvent(msg.sender, name);
        
        if(msg.sender == idDelegate)
            revert("idDelegate must not be same as id");
        idDelegates[msg.sender] = idDelegate;
        
        emit IdDelegateRegisteredEvent(msg.sender, idDelegate);
        
        idURLs[msg.sender] = url;
        
        emit IdURLSetEvent(msg.sender, url);
        
        return true;
    }
    
    /**
     * checks if an address is a registered identity
     */
    function isIdentity(address id) internal view returns (bool)
    {
        if(idIdentities[id] != address(0))
            return true;
        else
            return false;
    }
    
    /**
     * checks if a name is registered
     */
    function isNameRegistered(string calldata name) public view returns (bool)
    {
        bytes32 n = keccak256(abi.encodePacked(name));
        
        if(idNames[n] == address(0))
        {
            if(orgNames[n] == address(0))
                return false;
        }
        
        return true;
    }
    
    /**
     * returns the registered URL for a given identity
     */
    function getIdentityURL(address id) public view returns (string memory)
    {
        // TODO handle nonexistent ids / unset urls
        return idURLs[id];
    }
    
    /**
     * sets the url for an identity
     */
    function setURL(address identity, string calldata url) public returns (bool)
    {
        if(identity != msg.sender && msg.sender != idDelegates[identity])
            revert("Only the identity owner or his delgate can update the URL");
        
        emit IdURLSetEvent(identity, url);
        
        idURLs[identity] = url;
        
        return true;
    }
    
    /**
     * returns the registered name for a given identity
     */
    function getIdentityName(address id) public view returns (string memory)
    {
        // TODO handle nonexistent ids
        return idNameMapping[id];
    }
    
    /**
     * returns the identity for a registered name
     */
    function getIdentity(string calldata name) public view returns (address)
    {
        if(idNames[keccak256(abi.encodePacked(name))] == address(0))
            revert("Name is not registered");
        
        return idNames[keccak256(abi.encodePacked(name))];
    }
    
    /**
     * sets a new delegate for an id and revokes the old one
     */
    function setDelegate(address idDelegate) public returns(bool)
    {
        if(isIdentity(msg.sender) == false)
            revert("Sender is not registered as identity");
        
        address oldDelegate = idDelegates[msg.sender];
        
        idDelegatesRevoked[oldDelegate] = msg.sender;
        
        idDelegates[msg.sender] = idDelegate;
        
        emit IdDelegateRevokedEvent(msg.sender, idDelegate);
        emit IdDelegateRegisteredEvent(msg.sender, idDelegate);
        
        return true;
    }
    
    /**
     * returns the currently active idDelegate for an identity
     */
    function getDelegate(address id) public view returns (address)
    {
        // TODO handle nonexistent ids
        return idDelegates[id];
    }
    
    /**
     * checks if an address is a revoked delegate for a given identity
     */
    function isRevokedDelegateForID(address id, address delegate) public view returns (bool)
    {
        // check if this delegate is registered as revoked
        if(idDelegatesRevoked[delegate] == address(0))
            return false;
            
        if(idDelegatesRevoked[delegate] == id)
            return true;
        else
            return false;
    }
    
    /**
     * checks if an address is a revoked delegate
     */
    function isRevokedDelegate(address delegate) internal view returns (bool)
    {
        if(idDelegatesRevoked[delegate] == address(0))
            return false;
        else
            return true;
    }
    
    ////////////////////////////////////////////////////////////////
    // ORGANIZATIONS
    ////////////////////////////////////////////////////////////////
    
    mapping (address => address) private orgOwners; // (orgID => ownerID)
    // mapping (address => address) private orgMembers; // (memberID => orgID)
    
    mapping (address => mapping (address => address)) private orgMembers; // (orgID => (identity => identity))
    
    mapping (address => string) private orgURLs; // (orgID => URL)
    mapping (bytes32 => address) private orgNames; // (hash(name) => orgID)
    mapping (address => string) private orgNameMapping; // (orgID => name)
    
    event OrgCreatedEvent(address indexed creator, address orgID);
    event OrgNameRegisteredEvent(address indexed orgID, string name);
    event OrgURLSetEvent(address indexed orgID, string url);
    event OrgMemberRemovedEvent(address indexed orgID, address memberID);
    event OrgMemberAddedEvent(address indexed orgID, address memberID);
    event OrgOwnerChangedEvent(address indexed orgID, address oldOwner, address newOwner);
    
    function createOrganization(address orgID, string calldata name, string calldata url) public returns (bool)
    {
        if(orgOwners[orgID] != address(0))
            revert("An organization for this address is already registered");
        
        if(orgNames[keccak256(abi.encodePacked(name))] != address(0))
            revert("An organization with this name is already registered");
        
        orgOwners[orgID] = msg.sender;
        orgNames[keccak256(abi.encodePacked(name))] = orgID;
        orgNameMapping[orgID] = name;
        
        emit OrgCreatedEvent(msg.sender, orgID);
        emit OrgNameRegisteredEvent(orgID, name);
        
        orgURLs[orgID] = url;
        
        emit OrgURLSetEvent(orgID, url);
        
        return true;
    }
    
    /**
     * changes the owner of an organization. only the current owner can call this.
     */
    function changeOrgOwner(address orgID, address newOwner) public returns (bool)
    {
        if(orgOwners[orgID] != msg.sender)
            revert("Only owners can change an organization's owner");
        
        orgOwners[orgID] = newOwner;
        
        emit OrgOwnerChangedEvent(orgID, msg.sender, newOwner);
        
        return true;
    }
    
    /**
     * returns the address of the owner of an organization
     */
    function getOrgOwner(address orgID) public view returns (address)
    {
        if(orgOwners[orgID] == address(0))
            revert("An organization for this address is not registered");
        
        return orgOwners[orgID];
    }
    
    function addOrgMember(address orgID, address memberID) public
    {
        if(orgOwners[orgID] != msg.sender)
            revert("Only owners can add members to an organization");
        
        orgMembers[orgID][memberID] = memberID;
        
        emit OrgMemberAddedEvent(orgID, memberID);
    }
    
    function removeOrgMember(address orgID, address memberID) public
    {
        if(orgOwners[orgID] != msg.sender)
            revert("Only owners can remove members from an organization");
        
        orgMembers[orgID][memberID] = address(0);
        
        emit OrgMemberRemovedEvent(orgID, memberID);
    }
    
    function isOrgMember(address orgID, address memberID) public view returns (bool)
    {
        if(!isOrg(orgID))
            revert("An organization with this name is not registered");
        
        if(orgMembers[orgID][memberID] == memberID)
            return true;
        
        return false;
    }
    
    /**
     * checks if an address is a registered organization
     */
    function isOrg(address id) internal view returns (bool)
    {
        if(orgOwners[id] != address(0))
            return true;
        else
            return false;
    }
    
    /**
     * returns the registered URL for a given organization
     */
    function getOrgURL(address id) public view returns (string memory)
    {
        return orgURLs[id];
    }
    
    /**
     * returns the registered name for a given organization
     */
    function getOrgName(address id) public view returns (string memory)
    {
        return orgNameMapping[id];
    }
    
    /**
     * returns the organization for a registered name
     */
    function getOrgID(string calldata name) public view returns (address)
    {
        if(orgNames[keccak256(abi.encodePacked(name))] == address(0))
            revert("An organization with this name is not registered");
        
        return idNames[keccak256(abi.encodePacked(name))];
    }
    
    ////////////////////////////////////////////////////////////////
    // APPS / APIS
    ////////////////////////////////////////////////////////////////
    
    // appOwners stores the respective app owner's addresses
    mapping (address => address) private appOwners; // ownerID => appID
    
    //mapping (address => address[]) private appOwnersDeprecated; // past app owner addresses, needed for verification of old signatures!?
    
    // appVersionMapping stores a mapping from a appVersionID address to the main appID address
    mapping (address => address) private appVersionMapping; // appVersionID => appID
     
    // appVersions store a list of versions that exist for an app
    mapping (address => address[]) private appVersions; // appID => appVersionID[]
    
    // appNames store the registered name for an app
    mapping (string => address) private appNames; // name => appID
    
    // appTypes stores the type of an app
    mapping (address => AppType) private appTypes; // appID => APP/API
    
    // appDependencies store a list of dependencies for an appVersion
    mapping (address => address[]) private appDependencies; // appVersionID => appVersionID[]
    
    // versionURL stores version URL for an app
    mapping (address => string) private appVersionURL;
    
    enum AppType {APP, API}
    
    event AppRegisteredEvent(address indexed appID);
    event AppNameRegisteredEvent(address indexed appID, string appName);
    event AppVersionURLSetEvent(address indexed appID, string appURL);
    event AppSetOwnerEvent(address indexed appID, address appOwnerID);
    event AppVersionRegisteredEvent(address indexed appID, address appVersionID);
    
    /**
     * Creates a new app with a given appID and name and url
     *
     * returns true if successful
     */
    function createApp(address appID, string calldata name, string calldata url, AppType appType, address[] calldata dependencies) public returns (bool)
    {
        if(appOwners[appID] != address(0))
            revert("App address already registered");
            
        if(appNames[name] != address(0))
            revert("App name already registered");
        
        appOwners[appID] = msg.sender;

        appTypes[appID] = appType;
        appVersions[appID].push(appID); // first AppVersionID equals AppID
        appVersionMapping[appID] = appID;
        appNames[name] = appID;
        appVersionURL[appID] = url;
        
        emit AppRegisteredEvent(appID);
        emit AppVersionRegisteredEvent(appID, appID);
        emit AppSetOwnerEvent(appID, msg.sender);
        emit AppNameRegisteredEvent(appID, name);
        emit AppVersionURLSetEvent(appID, url);
        
        appDependencies[appID] = dependencies;
        
        return true;
    }
    
    /**
     * add a new app version for an existing app
     */
    function createAppVersion(address appID, address appVersionID, string calldata url) public returns (bool)
    {
        if(appOwners[appID] != msg.sender)
            revert("Only the app owner is allowed to register new app versions");
        
        if(appVersionMapping[appVersionID] != address(0))
            revert("An app version with the given app version ID already exists");
        
        appVersions[appID].push(appVersionID);
        appVersionMapping[appVersionID] = appID;
        appVersionURL[appID] = url;
        
        emit AppSetOwnerEvent(appID, msg.sender);
        emit AppVersionRegisteredEvent(appID, appVersionID);
        emit AppVersionURLSetEvent(appVersionID, url);

        return true;
    }
    
    /**
     * sets the version URL for an app
     */
    function setVersionURL(address appVersionID, string calldata url) public returns (bool)
    {
        if(appVersionMapping[appVersionID] == address(0))
            revert("An app version with the given app version ID does not exist");
        
        address appID = appVersionMapping[appVersionID];
        
        if(appOwners[appID] != msg.sender)
            revert("Only the app owner is allowed to change version URLs");
        
        appVersionURL[appVersionID] = url;

        emit AppVersionURLSetEvent(appVersionID, url);
        
        return true;
    }
    
    /**
     * register a name if possible and link it to the address sending the transaction
     */
    function registerName(address appID, string calldata name) public returns (bool)
    {
        // add name => identity/address to mapping
        if(appNames[name] != address(0)) // if the name is already registered
        {
            // name is already registerd -> cannot change
            return false;
        }
        
        emit AppNameRegisteredEvent(appID, name);

        // name not yet registered -> register name and return true
        appNames[name] = appID;
        return true;
    }
    
    /**
     * returns the app for a given version ID
     */
    function getAppForVersion(address appVersionID) public view returns (address)
    {
        return appVersionMapping[appVersionID];
    }
    
    /**
     * returns address of app by given name. returns 0 if name is not found
     */
    function getAppID(string calldata name) public view returns (address)
    {
        return appNames[name];
    }
    
    /**
     * sets or changes the owner of an app
     */
    function setAppOwner(address appID, address newOwner) public returns (bool)
    {
        if(appOwners[appID] == msg.sender)// || appOwners[appID] == address(0))
        {
            appOwners[appID] = newOwner;
            return true;
        }

        emit AppSetOwnerEvent(appID, newOwner);
        
        // name not yet registered or owned by other identity -> return false
        return false;
    }
    
    /**
     * returns address of app owner by app address. returns 0x0 if app not found
     */
    function getAppOwner(address appID) public view returns (address)
    {
        if(appOwners[appID] != address(0))
        {
            return appOwners[appID];
        }
        
        return address(0);
    }
    
    /**
     * returns address of app owner by app name. returns 0x0 if app not found
     */
    function getAppOwnerByName(string calldata name) public view returns (address)
    {
        address app = getAppID(name);
        
        if(app != address(0))
        {
            return appOwners[app];
        }
        
        return address(0);
    }
}