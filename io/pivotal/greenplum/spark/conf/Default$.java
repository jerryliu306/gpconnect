package io.pivotal.greenplum.spark.conf;

import scala.Option;
import scala.Serializable;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;

public final class Default$ extends AbstractFunction1<String, Default> implements Serializable {
   public static final Default$ MODULE$;

   static {
      new Default$();
   }

   public final String toString() {
      return "Default";
   }

   public Default apply(String value) {
      return new Default(value);
   }

   public Option<String> unapply(Default x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.value()));
   }

   private Object readResolve() {
      return MODULE$;
   }

   private Default$() {
      MODULE$ = this;
   }
}
