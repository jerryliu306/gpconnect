package io.pivotal.greenplum.spark.externaltable;

import com.typesafe.scalalogging.LazyLogging;
import com.typesafe.scalalogging.LazyLogging$class;
import com.typesafe.scalalogging.Logger;
import io.pivotal.greenplum.spark.ErrorHandling$;
import io.pivotal.greenplum.spark.GpfdistLocation;
import io.pivotal.greenplum.spark.GreenplumCSVFormat$;
import io.pivotal.greenplum.spark.SqlExecutor;
import io.pivotal.greenplum.spark.conf.GreenplumOptions;
import io.pivotal.greenplum.spark.jdbc.Jdbc$;
import java.sql.ResultSet;
import org.apache.spark.sql.SaveMode;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.StructType;
import scala.MatchError;
import scala.Option;
import scala.Serializable;
import scala.StringContext;
import scala.Predef.;
import scala.collection.Seq;
import scala.collection.immutable.StringOps;
import scala.collection.mutable.StringBuilder;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.util.Failure;
import scala.util.Success;
import scala.util.Try;

@ScalaSignature(
   bytes = "\u0006\u0001\tma\u0001B\u0001\u0003\u00015\u0011Qc\u0012:fK:\u0004H.^7UC\ndW-T1oC\u001e,'O\u0003\u0002\u0004\t\u0005iQ\r\u001f;fe:\fG\u000e^1cY\u0016T!!\u0002\u0004\u0002\u000bM\u0004\u0018M]6\u000b\u0005\u001dA\u0011!C4sK\u0016t\u0007\u000f\\;n\u0015\tI!\"A\u0004qSZ|G/\u00197\u000b\u0003-\t!![8\u0004\u0001M\u0019\u0001A\u0004\u000b\u0011\u0005=\u0011R\"\u0001\t\u000b\u0003E\tQa]2bY\u0006L!a\u0005\t\u0003\r\u0005s\u0017PU3g!\t)B$D\u0001\u0017\u0015\t9\u0002$\u0001\u0007tG\u0006d\u0017\r\\8hO&twM\u0003\u0002\u001a5\u0005AA/\u001f9fg\u00064WMC\u0001\u001c\u0003\r\u0019w.\\\u0005\u0003;Y\u00111\u0002T1{s2{wmZ5oO\"Aq\u0004\u0001B\u0001B\u0003%\u0001%A\u0006tc2,\u00050Z2vi>\u0014\bCA\u0011#\u001b\u0005!\u0011BA\u0012\u0005\u0005-\u0019\u0016\u000f\\#yK\u000e,Ho\u001c:\t\u000b\u0015\u0002A\u0011\u0001\u0014\u0002\rqJg.\u001b;?)\t9\u0013\u0006\u0005\u0002)\u00015\t!\u0001C\u0003 I\u0001\u0007\u0001\u0005C\u0003,\u0001\u0011\u0005A&\u0001\u000bqe\u0016\u0004\u0018M]3UC\ndWMR8s/JLG/\u001a\u000b\u0006[YZ$J\u0015\t\u0004]E\u001aT\"A\u0018\u000b\u0005A\u0002\u0012\u0001B;uS2L!AM\u0018\u0003\u0007Q\u0013\u0018\u0010\u0005\u0002\u0010i%\u0011Q\u0007\u0005\u0002\b\u0005>|G.Z1o\u0011\u00159$\u00061\u00019\u0003\u0015!\u0018M\u00197f!\tA\u0013(\u0003\u0002;\u0005\t1rI]3f]BdW/\\)vC2Lg-[3e\u001d\u0006lW\rC\u0003=U\u0001\u0007Q(A\u0006ta\u0006\u00148nU2iK6\f\u0007C\u0001 I\u001b\u0005y$B\u0001!B\u0003\u0015!\u0018\u0010]3t\u0015\t\u00115)A\u0002tc2T!!\u0002#\u000b\u0005\u00153\u0015AB1qC\u000eDWMC\u0001H\u0003\ry'oZ\u0005\u0003\u0013~\u0012!b\u0015;sk\u000e$H+\u001f9f\u0011\u0015Y%\u00061\u0001M\u0003\u001dy\u0007\u000f^5p]N\u0004\"!\u0014)\u000e\u00039S!a\u0014\u0003\u0002\t\r|gNZ\u0005\u0003#:\u0013\u0001c\u0012:fK:\u0004H.^7PaRLwN\\:\t\u000fMS\u0003\u0013!a\u0001)\u0006!Qn\u001c3f!\t)f+D\u0001B\u0013\t9\u0016I\u0001\u0005TCZ,Wj\u001c3f\u0011\u0019I\u0006\u0001\"\u0001\u00035\u0006qqN^3soJLG/\u001a+bE2,G\u0003B.`A\u0006\u00042AL\u0019]!\tyQ,\u0003\u0002_!\t1\u0011I\\=WC2DQa\u000e-A\u0002aBQ\u0001\u0010-A\u0002uBQa\u0013-A\u00021CQa\u0019\u0001\u0005\n\u0011\f1b\u0019:fCR,G+\u00192mKR!Q-\u001b6l!\rq\u0013G\u001a\t\u0003\u001f\u001dL!\u0001\u001b\t\u0003\tUs\u0017\u000e\u001e\u0005\u0006o\t\u0004\r\u0001\u000f\u0005\u0006y\t\u0004\r!\u0010\u0005\u0006\u0017\n\u0004\r\u0001\u0014\u0005\u0006[\u0002!\tA\\\u0001'GJ,\u0017\r^3SK\u0006$\u0017M\u00197f\u000bb$XM\u001d8bYR\u000b'\r\\3JM:{G/\u0012=jgR\u001cH\u0003B3pcjDQ\u0001\u001d7A\u0002a\nQ\"\u001b8uKJt\u0017\r\u001c+bE2,\u0007\"\u0002:m\u0001\u0004\u0019\u0018!D3yi\u0016\u0014h.\u00197UC\ndW\r\u0005\u0002uo:\u0011\u0001&^\u0005\u0003m\n\tac\u0012:fK:\u0004H.^7Rk\u0006d\u0017NZ5fI:\u000bW.Z\u0005\u0003qf\u0014\u0011\u0002V3naR\u000b'\r\\3\u000b\u0005Y\u0014\u0001\"B>m\u0001\u0004a\u0018aD4qM\u0012L7\u000f\u001e'pG\u0006$\u0018n\u001c8\u0011\u0005\u0005j\u0018B\u0001@\u0005\u0005=9\u0005O\u001a3jgRdunY1uS>t\u0007bBA\u0001\u0001\u0011%\u00111A\u0001\u001cGJ,\u0017\r^3SK\u0006$\u0017M\u00197f\u000bb$XM\u001d8bYR\u000b'\r\\3\u0015\u000f\u0015\f)!a\u0002\u0002\n!)\u0001o a\u0001q!)!o a\u0001g\")1p a\u0001y\"9\u0011Q\u0002\u0001\u0005\u0002\u0005=\u0011!F2paf$\u0016M\u00197f\rJ|W.\u0012=uKJt\u0017\r\u001c\u000b\u0007\u0003#\tI\"!\b\u0011\t9\n\u00141\u0003\t\u0004\u001f\u0005U\u0011bAA\f!\t\u0019\u0011J\u001c;\t\u000f\u0005m\u00111\u0002a\u0001q\u0005AQ\r\u001f;UC\ndW\rC\u0004\u0002 \u0005-\u0001\u0019\u0001\u001d\u0002\rQ\f'oZ3u\u0011\u001d\t\u0019\u0003\u0001C\u0005\u0003K\tad\u0019:fCR,'+Z1eC\ndW-\u0012=uKJt\u0017\r\u001c+bE2,7+\u001d7\u0015\u0011\u0005\u001d\u0012QGA\u001c\u0003s\u0001B!!\u000b\u000209\u0019q\"a\u000b\n\u0007\u00055\u0002#\u0001\u0004Qe\u0016$WMZ\u0005\u0005\u0003c\t\u0019D\u0001\u0004TiJLgn\u001a\u0006\u0004\u0003[\u0001\u0002B\u00029\u0002\"\u0001\u0007\u0001\b\u0003\u0004s\u0003C\u0001\ra\u001d\u0005\u0007w\u0006\u0005\u0002\u0019\u0001?\t\u000f\u0005u\u0002\u0001\"\u0001\u0002@\u0005qq-\u001a;D_2,XN\u001c(b[\u0016\u001cH\u0003BA!\u00037\u0002BAL\u0019\u0002DA1\u0011QIA+\u0003OqA!a\u0012\u0002R9!\u0011\u0011JA(\u001b\t\tYEC\u0002\u0002N1\ta\u0001\u0010:p_Rt\u0014\"A\t\n\u0007\u0005M\u0003#A\u0004qC\u000e\\\u0017mZ3\n\t\u0005]\u0013\u0011\f\u0002\u0004'\u0016\f(bAA*!!1q'a\u000fA\u0002aBq!a\u0018\u0001\t\u0003\t\t'A\u0006uC\ndW-\u0012=jgR\u001cHcA\u0017\u0002d!1q'!\u0018A\u0002aB\u0001\"a\u001a\u0001\t\u0003\u0011\u0011\u0011N\u0001\u000eiJ,hnY1uKR\u000b'\r\\3\u0015\u00075\nY\u0007\u0003\u00048\u0003K\u0002\r\u0001\u000f\u0005\t\u0003_\u0002A\u0011\u0001\u0002\u0002r\u0005IAM]8q)\u0006\u0014G.\u001a\u000b\u0004[\u0005M\u0004BB\u001c\u0002n\u0001\u0007\u0001\bC\u0005\u0002x\u0001\t\n\u0011\"\u0001\u0002z\u0005q\u0002O]3qCJ,G+\u00192mK\u001a{'o\u0016:ji\u0016$C-\u001a4bk2$H\u0005N\u000b\u0003\u0003wR3\u0001VA?W\t\ty\b\u0005\u0003\u0002\u0002\u0006-UBAAB\u0015\u0011\t))a\"\u0002\u0013Ut7\r[3dW\u0016$'bAAE!\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\t\u00055\u00151\u0011\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,waBAI\u0005!\u0005\u00111S\u0001\u0016\u000fJ,WM\u001c9mk6$\u0016M\u00197f\u001b\u0006t\u0017mZ3s!\rA\u0013Q\u0013\u0004\u0007\u0003\tA\t!a&\u0014\u0007\u0005Ue\u0002C\u0004&\u0003+#\t!a'\u0015\u0005\u0005M\u0005\u0002CAP\u0003+#\t!!)\u0002\u001d\u001d\u0004HMY\"pYVlg\u000eV=qKR!\u00111UAU!\u0015y\u0011QUA\u0014\u0013\r\t9\u000b\u0005\u0002\u0007\u001fB$\u0018n\u001c8\t\u0011\u0005-\u0016Q\u0014a\u0001\u0003[\u000b!\u0001\u001a;\u0011\u0007y\ny+C\u0002\u00022~\u0012\u0001\u0002R1uCRK\b/\u001a\u0005\t\u0003k\u000b)\n\"\u0001\u00028\u0006)2M]3bi\u0016$\u0016M\u00197f\u0007>dW/\u001c8MSN$H\u0003BA]\u0003w\u0003BAL\u0019\u0002(!1A(a-A\u0002uB\u0001\"a0\u0002\u0016\u0012\u0005\u0011\u0011Y\u0001\u001bO\u0016$8+\u001e9q_J$X\rZ*qCJ\\G)\u0019;b)f\u0004Xm]\u000b\u0003\u0003\u0007\u0004b!!\u0012\u0002V\u00055\u0006\u0002CAd\u0003+#\t!!3\u0002)\r\u0014X-\u0019;f)\u0006\u0014G.Z*uCR,W.\u001a8u)!\tI,a3\u0002P\u0006M\u0007bBAg\u0003\u000b\u0004\r\u0001O\u0001\ni\u0006\u0014G.\u001a(b[\u0016Dq!!5\u0002F\u0002\u0007Q(\u0001\u0004tG\",W.\u0019\u0005\u0007\u0017\u0006\u0015\u0007\u0019\u0001'\t\u0011\u0005]\u0017Q\u0013C\u0001\u00033\fqdZ3oKJ\fG/Z#yi\u0016\u0014h.\u00197UC\ndWMT1nKB\u0013XMZ5y)\u0019\t9#a7\u0002`\"A\u0011Q\\Ak\u0001\u0004\t9#A\u0007baBd\u0017nY1uS>t\u0017\n\u001a\u0005\t\u0003\u001b\f)\u000e1\u0001\u0002(!I\u00111]AK\t\u0003\u0011\u0011Q]\u0001&O\u0016tWM]1uK\u0016CH/\u001a:oC2$\u0016M\u00197f\u001d\u0006lWmQ8mk6t\u0007K]3gSb$\u0002\"a\n\u0002h\u0006%\u00181\u001e\u0005\t\u0003;\f\t\u000f1\u0001\u0002(!9q'!9A\u0002\u0005\u001d\u0002\u0002CAw\u0003C\u0004\r!a\u0011\u0002\u000f\r|G.^7og\"A\u0011\u0011_AK\t\u0003\t\u00190A\rhK:,'/\u0019;f\u000bb$XM\u001d8bYR\u000b'\r\\3OC6,G\u0003DA\u0014\u0003k\f90!?\u0002~\n\u001d\u0001\u0002CAo\u0003_\u0004\r!a\n\t\u000f]\ny\u000f1\u0001\u0002(!A\u00111`Ax\u0001\u0004\t9#\u0001\u0006fq\u0016\u001cW\u000f^8s\u0013\u0012D\u0001\"a@\u0002p\u0002\u0007!\u0011A\u0001\ti\"\u0014X-\u00193JIB\u0019qBa\u0001\n\u0007\t\u0015\u0001C\u0001\u0003M_:<\u0007BCAw\u0003_\u0004\n\u00111\u0001\u0002D!A!1BAK\t\u0013\u0011i!\u0001\u0007g_2$W\rZ'ek!+\u0007\u0010\u0006\u0003\u0002(\t=\u0001\u0002\u0003B\t\u0005\u0013\u0001\r!a\n\u0002\u0003MD!B!\u0006\u0002\u0016F\u0005I\u0011\u0001B\f\u0003\r:WM\\3sCR,W\t\u001f;fe:\fG\u000eV1cY\u0016t\u0015-\\3%I\u00164\u0017-\u001e7uIU*\"A!\u0007+\t\u0005\r\u0013Q\u0010"
)
public class GreenplumTableManager implements LazyLogging {
   public final SqlExecutor io$pivotal$greenplum$spark$externaltable$GreenplumTableManager$$sqlExecutor;
   private final transient Logger logger;
   private transient volatile boolean bitmap$trans$0;

   public static Seq<String> generateExternalTableName$default$5() {
      return GreenplumTableManager$.MODULE$.generateExternalTableName$default$5();
   }

   public static String generateExternalTableName(String var0, String var1, String var2, long var3, Seq<String> var5) {
      return GreenplumTableManager$.MODULE$.generateExternalTableName(var0, var1, var2, var3, var5);
   }

   public static String generateExternalTableNamePrefix(String var0, String var1) {
      return GreenplumTableManager$.MODULE$.generateExternalTableNamePrefix(var0, var1);
   }

   public static Try<String> createTableStatement(GreenplumQualifiedName var0, StructType var1, GreenplumOptions var2) {
      return GreenplumTableManager$.MODULE$.createTableStatement(var0, var1, var2);
   }

   public static Seq<DataType> getSupportedSparkDataTypes() {
      return GreenplumTableManager$.MODULE$.getSupportedSparkDataTypes();
   }

   public static Try<String> createTableColumnList(StructType var0) {
      return GreenplumTableManager$.MODULE$.createTableColumnList(var0);
   }

   public static Option<String> gpdbColumnType(DataType var0) {
      return GreenplumTableManager$.MODULE$.gpdbColumnType(var0);
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

   public Try<Object> prepareTableForWrite(GreenplumQualifiedName table, StructType sparkSchema, GreenplumOptions options, SaveMode mode) {
      Try var5 = this.tableExists(table);
      Object var8;
      if (var5 instanceof Failure) {
         Failure var6 = (Failure)var5;
         Throwable exception = var6.exception();
         var8 = new Failure(exception);
      } else {
         if (!(var5 instanceof Success)) {
            throw new MatchError(var5);
         }

         Success var9 = (Success)var5;
         boolean exists = BoxesRunTime.unboxToBoolean(var9.value());
         Object var10000;
         if (!exists) {
            var10000 = this.io$pivotal$greenplum$spark$externaltable$GreenplumTableManager$$createTable(table, sparkSchema, options).map(new Serializable(this) {
               public static final long serialVersionUID = 0L;

               public final boolean apply(BoxedUnit x$2) {
                  return true;
               }
            });
         } else {
            label47: {
               label38: {
                  SaveMode var11 = SaveMode.ErrorIfExists;
                  if (mode == null) {
                     if (var11 != null) {
                        break label38;
                     }
                  } else if (!mode.equals(var11)) {
                     break label38;
                  }

                  var10000 = new Failure(new RuntimeException((new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{"Table ", " exists, and SaveMode.ErrorIfExists was specified"})))).s(.MODULE$.genericWrapArray(new Object[]{table}))));
                  break label47;
               }

               label31: {
                  SaveMode var12 = SaveMode.Overwrite;
                  if (mode == null) {
                     if (var12 == null) {
                        break label31;
                     }
                  } else if (mode.equals(var12)) {
                     break label31;
                  }

                  var10000 = new Success(BoxesRunTime.boxToBoolean(false));
                  break label47;
               }

               var10000 = this.overwriteTable(table, sparkSchema, options).map(new Serializable(this) {
                  public static final long serialVersionUID = 0L;

                  public final boolean apply(Object x$1) {
                     return true;
                  }
               });
            }
         }

         var8 = var10000;
      }

      return (Try)var8;
   }

   public SaveMode prepareTableForWrite$default$4() {
      return SaveMode.ErrorIfExists;
   }

   public Try<Object> overwriteTable(GreenplumQualifiedName table, StructType sparkSchema, GreenplumOptions options) {
      return options.truncateTable() ? this.truncateTable(table) : this.dropTable(table).flatMap(new Serializable(this, table, sparkSchema, options) {
         public static final long serialVersionUID = 0L;
         // $FF: synthetic field
         private final GreenplumTableManager $outer;
         public final GreenplumQualifiedName table$1;
         private final StructType sparkSchema$1;
         private final GreenplumOptions options$1;

         public final Try<BoxedUnit> apply(boolean _) {
            return this.$outer.io$pivotal$greenplum$spark$externaltable$GreenplumTableManager$$createTable(this.table$1, this.sparkSchema$1, this.options$1).map(new Serializable(this) {
               public static final long serialVersionUID = 0L;
               // $FF: synthetic field
               private final <undefinedtype> $outer;

               public final void apply(BoxedUnit _) {
                  BoxedUnit var10000;
                  if (this.$outer.io$pivotal$greenplum$spark$externaltable$GreenplumTableManager$$anonfun$$$outer().logger().underlying().isDebugEnabled()) {
                     this.$outer.io$pivotal$greenplum$spark$externaltable$GreenplumTableManager$$anonfun$$$outer().logger().underlying().debug("Table {} was successfully dropped and created", this.$outer.table$1);
                     var10000 = BoxedUnit.UNIT;
                  } else {
                     var10000 = BoxedUnit.UNIT;
                  }

               }

               public {
                  if ($outer == null) {
                     throw null;
                  } else {
                     this.$outer = $outer;
                  }
               }
            });
         }

         // $FF: synthetic method
         public GreenplumTableManager io$pivotal$greenplum$spark$externaltable$GreenplumTableManager$$anonfun$$$outer() {
            return this.$outer;
         }

         public {
            if ($outer == null) {
               throw null;
            } else {
               this.$outer = $outer;
               this.table$1 = table$1;
               this.sparkSchema$1 = sparkSchema$1;
               this.options$1 = options$1;
            }
         }
      });
   }

   public Try<BoxedUnit> io$pivotal$greenplum$spark$externaltable$GreenplumTableManager$$createTable(GreenplumQualifiedName table, StructType sparkSchema, GreenplumOptions options) {
      return GreenplumTableManager$.MODULE$.createTableStatement(table, sparkSchema, options).flatMap(new Serializable(this, table) {
         public static final long serialVersionUID = 0L;
         // $FF: synthetic field
         private final GreenplumTableManager $outer;
         public final GreenplumQualifiedName table$2;

         public final Try<BoxedUnit> apply(String sql) {
            return this.$outer.io$pivotal$greenplum$spark$externaltable$GreenplumTableManager$$sqlExecutor.execute(sql).map(new Serializable(this) {
               public static final long serialVersionUID = 0L;
               // $FF: synthetic field
               private final <undefinedtype> $outer;

               public final void apply(boolean _) {
                  BoxedUnit var10000;
                  if (this.$outer.io$pivotal$greenplum$spark$externaltable$GreenplumTableManager$$anonfun$$$outer().logger().underlying().isDebugEnabled()) {
                     this.$outer.io$pivotal$greenplum$spark$externaltable$GreenplumTableManager$$anonfun$$$outer().logger().underlying().debug("Table {} was successfully created", this.$outer.table$2);
                     var10000 = BoxedUnit.UNIT;
                  } else {
                     var10000 = BoxedUnit.UNIT;
                  }

               }

               public {
                  if ($outer == null) {
                     throw null;
                  } else {
                     this.$outer = $outer;
                  }
               }
            });
         }

         // $FF: synthetic method
         public GreenplumTableManager io$pivotal$greenplum$spark$externaltable$GreenplumTableManager$$anonfun$$$outer() {
            return this.$outer;
         }

         public {
            if ($outer == null) {
               throw null;
            } else {
               this.$outer = $outer;
               this.table$2 = table$2;
            }
         }
      }).recoverWith(ErrorHandling$.MODULE$.wrapErrorMessage((new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{"Exception while creating table ", ""})))).s(.MODULE$.genericWrapArray(new Object[]{table}))));
   }

   public Try<BoxedUnit> createReadableExternalTableIfNotExists(GreenplumQualifiedName internalTable, GreenplumQualifiedName.TempTable externalTable, GpfdistLocation gpfdistLocation) {
      boolean var4 = false;
      Success var5 = null;
      Try var6 = this.tableExists(externalTable);
      Object var8;
      if (var6 instanceof Success) {
         var4 = true;
         var5 = (Success)var6;
         boolean var7 = BoxesRunTime.unboxToBoolean(var5.value());
         if (var7) {
            var8 = new Success(BoxedUnit.UNIT);
            return (Try)var8;
         }
      }

      if (var4) {
         boolean var9 = BoxesRunTime.unboxToBoolean(var5.value());
         if (!var9) {
            var8 = this.createReadableExternalTable(internalTable, externalTable, gpfdistLocation);
            return (Try)var8;
         }
      }

      if (!(var6 instanceof Failure)) {
         throw new MatchError(var6);
      } else {
         Failure var10 = (Failure)var6;
         Throwable exception = var10.exception();
         var8 = new Failure(exception);
         return (Try)var8;
      }
   }

   private Try<BoxedUnit> createReadableExternalTable(GreenplumQualifiedName internalTable, GreenplumQualifiedName.TempTable externalTable, GpfdistLocation gpfdistLocation) {
      String sql = this.createReadableExternalTableSql(internalTable, externalTable, gpfdistLocation);
      Try var5 = this.io$pivotal$greenplum$spark$externaltable$GreenplumTableManager$$sqlExecutor.execute(sql);
      BoxedUnit var10000;
      Object var6;
      if (var5 instanceof Success) {
         if (this.logger().underlying().isDebugEnabled()) {
            this.logger().underlying().debug((new StringBuilder()).append((new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{"External table ", " not found, "})))).s(.MODULE$.genericWrapArray(new Object[]{externalTable}))).append((new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{"created table with port=", ""})))).s(.MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(gpfdistLocation.port())}))).toString());
            var10000 = BoxedUnit.UNIT;
         } else {
            var10000 = BoxedUnit.UNIT;
         }

         var6 = new Success(BoxedUnit.UNIT);
      } else {
         if (!(var5 instanceof Failure)) {
            throw new MatchError(var5);
         }

         Failure var7 = (Failure)var5;
         Throwable exception = var7.exception();
         if (this.logger().underlying().isErrorEnabled()) {
            this.logger().underlying().error((new StringBuilder()).append((new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{"Exception while creating external table ", " "})))).s(.MODULE$.genericWrapArray(new Object[]{externalTable}))).append((new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{"with port=", ": ", ""})))).s(.MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(gpfdistLocation.port()), exception}))).toString());
            var10000 = BoxedUnit.UNIT;
         } else {
            var10000 = BoxedUnit.UNIT;
         }

         var6 = new Failure(exception);
      }

      return (Try)var6;
   }

   public Try<Object> copyTableFromExternal(GreenplumQualifiedName extTable, GreenplumQualifiedName target) {
      String sqlQuery = (new StringOps(.MODULE$.augmentString((new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{"INSERT INTO ", "\n         |SELECT *\n         |FROM ", ""})))).s(.MODULE$.genericWrapArray(new Object[]{target, extTable}))))).stripMargin();
      return this.io$pivotal$greenplum$spark$externaltable$GreenplumTableManager$$sqlExecutor.executeUpdate(sqlQuery);
   }

   private String createReadableExternalTableSql(GreenplumQualifiedName internalTable, GreenplumQualifiedName.TempTable externalTable, GpfdistLocation gpfdistLocation) {
      String url = gpfdistLocation.generate(externalTable.name());
      String sqlQuery = (new StringOps(.MODULE$.augmentString((new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{"CREATE READABLE EXTERNAL TEMP TABLE\n         |", " (LIKE ", ")\n         |LOCATION ('", "')\n         |FORMAT 'CSV'\n         |(DELIMITER AS '", "'\n         | NULL AS '", "')\n         |ENCODING '", "'"})))).s(.MODULE$.genericWrapArray(new Object[]{externalTable, internalTable, url, BoxesRunTime.boxToCharacter(GreenplumCSVFormat$.MODULE$.CHAR_DELIMITER()), GreenplumCSVFormat$.MODULE$.VALUE_OF_NULL(), GreenplumCSVFormat$.MODULE$.DEFAULT_ENCODING()}))))).stripMargin();
      BoxedUnit var10000;
      if (this.logger().underlying().isDebugEnabled()) {
         this.logger().underlying().debug("Create readable external table query: {}", sqlQuery);
         var10000 = BoxedUnit.UNIT;
      } else {
         var10000 = BoxedUnit.UNIT;
      }

      return sqlQuery;
   }

   public Try<Seq<String>> getColumnNames(GreenplumQualifiedName table) {
      return this.io$pivotal$greenplum$spark$externaltable$GreenplumTableManager$$sqlExecutor.executeQuery((new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{"SELECT * FROM ", " LIMIT 0"})))).s(.MODULE$.genericWrapArray(new Object[]{table})), new Serializable(this) {
         public static final long serialVersionUID = 0L;

         public final Seq<String> apply(ResultSet result) {
            return Jdbc$.MODULE$.getColumnNames(result.getMetaData());
         }
      });
   }

   public Try<Object> tableExists(GreenplumQualifiedName table) {
      String sqlQuery = (new StringOps(.MODULE$.augmentString((new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{"SELECT EXISTS (\n         |SELECT 1\n         |FROM information_schema.tables\n         |", ");"})))).s(.MODULE$.genericWrapArray(new Object[]{table.preparedSQL()}))))).stripMargin();
      return this.io$pivotal$greenplum$spark$externaltable$GreenplumTableManager$$sqlExecutor.executeQuery(sqlQuery, table.queryArgs(), new Serializable(this) {
         public static final long serialVersionUID = 0L;

         public final boolean apply(ResultSet result) {
            result.next();
            return result.getBoolean(1);
         }
      }).recoverWith(ErrorHandling$.MODULE$.wrapErrorMessage((new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{"Cannot determine if table ", " exists"})))).s(.MODULE$.genericWrapArray(new Object[]{table}))));
   }

   public Try<Object> truncateTable(GreenplumQualifiedName table) {
      String sqlQuery = (new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{"TRUNCATE TABLE ", ""})))).s(.MODULE$.genericWrapArray(new Object[]{table}));
      return this.io$pivotal$greenplum$spark$externaltable$GreenplumTableManager$$sqlExecutor.execute(sqlQuery).recoverWith(ErrorHandling$.MODULE$.wrapErrorMessage((new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{"Exception while truncating table ", ""})))).s(.MODULE$.genericWrapArray(new Object[]{table}))));
   }

   public Try<Object> dropTable(GreenplumQualifiedName table) {
      String sqlQuery = (new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{"DROP TABLE ", ""})))).s(.MODULE$.genericWrapArray(new Object[]{table}));
      return this.io$pivotal$greenplum$spark$externaltable$GreenplumTableManager$$sqlExecutor.execute(sqlQuery).recoverWith(ErrorHandling$.MODULE$.wrapErrorMessage((new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{"Exception while dropping table ", ""})))).s(.MODULE$.genericWrapArray(new Object[]{table}))));
   }

   public GreenplumTableManager(SqlExecutor sqlExecutor) {
      this.io$pivotal$greenplum$spark$externaltable$GreenplumTableManager$$sqlExecutor = sqlExecutor;
      LazyLogging$class.$init$(this);
   }
}
