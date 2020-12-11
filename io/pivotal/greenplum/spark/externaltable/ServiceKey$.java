package io.pivotal.greenplum.spark.externaltable;

import scala.Option;
import scala.Serializable;
import scala.Some;
import scala.None.;
import scala.collection.immutable.List;
import scala.runtime.AbstractFunction1;

public final class ServiceKey$ extends AbstractFunction1<List<Object>, ServiceKey> implements Serializable {
   public static final ServiceKey$ MODULE$;

   static {
      new ServiceKey$();
   }

   public final String toString() {
      return "ServiceKey";
   }

   public ServiceKey apply(List<Object> port) {
      return new ServiceKey(port);
   }

   public Option<List<Object>> unapply(ServiceKey x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.port()));
   }

   private Object readResolve() {
      return MODULE$;
   }

   private ServiceKey$() {
      MODULE$ = this;
   }
}
