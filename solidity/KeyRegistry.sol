// SPDX-License-Identifier: GPL-3.0
pragma solidity ^0.8.4;

/*
 * Public Key Registry Contract
 * @author Sebastian Göndör
 * @version 0.1.0
 * @date 27.05.2021
 */

/**
 * This public key registry allows users to publicly register their public key. Others can then
 * retrieve the public key for any registered address.
 */
contract KeyRegistry
{
    mapping (address => bytes32) private keys;
    
    function getKey(address a) public view returns (bytes32)
    {
        if(keys[a] == 0x0)
            revert("Error: Unknown address");
            
        return keys[a];
    }
    
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
}
