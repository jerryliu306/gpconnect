package io.pivotal.greenplum.spark.externaltable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.time.temporal.Temporal;
import org.apache.spark.sql.catalyst.expressions.SpecificInternalRow;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.DecimalType;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import org.apache.spark.unsafe.types.UTF8String;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.Serializable;
import scala.StringContext;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.immutable.StringOps;
import scala.collection.mutable.StringBuilder;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.util.Failure;
import scala.util.Try;

public final class DataTypeConverterFactory$ {
   public static final DataTypeConverterFactory$ MODULE$;
   private final LocalDate io$pivotal$greenplum$spark$externaltable$DataTypeConverterFactory$$epochDate;
   private final ZoneId io$pivotal$greenplum$spark$externaltable$DataTypeConverterFactory$$localTimeZone;
   private final DateTimeFormatter dateFormatter;
   private final DateTimeFormatter timeFormatter;
   private final String optionalTzPattern;
   private final DateTimeFormatter io$pivotal$greenplum$spark$externaltable$DataTypeConverterFactory$$timeTzFormatter;
   private final DateTimeFormatterBuilder timestampFormatterBuilder;
   private final DateTimeFormatter io$pivotal$greenplum$spark$externaltable$DataTypeConverterFactory$$timestampFormatter;
   private final DateTimeFormatter io$pivotal$greenplum$spark$externaltable$DataTypeConverterFactory$$timestampTzFormatter;

   static {
      new DataTypeConverterFactory$();
   }

   public boolean io$pivotal$greenplum$spark$externaltable$DataTypeConverterFactory$$parseBoolean(String s) {
      if (s == null) {
         throw new IllegalArgumentException("For input string: \"null\"");
      } else {
         String var2 = s.toLowerCase();
         boolean var3;
         if ("t".equals(var2)) {
            var3 = true;
         } else {
            if (!"f".equals(var2)) {
               throw new IllegalArgumentException((new StringBuilder()).append("For input string: \"").append(s).append("\"").toString());
            }

            var3 = false;
         }

         return var3;
      }
   }

   public LocalDate io$pivotal$greenplum$spark$externaltable$DataTypeConverterFactory$$epochDate() {
      return this.io$pivotal$greenplum$spark$externaltable$DataTypeConverterFactory$$epochDate;
   }

   public ZoneId io$pivotal$greenplum$spark$externaltable$DataTypeConverterFactory$$localTimeZone() {
      return this.io$pivotal$greenplum$spark$externaltable$DataTypeConverterFactory$$localTimeZone;
   }

   private DateTimeFormatter dateFormatter() {
      return this.dateFormatter;
   }

   private DateTimeFormatter timeFormatter() {
      return this.timeFormatter;
   }

   private String optionalTzPattern() {
      return this.optionalTzPattern;
   }

   public DateTimeFormatter io$pivotal$greenplum$spark$externaltable$DataTypeConverterFactory$$timeTzFormatter() {
      return this.io$pivotal$greenplum$spark$externaltable$DataTypeConverterFactory$$timeTzFormatter;
   }

   private DateTimeFormatterBuilder timestampFormatterBuilder() {
      return this.timestampFormatterBuilder;
   }

   public DateTimeFormatter io$pivotal$greenplum$spark$externaltable$DataTypeConverterFactory$$timestampFormatter() {
      return this.io$pivotal$greenplum$spark$externaltable$DataTypeConverterFactory$$timestampFormatter;
   }

   public DateTimeFormatter io$pivotal$greenplum$spark$externaltable$DataTypeConverterFactory$$timestampTzFormatter() {
      return this.io$pivotal$greenplum$spark$externaltable$DataTypeConverterFactory$$timestampTzFormatter;
   }

   public Function2<String, SpecificInternalRow, BoxedUnit>[] create(StructType schema) {
      Function2[] conversionFunctions = (Function2[]).MODULE$.refArrayOps((Object[]).MODULE$.refArrayOps((Object[])schema.fields()).zipWithIndex(scala.Array..MODULE$.canBuildFrom(scala.reflect.ClassTag..MODULE$.apply(Tuple2.class)))).map(new Serializable() {
         public static final long serialVersionUID = 0L;

         public final Function2<String, SpecificInternalRow, BoxedUnit> apply(Tuple2<StructField, Object> x0$1) {
            if (x0$1 != null) {
               StructField field = (StructField)x0$1._1();
               int index = x0$1._2$mcI$sp();
               DataType var6 = field.dataType();
               Serializable var7;
               if (org.apache.spark.sql.types.BooleanType..MODULE$.equals(var6)) {
                  var7 = new Serializable(this, index) {
                     public static final long serialVersionUID = 0L;
                     private final int index$1;

                     public final void apply(String value, SpecificInternalRow row) {
                        row.setBoolean(this.index$1, DataTypeConverterFactory$.MODULE$.io$pivotal$greenplum$spark$externaltable$DataTypeConverterFactory$$parseBoolean(value));
                     }

                     public {
                        this.index$1 = index$1;
                     }
                  };
               } else if (var6 instanceof DecimalType) {
                  var7 = new Serializable(this, index) {
                     public static final long serialVersionUID = 0L;
                     private final int index$1;

                     public final void apply(String value, SpecificInternalRow row) {
                        row.update(this.index$1, org.apache.spark.sql.types.Decimal..MODULE$.apply(value));
                     }

                     public {
                        this.index$1 = index$1;
                     }
                  };
               } else if (org.apache.spark.sql.types.DoubleType..MODULE$.equals(var6)) {
                  var7 = new Serializable(this, index) {
                     public static final long serialVersionUID = 0L;
                     private final int index$1;

                     public final void apply(String value, SpecificInternalRow row) {
                        row.setDouble(this.index$1, (new StringOps(.MODULE$.augmentString(value))).toDouble());
                     }

                     public {
                        this.index$1 = index$1;
                     }
                  };
               } else if (org.apache.spark.sql.types.FloatType..MODULE$.equals(var6)) {
                  var7 = new Serializable(this, index) {
                     public static final long serialVersionUID = 0L;
                     private final int index$1;

                     public final void apply(String value, SpecificInternalRow row) {
                        row.setFloat(this.index$1, (new StringOps(.MODULE$.augmentString(value))).toFloat());
                     }

                     public {
                        this.index$1 = index$1;
                     }
                  };
               } else if (org.apache.spark.sql.types.IntegerType..MODULE$.equals(var6)) {
                  var7 = new Serializable(this, index) {
                     public static final long serialVersionUID = 0L;
                     private final int index$1;

                     public final void apply(String value, SpecificInternalRow row) {
                        row.setInt(this.index$1, (new StringOps(.MODULE$.augmentString(value))).toInt());
                     }

                     public {
                        this.index$1 = index$1;
                     }
                  };
               } else if (org.apache.spark.sql.types.LongType..MODULE$.equals(var6)) {
                  var7 = new Serializable(this, index) {
                     public static final long serialVersionUID = 0L;
                     private final int index$1;

                     public final void apply(String value, SpecificInternalRow row) {
                        row.setLong(this.index$1, (new StringOps(.MODULE$.augmentString(value))).toLong());
                     }

                     public {
                        this.index$1 = index$1;
                     }
                  };
               } else if (org.apache.spark.sql.types.ShortType..MODULE$.equals(var6)) {
                  var7 = new Serializable(this, index) {
                     public static final long serialVersionUID = 0L;
                     private final int index$1;

                     public final void apply(String value, SpecificInternalRow row) {
                        row.setShort(this.index$1, (new StringOps(.MODULE$.augmentString(value))).toShort());
                     }

                     public {
                        this.index$1 = index$1;
                     }
                  };
               } else if (org.apache.spark.sql.types.StringType..MODULE$.equals(var6)) {
                  var7 = new Serializable(this, index) {
                     public static final long serialVersionUID = 0L;
                     private final int index$1;

                     public final void apply(String value, SpecificInternalRow row) {
                        row.update(this.index$1, UTF8String.fromString(value));
                     }

                     public {
                        this.index$1 = index$1;
                     }
                  };
               } else if (org.apache.spark.sql.types.DateType..MODULE$.equals(var6)) {
                  var7 = new Serializable(this, index) {
                     public static final long serialVersionUID = 0L;
                     private final int index$1;

                     public final void apply(String value, SpecificInternalRow row) {
                        row.update(this.index$1, BoxesRunTime.boxToInteger(DataTypeConverterFactory$.MODULE$.io$pivotal$greenplum$spark$externaltable$DataTypeConverterFactory$$parseDate(value)));
                     }

                     public {
                        this.index$1 = index$1;
                     }
                  };
               } else {
                  if (!org.apache.spark.sql.types.TimestampType..MODULE$.equals(var6)) {
                     String columnTypeName = field.dataType().typeName();
                     throw new RuntimeException((new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{"Unsupported data type: ", ""})))).s(.MODULE$.genericWrapArray(new Object[]{columnTypeName})));
                  }

                  var7 = new Serializable(this, index) {
                     public static final long serialVersionUID = 0L;
                     private final int index$1;

                     public final void apply(String value, SpecificInternalRow row) {
                        row.update(this.index$1, BoxesRunTime.boxToLong(DataTypeConverterFactory$.MODULE$.io$pivotal$greenplum$spark$externaltable$DataTypeConverterFactory$$convertToSqlTimestamp(value)));
                     }

                     public {
                        this.index$1 = index$1;
                     }
                  };
               }

               return var7;
            } else {
               throw new MatchError(x0$1);
            }
         }
      }, scala.Array..MODULE$.canBuildFrom(scala.reflect.ClassTag..MODULE$.apply(Function2.class)));
      return conversionFunctions;
   }

   public long io$pivotal$greenplum$spark$externaltable$DataTypeConverterFactory$$convertToSqlTimestamp(String value) {
      return BoxesRunTime.unboxToLong(this.parseTimestamp(value).orElse(new Serializable(value) {
         public static final long serialVersionUID = 0L;
         private final String value$1;

         public final Try<Object> apply() {
            return DataTypeConverterFactory$.MODULE$.io$pivotal$greenplum$spark$externaltable$DataTypeConverterFactory$$parseTimestampTz(this.value$1);
         }

         public {
            this.value$1 = value$1;
         }
      }).orElse(new Serializable(value) {
         public static final long serialVersionUID = 0L;
         private final String value$1;

         public final Try<Object> apply() {
            return DataTypeConverterFactory$.MODULE$.io$pivotal$greenplum$spark$externaltable$DataTypeConverterFactory$$parseTime(this.value$1);
         }

         public {
            this.value$1 = value$1;
         }
      }).orElse(new Serializable(value) {
         public static final long serialVersionUID = 0L;
         private final String value$1;

         public final Try<Object> apply() {
            return DataTypeConverterFactory$.MODULE$.io$pivotal$greenplum$spark$externaltable$DataTypeConverterFactory$$parseTimeTz(this.value$1);
         }

         public {
            this.value$1 = value$1;
         }
      }).recoverWith(new Serializable(value) {
         public static final long serialVersionUID = 0L;
         private final String value$1;

         public final <A1 extends Throwable, B1> B1 applyOrElse(A1 x1, Function1<A1, B1> var2) {
            Failure var4 = new Failure(new DateTimeParseException((new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{"Text '", "' could not be parsed"})))).s(.MODULE$.genericWrapArray(new Object[]{this.value$1})), this.value$1, -1));
            return var4;
         }

         public final boolean isDefinedAt(Throwable x1) {
            boolean var3 = true;
            return var3;
         }

         public {
            this.value$1 = value$1;
         }
      }).get());
   }

   public int io$pivotal$greenplum$spark$externaltable$DataTypeConverterFactory$$parseDate(String value) {
      return (int)LocalDate.parse(value, this.dateFormatter()).getLong(ChronoField.EPOCH_DAY);
   }

   private Try<Object> parseTimestamp(String value) {
      return scala.util.Try..MODULE$.apply(new Serializable(value) {
         public static final long serialVersionUID = 0L;
         private final String value$2;

         public final long apply() {
            return this.apply$mcJ$sp();
         }

         public long apply$mcJ$sp() {
            ZonedDateTime dateTime = LocalDateTime.parse(this.value$2, DataTypeConverterFactory$.MODULE$.io$pivotal$greenplum$spark$externaltable$DataTypeConverterFactory$$timestampFormatter()).atZone(DataTypeConverterFactory$.MODULE$.io$pivotal$greenplum$spark$externaltable$DataTypeConverterFactory$$localTimeZone());
            return DataTypeConverterFactory$.MODULE$.io$pivotal$greenplum$spark$externaltable$DataTypeConverterFactory$$microsFor((Temporal)dateTime);
         }

         public {
            this.value$2 = value$2;
         }
      });
   }

   public Try<Object> io$pivotal$greenplum$spark$externaltable$DataTypeConverterFactory$$parseTimestampTz(String value) {
      return scala.util.Try..MODULE$.apply(new Serializable(value) {
         public static final long serialVersionUID = 0L;
         private final String value$3;

         public final long apply() {
            return this.apply$mcJ$sp();
         }

         public long apply$mcJ$sp() {
            OffsetDateTime dateTime = OffsetDateTime.parse(this.value$3, DataTypeConverterFactory$.MODULE$.io$pivotal$greenplum$spark$externaltable$DataTypeConverterFactory$$timestampTzFormatter());
            return DataTypeConverterFactory$.MODULE$.io$pivotal$greenplum$spark$externaltable$DataTypeConverterFactory$$microsFor((Temporal)dateTime);
         }

         public {
            this.value$3 = value$3;
         }
      });
   }

   public Try<Object> io$pivotal$greenplum$spark$externaltable$DataTypeConverterFactory$$parseTimeTz(String value) {
      return scala.util.Try..MODULE$.apply(new Serializable(value) {
         public static final long serialVersionUID = 0L;
         private final String value$5;

         public final long apply() {
            return this.apply$mcJ$sp();
         }

         public long apply$mcJ$sp() {
            OffsetTime dateTime = OffsetTime.parse(this.value$5, DataTypeConverterFactory$.MODULE$.io$pivotal$greenplum$spark$externaltable$DataTypeConverterFactory$$timeTzFormatter());
            return DataTypeConverterFactory$.MODULE$.io$pivotal$greenplum$spark$externaltable$DataTypeConverterFactory$$microsFor(dateTime);
         }

         public {
            this.value$5 = value$5;
         }
      });
   }

   public Try<Object> io$pivotal$greenplum$spark$externaltable$DataTypeConverterFactory$$parseTime(String value) {
      return scala.util.Try..MODULE$.apply(new Serializable(value) {
         public static final long serialVersionUID = 0L;
         private final String value$4;

         public final long apply() {
            return this.apply$mcJ$sp();
         }

         public long apply$mcJ$sp() {
            ZonedDateTime dateTime = LocalTime.parse(this.value$4).atDate(DataTypeConverterFactory$.MODULE$.io$pivotal$greenplum$spark$externaltable$DataTypeConverterFactory$$epochDate()).atZone(DataTypeConverterFactory$.MODULE$.io$pivotal$greenplum$spark$externaltable$DataTypeConverterFactory$$localTimeZone());
            return DataTypeConverterFactory$.MODULE$.io$pivotal$greenplum$spark$externaltable$DataTypeConverterFactory$$microsFor((Temporal)dateTime);
         }

         public {
            this.value$4 = value$4;
         }
      });
   }

   public long io$pivotal$greenplum$spark$externaltable$DataTypeConverterFactory$$microsFor(Temporal temporal) {
      return temporal.getLong(ChronoField.INSTANT_SECONDS) * 1000L * 1000L + temporal.getLong(ChronoField.MICRO_OF_SECOND);
   }

   public long io$pivotal$greenplum$spark$externaltable$DataTypeConverterFactory$$microsFor(OffsetTime dateTime) {
      long secondOfDay = dateTime.getLong(ChronoField.SECOND_OF_DAY);
      long offsetSeconds = dateTime.getLong(ChronoField.OFFSET_SECONDS);
      long offsetMicrosOfDay = (secondOfDay - offsetSeconds) * 1000L * 1000L;
      return offsetMicrosOfDay + dateTime.getLong(ChronoField.MICRO_OF_SECOND);
   }

   private DataTypeConverterFactory$() {
      MODULE$ = this;
      this.io$pivotal$greenplum$spark$externaltable$DataTypeConverterFactory$$epochDate = LocalDate.parse("1970-01-01");
      this.io$pivotal$greenplum$spark$externaltable$DataTypeConverterFactory$$localTimeZone = ZoneId.systemDefault();
      this.dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
      this.timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
      this.optionalTzPattern = "[xxx][x]";
      this.io$pivotal$greenplum$spark$externaltable$DataTypeConverterFactory$$timeTzFormatter = (new DateTimeFormatterBuilder()).append(this.timeFormatter()).appendFraction(ChronoField.MICRO_OF_SECOND, 0, 6, true).appendPattern(this.optionalTzPattern()).toFormatter();
      this.timestampFormatterBuilder = (new DateTimeFormatterBuilder()).append(this.dateFormatter()).appendLiteral(" ").append(this.timeFormatter()).appendFraction(ChronoField.MICRO_OF_SECOND, 0, 6, true);
      this.io$pivotal$greenplum$spark$externaltable$DataTypeConverterFactory$$timestampFormatter = this.timestampFormatterBuilder().toFormatter();
      this.io$pivotal$greenplum$spark$externaltable$DataTypeConverterFactory$$timestampTzFormatter = this.timestampFormatterBuilder().appendPattern(this.optionalTzPattern()).toFormatter();
   }
}
