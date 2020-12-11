package io.pivotal.greenplum.spark.conf;

import com.typesafe.scalalogging.LazyLogging;
import com.typesafe.scalalogging.LazyLogging$class;
import com.typesafe.scalalogging.Logger;
import io.pivotal.greenplum.spark.ConnectorUtils$;
import java.time.Duration;
import org.apache.commons.lang.StringUtils;
import scala.Function2;
import scala.Option;
import scala.Serializable;
import scala.StringContext;
import scala.Predef.;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.collection.mutable.StringBuilder;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.util.Try;

@ScalaSignature(
   bytes = "\u0006\u0001\u0005\u001dd\u0001B\u0001\u0003\u00015\u0011\u0001cQ8o]\u0016\u001cGo\u001c:PaRLwN\\:\u000b\u0005\r!\u0011\u0001B2p]\u001aT!!\u0002\u0004\u0002\u000bM\u0004\u0018M]6\u000b\u0005\u001dA\u0011!C4sK\u0016t\u0007\u000f\\;n\u0015\tI!\"A\u0004qSZ|G/\u00197\u000b\u0003-\t!![8\u0004\u0001M)\u0001A\u0004\u000b\u00187A\u0011qBE\u0007\u0002!)\t\u0011#A\u0003tG\u0006d\u0017-\u0003\u0002\u0014!\t1\u0011I\\=SK\u001a\u0004\"aD\u000b\n\u0005Y\u0001\"\u0001D*fe&\fG.\u001b>bE2,\u0007C\u0001\r\u001a\u001b\u0005\u0011\u0011B\u0001\u000e\u0003\u0005\u001dy\u0005\u000f^5p]N\u0004\"\u0001H\u0012\u000e\u0003uQ!AH\u0010\u0002\u0019M\u001c\u0017\r\\1m_\u001e<\u0017N\\4\u000b\u0005\u0001\n\u0013\u0001\u0003;za\u0016\u001c\u0018MZ3\u000b\u0003\t\n1aY8n\u0013\t!SDA\u0006MCjLHj\\4hS:<\u0007\u0002\u0003\u0014\u0001\u0005\u000b\u0007I\u0011A\u0014\u0002\u0015A\f'/Y7fi\u0016\u00148/F\u0001)!\u0011ICfL\u0018\u000f\u0005=Q\u0013BA\u0016\u0011\u0003\u0019\u0001&/\u001a3fM&\u0011QF\f\u0002\u0004\u001b\u0006\u0004(BA\u0016\u0011!\tI\u0003'\u0003\u00022]\t11\u000b\u001e:j]\u001eD\u0001b\r\u0001\u0003\u0002\u0003\u0006I\u0001K\u0001\fa\u0006\u0014\u0018-\\3uKJ\u001c\b\u0005\u0003\u00056\u0001\t\u0015\r\u0011\"\u0001(\u0003\r)gN\u001e\u0005\to\u0001\u0011\t\u0011)A\u0005Q\u0005!QM\u001c<!\u0011\u0015I\u0004\u0001\"\u0001;\u0003\u0019a\u0014N\\5u}Q\u00191\bP\u001f\u0011\u0005a\u0001\u0001\"\u0002\u00149\u0001\u0004A\u0003bB\u001b9!\u0003\u0005\r\u0001\u000b\u0005\u0006s\u0001!\ta\u0010\u000b\u0002w!9\u0011\t\u0001b\u0001\n\u0003\u0011\u0015\u0001\u00029peR,\u0012a\u0011\t\u0004\t2{eBA#K\u001d\t1\u0015*D\u0001H\u0015\tAE\"\u0001\u0004=e>|GOP\u0005\u0002#%\u00111\nE\u0001\ba\u0006\u001c7.Y4f\u0013\tieJ\u0001\u0003MSN$(BA&\u0011!\ty\u0001+\u0003\u0002R!\t\u0019\u0011J\u001c;\t\rM\u0003\u0001\u0015!\u0003D\u0003\u0015\u0001xN\u001d;!\u0011\u001d)\u0006A1A\u0005\u0002Y\u000b1\"^:f\u0011>\u001cHO\\1nKV\tq\u000b\u0005\u0002\u00101&\u0011\u0011\f\u0005\u0002\b\u0005>|G.Z1o\u0011\u0019Y\u0006\u0001)A\u0005/\u0006aQo]3I_N$h.Y7fA!9Q\f\u0001b\u0001\n\u0003q\u0016aB1eIJ,7o]\u000b\u0002_!1\u0001\r\u0001Q\u0001\n=\n\u0001\"\u00193ee\u0016\u001c8\u000f\t\u0005\bE\u0002\u0011\r\u0011\"\u0001_\u0003AqW\r^<pe.Le\u000e^3sM\u0006\u001cW\r\u0003\u0004e\u0001\u0001\u0006IaL\u0001\u0012]\u0016$xo\u001c:l\u0013:$XM\u001d4bG\u0016\u0004\u0003b\u00024\u0001\u0005\u0004%\taZ\u0001\u0013\u001b\u0006Cv\fV%N\u000b>+FkX'J\u00192K5+F\u0001i!\ty\u0011.\u0003\u0002k!\t!Aj\u001c8h\u0011\u0019a\u0007\u0001)A\u0005Q\u0006\u0019R*\u0011-`)&kUiT+U?6KE\nT%TA!9a\u000e\u0001b\u0001\n\u00039\u0017a\u0004;j[\u0016|W\u000f^%o\u001b&dG.[:\t\rA\u0004\u0001\u0015!\u0003i\u0003A!\u0018.\\3pkRLe.T5mY&\u001c\beB\u0003s\u0005!\u00051/\u0001\tD_:tWm\u0019;pe>\u0003H/[8ogB\u0011\u0001\u0004\u001e\u0004\u0006\u0003\tA\t!^\n\u0004i:!\u0002\"B\u001du\t\u00039H#A:\t\u000fe$(\u0019!C\u0001u\u0006\tr\t\u0015#C?:+EkV(S\u0017~\u0003vJ\u0015+\u0016\u0003m\u00042\u0001`A\u0002\u001b\u0005i(B\u0001@\u0000\u0003\u0011a\u0017M\\4\u000b\u0005\u0005\u0005\u0011\u0001\u00026bm\u0006L!!M?\t\u000f\u0005\u001dA\u000f)A\u0005w\u0006\u0011r\t\u0015#C?:+EkV(S\u0017~\u0003vJ\u0015+!\u0011!\tY\u0001\u001eb\u0001\n\u0003Q\u0018\u0001F$Q\t\n{f*\u0012+X\u001fJ[u,\u0011#E%\u0016\u001b6\u000bC\u0004\u0002\u0010Q\u0004\u000b\u0011B>\u0002+\u001d\u0003FIQ0O\u000bR;vJU&`\u0003\u0012#%+R*TA!A\u00111\u0003;C\u0002\u0013\u0005!0\u0001\fH!\u0012\u0013uLT#U/>\u00136jX%O)\u0016\u0013f)Q\"F\u0011\u001d\t9\u0002\u001eQ\u0001\nm\fqc\u0012)E\u0005~sU\tV,P%.{\u0016J\u0014+F%\u001a\u000b5)\u0012\u0011\t\u0011\u0005mAO1A\u0005\u0002i\fQc\u0012)E\u0005~sU\tV,P%.{\u0006jT*U\u001d\u0006kU\tC\u0004\u0002 Q\u0004\u000b\u0011B>\u0002-\u001d\u0003FIQ0O\u000bR;vJU&`\u0011>\u001bFKT!N\u000b\u0002B\u0001\"a\tu\u0005\u0004%\tA_\u0001\u0015\u000fB#%i\u0018(F)^{%kS0U\u00136+u*\u0016+\t\u000f\u0005\u001dB\u000f)A\u0005w\u0006)r\t\u0015#C?:+EkV(S\u0017~#\u0016*T#P+R\u0003\u0003\u0002CA\u0016i\n\u0007I\u0011\u0001>\u0002'\u001d\u0003FIQ0F\u001dZ{f+\u0011*`!J+e)\u0013-\t\u000f\u0005=B\u000f)A\u0005w\u0006!r\t\u0015#C?\u0016sek\u0018,B%~\u0003&+\u0012$J1\u0002B\u0001\"a\ru\u0005\u0004%\tA_\u0001\u001f\u000fB#%i\u0018#F\r\u0006+F\nV0O\u000bR;vJU&`\u0013:#VI\u0015$B\u0007\u0016Cq!a\u000euA\u0003%10A\u0010H!\u0012\u0013u\fR#G\u0003VcEk\u0018(F)^{%kS0J\u001dR+%KR!D\u000b\u0002B\u0001\"a\u000fu\u0005\u0004%\tA_\u0001\u0019\u000fB#%i\u0018#F\r\u0006+F\nV0T\u000bJ3VIU0I\u001fN#\u0006bBA i\u0002\u0006Ia_\u0001\u001a\u000fB#%i\u0018#F\r\u0006+F\nV0T\u000bJ3VIU0I\u001fN#\u0006\u0005C\u0005\u0002DQ\f\n\u0011\"\u0001\u0002F\u0005YB\u0005\\3tg&t\u0017\u000e\u001e\u0013he\u0016\fG/\u001a:%I\u00164\u0017-\u001e7uII*\"!a\u0012+\u0007!\nIe\u000b\u0002\u0002LA!\u0011QJA,\u001b\t\tyE\u0003\u0003\u0002R\u0005M\u0013!C;oG\",7m[3e\u0015\r\t)\u0006E\u0001\u000bC:tw\u000e^1uS>t\u0017\u0002BA-\u0003\u001f\u0012\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u0011%\ti\u0006^A\u0001\n\u0013\ty&A\u0006sK\u0006$'+Z:pYZ,GCAA1!\ra\u00181M\u0005\u0004\u0003Kj(AB(cU\u0016\u001cG\u000f"
)
public class ConnectorOptions implements Serializable, Options, LazyLogging {
   private final Map<String, String> parameters;
   private final Map<String, String> env;
   private final List<Object> port;
   private final boolean useHostname;
   private final String address;
   private final String networkInterface;
   private final long MAX_TIMEOUT_MILLIS;
   private final long timeoutInMillis;
   private final transient Logger logger;
   private transient volatile boolean bitmap$trans$0;

   public static Map<String, String> $lessinit$greater$default$2() {
      return ConnectorOptions$.MODULE$.$lessinit$greater$default$2();
   }

   public static String GPDB_DEFAULT_SERVER_HOST() {
      return ConnectorOptions$.MODULE$.GPDB_DEFAULT_SERVER_HOST();
   }

   public static String GPDB_DEFAULT_NETWORK_INTERFACE() {
      return ConnectorOptions$.MODULE$.GPDB_DEFAULT_NETWORK_INTERFACE();
   }

   public static String GPDB_ENV_VAR_PREFIX() {
      return ConnectorOptions$.MODULE$.GPDB_ENV_VAR_PREFIX();
   }

   public static String GPDB_NETWORK_TIMEOUT() {
      return ConnectorOptions$.MODULE$.GPDB_NETWORK_TIMEOUT();
   }

   public static String GPDB_NETWORK_HOSTNAME() {
      return ConnectorOptions$.MODULE$.GPDB_NETWORK_HOSTNAME();
   }

   public static String GPDB_NETWORK_INTERFACE() {
      return ConnectorOptions$.MODULE$.GPDB_NETWORK_INTERFACE();
   }

   public static String GPDB_NETWORK_ADDRESS() {
      return ConnectorOptions$.MODULE$.GPDB_NETWORK_ADDRESS();
   }

   public static String GPDB_NETWORK_PORT() {
      return ConnectorOptions$.MODULE$.GPDB_NETWORK_PORT();
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

   public Map<String, String> parameters() {
      return this.parameters;
   }

   public Map<String, String> env() {
      return this.env;
   }

   public List<Object> port() {
      return this.port;
   }

   public boolean useHostname() {
      return this.useHostname;
   }

   public String address() {
      return this.address;
   }

   public String networkInterface() {
      return this.networkInterface;
   }

   public long MAX_TIMEOUT_MILLIS() {
      return this.MAX_TIMEOUT_MILLIS;
   }

   public long timeoutInMillis() {
      return this.timeoutInMillis;
   }

   public ConnectorOptions(Map<String, String> parameters, Map<String, String> env) {
      this.parameters = parameters;
      this.env = env;
      Options$class.$init$(this);
      LazyLogging$class.$init$(this);
      String value = this.option(ConnectorOptions$.MODULE$.GPDB_NETWORK_PORT(), (WhatIfMissing)(new Default("0")));
      String portString = StringUtils.startsWith(value, ConnectorOptions$.MODULE$.GPDB_ENV_VAR_PREFIX()) ? (String)env.getOrElse(value.substring(ConnectorOptions$.MODULE$.GPDB_ENV_VAR_PREFIX().length()), new Serializable(this) {
         public static final long serialVersionUID = 0L;

         public final String apply() {
            return "0";
         }
      }) : value;
      this.port = ConnectorUtils$.MODULE$.parsePortString(portString);
      this.useHostname = BoxesRunTime.unboxToBoolean(this.option(ConnectorOptions$.MODULE$.GPDB_NETWORK_HOSTNAME(), new Default("false"), this.bool()));
      String value = (String)this.option(ConnectorOptions$.MODULE$.GPDB_NETWORK_ADDRESS()).orNull(.MODULE$.$conforms());
      String var10001;
      if (StringUtils.isNotBlank(value)) {
         String environmentVariableKey = value.startsWith(ConnectorOptions$.MODULE$.GPDB_ENV_VAR_PREFIX()) ? value.substring(ConnectorOptions$.MODULE$.GPDB_ENV_VAR_PREFIX().length()) : value;
         var10001 = (String)env.get(environmentVariableKey).orNull(.MODULE$.$conforms());
      } else {
         var10001 = null;
      }

      this.address = var10001;
      String value = (String)this.option(ConnectorOptions$.MODULE$.GPDB_NETWORK_INTERFACE()).orNull(.MODULE$.$conforms());
      this.networkInterface = StringUtils.startsWith(value, ConnectorOptions$.MODULE$.GPDB_ENV_VAR_PREFIX()) ? (String)env.get(value.substring(ConnectorOptions$.MODULE$.GPDB_ENV_VAR_PREFIX().length())).orNull(.MODULE$.$conforms()) : value;
      this.MAX_TIMEOUT_MILLIS = Duration.ofHours(2L).toMillis();
      this.timeoutInMillis = BoxesRunTime.unboxToLong(this.option(ConnectorOptions$.MODULE$.GPDB_NETWORK_TIMEOUT(), this.naturalLong()).getOrElse(new Serializable(this) {
         public static final long serialVersionUID = 0L;

         public final long apply() {
            return this.apply$mcJ$sp();
         }

         public long apply$mcJ$sp() {
            return Duration.ofMinutes(5L).toMillis();
         }
      }));
      if (this.timeoutInMillis() > this.MAX_TIMEOUT_MILLIS()) {
         throw new IllegalArgumentException((new StringBuilder()).append((new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{"requirement failed: Option "})))).s(scala.collection.immutable.Nil..MODULE$)).append((new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{"'", "' has a value of '", "' that is "})))).s(.MODULE$.genericWrapArray(new Object[]{ConnectorOptions$.MODULE$.GPDB_NETWORK_TIMEOUT(), BoxesRunTime.boxToLong(this.timeoutInMillis())}))).append((new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{"greater than the maximum allowed value of ", " milliseconds."})))).s(.MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToLong(this.MAX_TIMEOUT_MILLIS())}))).toString());
      }
   }

   public ConnectorOptions() {
      this(.MODULE$.Map().empty(), ConnectorOptions$.MODULE$.$lessinit$greater$default$2());
   }
}
