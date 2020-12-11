package io.pivotal.greenplum.spark;

import scala.Function1;
import scala.PartialFunction;
import scala.Serializable;
import scala.util.Failure;
import scala.util.Try;

public final class ErrorHandling$ {
   public static final ErrorHandling$ MODULE$;

   static {
      new ErrorHandling$();
   }

   public <U> PartialFunction<Throwable, Try<U>> wrapErrorMessage(String message) {
      return new Serializable(message) {
         public static final long serialVersionUID = 0L;
         private final String message$1;

         public final <A1 extends Throwable, B1> B1 applyOrElse(A1 x1, Function1<A1, B1> var2) {
            Failure var4 = new Failure(new RuntimeException(this.message$1, x1));
            return var4;
         }

         public final boolean isDefinedAt(Throwable x1) {
            boolean var3 = true;
            return var3;
         }

         public {
            this.message$1 = message$1;
         }
      };
   }

   private ErrorHandling$() {
      MODULE$ = this;
   }
}
