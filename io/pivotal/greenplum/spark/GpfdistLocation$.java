package io.pivotal.greenplum.spark;

import scala.Option;
import scala.Serializable;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.AbstractFunction2;
import scala.runtime.BoxesRunTime;

public final class GpfdistLocation$ extends AbstractFunction2<String, Object, GpfdistLocation> implements Serializable {
   public static final GpfdistLocation$ MODULE$;

   static {
      new GpfdistLocation$();
   }

   public final String toString() {
      return "GpfdistLocation";
   }

   public GpfdistLocation apply(String server, int port) {
      return new GpfdistLocation(server, port);
   }

   public Option<Tuple2<String, Object>> unapply(GpfdistLocation x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.server(), BoxesRunTime.boxToInteger(x$0.port()))));
   }

   private Object readResolve() {
      return MODULE$;
   }

   private GpfdistLocation$() {
      MODULE$ = this;
   }
}
