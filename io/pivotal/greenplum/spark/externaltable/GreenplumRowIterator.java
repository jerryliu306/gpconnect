package io.pivotal.greenplum.spark.externaltable;

import com.typesafe.scalalogging.LazyLogging;
import com.typesafe.scalalogging.LazyLogging$class;
import com.typesafe.scalalogging.Logger;
import io.pivotal.greenplum.spark.ConnectorUtils$;
import io.pivotal.greenplum.spark.GpfdistLocation;
import io.pivotal.greenplum.spark.GreenplumPartition;
import io.pivotal.greenplum.spark.conf.GreenplumOptions;
import io.pivotal.greenplum.spark.jdbc.ConnectionManager$;
import io.pivotal.greenplum.spark.jdbc.Jdbc$;
import java.io.InputStream;
import java.sql.Connection;
import org.apache.spark.sql.catalyst.InternalRow;
import org.apache.spark.sql.catalyst.expressions.SpecificInternalRow;
import org.apache.spark.sql.sources.Filter;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import org.postgresql.util.PSQLException;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.Option;
import scala.PartialFunction;
import scala.Serializable;
import scala.StringContext;
import scala.Tuple2;
import scala.Predef..less.colon.less;
import scala.collection.BufferedIterator;
import scala.collection.GenTraversableOnce;
import scala.collection.Iterable;
import scala.collection.Iterator;
import scala.collection.Seq;
import scala.collection.Traversable;
import scala.collection.Iterator.class;
import scala.collection.generic.CanBuildFrom;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.collection.immutable.Set;
import scala.collection.immutable.Stream;
import scala.collection.immutable.Vector;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.StringBuilder;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Nothing.;
import scala.util.Failure;
import scala.util.Success;
import scala.util.Try;

@ScalaSignature(
   bytes = "\u0006\u0001\u0005\re\u0001B\u0001\u0003\u00015\u0011Ac\u0012:fK:\u0004H.^7S_^LE/\u001a:bi>\u0014(BA\u0002\u0005\u00035)\u0007\u0010^3s]\u0006dG/\u00192mK*\u0011QAB\u0001\u0006gB\f'o\u001b\u0006\u0003\u000f!\t\u0011b\u001a:fK:\u0004H.^7\u000b\u0005%Q\u0011a\u00029jm>$\u0018\r\u001c\u0006\u0002\u0017\u0005\u0011\u0011n\\\u0002\u0001'\u0011\u0001a\u0002F\u0017\u0011\u0005=\u0011R\"\u0001\t\u000b\u0003E\tQa]2bY\u0006L!a\u0005\t\u0003\r\u0005s\u0017PU3g!\r)R\u0004\t\b\u0003-mq!a\u0006\u000e\u000e\u0003aQ!!\u0007\u0007\u0002\rq\u0012xn\u001c;?\u0013\u0005\t\u0012B\u0001\u000f\u0011\u0003\u001d\u0001\u0018mY6bO\u0016L!AH\u0010\u0003\u0011%#XM]1u_JT!\u0001\b\t\u0011\u0005\u0005ZS\"\u0001\u0012\u000b\u0005\r\"\u0013\u0001C2bi\u0006d\u0017p\u001d;\u000b\u0005\u00152\u0013aA:rY*\u0011Qa\n\u0006\u0003Q%\na!\u00199bG\",'\"\u0001\u0016\u0002\u0007=\u0014x-\u0003\u0002-E\tY\u0011J\u001c;fe:\fGNU8x!\tqS'D\u00010\u0015\t\u0001\u0014'\u0001\u0007tG\u0006d\u0017\r\\8hO&twM\u0003\u00023g\u0005AA/\u001f9fg\u00064WMC\u00015\u0003\r\u0019w.\\\u0005\u0003m=\u00121\u0002T1{s2{wmZ5oO\"A\u0001\b\u0001B\u0001B\u0003%\u0011(A\u0007baBd\u0017nY1uS>t\u0017\n\u001a\t\u0003uur!aD\u001e\n\u0005q\u0002\u0012A\u0002)sK\u0012,g-\u0003\u0002?\u007f\t11\u000b\u001e:j]\u001eT!\u0001\u0010\t\t\u0011\u0005\u0003!\u0011!Q\u0001\n\t\u000b\u0011\u0002]1si&$\u0018n\u001c8\u0011\u0005\r#U\"\u0001\u0003\n\u0005\u0015#!AE$sK\u0016t\u0007\u000f\\;n!\u0006\u0014H/\u001b;j_:D\u0001b\u0012\u0001\u0003\u0002\u0003\u0006I\u0001S\u0001\u0007g\u000eDW-\\1\u0011\u0005%cU\"\u0001&\u000b\u0005-#\u0013!\u0002;za\u0016\u001c\u0018BA'K\u0005)\u0019FO];diRK\b/\u001a\u0005\t\u001f\u0002\u0011\t\u0011)A\u0005!\u0006\u0001rM]3f]BdW/\\(qi&|gn\u001d\t\u0003#Rk\u0011A\u0015\u0006\u0003'\u0012\tAaY8oM&\u0011QK\u0015\u0002\u0011\u000fJ,WM\u001c9mk6|\u0005\u000f^5p]ND\u0001b\u0016\u0001\u0003\u0002\u0003\u0006I\u0001W\u0001\u000bC2d7i\u001c7v[:\u001c\bcA\u000bZs%\u0011!l\b\u0002\u0004'\u0016\f\b\u0002\u0003/\u0001\u0005\u0003\u0005\u000b\u0011B/\u0002\u000f\u0019LG\u000e^3sgB\u0019qB\u00181\n\u0005}\u0003\"!B!se\u0006L\bCA1e\u001b\u0005\u0011'BA2%\u0003\u001d\u0019x.\u001e:dKNL!!\u001a2\u0003\r\u0019KG\u000e^3s\u0011\u00159\u0007\u0001\"\u0001i\u0003\u0019a\u0014N\\5u}Q9\u0011n\u001b7n]>\u0004\bC\u00016\u0001\u001b\u0005\u0011\u0001\"\u0002\u001dg\u0001\u0004I\u0004\"B!g\u0001\u0004\u0011\u0005\"B$g\u0001\u0004A\u0005\"B(g\u0001\u0004\u0001\u0006\"B,g\u0001\u0004A\u0006\"\u0002/g\u0001\u0004i\u0006b\u0002:\u0001\u0005\u0004%Ia]\u0001\u0005G>tg.F\u0001u!\t)\u00180D\u0001w\u0015\t)sOC\u0001y\u0003\u0011Q\u0017M^1\n\u0005i4(AC\"p]:,7\r^5p]\"1A\u0010\u0001Q\u0001\nQ\fQaY8o]\u0002BqA \u0001C\u0002\u0013%q0A\tgS2$XM],iKJ,7\t\\1vg\u0016,\u0012!\u000f\u0005\b\u0003\u0007\u0001\u0001\u0015!\u0003:\u0003I1\u0017\u000e\u001c;fe^CWM]3DY\u0006,8/\u001a\u0011\t\u0011\u0005\u001d\u0001A1A\u0005\n}\fab\u001e5fe\u0016\u0004&/\u001a3jG\u0006$X\rC\u0004\u0002\f\u0001\u0001\u000b\u0011B\u001d\u0002\u001f]DWM]3Qe\u0016$\u0017nY1uK\u0002B\u0011\"a\u0004\u0001\u0005\u0004%\t!!\u0005\u0002\u0019\u0011\fG/Y%uKJ\fGo\u001c:\u0016\u0005\u0005M\u0001c\u00016\u0002\u0016%\u0019\u0011q\u0003\u0002\u0003\u0019\u0011\u000bG/Y%uKJ\fGo\u001c:\t\u0011\u0005m\u0001\u0001)A\u0005\u0003'\tQ\u0002Z1uC&#XM]1u_J\u0004\u0003\"CA\u0010\u0001\u0001\u0007I\u0011AA\u0011\u00039\u0001(o\\2fgN,GmQ8v]R,\"!a\t\u0011\u0007=\t)#C\u0002\u0002(A\u00111!\u00138u\u0011%\tY\u0003\u0001a\u0001\n\u0003\ti#\u0001\nqe>\u001cWm]:fI\u000e{WO\u001c;`I\u0015\fH\u0003BA\u0018\u0003k\u00012aDA\u0019\u0013\r\t\u0019\u0004\u0005\u0002\u0005+:LG\u000f\u0003\u0006\u00028\u0005%\u0012\u0011!a\u0001\u0003G\t1\u0001\u001f\u00132\u0011!\tY\u0004\u0001Q!\n\u0005\r\u0012a\u00049s_\u000e,7o]3e\u0007>,h\u000e\u001e\u0011\t\u0013\u0005}\u0002A1A\u0005\u0002\u0005\u0005\u0013AC2p]Z,'\u000f^3sgV\u0011\u00111\t\t\u0005\u001fy\u000b)\u0005\u0005\u0005\u0010\u0003\u000fJ\u00141JA\u0018\u0013\r\tI\u0005\u0005\u0002\n\rVt7\r^5p]J\u0002B!!\u0014\u0002T5\u0011\u0011q\n\u0006\u0004\u0003#\u0012\u0013aC3yaJ,7o]5p]NLA!!\u0016\u0002P\t\u00192\u000b]3dS\u001aL7-\u00138uKJt\u0017\r\u001c*po\"A\u0011\u0011\f\u0001!\u0002\u0013\t\u0019%A\u0006d_:4XM\u001d;feN\u0004\u0003bBA/\u0001\u0011\u0005\u0013qL\u0001\bQ\u0006\u001ch*\u001a=u+\t\t\t\u0007E\u0002\u0010\u0003GJ1!!\u001a\u0011\u0005\u001d\u0011un\u001c7fC:Dq!!\u001b\u0001\t\u0003\nY'\u0001\u0003oKb$H#\u0001\u0011\b\u000f\u0005=$\u0001#\u0001\u0002r\u0005!rI]3f]BdW/\u001c*po&#XM]1u_J\u00042A[A:\r\u0019\t!\u0001#\u0001\u0002vM\u0019\u00111\u000f\b\t\u000f\u001d\f\u0019\b\"\u0001\u0002zQ\u0011\u0011\u0011\u000f\u0005\t\u0003{\n\u0019\b\"\u0001\u0002\u0000\u0005!b-\u001b7uKJ<\u0006.\u001a:f!J,G-[2bi\u0016$2!OAA\u0011\u0019a\u00161\u0010a\u0001;\u0002"
)
public class GreenplumRowIterator implements Iterator<InternalRow>, LazyLogging {
   private final GreenplumPartition partition;
   public final StructType io$pivotal$greenplum$spark$externaltable$GreenplumRowIterator$$schema;
   public final GreenplumOptions io$pivotal$greenplum$spark$externaltable$GreenplumRowIterator$$greenplumOptions;
   private final Seq<String> allColumns;
   private final Connection conn;
   private final String filterWhereClause;
   private final String wherePredicate;
   private final DataIterator dataIterator;
   private int processedCount;
   private final Function2<String, SpecificInternalRow, BoxedUnit>[] converters;
   private final transient Logger logger;
   private transient volatile boolean bitmap$trans$0;

   public static String filterWherePredicate(Filter[] var0) {
      return GreenplumRowIterator$.MODULE$.filterWherePredicate(var0);
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

   public Iterator<InternalRow> seq() {
      return class.seq(this);
   }

   public boolean isEmpty() {
      return class.isEmpty(this);
   }

   public boolean isTraversableAgain() {
      return class.isTraversableAgain(this);
   }

   public boolean hasDefiniteSize() {
      return class.hasDefiniteSize(this);
   }

   public Iterator<InternalRow> take(int n) {
      return class.take(this, n);
   }

   public Iterator<InternalRow> drop(int n) {
      return class.drop(this, n);
   }

   public Iterator<InternalRow> slice(int from, int until) {
      return class.slice(this, from, until);
   }

   public <B> Iterator<B> map(Function1<InternalRow, B> f) {
      return class.map(this, f);
   }

   public <B> Iterator<B> $plus$plus(Function0<GenTraversableOnce<B>> that) {
      return class.$plus$plus(this, that);
   }

   public <B> Iterator<B> flatMap(Function1<InternalRow, GenTraversableOnce<B>> f) {
      return class.flatMap(this, f);
   }

   public Iterator<InternalRow> filter(Function1<InternalRow, Object> p) {
      return class.filter(this, p);
   }

   public <B> boolean corresponds(GenTraversableOnce<B> that, Function2<InternalRow, B, Object> p) {
      return class.corresponds(this, that, p);
   }

   public Iterator<InternalRow> withFilter(Function1<InternalRow, Object> p) {
      return class.withFilter(this, p);
   }

   public Iterator<InternalRow> filterNot(Function1<InternalRow, Object> p) {
      return class.filterNot(this, p);
   }

   public <B> Iterator<B> collect(PartialFunction<InternalRow, B> pf) {
      return class.collect(this, pf);
   }

   public <B> Iterator<B> scanLeft(B z, Function2<B, InternalRow, B> op) {
      return class.scanLeft(this, z, op);
   }

   public <B> Iterator<B> scanRight(B z, Function2<InternalRow, B, B> op) {
      return class.scanRight(this, z, op);
   }

   public Iterator<InternalRow> takeWhile(Function1<InternalRow, Object> p) {
      return class.takeWhile(this, p);
   }

   public Tuple2<Iterator<InternalRow>, Iterator<InternalRow>> partition(Function1<InternalRow, Object> p) {
      return class.partition(this, p);
   }

   public Tuple2<Iterator<InternalRow>, Iterator<InternalRow>> span(Function1<InternalRow, Object> p) {
      return class.span(this, p);
   }

   public Iterator<InternalRow> dropWhile(Function1<InternalRow, Object> p) {
      return class.dropWhile(this, p);
   }

   public <B> Iterator<Tuple2<InternalRow, B>> zip(Iterator<B> that) {
      return class.zip(this, that);
   }

   public <A1> Iterator<A1> padTo(int len, A1 elem) {
      return class.padTo(this, len, elem);
   }

   public Iterator<Tuple2<InternalRow, Object>> zipWithIndex() {
      return class.zipWithIndex(this);
   }

   public <B, A1, B1> Iterator<Tuple2<A1, B1>> zipAll(Iterator<B> that, A1 thisElem, B1 thatElem) {
      return class.zipAll(this, that, thisElem, thatElem);
   }

   public <U> void foreach(Function1<InternalRow, U> f) {
      class.foreach(this, f);
   }

   public boolean forall(Function1<InternalRow, Object> p) {
      return class.forall(this, p);
   }

   public boolean exists(Function1<InternalRow, Object> p) {
      return class.exists(this, p);
   }

   public boolean contains(Object elem) {
      return class.contains(this, elem);
   }

   public Option<InternalRow> find(Function1<InternalRow, Object> p) {
      return class.find(this, p);
   }

   public int indexWhere(Function1<InternalRow, Object> p) {
      return class.indexWhere(this, p);
   }

   public <B> int indexOf(B elem) {
      return class.indexOf(this, elem);
   }

   public BufferedIterator<InternalRow> buffered() {
      return class.buffered(this);
   }

   public <B> Iterator<InternalRow>.GroupedIterator<B> grouped(int size) {
      return class.grouped(this, size);
   }

   public <B> Iterator<InternalRow>.GroupedIterator<B> sliding(int size, int step) {
      return class.sliding(this, size, step);
   }

   public int length() {
      return class.length(this);
   }

   public Tuple2<Iterator<InternalRow>, Iterator<InternalRow>> duplicate() {
      return class.duplicate(this);
   }

   public <B> Iterator<B> patch(int from, Iterator<B> patchElems, int replaced) {
      return class.patch(this, from, patchElems, replaced);
   }

   public <B> void copyToArray(Object xs, int start, int len) {
      class.copyToArray(this, xs, start, len);
   }

   public boolean sameElements(Iterator<?> that) {
      return class.sameElements(this, that);
   }

   public Traversable<InternalRow> toTraversable() {
      return class.toTraversable(this);
   }

   public Iterator<InternalRow> toIterator() {
      return class.toIterator(this);
   }

   public Stream<InternalRow> toStream() {
      return class.toStream(this);
   }

   public String toString() {
      return class.toString(this);
   }

   public <B> int sliding$default$2() {
      return class.sliding$default$2(this);
   }

   public List<InternalRow> reversed() {
      return scala.collection.TraversableOnce.class.reversed(this);
   }

   public int size() {
      return scala.collection.TraversableOnce.class.size(this);
   }

   public boolean nonEmpty() {
      return scala.collection.TraversableOnce.class.nonEmpty(this);
   }

   public int count(Function1<InternalRow, Object> p) {
      return scala.collection.TraversableOnce.class.count(this, p);
   }

   public <B> Option<B> collectFirst(PartialFunction<InternalRow, B> pf) {
      return scala.collection.TraversableOnce.class.collectFirst(this, pf);
   }

   public <B> B $div$colon(B z, Function2<B, InternalRow, B> op) {
      return scala.collection.TraversableOnce.class.$div$colon(this, z, op);
   }

   public <B> B $colon$bslash(B z, Function2<InternalRow, B, B> op) {
      return scala.collection.TraversableOnce.class.$colon$bslash(this, z, op);
   }

   public <B> B foldLeft(B z, Function2<B, InternalRow, B> op) {
      return scala.collection.TraversableOnce.class.foldLeft(this, z, op);
   }

   public <B> B foldRight(B z, Function2<InternalRow, B, B> op) {
      return scala.collection.TraversableOnce.class.foldRight(this, z, op);
   }

   public <B> B reduceLeft(Function2<B, InternalRow, B> op) {
      return scala.collection.TraversableOnce.class.reduceLeft(this, op);
   }

   public <B> B reduceRight(Function2<InternalRow, B, B> op) {
      return scala.collection.TraversableOnce.class.reduceRight(this, op);
   }

   public <B> Option<B> reduceLeftOption(Function2<B, InternalRow, B> op) {
      return scala.collection.TraversableOnce.class.reduceLeftOption(this, op);
   }

   public <B> Option<B> reduceRightOption(Function2<InternalRow, B, B> op) {
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

   public <B> B aggregate(Function0<B> z, Function2<B, InternalRow, B> seqop, Function2<B, B, B> combop) {
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

   public List<InternalRow> toList() {
      return scala.collection.TraversableOnce.class.toList(this);
   }

   public Iterable<InternalRow> toIterable() {
      return scala.collection.TraversableOnce.class.toIterable(this);
   }

   public Seq<InternalRow> toSeq() {
      return scala.collection.TraversableOnce.class.toSeq(this);
   }

   public IndexedSeq<InternalRow> toIndexedSeq() {
      return scala.collection.TraversableOnce.class.toIndexedSeq(this);
   }

   public <B> Buffer<B> toBuffer() {
      return scala.collection.TraversableOnce.class.toBuffer(this);
   }

   public <B> Set<B> toSet() {
      return scala.collection.TraversableOnce.class.toSet(this);
   }

   public Vector<InternalRow> toVector() {
      return scala.collection.TraversableOnce.class.toVector(this);
   }

   public <Col> Col to(CanBuildFrom<, InternalRow, Col> cbf) {
      return scala.collection.TraversableOnce.class.to(this, cbf);
   }

   public <T, U> Map<T, U> toMap(less<InternalRow, Tuple2<T, U>> ev) {
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

   private Connection conn() {
      return this.conn;
   }

   private String filterWhereClause() {
      return this.filterWhereClause;
   }

   private String wherePredicate() {
      return this.wherePredicate;
   }

   public DataIterator dataIterator() {
      return this.dataIterator;
   }

   public int processedCount() {
      return this.processedCount;
   }

   public void processedCount_$eq(int x$1) {
      this.processedCount = x$1;
   }

   public Function2<String, SpecificInternalRow, BoxedUnit>[] converters() {
      return this.converters;
   }

   public boolean hasNext() {
      return this.dataIterator().hasNext();
   }

   public InternalRow next() {
      SpecificInternalRow row = new SpecificInternalRow(scala.Predef..MODULE$.wrapRefArray((Object[])scala.Predef..MODULE$.refArrayOps((Object[])this.io$pivotal$greenplum$spark$externaltable$GreenplumRowIterator$$schema.fields()).map(new Serializable(this) {
         public static final long serialVersionUID = 0L;

         public final DataType apply(StructField x) {
            return x.dataType();
         }
      }, scala.Array..MODULE$.canBuildFrom(scala.reflect.ClassTag..MODULE$.apply(DataType.class)))));

      try {
         String[] record = this.dataIterator().next();
         scala.Predef..MODULE$.refArrayOps((Object[])scala.Predef..MODULE$.refArrayOps((Object[])record).zipWithIndex(scala.Array..MODULE$.canBuildFrom(scala.reflect.ClassTag..MODULE$.apply(Tuple2.class)))).foreach(new Serializable(this, row) {
            public static final long serialVersionUID = 0L;
            // $FF: synthetic field
            private final GreenplumRowIterator $outer;
            private final SpecificInternalRow row$1;

            public final void apply(Tuple2<String, Object> x0$1) {
               if (x0$1 != null) {
                  String value = (String)x0$1._1();
                  int index = x0$1._2$mcI$sp();
                  BoxedUnit var10000;
                  if (value == null) {
                     if (!this.$outer.io$pivotal$greenplum$spark$externaltable$GreenplumRowIterator$$schema.fields()[index].nullable()) {
                        String message = (new StringBuilder()).append((new StringContext(scala.Predef..MODULE$.wrapRefArray((Object[])(new String[]{"The ", " column in "})))).s(scala.Predef..MODULE$.genericWrapArray(new Object[]{this.$outer.io$pivotal$greenplum$spark$externaltable$GreenplumRowIterator$$schema.fields()[index].name()}))).append((new StringContext(scala.Predef..MODULE$.wrapRefArray((Object[])(new String[]{"", " table should not have null value."})))).s(scala.Predef..MODULE$.genericWrapArray(new Object[]{this.$outer.io$pivotal$greenplum$spark$externaltable$GreenplumRowIterator$$greenplumOptions.dbTable()}))).toString();
                        throw new RuntimeException(message);
                     }

                     var10000 = BoxedUnit.UNIT;
                  } else if (index < this.$outer.converters().length) {
                     var10000 = (BoxedUnit)this.$outer.converters()[index].apply(value, this.row$1);
                  } else {
                     var10000 = BoxedUnit.UNIT;
                  }

               } else {
                  throw new MatchError(x0$1);
               }
            }

            public {
               if ($outer == null) {
                  throw null;
               } else {
                  this.$outer = $outer;
                  this.row$1 = row$1;
               }
            }
         });
      } catch (Exception var4) {
         this.dataIterator().closeIfNeeded();
         throw var4;
      }

      this.processedCount_$eq(this.processedCount() + 1);
      BoxedUnit var10000;
      if (this.processedCount() % 100000 == 0) {
         if (this.logger().underlying().isDebugEnabled()) {
            this.logger().underlying().debug("partition.index = {} processedCount = {}", (Object[])(new Object[]{BoxesRunTime.boxToInteger(this.partition.index()), BoxesRunTime.boxToInteger(this.processedCount())}));
            var10000 = BoxedUnit.UNIT;
         } else {
            var10000 = BoxedUnit.UNIT;
         }
      } else {
         var10000 = BoxedUnit.UNIT;
      }

      return row;
   }

   private final void liftedTree1$1(GreenplumQualifiedName.Table internalTable$1, GreenplumQualifiedName.TempTable externalTable$1, GpfdistService service$1, String txId$1) {
      try {
         Jdbc$.MODULE$.copyTableToExternal(this.conn(), internalTable$1, externalTable$1, this.wherePredicate(), this.allColumns);
         this.conn().commit();
      } catch (PSQLException var9) {
         Try var6 = service$1.getReceivedDataFor(txId$1);
         if (var6 instanceof Failure) {
            Failure var7 = (Failure)var6;
            Throwable error = var7.exception();
            throw error;
         } else {
            throw var9;
         }
      }
   }

   public GreenplumRowIterator(String applicationId, GreenplumPartition partition, StructType schema, GreenplumOptions greenplumOptions, Seq<String> allColumns, Filter[] filters) {
      this.partition = partition;
      this.io$pivotal$greenplum$spark$externaltable$GreenplumRowIterator$$schema = schema;
      this.io$pivotal$greenplum$spark$externaltable$GreenplumRowIterator$$greenplumOptions = greenplumOptions;
      this.allColumns = allColumns;
      scala.collection.TraversableOnce.class.$init$(this);
      class.$init$(this);
      LazyLogging$class.$init$(this);
      this.conn = ConnectionManager$.MODULE$.getConnection(greenplumOptions, false);
      this.filterWhereClause = GreenplumRowIterator$.MODULE$.filterWherePredicate(filters);
      BoxedUnit var10001;
      String var26;
      if (this.filterWhereClause().length() > 0) {
         if (this.logger().underlying().isDebugEnabled()) {
            this.logger().underlying().debug("Including '{}' in WHERE clause", this.filterWhereClause());
            var10001 = BoxedUnit.UNIT;
         } else {
            var10001 = BoxedUnit.UNIT;
         }

         var26 = (new StringContext(scala.Predef..MODULE$.wrapRefArray((Object[])(new String[]{"(", ") AND (", ")"})))).s(scala.Predef..MODULE$.genericWrapArray(new Object[]{this.filterWhereClause(), partition.whereClause()}));
      } else {
         var26 = partition.whereClause();
      }

      this.wherePredicate = var26;

      Connection var27;
      try {
         long threadId = Thread.currentThread().getId();
         String executorId = org.apache.spark.SparkEnv..MODULE$.get().executorId();
         String externalTableName = GreenplumTableManager$.MODULE$.generateExternalTableName(applicationId, greenplumOptions.dbTable(), executorId, threadId, allColumns);
         GreenplumQualifiedName.Table internalTable = GreenplumQualifiedName$.MODULE$.forTable(greenplumOptions.dbSchema(), greenplumOptions.dbTable());
         GreenplumQualifiedName.TempTable externalTable = GreenplumQualifiedName$.MODULE$.forTempTable(externalTableName);
         GpfdistService service = GpfdistServiceManager$.MODULE$.getService(greenplumOptions.connectorOptions());
         if (!Jdbc$.MODULE$.externalTableExists(this.conn(), externalTable)) {
            if (this.logger().underlying().isDebugEnabled()) {
               this.logger().underlying().debug((new StringBuilder()).append((new StringContext(scala.Predef..MODULE$.wrapRefArray((Object[])(new String[]{"Temporary external table ", " not found, "})))).s(scala.Predef..MODULE$.genericWrapArray(new Object[]{externalTable}))).append((new StringContext(scala.Predef..MODULE$.wrapRefArray((Object[])(new String[]{"creating table w/ port=", ", columns=", ""})))).s(scala.Predef..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(service.getPort()), allColumns.mkString(",")}))).toString());
               var10001 = BoxedUnit.UNIT;
            } else {
               var10001 = BoxedUnit.UNIT;
            }

            String serverAddress = ConnectorUtils$.MODULE$.getServerAddress(greenplumOptions.connectorOptions(), ConnectorUtils$.MODULE$.getServerAddress$default$2());
            int port = service.getPort();
            Jdbc$.MODULE$.createGpfdistWritableExternalTable(this.conn(), internalTable, externalTable, new GpfdistLocation(serverAddress, port), allColumns);
         }

         String txId = Jdbc$.MODULE$.getDistributedTransactionId(this.conn());
         if (this.logger().underlying().isDebugEnabled()) {
            this.logger().underlying().debug((new StringBuilder()).append((new StringContext(scala.Predef..MODULE$.wrapRefArray((Object[])(new String[]{"Distributed transaction id is ", ""})))).s(scala.Predef..MODULE$.genericWrapArray(new Object[]{txId}))).append((new StringContext(scala.Predef..MODULE$.wrapRefArray((Object[])(new String[]{" for a partition ", ""})))).s(scala.Predef..MODULE$.genericWrapArray(new Object[]{partition.whereClause()}))).toString());
            var10001 = BoxedUnit.UNIT;
         } else {
            var10001 = BoxedUnit.UNIT;
         }

         this.liftedTree1$1(internalTable, externalTable, service, txId);
         Try var18 = service.getReceivedDataFor(txId);
         if (!(var18 instanceof Success)) {
            if (var18 instanceof Failure) {
               Failure var22 = (Failure)var18;
               Throwable error = var22.exception();
               if (this.logger().underlying().isErrorEnabled()) {
                  this.logger().underlying().error("Table copy succeeded but there was an error in the HTTP handler; this should not happen");
                  var10001 = BoxedUnit.UNIT;
               } else {
                  var10001 = BoxedUnit.UNIT;
               }

               if (this.logger().underlying().isErrorEnabled()) {
                  this.logger().underlying().error(error.getMessage());
                  var10001 = BoxedUnit.UNIT;
               } else {
                  var10001 = BoxedUnit.UNIT;
               }

               throw error;
            }

            throw new MatchError(var18);
         }

         Success var19 = (Success)var18;
         InputStream data = (InputStream)var19.value();
         if (this.logger().underlying().isDebugEnabled()) {
            this.logger().underlying().debug("Reading table data for {}", txId);
            var10001 = BoxedUnit.UNIT;
         } else {
            var10001 = BoxedUnit.UNIT;
         }

         DataIterator var21 = new DataIterator(data);
      } finally {
         this.conn().rollback();
         var27 = this.conn();
         var27.close();
      }

      this.dataIterator = var27;
      this.processedCount = 0;
      this.converters = DataTypeConverterFactory$.MODULE$.create(schema);
   }
}
