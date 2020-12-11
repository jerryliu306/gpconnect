package io.pivotal.greenplum.spark.externaltable;

import scala.Function1;
import scala.Option;
import scala.Product;
import scala.Serializable;
import scala.Tuple3;
import scala.Product.class;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0001\u0005md\u0001B\u0001\u0003\u00016\u0011AbV3c\u000bb\u001cW\r\u001d;j_:T!a\u0001\u0003\u0002\u001b\u0015DH/\u001a:oC2$\u0018M\u00197f\u0015\t)a!A\u0003ta\u0006\u00148N\u0003\u0002\b\u0011\u0005IqM]3f]BdW/\u001c\u0006\u0003\u0013)\tq\u0001]5w_R\fGNC\u0001\f\u0003\tIwn\u0001\u0001\u0014\t\u0001qA\u0004\t\t\u0003\u001feq!\u0001\u0005\f\u000f\u0005E!R\"\u0001\n\u000b\u0005Ma\u0011A\u0002\u001fs_>$h(C\u0001\u0016\u0003\u0015\u00198-\u00197b\u0013\t9\u0002$A\u0004qC\u000e\\\u0017mZ3\u000b\u0003UI!AG\u000e\u0003\u0013\u0015C8-\u001a9uS>t'BA\f\u0019!\tib$D\u0001\u0019\u0013\ty\u0002DA\u0004Qe>$Wo\u0019;\u0011\u0005u\t\u0013B\u0001\u0012\u0019\u00051\u0019VM]5bY&T\u0018M\u00197f\u0011!!\u0003A!f\u0001\n\u0003)\u0013\u0001B2pI\u0016,\u0012A\n\t\u0003;\u001dJ!\u0001\u000b\r\u0003\u0007%sG\u000f\u0003\u0005+\u0001\tE\t\u0015!\u0003'\u0003\u0015\u0019w\u000eZ3!\u0011!a\u0003A!f\u0001\n\u0003i\u0013aB7fgN\fw-Z\u000b\u0002]A\u0011qF\r\b\u0003;AJ!!\r\r\u0002\rA\u0013X\rZ3g\u0013\t\u0019DG\u0001\u0004TiJLgn\u001a\u0006\u0003caA\u0001B\u000e\u0001\u0003\u0012\u0003\u0006IAL\u0001\t[\u0016\u001c8/Y4fA!A\u0001\b\u0001BK\u0002\u0013\u0005\u0011(A\u0003dCV\u001cX-F\u0001;!\ri2(P\u0005\u0003ya\u0011aa\u00149uS>t\u0007CA\b?\u0013\ty4DA\u0005UQJ|w/\u00192mK\"A\u0011\t\u0001B\tB\u0003%!(\u0001\u0004dCV\u001cX\r\t\u0005\u0006\u0007\u0002!\t\u0001R\u0001\u0007y%t\u0017\u000e\u001e \u0015\t\u0015;\u0005*\u0013\t\u0003\r\u0002i\u0011A\u0001\u0005\u0006I\t\u0003\rA\n\u0005\bY\t\u0003\n\u00111\u0001/\u0011\u001dA$\t%AA\u0002iBqa\u0013\u0001\u0002\u0002\u0013\u0005A*\u0001\u0003d_BLH\u0003B#N\u001d>Cq\u0001\n&\u0011\u0002\u0003\u0007a\u0005C\u0004-\u0015B\u0005\t\u0019\u0001\u0018\t\u000faR\u0005\u0013!a\u0001u!9\u0011\u000bAI\u0001\n\u0003\u0011\u0016AD2paf$C-\u001a4bk2$H%M\u000b\u0002'*\u0012a\u0005V\u0016\u0002+B\u0011akW\u0007\u0002/*\u0011\u0001,W\u0001\nk:\u001c\u0007.Z2lK\u0012T!A\u0017\r\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002]/\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\t\u000fy\u0003\u0011\u0013!C\u0001?\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\u0012T#\u00011+\u00059\"\u0006b\u00022\u0001#\u0003%\taY\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00134+\u0005!'F\u0001\u001eU\u0011\u001d1\u0007!!A\u0005B\u001d\fQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DX#\u00015\u0011\u0005%tW\"\u00016\u000b\u0005-d\u0017\u0001\u00027b]\u001eT\u0011!\\\u0001\u0005U\u00064\u0018-\u0003\u00024U\"9\u0001\u000fAA\u0001\n\u0003)\u0013\u0001\u00049s_\u0012,8\r^!sSRL\bb\u0002:\u0001\u0003\u0003%\ta]\u0001\u000faJ|G-^2u\u000b2,W.\u001a8u)\t!x\u000f\u0005\u0002\u001ek&\u0011a\u000f\u0007\u0002\u0004\u0003:L\bb\u0002=r\u0003\u0003\u0005\rAJ\u0001\u0004q\u0012\n\u0004b\u0002>\u0001\u0003\u0003%\te_\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\tA\u0010\u0005\u0003~\u0003\u0003!X\"\u0001@\u000b\u0005}D\u0012AC2pY2,7\r^5p]&\u0019\u00111\u0001@\u0003\u0011%#XM]1u_JD\u0011\"a\u0002\u0001\u0003\u0003%\t!!\u0003\u0002\u0011\r\fg.R9vC2$B!a\u0003\u0002\u0012A\u0019Q$!\u0004\n\u0007\u0005=\u0001DA\u0004C_>dW-\u00198\t\u0011a\f)!!AA\u0002QD\u0011\"!\u0006\u0001\u0003\u0003%\t%a\u0006\u0002\u0011!\f7\u000f[\"pI\u0016$\u0012A\n\u0005\n\u00037\u0001\u0011\u0011!C!\u0003;\ta!Z9vC2\u001cH\u0003BA\u0006\u0003?A\u0001\u0002_A\r\u0003\u0003\u0005\r\u0001^\u0004\n\u0003G\u0011\u0011\u0011!E\u0001\u0003K\tAbV3c\u000bb\u001cW\r\u001d;j_:\u00042ARA\u0014\r!\t!!!A\t\u0002\u0005%2#BA\u0014\u0003W\u0001\u0003\u0003CA\u0017\u0003g1cFO#\u000e\u0005\u0005=\"bAA\u00191\u00059!/\u001e8uS6,\u0017\u0002BA\u001b\u0003_\u0011\u0011#\u00112tiJ\f7\r\u001e$v]\u000e$\u0018n\u001c84\u0011\u001d\u0019\u0015q\u0005C\u0001\u0003s!\"!!\n\t\u0015\u0005u\u0012qEA\u0001\n\u000b\ny$\u0001\u0005u_N#(/\u001b8h)\u0005A\u0007BCA\"\u0003O\t\t\u0011\"!\u0002F\u0005)\u0011\r\u001d9msR9Q)a\u0012\u0002J\u0005-\u0003B\u0002\u0013\u0002B\u0001\u0007a\u0005\u0003\u0005-\u0003\u0003\u0002\n\u00111\u0001/\u0011!A\u0014\u0011\tI\u0001\u0002\u0004Q\u0004BCA(\u0003O\t\t\u0011\"!\u0002R\u00059QO\\1qa2LH\u0003BA*\u00037\u0002B!H\u001e\u0002VA1Q$a\u0016']iJ1!!\u0017\u0019\u0005\u0019!V\u000f\u001d7fg!I\u0011QLA'\u0003\u0003\u0005\r!R\u0001\u0004q\u0012\u0002\u0004\"CA1\u0003O\t\n\u0011\"\u0001`\u0003m!C.Z:tS:LG\u000fJ4sK\u0006$XM\u001d\u0013eK\u001a\fW\u000f\u001c;%e!I\u0011QMA\u0014#\u0003%\taY\u0001\u001cI1,7o]5oSR$sM]3bi\u0016\u0014H\u0005Z3gCVdG\u000fJ\u001a\t\u0013\u0005%\u0014qEI\u0001\n\u0003y\u0016aD1qa2LH\u0005Z3gCVdG\u000f\n\u001a\t\u0013\u00055\u0014qEI\u0001\n\u0003\u0019\u0017aD1qa2LH\u0005Z3gCVdG\u000fJ\u001a\t\u0015\u0005E\u0014qEA\u0001\n\u0013\t\u0019(A\u0006sK\u0006$'+Z:pYZ,GCAA;!\rI\u0017qO\u0005\u0004\u0003sR'AB(cU\u0016\u001cG\u000f"
)
public class WebException extends Exception implements Product, Serializable {
   private final int code;
   private final String message;
   private final Option<Throwable> cause;

   public static Option<Throwable> apply$default$3() {
      return WebException$.MODULE$.apply$default$3();
   }

   public static String apply$default$2() {
      return WebException$.MODULE$.apply$default$2();
   }

   public static Option<Throwable> $lessinit$greater$default$3() {
      return WebException$.MODULE$.$lessinit$greater$default$3();
   }

   public static String $lessinit$greater$default$2() {
      return WebException$.MODULE$.$lessinit$greater$default$2();
   }

   public static Option<Tuple3<Object, String, Option<Throwable>>> unapply(WebException var0) {
      return WebException$.MODULE$.unapply(var0);
   }

   public static WebException apply(int var0, String var1, Option<Throwable> var2) {
      return WebException$.MODULE$.apply(var0, var1, var2);
   }

   public static Function1<Tuple3<Object, String, Option<Throwable>>, WebException> tupled() {
      return WebException$.MODULE$.tupled();
   }

   public static Function1<Object, Function1<String, Function1<Option<Throwable>, WebException>>> curried() {
      return WebException$.MODULE$.curried();
   }

   public int code() {
      return this.code;
   }

   public String message() {
      return this.message;
   }

   public Option<Throwable> cause() {
      return this.cause;
   }

   public WebException copy(int code, String message, Option<Throwable> cause) {
      return new WebException(code, message, cause);
   }

   public int copy$default$1() {
      return this.code();
   }

   public String copy$default$2() {
      return this.message();
   }

   public Option<Throwable> copy$default$3() {
      return this.cause();
   }

   public String productPrefix() {
      return "WebException";
   }

   public int productArity() {
      return 3;
   }

   public Object productElement(int x$1) {
      Object var10000;
      switch(x$1) {
      case 0:
         var10000 = BoxesRunTime.boxToInteger(this.code());
         break;
      case 1:
         var10000 = this.message();
         break;
      case 2:
         var10000 = this.cause();
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
      return x$1 instanceof WebException;
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.code());
      var1 = Statics.mix(var1, Statics.anyHash(this.message()));
      var1 = Statics.mix(var1, Statics.anyHash(this.cause()));
      return Statics.finalizeHash(var1, 3);
   }

   public boolean equals(Object x$1) {
      boolean var8;
      if (this != x$1) {
         label64: {
            boolean var3;
            if (x$1 instanceof WebException) {
               var3 = true;
            } else {
               var3 = false;
            }

            if (var3) {
               label46: {
                  WebException var4 = (WebException)x$1;
                  if (this.code() == var4.code()) {
                     label55: {
                        String var10000 = this.message();
                        String var5 = var4.message();
                        if (var10000 == null) {
                           if (var5 != null) {
                              break label55;
                           }
                        } else if (!var10000.equals(var5)) {
                           break label55;
                        }

                        Option var7 = this.cause();
                        Option var6 = var4.cause();
                        if (var7 == null) {
                           if (var6 != null) {
                              break label55;
                           }
                        } else if (!var7.equals(var6)) {
                           break label55;
                        }

                        if (var4.canEqual(this)) {
                           var8 = true;
                           break label46;
                        }
                     }
                  }

                  var8 = false;
               }

               if (var8) {
                  break label64;
               }
            }

            var8 = false;
            return var8;
         }
      }

      var8 = true;
      return var8;
   }

   public WebException(int code, String message, Option<Throwable> cause) {
      super(message);
      this.code = code;
      this.message = message;
      this.cause = cause;
      class.$init$(this);
   }
}
