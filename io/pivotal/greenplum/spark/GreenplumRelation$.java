package io.pivotal.greenplum.spark;

import com.typesafe.scalalogging.LazyLogging;
import com.typesafe.scalalogging.LazyLogging$class;
import com.typesafe.scalalogging.Logger;
import io.pivotal.greenplum.spark.conf.GreenplumOptions;
import io.pivotal.greenplum.spark.externaltable.SqlObjectNameUtils$;
import io.pivotal.greenplum.spark.jdbc.ColumnValueRange;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import scala.MatchError;
import scala.Option;
import scala.Serializable;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.Tuple3;
import scala.Predef.;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.StringBuilder;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.LongRef;
import scala.runtime.ObjectRef;

public final class GreenplumRelation$ implements LazyLogging, Serializable {
   public static final GreenplumRelation$ MODULE$;
   private final transient Logger logger;
   private transient volatile boolean bitmap$trans$0;

   static {
      new GreenplumRelation$();
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

   public GreenplumPartition[] columnPartition(int requestedPartitions, StructField partitionColumn, ColumnValueRange valueRange) {
      GreenplumPartition[] var10000;
      if (this.onlyOnePartition$1(partitionColumn, valueRange)) {
         var10000 = (GreenplumPartition[])((Object[])(new GreenplumPartition[]{new GreenplumPartition("1 = 1", 0)}));
      } else {
         long lowerBound = valueRange.min();
         long upperBound = valueRange.max();
         long distinctValuesInRange = upperBound - lowerBound;
         .MODULE$.require(lowerBound <= upperBound, new Serializable(lowerBound, upperBound) {
            public static final long serialVersionUID = 0L;
            private final long lowerBound$1;
            private final long upperBound$1;

            public final String apply() {
               return (new StringBuilder()).append("Operation not allowed: the lower bound of partitioning column is larger than the upper ").append((new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{"bound. Lower bound: ", "; Upper bound: ", ""})))).s(.MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToLong(this.lowerBound$1), BoxesRunTime.boxToLong(this.upperBound$1)}))).toString();
            }

            public {
               this.lowerBound$1 = lowerBound$1;
               this.upperBound$1 = upperBound$1;
            }
         });
         int var17;
         if (distinctValuesInRange < (long)requestedPartitions && !this.overflows$1(distinctValuesInRange)) {
            BoxedUnit var18;
            if (this.logger().underlying().isWarnEnabled()) {
               this.logger().underlying().warn((new StringBuilder()).append("The number of partitions is reduced because the specified number of partitions is less than the difference between upper bound and lower bound. ").append((new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{"Updated number of partitions per segment: ", "; "})))).s(.MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToLong(distinctValuesInRange)}))).append((new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{"Input number of partitions per segment: ", "; "})))).s(.MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(requestedPartitions)}))).append((new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{"Lower bound: ", "; Upper bound: ", "."})))).s(.MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToLong(lowerBound), BoxesRunTime.boxToLong(upperBound)}))).toString());
               var18 = BoxedUnit.UNIT;
            } else {
               var18 = BoxedUnit.UNIT;
            }

            var17 = (int)distinctValuesInRange;
         } else {
            var17 = requestedPartitions;
         }

         int numPartitions = var17;
         long stride = upperBound / (long)numPartitions - lowerBound / (long)numPartitions;
         String column = SqlObjectNameUtils$.MODULE$.escape(partitionColumn.name());
         boolean nullable = partitionColumn.nullable();
         ObjectRef partitionArrayBuf = ObjectRef.create(new ArrayBuffer());
         LongRef currentPartitionBoundaryValue = LongRef.create(lowerBound);
         scala.runtime.RichInt..MODULE$.until$extension0(.MODULE$.intWrapper(0), numPartitions).foreach(new Serializable(numPartitions, stride, column, nullable, partitionArrayBuf, currentPartitionBoundaryValue) {
            public static final long serialVersionUID = 0L;
            private final int numPartitions$1;
            private final long stride$1;
            private final String column$1;
            private final boolean nullable$1;
            private final ObjectRef partitionArrayBuf$1;
            private final LongRef currentPartitionBoundaryValue$1;

            public final ArrayBuffer<GreenplumPartition> apply(int partitionIndex) {
               boolean isFirstPartition = partitionIndex == 0;
               boolean isLastPartition = partitionIndex == this.numPartitions$1 - 1;
               boolean isOnlyPartition = isFirstPartition && isLastPartition;
               long currPartitionLowBound = this.currentPartitionBoundaryValue$1.elem;
               this.currentPartitionBoundaryValue$1.elem += this.stride$1;
               long currPartitionUppBound = this.currentPartitionBoundaryValue$1.elem;
               String var10000;
               if (isOnlyPartition) {
                  var10000 = "1 = 1";
               } else if (isFirstPartition) {
                  String conditionForNulls = this.nullable$1 ? (new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{" OR ", " IS NULL"})))).s(.MODULE$.genericWrapArray(new Object[]{this.column$1})) : "";
                  var10000 = (new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{"", " < ", "", ""})))).s(.MODULE$.genericWrapArray(new Object[]{this.column$1, BoxesRunTime.boxToLong(currPartitionUppBound), conditionForNulls}));
               } else {
                  var10000 = isLastPartition ? (new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{"", " >= ", ""})))).s(.MODULE$.genericWrapArray(new Object[]{this.column$1, BoxesRunTime.boxToLong(currPartitionLowBound)})) : (new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{"", " >= ", " AND ", " < ", ""})))).s(.MODULE$.genericWrapArray(new Object[]{this.column$1, BoxesRunTime.boxToLong(currPartitionLowBound), this.column$1, BoxesRunTime.boxToLong(currPartitionUppBound)}));
               }

               String whereClause = var10000;
               return ((ArrayBuffer)this.partitionArrayBuf$1.elem).$plus$eq(new GreenplumPartition(whereClause, partitionIndex));
            }

            public {
               this.numPartitions$1 = numPartitions$1;
               this.stride$1 = stride$1;
               this.column$1 = column$1;
               this.nullable$1 = nullable$1;
               this.partitionArrayBuf$1 = partitionArrayBuf$1;
               this.currentPartitionBoundaryValue$1 = currentPartitionBoundaryValue$1;
            }
         });
         var10000 = (GreenplumPartition[])((ArrayBuffer)partitionArrayBuf.elem).toArray(scala.reflect.ClassTag..MODULE$.apply(GreenplumPartition.class));
      }

      return var10000;
   }

   public GreenplumPartition[] segmentPartitions(int[] segmentIds) {
      return (GreenplumPartition[]).MODULE$.refArrayOps((Object[]).MODULE$.intArrayOps(segmentIds).zipWithIndex(scala.Array..MODULE$.canBuildFrom(scala.reflect.ClassTag..MODULE$.apply(Tuple2.class)))).map(new Serializable() {
         public static final long serialVersionUID = 0L;

         public final GreenplumPartition apply(Tuple2<Object, Object> x0$1) {
            if (x0$1 != null) {
               int segmentId = x0$1._1$mcI$sp();
               int index = x0$1._2$mcI$sp();
               GreenplumPartition var5 = new GreenplumPartition((new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{"gp_segment_id = ", ""})))).s(.MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(segmentId)})), index);
               return var5;
            } else {
               throw new MatchError(x0$1);
            }
         }
      }, scala.Array..MODULE$.canBuildFrom(scala.reflect.ClassTag..MODULE$.apply(GreenplumPartition.class)));
   }

   public GreenplumRelation apply(StructType schema, GreenplumPartition[] parts, GreenplumOptions greenplumOptions, SQLContext sqlContext) {
      return new GreenplumRelation(schema, parts, greenplumOptions, sqlContext);
   }

   public Option<Tuple3<StructType, GreenplumPartition[], GreenplumOptions>> unapply(GreenplumRelation x$0) {
      return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple3(x$0.schema(), x$0.parts(), x$0.greenplumOptions())));
   }

   private Object readResolve() {
      return MODULE$;
   }

   private final boolean onlyOnePartition$1(StructField partitionColumn$1, ColumnValueRange valueRange$1) {
      return partitionColumn$1 == null || valueRange$1 == null || valueRange$1.min() == valueRange$1.max();
   }

   private final boolean overflows$1(long positiveValue) {
      return positiveValue < 0L;
   }

   private GreenplumRelation$() {
      MODULE$ = this;
      LazyLogging$class.$init$(this);
   }
}
