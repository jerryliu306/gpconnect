package resource;

import scala.Function0;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Serializable;
import scala.Some;
import scala.Tuple2;
import scala.Predef..less.colon.less;
import scala.collection.Iterator;
import scala.collection.Seq;
import scala.collection.SeqLike;
import scala.collection.Traversable;
import scala.collection.TraversableOnce;
import scala.collection.Seq.;
import scala.collection.immutable.List;
import scala.concurrent.ExecutionContext;
import scala.concurrent.Future;
import scala.reflect.OptManifest;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.VolatileObjectRef;
import scala.util.Either;

public final class package$ {
   public static final package$ MODULE$;

   static {
      new package$();
   }

   public <A> ManagedResource<A> managed(Function0<A> opener, Resource<A> evidence$1, OptManifest<A> evidence$2) {
      return new DefaultManagedResource(opener, evidence$1, evidence$2);
   }

   public <V> ManagedResource<V> constant(V value) {
      return new ConstantManagedResource(value);
   }

   public <R> DefaultManagedResource<R> makeManagedResource(Function0<R> opener, Function1<R, BoxedUnit> closer, List<Class<? extends Throwable>> exceptions, OptManifest<R> evidence$3) {
      Resource typeTrait = new Resource<R>(closer, exceptions) {
         private final Function1 closer$1;
         private final List exceptions$1;

         public void open(R r) {
            Resource$class.open(this, r);
         }

         public void closeAfterException(R r, Throwable t) {
            Resource$class.closeAfterException(this, r, t);
         }

         public boolean isRethrownException(Throwable t) {
            return Resource$class.isRethrownException(this, t);
         }

         public void close(R r) {
            this.closer$1.apply(r);
         }

         public boolean isFatalException(Throwable t) {
            return this.exceptions$1.exists(new Serializable(this, t) {
               public static final long serialVersionUID = 0L;
               private final Throwable t$1;

               public final boolean apply(Class<? extends Throwable> cls) {
                  return cls.isAssignableFrom(this.t$1.getClass());
               }

               public {
                  this.t$1 = t$1;
               }
            });
         }

         public {
            this.closer$1 = closer$1;
            this.exceptions$1 = exceptions$1;
            Resource$class.$init$(this);
         }
      };
      return new DefaultManagedResource(opener, typeTrait, evidence$3);
   }

   public <A, B> ManagedResource<Tuple2<A, B>> and(ManagedResource<A> r1, ManagedResource<B> r2) {
      return r1.flatMap(new Serializable(r2) {
         public static final long serialVersionUID = 0L;
         private final ManagedResource r2$1;

         public final ExtractableManagedResource<Tuple2<A, B>> apply(A ther1) {
            return this.r2$1.map(new Serializable(this, ther1) {
               public static final long serialVersionUID = 0L;
               private final Object ther1$1;

               public final Tuple2<A, B> apply(B ther2) {
                  return new Tuple2(this.ther1$1, ther2);
               }

               public {
                  this.ther1$1 = ther1$1;
               }
            });
         }

         public {
            this.r2$1 = r2$1;
         }
      });
   }

   public <A, MR, CC> ManagedResource<Seq<A>> join(CC resources, less<CC, Seq<MR>> ev0, less<MR, ManagedResource<A>> ev1) {
      Iterator itr = ((SeqLike)ev0.apply(resources)).reverseIterator();
      ManagedResource first = (ManagedResource)ev1.apply(itr.next());

      Object toReturn;
      ManagedResource r2;
      for(toReturn = first.map(new Serializable() {
         public static final long serialVersionUID = 0L;

         public final Seq<A> apply(A x) {
            return (Seq).MODULE$.apply(scala.Predef..MODULE$.genericWrapArray(new Object[]{x}));
         }
      }); itr.hasNext(); toReturn = new ManagedResourceOperations<Seq<A>>((ManagedResource)toReturn, r2) {
         private final ManagedResource r1$1;
         public final ManagedResource r2$2;

         public <B> B acquireAndGet(Function1<Seq<A>, B> f) {
            return ManagedResourceOperations$class.acquireAndGet(this, f);
         }

         public <B> B apply(Function1<Seq<A>, B> f) {
            return ManagedResourceOperations$class.apply(this, f);
         }

         public <B> Traversable<B> toTraversable(less<Seq<A>, TraversableOnce<B>> ev) {
            return ManagedResourceOperations$class.toTraversable(this, ev);
         }

         public Future<Seq<A>> toFuture(ExecutionContext context) {
            return ManagedResourceOperations$class.toFuture(this, context);
         }

         public <B> ExtractableManagedResource<B> map(Function1<Seq<A>, B> f) {
            return ManagedResourceOperations$class.map(this, f);
         }

         public <B> ManagedResource<B> flatMap(Function1<Seq<A>, ManagedResource<B>> f) {
            return ManagedResourceOperations$class.flatMap(this, f);
         }

         public void foreach(Function1<Seq<A>, BoxedUnit> f) {
            ManagedResourceOperations$class.foreach(this, f);
         }

         public <B> ManagedResource<Tuple2<Seq<A>, B>> and(ManagedResource<B> that) {
            return ManagedResourceOperations$class.and(this, that);
         }

         public <B> ExtractedEither<List<Throwable>, B> acquireFor(Function1<Seq<A>, B> f) {
            return this.r1$1.acquireFor(new Serializable(this, f) {
               public static final long serialVersionUID = 0L;
               // $FF: synthetic field
               private final <undefinedtype> $outer;
               public final Function1 f$1;

               public final B apply(Seq<A> r1seq) {
                  return this.$outer.r2$2.acquireAndGet(new Serializable(this, r1seq) {
                     public static final long serialVersionUID = 0L;
                     // $FF: synthetic field
                     private final <undefinedtype> $outer;
                     private final Seq r1seq$1;

                     public final B apply(A r2item) {
                        return this.$outer.f$1.apply(this.r1seq$1.toList().$colon$colon(r2item));
                     }

                     public {
                        if ($outer == null) {
                           throw null;
                        } else {
                           this.$outer = $outer;
                           this.r1seq$1 = r1seq$1;
                        }
                     }
                  });
               }

               public {
                  if ($outer == null) {
                     throw null;
                  } else {
                     this.$outer = $outer;
                     this.f$1 = f$1;
                  }
               }
            });
         }

         public {
            this.r1$1 = r1$1;
            this.r2$2 = r2$2;
            ManagedResourceOperations$class.$init$(this);
         }
      }) {
         r2 = (ManagedResource)ev1.apply(itr.next());
      }

      return (ManagedResource)toReturn;
   }

   public <A> ManagedResource<A> shared(Function0<A> opener, Resource<A> evidence$4, OptManifest<A> evidence$5) {
      VolatileObjectRef sharedReference = VolatileObjectRef.create(scala.None..MODULE$);
      Object lock = new Object();
      Resource resource = new Resource<A>(evidence$4, sharedReference, lock) {
         private final Resource evidence$4$1;
         private final VolatileObjectRef sharedReference$1;
         private final Object lock$1;

         public void open(A r) {
            Resource$class.open(this, r);
         }

         public void closeAfterException(A r, Throwable t) {
            Resource$class.closeAfterException(this, r, t);
         }

         public boolean isFatalException(Throwable t) {
            return Resource$class.isFatalException(this, t);
         }

         public boolean isRethrownException(Throwable t) {
            return Resource$class.isRethrownException(this, t);
         }

         public void close(A r) {
            synchronized(this.lock$1) {
               Option var3 = (Option)this.sharedReference$1.elem;
               if (var3 instanceof Some) {
                  Some var4 = (Some)var3;
                  Tuple2 var5 = (Tuple2)var4.x();
                  if (var5 != null) {
                     int oldReferenceCount = var5._1$mcI$sp();
                     Object sc = var5._2();
                     if (BoxesRunTime.equals(r, sc)) {
                        BoxedUnit var10000;
                        if (oldReferenceCount == 1) {
                           ((Resource)scala.Predef..MODULE$.implicitly(this.evidence$4$1)).close(sc);
                           this.sharedReference$1.elem = scala.None..MODULE$;
                           var10000 = BoxedUnit.UNIT;
                        } else {
                           this.sharedReference$1.elem = new Some(new Tuple2(BoxesRunTime.boxToInteger(oldReferenceCount - 1), sc));
                           var10000 = BoxedUnit.UNIT;
                        }

                        var10000 = BoxedUnit.UNIT;
                        return;
                     }

                     throw new IllegalArgumentException();
                  }
               }

               if (scala.None..MODULE$.equals(var3)) {
                  throw new IllegalStateException();
               } else {
                  throw new MatchError(var3);
               }
            }
         }

         public {
            this.evidence$4$1 = evidence$4$1;
            this.sharedReference$1 = sharedReference$1;
            this.lock$1 = lock$1;
            Resource$class.$init$(this);
         }
      };
      return new DefaultManagedResource(new Serializable(opener, evidence$4, sharedReference, lock) {
         public static final long serialVersionUID = 0L;
         private final Function0 opener$1;
         private final Resource evidence$4$1;
         private final VolatileObjectRef sharedReference$1;
         private final Object lock$1;

         public final A apply() {
            return package$.MODULE$.resource$package$$acquire$1(this.opener$1, this.evidence$4$1, this.sharedReference$1, this.lock$1);
         }

         public {
            this.opener$1 = opener$1;
            this.evidence$4$1 = evidence$4$1;
            this.sharedReference$1 = sharedReference$1;
            this.lock$1 = lock$1;
         }
      }, resource, (OptManifest)scala.Predef..MODULE$.implicitly(evidence$5));
   }

   public <A, B> Either<A, B> extractedEitherToEither(ExtractedEither<A, B> extracted) {
      return extracted.either();
   }

   public final Object resource$package$$acquire$1(Function0 opener$1, Resource evidence$4$1, VolatileObjectRef sharedReference$1, Object lock$1) {
      synchronized(lock$1) {
         Option var9 = (Option)sharedReference$1.elem;
         Tuple2 var10;
         if (scala.None..MODULE$.equals(var9)) {
            Object r = opener$1.apply();
            ((Resource)scala.Predef..MODULE$.implicitly(evidence$4$1)).open(r);
            var10 = new Tuple2(BoxesRunTime.boxToInteger(1), r);
         } else {
            label34: {
               if (var9 instanceof Some) {
                  Some var12 = (Some)var9;
                  Tuple2 var13 = (Tuple2)var12.x();
                  if (var13 != null) {
                     int oldReferenceCount = var13._1$mcI$sp();
                     Object sc = var13._2();
                     var10 = new Tuple2(BoxesRunTime.boxToInteger(oldReferenceCount + 1), sc);
                     break label34;
                  }
               }

               throw new MatchError(var9);
            }
         }

         if (var10 != null) {
            int referenceCount = var10._1$mcI$sp();
            Object sc = var10._2();
            Tuple2 var18 = new Tuple2(BoxesRunTime.boxToInteger(referenceCount), sc);
            int referenceCount = var18._1$mcI$sp();
            Object sc = var18._2();
            sharedReference$1.elem = new Some(new Tuple2(BoxesRunTime.boxToInteger(referenceCount), sc));
            return sc;
         } else {
            throw new MatchError(var10);
         }
      }
   }

   private package$() {
      MODULE$ = this;
   }
}
