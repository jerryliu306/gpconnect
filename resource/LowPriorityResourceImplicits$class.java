package resource;

import java.lang.ref.SoftReference;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import scala.runtime.BoxedUnit;
import scala.runtime.EmptyMethodCache;
import scala.runtime.MethodCache;
import scala.runtime.ScalaRunTime.;

public abstract class LowPriorityResourceImplicits$class {
   public static Resource reflectiveCloseableResource(LowPriorityResourceImplicits $this) {
      return new Resource<A>($this) {
         private static Class[] reflParams$Cache1 = new Class[0];
         private static volatile SoftReference reflPoly$Cache1 = new SoftReference(new EmptyMethodCache());

         public static Method reflMethod$Method1(Class x$1) {
            MethodCache methodCache1 = (MethodCache)reflPoly$Cache1.get();
            if (methodCache1 == null) {
               methodCache1 = new EmptyMethodCache();
               reflPoly$Cache1 = new SoftReference(methodCache1);
            }

            Method method1 = ((MethodCache)methodCache1).find(x$1);
            if (method1 != null) {
               return method1;
            } else {
               method1 = .MODULE$.ensureAccessible(x$1.getMethod("close", reflParams$Cache1));
               reflPoly$Cache1 = new SoftReference(((MethodCache)methodCache1).add(x$1, method1));
               return method1;
            }
         }

         public void open(A r) {
            Resource$class.open(this, r);
         }

         public void closeAfterException(A r, Throwable t) {
            Resource$class.closeAfterException(this, r, t);
         }

         public boolean isFatalException(Throwable t) {
            return Resource$class.isFatalException(this, t);
         }

         public boolean isRethrownException(Throwable t) {
            return Resource$class.isRethrownException(this, t);
         }

         public void close(A r) {
            Object qual1 = r;

            try {
               reflMethod$Method1(qual1.getClass()).invoke(qual1);
            } catch (InvocationTargetException var4) {
               throw var4.getCause();
            }

            BoxedUnit var10000 = BoxedUnit.UNIT;
         }

         public String toString() {
            return "Resource[{ def close() : Unit }]";
         }

         public {
            Resource$class.$init$(this);
         }
      };
   }

   public static Resource reflectiveDisposableResource(LowPriorityResourceImplicits $this) {
      return new Resource<A>($this) {
         private static Class[] reflParams$Cache2 = new Class[0];
         private static volatile SoftReference reflPoly$Cache2 = new SoftReference(new EmptyMethodCache());

         public static Method reflMethod$Method2(Class x$1) {
            MethodCache methodCache2 = (MethodCache)reflPoly$Cache2.get();
            if (methodCache2 == null) {
               methodCache2 = new EmptyMethodCache();
               reflPoly$Cache2 = new SoftReference(methodCache2);
            }

            Method method2 = ((MethodCache)methodCache2).find(x$1);
            if (method2 != null) {
               return method2;
            } else {
               method2 = .MODULE$.ensureAccessible(x$1.getMethod("dispose", reflParams$Cache2));
               reflPoly$Cache2 = new SoftReference(((MethodCache)methodCache2).add(x$1, method2));
               return method2;
            }
         }

         public void open(A r) {
            Resource$class.open(this, r);
         }

         public void closeAfterException(A r, Throwable t) {
            Resource$class.closeAfterException(this, r, t);
         }

         public boolean isFatalException(Throwable t) {
            return Resource$class.isFatalException(this, t);
         }

         public boolean isRethrownException(Throwable t) {
            return Resource$class.isRethrownException(this, t);
         }

         public void close(A r) {
            Object qual2 = r;

            try {
               reflMethod$Method2(qual2.getClass()).invoke(qual2);
            } catch (InvocationTargetException var4) {
               throw var4.getCause();
            }

            BoxedUnit var10000 = BoxedUnit.UNIT;
         }

         public String toString() {
            return "Resource[{ def dispose() : Unit }]";
         }

         public {
            Resource$class.$init$(this);
         }
      };
   }

   public static void $init$(LowPriorityResourceImplicits $this) {
   }
}
