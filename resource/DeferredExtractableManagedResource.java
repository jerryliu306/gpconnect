package resource;

import scala.Function1;
import scala.Option;
import scala.Serializable;
import scala.Tuple2;
import scala.Predef..less.colon.less;
import scala.collection.Traversable;
import scala.collection.TraversableOnce;
import scala.collection.immutable.List;
import scala.collection.mutable.StringBuilder;
import scala.concurrent.ExecutionContext;
import scala.concurrent.Future;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.util.Try;
import scala.util.Try.;

@ScalaSignature(
   bytes = "\u0006\u0001}4a!\u0001\u0002\u0001\u0005\u0011!$A\t#fM\u0016\u0014(/\u001a3FqR\u0014\u0018m\u0019;bE2,W*\u00198bO\u0016$'+Z:pkJ\u001cWMC\u0001\u0004\u0003!\u0011Xm]8ve\u000e,WcA\u0003\u0013KM!\u0001A\u0002\u0007\u001d!\t9!\"D\u0001\t\u0015\u0005I\u0011!B:dC2\f\u0017BA\u0006\t\u0005\u0019\te.\u001f*fMB\u0019QB\u0004\t\u000e\u0003\tI!a\u0004\u0002\u00035\u0015CHO]1di\u0006\u0014G.Z'b]\u0006<W\r\u001a*fg>,(oY3\u0011\u0005E\u0011B\u0002\u0001\u0003\u0007'\u0001!)\u0019A\u000b\u0003\u0003\u0005\u001b\u0001!\u0005\u0002\u00173A\u0011qaF\u0005\u00031!\u0011qAT8uQ&tw\r\u0005\u0002\b5%\u00111\u0004\u0003\u0002\u0004\u0003:L\bcA\u0007\u001e!%\u0011aD\u0001\u0002\u001a\u001b\u0006t\u0017mZ3e%\u0016\u001cx.\u001e:dK>\u0003XM]1uS>t7\u000f\u0003\u0005\u0004\u0001\t\u0015\r\u0011\"\u0001!+\u0005\t\u0003cA\u0007#I%\u00111E\u0001\u0002\u0010\u001b\u0006t\u0017mZ3e%\u0016\u001cx.\u001e:dKB\u0011\u0011#\n\u0003\u0006M\u0001\u0011\r!\u0006\u0002\u0002%\"A\u0001\u0006\u0001B\u0001B\u0003%\u0011%A\u0005sKN|WO]2fA!A!\u0006\u0001BC\u0002\u0013\u00051&A\u0005ue\u0006t7\u000f\\1uKV\tA\u0006\u0005\u0003\b[\u0011\u0002\u0012B\u0001\u0018\t\u0005%1UO\\2uS>t\u0017\u0007\u0003\u00051\u0001\t\u0005\t\u0015!\u0003-\u0003)!(/\u00198tY\u0006$X\r\t\u0005\u0006e\u0001!\taM\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0007Q*d\u0007\u0005\u0003\u000e\u0001A!\u0003\"B\u00022\u0001\u0004\t\u0003\"\u0002\u00162\u0001\u0004a\u0003\"\u0002\u001d\u0001\t\u0003J\u0014AC1dcVL'/\u001a$peV\u0011!H\u0014\u000b\u0003wA\u0003B!\u0004\u001f?\u001b&\u0011QH\u0001\u0002\u0010\u000bb$(/Y2uK\u0012,\u0015\u000e\u001e5feB\u0019qh\u0012&\u000f\u0005\u0001+eBA!E\u001b\u0005\u0011%BA\"\u0015\u0003\u0019a$o\\8u}%\t\u0011\"\u0003\u0002G\u0011\u00059\u0001/Y2lC\u001e,\u0017B\u0001%J\u0005\u0011a\u0015n\u001d;\u000b\u0005\u0019C\u0001CA L\u0013\ta\u0015JA\u0005UQJ|w/\u00192mKB\u0011\u0011C\u0014\u0003\u0006\u001f^\u0012\r!\u0006\u0002\u0002\u0005\")\u0011k\u000ea\u0001%\u0006\ta\r\u0005\u0003\b[Ai\u0005\"\u0002+\u0001\t\u0003*\u0016AB3ji\",'/F\u0001W!\u0011iAH\u0010\t\t\u000ba\u0003A\u0011I-\u0002\u0007=\u0004H/F\u0001[!\r91\fE\u0005\u00039\"\u0011aa\u00149uS>t\u0007\"\u00020\u0001\t\u0003z\u0016!\u0002;sS\u0016$W#\u00011\u0011\u0007\u0005$\u0007#D\u0001c\u0015\t\u0019\u0007\"\u0001\u0003vi&d\u0017BA3c\u0005\r!&/\u001f\u0005\u0006O\u0002!\t\u0005[\u0001\u0007KF,\u0018\r\\:\u0015\u0005%d\u0007CA\u0004k\u0013\tY\u0007BA\u0004C_>dW-\u00198\t\u000b54\u0007\u0019A\r\u0002\tQD\u0017\r\u001e\u0005\u0006_\u0002!\t\u0005]\u0001\tQ\u0006\u001c\bnQ8eKR\t\u0011\u000f\u0005\u0002\be&\u00111\u000f\u0003\u0002\u0004\u0013:$\b\"B;\u0001\t\u00032\u0018\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0003]\u0004\"\u0001_?\u000e\u0003eT!A_>\u0002\t1\fgn\u001a\u0006\u0002y\u0006!!.\u0019<b\u0013\tq\u0018P\u0001\u0004TiJLgn\u001a"
)
public class DeferredExtractableManagedResource<A, R> implements ExtractableManagedResource<A>, ManagedResourceOperations<A> {
   private final ManagedResource<R> resource;
   private final Function1<R, A> translate;

   public <B> B acquireAndGet(Function1<A, B> f) {
      return ManagedResourceOperations$class.acquireAndGet(this, f);
   }

   public <B> B apply(Function1<A, B> f) {
      return ManagedResourceOperations$class.apply(this, f);
   }

   public <B> Traversable<B> toTraversable(less<A, TraversableOnce<B>> ev) {
      return ManagedResourceOperations$class.toTraversable(this, ev);
   }

   public Future<A> toFuture(ExecutionContext context) {
      return ManagedResourceOperations$class.toFuture(this, context);
   }

   public <B> ExtractableManagedResource<B> map(Function1<A, B> f) {
      return ManagedResourceOperations$class.map(this, f);
   }

   public <B> ManagedResource<B> flatMap(Function1<A, ManagedResource<B>> f) {
      return ManagedResourceOperations$class.flatMap(this, f);
   }

   public void foreach(Function1<A, BoxedUnit> f) {
      ManagedResourceOperations$class.foreach(this, f);
   }

   public <B> ManagedResource<Tuple2<A, B>> and(ManagedResource<B> that) {
      return ManagedResourceOperations$class.and(this, that);
   }

   public ManagedResource<R> resource() {
      return this.resource;
   }

   public Function1<R, A> translate() {
      return this.translate;
   }

   public <B> ExtractedEither<List<Throwable>, B> acquireFor(Function1<A, B> f) {
      return this.resource().acquireFor(this.translate().andThen(f));
   }

   public ExtractedEither<List<Throwable>, A> either() {
      return new ExtractedEither(package$.MODULE$.extractedEitherToEither(this.resource().acquireFor(this.translate())));
   }

   public Option<A> opt() {
      return this.either().either().right().toOption();
   }

   public Try<A> tried() {
      return .MODULE$.apply(new Serializable(this) {
         public static final long serialVersionUID = 0L;
         // $FF: synthetic field
         private final DeferredExtractableManagedResource $outer;

         public final A apply() {
            return this.$outer.resource().apply(this.$outer.translate());
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

   public boolean equals(Object that) {
      boolean var4;
      if (that instanceof DeferredExtractableManagedResource) {
         boolean var8;
         label29: {
            label28: {
               label27: {
                  DeferredExtractableManagedResource var3 = (DeferredExtractableManagedResource)that;
                  ManagedResource var10000 = var3.resource();
                  ManagedResource var5 = this.resource();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label27;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label27;
                  }

                  Function1 var7 = var3.translate();
                  Function1 var6 = this.translate();
                  if (var7 == null) {
                     if (var6 == null) {
                        break label28;
                     }
                  } else if (var7.equals(var6)) {
                     break label28;
                  }
               }

               var8 = false;
               break label29;
            }

            var8 = true;
         }

         var4 = var8;
      } else {
         var4 = false;
      }

      return var4;
   }

   public int hashCode() {
      return (this.resource().hashCode() << 7) + this.translate().hashCode() + 13;
   }

   public String toString() {
      return (new StringBuilder()).append("DeferredExtractableManagedResource(").append(this.resource()).append(", ").append(this.translate()).append(")").toString();
   }

   public DeferredExtractableManagedResource(ManagedResource<R> resource, Function1<R, A> translate) {
      this.resource = resource;
      this.translate = translate;
      ManagedResourceOperations$class.$init$(this);
   }
}
