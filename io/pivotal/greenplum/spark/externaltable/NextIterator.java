package io.pivotal.greenplum.spark.externaltable;

import java.util.NoSuchElementException;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.PartialFunction;
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
import scala.runtime.Nothing.;

@ScalaSignature(
   bytes = "\u0006\u0001\u00194a!\u0001\u0002\u0002\u0002\u0011a!\u0001\u0004(fqRLE/\u001a:bi>\u0014(BA\u0002\u0005\u00035)\u0007\u0010^3s]\u0006dG/\u00192mK*\u0011QAB\u0001\u0006gB\f'o\u001b\u0006\u0003\u000f!\t\u0011b\u001a:fK:\u0004H.^7\u000b\u0005%Q\u0011a\u00029jm>$\u0018\r\u001c\u0006\u0002\u0017\u0005\u0011\u0011n\\\u000b\u0003\u001b\r\u001a2\u0001\u0001\b\u0015!\ty!#D\u0001\u0011\u0015\u0005\t\u0012!B:dC2\f\u0017BA\n\u0011\u0005\u0019\te.\u001f*fMB\u0019QCH\u0011\u000f\u0005YabBA\f\u001c\u001b\u0005A\"BA\r\u001b\u0003\u0019a$o\\8u}\r\u0001\u0011\"A\t\n\u0005u\u0001\u0012a\u00029bG.\fw-Z\u0005\u0003?\u0001\u0012\u0001\"\u0013;fe\u0006$xN\u001d\u0006\u0003;A\u0001\"AI\u0012\r\u0001\u0011)A\u0005\u0001b\u0001K\t\tQ+\u0005\u0002'SA\u0011qbJ\u0005\u0003QA\u0011qAT8uQ&tw\r\u0005\u0002\u0010U%\u00111\u0006\u0005\u0002\u0004\u0003:L\b\"B\u0017\u0001\t\u0003q\u0013A\u0002\u001fj]&$h\bF\u00010!\r\u0001\u0004!I\u0007\u0002\u0005!9!\u0007\u0001a\u0001\n\u0013\u0019\u0014aB4pi:+\u0007\u0010^\u000b\u0002iA\u0011q\"N\u0005\u0003mA\u0011qAQ8pY\u0016\fg\u000eC\u00049\u0001\u0001\u0007I\u0011B\u001d\u0002\u0017\u001d|GOT3yi~#S-\u001d\u000b\u0003uu\u0002\"aD\u001e\n\u0005q\u0002\"\u0001B+oSRDqAP\u001c\u0002\u0002\u0003\u0007A'A\u0002yIEBa\u0001\u0011\u0001!B\u0013!\u0014\u0001C4pi:+\u0007\u0010\u001e\u0011\t\u0013\t\u0003\u0001\u0019!a\u0001\n\u0013\u0019\u0015!\u00038fqR4\u0016\r\\;f+\u0005\t\u0003\"C#\u0001\u0001\u0004\u0005\r\u0011\"\u0003G\u00035qW\r\u001f;WC2,Xm\u0018\u0013fcR\u0011!h\u0012\u0005\b}\u0011\u000b\t\u00111\u0001\"\u0011\u0019I\u0005\u0001)Q\u0005C\u0005Qa.\u001a=u-\u0006dW/\u001a\u0011\t\u000f-\u0003\u0001\u0019!C\u0005g\u000511\r\\8tK\u0012Dq!\u0014\u0001A\u0002\u0013%a*\u0001\u0006dY>\u001cX\rZ0%KF$\"AO(\t\u000fyb\u0015\u0011!a\u0001i!1\u0011\u000b\u0001Q!\nQ\nqa\u00197pg\u0016$\u0007\u0005C\u0004T\u0001\u0001\u0007I\u0011C\u001a\u0002\u0011\u0019Lg.[:iK\u0012Dq!\u0016\u0001A\u0002\u0013Ea+\u0001\u0007gS:L7\u000f[3e?\u0012*\u0017\u000f\u0006\u0002;/\"9a\bVA\u0001\u0002\u0004!\u0004BB-\u0001A\u0003&A'A\u0005gS:L7\u000f[3eA!)1\f\u0001D\t9\u00069q-\u001a;OKb$H#A\u0011\t\u000by\u0003a\u0011C0\u0002\u000b\rdwn]3\u0015\u0003iBQ!\u0019\u0001\u0005\u0002}\u000bQb\u00197pg\u0016LeMT3fI\u0016$\u0007\"B2\u0001\t\u0003\u001a\u0014a\u00025bg:+\u0007\u0010\u001e\u0005\u0006K\u0002!\t\u0005X\u0001\u0005]\u0016DH\u000f"
)
public abstract class NextIterator<U> implements Iterator<U> {
   private boolean gotNext;
   private U nextValue;
   private boolean closed;
   private boolean finished;

   public Iterator<U> seq() {
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

   public Iterator<U> take(int n) {
      return class.take(this, n);
   }

   public Iterator<U> drop(int n) {
      return class.drop(this, n);
   }

   public Iterator<U> slice(int from, int until) {
      return class.slice(this, from, until);
   }

   public <B> Iterator<B> map(Function1<U, B> f) {
      return class.map(this, f);
   }

   public <B> Iterator<B> $plus$plus(Function0<GenTraversableOnce<B>> that) {
      return class.$plus$plus(this, that);
   }

   public <B> Iterator<B> flatMap(Function1<U, GenTraversableOnce<B>> f) {
      return class.flatMap(this, f);
   }

   public Iterator<U> filter(Function1<U, Object> p) {
      return class.filter(this, p);
   }

   public <B> boolean corresponds(GenTraversableOnce<B> that, Function2<U, B, Object> p) {
      return class.corresponds(this, that, p);
   }

   public Iterator<U> withFilter(Function1<U, Object> p) {
      return class.withFilter(this, p);
   }

   public Iterator<U> filterNot(Function1<U, Object> p) {
      return class.filterNot(this, p);
   }

   public <B> Iterator<B> collect(PartialFunction<U, B> pf) {
      return class.collect(this, pf);
   }

   public <B> Iterator<B> scanLeft(B z, Function2<B, U, B> op) {
      return class.scanLeft(this, z, op);
   }

   public <B> Iterator<B> scanRight(B z, Function2<U, B, B> op) {
      return class.scanRight(this, z, op);
   }

   public Iterator<U> takeWhile(Function1<U, Object> p) {
      return class.takeWhile(this, p);
   }

   public Tuple2<Iterator<U>, Iterator<U>> partition(Function1<U, Object> p) {
      return class.partition(this, p);
   }

   public Tuple2<Iterator<U>, Iterator<U>> span(Function1<U, Object> p) {
      return class.span(this, p);
   }

   public Iterator<U> dropWhile(Function1<U, Object> p) {
      return class.dropWhile(this, p);
   }

   public <B> Iterator<Tuple2<U, B>> zip(Iterator<B> that) {
      return class.zip(this, that);
   }

   public <A1> Iterator<A1> padTo(int len, A1 elem) {
      return class.padTo(this, len, elem);
   }

   public Iterator<Tuple2<U, Object>> zipWithIndex() {
      return class.zipWithIndex(this);
   }

   public <B, A1, B1> Iterator<Tuple2<A1, B1>> zipAll(Iterator<B> that, A1 thisElem, B1 thatElem) {
      return class.zipAll(this, that, thisElem, thatElem);
   }

   public <U> void foreach(Function1<U, U> f) {
      class.foreach(this, f);
   }

   public boolean forall(Function1<U, Object> p) {
      return class.forall(this, p);
   }

   public boolean exists(Function1<U, Object> p) {
      return class.exists(this, p);
   }

   public boolean contains(Object elem) {
      return class.contains(this, elem);
   }

   public Option<U> find(Function1<U, Object> p) {
      return class.find(this, p);
   }

   public int indexWhere(Function1<U, Object> p) {
      return class.indexWhere(this, p);
   }

   public <B> int indexOf(B elem) {
      return class.indexOf(this, elem);
   }

   public BufferedIterator<U> buffered() {
      return class.buffered(this);
   }

   public <B> Iterator<U>.GroupedIterator<B> grouped(int size) {
      return class.grouped(this, size);
   }

   public <B> Iterator<U>.GroupedIterator<B> sliding(int size, int step) {
      return class.sliding(this, size, step);
   }

   public int length() {
      return class.length(this);
   }

   public Tuple2<Iterator<U>, Iterator<U>> duplicate() {
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

   public Traversable<U> toTraversable() {
      return class.toTraversable(this);
   }

   public Iterator<U> toIterator() {
      return class.toIterator(this);
   }

   public Stream<U> toStream() {
      return class.toStream(this);
   }

   public String toString() {
      return class.toString(this);
   }

   public <B> int sliding$default$2() {
      return class.sliding$default$2(this);
   }

   public List<U> reversed() {
      return scala.collection.TraversableOnce.class.reversed(this);
   }

   public int size() {
      return scala.collection.TraversableOnce.class.size(this);
   }

   public boolean nonEmpty() {
      return scala.collection.TraversableOnce.class.nonEmpty(this);
   }

   public int count(Function1<U, Object> p) {
      return scala.collection.TraversableOnce.class.count(this, p);
   }

   public <B> Option<B> collectFirst(PartialFunction<U, B> pf) {
      return scala.collection.TraversableOnce.class.collectFirst(this, pf);
   }

   public <B> B $div$colon(B z, Function2<B, U, B> op) {
      return scala.collection.TraversableOnce.class.$div$colon(this, z, op);
   }

   public <B> B $colon$bslash(B z, Function2<U, B, B> op) {
      return scala.collection.TraversableOnce.class.$colon$bslash(this, z, op);
   }

   public <B> B foldLeft(B z, Function2<B, U, B> op) {
      return scala.collection.TraversableOnce.class.foldLeft(this, z, op);
   }

   public <B> B foldRight(B z, Function2<U, B, B> op) {
      return scala.collection.TraversableOnce.class.foldRight(this, z, op);
   }

   public <B> B reduceLeft(Function2<B, U, B> op) {
      return scala.collection.TraversableOnce.class.reduceLeft(this, op);
   }

   public <B> B reduceRight(Function2<U, B, B> op) {
      return scala.collection.TraversableOnce.class.reduceRight(this, op);
   }

   public <B> Option<B> reduceLeftOption(Function2<B, U, B> op) {
      return scala.collection.TraversableOnce.class.reduceLeftOption(this, op);
   }

   public <B> Option<B> reduceRightOption(Function2<U, B, B> op) {
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

   public <B> B aggregate(Function0<B> z, Function2<B, U, B> seqop, Function2<B, B, B> combop) {
      return scala.collection.TraversableOnce.class.aggregate(this, z, seqop, combop);
   }

   public <B> B sum(Numeric<B> num) {
      return scala.collection.TraversableOnce.class.sum(this, num);
   }

   public <B> B product(Numeric<B> num) {
      return scala.collection.TraversableOnce.class.product(this, num);
   }

   public <B> U min(Ordering<B> cmp) {
      return scala.collection.TraversableOnce.class.min(this, cmp);
   }

   public <B> U max(Ordering<B> cmp) {
      return scala.collection.TraversableOnce.class.max(this, cmp);
   }

   public <B> U maxBy(Function1<U, B> f, Ordering<B> cmp) {
      return scala.collection.TraversableOnce.class.maxBy(this, f, cmp);
   }

   public <B> U minBy(Function1<U, B> f, Ordering<B> cmp) {
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

   public List<U> toList() {
      return scala.collection.TraversableOnce.class.toList(this);
   }

   public Iterable<U> toIterable() {
      return scala.collection.TraversableOnce.class.toIterable(this);
   }

   public Seq<U> toSeq() {
      return scala.collection.TraversableOnce.class.toSeq(this);
   }

   public IndexedSeq<U> toIndexedSeq() {
      return scala.collection.TraversableOnce.class.toIndexedSeq(this);
   }

   public <B> Buffer<B> toBuffer() {
      return scala.collection.TraversableOnce.class.toBuffer(this);
   }

   public <B> Set<B> toSet() {
      return scala.collection.TraversableOnce.class.toSet(this);
   }

   public Vector<U> toVector() {
      return scala.collection.TraversableOnce.class.toVector(this);
   }

   public <Col> Col to(CanBuildFrom<, U, Col> cbf) {
      return scala.collection.TraversableOnce.class.to(this, cbf);
   }

   public <T, U> Map<T, U> toMap(less<U, Tuple2<T, U>> ev) {
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

   private boolean gotNext() {
      return this.gotNext;
   }

   private void gotNext_$eq(boolean x$1) {
      this.gotNext = x$1;
   }

   private U nextValue() {
      return this.nextValue;
   }

   private void nextValue_$eq(U x$1) {
      this.nextValue = x$1;
   }

   private boolean closed() {
      return this.closed;
   }

   private void closed_$eq(boolean x$1) {
      this.closed = x$1;
   }

   public boolean finished() {
      return this.finished;
   }

   public void finished_$eq(boolean x$1) {
      this.finished = x$1;
   }

   public abstract U getNext();

   public abstract void close();

   public void closeIfNeeded() {
      if (!this.closed()) {
         this.closed_$eq(true);
         this.close();
      }

   }

   public boolean hasNext() {
      if (!this.finished() && !this.gotNext()) {
         this.nextValue_$eq(this.getNext());
         if (this.finished()) {
            this.closeIfNeeded();
         }

         this.gotNext_$eq(true);
      }

      return !this.finished();
   }

   public U next() {
      if (this.hasNext()) {
         this.gotNext_$eq(false);
         return this.nextValue();
      } else {
         throw new NoSuchElementException("End of stream");
      }
   }

   public NextIterator() {
      scala.collection.TraversableOnce.class.$init$(this);
      class.$init$(this);
      this.gotNext = false;
      this.closed = false;
      this.finished = false;
   }
}
