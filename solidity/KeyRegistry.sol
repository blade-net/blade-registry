// SPDX-License-Identifier: GPL-3.0
pragma solidity ^0.8.4;

/*
 * Public Key Registry Contract
 * @author Sebastian Göndör
 * @version 0.1.1
 * @date 28.05.2021
 */

/**
 * This public key registry allows users to publicly register their public key. Others can then
 * retrieve the public key for any registered address.
 */
contract KeyRegistry
{
    mapping (address => bytes32) private keys;
    
    /**
     * Retrieve the key for an address
     */
    function getKey(address a) public view returns (bytes32)
    {
        if(keys[a] == 0x0)
            revert("Error: Unknown address");
            
        return keys[a];
    }
    
    /**
     * Register a string-formatted key. The address derived from the provided key must match the sender's address.
     */
    function setKey(string memory key) public returns (address)
    {
        bytes32 byteKey;
        
        if(bytes(key).length == 0)
            revert("Error: Key cant be empty");
        
        assembly
        {
            byteKey := mload(add(key, 32))
        }
        
        return setKey(byteKey);
    }
    
    /**
     * Register a key. The address derived from the provided key must match the sender's address.
     */
    function setKey(bytes32 key) public returns (address)
    {
        if(key.length == 0)
            revert("Error: Key cant be empty");
        
        address a = address(uint160(bytes20(key)));
        
        if(a != msg.sender)
            revert("Error: Key does not match sender's address. Only the owner of a key can register this key");
        
        if(keys[a] != 0x0)
            revert("Error: Key is already registered");
        
        keys[a] = key;
        
        return a;
    }
    
    /**
     * Checks if a key for a specified address is registered.
     */
    function isRegistered(address a) public view returns (bool)
    {
        if(keys[a] == 0x0)
            return false;
        return true;
    }
}
