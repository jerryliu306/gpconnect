package io.pivotal.greenplum.spark.externaltable;

import scala.Option;
import scala.Serializable;
import scala.Some;
import scala.Tuple3;
import scala.None.;
import scala.runtime.AbstractFunction3;
import scala.runtime.BoxesRunTime;

public final class WebException$ extends AbstractFunction3<Object, String, Option<Throwable>, WebException> implements Serializable {
   public static final WebException$ MODULE$;

   static {
      new WebException$();
   }

   public final String toString() {
      return "WebException";
   }

   public WebException apply(int code, String message, Option<Throwable> cause) {
      return new WebException(code, message, cause);
   }

   public Option<Tuple3<Object, String, Option<Throwable>>> unapply(WebException x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple3(BoxesRunTime.boxToInteger(x$0.code()), x$0.message(), x$0.cause())));
   }

   public String $lessinit$greater$default$2() {
      return null;
   }

   public Option<Throwable> $lessinit$greater$default$3() {
      return .MODULE$;
   }

   public String apply$default$2() {
      return null;
   }

   public Option<Throwable> apply$default$3() {
      return .MODULE$;
   }

   private Object readResolve() {
      return MODULE$;
   }

   private WebException$() {
      MODULE$ = this;
   }
}
