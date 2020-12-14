package resource;

import scala.Function1;
import scala.Serializable;
import scala.StringContext;
import scala.collection.immutable.List;
import scala.runtime.BoxedUnit;
import scala.runtime.Nothing.;

public abstract class ManagedTraversable$class {
   public static boolean ignoreError(ManagedTraversable $this, Exception error) {
      return false;
   }

   public static void handleErrorsDuringTraversal(ManagedTraversable $this, List ex) {
      ex.headOption().foreach(new Serializable($this) {
         public static final long serialVersionUID = 0L;

         public final  apply(Throwable x$1) {
            throw x$1;
         }
      });
   }

   public static void foreach(ManagedTraversable $this, Function1 f) {
      ExtractedEither result = $this.resource().acquireFor(new Serializable($this, f) {
         public static final long serialVersionUID = 0L;
         // $FF: synthetic field
         private final ManagedTraversable $outer;
         private final Function1 f$1;

         public final void apply(A r) {
            try {
               this.$outer.internalForeach(r, this.f$1);
            } catch (Throwable var7) {
               if (var7 instanceof Exception) {
                  Exception var4 = (Exception)var7;
                  if (this.$outer.ignoreError(var4)) {
                     BoxedUnit var5 = BoxedUnit.UNIT;
                     return;
                  }
               }

               throw var7;
            }

         }

         public {
            if ($outer == null) {
               throw null;
            } else {
               this.$outer = $outer;
               this.f$1 = f$1;
            }
         }
      });
      package$.MODULE$.extractedEitherToEither(result).left().foreach(new Serializable($this) {
         public static final long serialVersionUID = 0L;
         // $FF: synthetic field
         private final ManagedTraversable $outer;

         public final void apply(List<Throwable> ex) {
            this.$outer.handleErrorsDuringTraversal(ex);
         }

         public {
            if ($outer == null) {
               throw null;
            } else {
               this.$outer = $outer;
            }
         }
      });
   }

   public static String toString(ManagedTraversable $this) {
      return (new StringContext(scala.Predef..MODULE$.wrapRefArray((Object[])(new String[]{"ManagedTraversable(", ")"})))).s(scala.Predef..MODULE$.genericWrapArray(new Object[]{$this.resource()}));
   }

   public static void $init$(ManagedTraversable $this) {
   }
}
