package io.pivotal.greenplum.spark.externaltable;

import com.typesafe.scalalogging.LazyLogging;
import com.typesafe.scalalogging.LazyLogging$class;
import com.typesafe.scalalogging.Logger;
import io.pivotal.greenplum.spark.ConnectorUtils$;
import io.pivotal.greenplum.spark.GpfdistLocation;
import io.pivotal.greenplum.spark.NetworkWrapper;
import io.pivotal.greenplum.spark.conf.GreenplumOptions;
import org.apache.spark.SparkEnv.;
import scala.MatchError;
import scala.Serializable;
import scala.collection.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.util.Failure;
import scala.util.Success;
import scala.util.Try;

@ScalaSignature(
   bytes = "\u0006\u000194A!\u0001\u0002\u0001\u001b\t\u0011rI]3f]BdW/\u001c#bi\u0006luN^3s\u0015\t\u0019A!A\u0007fqR,'O\\1mi\u0006\u0014G.\u001a\u0006\u0003\u000b\u0019\tQa\u001d9be.T!a\u0002\u0005\u0002\u0013\u001d\u0014X-\u001a8qYVl'BA\u0005\u000b\u0003\u001d\u0001\u0018N^8uC2T\u0011aC\u0001\u0003S>\u001c\u0001aE\u0002\u0001\u001dQ\u0001\"a\u0004\n\u000e\u0003AQ\u0011!E\u0001\u0006g\u000e\fG.Y\u0005\u0003'A\u0011a!\u00118z%\u00164\u0007CA\u000b\u001d\u001b\u00051\"BA\f\u0019\u00031\u00198-\u00197bY><w-\u001b8h\u0015\tI\"$\u0001\u0005usB,7/\u00194f\u0015\u0005Y\u0012aA2p[&\u0011QD\u0006\u0002\f\u0019\u0006T\u0018\u0010T8hO&tw\r\u0003\u0005 \u0001\t\u0005\t\u0015!\u0003!\u00035\t\u0007\u000f\u001d7jG\u0006$\u0018n\u001c8JIB\u0011\u0011\u0005\n\b\u0003\u001f\tJ!a\t\t\u0002\rA\u0013X\rZ3g\u0013\t)cE\u0001\u0004TiJLgn\u001a\u0006\u0003GAA\u0001\u0002\u000b\u0001\u0003\u0002\u0003\u0006I!K\u0001\u0011OJ,WM\u001c9mk6|\u0005\u000f^5p]N\u0004\"AK\u0017\u000e\u0003-R!\u0001\f\u0003\u0002\t\r|gNZ\u0005\u0003]-\u0012\u0001c\u0012:fK:\u0004H.^7PaRLwN\\:\t\u0011A\u0002!\u0011!Q\u0001\nE\nA\u0002^1cY\u0016l\u0015M\\1hKJ\u0004\"AM\u001a\u000e\u0003\tI!\u0001\u000e\u0002\u0003+\u001d\u0013X-\u001a8qYVlG+\u00192mK6\u000bg.Y4fe\"Aa\u0007\u0001B\u0001B\u0003%q'A\u0004tKJ4\u0018nY3\u0011\u0005IB\u0014BA\u001d\u0003\u000599\u0005O\u001a3jgR\u001cVM\u001d<jG\u0016D\u0001b\u000f\u0001\u0003\u0002\u0003\u0006I\u0001P\u0001\b]\u0016$xo\u001c:l!\tid(D\u0001\u0005\u0013\tyDA\u0001\bOKR<xN]6Xe\u0006\u0004\b/\u001a:\t\u000b\u0005\u0003A\u0011\u0001\"\u0002\rqJg.\u001b;?)\u0019\u0019E)\u0012$H\u0011B\u0011!\u0007\u0001\u0005\u0006?\u0001\u0003\r\u0001\t\u0005\u0006Q\u0001\u0003\r!\u000b\u0005\u0006a\u0001\u0003\r!\r\u0005\u0006m\u0001\u0003\ra\u000e\u0005\bw\u0001\u0003\n\u00111\u0001=\u0011\u0015Q\u0005\u0001\"\u0001L\u0003!iwN^3ECR\fGC\u0001'V!\ri\u0005KU\u0007\u0002\u001d*\u0011q\nE\u0001\u0005kRLG.\u0003\u0002R\u001d\n\u0019AK]=\u0011\u0005=\u0019\u0016B\u0001+\u0011\u0005\rIe\u000e\u001e\u0005\u0006-&\u0003\raV\u0001\u000ea\u0006\u0014H/\u001b;j_:$\u0015\r^1\u0011\u0005IB\u0016BA-\u0003\u00055\u0001\u0016M\u001d;ji&|g\u000eR1uC\u001e91LAA\u0001\u0012\u0003a\u0016AE$sK\u0016t\u0007\u000f\\;n\t\u0006$\u0018-T8wKJ\u0004\"AM/\u0007\u000f\u0005\u0011\u0011\u0011!E\u0001=N\u0011QL\u0004\u0005\u0006\u0003v#\t\u0001\u0019\u000b\u00029\"9!-XI\u0001\n\u0003\u0019\u0017a\u0007\u0013mKN\u001c\u0018N\\5uI\u001d\u0014X-\u0019;fe\u0012\"WMZ1vYR$S'F\u0001eU\taTmK\u0001g!\t9G.D\u0001i\u0015\tI'.A\u0005v]\u000eDWmY6fI*\u00111\u000eE\u0001\u000bC:tw\u000e^1uS>t\u0017BA7i\u0005E)hn\u00195fG.,GMV1sS\u0006t7-\u001a"
)
public class GreenplumDataMover implements LazyLogging {
   private final String applicationId;
   private final GreenplumOptions greenplumOptions;
   public final GreenplumTableManager io$pivotal$greenplum$spark$externaltable$GreenplumDataMover$$tableManager;
   private final GpfdistService service;
   private final NetworkWrapper network;
   private final transient Logger logger;
   private transient volatile boolean bitmap$trans$0;

   public static NetworkWrapper $lessinit$greater$default$5() {
      return GreenplumDataMover$.MODULE$.$lessinit$greater$default$5();
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

   public Try<Object> moveData(PartitionData partitionData) {
      String executorId = .MODULE$.get().executorId();
      long threadId = Thread.currentThread().getId();
      GreenplumQualifiedName.Table targetTable = GreenplumQualifiedName$.MODULE$.forTable(this.greenplumOptions.dbSchema(), this.greenplumOptions.dbTable());
      String externalTableName = GreenplumTableManager$.MODULE$.generateExternalTableName(this.applicationId, targetTable.name(), executorId, threadId, (Seq)scala.collection.Seq..MODULE$.empty());
      GreenplumQualifiedName.TempTable extTable = GreenplumQualifiedName$.MODULE$.forTempTable(externalTableName);
      String serverAddress = ConnectorUtils$.MODULE$.getServerAddress(this.greenplumOptions.connectorOptions(), this.network);
      int port = this.service.getPort();
      Try result = this.io$pivotal$greenplum$spark$externaltable$GreenplumDataMover$$tableManager.createReadableExternalTableIfNotExists(targetTable, extTable, new GpfdistLocation(serverAddress, port));
      Object var12;
      if (result instanceof Success) {
         Try rowsCopied = this.service.setPartitionDataFor(extTable.name(), partitionData).flatMap(new Serializable(this, targetTable, extTable) {
            public static final long serialVersionUID = 0L;
            // $FF: synthetic field
            private final GreenplumDataMover $outer;
            private final GreenplumQualifiedName.Table targetTable$1;
            private final GreenplumQualifiedName.TempTable extTable$1;

            public final Try<Object> apply(GpfdistService _) {
               return this.$outer.io$pivotal$greenplum$spark$externaltable$GreenplumDataMover$$tableManager.copyTableFromExternal(this.extTable$1, this.targetTable$1).map(new Serializable(this) {
                  public static final long serialVersionUID = 0L;

                  public final int apply(int rowsCopied) {
                     return this.apply$mcII$sp(rowsCopied);
                  }

                  public int apply$mcII$sp(int rowsCopied) {
                     return rowsCopied;
                  }
               });
            }

            public {
               if ($outer == null) {
                  throw null;
               } else {
                  this.$outer = $outer;
                  this.targetTable$1 = targetTable$1;
                  this.extTable$1 = extTable$1;
               }
            }
         });
         this.service.removePartitionDataFor(extTable.name());
         var12 = rowsCopied;
      } else {
         if (!(result instanceof Failure)) {
            throw new MatchError(result);
         }

         Failure var14 = (Failure)result;
         Throwable exception = var14.exception();
         var12 = new Failure(exception);
      }

      return (Try)var12;
   }

   public GreenplumDataMover(String applicationId, GreenplumOptions greenplumOptions, GreenplumTableManager tableManager, GpfdistService service, NetworkWrapper network) {
      this.applicationId = applicationId;
      this.greenplumOptions = greenplumOptions;
      this.io$pivotal$greenplum$spark$externaltable$GreenplumDataMover$$tableManager = tableManager;
      this.service = service;
      this.network = network;
      LazyLogging$class.$init$(this);
   }
}
