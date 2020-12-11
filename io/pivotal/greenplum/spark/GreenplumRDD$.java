package io.pivotal.greenplum.spark;

import org.apache.spark.sql.execution.datasources.jdbc.JDBCRDD.;
import org.apache.spark.sql.jdbc.JdbcDialect;
import org.apache.spark.sql.sources.Filter;
import scala.Option;
import scala.Serializable;

public final class GreenplumRDD$ implements Serializable {
   public static final GreenplumRDD$ MODULE$;

   static {
      new GreenplumRDD$();
   }

   public Option<String> compileFilter(Filter f, JdbcDialect dialect) {
      return .MODULE$.compileFilter(f, dialect);
   }

   public Option<String> compileFilter(Filter f) {
      return this.compileFilter(f, org.apache.spark.sql.jdbc.JdbcDialects..MODULE$.get("jdbc:postgresql"));
   }

   private Object readResolve() {
      return MODULE$;
   }

   private GreenplumRDD$() {
      MODULE$ = this;
   }
}
