package resource;

public abstract class MediumPriorityResourceImplicits$class {
   public static Resource closeableResource(MediumPriorityResourceImplicits $this) {
      return new Resource<A>($this) {
         public void open(Object r) {
            Resource$class.open(this, r);
         }

         public void closeAfterException(Object r, Throwable t) {
            Resource$class.closeAfterException(this, r, t);
         }

         public boolean isFatalException(Throwable t) {
            return Resource$class.isFatalException(this, t);
         }

         public boolean isRethrownException(Throwable t) {
            return Resource$class.isRethrownException(this, t);
         }

         public void close(A r) {
            r.close();
         }

         public String toString() {
            return "Resource[java.io.Closeable]";
         }

         public {
            Resource$class.$init$(this);
         }
      };
   }

   public static Resource connectionResource(MediumPriorityResourceImplicits $this) {
      return new Resource<A>($this) {
         public void open(Object r) {
            Resource$class.open(this, r);
         }

         public void closeAfterException(Object r, Throwable t) {
            Resource$class.closeAfterException(this, r, t);
         }

         public boolean isFatalException(Throwable t) {
            return Resource$class.isFatalException(this, t);
         }

         public boolean isRethrownException(Throwable t) {
            return Resource$class.isRethrownException(this, t);
         }

         public void close(A r) {
            r.close();
         }

         public String toString() {
            return "Resource[java.sql.Connection]";
         }

         public {
            Resource$class.$init$(this);
         }
      };
   }

   public static Resource statementResource(MediumPriorityResourceImplicits $this) {
      return new Resource<A>($this) {
         public void open(Object r) {
            Resource$class.open(this, r);
         }

         public void closeAfterException(Object r, Throwable t) {
            Resource$class.closeAfterException(this, r, t);
         }

         public boolean isFatalException(Throwable t) {
            return Resource$class.isFatalException(this, t);
         }

         public boolean isRethrownException(Throwable t) {
            return Resource$class.isRethrownException(this, t);
         }

         public void close(A r) {
            r.close();
         }

         public String toString() {
            return "Resource[java.sql.Statement]";
         }

         public {
            Resource$class.$init$(this);
         }
      };
   }

   public static Resource resultSetResource(MediumPriorityResourceImplicits $this) {
      return new Resource<A>($this) {
         public void open(Object r) {
            Resource$class.open(this, r);
         }

         public void closeAfterException(Object r, Throwable t) {
            Resource$class.closeAfterException(this, r, t);
         }

         public boolean isFatalException(Throwable t) {
            return Resource$class.isFatalException(this, t);
         }

         public boolean isRethrownException(Throwable t) {
            return Resource$class.isRethrownException(this, t);
         }

         public void close(A r) {
            r.close();
         }

         public String toString() {
            return "Resource[java.sql.ResultSet]";
         }

         public {
            Resource$class.$init$(this);
         }
      };
   }

   public static Resource pooledConnectionResource(MediumPriorityResourceImplicits $this) {
      return new Resource<A>($this) {
         public void open(Object r) {
            Resource$class.open(this, r);
         }

         public void closeAfterException(Object r, Throwable t) {
            Resource$class.closeAfterException(this, r, t);
         }

         public boolean isFatalException(Throwable t) {
            return Resource$class.isFatalException(this, t);
         }

         public boolean isRethrownException(Throwable t) {
            return Resource$class.isRethrownException(this, t);
         }

         public void close(A r) {
            r.close();
         }

         public String toString() {
            return "Resource[javax.sql.PooledConnection]";
         }

         public {
            Resource$class.$init$(this);
         }
      };
   }

   public static void $init$(MediumPriorityResourceImplicits $this) {
   }
}
