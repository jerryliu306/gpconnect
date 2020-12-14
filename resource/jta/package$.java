package resource.jta;

import javax.transaction.Transaction;
import resource.Resource;
import resource.Resource$class;

public final class package$ {
   public static final package$ MODULE$;

   static {
      new package$();
   }

   public <A extends Transaction> Resource<A> transactionSupport() {
      return new Resource<A>() {
         public void open(Object r) {
            Resource$class.open(this, r);
         }

         public boolean isFatalException(Throwable t) {
            return Resource$class.isFatalException(this, t);
         }

         public boolean isRethrownException(Throwable t) {
            return Resource$class.isRethrownException(this, t);
         }

         public void close(A r) {
            r.commit();
         }

         public void closeAfterException(A r, Throwable t) {
            r.rollback();
         }

         public {
            Resource$class.$init$(this);
         }
      };
   }

   private package$() {
      MODULE$ = this;
   }
}
