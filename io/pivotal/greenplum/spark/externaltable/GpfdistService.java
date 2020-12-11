package io.pivotal.greenplum.spark.externaltable;

import com.typesafe.scalalogging.LazyLogging;
import com.typesafe.scalalogging.LazyLogging$class;
import com.typesafe.scalalogging.Logger;
import io.pivotal.greenplum.spark.conf.ConnectorOptions$;
import io.pivotal.greenplum.spark.util.TransactionData;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.BindException;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.ServerConnector;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.Enumeration.Value;
import scala.Predef.;
import scala.collection.Iterator;
import scala.collection.immutable.List;
import scala.collection.immutable.StringOps;
import scala.collection.mutable.StringBuilder;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.util.Failure;
import scala.util.Success;
import scala.util.Try;

@ScalaSignature(
   bytes = "\u0006\u0001\u0005\rd\u0001B\u0001\u0003\u00015\u0011ab\u00129gI&\u001cHoU3sm&\u001cWM\u0003\u0002\u0004\t\u0005iQ\r\u001f;fe:\fG\u000e^1cY\u0016T!!\u0002\u0004\u0002\u000bM\u0004\u0018M]6\u000b\u0005\u001dA\u0011!C4sK\u0016t\u0007\u000f\\;n\u0015\tI!\"A\u0004qSZ|G/\u00197\u000b\u0003-\t!![8\u0004\u0001M\u0019\u0001A\u0004\u000b\u0011\u0005=\u0011R\"\u0001\t\u000b\u0003E\tQa]2bY\u0006L!a\u0005\t\u0003\r\u0005s\u0017PU3g!\t)B$D\u0001\u0017\u0015\t9\u0002$\u0001\u0007tG\u0006d\u0017\r\\8hO&twM\u0003\u0002\u001a5\u0005AA/\u001f9fg\u00064WMC\u0001\u001c\u0003\r\u0019w.\\\u0005\u0003;Y\u00111\u0002T1{s2{wmZ5oO\"Aq\u0004\u0001B\u0001B\u0003%\u0001%A\ntKJ4XM]\"p]:,7\r^8s\u0011>\u001cH\u000f\u0005\u0002\"I9\u0011qBI\u0005\u0003GA\ta\u0001\u0015:fI\u00164\u0017BA\u0013'\u0005\u0019\u0019FO]5oO*\u00111\u0005\u0005\u0005\tQ\u0001\u0011\t\u0011)A\u0005S\u0005q\u0011M^1jY\u0006\u0014G.\u001a)peR\u001c\bc\u0001\u00163k9\u00111\u0006\r\b\u0003Y=j\u0011!\f\u0006\u0003]1\ta\u0001\u0010:p_Rt\u0014\"A\t\n\u0005E\u0002\u0012a\u00029bG.\fw-Z\u0005\u0003gQ\u0012A\u0001T5ti*\u0011\u0011\u0007\u0005\t\u0003\u001fYJ!a\u000e\t\u0003\u0007%sG\u000f\u0003\u0005:\u0001\t\u0005\t\u0015!\u0003;\u0003%\u0011WO\u001a4fe6\u000b\u0007\u000f\u0005\u0003<\u0001\u0002\u0012U\"\u0001\u001f\u000b\u0005ur\u0014\u0001B;uS2T\u0011aP\u0001\u0005U\u00064\u0018-\u0003\u0002By\t\u0019Q*\u00199\u0011\u0007\r+u)D\u0001E\u0015\ti\u0004#\u0003\u0002G\t\n\u0019AK]=\u0011\u0005!SU\"A%\u000b\u0005u\"\u0011BA&J\u0005=!&/\u00198tC\u000e$\u0018n\u001c8ECR\f\u0007\u0002C'\u0001\u0005\u0003\u0005\u000b\u0011\u0002(\u0002\u001bM,g\u000e\u001a\"vM\u001a,'/T1q!\u0011Y\u0004\tI(\u0011\u0005A\u000bV\"\u0001\u0002\n\u0005I\u0013!!\u0004)beRLG/[8o\t\u0006$\u0018\r\u0003\u0005U\u0001\t\u0015\r\u0011\"\u0003V\u0003\u0019\u0019XM\u001d<feV\ta\u000b\u0005\u0002Q/&\u0011\u0001L\u0001\u0002\u000e'\u0016\u0014h/\u001a:Xe\u0006\u0004\b/\u001a:\t\u0011i\u0003!\u0011!Q\u0001\nY\u000bqa]3sm\u0016\u0014\b\u0005\u0003\u0005]\u0001\t\u0005\t\u0015!\u0003^\u0003=!\u0018.\\3pkRLe.T5mY&\u001c\bCA\b_\u0013\ty\u0006C\u0001\u0003M_:<\u0007\"B1\u0001\t\u0003\u0011\u0017A\u0002\u001fj]&$h\bF\u0004dI\u00164w\r[5\u0011\u0005A\u0003\u0001\"B\u0010a\u0001\u0004\u0001\u0003\"\u0002\u0015a\u0001\u0004I\u0003\"B\u001da\u0001\u0004Q\u0004\"B'a\u0001\u0004q\u0005\"\u0002+a\u0001\u00041\u0006\"\u0002/a\u0001\u0004i\u0006\"B1\u0001\t\u0003YGCB2m[:|\u0007\u000fC\u0003)U\u0002\u0007\u0011\u0006C\u0003:U\u0002\u0007!\bC\u0003NU\u0002\u0007a\nC\u0003UU\u0002\u0007a\u000bC\u0003]U\u0002\u0007Q\fC\u0004s\u0001\t\u0007I\u0011B:\u0002\u000fM$\u0018M\u001d;fIV\tA\u000f\u0005\u0002vu6\taO\u0003\u0002xq\u00061\u0011\r^8nS\u000eT!!\u001f\u001f\u0002\u0015\r|gnY;se\u0016tG/\u0003\u0002|m\ni\u0011\t^8nS\u000e\u0014un\u001c7fC:Da! \u0001!\u0002\u0013!\u0018\u0001C:uCJ$X\r\u001a\u0011\t\r}\u0004A\u0011AA\u0001\u0003\u0015\u0019H/\u0019:u)\t\t\u0019\u0001E\u0002\u0010\u0003\u000bI1!a\u0002\u0011\u0005\u0011)f.\u001b;\t\u000f\u0005-\u0001\u0001\"\u0001\u0002\u0002\u0005!1\u000f^8q\u0011\u001d\ty\u0001\u0001C\u0001\u0003#\tqaZ3u!>\u0014H\u000fF\u00016\u0011\u001d\t)\u0002\u0001C\u0001\u0003/\tQa\u001d;bi\u0016,\"!!\u0007\u0011\t\u0005m\u0011\u0011\u0005\b\u0004!\u0006u\u0011bAA\u0010\u0005\u0005\u0019r\t\u001d4eSN$8+\u001a:wS\u000e,7\u000b^1uK&!\u00111EA\u0013\u0005\u00151\u0016\r\\;f\u0013\r\t9\u0003\u0005\u0002\f\u000b:,X.\u001a:bi&|g\u000eC\u0004\u0002,\u0001!\t!!\f\u0002%\u001d,GOU3dK&4X\r\u001a#bi\u00064uN\u001d\u000b\u0005\u0003_\tY\u0004\u0005\u0003D\u000b\u0006E\u0002\u0003BA\u001a\u0003oi!!!\u000e\u000b\u0005-q\u0014\u0002BA\u001d\u0003k\u00111\"\u00138qkR\u001cFO]3b[\"9\u0011QHA\u0015\u0001\u0004\u0001\u0013!\u0004;sC:\u001c\u0018m\u0019;j_:LE\rC\u0004\u0002B\u0001!\t!a\u0011\u0002\u0015\u001d,G\u000fV5nK>,H\u000fF\u0001^\u0011\u001d\t9\u0005\u0001C\u0001\u0003\u0013\n1c]3u!\u0006\u0014H/\u001b;j_:$\u0015\r^1G_J$b!a\u0013\u0002N\u0005E\u0003cA\"FG\"9\u0011qJA#\u0001\u0004\u0001\u0013aA6fs\"9\u00111KA#\u0001\u0004y\u0015!\u00049beRLG/[8o\t\u0006$\u0018\rC\u0004\u0002X\u0001!\t!!\u0017\u0002-I,Wn\u001c<f!\u0006\u0014H/\u001b;j_:$\u0015\r^1G_J$B!a\u0017\u0002bA!q\"!\u0018P\u0013\r\ty\u0006\u0005\u0002\u0007\u001fB$\u0018n\u001c8\t\u000f\u0005=\u0013Q\u000ba\u0001A\u0001"
)
public class GpfdistService implements LazyLogging {
   private final String serverConnectorHost;
   private final List<Object> availablePorts;
   private final Map<String, Try<TransactionData>> bufferMap;
   private final Map<String, PartitionData> sendBufferMap;
   private final ServerWrapper server;
   private final long timeoutInMillis;
   private final AtomicBoolean started;
   private final transient Logger logger;
   private transient volatile boolean bitmap$trans$0;

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

   private ServerWrapper server() {
      return this.server;
   }

   private AtomicBoolean started() {
      return this.started;
   }

   public synchronized void start() {
      Iterator portsIterator = this.availablePorts.iterator();
      if (!this.started().get()) {
         while(true) {
            if (!portsIterator.hasNext() || this.started().get()) {
               if (!this.started().get()) {
                  throw new RuntimeException((new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{"Unable to start GpfdistService on any of ports=", ""})))).s(.MODULE$.genericWrapArray(new Object[]{this.availablePorts.mkString(", ")})));
               }
               break;
            }

            int currentPort = BoxesRunTime.unboxToInt(portsIterator.next());

            BoxedUnit var10000;
            try {
               ServerConnector connector = new ServerConnector(this.server().server());
               connector.setHost(this.serverConnectorHost);
               connector.setPort(currentPort);
               connector.setIdleTimeout(this.timeoutInMillis);
               this.server().setConnectors((Connector[])((Object[])(new Connector[]{connector})));
               this.server().start();
               this.started().set(true);
               if (this.logger().underlying().isInfoEnabled()) {
                  this.logger().underlying().info((new StringBuilder()).append((new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{"Successfully started Gpfdist service on ", ":"})))).s(.MODULE$.genericWrapArray(new Object[]{this.serverConnectorHost}))).append((new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).s(.MODULE$.genericWrapArray(new Object[]{currentPort != 0 ? BoxesRunTime.boxToInteger(currentPort) : BoxesRunTime.boxToInteger(this.getPort())}))).toString());
                  var10000 = BoxedUnit.UNIT;
               } else {
                  var10000 = BoxedUnit.UNIT;
               }
            } catch (BindException var5) {
               if (this.logger().underlying().isWarnEnabled()) {
                  this.logger().underlying().warn("Unable to bind port {}", BoxesRunTime.boxToInteger(currentPort));
                  var10000 = BoxedUnit.UNIT;
               } else {
                  var10000 = BoxedUnit.UNIT;
               }
            } catch (IOException var6) {
               if (this.logger().underlying().isWarnEnabled()) {
                  this.logger().underlying().warn("Error when starting GpfdistService: {}", var6.getMessage());
                  var10000 = BoxedUnit.UNIT;
               } else {
                  var10000 = BoxedUnit.UNIT;
               }
            }
         }
      }

   }

   public synchronized void stop() {
      if (this.started().compareAndSet(true, false)) {
         this.server().stop();
      }

   }

   public int getPort() {
      Value var10000 = this.state();
      Value var1 = GpfdistServiceState$.MODULE$.Started();
      if (var10000 == null) {
         if (var1 == null) {
            return ((ServerConnector).MODULE$.refArrayOps((Object[])this.server().getConnectors()).head()).getLocalPort();
         }
      } else if (var10000.equals(var1)) {
         return ((ServerConnector).MODULE$.refArrayOps((Object[])this.server().getConnectors()).head()).getLocalPort();
      }

      throw new RuntimeException((new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{"There is no server started"})))).s(scala.collection.immutable.Nil..MODULE$));
   }

   public Value state() {
      return GpfdistServiceState$.MODULE$.withName((new StringOps(.MODULE$.augmentString(this.server().getState().toLowerCase()))).capitalize());
   }

   public Try<InputStream> getReceivedDataFor(String transactionId) {
      Try var2 = (Try)this.bufferMap.remove(transactionId);
      Object var3;
      if (var2 == null) {
         var3 = new Success(new ByteArrayInputStream(new byte[0]));
      } else if (var2 instanceof Success) {
         Success var4 = (Success)var2;
         TransactionData transactionData = (TransactionData)var4.value();
         var3 = new Success(transactionData.getInputStream());
      } else {
         if (!(var2 instanceof Failure)) {
            throw new MatchError(var2);
         }

         Failure var6 = (Failure)var2;
         Throwable exception = var6.exception();
         var3 = new Failure(exception);
      }

      return (Try)var3;
   }

   public long getTimeout() {
      return this.timeoutInMillis;
   }

   public Try<GpfdistService> setPartitionDataFor(String key, PartitionData partitionData) {
      Object var10000;
      if (this.sendBufferMap.containsKey(key)) {
         String msg = (new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{"Send buffer already exists for the given path = ", ""})))).s(.MODULE$.genericWrapArray(new Object[]{key}));
         var10000 = new Failure(new IllegalStateException(msg));
      } else {
         this.sendBufferMap.put(key, partitionData);
         var10000 = new Success(this);
      }

      return (Try)var10000;
   }

   public Option<PartitionData> removePartitionDataFor(String key) {
      Option partitionInfo = scala.Option..MODULE$.apply(this.sendBufferMap.remove(key));
      BoxedUnit var10000;
      if (partitionInfo instanceof Some) {
         Some var4 = (Some)partitionInfo;
         PartitionData p = (PartitionData)var4.x();
         if (p.handled().get()) {
            var10000 = BoxedUnit.UNIT;
         } else if (this.logger().underlying().isWarnEnabled()) {
            this.logger().underlying().warn((new StringOps(.MODULE$.augmentString((new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{"Data has not been marked as processed for path ", "\n               | and partition ", ""})))).s(.MODULE$.genericWrapArray(new Object[]{key, BoxesRunTime.boxToInteger(p.partitionIndex())}))))).stripMargin());
            var10000 = BoxedUnit.UNIT;
         } else {
            var10000 = BoxedUnit.UNIT;
         }
      } else {
         if (!scala.None..MODULE$.equals(partitionInfo)) {
            throw new MatchError(partitionInfo);
         }

         if (this.logger().underlying().isWarnEnabled()) {
            this.logger().underlying().warn("No partition exists for path {}", key);
            var10000 = BoxedUnit.UNIT;
         } else {
            var10000 = BoxedUnit.UNIT;
         }
      }

      return partitionInfo;
   }

   public GpfdistService(String serverConnectorHost, List<Object> availablePorts, Map<String, Try<TransactionData>> bufferMap, Map<String, PartitionData> sendBufferMap, ServerWrapper server, long timeoutInMillis) {
      this.serverConnectorHost = serverConnectorHost;
      this.availablePorts = availablePorts;
      this.bufferMap = bufferMap;
      this.sendBufferMap = sendBufferMap;
      this.server = server;
      this.timeoutInMillis = timeoutInMillis;
      LazyLogging$class.$init$(this);
      if (server == null) {
         throw new NullPointerException("server is null");
      } else {
         this.started = new AtomicBoolean(false);
      }
   }

   public GpfdistService(List<Object> availablePorts, Map<String, Try<TransactionData>> bufferMap, Map<String, PartitionData> sendBufferMap, ServerWrapper server, long timeoutInMillis) {
      this(ConnectorOptions$.MODULE$.GPDB_DEFAULT_SERVER_HOST(), availablePorts, bufferMap, sendBufferMap, server, timeoutInMillis);
   }
}
