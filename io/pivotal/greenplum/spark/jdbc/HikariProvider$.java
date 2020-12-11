package io.pivotal.greenplum.spark.jdbc;

import com.typesafe.scalalogging.LazyLogging;
import com.typesafe.scalalogging.LazyLogging$class;
import com.typesafe.scalalogging.Logger;
import com.zaxxer.hikari.HikariDataSource;
import io.pivotal.greenplum.spark.conf.ConnectionPoolOptions;
import javax.sql.DataSource;
import org.apache.spark.sql.execution.datasources.jdbc.DriverRegistry.;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.collection.mutable.StringBuilder;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

public final class HikariProvider$ implements DataSourceProvider, LazyLogging {
   public static final HikariProvider$ MODULE$;
   private final transient Logger logger;
   private transient volatile boolean bitmap$trans$0;

   static {
      new HikariProvider$();
   }

   private Logger logger$lzycompute() {
      synchronized(this) {
         if (!this.bitmap$trans$0) {
            this.logger = LazyLogging$class.logger(this);
            this.bitmap$trans$0 = true;
         }

         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      return this.logger;
   }

   public Logger logger() {
      return this.bitmap$trans$0 ? this.logger : this.logger$lzycompute();
   }

   public DataSource createDataSource(ConnectionKey key, Option<String> password, String driver, ConnectionPoolOptions options) {
      .MODULE$.register(driver);
      HikariDataSource dataSource = new HikariDataSource();
      dataSource.setJdbcUrl(key.jdbcUrl());
      dataSource.setUsername(key.userName());
      BoxedUnit var10000;
      if (password instanceof Some) {
         Some var7 = (Some)password;
         String pass = (String)var7.x();
         dataSource.setPassword(pass);
         BoxedUnit var9 = BoxedUnit.UNIT;
      } else {
         if (!scala.None..MODULE$.equals(password)) {
            throw new MatchError(password);
         }

         if (this.logger().underlying().isDebugEnabled()) {
            this.logger().underlying().debug("No password is used");
            var10000 = BoxedUnit.UNIT;
         } else {
            var10000 = BoxedUnit.UNIT;
         }
      }

      dataSource.setMaximumPoolSize(options.maximumPoolSize());
      dataSource.setIdleTimeout((long)options.idleTimeoutMs());
      dataSource.setMinimumIdle(options.minimumIdle());
      if (this.logger().underlying().isDebugEnabled()) {
         this.logger().underlying().debug((new StringBuilder()).append((new StringContext(scala.Predef..MODULE$.wrapRefArray((Object[])(new String[]{"Created connection pool with ", " as a max number of "})))).s(scala.Predef..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(options.maximumPoolSize())}))).append((new StringContext(scala.Predef..MODULE$.wrapRefArray((Object[])(new String[]{"connections for a jdbc url: ", " and user: ", ""})))).s(scala.Predef..MODULE$.genericWrapArray(new Object[]{key.jdbcUrl(), key.userName()}))).toString());
         var10000 = BoxedUnit.UNIT;
      } else {
         var10000 = BoxedUnit.UNIT;
      }

      return dataSource;
   }

   private HikariProvider$() {
      MODULE$ = this;
      LazyLogging$class.$init$(this);
   }
}
