package io.pivotal.greenplum.spark.jdbc;

import io.pivotal.greenplum.spark.conf.GreenplumOptions;
import java.sql.Connection;
import scala.runtime.BoxedUnit;

public final class ConnectionManager$ {
   public static final ConnectionManager$ MODULE$;
   private ConnectionManager connectionManager;
   private volatile boolean bitmap$0;

   static {
      new ConnectionManager$();
   }

   private ConnectionManager connectionManager$lzycompute() {
      synchronized(this) {
         if (!this.bitmap$0) {
            this.connectionManager = new ConnectionManager(HikariProvider$.MODULE$);
            this.bitmap$0 = true;
         }

         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      return this.connectionManager;
   }

   public ConnectionManager connectionManager() {
      return this.bitmap$0 ? this.connectionManager : this.connectionManager$lzycompute();
   }

   public Connection getConnection(GreenplumOptions dbOpts, boolean autoCommit) {
      return this.connectionManager().getConnection(dbOpts, autoCommit);
   }

   public boolean getConnection$default$2() {
      return true;
   }

   private ConnectionManager$() {
      MODULE$ = this;
   }
}
