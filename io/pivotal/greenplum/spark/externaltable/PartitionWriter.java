package io.pivotal.greenplum.spark.externaltable;

import com.typesafe.scalalogging.LazyLogging;
import com.typesafe.scalalogging.LazyLogging$class;
import com.typesafe.scalalogging.Logger;
import io.pivotal.greenplum.spark.SqlExecutor;
import io.pivotal.greenplum.spark.conf.GreenplumOptions;
import io.pivotal.greenplum.spark.jdbc.ConnectionManager$;
import java.sql.Connection;
import org.apache.spark.sql.Row;
import resource.ExtractableManagedResource;
import resource.Resource$;
import resource.package$;
import scala.Function1;
import scala.Function2;
import scala.Serializable;
import scala.collection.Iterator;
import scala.collection.immutable.List;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassManifestFactory.;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0001i4A!\u0001\u0002\u0001\u001b\ty\u0001+\u0019:uSRLwN\\,sSR,'O\u0003\u0002\u0004\t\u0005iQ\r\u001f;fe:\fG\u000e^1cY\u0016T!!\u0002\u0004\u0002\u000bM\u0004\u0018M]6\u000b\u0005\u001dA\u0011!C4sK\u0016t\u0007\u000f\\;n\u0015\tI!\"A\u0004qSZ|G/\u00197\u000b\u0003-\t!![8\u0004\u0001M!\u0001A\u0004\u000b\u0018!\ty!#D\u0001\u0011\u0015\u0005\t\u0012!B:dC2\f\u0017BA\n\u0011\u0005\u0019\te.\u001f*fMB\u0011q\"F\u0005\u0003-A\u0011AbU3sS\u0006d\u0017N_1cY\u0016\u0004\"\u0001G\u0010\u000e\u0003eQ!AG\u000e\u0002\u0019M\u001c\u0017\r\\1m_\u001e<\u0017N\\4\u000b\u0005qi\u0012\u0001\u0003;za\u0016\u001c\u0018MZ3\u000b\u0003y\t1aY8n\u0013\t\u0001\u0013DA\u0006MCjLHj\\4hS:<\u0007\u0002\u0003\u0012\u0001\u0005\u0003\u0005\u000b\u0011B\u0012\u0002\u001b\u0005\u0004\b\u000f\\5dCRLwN\\%e!\t!sE\u0004\u0002\u0010K%\u0011a\u0005E\u0001\u0007!J,G-\u001a4\n\u0005!J#AB*ue&twM\u0003\u0002'!!A1\u0006\u0001B\u0001B\u0003%A&\u0001\the\u0016,g\u000e\u001d7v[>\u0003H/[8ogB\u0011Q\u0006M\u0007\u0002])\u0011q\u0006B\u0001\u0005G>tg-\u0003\u00022]\t\u0001rI]3f]BdW/\\(qi&|gn\u001d\u0005\tg\u0001\u0011\t\u0011)A\u0005i\u0005q!o\\<Ue\u0006t7OZ8s[\u0016\u0014\b\u0003B\b6o]J!A\u000e\t\u0003\u0013\u0019+hn\u0019;j_:\f\u0004C\u0001\u001dA\u001b\u0005I$B\u0001\u001e<\u0003\r\u0019\u0018\u000f\u001c\u0006\u0003\u000bqR!!\u0010 \u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005y\u0014aA8sO&\u0011\u0011)\u000f\u0002\u0004%><\b\"B\"\u0001\t\u0003!\u0015A\u0002\u001fj]&$h\b\u0006\u0003F\u000f\"K\u0005C\u0001$\u0001\u001b\u0005\u0011\u0001\"\u0002\u0012C\u0001\u0004\u0019\u0003\"B\u0016C\u0001\u0004a\u0003\"B\u001aC\u0001\u0004!\u0004\u0002C&\u0001\u0011\u000b\u0007I\u0011\u0001'\u0002\u001d\u001d\u0004h\rZ5tiN+'O^5dKV\tQ\n\u0005\u0002G\u001d&\u0011qJ\u0001\u0002\u000f\u000fB4G-[:u'\u0016\u0014h/[2f\u0011!\t\u0006\u0001#A!B\u0013i\u0015aD4qM\u0012L7\u000f^*feZL7-\u001a\u0011\t\u000bM\u0003A\u0011\u0001+\u0002\u0015\u001d,Go\u00117pgV\u0014X-F\u0001V!\u0015ya\u000bW.h\u0013\t9\u0006CA\u0005Gk:\u001cG/[8oeA\u0011q\"W\u0005\u00035B\u00111!\u00138u!\raFm\u000e\b\u0003;\nt!AX1\u000e\u0003}S!\u0001\u0019\u0007\u0002\rq\u0012xn\u001c;?\u0013\u0005\t\u0012BA2\u0011\u0003\u001d\u0001\u0018mY6bO\u0016L!!\u001a4\u0003\u0011%#XM]1u_JT!a\u0019\t\u0011\u0007q#\u0007\fC\u0003j\u0001\u0011\u0005!.\u0001\u0007hKR$\u0015\r^1N_Z,'\u000f\u0006\u0002l]B\u0011a\t\\\u0005\u0003[\n\u0011!c\u0012:fK:\u0004H.^7ECR\fWj\u001c<fe\")q\u000e\u001ba\u0001a\u0006!1m\u001c8o!\t\tX/D\u0001s\u0015\tQ4OC\u0001u\u0003\u0011Q\u0017M^1\n\u0005Y\u0014(AC\"p]:,7\r^5p]\")\u0001\u0010\u0001C\u0001s\u0006iq-\u001a;D_:tWm\u0019;j_:$\u0012\u0001\u001d"
)
public class PartitionWriter implements Serializable, LazyLogging {
   private final String applicationId;
   public final GreenplumOptions io$pivotal$greenplum$spark$externaltable$PartitionWriter$$greenplumOptions;
   public final Function1<Row, Row> io$pivotal$greenplum$spark$externaltable$PartitionWriter$$rowTransformer;
   private GpfdistService gpfdistService;
   private final transient Logger logger;
   private volatile boolean bitmap$0;
   private transient volatile boolean bitmap$trans$0;

   private GpfdistService gpfdistService$lzycompute() {
      synchronized(this) {
         if (!this.bitmap$0) {
            this.gpfdistService = GpfdistServiceManager$.MODULE$.getService(this.io$pivotal$greenplum$spark$externaltable$PartitionWriter$$greenplumOptions.connectorOptions());
            this.bitmap$0 = true;
         }

         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      return this.gpfdistService;
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

   public GpfdistService gpfdistService() {
      return this.bitmap$0 ? this.gpfdistService : this.gpfdistService$lzycompute();
   }

   public Function2<Object, Iterator<Row>, Iterator<Object>> getClosure() {
      return new Serializable(this) {
         public static final long serialVersionUID = 0L;
         // $FF: synthetic field
         private final PartitionWriter $outer;

         public final Iterator<Object> apply(int idx, Iterator<Row> it) {
            this.$outer.gpfdistService().start();
            PartitionData var10000;
            if (this.$outer.io$pivotal$greenplum$spark$externaltable$PartitionWriter$$greenplumOptions.iteratorOptimization()) {
               Function1 x$3 = this.$outer.io$pivotal$greenplum$spark$externaltable$PartitionWriter$$rowTransformer;
               List x$4 = PartitionData$.MODULE$.apply$default$3();
               var10000 = new PartitionData(idx, it, x$4, x$3);
            } else {
               List x$6 = it.toList();
               Function1 x$7 = this.$outer.io$pivotal$greenplum$spark$externaltable$PartitionWriter$$rowTransformer;
               Iterator x$8 = PartitionData$.MODULE$.apply$default$2();
               var10000 = new PartitionData(idx, x$8, x$6, x$7);
            }

            PartitionData partitionData = var10000;
            ExtractableManagedResource copiedCountIterator = package$.MODULE$.managed(new Serializable(this) {
               public static final long serialVersionUID = 0L;
               // $FF: synthetic field
               private final <undefinedtype> $outer;

               public final Connection apply() {
                  return this.$outer.io$pivotal$greenplum$spark$externaltable$PartitionWriter$$anonfun$$$outer().getConnection();
               }

               public {
                  if ($outer == null) {
                     throw null;
                  } else {
                     this.$outer = $outer;
                  }
               }
            }, Resource$.MODULE$.connectionResource(), .MODULE$.classType(Connection.class)).map(new Serializable(this, partitionData, idx) {
               public static final long serialVersionUID = 0L;
               // $FF: synthetic field
               private final <undefinedtype> $outer;
               private final PartitionData partitionData$1;
               private final int idx$1;

               public final Iterator<Object> apply(Connection connection) {
                  GreenplumDataMover dataMover = this.$outer.io$pivotal$greenplum$spark$externaltable$PartitionWriter$$anonfun$$$outer().getDataMover(connection);
                  int count = BoxesRunTime.unboxToInt(dataMover.moveData(this.partitionData$1).get());
                  BoxedUnit var10000;
                  if (this.$outer.io$pivotal$greenplum$spark$externaltable$PartitionWriter$$anonfun$$$outer().logger().underlying().isDebugEnabled()) {
                     this.$outer.io$pivotal$greenplum$spark$externaltable$PartitionWriter$$anonfun$$$outer().logger().underlying().debug("Datamover {} copied {} rows", (Object[])(new Object[]{BoxesRunTime.boxToInteger(this.idx$1), BoxesRunTime.boxToInteger(count)}));
                     var10000 = BoxedUnit.UNIT;
                  } else {
                     var10000 = BoxedUnit.UNIT;
                  }

                  return scala.package..MODULE$.Iterator().apply(scala.Predef..MODULE$.wrapIntArray(new int[]{count}));
               }

               public {
                  if ($outer == null) {
                     throw null;
                  } else {
                     this.$outer = $outer;
                     this.partitionData$1 = partitionData$1;
                     this.idx$1 = idx$1;
                  }
               }
            });
            return (Iterator)copiedCountIterator.tried().get();
         }

         // $FF: synthetic method
         public PartitionWriter io$pivotal$greenplum$spark$externaltable$PartitionWriter$$anonfun$$$outer() {
            return this.$outer;
         }

         public {
            if ($outer == null) {
               throw null;
            } else {
               this.$outer = $outer;
            }
         }
      };
   }

   public GreenplumDataMover getDataMover(Connection conn) {
      SqlExecutor sqlExecutor = new SqlExecutor(conn);
      GreenplumTableManager tableManager = new GreenplumTableManager(sqlExecutor);
      return new GreenplumDataMover(this.applicationId, this.io$pivotal$greenplum$spark$externaltable$PartitionWriter$$greenplumOptions, tableManager, this.gpfdistService(), GreenplumDataMover$.MODULE$.$lessinit$greater$default$5());
   }

   public Connection getConnection() {
      return ConnectionManager$.MODULE$.getConnection(this.io$pivotal$greenplum$spark$externaltable$PartitionWriter$$greenplumOptions, true);
   }

   public PartitionWriter(String applicationId, GreenplumOptions greenplumOptions, Function1<Row, Row> rowTransformer) {
      this.applicationId = applicationId;
      this.io$pivotal$greenplum$spark$externaltable$PartitionWriter$$greenplumOptions = greenplumOptions;
      this.io$pivotal$greenplum$spark$externaltable$PartitionWriter$$rowTransformer = rowTransformer;
      LazyLogging$class.$init$(this);
   }
}
