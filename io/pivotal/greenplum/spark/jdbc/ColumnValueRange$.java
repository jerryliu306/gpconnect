package io.pivotal.greenplum.spark.jdbc;

import scala.Option;
import scala.Serializable;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.Tuple2.mcJJ.sp;
import scala.runtime.AbstractFunction2;

public final class ColumnValueRange$ extends AbstractFunction2<Object, Object, ColumnValueRange> implements Serializable {
   public static final ColumnValueRange$ MODULE$;

   static {
      new ColumnValueRange$();
   }

   public final String toString() {
      return "ColumnValueRange";
   }

   public ColumnValueRange apply(long min, long max) {
      return new ColumnValueRange(min, max);
   }

   public Option<Tuple2<Object, Object>> unapply(ColumnValueRange x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new sp(x$0.min(), x$0.max())));
   }

   private Object readResolve() {
      return MODULE$;
   }

   private ColumnValueRange$() {
      MODULE$ = this;
   }
}
