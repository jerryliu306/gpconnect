package io.pivotal.greenplum.spark.jdbc;

import scala.Function1;
import scala.Option;
import scala.Product;
import scala.Serializable;
import scala.Tuple2;
import scala.Product.class;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0001\u0005Eb\u0001B\u0001\u0003\u00016\u0011\u0001cQ8mk6tg+\u00197vKJ\u000bgnZ3\u000b\u0005\r!\u0011\u0001\u00026eE\u000eT!!\u0002\u0004\u0002\u000bM\u0004\u0018M]6\u000b\u0005\u001dA\u0011!C4sK\u0016t\u0007\u000f\\;n\u0015\tI!\"A\u0004qSZ|G/\u00197\u000b\u0003-\t!![8\u0004\u0001M!\u0001A\u0004\u000b\u0018!\ty!#D\u0001\u0011\u0015\u0005\t\u0012!B:dC2\f\u0017BA\n\u0011\u0005\u0019\te.\u001f*fMB\u0011q\"F\u0005\u0003-A\u0011q\u0001\u0015:pIV\u001cG\u000f\u0005\u0002\u00101%\u0011\u0011\u0004\u0005\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0005\t7\u0001\u0011)\u001a!C\u00019\u0005\u0019Q.\u001b8\u0016\u0003u\u0001\"a\u0004\u0010\n\u0005}\u0001\"\u0001\u0002'p]\u001eD\u0001\"\t\u0001\u0003\u0012\u0003\u0006I!H\u0001\u0005[&t\u0007\u0005\u0003\u0005$\u0001\tU\r\u0011\"\u0001\u001d\u0003\ri\u0017\r\u001f\u0005\tK\u0001\u0011\t\u0012)A\u0005;\u0005!Q.\u0019=!\u0011\u00159\u0003\u0001\"\u0001)\u0003\u0019a\u0014N\\5u}Q\u0019\u0011f\u000b\u0017\u0011\u0005)\u0002Q\"\u0001\u0002\t\u000bm1\u0003\u0019A\u000f\t\u000b\r2\u0003\u0019A\u000f\t\u000f9\u0002\u0011\u0011!C\u0001_\u0005!1m\u001c9z)\rI\u0003'\r\u0005\b75\u0002\n\u00111\u0001\u001e\u0011\u001d\u0019S\u0006%AA\u0002uAqa\r\u0001\u0012\u0002\u0013\u0005A'\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u0019\u0016\u0003UR#!\b\u001c,\u0003]\u0002\"\u0001O\u001f\u000e\u0003eR!AO\u001e\u0002\u0013Ut7\r[3dW\u0016$'B\u0001\u001f\u0011\u0003)\tgN\\8uCRLwN\\\u0005\u0003}e\u0012\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u0011\u001d\u0001\u0005!%A\u0005\u0002Q\nabY8qs\u0012\"WMZ1vYR$#\u0007C\u0004C\u0001\u0005\u0005I\u0011I\"\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\u0005!\u0005CA#K\u001b\u00051%BA$I\u0003\u0011a\u0017M\\4\u000b\u0003%\u000bAA[1wC&\u00111J\u0012\u0002\u0007'R\u0014\u0018N\\4\t\u000f5\u0003\u0011\u0011!C\u0001\u001d\u0006a\u0001O]8ek\u000e$\u0018I]5usV\tq\n\u0005\u0002\u0010!&\u0011\u0011\u000b\u0005\u0002\u0004\u0013:$\bbB*\u0001\u0003\u0003%\t\u0001V\u0001\u000faJ|G-^2u\u000b2,W.\u001a8u)\t)\u0006\f\u0005\u0002\u0010-&\u0011q\u000b\u0005\u0002\u0004\u0003:L\bbB-S\u0003\u0003\u0005\raT\u0001\u0004q\u0012\n\u0004bB.\u0001\u0003\u0003%\t\u0005X\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\tQ\fE\u0002_CVk\u0011a\u0018\u0006\u0003AB\t!bY8mY\u0016\u001cG/[8o\u0013\t\u0011wL\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0011\u001d!\u0007!!A\u0005\u0002\u0015\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0003M&\u0004\"aD4\n\u0005!\u0004\"a\u0002\"p_2,\u0017M\u001c\u0005\b3\u000e\f\t\u00111\u0001V\u0011\u001dY\u0007!!A\u0005B1\f\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0002\u001f\"9a\u000eAA\u0001\n\u0003z\u0017\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0003\u0011Cq!\u001d\u0001\u0002\u0002\u0013\u0005#/\u0001\u0004fcV\fGn\u001d\u000b\u0003MNDq!\u00179\u0002\u0002\u0003\u0007QkB\u0004v\u0005\u0005\u0005\t\u0012\u0001<\u0002!\r{G.^7o-\u0006dW/\u001a*b]\u001e,\u0007C\u0001\u0016x\r\u001d\t!!!A\t\u0002a\u001c2a^=\u0018!\u0015QX0H\u000f*\u001b\u0005Y(B\u0001?\u0011\u0003\u001d\u0011XO\u001c;j[\u0016L!A`>\u0003#\u0005\u00137\u000f\u001e:bGR4UO\\2uS>t'\u0007\u0003\u0004(o\u0012\u0005\u0011\u0011\u0001\u000b\u0002m\"9an^A\u0001\n\u000bz\u0007\"CA\u0004o\u0006\u0005I\u0011QA\u0005\u0003\u0015\t\u0007\u000f\u001d7z)\u0015I\u00131BA\u0007\u0011\u0019Y\u0012Q\u0001a\u0001;!11%!\u0002A\u0002uA\u0011\"!\u0005x\u0003\u0003%\t)a\u0005\u0002\u000fUt\u0017\r\u001d9msR!\u0011QCA\u0011!\u0015y\u0011qCA\u000e\u0013\r\tI\u0002\u0005\u0002\u0007\u001fB$\u0018n\u001c8\u0011\u000b=\ti\"H\u000f\n\u0007\u0005}\u0001C\u0001\u0004UkBdWM\r\u0005\n\u0003G\ty!!AA\u0002%\n1\u0001\u001f\u00131\u0011%\t9c^A\u0001\n\u0013\tI#A\u0006sK\u0006$'+Z:pYZ,GCAA\u0016!\r)\u0015QF\u0005\u0004\u0003_1%AB(cU\u0016\u001cG\u000f"
)
public class ColumnValueRange implements Product, Serializable {
   private final long min;
   private final long max;

   public static Option<Tuple2<Object, Object>> unapply(ColumnValueRange var0) {
      return ColumnValueRange$.MODULE$.unapply(var0);
   }

   public static ColumnValueRange apply(long var0, long var2) {
      return ColumnValueRange$.MODULE$.apply(var0, var2);
   }

   public static Function1<Tuple2<Object, Object>, ColumnValueRange> tupled() {
      return ColumnValueRange$.MODULE$.tupled();
   }

   public static Function1<Object, Function1<Object, ColumnValueRange>> curried() {
      return ColumnValueRange$.MODULE$.curried();
   }

   public long min() {
      return this.min;
   }

   public long max() {
      return this.max;
   }

   public ColumnValueRange copy(long min, long max) {
      return new ColumnValueRange(min, max);
   }

   public long copy$default$1() {
      return this.min();
   }

   public long copy$default$2() {
      return this.max();
   }

   public String productPrefix() {
      return "ColumnValueRange";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(int x$1) {
      Long var10000;
      switch(x$1) {
      case 0:
         var10000 = BoxesRunTime.boxToLong(this.min());
         break;
      case 1:
         var10000 = BoxesRunTime.boxToLong(this.max());
         break;
      default:
         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
      }

      return var10000;
   }

   public Iterator<Object> productIterator() {
      return .MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(Object x$1) {
      return x$1 instanceof ColumnValueRange;
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, Statics.longHash(this.min()));
      var1 = Statics.mix(var1, Statics.longHash(this.max()));
      return Statics.finalizeHash(var1, 2);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(Object x$1) {
      boolean var10000;
      if (this != x$1) {
         label50: {
            boolean var3;
            if (x$1 instanceof ColumnValueRange) {
               var3 = true;
            } else {
               var3 = false;
            }

            if (var3) {
               ColumnValueRange var4 = (ColumnValueRange)x$1;
               if (this.min() == var4.min() && this.max() == var4.max() && var4.canEqual(this)) {
                  break label50;
               }
            }

            var10000 = false;
            return var10000;
         }
      }

      var10000 = true;
      return var10000;
   }

   public ColumnValueRange(long min, long max) {
      this.min = min;
      this.max = max;
      class.$init$(this);
   }
}
