package io.pivotal.greenplum.spark.externaltable;

import io.pivotal.greenplum.spark.conf.GreenplumOptions;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.spark.sql.types.AtomicType;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.DecimalType;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import org.apache.spark.sql.types.StringType.;
import scala.MatchError;
import scala.Option;
import scala.Serializable;
import scala.Some;
import scala.StringContext;
import scala.collection.Seq;
import scala.collection.TraversableOnce;
import scala.collection.mutable.StringBuilder;
import scala.runtime.BoxesRunTime;
import scala.util.Try;

public final class GreenplumTableManager$ {
   public static final GreenplumTableManager$ MODULE$;

   static {
      new GreenplumTableManager$();
   }

   public Option<String> gpdbColumnType(DataType dt) {
      Object var3;
      if (.MODULE$.equals(dt)) {
         var3 = new Some("TEXT");
      } else if (org.apache.spark.sql.types.BinaryType..MODULE$.equals(dt)) {
         var3 = new Some("BYTEA");
      } else if (org.apache.spark.sql.types.BooleanType..MODULE$.equals(dt)) {
         var3 = new Some("BOOLEAN");
      } else if (org.apache.spark.sql.types.FloatType..MODULE$.equals(dt)) {
         var3 = new Some("FLOAT4");
      } else if (org.apache.spark.sql.types.DoubleType..MODULE$.equals(dt)) {
         var3 = new Some("FLOAT8");
      } else if (org.apache.spark.sql.types.ShortType..MODULE$.equals(dt)) {
         var3 = new Some("SMALLINT");
      } else if (dt instanceof DecimalType) {
         DecimalType var4 = (DecimalType)dt;
         var3 = new Some((new StringContext(scala.Predef..MODULE$.wrapRefArray((Object[])(new String[]{"NUMERIC(", ",", ")"})))).s(scala.Predef..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(var4.precision()), BoxesRunTime.boxToInteger(var4.scale())})));
      } else if (org.apache.spark.sql.types.IntegerType..MODULE$.equals(dt)) {
         var3 = new Some("INTEGER");
      } else if (org.apache.spark.sql.types.LongType..MODULE$.equals(dt)) {
         var3 = new Some("BIGINT");
      } else if (org.apache.spark.sql.types.TimestampType..MODULE$.equals(dt)) {
         var3 = new Some("TIMESTAMP");
      } else if (org.apache.spark.sql.types.DateType..MODULE$.equals(dt)) {
         var3 = new Some("DATE");
      } else {
         var3 = scala.None..MODULE$;
      }

      return (Option)var3;
   }

   public Try<String> createTableColumnList(StructType sparkSchema) {
      return scala.util.Try..MODULE$.apply(new Serializable(sparkSchema) {
         public static final long serialVersionUID = 0L;
         private final StructType sparkSchema$2;

         public final String apply() {
            return scala.Predef..MODULE$.refArrayOps((Object[])scala.Predef..MODULE$.refArrayOps((Object[])this.sparkSchema$2.fields()).map(new Serializable(this) {
               public static final long serialVersionUID = 0L;

               public final String apply(StructField x0$1) {
                  if (x0$1 != null) {
                     String name = x0$1.name();
                     DataType dataType = x0$1.dataType();
                     boolean nullable = x0$1.nullable();
                     String nullableStr = nullable ? "" : " NOT NULL";
                     String typ = (String)GreenplumTableManager$.MODULE$.gpdbColumnType(dataType).getOrElse(new Serializable(this, name, dataType) {
                        public static final long serialVersionUID = 0L;
                        private final String name$1;
                        private final DataType dataType$1;

                        public final scala.runtime.Nothing. apply() {
                           throw new IllegalArgumentException((new StringBuilder()).append((new StringContext(scala.Predef..MODULE$.wrapRefArray((Object[])(new String[]{"Column ", " has unsupported type ", ". "})))).s(scala.Predef..MODULE$.genericWrapArray(new Object[]{this.name$1, this.dataType$1}))).append("Please use one of the supported Spark data types: ").append((new StringContext(scala.Predef..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).s(scala.Predef..MODULE$.genericWrapArray(new Object[]{((TraversableOnce)GreenplumTableManager$.MODULE$.getSupportedSparkDataTypes().map(new Serializable(this) {
                              public static final long serialVersionUID = 0L;

                              public final String apply(DataType x$3) {
                                 return x$3.toString();
                              }
                           }, scala.collection.Seq..MODULE$.canBuildFrom())).mkString(", ")}))).toString());
                        }

                        public {
                           this.name$1 = name$1;
                           this.dataType$1 = dataType$1;
                        }
                     });
                     String var6 = (new StringContext(scala.Predef..MODULE$.wrapRefArray((Object[])(new String[]{"", " ", "", ""})))).s(scala.Predef..MODULE$.genericWrapArray(new Object[]{SqlObjectNameUtils$.MODULE$.escape(name), typ, nullableStr}));
                     return var6;
                  } else {
                     throw new MatchError(x0$1);
                  }
               }
            }, scala.Array..MODULE$.canBuildFrom(scala.reflect.ClassTag..MODULE$.apply(String.class)))).mkString(", ");
         }

         public {
            this.sparkSchema$2 = sparkSchema$2;
         }
      });
   }

   public Seq<DataType> getSupportedSparkDataTypes() {
      return (Seq)scala.collection.Seq..MODULE$.apply(scala.Predef..MODULE$.wrapRefArray((Object[])(new AtomicType[]{.MODULE$, org.apache.spark.sql.types.BinaryType..MODULE$, org.apache.spark.sql.types.BooleanType..MODULE$, org.apache.spark.sql.types.FloatType..MODULE$, org.apache.spark.sql.types.DoubleType..MODULE$, org.apache.spark.sql.types.ShortType..MODULE$, new DecimalType() {
         public String toString() {
            return "DecimalType";
         }
      }, org.apache.spark.sql.types.IntegerType..MODULE$, org.apache.spark.sql.types.LongType..MODULE$, org.apache.spark.sql.types.TimestampType..MODULE$, org.apache.spark.sql.types.DateType..MODULE$})));
   }

   public Try<String> createTableStatement(GreenplumQualifiedName tableName, StructType schema, GreenplumOptions options) {
      Option var5 = options.distributedBy();
      String var8;
      if (var5 instanceof Some) {
         Some var6 = (Some)var5;
         String columns = (String)var6.x();
         var8 = (new StringContext(scala.Predef..MODULE$.wrapRefArray((Object[])(new String[]{" DISTRIBUTED BY (", ")"})))).s(scala.Predef..MODULE$.genericWrapArray(new Object[]{columns}));
      } else {
         if (!scala.None..MODULE$.equals(var5)) {
            throw new MatchError(var5);
         }

         var8 = " DISTRIBUTED RANDOMLY";
      }

      return this.createTableColumnList(schema).map(new Serializable(tableName, var8) {
         public static final long serialVersionUID = 0L;
         private final GreenplumQualifiedName tableName$1;
         private final String distribution$1;

         public final String apply(String columns) {
            return (new StringContext(scala.Predef..MODULE$.wrapRefArray((Object[])(new String[]{"CREATE TABLE ", " (", ")", ";"})))).s(scala.Predef..MODULE$.genericWrapArray(new Object[]{this.tableName$1, columns, this.distribution$1}));
         }

         public {
            this.tableName$1 = tableName$1;
            this.distribution$1 = distribution$1;
         }
      });
   }

   public String generateExternalTableNamePrefix(String applicationId, String tableName) {
      String applicationTableHash = this.foldedMd5Hex((new StringBuilder()).append(applicationId).append(tableName).toString());
      return (new StringContext(scala.Predef..MODULE$.wrapRefArray((Object[])(new String[]{"spark_", ""})))).s(scala.Predef..MODULE$.genericWrapArray(new Object[]{applicationTableHash}));
   }

   public String generateExternalTableNameColumnPrefix(String applicationId, String table, Seq<String> columns) {
      String applicationTableHash = this.generateExternalTableNamePrefix(applicationId, table);
      String columnsHash = this.foldedMd5Hex(columns.mkString(","));
      return (new StringContext(scala.Predef..MODULE$.wrapRefArray((Object[])(new String[]{"", "_", ""})))).s(scala.Predef..MODULE$.genericWrapArray(new Object[]{applicationTableHash, columnsHash}));
   }

   public String generateExternalTableName(String applicationId, String table, String executorId, long threadId, Seq<String> columns) {
      String prefix = this.generateExternalTableNameColumnPrefix(applicationId, table, columns);
      return (new StringContext(scala.Predef..MODULE$.wrapRefArray((Object[])(new String[]{"", "_", "_", ""})))).s(scala.Predef..MODULE$.genericWrapArray(new Object[]{prefix, executorId, BoxesRunTime.boxToLong(threadId)}));
   }

   public Seq<String> generateExternalTableName$default$5() {
      return (Seq)scala.collection.Seq..MODULE$.empty();
   }

   private String foldedMd5Hex(String s) {
      byte[] md5In16 = DigestUtils.md5(s);
      byte[] md5In8 = (byte[])((TraversableOnce)scala.runtime.RichInt..MODULE$.to$extension0(scala.Predef..MODULE$.intWrapper(0), 7).map(new Serializable(md5In16) {
         public static final long serialVersionUID = 0L;
         private final byte[] md5In16$1;

         public final byte apply(int i) {
            return (byte)((byte)(this.md5In16$1[i] ^ this.md5In16$1[i + 8]));
         }

         public {
            this.md5In16$1 = md5In16$1;
         }
      }, scala.collection.immutable.IndexedSeq..MODULE$.canBuildFrom())).toArray(scala.reflect.ClassTag..MODULE$.Byte());
      String md5In16Hex = Hex.encodeHexString(md5In8);
      return md5In16Hex;
   }

   private GreenplumTableManager$() {
      MODULE$ = this;
   }
}
