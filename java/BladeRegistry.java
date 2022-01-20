package web3j;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.5.16.
 */
@SuppressWarnings("rawtypes")
public class BladeRegistry extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b50612341806100206000396000f3fe608060405234801561001057600080fd5b50600436106101a95760003560e01c8063716890a5116100f9578063d6f3663b11610097578063f10af03911610071578063f10af039146103d1578063f6375eec146103e4578063f9e41d56146103f7578063ffb42b511461040a57600080fd5b8063d6f3663b14610398578063d7352ed7146103ab578063e6843814146103be57600080fd5b806394ae93b8116100d357806394ae93b81461034c578063a3135b0b1461035f578063c89782a214610372578063ca5eb5e11461038557600080fd5b8063716890a5146103135780638ae72a43146103265780638f1f028d1461033957600080fd5b8063494af7dd11610166578063544d856411610140578063544d8564146102ae5780635f23312e146102da5780636900f1b3146102ed5780636b4b3ab51461030057600080fd5b8063494af7dd146102735780634b930503146102885780634fb06e321461029b57600080fd5b80630a29ae6f146101ae578063161dc5bf146101de578063165e8f5314610201578063270b0c2d1461021457806335aac132146102345780634066db6914610247575b600080fd5b6101c16101bc366004611e86565b61041d565b6040516001600160a01b0390911681526020015b60405180910390f35b6101f16101ec366004611edf565b61050a565b60405190151581526020016101d5565b6101f161020f366004611f32565b6106a6565b610227610222366004611f93565b6108fa565b6040516101d59190611fae565b6101f1610242366004612003565b6109a6565b6101c1610255366004611f93565b6001600160a01b039081166000908152600c60205260409020541690565b610286610281366004612003565b610a49565b005b6101f1610296366004611edf565b610b3c565b6101f16102a9366004612003565b610c0f565b6101c16102bc366004611f93565b6001600160a01b039081166000908152600460205260409020541690565b6101f16102e8366004612036565b610c78565b6101c16102fb366004611e86565b610fa6565b6101f161030e366004612003565b610fdb565b610227610321366004611f93565b611039565b6101f1610334366004611edf565b611060565b610286610347366004612003565b611179565b6101f161035a366004611e86565b611261565b6101c161036d366004611e86565b6112dc565b610227610380366004611f93565b61134c565b6101f1610393366004611f93565b611373565b6102276103a6366004611f93565b61149e565b6101f16103b93660046120b7565b6114c5565b6101c16103cc366004611f93565b611784565b6101c16103df366004611e86565b611827565b6101f16103f2366004612138565b611871565b6101f1610405366004612003565b611bc6565b6101c1610418366004611f93565b611caf565b6000806001600160a01b031660016000858560405160200161044092919061222b565b60408051601f19818403018152918152815160209283012083529082019290925201600020546001600160a01b031614156104bb5760405162461bcd60e51b815260206004820152601660248201527513985b59481a5cc81b9bdd081c9959da5cdd195c995960521b60448201526064015b60405180910390fd5b6001600084846040516020016104d292919061222b565b60408051601f19818403018152918152815160209283012083529082019290925201600020546001600160a01b031690505b92915050565b6001600160a01b038381166000908152600c60205260408120549091166105995760405162461bcd60e51b815260206004820152603b60248201527f416e206170702076657273696f6e20776974682074686520676976656e20617060448201527f702076657273696f6e20494420646f6573206e6f74206578697374000000000060648201526084016104b2565b6001600160a01b038085166000908152600c60209081526040808320548416808452600b9092529091205490911633146106325760405162461bcd60e51b815260206004820152603460248201527f4f6e6c792074686520617070206f776e657220697320616c6c6f77656420746f604482015273206368616e67652076657273696f6e2055524c7360601b60648201526084016104b2565b6001600160a01b0385166000908152601160205260409020610655908585611d51565b50846001600160a01b03167ff9bf3908e526e2c123ac9a7e2bb025c5c185935412cc8e5c9bd0a5e38c2d333e858560405161069192919061223b565b60405180910390a260019150505b9392505050565b6001600160a01b038481166000908152600b602052604081205490911633146107375760405162461bcd60e51b815260206004820152603a60248201527f4f6e6c792074686520617070206f776e657220697320616c6c6f77656420746f60448201527f207265676973746572206e6577206170702076657273696f6e7300000000000060648201526084016104b2565b6001600160a01b038481166000908152600c602052604090205416156107c55760405162461bcd60e51b815260206004820152603b60248201527f416e206170702076657273696f6e20776974682074686520676976656e20617060448201527f702076657273696f6e20494420616c726561647920657869737473000000000060648201526084016104b2565b6001600160a01b038086166000818152600d602090815260408083208054600181018255908452828420018054958a166001600160a01b031996871681179091558352600c82528083208054909516841790945591815260119091522061082d908484611d51565b506040513381526001600160a01b038616907ffabd535e30cf9371d139d6aae222fd86952684a30cdd7e76ad8426ecc2eb9d329060200160405180910390a26040516001600160a01b0385811682528616907feec3ac48dfa6a040551b516f8e10b81603821c585edd494829e3a49dfaf1481d9060200160405180910390a2836001600160a01b03167ff9bf3908e526e2c123ac9a7e2bb025c5c185935412cc8e5c9bd0a5e38c2d333e84846040516108e792919061223b565b60405180910390a2506001949350505050565b6001600160a01b03811660009081526008602052604090208054606091906109219061226a565b80601f016020809104026020016040519081016040528092919081815260200182805461094d9061226a565b801561099a5780601f1061096f5761010080835404028352916020019161099a565b820191906000526020600020905b81548152906001019060200180831161097d57829003601f168201915b50505050509050919050565b6001600160a01b038281166000908152600b6020526040812054909116331415610a0057506001600160a01b038281166000908152600b6020526040902080546001600160a01b0319169183169190911790556001610504565b6040516001600160a01b0383811682528416907ffabd535e30cf9371d139d6aae222fd86952684a30cdd7e76ad8426ecc2eb9d329060200160405180910390a250600092915050565b6001600160a01b03828116600090815260066020526040902054163314610ace5760405162461bcd60e51b815260206004820152603360248201527f4f6e6c79206f776e6572732063616e2072656d6f7665206d656d6265727320666044820152723937b69030b71037b933b0b734bd30ba34b7b760691b60648201526084016104b2565b6001600160a01b0382811660008181526007602090815260408083209486168084529482529182902080546001600160a01b0319169055905192835290917fd6882887428041ff22c6c283385d277566d1c8f80a6ea358bc005f02f2b2e99e91015b60405180910390a25050565b6000806001600160a01b0316600e8484604051610b5a92919061222b565b908152604051908190036020019020546001600160a01b031614610b805750600061069f565b836001600160a01b03167f7a24870c3335258696dbe9b92afa45c597fbcfed9dd9e1bd76fe49e2d03bf6ed8484604051610bbb92919061223b565b60405180910390a283600e8484604051610bd692919061222b565b90815260405190819003602001902080546001600160a01b03929092166001600160a01b03199092169190911790555060019392505050565b6000610c1a83611cf8565b610c365760405162461bcd60e51b81526004016104b2906122a5565b6001600160a01b0383811660009081526007602090815260408083208487168085529252909120549091161415610c6f57506001610504565b50600092915050565b336000908152602081905260408120546001600160a01b031615610cde5760405162461bcd60e51b815260206004820152601b60248201527f4964656e7469747920616c72656164792072656769737465726564000000000060448201526064016104b2565b60006001600160a01b0316600160008888604051602001610d0092919061222b565b60408051601f19818403018152918152815160209283012083529082019290925201600020546001600160a01b031614610d7c5760405162461bcd60e51b815260206004820152601760248201527f4e616d6520616c7265616479207265676973746572656400000000000000000060448201526064016104b2565b3360008181526020818152604080832080546001600160a01b031916851790555160019291610daf918b918b910161222b565b60408051808303601f190181529181528151602092830120835282820193909352908201600090812080546001600160a01b0319166001600160a01b039590951694909417909355338352600290529020610e0b908787611d51565b5060405133907ff5acefdfaba7bcdd5f52eb944424dd9ca7f40b49bceb486aa8d21149d0e0a23b90600090a2336001600160a01b03167fd8b02f5509ad5b2b908c8fcb97d8f2180a100a92cb76acecd64287ac99d822528787604051610e7292919061223b565b60405180910390a2336001600160a01b0383161415610edd5760405162461bcd60e51b815260206004820152602160248201527f696444656c6567617465206d757374206e6f742062652073616d6520617320696044820152601960fa1b60648201526084016104b2565b3360008181526004602090815260409182902080546001600160a01b0319166001600160a01b03871690811790915591519182527fe28e068d26f2d0dd527a4f6e697749fd7c44a2a2035fe442377a04ffa4903e55910160405180910390a2336000908152600360205260409020610f56908585611d51565b50336001600160a01b03167fd0fbcea0427001752d6fa09fa8a7adcb05314f305d79caa5ef3815951970e1b58585604051610f9292919061223b565b60405180910390a250600195945050505050565b6000600e8383604051610fba92919061222b565b908152604051908190036020019020546001600160a01b0316905092915050565b6001600160a01b0381811660009081526005602052604081205490911661100457506000610504565b6001600160a01b038281166000908152600560205260409020548116908416141561103157506001610504565b506000610504565b6001600160a01b03811660009081526002602052604090208054606091906109219061226a565b60006001600160a01b038416331480159061109557506001600160a01b03848116600090815260046020526040902054163314155b156111085760405162461bcd60e51b815260206004820152603960248201527f4f6e6c7920746865206964656e74697479206f776e6572206f7220686973206460448201527f656c676174652063616e20757064617465207468652055524c0000000000000060648201526084016104b2565b836001600160a01b03167fd0fbcea0427001752d6fa09fa8a7adcb05314f305d79caa5ef3815951970e1b5848460405161114392919061223b565b60405180910390a26001600160a01b038416600090815260036020526040902061116e908484611d51565b506001949350505050565b6001600160a01b038281166000908152600660205260409020541633146111f95760405162461bcd60e51b815260206004820152602e60248201527f4f6e6c79206f776e6572732063616e20616464206d656d6265727320746f206160448201526d371037b933b0b734bd30ba34b7b760911b60648201526084016104b2565b6001600160a01b0382811660008181526007602090815260408083209486168084529482529182902080546001600160a01b03191685179055905192835290917fe96293aade87be7aef1c4bfb2a9f310cd13be4a519939310f9b6ef2f8e9db5609101610b30565b600080838360405160200161127792919061222b565b60408051601f198184030181529181528151602092830120600081815260019093529120549091506001600160a01b03166112d2576000818152600960205260409020546001600160a01b03166112d2576000915050610504565b5060019392505050565b6000806001600160a01b03166009600085856040516020016112ff92919061222b565b60408051601f19818403018152918152815160209283012083529082019290925201600020546001600160a01b031614156104bb5760405162461bcd60e51b81526004016104b2906122a5565b6001600160a01b0381166000908152600a602052604090208054606091906109219061226a565b600061137e33611d27565b6113d65760405162461bcd60e51b8152602060048201526024808201527f53656e646572206973206e6f742072656769737465726564206173206964656e6044820152637469747960e01b60648201526084016104b2565b33600081815260046020818152604080842080546001600160a01b039081168087526005855283872080546001600160a01b03199081168a17909155968890529484528154908916951685179055519283529092917fbdce5f229c4323d8318f6ef9dea6239484e0c721721a57b6bc913795736c9932910160405180910390a26040516001600160a01b038416815233907fe28e068d26f2d0dd527a4f6e697749fd7c44a2a2035fe442377a04ffa4903e55906020015b60405180910390a250600192915050565b6001600160a01b03811660009081526003602052604090208054606091906109219061226a565b6001600160a01b038581166000908152600660205260408120549091161561154e5760405162461bcd60e51b815260206004820152603660248201527f416e206f7267616e697a6174696f6e20666f7220746869732061646472657373604482015275081a5cc8185b1c9958591e481c9959da5cdd195c995960521b60648201526084016104b2565b60006001600160a01b031660096000878760405160200161157092919061222b565b60408051601f19818403018152918152815160209283012083529082019290925201600020546001600160a01b0316146116095760405162461bcd60e51b815260206004820152603460248201527f416e206f7267616e697a6174696f6e20776974682074686973206e616d6520696044820152731cc8185b1c9958591e481c9959da5cdd195c995960621b60648201526084016104b2565b6001600160a01b038616600090815260066020908152604080832080546001600160a01b03191633179055518892600992909161164a918a918a910161222b565b60408051808303601f190181529181528151602092830120835282820193909352908201600090812080546001600160a01b0319166001600160a01b039586161790559289168352600a905290206116a3908686611d51565b506040516001600160a01b038716815233907f55ba28cf8c1f7e69edc8abc763f7d458012c9939763a2366115a655598b670609060200160405180910390a2856001600160a01b03167f83e1e3877ad7bca56f1db475bcfc4ea2884b20feca8852f44bd3517eb63ecb17868660405161171d92919061223b565b60405180910390a26001600160a01b0386166000908152600860205260409020611748908484611d51565b50856001600160a01b03167f156cbc4e7b0956859c3c6a52b4cdab2505efd27a102317118946fea5d1ed5ec28484604051610f9292919061223b565b6001600160a01b038181166000908152600660205260408120549091166118085760405162461bcd60e51b815260206004820152603260248201527f416e206f7267616e697a6174696f6e20666f7220746869732061646472657373604482015271081a5cc81b9bdd081c9959da5cdd195c995960721b60648201526084016104b2565b506001600160a01b039081166000908152600660205260409020541690565b6000806118348484610fa6565b90506001600160a01b03811615611867576001600160a01b039081166000908152600b6020526040902054169050610504565b5060009392505050565b6001600160a01b038881166000908152600b6020526040812054909116156118db5760405162461bcd60e51b815260206004820152601e60248201527f417070206164647265737320616c72656164792072656769737465726564000060448201526064016104b2565b60006001600160a01b0316600e89896040516118f892919061222b565b908152604051908190036020019020546001600160a01b03161461195e5760405162461bcd60e51b815260206004820152601b60248201527f417070206e616d6520616c72656164792072656769737465726564000000000060448201526064016104b2565b6001600160a01b0389166000908152600b6020908152604080832080546001600160a01b03191633179055600f9091529020805485919060ff1916600183818111156119ac576119ac6122f5565b02179055506001600160a01b0389166000818152600d6020908152604080832080546001810182559084528284200180546001600160a01b03199081168617909155848452600c909252918290208054909116909217909155518990600e90611a18908b908b9061222b565b908152604080516020928190038301902080546001600160a01b0319166001600160a01b03948516179055918b166000908152601190915220611a5c908787611d51565b506040516001600160a01b038a16907f0d9f5e64abef512788d219501629f4137b845634f5790d7b874a99e8cac81ed890600090a26040516001600160a01b038a16808252907feec3ac48dfa6a040551b516f8e10b81603821c585edd494829e3a49dfaf1481d9060200160405180910390a26040513381526001600160a01b038a16907ffabd535e30cf9371d139d6aae222fd86952684a30cdd7e76ad8426ecc2eb9d329060200160405180910390a2886001600160a01b03167f7a24870c3335258696dbe9b92afa45c597fbcfed9dd9e1bd76fe49e2d03bf6ed8989604051611b4892919061223b565b60405180910390a2886001600160a01b03167ff9bf3908e526e2c123ac9a7e2bb025c5c185935412cc8e5c9bd0a5e38c2d333e8787604051611b8b92919061223b565b60405180910390a26001600160a01b0389166000908152601060205260409020611bb6908484611dd5565b5060019998505050505050505050565b6001600160a01b038281166000908152600660205260408120549091163314611c485760405162461bcd60e51b815260206004820152602e60248201527f4f6e6c79206f776e6572732063616e206368616e676520616e206f7267616e6960448201526d3d30ba34b7b713b99037bbb732b960911b60648201526084016104b2565b6001600160a01b0383811660008181526006602090815260409182902080546001600160a01b031916948716948517905581513381529081019390935290917f1c8822d3d1f26a0fa7726af16fcfaf7900796bc2d6659b447fc697c407b25e90910161148d565b6001600160a01b038181166000908152600b602052604081205490911615611cf057506001600160a01b039081166000908152600b60205260409020541690565b506000919050565b6001600160a01b0381811660009081526006602052604081205490911615611cf057506001919050565b919050565b6001600160a01b0381811660009081526020819052604081205490911615611cf057506001919050565b828054611d5d9061226a565b90600052602060002090601f016020900481019282611d7f5760008555611dc5565b82601f10611d985782800160ff19823516178555611dc5565b82800160010185558215611dc5579182015b82811115611dc5578235825591602001919060010190611daa565b50611dd1929150611e28565b5090565b828054828255906000526020600020908101928215611dc5579160200282015b82811115611dc55781546001600160a01b0319166001600160a01b03843516178255602090920191600190910190611df5565b5b80821115611dd15760008155600101611e29565b60008083601f840112611e4f57600080fd5b50813567ffffffffffffffff811115611e6757600080fd5b602083019150836020828501011115611e7f57600080fd5b9250929050565b60008060208385031215611e9957600080fd5b823567ffffffffffffffff811115611eb057600080fd5b611ebc85828601611e3d565b90969095509350505050565b80356001600160a01b0381168114611d2257600080fd5b600080600060408486031215611ef457600080fd5b611efd84611ec8565b9250602084013567ffffffffffffffff811115611f1957600080fd5b611f2586828701611e3d565b9497909650939450505050565b60008060008060608587031215611f4857600080fd5b611f5185611ec8565b9350611f5f60208601611ec8565b9250604085013567ffffffffffffffff811115611f7b57600080fd5b611f8787828801611e3d565b95989497509550505050565b600060208284031215611fa557600080fd5b61069f82611ec8565b600060208083528351808285015260005b81811015611fdb57858101830151858201604001528201611fbf565b81811115611fed576000604083870101525b50601f01601f1916929092016040019392505050565b6000806040838503121561201657600080fd5b61201f83611ec8565b915061202d60208401611ec8565b90509250929050565b60008060008060006060868803121561204e57600080fd5b853567ffffffffffffffff8082111561206657600080fd5b61207289838a01611e3d565b9097509550602088013591508082111561208b57600080fd5b5061209888828901611e3d565b90945092506120ab905060408701611ec8565b90509295509295909350565b6000806000806000606086880312156120cf57600080fd5b6120d886611ec8565b9450602086013567ffffffffffffffff808211156120f557600080fd5b61210189838a01611e3d565b9096509450604088013591508082111561211a57600080fd5b5061212788828901611e3d565b969995985093965092949392505050565b60008060008060008060008060a0898b03121561215457600080fd5b61215d89611ec8565b9750602089013567ffffffffffffffff8082111561217a57600080fd5b6121868c838d01611e3d565b909950975060408b013591508082111561219f57600080fd5b6121ab8c838d01611e3d565b909750955060608b01359150600282106121c457600080fd5b90935060808a013590808211156121da57600080fd5b818b0191508b601f8301126121ee57600080fd5b8135818111156121fd57600080fd5b8c60208260051b850101111561221257600080fd5b6020830194508093505050509295985092959890939650565b8183823760009101908152919050565b60208152816020820152818360408301376000818301604090810191909152601f909201601f19160101919050565b600181811c9082168061227e57607f821691505b6020821081141561229f57634e487b7160e01b600052602260045260246000fd5b50919050565b60208082526030908201527f416e206f7267616e697a6174696f6e20776974682074686973206e616d65206960408201526f1cc81b9bdd081c9959da5cdd195c995960821b606082015260800190565b634e487b7160e01b600052602160045260246000fdfea26469706673582212209e18a3115d0c115e40ca8542683c04966f0ed388c1b0f8dfbc5f9ccdcdc9a37e64736f6c634300080b0033";

    public static final String FUNC_ADDORGMEMBER = "addOrgMember";

    public static final String FUNC_CHANGEORGOWNER = "changeOrgOwner";

    public static final String FUNC_CREATEAPP = "createApp";

    public static final String FUNC_CREATEAPPVERSION = "createAppVersion";

    public static final String FUNC_CREATEIDENTITY = "createIdentity";

    public static final String FUNC_CREATEORGANIZATION = "createOrganization";

    public static final String FUNC_GETAPPFORVERSION = "getAppForVersion";

    public static final String FUNC_GETAPPID = "getAppID";

    public static final String FUNC_GETAPPOWNER = "getAppOwner";

    public static final String FUNC_GETAPPOWNERBYNAME = "getAppOwnerByName";

    public static final String FUNC_GETDELEGATE = "getDelegate";

    public static final String FUNC_GETIDENTITY = "getIdentity";

    public static final String FUNC_GETIDENTITYNAME = "getIdentityName";

    public static final String FUNC_GETIDENTITYURL = "getIdentityURL";

    public static final String FUNC_GETORGID = "getOrgID";

    public static final String FUNC_GETORGNAME = "getOrgName";

    public static final String FUNC_GETORGOWNER = "getOrgOwner";

    public static final String FUNC_GETORGURL = "getOrgURL";

    public static final String FUNC_ISNAMEREGISTERED = "isNameRegistered";

    public static final String FUNC_ISORGMEMBER = "isOrgMember";

    public static final String FUNC_ISREVOKEDDELEGATEFORID = "isRevokedDelegateForID";

    public static final String FUNC_REGISTERNAME = "registerName";

    public static final String FUNC_REMOVEORGMEMBER = "removeOrgMember";

    public static final String FUNC_SETAPPOWNER = "setAppOwner";

    public static final String FUNC_SETDELEGATE = "setDelegate";

    public static final String FUNC_SETURL = "setURL";

    public static final String FUNC_SETVERSIONURL = "setVersionURL";

    public static final Event APPNAMEREGISTEREDEVENT_EVENT = new Event("AppNameRegisteredEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Utf8String>() {}));
    ;

    public static final Event APPREGISTEREDEVENT_EVENT = new Event("AppRegisteredEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}));
    ;

    public static final Event APPSETOWNEREVENT_EVENT = new Event("AppSetOwnerEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>() {}));
    ;

    public static final Event APPVERSIONREGISTEREDEVENT_EVENT = new Event("AppVersionRegisteredEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>() {}));
    ;

    public static final Event APPVERSIONURLSETEVENT_EVENT = new Event("AppVersionURLSetEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Utf8String>() {}));
    ;

    public static final Event IDDELEGATEREGISTEREDEVENT_EVENT = new Event("IdDelegateRegisteredEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>() {}));
    ;

    public static final Event IDDELEGATEREVOKEDEVENT_EVENT = new Event("IdDelegateRevokedEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>() {}));
    ;

    public static final Event IDNAMEREGISTEREDEVENT_EVENT = new Event("IdNameRegisteredEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Utf8String>() {}));
    ;

    public static final Event IDURLSETEVENT_EVENT = new Event("IdURLSetEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Utf8String>() {}));
    ;

    public static final Event IDENTITYCREATEDEVENT_EVENT = new Event("IdentityCreatedEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}));
    ;

    public static final Event ORGCREATEDEVENT_EVENT = new Event("OrgCreatedEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>() {}));
    ;

    public static final Event ORGMEMBERADDEDEVENT_EVENT = new Event("OrgMemberAddedEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>() {}));
    ;

    public static final Event ORGMEMBERREMOVEDEVENT_EVENT = new Event("OrgMemberRemovedEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>() {}));
    ;

    public static final Event ORGNAMEREGISTEREDEVENT_EVENT = new Event("OrgNameRegisteredEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Utf8String>() {}));
    ;

    public static final Event ORGOWNERCHANGEDEVENT_EVENT = new Event("OrgOwnerChangedEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>() {}, new TypeReference<Address>() {}));
    ;

    public static final Event ORGURLSETEVENT_EVENT = new Event("OrgURLSetEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Utf8String>() {}));
    ;

    @Deprecated
    protected BladeRegistry(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected BladeRegistry(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected BladeRegistry(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected BladeRegistry(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public List<AppNameRegisteredEventEventResponse> getAppNameRegisteredEventEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(APPNAMEREGISTEREDEVENT_EVENT, transactionReceipt);
        ArrayList<AppNameRegisteredEventEventResponse> responses = new ArrayList<AppNameRegisteredEventEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            AppNameRegisteredEventEventResponse typedResponse = new AppNameRegisteredEventEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.appID = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.appName = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<AppNameRegisteredEventEventResponse> appNameRegisteredEventEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, AppNameRegisteredEventEventResponse>() {
            @Override
            public AppNameRegisteredEventEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(APPNAMEREGISTEREDEVENT_EVENT, log);
                AppNameRegisteredEventEventResponse typedResponse = new AppNameRegisteredEventEventResponse();
                typedResponse.log = log;
                typedResponse.appID = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.appName = (String) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<AppNameRegisteredEventEventResponse> appNameRegisteredEventEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(APPNAMEREGISTEREDEVENT_EVENT));
        return appNameRegisteredEventEventFlowable(filter);
    }

    public List<AppRegisteredEventEventResponse> getAppRegisteredEventEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(APPREGISTEREDEVENT_EVENT, transactionReceipt);
        ArrayList<AppRegisteredEventEventResponse> responses = new ArrayList<AppRegisteredEventEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            AppRegisteredEventEventResponse typedResponse = new AppRegisteredEventEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.appID = (String) eventValues.getIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<AppRegisteredEventEventResponse> appRegisteredEventEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, AppRegisteredEventEventResponse>() {
            @Override
            public AppRegisteredEventEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(APPREGISTEREDEVENT_EVENT, log);
                AppRegisteredEventEventResponse typedResponse = new AppRegisteredEventEventResponse();
                typedResponse.log = log;
                typedResponse.appID = (String) eventValues.getIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<AppRegisteredEventEventResponse> appRegisteredEventEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(APPREGISTEREDEVENT_EVENT));
        return appRegisteredEventEventFlowable(filter);
    }

    public List<AppSetOwnerEventEventResponse> getAppSetOwnerEventEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(APPSETOWNEREVENT_EVENT, transactionReceipt);
        ArrayList<AppSetOwnerEventEventResponse> responses = new ArrayList<AppSetOwnerEventEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            AppSetOwnerEventEventResponse typedResponse = new AppSetOwnerEventEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.appID = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.appOwnerID = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<AppSetOwnerEventEventResponse> appSetOwnerEventEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, AppSetOwnerEventEventResponse>() {
            @Override
            public AppSetOwnerEventEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(APPSETOWNEREVENT_EVENT, log);
                AppSetOwnerEventEventResponse typedResponse = new AppSetOwnerEventEventResponse();
                typedResponse.log = log;
                typedResponse.appID = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.appOwnerID = (String) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<AppSetOwnerEventEventResponse> appSetOwnerEventEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(APPSETOWNEREVENT_EVENT));
        return appSetOwnerEventEventFlowable(filter);
    }

    public List<AppVersionRegisteredEventEventResponse> getAppVersionRegisteredEventEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(APPVERSIONREGISTEREDEVENT_EVENT, transactionReceipt);
        ArrayList<AppVersionRegisteredEventEventResponse> responses = new ArrayList<AppVersionRegisteredEventEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            AppVersionRegisteredEventEventResponse typedResponse = new AppVersionRegisteredEventEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.appID = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.appVersionID = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<AppVersionRegisteredEventEventResponse> appVersionRegisteredEventEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, AppVersionRegisteredEventEventResponse>() {
            @Override
            public AppVersionRegisteredEventEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(APPVERSIONREGISTEREDEVENT_EVENT, log);
                AppVersionRegisteredEventEventResponse typedResponse = new AppVersionRegisteredEventEventResponse();
                typedResponse.log = log;
                typedResponse.appID = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.appVersionID = (String) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<AppVersionRegisteredEventEventResponse> appVersionRegisteredEventEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(APPVERSIONREGISTEREDEVENT_EVENT));
        return appVersionRegisteredEventEventFlowable(filter);
    }

    public List<AppVersionURLSetEventEventResponse> getAppVersionURLSetEventEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(APPVERSIONURLSETEVENT_EVENT, transactionReceipt);
        ArrayList<AppVersionURLSetEventEventResponse> responses = new ArrayList<AppVersionURLSetEventEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            AppVersionURLSetEventEventResponse typedResponse = new AppVersionURLSetEventEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.appID = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.appURL = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<AppVersionURLSetEventEventResponse> appVersionURLSetEventEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, AppVersionURLSetEventEventResponse>() {
            @Override
            public AppVersionURLSetEventEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(APPVERSIONURLSETEVENT_EVENT, log);
                AppVersionURLSetEventEventResponse typedResponse = new AppVersionURLSetEventEventResponse();
                typedResponse.log = log;
                typedResponse.appID = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.appURL = (String) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<AppVersionURLSetEventEventResponse> appVersionURLSetEventEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(APPVERSIONURLSETEVENT_EVENT));
        return appVersionURLSetEventEventFlowable(filter);
    }

    public List<IdDelegateRegisteredEventEventResponse> getIdDelegateRegisteredEventEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(IDDELEGATEREGISTEREDEVENT_EVENT, transactionReceipt);
        ArrayList<IdDelegateRegisteredEventEventResponse> responses = new ArrayList<IdDelegateRegisteredEventEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            IdDelegateRegisteredEventEventResponse typedResponse = new IdDelegateRegisteredEventEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.id = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.delegate = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<IdDelegateRegisteredEventEventResponse> idDelegateRegisteredEventEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, IdDelegateRegisteredEventEventResponse>() {
            @Override
            public IdDelegateRegisteredEventEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(IDDELEGATEREGISTEREDEVENT_EVENT, log);
                IdDelegateRegisteredEventEventResponse typedResponse = new IdDelegateRegisteredEventEventResponse();
                typedResponse.log = log;
                typedResponse.id = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.delegate = (String) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<IdDelegateRegisteredEventEventResponse> idDelegateRegisteredEventEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(IDDELEGATEREGISTEREDEVENT_EVENT));
        return idDelegateRegisteredEventEventFlowable(filter);
    }

    public List<IdDelegateRevokedEventEventResponse> getIdDelegateRevokedEventEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(IDDELEGATEREVOKEDEVENT_EVENT, transactionReceipt);
        ArrayList<IdDelegateRevokedEventEventResponse> responses = new ArrayList<IdDelegateRevokedEventEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            IdDelegateRevokedEventEventResponse typedResponse = new IdDelegateRevokedEventEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.id = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.delegate = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<IdDelegateRevokedEventEventResponse> idDelegateRevokedEventEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, IdDelegateRevokedEventEventResponse>() {
            @Override
            public IdDelegateRevokedEventEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(IDDELEGATEREVOKEDEVENT_EVENT, log);
                IdDelegateRevokedEventEventResponse typedResponse = new IdDelegateRevokedEventEventResponse();
                typedResponse.log = log;
                typedResponse.id = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.delegate = (String) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<IdDelegateRevokedEventEventResponse> idDelegateRevokedEventEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(IDDELEGATEREVOKEDEVENT_EVENT));
        return idDelegateRevokedEventEventFlowable(filter);
    }

    public List<IdNameRegisteredEventEventResponse> getIdNameRegisteredEventEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(IDNAMEREGISTEREDEVENT_EVENT, transactionReceipt);
        ArrayList<IdNameRegisteredEventEventResponse> responses = new ArrayList<IdNameRegisteredEventEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            IdNameRegisteredEventEventResponse typedResponse = new IdNameRegisteredEventEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.id = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.name = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<IdNameRegisteredEventEventResponse> idNameRegisteredEventEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, IdNameRegisteredEventEventResponse>() {
            @Override
            public IdNameRegisteredEventEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(IDNAMEREGISTEREDEVENT_EVENT, log);
                IdNameRegisteredEventEventResponse typedResponse = new IdNameRegisteredEventEventResponse();
                typedResponse.log = log;
                typedResponse.id = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.name = (String) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<IdNameRegisteredEventEventResponse> idNameRegisteredEventEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(IDNAMEREGISTEREDEVENT_EVENT));
        return idNameRegisteredEventEventFlowable(filter);
    }

    public List<IdURLSetEventEventResponse> getIdURLSetEventEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(IDURLSETEVENT_EVENT, transactionReceipt);
        ArrayList<IdURLSetEventEventResponse> responses = new ArrayList<IdURLSetEventEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            IdURLSetEventEventResponse typedResponse = new IdURLSetEventEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.id = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.url = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<IdURLSetEventEventResponse> idURLSetEventEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, IdURLSetEventEventResponse>() {
            @Override
            public IdURLSetEventEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(IDURLSETEVENT_EVENT, log);
                IdURLSetEventEventResponse typedResponse = new IdURLSetEventEventResponse();
                typedResponse.log = log;
                typedResponse.id = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.url = (String) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<IdURLSetEventEventResponse> idURLSetEventEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(IDURLSETEVENT_EVENT));
        return idURLSetEventEventFlowable(filter);
    }

    public List<IdentityCreatedEventEventResponse> getIdentityCreatedEventEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(IDENTITYCREATEDEVENT_EVENT, transactionReceipt);
        ArrayList<IdentityCreatedEventEventResponse> responses = new ArrayList<IdentityCreatedEventEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            IdentityCreatedEventEventResponse typedResponse = new IdentityCreatedEventEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.id = (String) eventValues.getIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<IdentityCreatedEventEventResponse> identityCreatedEventEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, IdentityCreatedEventEventResponse>() {
            @Override
            public IdentityCreatedEventEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(IDENTITYCREATEDEVENT_EVENT, log);
                IdentityCreatedEventEventResponse typedResponse = new IdentityCreatedEventEventResponse();
                typedResponse.log = log;
                typedResponse.id = (String) eventValues.getIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<IdentityCreatedEventEventResponse> identityCreatedEventEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(IDENTITYCREATEDEVENT_EVENT));
        return identityCreatedEventEventFlowable(filter);
    }

    public List<OrgCreatedEventEventResponse> getOrgCreatedEventEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(ORGCREATEDEVENT_EVENT, transactionReceipt);
        ArrayList<OrgCreatedEventEventResponse> responses = new ArrayList<OrgCreatedEventEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            OrgCreatedEventEventResponse typedResponse = new OrgCreatedEventEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.creator = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.orgID = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<OrgCreatedEventEventResponse> orgCreatedEventEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, OrgCreatedEventEventResponse>() {
            @Override
            public OrgCreatedEventEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(ORGCREATEDEVENT_EVENT, log);
                OrgCreatedEventEventResponse typedResponse = new OrgCreatedEventEventResponse();
                typedResponse.log = log;
                typedResponse.creator = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.orgID = (String) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<OrgCreatedEventEventResponse> orgCreatedEventEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ORGCREATEDEVENT_EVENT));
        return orgCreatedEventEventFlowable(filter);
    }

    public List<OrgMemberAddedEventEventResponse> getOrgMemberAddedEventEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(ORGMEMBERADDEDEVENT_EVENT, transactionReceipt);
        ArrayList<OrgMemberAddedEventEventResponse> responses = new ArrayList<OrgMemberAddedEventEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            OrgMemberAddedEventEventResponse typedResponse = new OrgMemberAddedEventEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.orgID = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.memberID = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<OrgMemberAddedEventEventResponse> orgMemberAddedEventEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, OrgMemberAddedEventEventResponse>() {
            @Override
            public OrgMemberAddedEventEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(ORGMEMBERADDEDEVENT_EVENT, log);
                OrgMemberAddedEventEventResponse typedResponse = new OrgMemberAddedEventEventResponse();
                typedResponse.log = log;
                typedResponse.orgID = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.memberID = (String) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<OrgMemberAddedEventEventResponse> orgMemberAddedEventEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ORGMEMBERADDEDEVENT_EVENT));
        return orgMemberAddedEventEventFlowable(filter);
    }

    public List<OrgMemberRemovedEventEventResponse> getOrgMemberRemovedEventEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(ORGMEMBERREMOVEDEVENT_EVENT, transactionReceipt);
        ArrayList<OrgMemberRemovedEventEventResponse> responses = new ArrayList<OrgMemberRemovedEventEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            OrgMemberRemovedEventEventResponse typedResponse = new OrgMemberRemovedEventEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.orgID = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.memberID = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<OrgMemberRemovedEventEventResponse> orgMemberRemovedEventEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, OrgMemberRemovedEventEventResponse>() {
            @Override
            public OrgMemberRemovedEventEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(ORGMEMBERREMOVEDEVENT_EVENT, log);
                OrgMemberRemovedEventEventResponse typedResponse = new OrgMemberRemovedEventEventResponse();
                typedResponse.log = log;
                typedResponse.orgID = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.memberID = (String) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<OrgMemberRemovedEventEventResponse> orgMemberRemovedEventEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ORGMEMBERREMOVEDEVENT_EVENT));
        return orgMemberRemovedEventEventFlowable(filter);
    }

    public List<OrgNameRegisteredEventEventResponse> getOrgNameRegisteredEventEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(ORGNAMEREGISTEREDEVENT_EVENT, transactionReceipt);
        ArrayList<OrgNameRegisteredEventEventResponse> responses = new ArrayList<OrgNameRegisteredEventEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            OrgNameRegisteredEventEventResponse typedResponse = new OrgNameRegisteredEventEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.orgID = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.name = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<OrgNameRegisteredEventEventResponse> orgNameRegisteredEventEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, OrgNameRegisteredEventEventResponse>() {
            @Override
            public OrgNameRegisteredEventEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(ORGNAMEREGISTEREDEVENT_EVENT, log);
                OrgNameRegisteredEventEventResponse typedResponse = new OrgNameRegisteredEventEventResponse();
                typedResponse.log = log;
                typedResponse.orgID = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.name = (String) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<OrgNameRegisteredEventEventResponse> orgNameRegisteredEventEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ORGNAMEREGISTEREDEVENT_EVENT));
        return orgNameRegisteredEventEventFlowable(filter);
    }

    public List<OrgOwnerChangedEventEventResponse> getOrgOwnerChangedEventEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(ORGOWNERCHANGEDEVENT_EVENT, transactionReceipt);
        ArrayList<OrgOwnerChangedEventEventResponse> responses = new ArrayList<OrgOwnerChangedEventEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            OrgOwnerChangedEventEventResponse typedResponse = new OrgOwnerChangedEventEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.orgID = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.oldOwner = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.newOwner = (String) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<OrgOwnerChangedEventEventResponse> orgOwnerChangedEventEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, OrgOwnerChangedEventEventResponse>() {
            @Override
            public OrgOwnerChangedEventEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(ORGOWNERCHANGEDEVENT_EVENT, log);
                OrgOwnerChangedEventEventResponse typedResponse = new OrgOwnerChangedEventEventResponse();
                typedResponse.log = log;
                typedResponse.orgID = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.oldOwner = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.newOwner = (String) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<OrgOwnerChangedEventEventResponse> orgOwnerChangedEventEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ORGOWNERCHANGEDEVENT_EVENT));
        return orgOwnerChangedEventEventFlowable(filter);
    }

    public List<OrgURLSetEventEventResponse> getOrgURLSetEventEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(ORGURLSETEVENT_EVENT, transactionReceipt);
        ArrayList<OrgURLSetEventEventResponse> responses = new ArrayList<OrgURLSetEventEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            OrgURLSetEventEventResponse typedResponse = new OrgURLSetEventEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.orgID = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.url = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<OrgURLSetEventEventResponse> orgURLSetEventEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, OrgURLSetEventEventResponse>() {
            @Override
            public OrgURLSetEventEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(ORGURLSETEVENT_EVENT, log);
                OrgURLSetEventEventResponse typedResponse = new OrgURLSetEventEventResponse();
                typedResponse.log = log;
                typedResponse.orgID = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.url = (String) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<OrgURLSetEventEventResponse> orgURLSetEventEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ORGURLSETEVENT_EVENT));
        return orgURLSetEventEventFlowable(filter);
    }

    public RemoteFunctionCall<TransactionReceipt> addOrgMember(String orgID, String memberID) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_ADDORGMEMBER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, orgID), 
                new org.web3j.abi.datatypes.Address(160, memberID)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> changeOrgOwner(String orgID, String newOwner) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_CHANGEORGOWNER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, orgID), 
                new org.web3j.abi.datatypes.Address(160, newOwner)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> createApp(String appID, String name, String url, BigInteger appType, List<String> dependencies) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_CREATEAPP, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, appID), 
                new org.web3j.abi.datatypes.Utf8String(name), 
                new org.web3j.abi.datatypes.Utf8String(url), 
                new org.web3j.abi.datatypes.generated.Uint8(appType), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Address>(
                        org.web3j.abi.datatypes.Address.class,
                        org.web3j.abi.Utils.typeMap(dependencies, org.web3j.abi.datatypes.Address.class))), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> createAppVersion(String appID, String appVersionID, String url) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_CREATEAPPVERSION, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, appID), 
                new org.web3j.abi.datatypes.Address(160, appVersionID), 
                new org.web3j.abi.datatypes.Utf8String(url)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> createIdentity(String name, String url, String idDelegate) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_CREATEIDENTITY, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(name), 
                new org.web3j.abi.datatypes.Utf8String(url), 
                new org.web3j.abi.datatypes.Address(160, idDelegate)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> createOrganization(String orgID, String name, String url) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_CREATEORGANIZATION, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, orgID), 
                new org.web3j.abi.datatypes.Utf8String(name), 
                new org.web3j.abi.datatypes.Utf8String(url)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> getAppForVersion(String appVersionID) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETAPPFORVERSION, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, appVersionID)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> getAppID(String name) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETAPPID, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(name)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> getAppOwner(String appID) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETAPPOWNER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, appID)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> getAppOwnerByName(String name) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETAPPOWNERBYNAME, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(name)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> getDelegate(String id) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETDELEGATE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, id)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> getIdentity(String name) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETIDENTITY, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(name)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> getIdentityName(String id) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETIDENTITYNAME, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, id)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> getIdentityURL(String id) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETIDENTITYURL, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, id)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> getOrgID(String name) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETORGID, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(name)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> getOrgName(String id) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETORGNAME, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, id)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> getOrgOwner(String orgID) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETORGOWNER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, orgID)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> getOrgURL(String id) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETORGURL, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, id)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<Boolean> isNameRegistered(String name) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ISNAMEREGISTERED, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(name)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<Boolean> isOrgMember(String orgID, String memberID) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ISORGMEMBER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, orgID), 
                new org.web3j.abi.datatypes.Address(160, memberID)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<Boolean> isRevokedDelegateForID(String id, String delegate) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ISREVOKEDDELEGATEFORID, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, id), 
                new org.web3j.abi.datatypes.Address(160, delegate)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<TransactionReceipt> registerName(String appID, String name) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_REGISTERNAME, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, appID), 
                new org.web3j.abi.datatypes.Utf8String(name)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> removeOrgMember(String orgID, String memberID) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_REMOVEORGMEMBER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, orgID), 
                new org.web3j.abi.datatypes.Address(160, memberID)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setAppOwner(String appID, String newOwner) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETAPPOWNER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, appID), 
                new org.web3j.abi.datatypes.Address(160, newOwner)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setDelegate(String idDelegate) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETDELEGATE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, idDelegate)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setURL(String identity, String url) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETURL, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, identity), 
                new org.web3j.abi.datatypes.Utf8String(url)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setVersionURL(String appVersionID, String url) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETVERSIONURL, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, appVersionID), 
                new org.web3j.abi.datatypes.Utf8String(url)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static BladeRegistry load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new BladeRegistry(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static BladeRegistry load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new BladeRegistry(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static BladeRegistry load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new BladeRegistry(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static BladeRegistry load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new BladeRegistry(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<BladeRegistry> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(BladeRegistry.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<BladeRegistry> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(BladeRegistry.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<BladeRegistry> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(BladeRegistry.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<BladeRegistry> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(BladeRegistry.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class AppNameRegisteredEventEventResponse extends BaseEventResponse {
        public String appID;

        public String appName;
    }

    public static class AppRegisteredEventEventResponse extends BaseEventResponse {
        public String appID;
    }

    public static class AppSetOwnerEventEventResponse extends BaseEventResponse {
        public String appID;

        public String appOwnerID;
    }

    public static class AppVersionRegisteredEventEventResponse extends BaseEventResponse {
        public String appID;

        public String appVersionID;
    }

    public static class AppVersionURLSetEventEventResponse extends BaseEventResponse {
        public String appID;

        public String appURL;
    }

    public static class IdDelegateRegisteredEventEventResponse extends BaseEventResponse {
        public String id;

        public String delegate;
    }

    public static class IdDelegateRevokedEventEventResponse extends BaseEventResponse {
        public String id;

        public String delegate;
    }

    public static class IdNameRegisteredEventEventResponse extends BaseEventResponse {
        public String id;

        public String name;
    }

    public static class IdURLSetEventEventResponse extends BaseEventResponse {
        public String id;

        public String url;
    }

    public static class IdentityCreatedEventEventResponse extends BaseEventResponse {
        public String id;
    }

    public static class OrgCreatedEventEventResponse extends BaseEventResponse {
        public String creator;

        public String orgID;
    }

    public static class OrgMemberAddedEventEventResponse extends BaseEventResponse {
        public String orgID;

        public String memberID;
    }

    public static class OrgMemberRemovedEventEventResponse extends BaseEventResponse {
        public String orgID;

        public String memberID;
    }

    public static class OrgNameRegisteredEventEventResponse extends BaseEventResponse {
        public String orgID;

        public String name;
    }

    public static class OrgOwnerChangedEventEventResponse extends BaseEventResponse {
        public String orgID;

        public String oldOwner;

        public String newOwner;
    }

    public static class OrgURLSetEventEventResponse extends BaseEventResponse {
        public String orgID;

        public String url;
    }
}
