package resource;

import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Serializable;
import scala.Tuple2;
import scala.Predef..less.colon.less;
import scala.collection.Traversable;
import scala.collection.TraversableOnce;
import scala.collection.immutable.List;
import scala.concurrent.ExecutionContext;
import scala.concurrent.Future;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.Nothing.;
import scala.util.Either;
import scala.util.Left;
import scala.util.Right;
import scala.util.control.ControlThrowable;
import scala.util.control.Exception.Catch;

@ScalaSignature(
   bytes = "\u0006\u000194Q!\u0001\u0002\u0002\u0002\u0015\u0011q#\u00112tiJ\f7\r^'b]\u0006<W\r\u001a*fg>,(oY3\u000b\u0003\r\t\u0001B]3t_V\u00148-Z\u0002\u0001+\t11c\u0005\u0003\u0001\u000f5a\u0002C\u0001\u0005\f\u001b\u0005I!\"\u0001\u0006\u0002\u000bM\u001c\u0017\r\\1\n\u00051I!AB!osJ+g\rE\u0002\u000f\u001fEi\u0011AA\u0005\u0003!\t\u0011q\"T1oC\u001e,GMU3t_V\u00148-\u001a\t\u0003%Ma\u0001\u0001B\u0003\u0015\u0001\t\u0007QCA\u0001S#\t1\u0012\u0004\u0005\u0002\t/%\u0011\u0001$\u0003\u0002\b\u001d>$\b.\u001b8h!\tA!$\u0003\u0002\u001c\u0013\t\u0019\u0011I\\=\u0011\u00079i\u0012#\u0003\u0002\u001f\u0005\tIR*\u00198bO\u0016$'+Z:pkJ\u001cWm\u00149fe\u0006$\u0018n\u001c8t\u0011\u0015\u0001\u0003\u0001\"\u0001\"\u0003\u0019a\u0014N\\5u}Q\t!\u0005E\u0002\u000f\u0001EAQ\u0001\n\u0001\u0007\u0012\u0015\nAa\u001c9f]V\t\u0011\u0003C\u0003(\u0001\u0019E\u0001&A\u0006v]N\fg-Z\"m_N,GcA\u0015-]A\u0011\u0001BK\u0005\u0003W%\u0011A!\u00168ji\")QF\na\u0001#\u00051\u0001.\u00198eY\u0016DQa\f\u0014A\u0002A\na!\u001a:s_J\u001c\bc\u0001\u00052g%\u0011!'\u0003\u0002\u0007\u001fB$\u0018n\u001c8\u0011\u0005QbdBA\u001b;\u001d\t1\u0014(D\u00018\u0015\tAD!\u0001\u0004=e>|GOP\u0005\u0002\u0015%\u00111(C\u0001\ba\u0006\u001c7.Y4f\u0013\tidHA\u0005UQJ|w/\u00192mK*\u00111(\u0003\u0005\u0006\u0001\u0002!\t\"Q\u0001\u000bSN\u0014V\r\u001e5s_^tGC\u0001\"F!\tA1)\u0003\u0002E\u0013\t9!i\\8mK\u0006t\u0007\"\u0002$@\u0001\u0004\u0019\u0014!\u0001;\t\u000b!\u0003A\u0011C%\u0002\u000f%\u001ch)\u0019;bYR\u0011!I\u0013\u0005\u0006\r\u001e\u0003\ra\r\u0005\b\u0019\u0002\u0011\r\u0011\"\u0004N\u0003A\u0019\u0017\r^2iS:<gj\u001c8GCR\fG.F\u0001O!\ryuK\u0006\b\u0003!Vk\u0011!\u0015\u0006\u0003%N\u000bqaY8oiJ|GN\u0003\u0002U\u0013\u0005!Q\u000f^5m\u0013\t1\u0016+A\u0005Fq\u000e,\u0007\u000f^5p]&\u0011\u0001,\u0017\u0002\u0006\u0007\u0006$8\r\u001b\u0006\u0003-FCaa\u0017\u0001!\u0002\u001bq\u0015!E2bi\u000eD\u0017N\\4O_:4\u0015\r^1mA!)Q\f\u0001C!=\u0006Q\u0011mY9vSJ,gi\u001c:\u0016\u0005};GC\u00011j!\u0011q\u0011m\u00194\n\u0005\t\u0014!aD#yiJ\f7\r^3e\u000b&$\b.\u001a:\u0011\u0007Q\"7'\u0003\u0002f}\t!A*[:u!\t\u0011r\rB\u0003i9\n\u0007QCA\u0001C\u0011\u0015QG\f1\u0001l\u0003\u00051\u0007\u0003\u0002\u0005m#\u0019L!!\\\u0005\u0003\u0013\u0019+hn\u0019;j_:\f\u0004"
)
public abstract class AbstractManagedResource<R> implements ManagedResourceOperations<R> {
   private final Catch<> catchingNonFatal;

   public <B> B acquireAndGet(Function1<R, B> f) {
      return ManagedResourceOperations$class.acquireAndGet(this, f);
   }

   public <B> B apply(Function1<R, B> f) {
      return ManagedResourceOperations$class.apply(this, f);
   }

   public <B> Traversable<B> toTraversable(less<R, TraversableOnce<B>> ev) {
      return ManagedResourceOperations$class.toTraversable(this, ev);
   }

   public Future<R> toFuture(ExecutionContext context) {
      return ManagedResourceOperations$class.toFuture(this, context);
   }

   public <B> ExtractableManagedResource<B> map(Function1<R, B> f) {
      return ManagedResourceOperations$class.map(this, f);
   }

   public <B> ManagedResource<B> flatMap(Function1<R, ManagedResource<B>> f) {
      return ManagedResourceOperations$class.flatMap(this, f);
   }

   public void foreach(Function1<R, BoxedUnit> f) {
      ManagedResourceOperations$class.foreach(this, f);
   }

   public <B> ManagedResource<Tuple2<R, B>> and(ManagedResource<B> that) {
      return ManagedResourceOperations$class.and(this, that);
   }

   public abstract R open();

   public abstract void unsafeClose(R var1, Option<Throwable> var2);

   public boolean isRethrown(Throwable t) {
      boolean var3;
      if (t instanceof ControlThrowable) {
         var3 = true;
      } else if (t instanceof InterruptedException) {
         var3 = true;
      } else {
         var3 = false;
      }

      return var3;
   }

   public boolean isFatal(Throwable t) {
      boolean var3;
      if (t instanceof VirtualMachineError) {
         var3 = true;
      } else {
         var3 = false;
      }

      return var3;
   }

   private final Catch<> catchingNonFatal() {
      return this.catchingNonFatal;
   }

   public <B> ExtractedEither<List<Throwable>, B> acquireFor(Function1<R, B> f) {
      Object handle = this.open();
      Either result = this.catchingNonFatal().either(new Serializable(this, f, handle) {
         public static final long serialVersionUID = 0L;
         private final Function1 f$1;
         private final Object handle$1;

         public final B apply() {
            return this.f$1.apply(this.handle$1);
         }

         public {
            this.f$1 = f$1;
            this.handle$1 = handle$1;
         }
      });
      Either close = this.catchingNonFatal().either(new Serializable(this, handle, result) {
         public static final long serialVersionUID = 0L;
         // $FF: synthetic field
         private final AbstractManagedResource $outer;
         private final Object handle$1;
         private final Either result$1;

         public final void apply() {
            this.apply$mcV$sp();
         }

         public void apply$mcV$sp() {
            this.$outer.unsafeClose(this.handle$1, this.result$1.left().toOption());
         }

         public {
            if ($outer == null) {
               throw null;
            } else {
               this.$outer = $outer;
               this.handle$1 = handle$1;
               this.result$1 = result$1;
            }
         }
      });
      Tuple2 var6 = new Tuple2(result, close);
      if (var6 != null) {
         Either var7 = (Either)var6._1();
         if (var7 instanceof Left) {
            Left var8 = (Left)var7;
            Throwable t1 = (Throwable)var8.a();
            if (this.isRethrown(t1)) {
               throw t1;
            }
         }
      }

      Object var10;
      if (var6 != null) {
         Either var11 = (Either)var6._1();
         Either var12 = (Either)var6._2();
         if (var11 instanceof Left) {
            Left var13 = (Left)var11;
            Throwable t1 = (Throwable)var13.a();
            if (var12 instanceof Left) {
               Left var15 = (Left)var12;
               Throwable t2 = (Throwable)var15.a();
               var10 = scala.package..MODULE$.Left().apply(scala.collection.immutable.Nil..MODULE$.$colon$colon(t2).$colon$colon(t1));
               return new ExtractedEither((Either)var10);
            }
         }
      }

      if (var6 != null) {
         Either var19 = (Either)var6._1();
         if (var19 instanceof Left) {
            Left var20 = (Left)var19;
            Throwable t1 = (Throwable)var20.a();
            var10 = scala.package..MODULE$.Left().apply(scala.collection.immutable.Nil..MODULE$.$colon$colon(t1));
            return new ExtractedEither((Either)var10);
         }
      }

      if (var6 != null) {
         Either var23 = (Either)var6._1();
         Either var24 = (Either)var6._2();
         if (var23 instanceof Right) {
            Right var25 = (Right)var23;
            Object var26 = var25.b();
            if (var26 instanceof ExtractedEither) {
               ExtractedEither var27 = (ExtractedEither)var26;
               Either var28 = var27.either();
               if (var28 instanceof Left) {
                  Left var29 = (Left)var28;
                  Object ts = var29.a();
                  if (var24 instanceof Left) {
                     Left var31 = (Left)var24;
                     Throwable t2 = (Throwable)var31.a();
                     var10 = scala.package..MODULE$.Left().apply(((List)ts).$colon$plus(t2, scala.collection.immutable.List..MODULE$.canBuildFrom()));
                     return new ExtractedEither((Either)var10);
                  }
               }
            }
         }
      }

      if (var6 != null) {
         Either var33 = (Either)var6._2();
         if (var33 instanceof Left) {
            Left var34 = (Left)var33;
            Throwable t2 = (Throwable)var34.a();
            var10 = scala.package..MODULE$.Left().apply(scala.collection.immutable.Nil..MODULE$.$colon$colon(t2));
            return new ExtractedEither((Either)var10);
         }
      }

      if (var6 != null) {
         Either var37 = (Either)var6._1();
         if (var37 instanceof Right) {
            Right var38 = (Right)var37;
            Object r = var38.b();
            var10 = scala.package..MODULE$.Right().apply(r);
            return new ExtractedEither((Either)var10);
         }
      }

      throw new MatchError(var6);
   }

   public AbstractManagedResource() {
      ManagedResourceOperations$class.$init$(this);
      this.catchingNonFatal = (Catch)(new Catch(scala.util.control.Exception..MODULE$.mkThrowableCatcher(new Serializable(this) {
         public static final long serialVersionUID = 0L;
         // $FF: synthetic field
         private final AbstractManagedResource $outer;

         public final boolean apply(Throwable e) {
            return !this.$outer.isFatal(e);
         }

         public {
            if ($outer == null) {
               throw null;
            } else {
               this.$outer = $outer;
            }
         }
      }, new Serializable(this) {
         public static final long serialVersionUID = 0L;

         public final  apply(Throwable x$1) {
            throw x$1;
         }
      }), scala.None..MODULE$, new Serializable(this) {
         public static final long serialVersionUID = 0L;

         public final boolean apply(Throwable x$2) {
            return false;
         }
      })).withDesc("<non-fatal>");
   }
}
