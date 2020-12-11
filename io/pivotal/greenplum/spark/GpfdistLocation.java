package io.pivotal.greenplum.spark;

import com.typesafe.scalalogging.LazyLogging;
import com.typesafe.scalalogging.LazyLogging$class;
import com.typesafe.scalalogging.Logger;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.Serializable;
import scala.StringContext;
import scala.Tuple2;
import scala.Predef.;
import scala.Product.class;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0001\u0005Uc\u0001B\u0001\u0003\u0001.\u0011qb\u00129gI&\u001cH\u000fT8dCRLwN\u001c\u0006\u0003\u0007\u0011\tQa\u001d9be.T!!\u0002\u0004\u0002\u0013\u001d\u0014X-\u001a8qYVl'BA\u0004\t\u0003\u001d\u0001\u0018N^8uC2T\u0011!C\u0001\u0003S>\u001c\u0001aE\u0003\u0001\u0019Iar\u0004\u0005\u0002\u000e!5\taBC\u0001\u0010\u0003\u0015\u00198-\u00197b\u0013\t\tbB\u0001\u0004B]f\u0014VM\u001a\t\u0003'ii\u0011\u0001\u0006\u0006\u0003+Y\tAb]2bY\u0006dwnZ4j]\u001eT!a\u0006\r\u0002\u0011QL\b/Z:bM\u0016T\u0011!G\u0001\u0004G>l\u0017BA\u000e\u0015\u0005-a\u0015M_=M_\u001e<\u0017N\\4\u0011\u00055i\u0012B\u0001\u0010\u000f\u0005\u001d\u0001&o\u001c3vGR\u0004\"!\u0004\u0011\n\u0005\u0005r!\u0001D*fe&\fG.\u001b>bE2,\u0007\u0002C\u0012\u0001\u0005+\u0007I\u0011\u0001\u0013\u0002\rM,'O^3s+\u0005)\u0003C\u0001\u0014*\u001d\tiq%\u0003\u0002)\u001d\u00051\u0001K]3eK\u001aL!AK\u0016\u0003\rM#(/\u001b8h\u0015\tAc\u0002\u0003\u0005.\u0001\tE\t\u0015!\u0003&\u0003\u001d\u0019XM\u001d<fe\u0002B\u0001b\f\u0001\u0003\u0016\u0004%\t\u0001M\u0001\u0005a>\u0014H/F\u00012!\ti!'\u0003\u00024\u001d\t\u0019\u0011J\u001c;\t\u0011U\u0002!\u0011#Q\u0001\nE\nQ\u0001]8si\u0002BQa\u000e\u0001\u0005\u0002a\na\u0001P5oSRtDcA\u001d<yA\u0011!\bA\u0007\u0002\u0005!)1E\u000ea\u0001K!)qF\u000ea\u0001c!)a\b\u0001C\u0001\u007f\u0005Aq-\u001a8fe\u0006$X\r\u0006\u0002&\u0001\")\u0011)\u0010a\u0001K\u0005!\u0001/\u0019;i\u0011\u001d\u0019\u0005!!A\u0005\u0002\u0011\u000bAaY8qsR\u0019\u0011(\u0012$\t\u000f\r\u0012\u0005\u0013!a\u0001K!9qF\u0011I\u0001\u0002\u0004\t\u0004b\u0002%\u0001#\u0003%\t!S\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\u0005Q%FA\u0013LW\u0005a\u0005CA'S\u001b\u0005q%BA(Q\u0003%)hn\u00195fG.,GM\u0003\u0002R\u001d\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005Ms%!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\"9Q\u000bAI\u0001\n\u00031\u0016AD2paf$C-\u001a4bk2$HEM\u000b\u0002/*\u0012\u0011g\u0013\u0005\b3\u0002\t\t\u0011\"\u0011[\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\t1\f\u0005\u0002]C6\tQL\u0003\u0002_?\u0006!A.\u00198h\u0015\u0005\u0001\u0017\u0001\u00026bm\u0006L!AK/\t\u000f\r\u0004\u0011\u0011!C\u0001a\u0005a\u0001O]8ek\u000e$\u0018I]5us\"9Q\rAA\u0001\n\u00031\u0017A\u00049s_\u0012,8\r^#mK6,g\u000e\u001e\u000b\u0003O*\u0004\"!\u00045\n\u0005%t!aA!os\"91\u000eZA\u0001\u0002\u0004\t\u0014a\u0001=%c!9Q\u000eAA\u0001\n\u0003r\u0017a\u00049s_\u0012,8\r^%uKJ\fGo\u001c:\u0016\u0003=\u00042\u0001]:h\u001b\u0005\t(B\u0001:\u000f\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0003iF\u0014\u0001\"\u0013;fe\u0006$xN\u001d\u0005\bm\u0002\t\t\u0011\"\u0001x\u0003!\u0019\u0017M\\#rk\u0006dGC\u0001=|!\ti\u00110\u0003\u0002{\u001d\t9!i\\8mK\u0006t\u0007bB6v\u0003\u0003\u0005\ra\u001a\u0005\b{\u0002\t\t\u0011\"\u0011\u007f\u0003!A\u0017m\u001d5D_\u0012,G#A\u0019\t\u0013\u0005\u0005\u0001!!A\u0005B\u0005\r\u0011\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0003mC\u0011\"a\u0002\u0001\u0003\u0003%\t%!\u0003\u0002\r\u0015\fX/\u00197t)\rA\u00181\u0002\u0005\tW\u0006\u0015\u0011\u0011!a\u0001O\u001eI\u0011q\u0002\u0002\u0002\u0002#\u0005\u0011\u0011C\u0001\u0010\u000fB4G-[:u\u0019>\u001c\u0017\r^5p]B\u0019!(a\u0005\u0007\u0011\u0005\u0011\u0011\u0011!E\u0001\u0003+\u0019R!a\u0005\u0002\u0018}\u0001r!!\u0007\u0002 \u0015\n\u0014(\u0004\u0002\u0002\u001c)\u0019\u0011Q\u0004\b\u0002\u000fI,h\u000e^5nK&!\u0011\u0011EA\u000e\u0005E\t%m\u001d;sC\u000e$h)\u001e8di&|gN\r\u0005\bo\u0005MA\u0011AA\u0013)\t\t\t\u0002\u0003\u0006\u0002\u0002\u0005M\u0011\u0011!C#\u0003\u0007A!\"a\u000b\u0002\u0014\u0005\u0005I\u0011QA\u0017\u0003\u0015\t\u0007\u000f\u001d7z)\u0015I\u0014qFA\u0019\u0011\u0019\u0019\u0013\u0011\u0006a\u0001K!1q&!\u000bA\u0002EB!\"!\u000e\u0002\u0014\u0005\u0005I\u0011QA\u001c\u0003\u001d)h.\u00199qYf$B!!\u000f\u0002FA)Q\"a\u000f\u0002@%\u0019\u0011Q\b\b\u0003\r=\u0003H/[8o!\u0015i\u0011\u0011I\u00132\u0013\r\t\u0019E\u0004\u0002\u0007)V\u0004H.\u001a\u001a\t\u0013\u0005\u001d\u00131GA\u0001\u0002\u0004I\u0014a\u0001=%a!Q\u00111JA\n\u0003\u0003%I!!\u0014\u0002\u0017I,\u0017\r\u001a*fg>dg/\u001a\u000b\u0003\u0003\u001f\u00022\u0001XA)\u0013\r\t\u0019&\u0018\u0002\u0007\u001f\nTWm\u0019;"
)
public class GpfdistLocation implements LazyLogging, Product, Serializable {
   private final String server;
   private final int port;
   private final transient Logger logger;
   private transient volatile boolean bitmap$trans$0;

   public static Option<Tuple2<String, Object>> unapply(GpfdistLocation var0) {
      return GpfdistLocation$.MODULE$.unapply(var0);
   }

   public static GpfdistLocation apply(String var0, int var1) {
      return GpfdistLocation$.MODULE$.apply(var0, var1);
   }

   public static Function1<Tuple2<String, Object>, GpfdistLocation> tupled() {
      return GpfdistLocation$.MODULE$.tupled();
   }

   public static Function1<String, Function1<Object, GpfdistLocation>> curried() {
      return GpfdistLocation$.MODULE$.curried();
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

   public String server() {
      return this.server;
   }

   public int port() {
      return this.port;
   }

   public String generate(String path) {
      String uriString = (new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{"gpfdist://", ":", "/", ""})))).s(.MODULE$.genericWrapArray(new Object[]{this.server(), BoxesRunTime.boxToInteger(this.port()), path}));
      BoxedUnit var10000;
      if (this.logger().underlying().isDebugEnabled()) {
         this.logger().underlying().debug("Creating URI string at {}", uriString);
         var10000 = BoxedUnit.UNIT;
      } else {
         var10000 = BoxedUnit.UNIT;
      }

      return uriString;
   }

   public GpfdistLocation copy(String server, int port) {
      return new GpfdistLocation(server, port);
   }

   public String copy$default$1() {
      return this.server();
   }

   public int copy$default$2() {
      return this.port();
   }

   public String productPrefix() {
      return "GpfdistLocation";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(int x$1) {
      Object var10000;
      switch(x$1) {
      case 0:
         var10000 = this.server();
         break;
      case 1:
         var10000 = BoxesRunTime.boxToInteger(this.port());
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
      return x$1 instanceof GpfdistLocation;
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, Statics.anyHash(this.server()));
      var1 = Statics.mix(var1, this.port());
      return Statics.finalizeHash(var1, 2);
   }

   public String toString() {
      return scala.runtime.ScalaRunTime..MODULE$._toString(this);
   }

   public boolean equals(Object x$1) {
      boolean var6;
      label48: {
         if (this != x$1) {
            boolean var3;
            if (x$1 instanceof GpfdistLocation) {
               var3 = true;
            } else {
               var3 = false;
            }

            if (!var3) {
               break label48;
            }

            label36: {
               label35: {
                  GpfdistLocation var4 = (GpfdistLocation)x$1;
                  String var10000 = this.server();
                  String var5 = var4.server();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label35;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label35;
                  }

                  if (this.port() == var4.port() && var4.canEqual(this)) {
                     var6 = true;
                     break label36;
                  }
               }

               var6 = false;
            }

            if (!var6) {
               break label48;
            }
         }

         var6 = true;
         return var6;
      }

      var6 = false;
      return var6;
   }

   public GpfdistLocation(String server, int port) {
      this.server = server;
      this.port = port;
      LazyLogging$class.$init$(this);
      class.$init$(this);
   }
}
