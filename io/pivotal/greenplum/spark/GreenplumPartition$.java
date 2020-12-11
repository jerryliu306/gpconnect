package io.pivotal.greenplum.spark;

import scala.Option;
import scala.Serializable;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.AbstractFunction2;
import scala.runtime.BoxesRunTime;

public final class GreenplumPartition$ extends AbstractFunction2<String, Object, GreenplumPartition> implements Serializable {
   public static final GreenplumPartition$ MODULE$;

   static {
      new GreenplumPartition$();
   }

   public final String toString() {
      return "GreenplumPartition";
   }

   public GreenplumPartition apply(String whereClause, int idx) {
      return new GreenplumPartition(whereClause, idx);
   }

   public Option<Tuple2<String, Object>> unapply(GreenplumPartition x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.whereClause(), BoxesRunTime.boxToInteger(x$0.idx()))));
   }

   private Object readResolve() {
      return MODULE$;
   }

   private GreenplumPartition$() {
      MODULE$ = this;
   }
}
