package io.pivotal.greenplum.spark.externaltable;

import com.typesafe.scalalogging.LazyLogging;
import com.typesafe.scalalogging.LazyLogging$class;
import com.typesafe.scalalogging.Logger;
import org.apache.spark.sql.Row;
import scala.Function1;
import scala.Serializable;
import scala.StringContext;
import scala.collection.Seq;
import scala.collection.TraversableLike;
import scala.collection.TraversableOnce;
import scala.collection.Seq.;
import scala.collection.immutable.IndexedSeq;
import scala.collection.mutable.StringBuilder;
import scala.runtime.BoxedUnit;
import scala.util.Failure;
import scala.util.Success;
import scala.util.Try;

public final class RowTransformer$ implements LazyLogging {
   public static final RowTransformer$ MODULE$;
   private final Function1<Row, Row> identityFunction;
   private final transient Logger logger;
   private transient volatile boolean bitmap$trans$0;

   static {
      new RowTransformer$();
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

   public Function1<Row, Row> identityFunction() {
      return this.identityFunction;
   }

   public Try<Function1<Row, Row>> getFunction(Seq<String> sparkCols, Seq<String> gpdbCols) {
      Seq lowercaseSparkCols = (Seq)sparkCols.map(new Serializable() {
         public static final long serialVersionUID = 0L;

         public final String apply(String x$1) {
            return x$1.toLowerCase();
         }
      }, .MODULE$.canBuildFrom());
      Seq lowercaseGPDBCols = (Seq)gpdbCols.map(new Serializable() {
         public static final long serialVersionUID = 0L;

         public final String apply(String x$2) {
            return x$2.toLowerCase();
         }
      }, .MODULE$.canBuildFrom());
      BoxedUnit var10000;
      if (lowercaseSparkCols.equals(lowercaseGPDBCols)) {
         if (this.logger().underlying().isDebugEnabled()) {
            this.logger().underlying().debug("RowTransformer.getfunction returning identity function...");
            var10000 = BoxedUnit.UNIT;
         } else {
            var10000 = BoxedUnit.UNIT;
         }

         return new Success(this.identityFunction());
      } else {
         if (!lowercaseSparkCols.toSet().equals(lowercaseGPDBCols.toSet())) {
            String missingColumns = this.formatColumnList((Seq)lowercaseGPDBCols.diff(lowercaseSparkCols));
            String extraColumns = this.formatColumnList((Seq)lowercaseSparkCols.diff(lowercaseGPDBCols));
            if (extraColumns.isEmpty()) {
               var10000 = BoxedUnit.UNIT;
            } else if (this.logger().underlying().isWarnEnabled()) {
               this.logger().underlying().warn((new StringBuilder()).append((new StringContext(scala.Predef..MODULE$.wrapRefArray((Object[])(new String[]{"Spark dataframe contains extra column[s] ", " "})))).s(scala.Predef..MODULE$.genericWrapArray(new Object[]{extraColumns}))).append("that will be ignored when writing to Greenplum Database table.").toString());
               var10000 = BoxedUnit.UNIT;
            } else {
               var10000 = BoxedUnit.UNIT;
            }

            if (!missingColumns.isEmpty()) {
               return new Failure(new RuntimeException((new StringBuilder()).append((new StringContext(scala.Predef..MODULE$.wrapRefArray((Object[])(new String[]{"Spark DataFrame must include column[s] ", " "})))).s(scala.Predef..MODULE$.genericWrapArray(new Object[]{missingColumns}))).append("when writing to Greenplum Database table.").toString()));
            }
         }

         Seq sparkColIndexFromGpdbColIndex = (Seq)lowercaseGPDBCols.map(new Serializable(lowercaseSparkCols) {
            public static final long serialVersionUID = 0L;
            private final Seq lowercaseSparkCols$1;

            public final int apply(Object elem) {
               return this.lowercaseSparkCols$1.indexOf(elem);
            }

            public {
               this.lowercaseSparkCols$1 = lowercaseSparkCols$1;
            }
         }, .MODULE$.canBuildFrom());
         Function1 reorderColumns = new Serializable(lowercaseGPDBCols, sparkColIndexFromGpdbColIndex) {
            public static final long serialVersionUID = 0L;
            private final Seq lowercaseGPDBCols$1;
            private final Seq sparkColIndexFromGpdbColIndex$1;

            public final Row apply(Row sparkRow) {
               IndexedSeq reorderedValues = (IndexedSeq)((TraversableLike)this.lowercaseGPDBCols$1.indices().map(this.sparkColIndexFromGpdbColIndex$1, scala.collection.immutable.IndexedSeq..MODULE$.canBuildFrom())).map(new Serializable(this, sparkRow) {
                  public static final long serialVersionUID = 0L;
                  private final Row sparkRow$1;

                  public final Object apply(int i) {
                     return this.sparkRow$1.get(i);
                  }

                  public {
                     this.sparkRow$1 = sparkRow$1;
                  }
               }, scala.collection.immutable.IndexedSeq..MODULE$.canBuildFrom());
               return org.apache.spark.sql.Row..MODULE$.fromSeq(reorderedValues);
            }

            public {
               this.lowercaseGPDBCols$1 = lowercaseGPDBCols$1;
               this.sparkColIndexFromGpdbColIndex$1 = sparkColIndexFromGpdbColIndex$1;
            }
         };
         return new Success(reorderColumns);
      }
   }

   private String formatColumnList(Seq<String> columns) {
      return ((TraversableOnce)columns.map(new Serializable() {
         public static final long serialVersionUID = 0L;

         public final String apply(String s) {
            return (new StringContext(scala.Predef..MODULE$.wrapRefArray((Object[])(new String[]{"\"", "\""})))).s(scala.Predef..MODULE$.genericWrapArray(new Object[]{s}));
         }
      }, .MODULE$.canBuildFrom())).mkString(", ");
   }

   private RowTransformer$() {
      MODULE$ = this;
      LazyLogging$class.$init$(this);
      this.identityFunction = new Serializable() {
         public static final long serialVersionUID = 0L;

         public final Row apply(Row x) {
            return (Row)scala.Predef..MODULE$.identity(x);
         }
      };
   }
}
