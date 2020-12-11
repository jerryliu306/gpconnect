package io.pivotal.greenplum.spark.conf;

import com.typesafe.scalalogging.LazyLogging;
import com.typesafe.scalalogging.LazyLogging$class;
import com.typesafe.scalalogging.Logger;
import org.apache.spark.sql.catalyst.util.CaseInsensitiveMap;
import org.apache.spark.sql.catalyst.util.CaseInsensitiveMap.;
import scala.Function2;
import scala.Option;
import scala.Serializable;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.util.Try;

@ScalaSignature(
   bytes = "\u0006\u0001\u0005]f\u0001B\u0001\u0003\u00015\u0011\u0001c\u0012:fK:\u0004H.^7PaRLwN\\:\u000b\u0005\r!\u0011\u0001B2p]\u001aT!!\u0002\u0004\u0002\u000bM\u0004\u0018M]6\u000b\u0005\u001dA\u0011!C4sK\u0016t\u0007\u000f\\;n\u0015\tI!\"A\u0004qSZ|G/\u00197\u000b\u0003-\t!![8\u0004\u0001M)\u0001A\u0004\u000b\u00187A\u0011qBE\u0007\u0002!)\t\u0011#A\u0003tG\u0006d\u0017-\u0003\u0002\u0014!\t1\u0011I\\=SK\u001a\u0004\"aD\u000b\n\u0005Y\u0001\"\u0001D*fe&\fG.\u001b>bE2,\u0007C\u0001\r\u001a\u001b\u0005\u0011\u0011B\u0001\u000e\u0003\u0005\u001dy\u0005\u000f^5p]N\u0004\"\u0001H\u0012\u000e\u0003uQ!AH\u0010\u0002\u0019M\u001c\u0017\r\\1m_\u001e<\u0017N\\4\u000b\u0005\u0001\n\u0013\u0001\u0003;za\u0016\u001c\u0018MZ3\u000b\u0003\t\n1aY8n\u0013\t!SDA\u0006MCjLHj\\4hS:<\u0007\u0002\u0003\u0014\u0001\u0005\u000b\u0007I\u0011A\u0014\u0002\u0015A\f'/Y7fi\u0016\u00148/F\u0001)!\rISgN\u0007\u0002U)\u00111\u0006L\u0001\u0005kRLGN\u0003\u0002.]\u0005A1-\u0019;bYf\u001cHO\u0003\u00020a\u0005\u00191/\u001d7\u000b\u0005\u0015\t$B\u0001\u001a4\u0003\u0019\t\u0007/Y2iK*\tA'A\u0002pe\u001eL!A\u000e\u0016\u0003%\r\u000b7/Z%og\u0016t7/\u001b;jm\u0016l\u0015\r\u001d\t\u0003qmr!aD\u001d\n\u0005i\u0002\u0012A\u0002)sK\u0012,g-\u0003\u0002={\t11\u000b\u001e:j]\u001eT!A\u000f\t\t\u0011}\u0002!\u0011!Q\u0001\n!\n1\u0002]1sC6,G/\u001a:tA!)\u0011\t\u0001C\u0001\u0005\u00061A(\u001b8jiz\"\"a\u0011#\u0011\u0005a\u0001\u0001\"\u0002\u0014A\u0001\u0004A\u0003\"B!\u0001\t\u00031ECA\"H\u0011\u0015AU\t1\u0001J\u0003\u0019\u0001\u0018M]1ngB!\u0001HS\u001c8\u0013\tYUHA\u0002NCBDq!\u0014\u0001C\u0002\u0013\u0005a*A\u0002ve2,\u0012a\u000e\u0005\u0007!\u0002\u0001\u000b\u0011B\u001c\u0002\tU\u0014H\u000e\t\u0005\b%\u0002\u0011\r\u0011\"\u0001O\u0003\u0011)8/\u001a:\t\rQ\u0003\u0001\u0015!\u00038\u0003\u0015)8/\u001a:!\u0011\u001d1\u0006A1A\u0005\u0002]\u000b\u0001\u0002]1tg^|'\u000fZ\u000b\u00021B\u0019q\"W\u001c\n\u0005i\u0003\"AB(qi&|g\u000e\u0003\u0004]\u0001\u0001\u0006I\u0001W\u0001\na\u0006\u001c8o^8sI\u0002BqA\u0018\u0001C\u0002\u0013\u0005a*\u0001\u0005eEN\u001b\u0007.Z7b\u0011\u0019\u0001\u0007\u0001)A\u0005o\u0005IAMY*dQ\u0016l\u0017\r\t\u0005\bE\u0002\u0011\r\u0011\"\u0001O\u0003\u001d!'\rV1cY\u0016Da\u0001\u001a\u0001!\u0002\u00139\u0014\u0001\u00033c)\u0006\u0014G.\u001a\u0011\t\u000f\u0019\u0004!\u0019!C\u0001\u001d\u0006y\u0001/\u0019:uSRLwN\\\"pYVlg\u000e\u0003\u0004i\u0001\u0001\u0006IaN\u0001\u0011a\u0006\u0014H/\u001b;j_:\u001cu\u000e\\;n]\u0002BqA\u001b\u0001C\u0002\u0013\u00051.\u0001\u0006qCJ$\u0018\u000e^5p]N,\u0012\u0001\u001c\t\u0004\u001fek\u0007CA\bo\u0013\ty\u0007CA\u0002J]RDa!\u001d\u0001!\u0002\u0013a\u0017a\u00039beRLG/[8og\u0002Bqa\u001d\u0001C\u0002\u0013\u0005a*\u0001\u0004ee&4XM\u001d\u0005\u0007k\u0002\u0001\u000b\u0011B\u001c\u0002\u000f\u0011\u0014\u0018N^3sA!9q\u000f\u0001b\u0001\n\u0003A\u0018\u0001E2p]:,7\r^8s\u001fB$\u0018n\u001c8t+\u0005I\bC\u0001\r{\u0013\tY(A\u0001\tD_:tWm\u0019;pe>\u0003H/[8og\"1Q\u0010\u0001Q\u0001\ne\f\u0011cY8o]\u0016\u001cGo\u001c:PaRLwN\\:!\u0011!y\bA1A\u0005\u0002\u0005\u0005\u0011!F2p]:,7\r^5p]B{w\u000e\\(qi&|gn]\u000b\u0003\u0003\u0007\u00012\u0001GA\u0003\u0013\r\t9A\u0001\u0002\u0016\u0007>tg.Z2uS>t\u0007k\\8m\u001fB$\u0018n\u001c8t\u0011!\tY\u0001\u0001Q\u0001\n\u0005\r\u0011AF2p]:,7\r^5p]B{w\u000e\\(qi&|gn\u001d\u0011\t\u0013\u0005=\u0001A1A\u0005\u0002\u0005E\u0011!\u0004;sk:\u001c\u0017\r^3UC\ndW-\u0006\u0002\u0002\u0014A\u0019q\"!\u0006\n\u0007\u0005]\u0001CA\u0004C_>dW-\u00198\t\u0011\u0005m\u0001\u0001)A\u0005\u0003'\ta\u0002\u001e:v]\u000e\fG/\u001a+bE2,\u0007\u0005\u0003\u0005\u0002 \u0001\u0011\r\u0011\"\u0001X\u00035!\u0017n\u001d;sS\n,H/\u001a3Cs\"9\u00111\u0005\u0001!\u0002\u0013A\u0016A\u00043jgR\u0014\u0018NY;uK\u0012\u0014\u0015\u0010\t\u0005\n\u0003O\u0001!\u0019!C\u0001\u0003#\tA#\u001b;fe\u0006$xN](qi&l\u0017N_1uS>t\u0007\u0002CA\u0016\u0001\u0001\u0006I!a\u0005\u0002+%$XM]1u_J|\u0005\u000f^5nSj\fG/[8oA\u001d9\u0011q\u0006\u0002\t\u0002\u0005E\u0012\u0001E$sK\u0016t\u0007\u000f\\;n\u001fB$\u0018n\u001c8t!\rA\u00121\u0007\u0004\u0007\u0003\tA\t!!\u000e\u0014\u0007\u0005M\u0012\u0010C\u0004B\u0003g!\t!!\u000f\u0015\u0005\u0005E\u0002BCA\u001f\u0003g\u0011\r\u0011\"\u0001\u0002@\u0005Aq\t\u0015#C?V\u0013F*\u0006\u0002\u0002BA!\u00111IA'\u001b\t\t)E\u0003\u0003\u0002H\u0005%\u0013\u0001\u00027b]\u001eT!!a\u0013\u0002\t)\fg/Y\u0005\u0004y\u0005\u0015\u0003\"CA)\u0003g\u0001\u000b\u0011BA!\u0003%9\u0005\u000b\u0012\"`+Jc\u0005\u0005\u0003\u0006\u0002V\u0005M\"\u0019!C\u0001\u0003\u007f\t\u0011b\u0012)E\u0005~+6+\u0012*\t\u0013\u0005e\u00131\u0007Q\u0001\n\u0005\u0005\u0013AC$Q\t\n{VkU#SA!Q\u0011QLA\u001a\u0005\u0004%\t!a\u0010\u0002\u001b\u001d\u0003FIQ0Q\u0003N\u001bvk\u0014*E\u0011%\t\t'a\r!\u0002\u0013\t\t%\u0001\bH!\u0012\u0013u\fU!T'^{%\u000b\u0012\u0011\t\u0015\u0005\u0015\u00141\u0007b\u0001\n\u0003\ty$\u0001\tH!\u0012\u0013ulU\"I\u000b6\u000buLT!N\u000b\"I\u0011\u0011NA\u001aA\u0003%\u0011\u0011I\u0001\u0012\u000fB#%iX*D\u0011\u0016k\u0015i\u0018(B\u001b\u0016\u0003\u0003BCA7\u0003g\u0011\r\u0011\"\u0001\u0002@\u0005yq\t\u0015#C?R\u000b%\tT#`\u001d\u0006kU\tC\u0005\u0002r\u0005M\u0002\u0015!\u0003\u0002B\u0005\u0001r\t\u0015#C?R\u000b%\tT#`\u001d\u0006kU\t\t\u0005\u000b\u0003k\n\u0019D1A\u0005\u0002\u0005}\u0012!F$Q\t\n{\u0006+\u0011*U\u0013RKuJT0D\u001f2+VJ\u0014\u0005\n\u0003s\n\u0019\u0004)A\u0005\u0003\u0003\nac\u0012)E\u0005~\u0003\u0016I\u0015+J)&{ejX\"P\u0019Vke\n\t\u0005\u000b\u0003{\n\u0019D1A\u0005\u0002\u0005}\u0012aD$Q\t\n{\u0006+\u0011*U\u0013RKuJT*\t\u0013\u0005\u0005\u00151\u0007Q\u0001\n\u0005\u0005\u0013\u0001E$Q\t\n{\u0006+\u0011*U\u0013RKuJT*!\u0011)\t))a\rC\u0002\u0013\u0005\u0011qH\u0001\f\u000fB#%i\u0018#S\u0013Z+%\u000bC\u0005\u0002\n\u0006M\u0002\u0015!\u0003\u0002B\u0005aq\t\u0015#C?\u0012\u0013\u0016JV#SA!Q\u0011QRA\u001a\u0005\u0004%\t!a\u0010\u0002'\u001d\u0003FIQ0E\u0013N#&+\u0013\"V)\u0016#uLQ-\t\u0013\u0005E\u00151\u0007Q\u0001\n\u0005\u0005\u0013\u0001F$Q\t\n{F)S*U%&\u0013U\u000bV#E?\nK\u0006\u0005\u0003\u0006\u0002\u0016\u0006M\"\u0019!C\u0001\u0003\u007f\t1c\u0012)E\u0005~#&+\u0016(D\u0003R+u\fV!C\u0019\u0016C\u0011\"!'\u00024\u0001\u0006I!!\u0011\u0002)\u001d\u0003FIQ0U%Vs5)\u0011+F?R\u000b%\tT#!\u0011)\ti*a\rC\u0002\u0013\u0005\u0011qH\u0001\u001b\u000fB#%iX%U\u000bJ\u000bEk\u0014*`\u001fB#\u0016*T%[\u0003RKuJ\u0014\u0005\n\u0003C\u000b\u0019\u0004)A\u0005\u0003\u0003\n1d\u0012)E\u0005~KE+\u0012*B)>\u0013vl\u0014)U\u00136K%,\u0011+J\u001f:\u0003\u0003BCAS\u0003g\u0011\r\u0011\"\u0001\u0002@\u0005iB)\u0012$B+2#v\fU!S)&#\u0016j\u0014(`\u0007>cU+\u0014(`\u001d\u0006kU\tC\u0005\u0002*\u0006M\u0002\u0015!\u0003\u0002B\u0005qB)\u0012$B+2#v\fU!S)&#\u0016j\u0014(`\u0007>cU+\u0014(`\u001d\u0006kU\t\t\u0005\u000b\u0003[\u000b\u0019$!A\u0005\n\u0005=\u0016a\u0003:fC\u0012\u0014Vm]8mm\u0016$\"!!-\u0011\t\u0005\r\u00131W\u0005\u0005\u0003k\u000b)E\u0001\u0004PE*,7\r\u001e"
)
public class GreenplumOptions implements Serializable, Options, LazyLogging {
   private final CaseInsensitiveMap<String> parameters;
   private final String url;
   private final String user;
   private final Option<String> password;
   private final String dbSchema;
   private final String dbTable;
   private final String partitionColumn;
   private final Option<Object> partitions;
   private final String driver;
   private final ConnectorOptions connectorOptions;
   private final ConnectionPoolOptions connectionPoolOptions;
   private final boolean truncateTable;
   private final Option<String> distributedBy;
   private final boolean iteratorOptimization;
   private final transient Logger logger;
   private transient volatile boolean bitmap$trans$0;

   public static String DEFAULT_PARTITION_COLUMN_NAME() {
      return GreenplumOptions$.MODULE$.DEFAULT_PARTITION_COLUMN_NAME();
   }

   public static String GPDB_ITERATOR_OPTIMIZATION() {
      return GreenplumOptions$.MODULE$.GPDB_ITERATOR_OPTIMIZATION();
   }

   public static String GPDB_TRUNCATE_TABLE() {
      return GreenplumOptions$.MODULE$.GPDB_TRUNCATE_TABLE();
   }

   public static String GPDB_DISTRIBUTED_BY() {
      return GreenplumOptions$.MODULE$.GPDB_DISTRIBUTED_BY();
   }

   public static String GPDB_DRIVER() {
      return GreenplumOptions$.MODULE$.GPDB_DRIVER();
   }

   public static String GPDB_PARTITIONS() {
      return GreenplumOptions$.MODULE$.GPDB_PARTITIONS();
   }

   public static String GPDB_PARTITION_COLUMN() {
      return GreenplumOptions$.MODULE$.GPDB_PARTITION_COLUMN();
   }

   public static String GPDB_TABLE_NAME() {
      return GreenplumOptions$.MODULE$.GPDB_TABLE_NAME();
   }

   public static String GPDB_SCHEMA_NAME() {
      return GreenplumOptions$.MODULE$.GPDB_SCHEMA_NAME();
   }

   public static String GPDB_PASSWORD() {
      return GreenplumOptions$.MODULE$.GPDB_PASSWORD();
   }

   public static String GPDB_USER() {
      return GreenplumOptions$.MODULE$.GPDB_USER();
   }

   public static String GPDB_URL() {
      return GreenplumOptions$.MODULE$.GPDB_URL();
   }

   public static long timeoutInMillis() {
      return GreenplumOptions$.MODULE$.timeoutInMillis();
   }

   public static long MAX_TIMEOUT_MILLIS() {
      return GreenplumOptions$.MODULE$.MAX_TIMEOUT_MILLIS();
   }

   public static String networkInterface() {
      return GreenplumOptions$.MODULE$.networkInterface();
   }

   public static String address() {
      return GreenplumOptions$.MODULE$.address();
   }

   public static boolean useHostname() {
      return GreenplumOptions$.MODULE$.useHostname();
   }

   public static List<Object> port() {
      return GreenplumOptions$.MODULE$.port();
   }

   public static Map<String, String> env() {
      return GreenplumOptions$.MODULE$.env();
   }

   private Logger logger$lzycompute() {
      synchronized(this) {
         if (!this.bitmap$trans$0) {
            this.logger = LazyLogging$class.logger(this);
            this.bitmap$trans$0 = true;
         }

         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      return this.logger;
   }

   public Logger logger() {
      return this.bitmap$trans$0 ? this.logger : this.logger$lzycompute();
   }

   public String option(String optionName, WhatIfMissing whatIfMissing) {
      return Options$class.option(this, optionName, (WhatIfMissing)whatIfMissing);
   }

   public <T> T option(String optionName, WhatIfMissing whatIfMissing, Function2<String, String, Try<T>> convert) {
      return Options$class.option(this, optionName, whatIfMissing, convert);
   }

   public Option<String> option(String optionName) {
      return Options$class.option(this, optionName);
   }

   public <T> Option<T> option(String optionName, Function2<String, String, Try<T>> convert) {
      return Options$class.option(this, optionName, (Function2)convert);
   }

   public Function2<String, String, Try<Object>> bool() {
      return Options$class.bool(this);
   }

   public Function2<String, String, Try<Object>> naturalLong() {
      return Options$class.naturalLong(this);
   }

   public Function2<String, String, Try<Object>> int() {
      return Options$class.int(this);
   }

   public Function2<String, String, Try<Object>> positiveInt() {
      return Options$class.positiveInt(this);
   }

   public Function2<String, String, Try<Object>> nonNegativeInt() {
      return Options$class.nonNegativeInt(this);
   }

   public CaseInsensitiveMap<String> parameters() {
      return this.parameters;
   }

   public String url() {
      return this.url;
   }

   public String user() {
      return this.user;
   }

   public Option<String> password() {
      return this.password;
   }

   public String dbSchema() {
      return this.dbSchema;
   }

   public String dbTable() {
      return this.dbTable;
   }

   public String partitionColumn() {
      return this.partitionColumn;
   }

   public Option<Object> partitions() {
      return this.partitions;
   }

   public String driver() {
      return this.driver;
   }

   public ConnectorOptions connectorOptions() {
      return this.connectorOptions;
   }

   public ConnectionPoolOptions connectionPoolOptions() {
      return this.connectionPoolOptions;
   }

   public boolean truncateTable() {
      return this.truncateTable;
   }

   public Option<String> distributedBy() {
      return this.distributedBy;
   }

   public boolean iteratorOptimization() {
      return this.iteratorOptimization;
   }

   public GreenplumOptions(CaseInsensitiveMap<String> parameters) {
      this.parameters = parameters;
      Options$class.$init$(this);
      LazyLogging$class.$init$(this);
      this.url = this.option(GreenplumOptions$.MODULE$.GPDB_URL(), (WhatIfMissing)ErrorIfMissing$.MODULE$);
      this.user = this.option(GreenplumOptions$.MODULE$.GPDB_USER(), (WhatIfMissing)ErrorIfMissing$.MODULE$);
      this.password = this.option(GreenplumOptions$.MODULE$.GPDB_PASSWORD());
      this.dbSchema = this.option(GreenplumOptions$.MODULE$.GPDB_SCHEMA_NAME(), (WhatIfMissing)(new Default("public")));
      this.dbTable = this.option(GreenplumOptions$.MODULE$.GPDB_TABLE_NAME(), (WhatIfMissing)ErrorIfMissing$.MODULE$);
      this.partitionColumn = this.option(GreenplumOptions$.MODULE$.GPDB_PARTITION_COLUMN(), (WhatIfMissing)(new Default("gp_segment_id")));
      this.partitions = this.option(GreenplumOptions$.MODULE$.GPDB_PARTITIONS(), this.positiveInt());
      this.driver = this.option(GreenplumOptions$.MODULE$.GPDB_DRIVER(), (WhatIfMissing)(new Default("org.postgresql.Driver")));
      this.connectorOptions = new ConnectorOptions(parameters, ConnectorOptions$.MODULE$.$lessinit$greater$default$2());
      this.connectionPoolOptions = new ConnectionPoolOptions(parameters);
      this.truncateTable = BoxesRunTime.unboxToBoolean(this.option(GreenplumOptions$.MODULE$.GPDB_TRUNCATE_TABLE(), new Default("false"), this.bool()));
      this.distributedBy = this.option(GreenplumOptions$.MODULE$.GPDB_DISTRIBUTED_BY());
      this.iteratorOptimization = BoxesRunTime.unboxToBoolean(this.option(GreenplumOptions$.MODULE$.GPDB_ITERATOR_OPTIMIZATION(), new Default("true"), this.bool()));
   }

   public GreenplumOptions(Map<String, String> params) {
      this(.MODULE$.apply(params));
   }
}
