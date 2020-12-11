package io.pivotal.greenplum.spark.externaltable;

import scala.Option;
import scala.Serializable;
import scala.Some;
import scala.Tuple3;
import scala.None.;
import scala.runtime.AbstractFunction3;
import scala.runtime.BoxesRunTime;

public final class GpfdistHeaders$ extends AbstractFunction3<String, Object, Object, GpfdistHeaders> implements Serializable {
   public static final GpfdistHeaders$ MODULE$;

   static {
      new GpfdistHeaders$();
   }

   public final String toString() {
      return "GpfdistHeaders";
   }

   public GpfdistHeaders apply(String transactionId, int segmentId, int segmentCount) {
      return new GpfdistHeaders(transactionId, segmentId, segmentCount);
   }

   public Option<Tuple3<String, Object, Object>> unapply(GpfdistHeaders x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple3(x$0.transactionId(), BoxesRunTime.boxToInteger(x$0.segmentId()), BoxesRunTime.boxToInteger(x$0.segmentCount()))));
   }

   private Object readResolve() {
      return MODULE$;
   }

   private GpfdistHeaders$() {
      MODULE$ = this;
   }
}
