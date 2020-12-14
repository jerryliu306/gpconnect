package resource;

import scala.Option;
import scala.Serializable;
import scala.Some;
import scala.None.;
import scala.util.Either;

public final class ExtractedEither$ implements Serializable {
   public static final ExtractedEither$ MODULE$;

   static {
      new ExtractedEither$();
   }

   public final String toString() {
      return "ExtractedEither";
   }

   public <A, B> ExtractedEither<A, B> apply(Either<A, B> either) {
      return new ExtractedEither(either);
   }

   public <A, B> Option<Either<A, B>> unapply(ExtractedEither<A, B> x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.either()));
   }

   private Object readResolve() {
      return MODULE$;
   }

   private ExtractedEither$() {
      MODULE$ = this;
   }
}
