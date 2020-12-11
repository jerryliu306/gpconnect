package io.pivotal.greenplum.spark.externaltable;

import scala.Option;
import scala.Product;
import scala.Serializable;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.Product.class;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.AbstractFunction1;
import scala.runtime.AbstractFunction2;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0001\t\u0015a!B\u0001\u0003\u0003Ci!AF$sK\u0016t\u0007\u000f\\;n#V\fG.\u001b4jK\u0012t\u0015-\\3\u000b\u0005\r!\u0011!D3yi\u0016\u0014h.\u00197uC\ndWM\u0003\u0002\u0006\r\u0005)1\u000f]1sW*\u0011q\u0001C\u0001\nOJ,WM\u001c9mk6T!!\u0003\u0006\u0002\u000fALgo\u001c;bY*\t1\"\u0001\u0002j_\u000e\u00011\u0003\u0002\u0001\u000f)]\u0001\"a\u0004\n\u000e\u0003AQ\u0011!E\u0001\u0006g\u000e\fG.Y\u0005\u0003'A\u0011a!\u00118z%\u00164\u0007CA\b\u0016\u0013\t1\u0002CA\u0004Qe>$Wo\u0019;\u0011\u0005=A\u0012BA\r\u0011\u00051\u0019VM]5bY&T\u0018M\u00197f\u0011\u0015Y\u0002\u0001\"\u0001\u001d\u0003\u0019a\u0014N\\5u}Q\tQ\u0004\u0005\u0002\u001f\u00015\t!\u0001C\u0003!\u0001\u0019\u0005\u0011%A\u0006qe\u0016\u0004\u0018M]3e'FcU#\u0001\u0012\u0011\u0005\r2cBA\b%\u0013\t)\u0003#\u0001\u0004Qe\u0016$WMZ\u0005\u0003O!\u0012aa\u0015;sS:<'BA\u0013\u0011\u0011\u0015Q\u0003A\"\u0001,\u0003%\tX/\u001a:z\u0003J<7/F\u0001-!\ryQfL\u0005\u0003]A\u0011Q!\u0011:sCf\u0004\"a\u0004\u0019\n\u0005E\u0002\"aA!os&\u001a\u0001aM$\u0007\u000bQ*$)!/\u0003\u000bQ\u000b'\r\\3\u0007\u000b\u0005\u0011\u0001\u0012\u0001\u001c\u0014\u0007Urq\u0003C\u0003\u001ck\u0011\u0005\u0001\bF\u0001:!\tqR\u0007C\u0003<k\u0011\u0005A(\u0001\u0005g_J$\u0016M\u00197f)\rit(\u0011\t\u0003}Mj\u0011!\u000e\u0005\u0006\u0001j\u0002\rAI\u0001\u0007g\u000eDW-\\1\t\u000b\tS\u0004\u0019\u0001\u0012\u0002\t9\fW.\u001a\u0005\u0006\tV\"\t!R\u0001\rM>\u0014H+Z7q)\u0006\u0014G.\u001a\u000b\u0004\r\u0006=\u0002C\u0001 H\r\u0011AUGQ%\u0003\u0013Q+W\u000e\u001d+bE2,7\u0003B$\u001e)]A\u0001BQ$\u0003\u0016\u0004%\t!\t\u0005\t\u0019\u001e\u0013\t\u0012)A\u0005E\u0005)a.Y7fA!)1d\u0012C\u0001\u001dR\u0011ai\u0014\u0005\u0006\u00056\u0003\rA\t\u0005\tA\u001dC)\u0019!C!C!A!k\u0012E\u0001B\u0003&!%\u0001\u0007qe\u0016\u0004\u0018M]3e'Fc\u0005\u0005\u0003\u0005+\u000f\"\u0015\r\u0011\"\u0011,\u0011!)v\t#A!B\u0013a\u0013AC9vKJL\u0018I]4tA!Aqk\u0012EC\u0002\u0013\u0005\u0013%\u0001\u0005u_N#(/\u001b8h\u0011!Iv\t#A!B\u0013\u0011\u0013!\u0003;p'R\u0014\u0018N\\4!\u0011\u001dYv)!A\u0005\u0002q\u000bAaY8qsR\u0011a)\u0018\u0005\b\u0005j\u0003\n\u00111\u0001#\u0011\u001dyv)%A\u0005\u0002\u0001\fabY8qs\u0012\"WMZ1vYR$\u0013'F\u0001bU\t\u0011#mK\u0001d!\t!\u0017.D\u0001f\u0015\t1w-A\u0005v]\u000eDWmY6fI*\u0011\u0001\u000eE\u0001\u000bC:tw\u000e^1uS>t\u0017B\u00016f\u0005E)hn\u00195fG.,GMV1sS\u0006t7-\u001a\u0005\bY\u001e\u000b\t\u0011\"\u0011n\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\ta\u000e\u0005\u0002pi6\t\u0001O\u0003\u0002re\u0006!A.\u00198h\u0015\u0005\u0019\u0018\u0001\u00026bm\u0006L!a\n9\t\u000fY<\u0015\u0011!C\u0001o\u0006a\u0001O]8ek\u000e$\u0018I]5usV\t\u0001\u0010\u0005\u0002\u0010s&\u0011!\u0010\u0005\u0002\u0004\u0013:$\bb\u0002?H\u0003\u0003%\t!`\u0001\u000faJ|G-^2u\u000b2,W.\u001a8u)\tyc\u0010C\u0004\u0000w\u0006\u0005\t\u0019\u0001=\u0002\u0007a$\u0013\u0007C\u0005\u0002\u0004\u001d\u000b\t\u0011\"\u0011\u0002\u0006\u0005y\u0001O]8ek\u000e$\u0018\n^3sCR|'/\u0006\u0002\u0002\bA)\u0011\u0011BA\b_5\u0011\u00111\u0002\u0006\u0004\u0003\u001b\u0001\u0012AC2pY2,7\r^5p]&!\u0011\u0011CA\u0006\u0005!IE/\u001a:bi>\u0014\b\"CA\u000b\u000f\u0006\u0005I\u0011AA\f\u0003!\u0019\u0017M\\#rk\u0006dG\u0003BA\r\u0003?\u00012aDA\u000e\u0013\r\ti\u0002\u0005\u0002\b\u0005>|G.Z1o\u0011!y\u00181CA\u0001\u0002\u0004y\u0003\"CA\u0012\u000f\u0006\u0005I\u0011IA\u0013\u0003!A\u0017m\u001d5D_\u0012,G#\u0001=\t\u0013\u0005%r)!A\u0005B\u0005-\u0012AB3rk\u0006d7\u000f\u0006\u0003\u0002\u001a\u00055\u0002\u0002C@\u0002(\u0005\u0005\t\u0019A\u0018\t\u000b\t\u001b\u0005\u0019\u0001\u0012\t\u000f\u0005MR\u0007\"\u0003\u00026\u0005qa/\u00197jI\u0006$XmU2iK6\fG\u0003BA\u001c\u0003{\u00012aDA\u001d\u0013\r\tY\u0004\u0005\u0002\u0005+:LG\u000f\u0003\u0004A\u0003c\u0001\rA\t\u0005\b\u0003\u0003*D\u0011BA\"\u000311\u0018\r\\5eCR,g*Y7f)\u0011\t9$!\u0012\t\r\t\u000by\u00041\u0001#\u000f%\tI%NA\u0001\u0012\u0003\tY%A\u0003UC\ndW\rE\u0002?\u0003\u001b2\u0001\u0002N\u001b\u0002\u0002#\u0005\u0011qJ\n\u0006\u0003\u001b\n\tf\u0006\t\b\u0003'\nIF\t\u0012>\u001b\t\t)FC\u0002\u0002XA\tqA];oi&lW-\u0003\u0003\u0002\\\u0005U#!E!cgR\u0014\u0018m\u0019;Gk:\u001cG/[8oe!91$!\u0014\u0005\u0002\u0005}CCAA&\u0011%9\u0016QJA\u0001\n\u000b\n\u0019\u0007F\u0001o\u0011)\t9'!\u0014\u0002\u0002\u0013\u0005\u0015\u0011N\u0001\u0006CB\u0004H.\u001f\u000b\u0006{\u0005-\u0014Q\u000e\u0005\u0007\u0001\u0006\u0015\u0004\u0019\u0001\u0012\t\r\t\u000b)\u00071\u0001#\u0011)\t\t(!\u0014\u0002\u0002\u0013\u0005\u00151O\u0001\bk:\f\u0007\u000f\u001d7z)\u0011\t)(!!\u0011\u000b=\t9(a\u001f\n\u0007\u0005e\u0004C\u0001\u0004PaRLwN\u001c\t\u0006\u001f\u0005u$EI\u0005\u0004\u0003\u007f\u0002\"A\u0002+va2,'\u0007C\u0005\u0002\u0004\u0006=\u0014\u0011!a\u0001{\u0005\u0019\u0001\u0010\n\u0019\t\u0015\u0005\u001d\u0015QJA\u0001\n\u0013\tI)A\u0006sK\u0006$'+Z:pYZ,GCAAF!\ry\u0017QR\u0005\u0004\u0003\u001f\u0003(AB(cU\u0016\u001cGoB\u0005\u0002\u0014V\n\t\u0011#\u0001\u0002\u0016\u0006IA+Z7q)\u0006\u0014G.\u001a\t\u0004}\u0005]e\u0001\u0003%6\u0003\u0003E\t!!'\u0014\u000b\u0005]\u00151T\f\u0011\r\u0005M\u0013Q\u0014\u0012G\u0013\u0011\ty*!\u0016\u0003#\u0005\u00137\u000f\u001e:bGR4UO\\2uS>t\u0017\u0007C\u0004\u001c\u0003/#\t!a)\u0015\u0005\u0005U\u0005\"C,\u0002\u0018\u0006\u0005IQIA2\u0011)\t9'a&\u0002\u0002\u0013\u0005\u0015\u0011\u0016\u000b\u0004\r\u0006-\u0006B\u0002\"\u0002(\u0002\u0007!\u0005\u0003\u0006\u0002r\u0005]\u0015\u0011!CA\u0003_#B!!-\u00024B!q\"a\u001e#\u0011%\t\u0019)!,\u0002\u0002\u0003\u0007a\t\u0003\u0006\u0002\b\u0006]\u0015\u0011!C\u0005\u0003\u0013C\u0011\"a\"6\u0003\u0003%I!!#\u0014\tMjBc\u0006\u0005\t\u0001N\u0012)\u001a!C\u0001C!I\u0011qX\u001a\u0003\u0012\u0003\u0006IAI\u0001\bg\u000eDW-\\1!\u0011!\u00115G!f\u0001\n\u0003\t\u0003\u0002\u0003'4\u0005#\u0005\u000b\u0011\u0002\u0012\t\rm\u0019D\u0011AAd)\u0015i\u0014\u0011ZAf\u0011\u0019\u0001\u0015Q\u0019a\u0001E!1!)!2A\u0002\tB\u0001\u0002I\u001a\t\u0006\u0004%\t%\t\u0005\t%NB\t\u0011)Q\u0005E!A!f\rEC\u0002\u0013\u00053\u0006\u0003\u0005Vg!\u0005\t\u0015)\u0003-\u0011!96\u0007#b\u0001\n\u0003\n\u0003\u0002C-4\u0011\u0003\u0005\u000b\u0015\u0002\u0012\t\u0011m\u001b\u0014\u0011!C\u0001\u00037$R!PAo\u0003?D\u0001\u0002QAm!\u0003\u0005\rA\t\u0005\t\u0005\u0006e\u0007\u0013!a\u0001E!9qlMI\u0001\n\u0003\u0001\u0007\u0002CAsgE\u0005I\u0011\u00011\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%e!9AnMA\u0001\n\u0003j\u0007b\u0002<4\u0003\u0003%\ta\u001e\u0005\tyN\n\t\u0011\"\u0001\u0002nR\u0019q&a<\t\u0011}\fY/!AA\u0002aD\u0011\"a\u00014\u0003\u0003%\t%!\u0002\t\u0013\u0005U1'!A\u0005\u0002\u0005UH\u0003BA\r\u0003oD\u0001b`Az\u0003\u0003\u0005\ra\f\u0005\n\u0003G\u0019\u0014\u0011!C!\u0003KA\u0011\"!\u000b4\u0003\u0003%\t%!@\u0015\t\u0005e\u0011q \u0005\t\u007f\u0006m\u0018\u0011!a\u0001_\u001d1!1\u0001\u0002\t\u0002e\nac\u0012:fK:\u0004H.^7Rk\u0006d\u0017NZ5fI:\u000bW.\u001a"
)
public abstract class GreenplumQualifiedName implements Product, Serializable {
   public static GreenplumQualifiedName.TempTable forTempTable(String var0) {
      return GreenplumQualifiedName$.MODULE$.forTempTable(var0);
   }

   public static GreenplumQualifiedName.Table forTable(String var0, String var1) {
      return GreenplumQualifiedName$.MODULE$.forTable(var0, var1);
   }

   public Iterator<Object> productIterator() {
      return class.productIterator(this);
   }

   public String productPrefix() {
      return class.productPrefix(this);
   }

   public abstract String preparedSQL();

   public abstract Object[] queryArgs();

   public GreenplumQualifiedName() {
      class.$init$(this);
   }

   public static final class Table extends GreenplumQualifiedName {
      private final String schema;
      private final String name;
      private String preparedSQL;
      private Object[] queryArgs;
      private String toString;
      private volatile byte bitmap$0;

      private String preparedSQL$lzycompute() {
         synchronized(this) {
            if ((byte)(this.bitmap$0 & 1) == 0) {
               this.preparedSQL = "WHERE table_schema = ? AND table_name = ?";
               this.bitmap$0 = (byte)(this.bitmap$0 | 1);
            }

            BoxedUnit var10000 = BoxedUnit.UNIT;
         }

         return this.preparedSQL;
      }

      private Object[] queryArgs$lzycompute() {
         synchronized(this) {
            if ((byte)(this.bitmap$0 & 2) == 0) {
               this.queryArgs = (Object[])scala.Array..MODULE$.apply(scala.Predef..MODULE$.genericWrapArray(new Object[]{this.schema(), this.name()}), scala.reflect.ClassTag..MODULE$.Any());
               this.bitmap$0 = (byte)(this.bitmap$0 | 2);
            }

            BoxedUnit var10000 = BoxedUnit.UNIT;
         }

         return this.queryArgs;
      }

      private String toString$lzycompute() {
         synchronized(this) {
            if ((byte)(this.bitmap$0 & 4) == 0) {
               this.toString = (new StringContext(scala.Predef..MODULE$.wrapRefArray((Object[])(new String[]{"", ".", ""})))).s(scala.Predef..MODULE$.genericWrapArray(new Object[]{SqlObjectNameUtils$.MODULE$.escape(this.schema()), SqlObjectNameUtils$.MODULE$.escape(this.name())}));
               this.bitmap$0 = (byte)(this.bitmap$0 | 4);
            }

            BoxedUnit var10000 = BoxedUnit.UNIT;
         }

         return this.toString;
      }

      public String schema() {
         return this.schema;
      }

      public String name() {
         return this.name;
      }

      public String preparedSQL() {
         return (byte)(this.bitmap$0 & 1) == 0 ? this.preparedSQL$lzycompute() : this.preparedSQL;
      }

      public Object[] queryArgs() {
         return (byte)(this.bitmap$0 & 2) == 0 ? this.queryArgs$lzycompute() : this.queryArgs;
      }

      public String toString() {
         return (byte)(this.bitmap$0 & 4) == 0 ? this.toString$lzycompute() : this.toString;
      }

      public GreenplumQualifiedName.Table copy(String schema, String name) {
         return new GreenplumQualifiedName.Table(schema, name);
      }

      public String copy$default$1() {
         return this.schema();
      }

      public String copy$default$2() {
         return this.name();
      }

      public String productPrefix() {
         return "Table";
      }

      public int productArity() {
         return 2;
      }

      public Object productElement(int x$1) {
         String var10000;
         switch(x$1) {
         case 0:
            var10000 = this.schema();
            break;
         case 1:
            var10000 = this.name();
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
         return x$1 instanceof GreenplumQualifiedName.Table;
      }

      public int hashCode() {
         return scala.runtime.ScalaRunTime..MODULE$._hashCode(this);
      }

      public boolean equals(Object x$1) {
         boolean var7;
         if (this != x$1) {
            label59: {
               boolean var3;
               if (x$1 instanceof GreenplumQualifiedName.Table) {
                  var3 = true;
               } else {
                  var3 = false;
               }

               if (var3) {
                  label42: {
                     label41: {
                        label40: {
                           GreenplumQualifiedName.Table var4 = (GreenplumQualifiedName.Table)x$1;
                           String var10000 = this.schema();
                           String var5 = var4.schema();
                           if (var10000 == null) {
                              if (var5 != null) {
                                 break label40;
                              }
                           } else if (!var10000.equals(var5)) {
                              break label40;
                           }

                           var10000 = this.name();
                           String var6 = var4.name();
                           if (var10000 == null) {
                              if (var6 == null) {
                                 break label41;
                              }
                           } else if (var10000.equals(var6)) {
                              break label41;
                           }
                        }

                        var7 = false;
                        break label42;
                     }

                     var7 = true;
                  }

                  if (var7) {
                     break label59;
                  }
               }

               var7 = false;
               return var7;
            }
         }

         var7 = true;
         return var7;
      }

      public Table(String schema, String name) {
         this.schema = schema;
         this.name = name;
      }
   }

   public static class Table$ extends AbstractFunction2<String, String, GreenplumQualifiedName.Table> implements Serializable {
      public static final GreenplumQualifiedName.Table$ MODULE$;

      static {
         new GreenplumQualifiedName.Table$();
      }

      public final String toString() {
         return "Table";
      }

      public GreenplumQualifiedName.Table apply(String schema, String name) {
         return new GreenplumQualifiedName.Table(schema, name);
      }

      public Option<Tuple2<String, String>> unapply(GreenplumQualifiedName.Table x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple2(x$0.schema(), x$0.name())));
      }

      private Object readResolve() {
         return MODULE$;
      }

      public Table$() {
         MODULE$ = this;
      }
   }

   public static final class TempTable extends GreenplumQualifiedName {
      private final String name;
      private String preparedSQL;
      private Object[] queryArgs;
      private String toString;
      private volatile byte bitmap$0;

      private String preparedSQL$lzycompute() {
         synchronized(this) {
            if ((byte)(this.bitmap$0 & 1) == 0) {
               this.preparedSQL = "WHERE table_name = ?";
               this.bitmap$0 = (byte)(this.bitmap$0 | 1);
            }

            BoxedUnit var10000 = BoxedUnit.UNIT;
         }

         return this.preparedSQL;
      }

      private Object[] queryArgs$lzycompute() {
         synchronized(this) {
            if ((byte)(this.bitmap$0 & 2) == 0) {
               this.queryArgs = (Object[])scala.Array..MODULE$.apply(scala.Predef..MODULE$.genericWrapArray(new Object[]{this.name()}), scala.reflect.ClassTag..MODULE$.Any());
               this.bitmap$0 = (byte)(this.bitmap$0 | 2);
            }

            BoxedUnit var10000 = BoxedUnit.UNIT;
         }

         return this.queryArgs;
      }

      private String toString$lzycompute() {
         synchronized(this) {
            if ((byte)(this.bitmap$0 & 4) == 0) {
               this.toString = (new StringContext(scala.Predef..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).s(scala.Predef..MODULE$.genericWrapArray(new Object[]{SqlObjectNameUtils$.MODULE$.escape(this.name())}));
               this.bitmap$0 = (byte)(this.bitmap$0 | 4);
            }

            BoxedUnit var10000 = BoxedUnit.UNIT;
         }

         return this.toString;
      }

      public String name() {
         return this.name;
      }

      public String preparedSQL() {
         return (byte)(this.bitmap$0 & 1) == 0 ? this.preparedSQL$lzycompute() : this.preparedSQL;
      }

      public Object[] queryArgs() {
         return (byte)(this.bitmap$0 & 2) == 0 ? this.queryArgs$lzycompute() : this.queryArgs;
      }

      public String toString() {
         return (byte)(this.bitmap$0 & 4) == 0 ? this.toString$lzycompute() : this.toString;
      }

      public GreenplumQualifiedName.TempTable copy(String name) {
         return new GreenplumQualifiedName.TempTable(name);
      }

      public String copy$default$1() {
         return this.name();
      }

      public String productPrefix() {
         return "TempTable";
      }

      public int productArity() {
         return 1;
      }

      public Object productElement(int x$1) {
         switch(x$1) {
         case 0:
            return this.name();
         default:
            throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
         }
      }

      public Iterator<Object> productIterator() {
         return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(Object x$1) {
         return x$1 instanceof GreenplumQualifiedName.TempTable;
      }

      public int hashCode() {
         return scala.runtime.ScalaRunTime..MODULE$._hashCode(this);
      }

      public boolean equals(Object x$1) {
         boolean var6;
         if (this != x$1) {
            label50: {
               boolean var3;
               if (x$1 instanceof GreenplumQualifiedName.TempTable) {
                  var3 = true;
               } else {
                  var3 = false;
               }

               if (var3) {
                  label33: {
                     label32: {
                        GreenplumQualifiedName.TempTable var4 = (GreenplumQualifiedName.TempTable)x$1;
                        String var10000 = this.name();
                        String var5 = var4.name();
                        if (var10000 == null) {
                           if (var5 == null) {
                              break label32;
                           }
                        } else if (var10000.equals(var5)) {
                           break label32;
                        }

                        var6 = false;
                        break label33;
                     }

                     var6 = true;
                  }

                  if (var6) {
                     break label50;
                  }
               }

               var6 = false;
               return var6;
            }
         }

         var6 = true;
         return var6;
      }

      public TempTable(String name) {
         this.name = name;
      }
   }

   public static class TempTable$ extends AbstractFunction1<String, GreenplumQualifiedName.TempTable> implements Serializable {
      public static final GreenplumQualifiedName.TempTable$ MODULE$;

      static {
         new GreenplumQualifiedName.TempTable$();
      }

      public final String toString() {
         return "TempTable";
      }

      public GreenplumQualifiedName.TempTable apply(String name) {
         return new GreenplumQualifiedName.TempTable(name);
      }

      public Option<String> unapply(GreenplumQualifiedName.TempTable x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(x$0.name()));
      }

      private Object readResolve() {
         return MODULE$;
      }

      public TempTable$() {
         MODULE$ = this;
      }
   }
}
