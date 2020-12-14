package resource;

import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.PartialFunction;
import scala.Serializable;
import scala.Tuple2;
import scala.Tuple3;
import scala.Predef..less.colon.less;
import scala.collection.GenTraversable;
import scala.collection.GenTraversableOnce;
import scala.collection.Iterable;
import scala.collection.Iterator;
import scala.collection.Parallel;
import scala.collection.Seq;
import scala.collection.Traversable;
import scala.collection.TraversableOnce;
import scala.collection.TraversableView;
import scala.collection.Traversable.class;
import scala.collection.generic.CanBuildFrom;
import scala.collection.generic.FilterMonadic;
import scala.collection.generic.GenericCompanion;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.collection.immutable.Set;
import scala.collection.immutable.Stream;
import scala.collection.immutable.Vector;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Builder;
import scala.collection.mutable.StringBuilder;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.ParIterable;
import scala.concurrent.ExecutionContext;
import scala.concurrent.Future;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.runtime.BoxedUnit;
import scala.runtime.Nothing.;

public abstract class ManagedResourceOperations$class {
   public static Object acquireAndGet(ManagedResourceOperations $this, Function1 f) {
      return $this.apply(f);
   }

   public static Object apply(ManagedResourceOperations $this, Function1 f) {
      return package$.MODULE$.extractedEitherToEither($this.acquireFor(f)).fold(new Serializable($this) {
         public static final long serialVersionUID = 0L;

         public final  apply(List<Throwable> liste) {
            throw (Throwable)liste.reduce(new Serializable(this) {
               public static final long serialVersionUID = 0L;

               public final Throwable apply(Throwable prev, Throwable next) {
                  prev.addSuppressed(next);
                  return prev;
               }
            });
         }
      }, new Serializable($this) {
         public static final long serialVersionUID = 0L;

         public final B apply(B x) {
            return x;
         }
      });
   }

   public static Traversable toTraversable(ManagedResourceOperations $this, less ev) {
      return new ManagedTraversable<B, R>($this, ev) {
         private final ManagedResourceOperations<R> resource;
         private final less ev$1;

         public boolean ignoreError(Exception error) {
            return ManagedTraversable$class.ignoreError(this, error);
         }

         public void handleErrorsDuringTraversal(List<Throwable> ex) {
            ManagedTraversable$class.handleErrorsDuringTraversal(this, ex);
         }

         public <U> void foreach(Function1<B, U> f) {
            ManagedTraversable$class.foreach(this, f);
         }

         public String toString() {
            return ManagedTraversable$class.toString(this);
         }

         public GenericCompanion<Traversable> companion() {
            return class.companion(this);
         }

         public Traversable<B> seq() {
            return class.seq(this);
         }

         public Builder<B, Traversable<B>> newBuilder() {
            return scala.collection.generic.GenericTraversableTemplate.class.newBuilder(this);
         }

         public <B> Builder<B, Traversable<B>> genericBuilder() {
            return scala.collection.generic.GenericTraversableTemplate.class.genericBuilder(this);
         }

         public <A1, A2> Tuple2<Traversable<A1>, Traversable<A2>> unzip(Function1<B, Tuple2<A1, A2>> asPair) {
            return scala.collection.generic.GenericTraversableTemplate.class.unzip(this, asPair);
         }

         public <A1, A2, A3> Tuple3<Traversable<A1>, Traversable<A2>, Traversable<A3>> unzip3(Function1<B, Tuple3<A1, A2, A3>> asTriple) {
            return scala.collection.generic.GenericTraversableTemplate.class.unzip3(this, asTriple);
         }

         public GenTraversable flatten(Function1 asTraversable) {
            return scala.collection.generic.GenericTraversableTemplate.class.flatten(this, asTraversable);
         }

         public GenTraversable transpose(Function1 asTraversable) {
            return scala.collection.generic.GenericTraversableTemplate.class.transpose(this, asTraversable);
         }

         public Object repr() {
            return scala.collection.TraversableLike.class.repr(this);
         }

         public final boolean isTraversableAgain() {
            return scala.collection.TraversableLike.class.isTraversableAgain(this);
         }

         public Traversable<B> thisCollection() {
            return scala.collection.TraversableLike.class.thisCollection(this);
         }

         public Traversable toCollection(Object repr) {
            return scala.collection.TraversableLike.class.toCollection(this, repr);
         }

         public Combiner<B, ParIterable<B>> parCombiner() {
            return scala.collection.TraversableLike.class.parCombiner(this);
         }

         public boolean isEmpty() {
            return scala.collection.TraversableLike.class.isEmpty(this);
         }

         public boolean hasDefiniteSize() {
            return scala.collection.TraversableLike.class.hasDefiniteSize(this);
         }

         public <B, That> That $plus$plus(GenTraversableOnce<B> that, CanBuildFrom<Traversable<B>, B, That> bf) {
            return scala.collection.TraversableLike.class.$plus$plus(this, that, bf);
         }

         public <B, That> That $plus$plus$colon(TraversableOnce<B> that, CanBuildFrom<Traversable<B>, B, That> bf) {
            return scala.collection.TraversableLike.class.$plus$plus$colon(this, that, bf);
         }

         public <B, That> That $plus$plus$colon(Traversable<B> that, CanBuildFrom<Traversable<B>, B, That> bf) {
            return scala.collection.TraversableLike.class.$plus$plus$colon(this, that, bf);
         }

         public <B, That> That map(Function1<B, B> f, CanBuildFrom<Traversable<B>, B, That> bf) {
            return scala.collection.TraversableLike.class.map(this, f, bf);
         }

         public <B, That> That flatMap(Function1<B, GenTraversableOnce<B>> f, CanBuildFrom<Traversable<B>, B, That> bf) {
            return scala.collection.TraversableLike.class.flatMap(this, f, bf);
         }

         public Object filter(Function1 p) {
            return scala.collection.TraversableLike.class.filter(this, p);
         }

         public Object filterNot(Function1 p) {
            return scala.collection.TraversableLike.class.filterNot(this, p);
         }

         public <B, That> That collect(PartialFunction<B, B> pf, CanBuildFrom<Traversable<B>, B, That> bf) {
            return scala.collection.TraversableLike.class.collect(this, pf, bf);
         }

         public Tuple2<Traversable<B>, Traversable<B>> partition(Function1<B, Object> p) {
            return scala.collection.TraversableLike.class.partition(this, p);
         }

         public <K> Map<K, Traversable<B>> groupBy(Function1<B, K> f) {
            return scala.collection.TraversableLike.class.groupBy(this, f);
         }

         public boolean forall(Function1<B, Object> p) {
            return scala.collection.TraversableLike.class.forall(this, p);
         }

         public boolean exists(Function1<B, Object> p) {
            return scala.collection.TraversableLike.class.exists(this, p);
         }

         public Option<B> find(Function1<B, Object> p) {
            return scala.collection.TraversableLike.class.find(this, p);
         }

         public <B, That> That scan(B z, Function2<B, B, B> op, CanBuildFrom<Traversable<B>, B, That> cbf) {
            return scala.collection.TraversableLike.class.scan(this, z, op, cbf);
         }

         public <B, That> That scanLeft(B z, Function2<B, B, B> op, CanBuildFrom<Traversable<B>, B, That> bf) {
            return scala.collection.TraversableLike.class.scanLeft(this, z, op, bf);
         }

         public <B, That> That scanRight(B z, Function2<B, B, B> op, CanBuildFrom<Traversable<B>, B, That> bf) {
            return scala.collection.TraversableLike.class.scanRight(this, z, op, bf);
         }

         public B head() {
            return scala.collection.TraversableLike.class.head(this);
         }

         public Option<B> headOption() {
            return scala.collection.TraversableLike.class.headOption(this);
         }

         public Object tail() {
            return scala.collection.TraversableLike.class.tail(this);
         }

         public B last() {
            return scala.collection.TraversableLike.class.last(this);
         }

         public Option<B> lastOption() {
            return scala.collection.TraversableLike.class.lastOption(this);
         }

         public Object init() {
            return scala.collection.TraversableLike.class.init(this);
         }

         public Object take(int n) {
            return scala.collection.TraversableLike.class.take(this, n);
         }

         public Object drop(int n) {
            return scala.collection.TraversableLike.class.drop(this, n);
         }

         public Object slice(int from, int until) {
            return scala.collection.TraversableLike.class.slice(this, from, until);
         }

         public Object sliceWithKnownDelta(int from, int until, int delta) {
            return scala.collection.TraversableLike.class.sliceWithKnownDelta(this, from, until, delta);
         }

         public Object sliceWithKnownBound(int from, int until) {
            return scala.collection.TraversableLike.class.sliceWithKnownBound(this, from, until);
         }

         public Object takeWhile(Function1 p) {
            return scala.collection.TraversableLike.class.takeWhile(this, p);
         }

         public Object dropWhile(Function1 p) {
            return scala.collection.TraversableLike.class.dropWhile(this, p);
         }

         public Tuple2<Traversable<B>, Traversable<B>> span(Function1<B, Object> p) {
            return scala.collection.TraversableLike.class.span(this, p);
         }

         public Tuple2<Traversable<B>, Traversable<B>> splitAt(int n) {
            return scala.collection.TraversableLike.class.splitAt(this, n);
         }

         public Iterator<Traversable<B>> tails() {
            return scala.collection.TraversableLike.class.tails(this);
         }

         public Iterator<Traversable<B>> inits() {
            return scala.collection.TraversableLike.class.inits(this);
         }

         public <B> void copyToArray(Object xs, int start, int len) {
            scala.collection.TraversableLike.class.copyToArray(this, xs, start, len);
         }

         public Traversable<B> toTraversable() {
            return scala.collection.TraversableLike.class.toTraversable(this);
         }

         public Iterator<B> toIterator() {
            return scala.collection.TraversableLike.class.toIterator(this);
         }

         public Stream<B> toStream() {
            return scala.collection.TraversableLike.class.toStream(this);
         }

         public <Col> Col to(CanBuildFrom<, B, Col> cbf) {
            return scala.collection.TraversableLike.class.to(this, cbf);
         }

         public String stringPrefix() {
            return scala.collection.TraversableLike.class.stringPrefix(this);
         }

         public Object view() {
            return scala.collection.TraversableLike.class.view(this);
         }

         public TraversableView<B, Traversable<B>> view(int from, int until) {
            return scala.collection.TraversableLike.class.view(this, from, until);
         }

         public FilterMonadic<B, Traversable<B>> withFilter(Function1<B, Object> p) {
            return scala.collection.TraversableLike.class.withFilter(this, p);
         }

         public Parallel par() {
            return scala.collection.Parallelizable.class.par(this);
         }

         public List<B> reversed() {
            return scala.collection.TraversableOnce.class.reversed(this);
         }

         public int size() {
            return scala.collection.TraversableOnce.class.size(this);
         }

         public boolean nonEmpty() {
            return scala.collection.TraversableOnce.class.nonEmpty(this);
         }

         public int count(Function1<B, Object> p) {
            return scala.collection.TraversableOnce.class.count(this, p);
         }

         public <B> Option<B> collectFirst(PartialFunction<B, B> pf) {
            return scala.collection.TraversableOnce.class.collectFirst(this, pf);
         }

         public <B> B $div$colon(B z, Function2<B, B, B> op) {
            return scala.collection.TraversableOnce.class.$div$colon(this, z, op);
         }

         public <B> B $colon$bslash(B z, Function2<B, B, B> op) {
            return scala.collection.TraversableOnce.class.$colon$bslash(this, z, op);
         }

         public <B> B foldLeft(B z, Function2<B, B, B> op) {
            return scala.collection.TraversableOnce.class.foldLeft(this, z, op);
         }

         public <B> B foldRight(B z, Function2<B, B, B> op) {
            return scala.collection.TraversableOnce.class.foldRight(this, z, op);
         }

         public <B> B reduceLeft(Function2<B, B, B> op) {
            return scala.collection.TraversableOnce.class.reduceLeft(this, op);
         }

         public <B> B reduceRight(Function2<B, B, B> op) {
            return scala.collection.TraversableOnce.class.reduceRight(this, op);
         }

         public <B> Option<B> reduceLeftOption(Function2<B, B, B> op) {
            return scala.collection.TraversableOnce.class.reduceLeftOption(this, op);
         }

         public <B> Option<B> reduceRightOption(Function2<B, B, B> op) {
            return scala.collection.TraversableOnce.class.reduceRightOption(this, op);
         }

         public <A1> A1 reduce(Function2<A1, A1, A1> op) {
            return scala.collection.TraversableOnce.class.reduce(this, op);
         }

         public <A1> Option<A1> reduceOption(Function2<A1, A1, A1> op) {
            return scala.collection.TraversableOnce.class.reduceOption(this, op);
         }

         public <A1> A1 fold(A1 z, Function2<A1, A1, A1> op) {
            return scala.collection.TraversableOnce.class.fold(this, z, op);
         }

         public <B> B aggregate(Function0<B> z, Function2<B, B, B> seqop, Function2<B, B, B> combop) {
            return scala.collection.TraversableOnce.class.aggregate(this, z, seqop, combop);
         }

         public <B> B sum(Numeric<B> num) {
            return scala.collection.TraversableOnce.class.sum(this, num);
         }

         public <B> B product(Numeric<B> num) {
            return scala.collection.TraversableOnce.class.product(this, num);
         }

         public <B> B min(Ordering<B> cmp) {
            return scala.collection.TraversableOnce.class.min(this, cmp);
         }

         public <B> B max(Ordering<B> cmp) {
            return scala.collection.TraversableOnce.class.max(this, cmp);
         }

         public <B> B maxBy(Function1<B, B> f, Ordering<B> cmp) {
            return scala.collection.TraversableOnce.class.maxBy(this, f, cmp);
         }

         public <B> B minBy(Function1<B, B> f, Ordering<B> cmp) {
            return scala.collection.TraversableOnce.class.minBy(this, f, cmp);
         }

         public <B> void copyToBuffer(Buffer<B> dest) {
            scala.collection.TraversableOnce.class.copyToBuffer(this, dest);
         }

         public <B> void copyToArray(Object xs, int start) {
            scala.collection.TraversableOnce.class.copyToArray(this, xs, start);
         }

         public <B> void copyToArray(Object xs) {
            scala.collection.TraversableOnce.class.copyToArray(this, xs);
         }

         public <B> Object toArray(ClassTag<B> evidence$1) {
            return scala.collection.TraversableOnce.class.toArray(this, evidence$1);
         }

         public List<B> toList() {
            return scala.collection.TraversableOnce.class.toList(this);
         }

         public Iterable<B> toIterable() {
            return scala.collection.TraversableOnce.class.toIterable(this);
         }

         public Seq<B> toSeq() {
            return scala.collection.TraversableOnce.class.toSeq(this);
         }

         public IndexedSeq<B> toIndexedSeq() {
            return scala.collection.TraversableOnce.class.toIndexedSeq(this);
         }

         public <B> Buffer<B> toBuffer() {
            return scala.collection.TraversableOnce.class.toBuffer(this);
         }

         public <B> Set<B> toSet() {
            return scala.collection.TraversableOnce.class.toSet(this);
         }

         public Vector<B> toVector() {
            return scala.collection.TraversableOnce.class.toVector(this);
         }

         public <T, U> Map<T, U> toMap(less<B, Tuple2<T, U>> ev) {
            return scala.collection.TraversableOnce.class.toMap(this, ev);
         }

         public String mkString(String start, String sep, String end) {
            return scala.collection.TraversableOnce.class.mkString(this, start, sep, end);
         }

         public String mkString(String sep) {
            return scala.collection.TraversableOnce.class.mkString(this, sep);
         }

         public String mkString() {
            return scala.collection.TraversableOnce.class.mkString(this);
         }

         public StringBuilder addString(StringBuilder b, String start, String sep, String end) {
            return scala.collection.TraversableOnce.class.addString(this, b, start, sep, end);
         }

         public StringBuilder addString(StringBuilder b, String sep) {
            return scala.collection.TraversableOnce.class.addString(this, b, sep);
         }

         public StringBuilder addString(StringBuilder b) {
            return scala.collection.TraversableOnce.class.addString(this, b);
         }

         public ManagedResourceOperations<R> resource() {
            return this.resource;
         }

         public <U> void internalForeach(R resource, Function1<B, U> g) {
            ((TraversableOnce)this.ev$1.apply(resource)).foreach(g);
         }

         public {
            this.ev$1 = ev$1;
            scala.collection.TraversableOnce.class.$init$(this);
            scala.collection.Parallelizable.class.$init$(this);
            scala.collection.TraversableLike.class.$init$(this);
            scala.collection.generic.GenericTraversableTemplate.class.$init$(this);
            scala.collection.GenTraversable.class.$init$(this);
            class.$init$(this);
            ManagedTraversable$class.$init$(this);
            this.resource = $outer;
         }
      };
   }

   public static Future toFuture(ManagedResourceOperations $this, ExecutionContext context) {
      return scala.concurrent.Future..MODULE$.apply(new Serializable($this) {
         public static final long serialVersionUID = 0L;
         // $FF: synthetic field
         private final ManagedResourceOperations $outer;

         public final R apply() {
            return this.$outer.acquireAndGet(new Serializable(this) {
               public static final long serialVersionUID = 0L;

               public final R apply(R x) {
                  return scala.Predef..MODULE$.identity(x);
               }
            });
         }

         public {
            if ($outer == null) {
               throw null;
            } else {
               this.$outer = $outer;
            }
         }
      }, context);
   }

   public static ExtractableManagedResource map(ManagedResourceOperations $this, Function1 f) {
      return new DeferredExtractableManagedResource($this, f);
   }

   public static ManagedResource flatMap(ManagedResourceOperations $this, Function1 f) {
      return new ManagedResourceOperations<B>($this, f) {
         // $FF: synthetic field
         private final ManagedResourceOperations $outer;
         public final Function1 f$1;

         public <B> B acquireAndGet(Function1<B, B> f) {
            return ManagedResourceOperations$class.acquireAndGet(this, f);
         }

         public <B> B apply(Function1<B, B> f) {
            return ManagedResourceOperations$class.apply(this, f);
         }

         public <B> Traversable<B> toTraversable(less<B, TraversableOnce<B>> ev) {
            return ManagedResourceOperations$class.toTraversable(this, ev);
         }

         public Future<B> toFuture(ExecutionContext context) {
            return ManagedResourceOperations$class.toFuture(this, context);
         }

         public <B> ExtractableManagedResource<B> map(Function1<B, B> f) {
            return ManagedResourceOperations$class.map(this, f);
         }

         public <B> ManagedResource<B> flatMap(Function1<B, ManagedResource<B>> f) {
            return ManagedResourceOperations$class.flatMap(this, f);
         }

         public void foreach(Function1<B, BoxedUnit> f) {
            ManagedResourceOperations$class.foreach(this, f);
         }

         public <B> ManagedResource<Tuple2<B, B>> and(ManagedResource<B> that) {
            return ManagedResourceOperations$class.and(this, that);
         }

         public <C> ExtractedEither<List<Throwable>, C> acquireFor(Function1<B, C> f2) {
            return (ExtractedEither)package$.MODULE$.extractedEitherToEither(this.$outer.acquireFor(new Serializable(this, f2) {
               public static final long serialVersionUID = 0L;
               // $FF: synthetic field
               private final <undefinedtype> $outer;
               private final Function1 f2$1;

               public final ExtractedEither<List<Throwable>, C> apply(R r) {
                  return ((ManagedResource)this.$outer.f$1.apply(r)).acquireFor(this.f2$1);
               }

               public {
                  if ($outer == null) {
                     throw null;
                  } else {
                     this.$outer = $outer;
                     this.f2$1 = f2$1;
                  }
               }
            })).fold(new Serializable(this) {
               public static final long serialVersionUID = 0L;

               public final ExtractedEither<List<Throwable>, > apply(List<Throwable> x) {
                  return new ExtractedEither(scala.package..MODULE$.Left().apply(x));
               }
            }, new Serializable(this) {
               public static final long serialVersionUID = 0L;

               public final ExtractedEither<List<Throwable>, C> apply(ExtractedEither<List<Throwable>, C> x) {
                  return x;
               }
            });
         }

         public String toString() {
            return "FlattenedManagedResource[?](...)";
         }

         public {
            if ($outer == null) {
               throw null;
            } else {
               this.$outer = $outer;
               this.f$1 = f$1;
               ManagedResourceOperations$class.$init$(this);
            }
         }
      };
   }

   public static void foreach(ManagedResourceOperations $this, Function1 f) {
      $this.acquireAndGet(f);
   }

   public static ManagedResource and(ManagedResourceOperations $this, ManagedResource that) {
      return package$.MODULE$.and($this, that);
   }

   public static void $init$(ManagedResourceOperations $this) {
   }
}
