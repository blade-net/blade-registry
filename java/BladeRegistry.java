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
import org.web3j.abi.datatypes.generated.Uint8;
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
    public static final String BINARY = "608060405234801561001057600080fd5b5061207f806100206000396000f3fe608060405234801561001057600080fd5b50600436106101a95760003560e01c8063716890a5116100f9578063d6f3663b11610097578063eda742b211610071578063eda742b2146103d1578063f6375eec146103e4578063f9e41d56146103f7578063ffb42b511461040a57600080fd5b8063d6f3663b14610398578063d7352ed7146103ab578063e6843814146103be57600080fd5b806394ae93b8116100d357806394ae93b81461034c578063a3135b0b1461035f578063c89782a214610372578063ca5eb5e11461038557600080fd5b8063716890a5146103135780638ae72a43146103265780638f1f028d1461033957600080fd5b80634066db6911610166578063544d856411610140578063544d8564146102ae5780635f23312e146102da5780636900f1b3146102ed5780636b4b3ab51461030057600080fd5b80634066db691461025a578063494af7dd146102865780634fb06e321461029b57600080fd5b80630830602b146101ae5780630a29ae6f146101d6578063161dc5bf14610201578063165e8f5314610214578063270b0c2d1461022757806335aac13214610247575b600080fd5b6101c16101bc366004611e40565b61041d565b60405190151581526020015b60405180910390f35b6101e96101e4366004611e40565b6104ad565b6040516001600160a01b0390911681526020016101cd565b6101c161020f366004611c79565b610598565b6101c1610222366004611c18565b6106ef565b61023a610235366004611bc3565b610876565b6040516101cd9190611f53565b6101c1610255366004611be5565b610922565b6101e9610268366004611bc3565b6001600160a01b039081166000908152600c60205260409020541690565b610299610294366004611be5565b610985565b005b6101c16102a9366004611be5565b610a7c565b6101e96102bc366004611bc3565b6001600160a01b039081166000908152600460205260409020541690565b6101c16102e8366004611e82565b610adc565b6101e96102fb366004611e40565b610e06565b6101c161030e366004611be5565b610e3b565b61023a610321366004611bc3565b610e99565b6101c1610334366004611c79565b610ec0565b610299610347366004611be5565b610fd1565b6101c161035a366004611e40565b6110bd565b6101e961036d366004611e40565b611138565b61023a610380366004611bc3565b6111a8565b6101c1610393366004611bc3565b6111cf565b61023a6103a6366004611bc3565b611303565b6101c16103b9366004611ccc565b61132a565b6101e96103cc366004611bc3565b6115dc565b6101e96103df366004611e40565b61167f565b6101c16103f2366004611d4d565b6116c9565b6101c1610405366004611be5565b6118e8565b6101e9610418366004611bc3565b6119d5565b6000806001600160a01b0316600e848460405161043b929190611f03565b908152604051908190036020019020546001600160a01b031614610461575060006104a7565b33600e8484604051610474929190611f03565b90815260405190819003602001902080546001600160a01b03929092166001600160a01b03199092169190911790555060015b92915050565b6000806001600160a01b03166001600085856040516020016104d0929190611f03565b60408051601f19818403018152918152815160209283012083529082019290925201600020546001600160a01b0316141561054b5760405162461bcd60e51b815260206004820152601660248201527513985b59481a5cc81b9bdd081c9959da5cdd195c995960521b60448201526064015b60405180910390fd5b600160008484604051602001610562929190611f03565b60408051808303601f19018152918152815160209283012083529082019290925201600020546001600160a01b03169392505050565b6001600160a01b038381166000908152600c60205260408120549091166106275760405162461bcd60e51b815260206004820152603b60248201527f416e206170702076657273696f6e20776974682074686520676976656e20617060448201527f702076657273696f6e20494420646f6573206e6f7420657869737400000000006064820152608401610542565b6001600160a01b038085166000908152600c60209081526040808320548416808452600b9092529091205490911633146106c05760405162461bcd60e51b815260206004820152603460248201527f4f6e6c792074686520617070206f776e657220697320616c6c6f77656420746f604482015273206368616e67652076657273696f6e2055524c7360601b6064820152608401610542565b6001600160a01b03851660009081526011602052604090206106e3908585611a77565b50600195945050505050565b6001600160a01b038481166000908152600b602052604081205490911633146107805760405162461bcd60e51b815260206004820152603a60248201527f4f6e6c792074686520617070206f776e657220697320616c6c6f77656420746f60448201527f207265676973746572206e6577206170702076657273696f6e730000000000006064820152608401610542565b6001600160a01b038481166000908152600c6020526040902054161561080e5760405162461bcd60e51b815260206004820152603b60248201527f416e206170702076657273696f6e20776974682074686520676976656e20617060448201527f702076657273696f6e20494420616c72656164792065786973747300000000006064820152608401610542565b6001600160a01b038086166000818152600d602090815260408083208054600181018255908452828420018054958a166001600160a01b031996871681179091558352600c8252808320805490951684179094559181526011909152206106e3908484611a77565b6001600160a01b038116600090815260086020526040902080546060919061089d90611ff8565b80601f01602080910402602001604051908101604052809291908181526020018280546108c990611ff8565b80156109165780601f106108eb57610100808354040283529160200191610916565b820191906000526020600020905b8154815290600101906020018083116108f957829003601f168201915b50505050509050919050565b6001600160a01b038281166000908152600b602052604081205490911633141561097c57506001600160a01b038281166000908152600b6020526040902080546001600160a01b03191691831691909117905560016104a7565b50600092915050565b6001600160a01b03828116600090815260066020526040902054163314610a0a5760405162461bcd60e51b815260206004820152603360248201527f4f6e6c79206f776e6572732063616e2072656d6f7665206d656d6265727320666044820152723937b69030b71037b933b0b734bd30ba34b7b760691b6064820152608401610542565b6001600160a01b0382811660008181526007602090815260408083209486168084529482529182902080546001600160a01b031916905581519283528201929092527fd6882887428041ff22c6c283385d277566d1c8f80a6ea358bc005f02f2b2e99e91015b60405180910390a15050565b6000610a8783611a1e565b610aa35760405162461bcd60e51b815260040161054290611fa8565b6001600160a01b038381166000908152600760209081526040808320848716808552925290912054909116141561097c575060016104a7565b336000908152602081905260408120546001600160a01b031615610b425760405162461bcd60e51b815260206004820152601b60248201527f4964656e7469747920616c7265616479207265676973746572656400000000006044820152606401610542565b60006001600160a01b0316600160008888604051602001610b64929190611f03565b60408051601f19818403018152918152815160209283012083529082019290925201600020546001600160a01b031614610be05760405162461bcd60e51b815260206004820152601760248201527f4e616d6520616c726561647920726567697374657265640000000000000000006044820152606401610542565b3360008181526020818152604080832080546001600160a01b031916851790555160019291610c13918b918b9101611f03565b60408051808303601f190181529181528151602092830120835282820193909352908201600090812080546001600160a01b0319166001600160a01b039590951694909417909355338352600290529020610c6f908787611a77565b506040513381527ff5acefdfaba7bcdd5f52eb944424dd9ca7f40b49bceb486aa8d21149d0e0a23b9060200160405180910390a17fd8b02f5509ad5b2b908c8fcb97d8f2180a100a92cb76acecd64287ac99d82252338787604051610cd693929190611f13565b60405180910390a1336001600160a01b0383161415610d415760405162461bcd60e51b815260206004820152602160248201527f696444656c6567617465206d757374206e6f742062652073616d6520617320696044820152601960fa1b6064820152608401610542565b3360008181526004602090815260409182902080546001600160a01b0319166001600160a01b0387169081179091558251938452908301527fe28e068d26f2d0dd527a4f6e697749fd7c44a2a2035fe442377a04ffa4903e55910160405180910390a1336000908152600360205260409020610dbe908585611a77565b507fd0fbcea0427001752d6fa09fa8a7adcb05314f305d79caa5ef3815951970e1b5338585604051610df293929190611f13565b60405180910390a150600195945050505050565b6000600e8383604051610e1a929190611f03565b908152604051908190036020019020546001600160a01b0316905092915050565b6001600160a01b03818116600090815260056020526040812054909116610e64575060006104a7565b6001600160a01b0382811660009081526005602052604090205481169084161415610e91575060016104a7565b5060006104a7565b6001600160a01b038116600090815260026020526040902080546060919061089d90611ff8565b60006001600160a01b0384163314801590610ef557506001600160a01b03848116600090815260046020526040902054163314155b15610f685760405162461bcd60e51b815260206004820152603960248201527f4f6e6c7920746865206964656e74697479206f776e6572206f7220686973206460448201527f656c676174652063616e20757064617465207468652055524c000000000000006064820152608401610542565b7fd0fbcea0427001752d6fa09fa8a7adcb05314f305d79caa5ef3815951970e1b5848484604051610f9b93929190611f13565b60405180910390a16001600160a01b0384166000908152600360205260409020610fc6908484611a77565b506001949350505050565b6001600160a01b038281166000908152600660205260409020541633146110515760405162461bcd60e51b815260206004820152602e60248201527f4f6e6c79206f776e6572732063616e20616464206d656d6265727320746f206160448201526d371037b933b0b734bd30ba34b7b760911b6064820152608401610542565b6001600160a01b0382811660008181526007602090815260408083209486168084529482529182902080546001600160a01b0319168517905581519283528201929092527fe96293aade87be7aef1c4bfb2a9f310cd13be4a519939310f9b6ef2f8e9db5609101610a70565b60008083836040516020016110d3929190611f03565b60408051601f198184030181529181528151602092830120600081815260019093529120549091506001600160a01b031661112e576000818152600960205260409020546001600160a01b031661112e5760009150506104a7565b5060019392505050565b6000806001600160a01b031660096000858560405160200161115b929190611f03565b60408051601f19818403018152918152815160209283012083529082019290925201600020546001600160a01b0316141561054b5760405162461bcd60e51b815260040161054290611fa8565b6001600160a01b0381166000908152600a6020526040902080546060919061089d90611ff8565b60006111da33611a4d565b6112325760405162461bcd60e51b8152602060048201526024808201527f53656e646572206973206e6f742072656769737465726564206173206964656e6044820152637469747960e01b6064820152608401610542565b33600081815260046020818152604080842080546001600160a01b039081168087526005855283872080546001600160a01b03199081168a17909155968890529484528154908916951685179055805194855290840192909252917fbdce5f229c4323d8318f6ef9dea6239484e0c721721a57b6bc913795736c9932910160405180910390a1604080513381526001600160a01b03851660208201527fe28e068d26f2d0dd527a4f6e697749fd7c44a2a2035fe442377a04ffa4903e5591015b60405180910390a150600192915050565b6001600160a01b038116600090815260036020526040902080546060919061089d90611ff8565b6001600160a01b03858116600090815260066020526040812054909116156113b35760405162461bcd60e51b815260206004820152603660248201527f416e206f7267616e697a6174696f6e20666f7220746869732061646472657373604482015275081a5cc8185b1c9958591e481c9959da5cdd195c995960521b6064820152608401610542565b60006001600160a01b03166009600087876040516020016113d5929190611f03565b60408051601f19818403018152918152815160209283012083529082019290925201600020546001600160a01b03161461146e5760405162461bcd60e51b815260206004820152603460248201527f416e206f7267616e697a6174696f6e20776974682074686973206e616d6520696044820152731cc8185b1c9958591e481c9959da5cdd195c995960621b6064820152608401610542565b6001600160a01b038616600090815260066020908152604080832080546001600160a01b0319163317905551889260099290916114af918a918a9101611f03565b60408051808303601f190181529181528151602092830120835282820193909352908201600090812080546001600160a01b0319166001600160a01b039586161790559289168352600a90529020611508908686611a77565b50604080513381526001600160a01b03881660208201527f55ba28cf8c1f7e69edc8abc763f7d458012c9939763a2366115a655598b67060910160405180910390a17f83e1e3877ad7bca56f1db475bcfc4ea2884b20feca8852f44bd3517eb63ecb1786868660405161157d93929190611f13565b60405180910390a16001600160a01b03861660009081526008602052604090206115a8908484611a77565b507f156cbc4e7b0956859c3c6a52b4cdab2505efd27a102317118946fea5d1ed5ec2868484604051610df293929190611f13565b6001600160a01b038181166000908152600660205260408120549091166116605760405162461bcd60e51b815260206004820152603260248201527f416e206f7267616e697a6174696f6e20666f7220746869732061646472657373604482015271081a5cc81b9bdd081c9959da5cdd195c995960721b6064820152608401610542565b506001600160a01b039081166000908152600660205260409020541690565b60008061168c8484610e06565b90506001600160a01b038116156116bf576001600160a01b039081166000908152600b60205260409020541690506104a7565b5060009392505050565b6001600160a01b038881166000908152600b60205260408120549091166117325760405162461bcd60e51b815260206004820152601e60248201527f417070206164647265737320616c7265616479207265676973746572656400006044820152606401610542565b60006001600160a01b0316600e898960405161174f929190611f03565b908152604051908190036020019020546001600160a01b031614156117b65760405162461bcd60e51b815260206004820152601b60248201527f417070206e616d6520616c7265616479207265676973746572656400000000006044820152606401610542565b6001600160a01b0389166000908152600b6020908152604080832080546001600160a01b03191633179055600f9091529020805485919060ff19166001838181111561180457611804612033565b02179055506001600160a01b0389166000818152600d6020908152604080832080546001810182559084528284200180546001600160a01b03199081168617909155848452600c909252918290208054909116909217909155518990600e90611870908b908b90611f03565b908152604080516020928190038301902080546001600160a01b0319166001600160a01b03948516179055918b1660009081526011909152206118b4908787611a77565b506001600160a01b03891660009081526010602052604090206118d8908484611afb565b5060019998505050505050505050565b6001600160a01b03828116600090815260066020526040812054909116331461196a5760405162461bcd60e51b815260206004820152602e60248201527f4f6e6c79206f776e6572732063616e206368616e676520616e206f7267616e6960448201526d3d30ba34b7b713b99037bbb732b960911b6064820152608401610542565b6001600160a01b0383811660008181526006602090815260409182902080546001600160a01b0319169487169485179055815192835233908301528101919091527f1c8822d3d1f26a0fa7726af16fcfaf7900796bc2d6659b447fc697c407b25e90906060016112f2565b6001600160a01b038181166000908152600b602052604081205490911615611a1657506001600160a01b039081166000908152600b60205260409020541690565b506000919050565b6001600160a01b0381811660009081526006602052604081205490911615611a1657506001919050565b919050565b6001600160a01b0381811660009081526020819052604081205490911615611a1657506001919050565b828054611a8390611ff8565b90600052602060002090601f016020900481019282611aa55760008555611aeb565b82601f10611abe5782800160ff19823516178555611aeb565b82800160010185558215611aeb579182015b82811115611aeb578235825591602001919060010190611ad0565b50611af7929150611b4e565b5090565b828054828255906000526020600020908101928215611aeb579160200282015b82811115611aeb5781546001600160a01b0319166001600160a01b03843516178255602090920191600190910190611b1b565b5b80821115611af75760008155600101611b4f565b80356001600160a01b0381168114611a4857600080fd5b60008083601f840112611b8c57600080fd5b50813567ffffffffffffffff811115611ba457600080fd5b602083019150836020828501011115611bbc57600080fd5b9250929050565b600060208284031215611bd557600080fd5b611bde82611b63565b9392505050565b60008060408385031215611bf857600080fd5b611c0183611b63565b9150611c0f60208401611b63565b90509250929050565b60008060008060608587031215611c2e57600080fd5b611c3785611b63565b9350611c4560208601611b63565b9250604085013567ffffffffffffffff811115611c6157600080fd5b611c6d87828801611b7a565b95989497509550505050565b600080600060408486031215611c8e57600080fd5b611c9784611b63565b9250602084013567ffffffffffffffff811115611cb357600080fd5b611cbf86828701611b7a565b9497909650939450505050565b600080600080600060608688031215611ce457600080fd5b611ced86611b63565b9450602086013567ffffffffffffffff80821115611d0a57600080fd5b611d1689838a01611b7a565b90965094506040880135915080821115611d2f57600080fd5b50611d3c88828901611b7a565b969995985093965092949392505050565b60008060008060008060008060a0898b031215611d6957600080fd5b611d7289611b63565b9750602089013567ffffffffffffffff80821115611d8f57600080fd5b611d9b8c838d01611b7a565b909950975060408b0135915080821115611db457600080fd5b611dc08c838d01611b7a565b909750955060608b0135915060028210611dd957600080fd5b90935060808a01359080821115611def57600080fd5b818b0191508b601f830112611e0357600080fd5b813581811115611e1257600080fd5b8c60208260051b8501011115611e2757600080fd5b6020830194508093505050509295985092959890939650565b60008060208385031215611e5357600080fd5b823567ffffffffffffffff811115611e6a57600080fd5b611e7685828601611b7a565b90969095509350505050565b600080600080600060608688031215611e9a57600080fd5b853567ffffffffffffffff80821115611eb257600080fd5b611ebe89838a01611b7a565b90975095506020880135915080821115611ed757600080fd5b50611ee488828901611b7a565b9094509250611ef7905060408701611b63565b90509295509295909350565b8183823760009101908152919050565b6001600160a01b03841681526040602082018190528101829052818360608301376000818301606090810191909152601f909201601f1916010192915050565b600060208083528351808285015260005b81811015611f8057858101830151858201604001528201611f64565b81811115611f92576000604083870101525b50601f01601f1916929092016040019392505050565b60208082526030908201527f416e206f7267616e697a6174696f6e20776974682074686973206e616d65206960408201526f1cc81b9bdd081c9959da5cdd195c995960821b606082015260800190565b600181811c9082168061200c57607f821691505b6020821081141561202d57634e487b7160e01b600052602260045260246000fd5b50919050565b634e487b7160e01b600052602160045260246000fdfea26469706673582212203dc935dc129e5aeda19aa80b1f8dacafc14a3682cfa9e26db82ae44407a2349664736f6c63430008060033";

    public static final String FUNC_ADDORGMEMBER = "addOrgMember";

    public static final String FUNC_CHANGEORGOWNER = "changeOrgOwner";

    public static final String FUNC_CREATEAPP = "createApp";

    public static final String FUNC_CREATEAPPVERSION = "createAppVersion";

    public static final String FUNC_CREATEIDENTITY = "createIdentity";

    public static final String FUNC_CREATEORGANIZATION = "createOrganization";

    public static final String FUNC_GETAPPFORVERSION = "getAppForVersion";

    public static final String FUNC_GETAPPID = "getAppID";

    public static final String FUNC_getAppOwner = "getAppOwner";

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

    public static final Event APPREGISTERED_EVENT = new Event("AppRegistered", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>() {}, new TypeReference<Uint8>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Address>() {}));
    ;

    public static final Event IDDELEGATEREGISTEREDEVENT_EVENT = new Event("IdDelegateRegisteredEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}));
    ;

    public static final Event IDDELEGATEREVOKEDEVENT_EVENT = new Event("IdDelegateRevokedEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}));
    ;

    public static final Event IDNAMEREGISTEREDEVENT_EVENT = new Event("IdNameRegisteredEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}));
    ;

    public static final Event IDURLSETEVENT_EVENT = new Event("IdURLSetEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}));
    ;

    public static final Event IDENTITYCREATEDEVENT_EVENT = new Event("IdentityCreatedEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
    ;

    public static final Event ORGCREATEDEVENT_EVENT = new Event("OrgCreatedEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}));
    ;

    public static final Event ORGMEMBERADDEDEVENT_EVENT = new Event("OrgMemberAddedEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}));
    ;

    public static final Event ORGMEMBERREMOVEDEVENT_EVENT = new Event("OrgMemberRemovedEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}));
    ;

    public static final Event ORGNAMEREGISTEREDEVENT_EVENT = new Event("OrgNameRegisteredEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}));
    ;

    public static final Event ORGOWNERCHANGEDEVENT_EVENT = new Event("OrgOwnerChangedEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Address>() {}));
    ;

    public static final Event ORGURLSETEVENT_EVENT = new Event("OrgURLSetEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}));
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

    public List<AppRegisteredEventResponse> getAppRegisteredEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(APPREGISTERED_EVENT, transactionReceipt);
        ArrayList<AppRegisteredEventResponse> responses = new ArrayList<AppRegisteredEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            AppRegisteredEventResponse typedResponse = new AppRegisteredEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.appID = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.appVersionID = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.appType = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.appName = (String) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.owner = (String) eventValues.getNonIndexedValues().get(3).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<AppRegisteredEventResponse> appRegisteredEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, AppRegisteredEventResponse>() {
            @Override
            public AppRegisteredEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(APPREGISTERED_EVENT, log);
                AppRegisteredEventResponse typedResponse = new AppRegisteredEventResponse();
                typedResponse.log = log;
                typedResponse.appID = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.appVersionID = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.appType = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.appName = (String) eventValues.getNonIndexedValues().get(2).getValue();
                typedResponse.owner = (String) eventValues.getNonIndexedValues().get(3).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<AppRegisteredEventResponse> appRegisteredEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(APPREGISTERED_EVENT));
        return appRegisteredEventFlowable(filter);
    }

    public List<IdDelegateRegisteredEventEventResponse> getIdDelegateRegisteredEventEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(IDDELEGATEREGISTEREDEVENT_EVENT, transactionReceipt);
        ArrayList<IdDelegateRegisteredEventEventResponse> responses = new ArrayList<IdDelegateRegisteredEventEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            IdDelegateRegisteredEventEventResponse typedResponse = new IdDelegateRegisteredEventEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.id = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.delegate = (String) eventValues.getNonIndexedValues().get(1).getValue();
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
                typedResponse.id = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.delegate = (String) eventValues.getNonIndexedValues().get(1).getValue();
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
            typedResponse.id = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.delegate = (String) eventValues.getNonIndexedValues().get(1).getValue();
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
                typedResponse.id = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.delegate = (String) eventValues.getNonIndexedValues().get(1).getValue();
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
            typedResponse.id = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.name = (String) eventValues.getNonIndexedValues().get(1).getValue();
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
                typedResponse.id = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.name = (String) eventValues.getNonIndexedValues().get(1).getValue();
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
            typedResponse.id = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.url = (String) eventValues.getNonIndexedValues().get(1).getValue();
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
                typedResponse.id = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.url = (String) eventValues.getNonIndexedValues().get(1).getValue();
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
            typedResponse.id = (String) eventValues.getNonIndexedValues().get(0).getValue();
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
                typedResponse.id = (String) eventValues.getNonIndexedValues().get(0).getValue();
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
            typedResponse.creator = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.orgID = (String) eventValues.getNonIndexedValues().get(1).getValue();
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
                typedResponse.creator = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.orgID = (String) eventValues.getNonIndexedValues().get(1).getValue();
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
            typedResponse.orgID = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.memberID = (String) eventValues.getNonIndexedValues().get(1).getValue();
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
                typedResponse.orgID = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.memberID = (String) eventValues.getNonIndexedValues().get(1).getValue();
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
            typedResponse.orgID = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.memberID = (String) eventValues.getNonIndexedValues().get(1).getValue();
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
                typedResponse.orgID = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.memberID = (String) eventValues.getNonIndexedValues().get(1).getValue();
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
            typedResponse.orgID = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.name = (String) eventValues.getNonIndexedValues().get(1).getValue();
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
                typedResponse.orgID = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.name = (String) eventValues.getNonIndexedValues().get(1).getValue();
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
            typedResponse.orgID = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.oldOwner = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.newOwner = (String) eventValues.getNonIndexedValues().get(2).getValue();
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
                typedResponse.orgID = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.oldOwner = (String) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.newOwner = (String) eventValues.getNonIndexedValues().get(2).getValue();
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
            typedResponse.orgID = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.url = (String) eventValues.getNonIndexedValues().get(1).getValue();
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
                typedResponse.orgID = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.url = (String) eventValues.getNonIndexedValues().get(1).getValue();
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

    public RemoteFunctionCall<String> getAppOwner(String name) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_getAppOwner, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(name)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> getAppOwner(String appID) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_getAppOwner, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, appID)), 
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

    public RemoteFunctionCall<TransactionReceipt> registerName(String name) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_REGISTERNAME, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(name)), 
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

    public static class AppRegisteredEventResponse extends BaseEventResponse {
        public String appID;

        public String appVersionID;

        public BigInteger appType;

        public String appName;

        public String owner;
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
