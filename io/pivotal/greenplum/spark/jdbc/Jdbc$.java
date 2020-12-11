package io.pivotal.greenplum.spark.jdbc;

import com.typesafe.scalalogging.LazyLogging;
import com.typesafe.scalalogging.LazyLogging$class;
import com.typesafe.scalalogging.Logger;
import io.pivotal.greenplum.spark.GpfdistLocation;
import io.pivotal.greenplum.spark.GreenplumCSVFormat$;
import io.pivotal.greenplum.spark.externaltable.GreenplumQualifiedName;
import io.pivotal.greenplum.spark.externaltable.SqlObjectNameUtils$;
import java.sql.Connection;
import java.sql.JDBCType;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.spark.sql.types.AtomicType;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.DecimalType;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import resource.ExtractableManagedResource;
import resource.ManagedResource;
import resource.Resource$;
import resource.package$;
import scala.Function1;
import scala.MatchError;
import scala.Product;
import scala.Serializable;
import scala.StringContext;
import scala.collection.Seq;
import scala.collection.TraversableOnce;
import scala.collection.Seq.;
import scala.collection.immutable.StringOps;
import scala.collection.immutable.Vector;
import scala.collection.mutable.StringBuilder;
import scala.reflect.ClassTag;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.util.Either;
import scala.util.Left;
import scala.util.Right;

public final class Jdbc$ implements LazyLogging {
   public static final Jdbc$ MODULE$;
   private final transient Logger logger;
   private transient volatile boolean bitmap$trans$0;

   static {
      new Jdbc$();
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

   public void copyTableToExternal(Connection conn, GreenplumQualifiedName srcTable, GreenplumQualifiedName.TempTable extTable, String predicate, Seq<String> columns) {
      String colNames = columns.isEmpty() ? "1" : ((TraversableOnce)columns.map(new Serializable() {
         public static final long serialVersionUID = 0L;

         public final String apply(String colName) {
            return SqlObjectNameUtils$.MODULE$.escape(colName);
         }
      }, .MODULE$.canBuildFrom())).mkString(",");
      String sqlQuery = (new StringOps(scala.Predef..MODULE$.augmentString((new StringContext(scala.Predef..MODULE$.wrapRefArray((Object[])(new String[]{"\n         |INSERT INTO ", "\n         |SELECT ", "\n         |FROM ", "\n         |WHERE ", ""})))).s(scala.Predef..MODULE$.genericWrapArray(new Object[]{extTable, colNames, srcTable, predicate}))))).stripMargin();
      ExtractableManagedResource result = package$.MODULE$.managed(new Serializable(conn) {
         public static final long serialVersionUID = 0L;
         private final Connection conn$4;

         public final Statement apply() {
            return this.conn$4.createStatement();
         }

         public {
            this.conn$4 = conn$4;
         }
      }, Resource$.MODULE$.statementResource(), scala.reflect.ClassManifestFactory..MODULE$.classType(Statement.class)).map(new Serializable(sqlQuery) {
         public static final long serialVersionUID = 0L;
         private final String sqlQuery$1;

         public final void apply(Statement statement) {
            statement.executeUpdate(this.sqlQuery$1);
         }

         public {
            this.sqlQuery$1 = sqlQuery$1;
         }
      });
      result.tried().get();
   }

   public boolean externalTableExists(Connection conn, GreenplumQualifiedName.TempTable table) {
      String sqlQuery = (new StringOps(scala.Predef..MODULE$.augmentString((new StringContext(scala.Predef..MODULE$.wrapRefArray((Object[])(new String[]{"SELECT EXISTS (\n         |SELECT 1\n         |FROM information_schema.tables\n         |WHERE table_name = '", "' );"})))).s(scala.Predef..MODULE$.genericWrapArray(new Object[]{table.name()}))))).stripMargin();
      ManagedResource result = package$.MODULE$.managed(new Serializable(conn) {
         public static final long serialVersionUID = 0L;
         private final Connection conn$1;

         public final Statement apply() {
            return this.conn$1.createStatement();
         }

         public {
            this.conn$1 = conn$1;
         }
      }, Resource$.MODULE$.statementResource(), scala.reflect.ClassManifestFactory..MODULE$.classType(Statement.class)).flatMap(new Serializable(sqlQuery) {
         public static final long serialVersionUID = 0L;
         public final String sqlQuery$2;

         public final ExtractableManagedResource<Object> apply(Statement statement) {
            return package$.MODULE$.managed(new Serializable(this, statement) {
               public static final long serialVersionUID = 0L;
               // $FF: synthetic field
               private final <undefinedtype> $outer;
               private final Statement statement$1;

               public final ResultSet apply() {
                  return this.statement$1.executeQuery(this.$outer.sqlQuery$2);
               }

               public {
                  if ($outer == null) {
                     throw null;
                  } else {
                     this.$outer = $outer;
                     this.statement$1 = statement$1;
                  }
               }
            }, Resource$.MODULE$.resultSetResource(), scala.reflect.ClassManifestFactory..MODULE$.classType(ResultSet.class)).map(new Serializable(this) {
               public static final long serialVersionUID = 0L;

               public final boolean apply(ResultSet result) {
                  result.next();
                  return result.getBoolean(1);
               }
            });
         }

         public {
            this.sqlQuery$2 = sqlQuery$2;
         }
      });
      return BoxesRunTime.unboxToBoolean(result.map(new Serializable() {
         public static final long serialVersionUID = 0L;

         public final boolean apply(boolean x) {
            return x;
         }
      }).opt().get());
   }

   public Seq<String> getColumnsMetadata(Connection conn, GreenplumQualifiedName.Table table, Seq<String> columnNames) {
      String schema = table.schema();
      String srcTable = table.name();
      String columnNamesJoined = columnNames.mkString("','");
      String sqlQuery = (new StringOps(scala.Predef..MODULE$.augmentString((new StringContext(scala.Predef..MODULE$.wrapRefArray((Object[])(new String[]{"\n         |SELECT '\"' || a.attname || '\" ' ||\n         |pg_catalog.format_type(a.atttypid, a.atttypmod) as column_metadata\n         |FROM pg_catalog.pg_attribute a, pg_class b, pg_namespace n\n         |WHERE n.nspname = '", "'\n         |AND n.oid = b.relnamespace\n         |AND a.attrelid = b.oid\n         |AND b.relname = '", "'\n         |AND a.attnum > 0 AND NOT a.attisdropped\n         |AND a.attname IN ('", "')\n         |ORDER BY strpos(\n         |E'\\\\'", "\\\\'',\n         |E'\\\\'' || a.attname || E'\\\\'')\n       "})))).s(scala.Predef..MODULE$.genericWrapArray(new Object[]{schema, srcTable, columnNamesJoined, columnNames.mkString("\\',\\'")}))))).stripMargin();
      Seq results = this.retrieveResults(conn, sqlQuery, new Serializable() {
         public static final long serialVersionUID = 0L;

         public final String apply(ResultSet x$1) {
            return x$1.getString("column_metadata");
         }
      }, scala.reflect.ClassTag..MODULE$.apply(String.class));
      if (results.size() != columnNames.size()) {
         throw new RuntimeException((new StringContext(scala.Predef..MODULE$.wrapRefArray((Object[])(new String[]{"Error retrieving metadata for ", " for ", ""})))).s(scala.Predef..MODULE$.genericWrapArray(new Object[]{columnNames.mkString(","), srcTable})));
      } else {
         return results;
      }
   }

   public void createGpfdistWritableExternalTable(Connection conn, GreenplumQualifiedName.Table srcTable, GreenplumQualifiedName.TempTable extTable, GpfdistLocation gpfdistLocation, Seq<String> columns) {
      String url = gpfdistLocation.generate(extTable.name());
      String projectedColumnsMetadata = columns.isEmpty() ? "dummy int" : this.getColumnsMetadata(conn, srcTable, columns).mkString(",");
      String sqlQuery = (new StringOps(scala.Predef..MODULE$.augmentString((new StringContext(scala.Predef..MODULE$.wrapRefArray((Object[])(new String[]{"CREATE WRITABLE EXTERNAL TEMP TABLE ", " (", ")\n         |LOCATION ('", "')\n         |FORMAT 'CSV'\n         |(DELIMITER '", "'\n         | NULL AS '", "')\n         |ENCODING '", "'\n         |DISTRIBUTED RANDOMLY"})))).s(scala.Predef..MODULE$.genericWrapArray(new Object[]{extTable, projectedColumnsMetadata, url, BoxesRunTime.boxToCharacter(GreenplumCSVFormat$.MODULE$.CHAR_DELIMITER()), GreenplumCSVFormat$.MODULE$.VALUE_OF_NULL(), GreenplumCSVFormat$.MODULE$.DEFAULT_ENCODING()}))))).stripMargin();
      BoxedUnit var10000;
      if (this.logger().underlying().isDebugEnabled()) {
         this.logger().underlying().debug(sqlQuery);
         var10000 = BoxedUnit.UNIT;
      } else {
         var10000 = BoxedUnit.UNIT;
      }

      package$.MODULE$.managed(new Serializable(conn) {
         public static final long serialVersionUID = 0L;
         private final Connection conn$2;

         public final Statement apply() {
            return this.conn$2.createStatement();
         }

         public {
            this.conn$2 = conn$2;
         }
      }, Resource$.MODULE$.statementResource(), scala.reflect.ClassManifestFactory..MODULE$.classType(Statement.class)).foreach(new Serializable(sqlQuery) {
         public static final long serialVersionUID = 0L;
         private final String sqlQuery$4;

         public final void apply(Statement statement) {
            statement.execute(this.sqlQuery$4);
         }

         public {
            this.sqlQuery$4 = sqlQuery$4;
         }
      });
   }

   public int[] retrieveSegmentIds(Connection conn) {
      String sql = (new StringOps(scala.Predef..MODULE$.augmentString("\n        |SELECT content\n        |FROM gp_segment_configuration\n        |WHERE content != -1 AND role = 'p'\n        |ORDER BY content"))).stripMargin();
      return (int[])this.retrieveResults(conn, sql, new Serializable() {
         public static final long serialVersionUID = 0L;

         public final int apply(ResultSet x$2) {
            return x$2.getInt("content");
         }
      }, scala.reflect.ClassTag..MODULE$.Int()).toArray(scala.reflect.ClassTag..MODULE$.Int());
   }

   public <T> Seq<T> retrieveResults(Connection conn, String sqlQuery, Function1<ResultSet, T> getter, ClassTag<T> evidence$1) {
      ManagedResource result = package$.MODULE$.managed(new Serializable(conn) {
         public static final long serialVersionUID = 0L;
         private final Connection conn$5;

         public final Statement apply() {
            return this.conn$5.createStatement();
         }

         public {
            this.conn$5 = conn$5;
         }
      }, Resource$.MODULE$.statementResource(), scala.reflect.ClassManifestFactory..MODULE$.classType(Statement.class)).flatMap(new Serializable(sqlQuery, getter, evidence$1) {
         public static final long serialVersionUID = 0L;
         public final String sqlQuery$3;
         public final Function1 getter$1;
         public final ClassTag evidence$1$1;

         public final ExtractableManagedResource<Vector<T>> apply(Statement statement) {
            return package$.MODULE$.managed(new Serializable(this, statement) {
               public static final long serialVersionUID = 0L;
               // $FF: synthetic field
               private final <undefinedtype> $outer;
               private final Statement statement$2;

               public final ResultSet apply() {
                  return this.statement$2.executeQuery(this.$outer.sqlQuery$3);
               }

               public {
                  if ($outer == null) {
                     throw null;
                  } else {
                     this.$outer = $outer;
                     this.statement$2 = statement$2;
                  }
               }
            }, Resource$.MODULE$.resultSetResource(), scala.reflect.ClassManifestFactory..MODULE$.classType(ResultSet.class)).map(new Serializable(this) {
               public static final long serialVersionUID = 0L;
               // $FF: synthetic field
               private final <undefinedtype> $outer;

               public final Vector<T> apply(ResultSet resultSet) {
                  return Jdbc$.MODULE$.io$pivotal$greenplum$spark$jdbc$Jdbc$$collectFrom(resultSet, this.$outer.getter$1, this.$outer.evidence$1$1);
               }

               public {
                  if ($outer == null) {
                     throw null;
                  } else {
                     this.$outer = $outer;
                  }
               }
            });
         }

         public {
            this.sqlQuery$3 = sqlQuery$3;
            this.getter$1 = getter$1;
            this.evidence$1$1 = evidence$1$1;
         }
      });
      return (Seq)result.map(new Serializable() {
         public static final long serialVersionUID = 0L;

         public final Vector<T> apply(Vector<T> x) {
            return x;
         }
      }).opt().get();
   }

   public ColumnValueRange computeColumnValueRange(Connection conn, GreenplumQualifiedName.Table table, String columnName) {
      String tableName = table.name();
      String sqlStatSubQuery = (new StringOps(scala.Predef..MODULE$.augmentString((new StringContext(scala.Predef..MODULE$.wrapRefArray((Object[])(new String[]{"SELECT CAST(UNNEST(string_to_array(array_to_string\n       |             (most_common_vals || histogram_bounds, ','), ',')) AS\n       |              FLOAT) AS combined_histogram\n       |      FROM pg_stats ps\n       |      WHERE tablename = '", "'\n       |      AND ps.attname = '", "'\n       |      AND histogram_bounds IS NOT NULL\n       "})))).s(scala.Predef..MODULE$.genericWrapArray(new Object[]{tableName, columnName}))))).stripMargin();
      String sqlStatQuery = (new StringOps(scala.Predef..MODULE$.augmentString((new StringContext(scala.Predef..MODULE$.wrapRefArray((Object[])(new String[]{"\n         |SELECT\n         |  MIN(combined_histogram) approx_min,\n         |  MAX(combined_histogram) approx_max\n         |FROM (", ") t\n         |      HAVING MIN(combined_histogram) IS NOT NULL\n         |      AND MAX(combined_histogram) IS NOT NULL"})))).s(scala.Predef..MODULE$.genericWrapArray(new Object[]{sqlStatSubQuery}))))).stripMargin();
      Either var7 = this.queryColumnValueRange(conn, sqlStatQuery);
      ColumnValueRange var10;
      BoxedUnit var10000;
      if (var7 instanceof Right) {
         Right var8 = (Right)var7;
         ColumnValueRange statsRange = (ColumnValueRange)var8.b();
         if (this.logger().underlying().isInfoEnabled()) {
            this.logger().underlying().info("Using approximate statistics data for column {}.{} range", (Object[])((Object[])(new String[]{tableName, columnName})));
            var10000 = BoxedUnit.UNIT;
         } else {
            var10000 = BoxedUnit.UNIT;
         }

         var10 = statsRange;
      } else {
         if (!(var7 instanceof Left)) {
            throw new MatchError(var7);
         }

         if (this.logger().underlying().isInfoEnabled()) {
            this.logger().underlying().info("Using accurate aggregated data for column {}.{} range", (Object[])((Object[])(new String[]{tableName, columnName})));
            var10000 = BoxedUnit.UNIT;
         } else {
            var10000 = BoxedUnit.UNIT;
         }

         String sqlAggQuery = (new StringOps(scala.Predef..MODULE$.augmentString((new StringContext(scala.Predef..MODULE$.wrapRefArray((Object[])(new String[]{"SELECT MIN(\"", "\"), MAX(\"", "\")\n             | FROM ", ";"})))).s(scala.Predef..MODULE$.genericWrapArray(new Object[]{columnName, columnName, table}))))).stripMargin();
         Either var12 = this.queryColumnValueRange(conn, sqlAggQuery);
         if (!(var12 instanceof Right)) {
            if (var12 instanceof Left) {
               Left var16 = (Left)var12;
               String error = (String)var16.a();
               if (this.logger().underlying().isErrorEnabled()) {
                  this.logger().underlying().error(error);
                  var10000 = BoxedUnit.UNIT;
               } else {
                  var10000 = BoxedUnit.UNIT;
               }

               throw new IllegalArgumentException((new StringContext(scala.Predef..MODULE$.wrapRefArray((Object[])(new String[]{"Unable to determine range for a column ", ".", ""})))).s(scala.Predef..MODULE$.genericWrapArray(new Object[]{tableName, columnName})));
            }

            throw new MatchError(var12);
         }

         Right var13 = (Right)var12;
         ColumnValueRange aggRange = (ColumnValueRange)var13.b();
         var10 = aggRange;
      }

      return var10;
   }

   private Either<String, ColumnValueRange> queryColumnValueRange(Connection conn, String sqlQuery) {
      ManagedResource result = package$.MODULE$.managed(new Serializable(conn) {
         public static final long serialVersionUID = 0L;
         private final Connection conn$6;

         public final Statement apply() {
            return this.conn$6.createStatement();
         }

         public {
            this.conn$6 = conn$6;
         }
      }, Resource$.MODULE$.statementResource(), scala.reflect.ClassManifestFactory..MODULE$.classType(Statement.class)).flatMap(new Serializable(sqlQuery) {
         public static final long serialVersionUID = 0L;
         public final String sqlQuery$5;

         public final ExtractableManagedResource<Product> apply(Statement statement) {
            return package$.MODULE$.managed(new Serializable(this, statement) {
               public static final long serialVersionUID = 0L;
               // $FF: synthetic field
               private final <undefinedtype> $outer;
               private final Statement statement$3;

               public final ResultSet apply() {
                  return this.statement$3.executeQuery(this.$outer.sqlQuery$5);
               }

               public {
                  if ($outer == null) {
                     throw null;
                  } else {
                     this.$outer = $outer;
                     this.statement$3 = statement$3;
                  }
               }
            }, Resource$.MODULE$.resultSetResource(), scala.reflect.ClassManifestFactory..MODULE$.classType(ResultSet.class)).map(new Serializable(this) {
               public static final long serialVersionUID = 0L;
               // $FF: synthetic field
               private final <undefinedtype> $outer;

               public final Product apply(ResultSet resultSet) {
                  Vector columnValueRanges = Jdbc$.MODULE$.io$pivotal$greenplum$spark$jdbc$Jdbc$$collectFrom(resultSet, new Serializable(this) {
                     public static final long serialVersionUID = 0L;

                     public final ColumnValueRange apply(ResultSet rs) {
                        return new ColumnValueRange(rs.getLong(1), rs.getLong(2));
                     }
                  }, scala.reflect.ClassTag..MODULE$.apply(ColumnValueRange.class));
                  return (Either)(columnValueRanges.length() == 1 ? scala.package..MODULE$.Right().apply(columnValueRanges.apply(0)) : (columnValueRanges.isEmpty() ? scala.package..MODULE$.Left().apply((new StringContext(scala.Predef..MODULE$.wrapRefArray((Object[])(new String[]{"Empty result set for a query: ", ""})))).s(scala.Predef..MODULE$.genericWrapArray(new Object[]{this.$outer.sqlQuery$5}))) : scala.package..MODULE$.Left().apply((new StringContext(scala.Predef..MODULE$.wrapRefArray((Object[])(new String[]{"Too many rows for a query: ", ""})))).s(scala.Predef..MODULE$.genericWrapArray(new Object[]{this.$outer.sqlQuery$5})))));
               }

               public {
                  if ($outer == null) {
                     throw null;
                  } else {
                     this.$outer = $outer;
                  }
               }
            });
         }

         public {
            this.sqlQuery$5 = sqlQuery$5;
         }
      });
      return (Either)result.map(new Serializable() {
         public static final long serialVersionUID = 0L;

         public final Product apply(Product x) {
            return x;
         }
      }).opt().getOrElse(new Serializable(sqlQuery) {
         public static final long serialVersionUID = 0L;
         private final String sqlQuery$5;

         public final Left<String, scala.runtime.Nothing.> apply() {
            return scala.package..MODULE$.Left().apply((new StringContext(scala.Predef..MODULE$.wrapRefArray((Object[])(new String[]{"Error while executing ", ""})))).s(scala.Predef..MODULE$.genericWrapArray(new Object[]{this.sqlQuery$5})));
         }

         public {
            this.sqlQuery$5 = sqlQuery$5;
         }
      });
   }

   public StructType resolveTable(Connection conn, String url, GreenplumQualifiedName table) {
      String sql = (new StringOps(scala.Predef..MODULE$.augmentString((new StringContext(scala.Predef..MODULE$.wrapRefArray((Object[])(new String[]{"SELECT * FROM ", " LIMIT 0"})))).s(scala.Predef..MODULE$.genericWrapArray(new Object[]{table}))))).stripMargin();
      Statement statement = conn.createStatement();

      Object var10000;
      try {
         statement.execute("set optimizer = off;");
         ResultSet rs;
         var10000 = rs = statement.executeQuery(sql);

         try {
            var10000 = this.getSchema(rs.getMetaData());
         } finally {
            rs.close();
         }
      } finally {
         statement.close();
      }

      return (StructType)var10000;
   }

   public Seq<String> getColumnNames(ResultSetMetaData meta) {
      return (Seq)scala.runtime.RichInt..MODULE$.to$extension0(scala.Predef..MODULE$.intWrapper(1), meta.getColumnCount()).map(new Serializable(meta) {
         public static final long serialVersionUID = 0L;
         private final ResultSetMetaData meta$1;

         public final String apply(int i) {
            return this.meta$1.getColumnLabel(i);
         }

         public {
            this.meta$1 = meta$1;
         }
      }, scala.collection.immutable.IndexedSeq..MODULE$.canBuildFrom());
   }

   public StructType getSchema(ResultSetMetaData meta) {
      StructField[] fields = new StructField[meta.getColumnCount()];
      scala.runtime.RichInt..MODULE$.to$extension0(scala.Predef..MODULE$.intWrapper(1), meta.getColumnCount()).foreach$mVc$sp(new Serializable(meta, fields) {
         public static final long serialVersionUID = 0L;
         private final ResultSetMetaData meta$2;
         private final StructField[] fields$1;

         public final void apply(int i) {
            this.apply$mcVI$sp(i);
         }

         public void apply$mcVI$sp(int i) {
            String columnLabel = this.meta$2.getColumnLabel(i);
            int dataType = this.meta$2.getColumnType(i);
            int fieldSize = this.meta$2.getPrecision(i);
            int fieldScale = this.meta$2.getScale(i);
            boolean isSigned = this.meta$2.isSigned(i);
            int var8 = this.meta$2.isNullable(i);
            boolean var9;
            if (1 == var8) {
               var9 = true;
            } else {
               if (0 != var8) {
                  boolean var10;
                  if (2 == var8) {
                     var10 = true;
                  } else {
                     var10 = true;
                  }

                  if (var10) {
                     throw new SQLException((new StringContext(scala.Predef..MODULE$.wrapRefArray((Object[])(new String[]{"Could not determine if column ", " is nullable"})))).s(scala.Predef..MODULE$.genericWrapArray(new Object[]{columnLabel})));
                  }

                  throw new MatchError(BoxesRunTime.boxToInteger(var8));
               }

               var9 = false;
            }

            DataType columnType = Jdbc$.MODULE$.io$pivotal$greenplum$spark$jdbc$Jdbc$$getCatalystType(dataType, fieldSize, fieldScale, isSigned);
            this.fields$1[i - 1] = new StructField(columnLabel, columnType, var9, org.apache.spark.sql.types.StructField..MODULE$.apply$default$4());
         }

         public {
            this.meta$2 = meta$2;
            this.fields$1 = fields$1;
         }
      });
      return new StructType(fields);
   }

   public DataType io$pivotal$greenplum$spark$jdbc$Jdbc$$getCatalystType(int sqlType, int precision, int scale, boolean signed) {
      Object var10000;
      switch(sqlType) {
      case -16:
      case -15:
      case -9:
      case -1:
      case 1:
      case 12:
      case 2002:
      case 2005:
      case 2006:
      case 2009:
      case 2011:
         var10000 = org.apache.spark.sql.types.StringType..MODULE$;
         break;
      case -8:
         var10000 = org.apache.spark.sql.types.LongType..MODULE$;
         break;
      case -7:
         var10000 = precision == 1 ? org.apache.spark.sql.types.BooleanType..MODULE$ : org.apache.spark.sql.types.BinaryType..MODULE$;
         break;
      case -6:
         var10000 = org.apache.spark.sql.types.IntegerType..MODULE$;
         break;
      case -5:
         var10000 = signed ? org.apache.spark.sql.types.LongType..MODULE$ : new DecimalType(20, 0);
         break;
      case -4:
      case -3:
      case -2:
      case 2004:
         var10000 = org.apache.spark.sql.types.BinaryType..MODULE$;
         break;
      case 0:
      case 70:
      case 1111:
      case 2000:
      case 2001:
      case 2003:
      case 2012:
      case 2013:
      case 2014:
         var10000 = null;
         break;
      case 2:
      case 3:
         var10000 = precision == 0 && scale == 0 ? org.apache.spark.sql.types.DecimalType..MODULE$.SYSTEM_DEFAULT() : new DecimalType(precision, scale);
         break;
      case 4:
         var10000 = signed ? org.apache.spark.sql.types.IntegerType..MODULE$ : org.apache.spark.sql.types.LongType..MODULE$;
         break;
      case 5:
         var10000 = org.apache.spark.sql.types.ShortType..MODULE$;
         break;
      case 6:
      case 7:
         var10000 = org.apache.spark.sql.types.FloatType..MODULE$;
         break;
      case 8:
         var10000 = org.apache.spark.sql.types.DoubleType..MODULE$;
         break;
      case 16:
         var10000 = org.apache.spark.sql.types.BooleanType..MODULE$;
         break;
      case 91:
         var10000 = org.apache.spark.sql.types.DateType..MODULE$;
         break;
      case 92:
      case 93:
         var10000 = org.apache.spark.sql.types.TimestampType..MODULE$;
         break;
      default:
         throw new SQLException((new StringBuilder()).append("Unrecognized SQL type ").append(BoxesRunTime.boxToInteger(sqlType)).toString());
      }

      AtomicType answer = var10000;
      if (answer == null) {
         throw new IllegalArgumentException((new StringBuilder()).append("Unsupported type ").append(JDBCType.valueOf(sqlType).getName()).toString());
      } else {
         return (DataType)answer;
      }
   }

   public <T> Vector<T> io$pivotal$greenplum$spark$jdbc$Jdbc$$collectFrom(ResultSet resultSet, Function1<ResultSet, T> getter, ClassTag<T> evidence$2) {
      return this.inner$1(scala.package..MODULE$.Vector().empty(), resultSet, getter);
   }

   public String getDistributedTransactionId(Connection conn) {
      String sqlQuery = (new StringOps(scala.Predef..MODULE$.augmentString("\n        |SELECT x.distributed_id\n        |FROM pg_settings s, gp_distributed_xacts x\n        |WHERE s.setting::int = x.gp_session_id\n        |AND s.name = 'gp_session_id'\n        |AND x.state IN ('Active Distributed', 'None')\n      "))).stripMargin();
      ManagedResource result = package$.MODULE$.managed(new Serializable(conn) {
         public static final long serialVersionUID = 0L;
         private final Connection conn$3;

         public final Statement apply() {
            return this.conn$3.createStatement();
         }

         public {
            this.conn$3 = conn$3;
         }
      }, Resource$.MODULE$.statementResource(), scala.reflect.ClassManifestFactory..MODULE$.classType(Statement.class)).flatMap(new Serializable(sqlQuery) {
         public static final long serialVersionUID = 0L;
         public final String sqlQuery$6;

         public final ExtractableManagedResource<String> apply(Statement statement) {
            return package$.MODULE$.managed(new Serializable(this, statement) {
               public static final long serialVersionUID = 0L;
               // $FF: synthetic field
               private final <undefinedtype> $outer;
               private final Statement statement$4;

               public final ResultSet apply() {
                  return this.statement$4.executeQuery(this.$outer.sqlQuery$6);
               }

               public {
                  if ($outer == null) {
                     throw null;
                  } else {
                     this.$outer = $outer;
                     this.statement$4 = statement$4;
                  }
               }
            }, Resource$.MODULE$.resultSetResource(), scala.reflect.ClassManifestFactory..MODULE$.classType(ResultSet.class)).map(new Serializable(this) {
               public static final long serialVersionUID = 0L;

               public final String apply(ResultSet rs) {
                  Vector transactionIdVector = Jdbc$.MODULE$.io$pivotal$greenplum$spark$jdbc$Jdbc$$collectFrom(rs, new Serializable(this) {
                     public static final long serialVersionUID = 0L;

                     public final String apply(ResultSet x$3) {
                        return x$3.getString("distributed_id");
                     }
                  }, scala.reflect.ClassTag..MODULE$.apply(String.class));
                  if (transactionIdVector.size() != 1) {
                     throw new RuntimeException("Unable to retrieve current distributed transaction id");
                  } else {
                     return (String)transactionIdVector.apply(0);
                  }
               }
            });
         }

         public {
            this.sqlQuery$6 = sqlQuery$6;
         }
      });
      return (String)result.map(new Serializable() {
         public static final long serialVersionUID = 0L;

         public final String apply(String x) {
            return x;
         }
      }).opt().get();
   }

   private final Vector inner$1(Vector acc, ResultSet rs, Function1 getter$2) {
      while(rs.next()) {
         Vector var10000 = (Vector)acc.$colon$plus(getter$2.apply(rs), scala.collection.immutable.Vector..MODULE$.canBuildFrom());
         rs = rs;
         acc = var10000;
      }

      return acc;
   }

   private Jdbc$() {
      MODULE$ = this;
      LazyLogging$class.$init$(this);
   }
}
