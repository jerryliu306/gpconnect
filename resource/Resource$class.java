package resource;

import scala.util.control.ControlThrowable;

public abstract class Resource$class {
   public static void open(Resource $this, Object r) {
   }

   public static void closeAfterException(Resource $this, Object r, Throwable t) {
      $this.close(r);
   }

   public static boolean isFatalException(Resource $this, Throwable t) {
      return t instanceof VirtualMachineError;
   }

   public static boolean isRethrownException(Resource $this, Throwable t) {
      boolean var3;
      if (t instanceof ControlThrowable) {
         var3 = true;
      } else if (t instanceof InterruptedException) {
         var3 = true;
      } else {
         var3 = false;
      }

      return var3;
   }

   public static void $init$(Resource $this) {
   }
}
