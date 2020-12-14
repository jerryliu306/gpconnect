package resource;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.jar.JarFile;
import java.util.jar.JarInputStream;
import java.util.jar.JarOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.PartialFunction;
import scala.Serializable;
import scala.StringContext;
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
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.OptManifest;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.VolatileObjectRef;

public final class Using$ {
   public static final Using$ MODULE$;
   private final Charset utf8;

   static {
      new Using$();
   }

   private traverser$2$ traverser$1$lzycompute(BufferedReader x$1, VolatileObjectRef x$2) {
      BoxedUnit var10000;

      class traverser$2$ implements Traversable<String> {
         private final BufferedReader reader$1;

         public GenericCompanion<Traversable> companion() {
            return class.companion(this);
         }

         public Traversable<String> seq() {
            return class.seq(this);
         }

         public Builder<String, Traversable<String>> newBuilder() {
            return scala.collection.generic.GenericTraversableTemplate.class.newBuilder(this);
         }

         public <B> Builder<B, Traversable<B>> genericBuilder() {
            return scala.collection.generic.GenericTraversableTemplate.class.genericBuilder(this);
         }

         public <A1, A2> Tuple2<Traversable<A1>, Traversable<A2>> unzip(Function1<String, Tuple2<A1, A2>> asPair) {
            return scala.collection.generic.GenericTraversableTemplate.class.unzip(this, asPair);
         }

         public <A1, A2, A3> Tuple3<Traversable<A1>, Traversable<A2>, Traversable<A3>> unzip3(Function1<String, Tuple3<A1, A2, A3>> asTriple) {
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

         public Traversable<String> thisCollection() {
            return scala.collection.TraversableLike.class.thisCollection(this);
         }

         public Traversable toCollection(Object repr) {
            return scala.collection.TraversableLike.class.toCollection(this, repr);
         }

         public Combiner<String, ParIterable<String>> parCombiner() {
            return scala.collection.TraversableLike.class.parCombiner(this);
         }

         public boolean isEmpty() {
            return scala.collection.TraversableLike.class.isEmpty(this);
         }

         public boolean hasDefiniteSize() {
            return scala.collection.TraversableLike.class.hasDefiniteSize(this);
         }

         public <B, That> That $plus$plus(GenTraversableOnce<B> that, CanBuildFrom<Traversable<String>, B, That> bf) {
            return scala.collection.TraversableLike.class.$plus$plus(this, that, bf);
         }

         public <B, That> That $plus$plus$colon(TraversableOnce<B> that, CanBuildFrom<Traversable<String>, B, That> bf) {
            return scala.collection.TraversableLike.class.$plus$plus$colon(this, that, bf);
         }

         public <B, That> That $plus$plus$colon(Traversable<B> that, CanBuildFrom<Traversable<String>, B, That> bf) {
            return scala.collection.TraversableLike.class.$plus$plus$colon(this, that, bf);
         }

         public <B, That> That map(Function1<String, B> f, CanBuildFrom<Traversable<String>, B, That> bf) {
            return scala.collection.TraversableLike.class.map(this, f, bf);
         }

         public <B, That> That flatMap(Function1<String, GenTraversableOnce<B>> f, CanBuildFrom<Traversable<String>, B, That> bf) {
            return scala.collection.TraversableLike.class.flatMap(this, f, bf);
         }

         public Object filter(Function1 p) {
            return scala.collection.TraversableLike.class.filter(this, p);
         }

         public Object filterNot(Function1 p) {
            return scala.collection.TraversableLike.class.filterNot(this, p);
         }

         public <B, That> That collect(PartialFunction<String, B> pf, CanBuildFrom<Traversable<String>, B, That> bf) {
            return scala.collection.TraversableLike.class.collect(this, pf, bf);
         }

         public Tuple2<Traversable<String>, Traversable<String>> partition(Function1<String, Object> p) {
            return scala.collection.TraversableLike.class.partition(this, p);
         }

         public <K> Map<K, Traversable<String>> groupBy(Function1<String, K> f) {
            return scala.collection.TraversableLike.class.groupBy(this, f);
         }

         public boolean forall(Function1<String, Object> p) {
            return scala.collection.TraversableLike.class.forall(this, p);
         }

         public boolean exists(Function1<String, Object> p) {
            return scala.collection.TraversableLike.class.exists(this, p);
         }

         public Option<String> find(Function1<String, Object> p) {
            return scala.collection.TraversableLike.class.find(this, p);
         }

         public <B, That> That scan(B z, Function2<B, B, B> op, CanBuildFrom<Traversable<String>, B, That> cbf) {
            return scala.collection.TraversableLike.class.scan(this, z, op, cbf);
         }

         public <B, That> That scanLeft(B z, Function2<B, String, B> op, CanBuildFrom<Traversable<String>, B, That> bf) {
            return scala.collection.TraversableLike.class.scanLeft(this, z, op, bf);
         }

         public <B, That> That scanRight(B z, Function2<String, B, B> op, CanBuildFrom<Traversable<String>, B, That> bf) {
            return scala.collection.TraversableLike.class.scanRight(this, z, op, bf);
         }

         public Object head() {
            return scala.collection.TraversableLike.class.head(this);
         }

         public Option<String> headOption() {
            return scala.collection.TraversableLike.class.headOption(this);
         }

         public Object tail() {
            return scala.collection.TraversableLike.class.tail(this);
         }

         public Object last() {
            return scala.collection.TraversableLike.class.last(this);
         }

         public Option<String> lastOption() {
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

         public Tuple2<Traversable<String>, Traversable<String>> span(Function1<String, Object> p) {
            return scala.collection.TraversableLike.class.span(this, p);
         }

         public Tuple2<Traversable<String>, Traversable<String>> splitAt(int n) {
            return scala.collection.TraversableLike.class.splitAt(this, n);
         }

         public Iterator<Traversable<String>> tails() {
            return scala.collection.TraversableLike.class.tails(this);
         }

         public Iterator<Traversable<String>> inits() {
            return scala.collection.TraversableLike.class.inits(this);
         }

         public <B> void copyToArray(Object xs, int start, int len) {
            scala.collection.TraversableLike.class.copyToArray(this, xs, start, len);
         }

         public Traversable<String> toTraversable() {
            return scala.collection.TraversableLike.class.toTraversable(this);
         }

         public Iterator<String> toIterator() {
            return scala.collection.TraversableLike.class.toIterator(this);
         }

         public Stream<String> toStream() {
            return scala.collection.TraversableLike.class.toStream(this);
         }

         public <Col> Col to(CanBuildFrom<scala.runtime.Nothing., String, Col> cbf) {
            return scala.collection.TraversableLike.class.to(this, cbf);
         }

         public String stringPrefix() {
            return scala.collection.TraversableLike.class.stringPrefix(this);
         }

         public Object view() {
            return scala.collection.TraversableLike.class.view(this);
         }

         public TraversableView<String, Traversable<String>> view(int from, int until) {
            return scala.collection.TraversableLike.class.view(this, from, until);
         }

         public FilterMonadic<String, Traversable<String>> withFilter(Function1<String, Object> p) {
            return scala.collection.TraversableLike.class.withFilter(this, p);
         }

         public Parallel par() {
            return scala.collection.Parallelizable.class.par(this);
         }

         public List<String> reversed() {
            return scala.collection.TraversableOnce.class.reversed(this);
         }

         public int size() {
            return scala.collection.TraversableOnce.class.size(this);
         }

         public boolean nonEmpty() {
            return scala.collection.TraversableOnce.class.nonEmpty(this);
         }

         public int count(Function1<String, Object> p) {
            return scala.collection.TraversableOnce.class.count(this, p);
         }

         public <B> Option<B> collectFirst(PartialFunction<String, B> pf) {
            return scala.collection.TraversableOnce.class.collectFirst(this, pf);
         }

         public <B> B $div$colon(B z, Function2<B, String, B> op) {
            return scala.collection.TraversableOnce.class.$div$colon(this, z, op);
         }

         public <B> B $colon$bslash(B z, Function2<String, B, B> op) {
            return scala.collection.TraversableOnce.class.$colon$bslash(this, z, op);
         }

         public <B> B foldLeft(B z, Function2<B, String, B> op) {
            return scala.collection.TraversableOnce.class.foldLeft(this, z, op);
         }

         public <B> B foldRight(B z, Function2<String, B, B> op) {
            return scala.collection.TraversableOnce.class.foldRight(this, z, op);
         }

         public <B> B reduceLeft(Function2<B, String, B> op) {
            return scala.collection.TraversableOnce.class.reduceLeft(this, op);
         }

         public <B> B reduceRight(Function2<String, B, B> op) {
            return scala.collection.TraversableOnce.class.reduceRight(this, op);
         }

         public <B> Option<B> reduceLeftOption(Function2<B, String, B> op) {
            return scala.collection.TraversableOnce.class.reduceLeftOption(this, op);
         }

         public <B> Option<B> reduceRightOption(Function2<String, B, B> op) {
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

         public <B> B aggregate(Function0<B> z, Function2<B, String, B> seqop, Function2<B, B, B> combop) {
            return scala.collection.TraversableOnce.class.aggregate(this, z, seqop, combop);
         }

         public <B> B sum(Numeric<B> num) {
            return scala.collection.TraversableOnce.class.sum(this, num);
         }

         public <B> B product(Numeric<B> num) {
            return scala.collection.TraversableOnce.class.product(this, num);
         }

         public Object min(Ordering cmp) {
            return scala.collection.TraversableOnce.class.min(this, cmp);
         }

         public Object max(Ordering cmp) {
            return scala.collection.TraversableOnce.class.max(this, cmp);
         }

         public Object maxBy(Function1 f, Ordering cmp) {
            return scala.collection.TraversableOnce.class.maxBy(this, f, cmp);
         }

         public Object minBy(Function1 f, Ordering cmp) {
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

         public List<String> toList() {
            return scala.collection.TraversableOnce.class.toList(this);
         }

         public Iterable<String> toIterable() {
            return scala.collection.TraversableOnce.class.toIterable(this);
         }

         public Seq<String> toSeq() {
            return scala.collection.TraversableOnce.class.toSeq(this);
         }

         public IndexedSeq<String> toIndexedSeq() {
            return scala.collection.TraversableOnce.class.toIndexedSeq(this);
         }

         public <B> Buffer<B> toBuffer() {
            return scala.collection.TraversableOnce.class.toBuffer(this);
         }

         public <B> Set<B> toSet() {
            return scala.collection.TraversableOnce.class.toSet(this);
         }

         public Vector<String> toVector() {
            return scala.collection.TraversableOnce.class.toVector(this);
         }

         public <T, U> Map<T, U> toMap(less<String, Tuple2<T, U>> ev) {
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

         public <U> void foreach(Function1<String, U> f) {
            this.read$1(f);
         }

         public String toString() {
            return (new StringContext(scala.Predef..MODULE$.wrapRefArray((Object[])(new String[]{"BufferedReaderLineIterator(", ")"})))).s(scala.Predef..MODULE$.genericWrapArray(new Object[]{this.reader$1}));
         }

         private final void read$1(Function1 f$1) {
            while(true) {
               String var3 = this.reader$1.readLine();
               if (var3 == null) {
                  BoxedUnit var4 = BoxedUnit.UNIT;
                  BoxedUnit var10000 = BoxedUnit.UNIT;
                  return;
               }

               f$1.apply(var3);
            }
         }

         public traverser$2$(BufferedReader reader$1) {
            this.reader$1 = reader$1;
            scala.collection.TraversableOnce.class.$init$(this);
            scala.collection.Parallelizable.class.$init$(this);
            scala.collection.TraversableLike.class.$init$(this);
            scala.collection.generic.GenericTraversableTemplate.class.$init$(this);
            scala.collection.GenTraversable.class.$init$(this);
            class.$init$(this);
         }
      }

      synchronized(this) {
         if (x$2.elem == null) {
            x$2.elem = new traverser$2$(x$1);
         }

         var10000 = BoxedUnit.UNIT;
      }

      var10000 = null;
      return (traverser$2$)x$2.elem;
   }

   public ManagedResource<BufferedOutputStream> bufferedOutputStream(Function0<OutputStream> out) {
      return package$.MODULE$.managed(out, Resource$.MODULE$.closeableResource(), scala.reflect.ClassManifestFactory..MODULE$.classType(OutputStream.class)).map(new Serializable() {
         public static final long serialVersionUID = 0L;

         public final BufferedOutputStream apply(OutputStream x$1) {
            return new BufferedOutputStream(x$1);
         }
      });
   }

   public ManagedResource<BufferedInputStream> bufferedInputStream(Function0<InputStream> in) {
      return package$.MODULE$.managed(in, Resource$.MODULE$.closeableResource(), scala.reflect.ClassManifestFactory..MODULE$.classType(InputStream.class)).map(new Serializable() {
         public static final long serialVersionUID = 0L;

         public final BufferedInputStream apply(InputStream x$2) {
            return new BufferedInputStream(x$2);
         }
      });
   }

   public <T> ManagedResource<T> file(Function1<File, T> in, File source, Resource<T> evidence$1, OptManifest<T> evidence$2) {
      return package$.MODULE$.managed(new Serializable(in, source) {
         public static final long serialVersionUID = 0L;
         private final Function1 in$1;
         private final File source$1;

         public final T apply() {
            return Using$.MODULE$.resource$Using$$open$1(this.in$1, this.source$1);
         }

         public {
            this.in$1 = in$1;
            this.source$1 = source$1;
         }
      }, evidence$1, evidence$2);
   }

   public ManagedResource<BufferedInputStream> fileInputStream(File source) {
      return this.file(new Serializable() {
         public static final long serialVersionUID = 0L;

         public final BufferedInputStream apply(File f) {
            return new BufferedInputStream(new FileInputStream(f));
         }
      }, source, Resource$.MODULE$.closeableResource(), scala.reflect.ClassManifestFactory..MODULE$.classType(BufferedInputStream.class));
   }

   public ManagedResource<BufferedOutputStream> fileOutputStream(File source) {
      return this.file(new Serializable() {
         public static final long serialVersionUID = 0L;

         public final BufferedOutputStream apply(File f) {
            return new BufferedOutputStream(new FileOutputStream(f));
         }
      }, source, Resource$.MODULE$.closeableResource(), scala.reflect.ClassManifestFactory..MODULE$.classType(BufferedOutputStream.class));
   }

   public ManagedResource<BufferedInputStream> urlInputStream(URL url) {
      return this.bufferedInputStream(new Serializable(url) {
         public static final long serialVersionUID = 0L;
         private final URL url$1;

         public final InputStream apply() {
            return this.url$1.openStream();
         }

         public {
            this.url$1 = url$1;
         }
      });
   }

   public ManagedResource<FileChannel> fileOuputChannel(File source) {
      return this.file(new Serializable() {
         public static final long serialVersionUID = 0L;

         public final FileChannel apply(File f) {
            return (new FileOutputStream(f)).getChannel();
         }
      }, source, Resource$.MODULE$.closeableResource(), scala.reflect.ClassManifestFactory..MODULE$.classType(FileChannel.class));
   }

   public ManagedResource<FileChannel> fileInputChannel(File source) {
      return this.file(new Serializable() {
         public static final long serialVersionUID = 0L;

         public final FileChannel apply(File f) {
            return (new FileInputStream(f)).getChannel();
         }
      }, source, Resource$.MODULE$.closeableResource(), scala.reflect.ClassManifestFactory..MODULE$.classType(FileChannel.class));
   }

   private Charset utf8() {
      return this.utf8;
   }

   public ManagedResource<BufferedWriter> fileWriter(Charset charset, boolean append, File source) {
      return this.file(new Serializable(charset, append) {
         public static final long serialVersionUID = 0L;
         private final Charset charset$1;
         private final boolean append$1;

         public final BufferedWriter apply(File f) {
            return new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f, this.append$1), this.charset$1));
         }

         public {
            this.charset$1 = charset$1;
            this.append$1 = append$1;
         }
      }, source, Resource$.MODULE$.closeableResource(), scala.reflect.ClassManifestFactory..MODULE$.classType(BufferedWriter.class));
   }

   public Charset fileWriter$default$1() {
      return this.utf8();
   }

   public boolean fileWriter$default$2() {
      return false;
   }

   public ManagedResource<BufferedReader> fileReader(Charset charset, File source) {
      return this.file(new Serializable(charset) {
         public static final long serialVersionUID = 0L;
         private final Charset charset$2;

         public final BufferedReader apply(File f) {
            return new BufferedReader(new InputStreamReader(new FileInputStream(f), this.charset$2));
         }

         public {
            this.charset$2 = charset$2;
         }
      }, source, Resource$.MODULE$.closeableResource(), scala.reflect.ClassManifestFactory..MODULE$.classType(BufferedReader.class));
   }

   public Traversable<String> fileLines(Charset charset, File source) {
      return this.fileReader(charset, source).map(new Serializable() {
         public static final long serialVersionUID = 0L;

         public final TraversableOnce<String> apply(BufferedReader reader) {
            return Using$.MODULE$.resource$Using$$makeBufferedReaderLineTraverser(reader);
         }
      }).toTraversable(scala.Predef..MODULE$.$conforms());
   }

   public ManagedResource<BufferedReader> urlReader(Charset charset, URL u) {
      return package$.MODULE$.managed(new Serializable(charset, u) {
         public static final long serialVersionUID = 0L;
         private final Charset charset$3;
         private final URL u$1;

         public final BufferedReader apply() {
            return new BufferedReader(new InputStreamReader(this.u$1.openStream(), this.charset$3));
         }

         public {
            this.charset$3 = charset$3;
            this.u$1 = u$1;
         }
      }, Resource$.MODULE$.closeableResource(), scala.reflect.ClassManifestFactory..MODULE$.classType(BufferedReader.class));
   }

   public ManagedResource<JarFile> jarFile(boolean verify, File source) {
      return this.file(new Serializable(verify) {
         public static final long serialVersionUID = 0L;
         private final boolean verify$1;

         public final JarFile apply(File f) {
            return new JarFile(f, this.verify$1);
         }

         public {
            this.verify$1 = verify$1;
         }
      }, source, Resource$.MODULE$.jarFileResource(), scala.reflect.ClassManifestFactory..MODULE$.classType(JarFile.class));
   }

   public ManagedResource<ZipFile> zipFile(File source) {
      return this.file(new Serializable() {
         public static final long serialVersionUID = 0L;

         public final ZipFile apply(File f) {
            return new ZipFile(f);
         }
      }, source, Resource$.MODULE$.closeableResource(), scala.reflect.ClassManifestFactory..MODULE$.classType(ZipFile.class));
   }

   public ManagedResource<Reader> streamReader(InputStream in, Charset charset) {
      return package$.MODULE$.managed(new Serializable(in, charset) {
         public static final long serialVersionUID = 0L;
         private final InputStream in$2;
         private final Charset charset$4;

         public final InputStreamReader apply() {
            return new InputStreamReader(this.in$2, this.charset$4);
         }

         public {
            this.in$2 = in$2;
            this.charset$4 = charset$4;
         }
      }, Resource$.MODULE$.closeableResource(), scala.reflect.ClassManifestFactory..MODULE$.classType(InputStreamReader.class));
   }

   public ManagedResource<GZIPInputStream> gzipInputStream(Function0<InputStream> in) {
      return package$.MODULE$.managed(new Serializable(in) {
         public static final long serialVersionUID = 0L;
         private final Function0 in$3;

         public final GZIPInputStream apply() {
            return new GZIPInputStream((InputStream)this.in$3.apply(), 8192);
         }

         public {
            this.in$3 = in$3;
         }
      }, Resource$.MODULE$.closeableResource(), scala.reflect.ClassManifestFactory..MODULE$.classType(GZIPInputStream.class));
   }

   public ManagedResource<ZipInputStream> zipInputStream(Function0<InputStream> in) {
      return package$.MODULE$.managed(new Serializable(in) {
         public static final long serialVersionUID = 0L;
         private final Function0 in$4;

         public final ZipInputStream apply() {
            return new ZipInputStream((InputStream)this.in$4.apply());
         }

         public {
            this.in$4 = in$4;
         }
      }, Resource$.MODULE$.closeableResource(), scala.reflect.ClassManifestFactory..MODULE$.classType(ZipInputStream.class));
   }

   public ManagedResource<ZipOutputStream> zipOutputStream(Function0<OutputStream> out) {
      return package$.MODULE$.managed(new Serializable(out) {
         public static final long serialVersionUID = 0L;
         private final Function0 out$1;

         public final ZipOutputStream apply() {
            return new ZipOutputStream((OutputStream)this.out$1.apply());
         }

         public {
            this.out$1 = out$1;
         }
      }, Resource$.MODULE$.closeableResource(), scala.reflect.ClassManifestFactory..MODULE$.classType(ZipOutputStream.class));
   }

   public ManagedResource<GZIPOutputStream> gzipOutputStream(Function0<OutputStream> out) {
      return package$.MODULE$.managed(new Serializable(out) {
         public static final long serialVersionUID = 0L;
         private final Function0 out$2;

         public final GZIPOutputStream apply() {
            return new GZIPOutputStream((OutputStream)this.out$2.apply(), 8192);
         }

         public {
            this.out$2 = out$2;
         }
      }, Resource$.MODULE$.gzipOuputStraemResource(), scala.reflect.ClassManifestFactory..MODULE$.classType(GZIPOutputStream.class));
   }

   public ManagedResource<JarOutputStream> jarOutputStream(Function0<OutputStream> out) {
      return package$.MODULE$.managed(new Serializable(out) {
         public static final long serialVersionUID = 0L;
         private final Function0 out$3;

         public final JarOutputStream apply() {
            return new JarOutputStream((OutputStream)this.out$3.apply());
         }

         public {
            this.out$3 = out$3;
         }
      }, Resource$.MODULE$.closeableResource(), scala.reflect.ClassManifestFactory..MODULE$.classType(JarOutputStream.class));
   }

   public ManagedResource<JarInputStream> jarInputStream(Function0<InputStream> in) {
      return package$.MODULE$.managed(new Serializable(in) {
         public static final long serialVersionUID = 0L;
         private final Function0 in$5;

         public final JarInputStream apply() {
            return new JarInputStream((InputStream)this.in$5.apply());
         }

         public {
            this.in$5 = in$5;
         }
      }, Resource$.MODULE$.closeableResource(), scala.reflect.ClassManifestFactory..MODULE$.classType(JarInputStream.class));
   }

   public ManagedResource<InputStream> zipEntry(ZipFile zip, ZipEntry entry) {
      return package$.MODULE$.managed(new Serializable(zip, entry) {
         public static final long serialVersionUID = 0L;
         private final ZipFile zip$1;
         private final ZipEntry entry$1;

         public final InputStream apply() {
            return this.zip$1.getInputStream(this.entry$1);
         }

         public {
            this.zip$1 = zip$1;
            this.entry$1 = entry$1;
         }
      }, Resource$.MODULE$.closeableResource(), scala.reflect.ClassManifestFactory..MODULE$.classType(InputStream.class));
   }

   public TraversableOnce<String> resource$Using$$makeBufferedReaderLineTraverser(BufferedReader reader) {
      VolatileObjectRef traverser$module = VolatileObjectRef.zero();
      return this.traverser$1(reader, traverser$module);
   }

   public final Object resource$Using$$open$1(Function1 in$1, File source$1) {
      File parent = source$1.getParentFile();
      if (parent == null) {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         BoxesRunTime.boxToBoolean(parent.mkdirs());
      }

      return in$1.apply(source$1);
   }

   private final traverser$2$ traverser$1(BufferedReader reader$1, VolatileObjectRef traverser$module$1) {
      return traverser$module$1.elem == null ? this.traverser$1$lzycompute(reader$1, traverser$module$1) : (traverser$2$)traverser$module$1.elem;
   }

   private Using$() {
      MODULE$ = this;
      this.utf8 = Charset.forName("UTF-8");
   }
}
