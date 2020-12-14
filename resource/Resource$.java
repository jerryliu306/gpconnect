package resource;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.sql.PooledConnection;
import scala.runtime.BoxedUnit;

public final class Resource$ implements MediumPriorityResourceImplicits {
   public static final Resource$ MODULE$;
   private volatile MediumPriorityResourceImplicits.gzipOuputStraemResource$ gzipOuputStraemResource$module;
   private volatile MediumPriorityResourceImplicits.jarFileResource$ jarFileResource$module;
   private volatile MediumPriorityResourceImplicits.HttpURLConnectionResource$ HttpURLConnectionResource$module;

   static {
      new Resource$();
   }

   private MediumPriorityResourceImplicits.gzipOuputStraemResource$ gzipOuputStraemResource$lzycompute() {
      BoxedUnit var10000;
      synchronized(this) {
         if (this.gzipOuputStraemResource$module == null) {
            this.gzipOuputStraemResource$module = new MediumPriorityResourceImplicits.gzipOuputStraemResource$();
         }

         var10000 = BoxedUnit.UNIT;
      }

      var10000 = null;
      return this.gzipOuputStraemResource$module;
   }

   public MediumPriorityResourceImplicits.gzipOuputStraemResource$ gzipOuputStraemResource() {
      return this.gzipOuputStraemResource$module == null ? this.gzipOuputStraemResource$lzycompute() : this.gzipOuputStraemResource$module;
   }

   private MediumPriorityResourceImplicits.jarFileResource$ jarFileResource$lzycompute() {
      BoxedUnit var10000;
      synchronized(this) {
         if (this.jarFileResource$module == null) {
            this.jarFileResource$module = new MediumPriorityResourceImplicits.jarFileResource$();
         }

         var10000 = BoxedUnit.UNIT;
      }

      var10000 = null;
      return this.jarFileResource$module;
   }

   public MediumPriorityResourceImplicits.jarFileResource$ jarFileResource() {
      return this.jarFileResource$module == null ? this.jarFileResource$lzycompute() : this.jarFileResource$module;
   }

   private MediumPriorityResourceImplicits.HttpURLConnectionResource$ HttpURLConnectionResource$lzycompute() {
      BoxedUnit var10000;
      synchronized(this) {
         if (this.HttpURLConnectionResource$module == null) {
            this.HttpURLConnectionResource$module = new MediumPriorityResourceImplicits.HttpURLConnectionResource$();
         }

         var10000 = BoxedUnit.UNIT;
      }

      var10000 = null;
      return this.HttpURLConnectionResource$module;
   }

   public MediumPriorityResourceImplicits.HttpURLConnectionResource$ HttpURLConnectionResource() {
      return this.HttpURLConnectionResource$module == null ? this.HttpURLConnectionResource$lzycompute() : this.HttpURLConnectionResource$module;
   }

   public <A extends Closeable> Object closeableResource() {
      return MediumPriorityResourceImplicits$class.closeableResource(this);
   }

   public <A extends Connection> Object connectionResource() {
      return MediumPriorityResourceImplicits$class.connectionResource(this);
   }

   public <A extends Statement> Object statementResource() {
      return MediumPriorityResourceImplicits$class.statementResource(this);
   }

   public <A extends ResultSet> Object resultSetResource() {
      return MediumPriorityResourceImplicits$class.resultSetResource(this);
   }

   public <A extends PooledConnection> Object pooledConnectionResource() {
      return MediumPriorityResourceImplicits$class.pooledConnectionResource(this);
   }

   public <A> Object reflectiveCloseableResource() {
      return LowPriorityResourceImplicits$class.reflectiveCloseableResource(this);
   }

   public <A> Object reflectiveDisposableResource() {
      return LowPriorityResourceImplicits$class.reflectiveDisposableResource(this);
   }

   private Resource$() {
      MODULE$ = this;
      LowPriorityResourceImplicits$class.$init$(this);
      MediumPriorityResourceImplicits$class.$init$(this);
   }
}
