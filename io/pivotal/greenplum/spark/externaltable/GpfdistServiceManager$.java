package io.pivotal.greenplum.spark.externaltable;

import com.typesafe.scalalogging.LazyLogging;
import com.typesafe.scalalogging.LazyLogging$class;
import com.typesafe.scalalogging.Logger;
import io.pivotal.greenplum.spark.NetworkWrapper$;
import io.pivotal.greenplum.spark.conf.ConnectorOptions;
import io.pivotal.greenplum.spark.conf.ConnectorOptions$;
import io.pivotal.greenplum.spark.util.TransactionData;
import java.net.InetAddress;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.commons.lang.StringUtils;
import org.apache.spark.SparkContext;
import org.eclipse.jetty.server.Server;
import scala.Serializable;
import scala.StringContext;
import scala.collection.IterableLike;
import scala.collection.JavaConverters.;
import scala.collection.immutable.List;
import scala.collection.mutable.StringBuilder;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.util.Try;

public final class GpfdistServiceManager$ implements LazyLogging {
   public static final GpfdistServiceManager$ MODULE$;
   private final ConcurrentHashMap<String, Try<TransactionData>> bufferMap;
   private final ConcurrentHashMap<String, PartitionData> sendBufferMap;
   private final ConcurrentHashMap<ServiceKey, GpfdistService> servicesMap;
   private final transient Logger logger;
   private transient volatile boolean bitmap$trans$0;

   static {
      new GpfdistServiceManager$();
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

   private ConcurrentHashMap<String, Try<TransactionData>> bufferMap() {
      return this.bufferMap;
   }

   private ConcurrentHashMap<String, PartitionData> sendBufferMap() {
      return this.sendBufferMap;
   }

   private ConcurrentHashMap<ServiceKey, GpfdistService> servicesMap() {
      return this.servicesMap;
   }

   public GpfdistService getService(ConnectorOptions connectorOptions) {
      List ports = connectorOptions.port();
      ServiceKey key = new ServiceKey(ports);
      BoxedUnit var10000;
      if (this.logger().underlying().isTraceEnabled()) {
         ((IterableLike).MODULE$.asScalaSetConverter(this.servicesMap().entrySet()).asScala()).foreach(new Serializable() {
            public static final long serialVersionUID = 0L;

            public final void apply(Entry<ServiceKey, GpfdistService> e) {
               BoxedUnit var10000;
               if (GpfdistServiceManager$.MODULE$.logger().underlying().isTraceEnabled()) {
                  GpfdistServiceManager$.MODULE$.logger().underlying().trace("{}: [key: {}; value: {}]", BoxesRunTime.boxToInteger(GpfdistServiceManager$.MODULE$.hashCode()), e.getKey(), e.getValue());
                  var10000 = BoxedUnit.UNIT;
               } else {
                  var10000 = BoxedUnit.UNIT;
               }

            }
         });
         var10000 = BoxedUnit.UNIT;
      } else {
         var10000 = BoxedUnit.UNIT;
      }

      if (this.servicesMap().containsKey(key)) {
         var10000 = BoxedUnit.UNIT;
      } else {
         synchronized(this.servicesMap()) {
            if (this.servicesMap().containsKey(key)) {
               var10000 = BoxedUnit.UNIT;
            } else {
               GpfdistHandler handler = new GpfdistHandler(this.bufferMap(), this.sendBufferMap());
               Server server = new Server();
               server.setHandler(handler);
               String serverHost = this.getServerHost(connectorOptions);
               GpfdistService gpfdistService = new GpfdistService(serverHost, ports, this.bufferMap(), this.sendBufferMap(), new ServerWrapper(server), connectorOptions.timeoutInMillis());
               if (this.logger().underlying().isDebugEnabled()) {
                  this.logger().underlying().debug("Service for {} is being started....", ports);
                  var10000 = BoxedUnit.UNIT;
               } else {
                  var10000 = BoxedUnit.UNIT;
               }

               label42: {
                  gpfdistService.start();
                  String var16 = org.apache.spark.SparkEnv..MODULE$.get().executorId();
                  String var10 = "driver";
                  if (var16 == null) {
                     if (var10 != null) {
                        break label42;
                     }
                  } else if (!var16.equals(var10)) {
                     break label42;
                  }

                  SparkContext sc = org.apache.spark.sql.SparkSession..MODULE$.builder().getOrCreate().sparkContext();
                  sc.addSparkListener(new StopGpfdistServiceSparkListener(key));
               }

               this.servicesMap().put(key, gpfdistService);
            }
         }
      }

      GpfdistService service = (GpfdistService)this.servicesMap().get(key);
      long serviceTimeout = service.getTimeout();
      if (serviceTimeout < connectorOptions.timeoutInMillis()) {
         if (this.logger().underlying().isWarnEnabled()) {
            this.logger().underlying().warn((new StringBuilder()).append((new StringContext(scala.Predef..MODULE$.wrapRefArray((Object[])(new String[]{"Unable to change the GpfdistService timeout after the service "})))).s(scala.collection.immutable.Nil..MODULE$)).append((new StringContext(scala.Predef..MODULE$.wrapRefArray((Object[])(new String[]{"has started. GpfdistService started with timeout ", " but "})))).s(scala.Predef..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToLong(serviceTimeout)}))).append((new StringContext(scala.Predef..MODULE$.wrapRefArray((Object[])(new String[]{"a higher timeout (", ") is configured. "})))).s(scala.Predef..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToLong(connectorOptions.timeoutInMillis())}))).append((new StringContext(scala.Predef..MODULE$.wrapRefArray((Object[])(new String[]{"Ensure that the desired GpfdistService timeout is set for the first "})))).s(scala.collection.immutable.Nil..MODULE$)).append((new StringContext(scala.Predef..MODULE$.wrapRefArray((Object[])(new String[]{"operation that accesses Greenplum."})))).s(scala.collection.immutable.Nil..MODULE$)).toString());
            var10000 = BoxedUnit.UNIT;
         } else {
            var10000 = BoxedUnit.UNIT;
         }
      } else {
         var10000 = BoxedUnit.UNIT;
      }

      return service;
   }

   public void stopAndRemove(ServiceKey key) {
      GpfdistService service = (GpfdistService)this.servicesMap().remove(key);
      if (service == null) {
         BoxedUnit var10000;
         if (this.logger().underlying().isWarnEnabled()) {
            this.logger().underlying().warn("Unable to find service for {}", key);
            var10000 = BoxedUnit.UNIT;
         } else {
            var10000 = BoxedUnit.UNIT;
         }
      } else {
         service.stop();
      }

   }

   private String getServerHost(ConnectorOptions connectorOptions) {
      if (StringUtils.isNotBlank(connectorOptions.networkInterface())) {
         InetAddress address = NetworkWrapper$.MODULE$.getInet4AddressByNetworkInterfaceName(connectorOptions.networkInterface());
         if (address != null) {
            return address.getHostAddress();
         }
      }

      return ConnectorOptions$.MODULE$.GPDB_DEFAULT_SERVER_HOST();
   }

   private GpfdistServiceManager$() {
      MODULE$ = this;
      LazyLogging$class.$init$(this);
      this.bufferMap = new ConcurrentHashMap();
      this.sendBufferMap = new ConcurrentHashMap();
      this.servicesMap = new ConcurrentHashMap();
   }
}
