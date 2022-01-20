# Blade Registry

The Blade Registry is a directory service implemented as a smart contract in Ethereum/Solidity. The smart contract is (loosely) based on [EiP 1056](https://eips.ethereum.org/EIPS/eip-1056), allowing any identity on Ethereum to register an identity for the use in the [Blade ecosystem](https://github.com/blade-net).

## Usage

## Deployment

List of deployed versions

### 0.2.1

* Ropsten: 0xe6059fB31A5Ff5b9a507daD4878a3912b327dF22

### 0.2.0

* Ropsten: 0x8708975b585762a09aa568736a5298d6845772b7

## Compilation

* Compile

```solc ./solidity/BladeRegistry.sol --bin --abi --optimize -o ./bin```

* Create Java classes with/for web3j

```web3j solidity -b ./bin/BladeRegistry.bin -a ./bin/BladeRegistry.abi -o ./java --package web3j```

## Changelog

### 0.2.x
* Added missing events for App registrations
* Fixed function createApp()
* Fixed function appSetOwner()
### 0.2.1
* Reworked events
* Refactored getAppOwner()
* Changed Solidity version requirement to ^0.8.11
### 0.2.0
* Initial release
