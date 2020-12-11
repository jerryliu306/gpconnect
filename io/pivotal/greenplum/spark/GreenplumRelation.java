package io.pivotal.greenplum.spark;

import com.typesafe.scalalogging.Logger;
import io.pivotal.greenplum.spark.conf.GreenplumOptions;
import io.pivotal.greenplum.spark.jdbc.ColumnValueRange;
import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.sources.BaseRelation;
import org.apache.spark.sql.sources.Filter;
import org.apache.spark.sql.sources.PrunedFilteredScan;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import scala.Option;
import scala.Product;
import scala.Serializable;
import scala.Tuple3;
import scala.Predef.;
import scala.Product.class;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0001\tmb!B\u0001\u0003\u0001\nQ!!E$sK\u0016t\u0007\u000f\\;n%\u0016d\u0017\r^5p]*\u00111\u0001B\u0001\u0006gB\f'o\u001b\u0006\u0003\u000b\u0019\t\u0011b\u001a:fK:\u0004H.^7\u000b\u0005\u001dA\u0011a\u00029jm>$\u0018\r\u001c\u0006\u0002\u0013\u0005\u0011\u0011n\\\n\u0006\u0001-A2$\t\t\u0003\u0019Yi\u0011!\u0004\u0006\u0003\u001d=\tqa]8ve\u000e,7O\u0003\u0002\u0011#\u0005\u00191/\u001d7\u000b\u0005\r\u0011\"BA\n\u0015\u0003\u0019\t\u0007/Y2iK*\tQ#A\u0002pe\u001eL!aF\u0007\u0003\u0019\t\u000b7/\u001a*fY\u0006$\u0018n\u001c8\u0011\u00051I\u0012B\u0001\u000e\u000e\u0005I\u0001&/\u001e8fI\u001aKG\u000e^3sK\u0012\u001c6-\u00198\u0011\u0005qyR\"A\u000f\u000b\u0003y\tQa]2bY\u0006L!\u0001I\u000f\u0003\u000fA\u0013x\u000eZ;diB\u0011ADI\u0005\u0003Gu\u0011AbU3sS\u0006d\u0017N_1cY\u0016D\u0001\"\n\u0001\u0003\u0016\u0004%\taJ\u0001\u0007g\u000eDW-\\1\u0004\u0001U\t\u0001\u0006\u0005\u0002*Y5\t!F\u0003\u0002,\u001f\u0005)A/\u001f9fg&\u0011QF\u000b\u0002\u000b'R\u0014Xo\u0019;UsB,\u0007\u0002C\u0018\u0001\u0005#\u0005\u000b\u0011\u0002\u0015\u0002\u000fM\u001c\u0007.Z7bA!A\u0011\u0007\u0001BK\u0002\u0013\u0005!'A\u0003qCJ$8/F\u00014!\raBGN\u0005\u0003ku\u0011Q!\u0011:sCf\u0004\"a\u000e\u001d\u000e\u0003\tI!!\u000f\u0002\u0003%\u001d\u0013X-\u001a8qYVl\u0007+\u0019:uSRLwN\u001c\u0005\tw\u0001\u0011\t\u0012)A\u0005g\u00051\u0001/\u0019:ug\u0002B\u0001\"\u0010\u0001\u0003\u0016\u0004%\tAP\u0001\u0011OJ,WM\u001c9mk6|\u0005\u000f^5p]N,\u0012a\u0010\t\u0003\u0001\u000ek\u0011!\u0011\u0006\u0003\u0005\n\tAaY8oM&\u0011A)\u0011\u0002\u0011\u000fJ,WM\u001c9mk6|\u0005\u000f^5p]ND\u0001B\u0012\u0001\u0003\u0012\u0003\u0006IaP\u0001\u0012OJ,WM\u001c9mk6|\u0005\u000f^5p]N\u0004\u0003\u0002\u0003%\u0001\u0005\u000b\u0007I\u0011A%\u0002\u0015M\fHnQ8oi\u0016DH/F\u0001K!\tYE*D\u0001\u0010\u0013\tiuB\u0001\u0006T#2\u001buN\u001c;fqRD\u0001b\u0014\u0001\u0003\u0002\u0003\u0006IAS\u0001\fgFd7i\u001c8uKb$\b\u0005\u000b\u0002O#B\u0011ADU\u0005\u0003'v\u0011\u0011\u0002\u001e:b]NLWM\u001c;\t\u000bU\u0003A\u0011\u0001,\u0002\rqJg.\u001b;?)\u00119&l\u0017/\u0015\u0005aK\u0006CA\u001c\u0001\u0011\u0015AE\u000b1\u0001K\u0011\u0015)C\u000b1\u0001)\u0011\u0015\tD\u000b1\u00014\u0011\u0015iD\u000b1\u0001@\u0011\u001dq\u0006A1A\u0005B}\u000baB\\3fI\u000e{gN^3sg&|g.F\u0001a!\ta\u0012-\u0003\u0002c;\t9!i\\8mK\u0006t\u0007B\u00023\u0001A\u0003%\u0001-A\boK\u0016$7i\u001c8wKJ\u001c\u0018n\u001c8!\u0011\u00151\u0007\u0001\"\u0003h\u0003Y\u0001(o\u001c6fGR\u001c6\r[3nCR{7i\u001c7v[:\u001cHc\u0001\u0015iS\")Q%\u001aa\u0001Q!)!.\u001aa\u0001W\u000691m\u001c7v[:\u001c\bc\u0001\u000f5YB\u0011Q\u000e\u001d\b\u000399L!a\\\u000f\u0002\rA\u0013X\rZ3g\u0013\t\t(O\u0001\u0004TiJLgn\u001a\u0006\u0003_vAQ\u0001\u001e\u0001\u0005BU\f\u0001#\u001e8iC:$G.\u001a3GS2$XM]:\u0015\u0005YT\bc\u0001\u000f5oB\u0011A\u0002_\u0005\u0003s6\u0011aAR5mi\u0016\u0014\b\"B>t\u0001\u00041\u0018a\u00024jYR,'o\u001d\u0005\u0006{\u0002!\tE`\u0001\nEVLG\u000eZ*dC:$Ra`A\t\u0003+\u0001b!!\u0001\u0002\b\u0005-QBAA\u0002\u0015\r\t)!E\u0001\u0004e\u0012$\u0017\u0002BA\u0005\u0003\u0007\u00111A\u0015#E!\rY\u0015QB\u0005\u0004\u0003\u001fy!a\u0001*po\"1\u00111\u0003?A\u0002-\fqB]3rk&\u0014X\rZ\"pYVlgn\u001d\u0005\u0006wr\u0004\rA\u001e\u0005\n\u00033\u0001\u0011\u0011!C\u0001\u00037\tAaY8qsRA\u0011QDA\u0011\u0003G\t)\u0003F\u0002Y\u0003?Aa\u0001SA\f\u0001\u0004Q\u0005\u0002C\u0013\u0002\u0018A\u0005\t\u0019\u0001\u0015\t\u0011E\n9\u0002%AA\u0002MB\u0001\"PA\f!\u0003\u0005\ra\u0010\u0005\n\u0003S\u0001\u0011\u0013!C\u0001\u0003W\tabY8qs\u0012\"WMZ1vYR$\u0013'\u0006\u0002\u0002.)\u001a\u0001&a\f,\u0005\u0005E\u0002\u0003BA\u001a\u0003{i!!!\u000e\u000b\t\u0005]\u0012\u0011H\u0001\nk:\u001c\u0007.Z2lK\u0012T1!a\u000f\u001e\u0003)\tgN\\8uCRLwN\\\u0005\u0005\u0003\u007f\t)DA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016D\u0011\"a\u0011\u0001#\u0003%\t!!\u0012\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%eU\u0011\u0011q\t\u0016\u0004g\u0005=\u0002\"CA&\u0001E\u0005I\u0011AA'\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIM*\"!a\u0014+\u0007}\ny\u0003C\u0005\u0002T\u0001\t\t\u0011\"\u0011\u0002V\u0005i\u0001O]8ek\u000e$\bK]3gSb,\"!a\u0016\u0011\t\u0005e\u00131M\u0007\u0003\u00037RA!!\u0018\u0002`\u0005!A.\u00198h\u0015\t\t\t'\u0001\u0003kCZ\f\u0017bA9\u0002\\!I\u0011q\r\u0001\u0002\u0002\u0013\u0005\u0011\u0011N\u0001\raJ|G-^2u\u0003JLG/_\u000b\u0003\u0003W\u00022\u0001HA7\u0013\r\ty'\b\u0002\u0004\u0013:$\b\"CA:\u0001\u0005\u0005I\u0011AA;\u00039\u0001(o\u001c3vGR,E.Z7f]R$B!a\u001e\u0002~A\u0019A$!\u001f\n\u0007\u0005mTDA\u0002B]fD!\"a \u0002r\u0005\u0005\t\u0019AA6\u0003\rAH%\r\u0005\n\u0003\u0007\u0003\u0011\u0011!C!\u0003\u000b\u000bq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0003\u0003\u000f\u0003b!!#\u0002\u0010\u0006]TBAAF\u0015\r\ti)H\u0001\u000bG>dG.Z2uS>t\u0017\u0002BAI\u0003\u0017\u0013\u0001\"\u0013;fe\u0006$xN\u001d\u0005\n\u0003+\u0003\u0011\u0011!C\u0001\u0003/\u000b\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0004A\u0006e\u0005BCA@\u0003'\u000b\t\u00111\u0001\u0002x!I\u0011Q\u0014\u0001\u0002\u0002\u0013\u0005\u0013qT\u0001\tQ\u0006\u001c\bnQ8eKR\u0011\u00111\u000e\u0005\n\u0003G\u0003\u0011\u0011!C!\u0003K\u000b\u0001\u0002^8TiJLgn\u001a\u000b\u0003\u0003/B\u0011\"!+\u0001\u0003\u0003%\t%a+\u0002\r\u0015\fX/\u00197t)\r\u0001\u0017Q\u0016\u0005\u000b\u0003\u007f\n9+!AA\u0002\u0005]t\u0001CAY\u0005!%!!a-\u0002#\u001d\u0013X-\u001a8qYVl'+\u001a7bi&|g\u000eE\u00028\u0003k3q!\u0001\u0002\t\n\t\t9lE\u0004\u00026\u0006e\u0016qX\u0011\u0011\u0007q\tY,C\u0002\u0002>v\u0011a!\u00118z%\u00164\u0007\u0003BAa\u0003\u001fl!!a1\u000b\t\u0005\u0015\u0017qY\u0001\rg\u000e\fG.\u00197pO\u001eLgn\u001a\u0006\u0005\u0003\u0013\fY-\u0001\u0005usB,7/\u00194f\u0015\t\ti-A\u0002d_6LA!!5\u0002D\nYA*\u0019>z\u0019><w-\u001b8h\u0011\u001d)\u0016Q\u0017C\u0001\u0003+$\"!a-\t\u0011\u0005e\u0017Q\u0017C\u0001\u00037\fqbY8mk6t\u0007+\u0019:uSRLwN\u001c\u000b\bg\u0005u\u0017\u0011]Av\u0011!\ty.a6A\u0002\u0005-\u0014a\u0005:fcV,7\u000f^3e!\u0006\u0014H/\u001b;j_:\u001c\b\u0002CAr\u0003/\u0004\r!!:\u0002\u001fA\f'\u000f^5uS>t7i\u001c7v[:\u00042!KAt\u0013\r\tIO\u000b\u0002\f'R\u0014Xo\u0019;GS\u0016dG\r\u0003\u0005\u0002n\u0006]\u0007\u0019AAx\u0003)1\u0018\r\\;f%\u0006tw-\u001a\t\u0005\u0003c\f90\u0004\u0002\u0002t*\u0019\u0011Q\u001f\u0002\u0002\t)$'mY\u0005\u0005\u0003s\f\u0019P\u0001\tD_2,XN\u001c,bYV,'+\u00198hK\"A\u0011Q`A[\t\u0003\ty0A\ttK\u001elWM\u001c;QCJ$\u0018\u000e^5p]N$2a\rB\u0001\u0011!\u0011\u0019!a?A\u0002\t\u0015\u0011AC:fO6,g\u000e^%egB!A\u0004NA6\u0011)\u0011I!!.\u0002\u0002\u0013\u0005%1B\u0001\u0006CB\u0004H.\u001f\u000b\t\u0005\u001b\u0011\u0019B!\u0006\u0003\u0018Q\u0019\u0001La\u0004\t\r!\u00139\u00011\u0001KQ\r\u0011y!\u0015\u0005\u0007K\t\u001d\u0001\u0019\u0001\u0015\t\rE\u00129\u00011\u00014\u0011\u0019i$q\u0001a\u0001\u007f!Q!1DA[\u0003\u0003%\tI!\b\u0002\u000fUt\u0017\r\u001d9msR!!q\u0004B\u0016!\u0015a\"\u0011\u0005B\u0013\u0013\r\u0011\u0019#\b\u0002\u0007\u001fB$\u0018n\u001c8\u0011\rq\u00119\u0003K\u001a@\u0013\r\u0011I#\b\u0002\u0007)V\u0004H.Z\u001a\t\u0013\t5\"\u0011DA\u0001\u0002\u0004A\u0016a\u0001=%a!Q!\u0011GA[\u0003\u0003%IAa\r\u0002\u0017I,\u0017\r\u001a*fg>dg/\u001a\u000b\u0003\u0005k\u0001B!!\u0017\u00038%!!\u0011HA.\u0005\u0019y%M[3di\u0002"
)
public class GreenplumRelation extends BaseRelation implements PrunedFilteredScan, Product, Serializable {
   private final StructType schema;
   private final GreenplumPartition[] parts;
   private final GreenplumOptions greenplumOptions;
   private final transient SQLContext sqlContext;
   private final boolean needConversion;

   public static Logger logger() {
      return GreenplumRelation$.MODULE$.logger();
   }

   public static Option<Tuple3<StructType, GreenplumPartition[], GreenplumOptions>> unapply(GreenplumRelation var0) {
      return GreenplumRelation$.MODULE$.unapply(var0);
   }

   public static GreenplumRelation apply(StructType var0, GreenplumPartition[] var1, GreenplumOptions var2, SQLContext var3) {
      return GreenplumRelation$.MODULE$.apply(var0, var1, var2, var3);
   }

   public static GreenplumPartition[] segmentPartitions(int[] var0) {
      return GreenplumRelation$.MODULE$.segmentPartitions(var0);
   }

   public static GreenplumPartition[] columnPartition(int var0, StructField var1, ColumnValueRange var2) {
      return GreenplumRelation$.MODULE$.columnPartition(var0, var1, var2);
   }

   public StructType schema() {
      return this.schema;
   }

   public GreenplumPartition[] parts() {
      return this.parts;
   }

   public GreenplumOptions greenplumOptions() {
      return this.greenplumOptions;
   }

   public SQLContext sqlContext() {
      return this.sqlContext;
   }

   public boolean needConversion() {
      return this.needConversion;
   }

   private StructType projectSchemaToColumns(StructType schema, String[] columns) {
      return new StructType((StructField[]).MODULE$.refArrayOps((Object[])columns).map(new Serializable(this, schema) {
         public static final long serialVersionUID = 0L;
         private final StructType schema$1;

         public final StructField apply(String col) {
            return (StructField)this.schema$1.find(new Serializable(this, col) {
               public static final long serialVersionUID = 0L;
               private final String col$1;

               public final boolean apply(StructField x$1) {
                  boolean var3;
                  label23: {
                     String var10000 = x$1.name();
                     String var2 = this.col$1;
                     if (var10000 == null) {
                        if (var2 == null) {
                           break label23;
                        }
                     } else if (var10000.equals(var2)) {
                        break label23;
                     }

                     var3 = false;
                     return var3;
                  }

                  var3 = true;
                  return var3;
               }

               public {
                  this.col$1 = col$1;
               }
            }).get();
         }

         public {
            this.schema$1 = schema$1;
         }
      }, scala.Array..MODULE$.canBuildFrom(scala.reflect.ClassTag..MODULE$.apply(StructField.class))));
   }

   public Filter[] unhandledFilters(Filter[] filters) {
      return (Filter[]).MODULE$.refArrayOps((Object[])filters).filter(new Serializable(this) {
         public static final long serialVersionUID = 0L;

         public final boolean apply(Filter x$2) {
            return GreenplumRDD$.MODULE$.compileFilter(x$2).isEmpty();
         }
      });
   }

   public RDD<Row> buildScan(String[] requiredColumns, Filter[] filters) {
      StructType projectedSchema = this.projectSchemaToColumns(this.schema(), requiredColumns);
      return new GreenplumRDD(this.sqlContext().sparkContext(), projectedSchema, this.parts(), this.greenplumOptions(), .MODULE$.wrapRefArray((Object[])requiredColumns), filters);
   }

   public GreenplumRelation copy(StructType schema, GreenplumPartition[] parts, GreenplumOptions greenplumOptions, SQLContext sqlContext) {
      return new GreenplumRelation(schema, parts, greenplumOptions, sqlContext);
   }

   public StructType copy$default$1() {
      return this.schema();
   }

   public GreenplumPartition[] copy$default$2() {
      return this.parts();
   }

   public GreenplumOptions copy$default$3() {
      return this.greenplumOptions();
   }

   public String productPrefix() {
      return "GreenplumRelation";
   }

   public int productArity() {
      return 3;
   }

   public Object productElement(int x$1) {
      Object var10000;
      switch(x$1) {
      case 0:
         var10000 = this.schema();
         break;
      case 1:
         var10000 = this.parts();
         break;
      case 2:
         var10000 = this.greenplumOptions();
         break;
      default:
         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
      }

      return var10000;
   }

   public Iterator<Object> productIterator() {
      return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(Object x$1) {
      return x$1 instanceof GreenplumRelation;
   }

   public int hashCode() {
      return scala.runtime.ScalaRunTime..MODULE$._hashCode(this);
   }

   public String toString() {
      return scala.runtime.ScalaRunTime..MODULE$._toString(this);
   }

   public boolean equals(Object x$1) {
      boolean var8;
      if (this != x$1) {
         label63: {
            boolean var3;
            if (x$1 instanceof GreenplumRelation) {
               var3 = true;
            } else {
               var3 = false;
            }

            if (var3) {
               label46: {
                  label45: {
                     GreenplumRelation var4 = (GreenplumRelation)x$1;
                     StructType var10000 = this.schema();
                     StructType var5 = var4.schema();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label45;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label45;
                     }

                     if (this.parts() == var4.parts()) {
                        label39: {
                           GreenplumOptions var7 = this.greenplumOptions();
                           GreenplumOptions var6 = var4.greenplumOptions();
                           if (var7 == null) {
                              if (var6 != null) {
                                 break label39;
                              }
                           } else if (!var7.equals(var6)) {
                              break label39;
                           }

                           if (var4.canEqual(this)) {
                              var8 = true;
                              break label46;
                           }
                        }
                     }
                  }

                  var8 = false;
               }

               if (var8) {
                  break label63;
               }
            }

            var8 = false;
            return var8;
         }
      }

      var8 = true;
      return var8;
   }

   public GreenplumRelation(StructType schema, GreenplumPartition[] parts, GreenplumOptions greenplumOptions, SQLContext sqlContext) {
      this.schema = schema;
      this.parts = parts;
      this.greenplumOptions = greenplumOptions;
      this.sqlContext = sqlContext;
      class.$init$(this);
      this.needConversion = false;
   }
}
