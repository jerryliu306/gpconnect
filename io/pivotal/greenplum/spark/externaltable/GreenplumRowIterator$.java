package io.pivotal.greenplum.spark.externaltable;

import io.pivotal.greenplum.spark.GreenplumRDD$;
import org.apache.spark.sql.sources.Filter;
import scala.Serializable;
import scala.StringContext;
import scala.Predef.;
import scala.collection.Iterable;

public final class GreenplumRowIterator$ {
   public static final GreenplumRowIterator$ MODULE$;

   static {
      new GreenplumRowIterator$();
   }

   public String filterWherePredicate(Filter[] filters) {
      return .MODULE$.refArrayOps((Object[]).MODULE$.refArrayOps((Object[]).MODULE$.refArrayOps((Object[])filters).flatMap(new Serializable() {
         public static final long serialVersionUID = 0L;

         public final Iterable<String> apply(Filter x$1) {
            return scala.Option..MODULE$.option2Iterable(GreenplumRDD$.MODULE$.compileFilter(x$1));
         }
      }, scala.Array..MODULE$.canBuildFrom(scala.reflect.ClassTag..MODULE$.apply(String.class)))).map(new Serializable() {
         public static final long serialVersionUID = 0L;

         public final String apply(String p) {
            return (new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{"(", ")"})))).s(.MODULE$.genericWrapArray(new Object[]{p}));
         }
      }, scala.Array..MODULE$.canBuildFrom(scala.reflect.ClassTag..MODULE$.apply(String.class)))).mkString(" AND ");
   }

   private GreenplumRowIterator$() {
      MODULE$ = this;
   }
}
