package io.pivotal.greenplum.spark;

import org.apache.spark.sql.DataFrameReader;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.StructField;
import scala.StringContext;
import scala.collection.mutable.StringBuilder;
import scala.runtime.BoxedUnit;
import scala.util.Either;
import scala.util.Left.;

public final class GreenplumRelationProvider$ {
   public static final GreenplumRelationProvider$ MODULE$;

   static {
      new GreenplumRelationProvider$();
   }

   public <A> DataFrameReader apply(DataFrameReader t) {
      return t;
   }

   public DataFrameReader GreenplumDataFrameReader(DataFrameReader reader) {
      return reader;
   }

   public Either<BoxedUnit, IllegalArgumentException> checkPartitionColumnType(StructField column) {
      DataType var2 = column.dataType();
       var10000;
      scala.Unit. var10001;
      Object var3;
      if (org.apache.spark.sql.types.IntegerType..MODULE$.equals(var2)) {
         var10000 = scala.package..MODULE$.Left();
         var10001 = scala.Unit..MODULE$;
         var3 = var10000.apply(BoxedUnit.UNIT);
      } else if (org.apache.spark.sql.types.LongType..MODULE$.equals(var2)) {
         var10000 = scala.package..MODULE$.Left();
         var10001 = scala.Unit..MODULE$;
         var3 = var10000.apply(BoxedUnit.UNIT);
      } else {
         String message = (new StringBuilder()).append((new StringContext(scala.Predef..MODULE$.wrapRefArray((Object[])(new String[]{"data type of '", "' is not supported for partitioning; "})))).s(scala.Predef..MODULE$.genericWrapArray(new Object[]{column.name()}))).append("supported data types are bigint, bigserial, integer, and serial").toString();
         var3 = scala.package..MODULE$.Right().apply(new IllegalArgumentException(message));
      }

      return (Either)var3;
   }

   private GreenplumRelationProvider$() {
      MODULE$ = this;
   }
}
