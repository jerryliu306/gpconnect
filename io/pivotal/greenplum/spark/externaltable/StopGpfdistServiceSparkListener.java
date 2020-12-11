package io.pivotal.greenplum.spark.externaltable;

import com.typesafe.scalalogging.LazyLogging;
import com.typesafe.scalalogging.LazyLogging$class;
import com.typesafe.scalalogging.Logger;
import org.apache.spark.scheduler.SparkListener;
import org.apache.spark.scheduler.SparkListenerApplicationEnd;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0001q2A!\u0001\u0002\u0001\u001b\ty2\u000b^8q\u000fB4G-[:u'\u0016\u0014h/[2f'B\f'o\u001b'jgR,g.\u001a:\u000b\u0005\r!\u0011!D3yi\u0016\u0014h.\u00197uC\ndWM\u0003\u0002\u0006\r\u0005)1\u000f]1sW*\u0011q\u0001C\u0001\nOJ,WM\u001c9mk6T!!\u0003\u0006\u0002\u000fALgo\u001c;bY*\t1\"\u0001\u0002j_\u000e\u00011c\u0001\u0001\u000f3A\u0011qbF\u0007\u0002!)\u0011\u0011CE\u0001\ng\u000eDW\rZ;mKJT!!B\n\u000b\u0005Q)\u0012AB1qC\u000eDWMC\u0001\u0017\u0003\ry'oZ\u0005\u00031A\u0011Qb\u00159be.d\u0015n\u001d;f]\u0016\u0014\bC\u0001\u000e\"\u001b\u0005Y\"B\u0001\u000f\u001e\u00031\u00198-\u00197bY><w-\u001b8h\u0015\tqr$\u0001\u0005usB,7/\u00194f\u0015\u0005\u0001\u0013aA2p[&\u0011!e\u0007\u0002\f\u0019\u0006T\u0018\u0010T8hO&tw\r\u0003\u0005%\u0001\t\u0005\t\u0015!\u0003&\u0003\rYW-\u001f\t\u0003M\u001dj\u0011AA\u0005\u0003Q\t\u0011!bU3sm&\u001cWmS3z\u0011\u0015Q\u0003\u0001\"\u0001,\u0003\u0019a\u0014N\\5u}Q\u0011A&\f\t\u0003M\u0001AQ\u0001J\u0015A\u0002\u0015BQa\f\u0001\u0005BA\n\u0001c\u001c8BaBd\u0017nY1uS>tWI\u001c3\u0015\u0005E:\u0004C\u0001\u001a6\u001b\u0005\u0019$\"\u0001\u001b\u0002\u000bM\u001c\u0017\r\\1\n\u0005Y\u001a$\u0001B+oSRDQ\u0001\u000f\u0018A\u0002e\na\"\u00199qY&\u001c\u0017\r^5p]\u0016sG\r\u0005\u0002\u0010u%\u00111\b\u0005\u0002\u001c'B\f'o\u001b'jgR,g.\u001a:BaBd\u0017nY1uS>tWI\u001c3"
)
public class StopGpfdistServiceSparkListener extends SparkListener implements LazyLogging {
   private final ServiceKey key;
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

   public void onApplicationEnd(SparkListenerApplicationEnd applicationEnd) {
      BoxedUnit var10000;
      if (this.logger().underlying().isInfoEnabled()) {
         this.logger().underlying().info("Stopping GpfdistService for {}", this.key);
         var10000 = BoxedUnit.UNIT;
      } else {
         var10000 = BoxedUnit.UNIT;
      }

      GpfdistServiceManager$.MODULE$.stopAndRemove(this.key);
   }

   public StopGpfdistServiceSparkListener(ServiceKey key) {
      this.key = key;
      LazyLogging$class.$init$(this);
   }
}
