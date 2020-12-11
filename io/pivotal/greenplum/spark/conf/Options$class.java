package io.pivotal.greenplum.spark.conf;

import scala.Function2;
import scala.MatchError;
import scala.Option;
import scala.Serializable;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.None.;
import scala.collection.immutable.StringOps;
import scala.runtime.BoxesRunTime;
import scala.util.Failure;
import scala.util.Success;
import scala.util.Try;

public abstract class Options$class {
   public static String option(Options $this, String optionName, WhatIfMissing whatIfMissing) {
      Tuple2 var3 = new Tuple2($this.option(optionName), whatIfMissing);
      if (var3 != null) {
         WhatIfMissing var5;
         boolean var8;
         label52: {
            Option var4 = (Option)var3._1();
            var5 = (WhatIfMissing)var3._2();
            if (var4 instanceof Some) {
               Some var6 = (Some)var4;
               String var7 = (String)var6.x();
               if (var7 == null) {
                  var8 = true;
                  break label52;
               }
            }

            if (.MODULE$.equals(var4)) {
               var8 = true;
            } else {
               var8 = false;
            }
         }

         if (var8 && ErrorIfMissing$.MODULE$.equals(var5)) {
            throw new IllegalArgumentException((new StringContext(scala.Predef..MODULE$.wrapRefArray((Object[])(new String[]{"requirement failed: Option '", "' is required."})))).s(scala.Predef..MODULE$.genericWrapArray(new Object[]{optionName})));
         }
      }

      String var9;
      if (var3 != null) {
         WhatIfMissing var11;
         boolean var14;
         label44: {
            Option var10 = (Option)var3._1();
            var11 = (WhatIfMissing)var3._2();
            if (var10 instanceof Some) {
               Some var12 = (Some)var10;
               String var13 = (String)var12.x();
               if (var13 == null) {
                  var14 = true;
                  break label44;
               }
            }

            if (.MODULE$.equals(var10)) {
               var14 = true;
            } else {
               var14 = false;
            }
         }

         if (var14 && var11 instanceof Default) {
            Default var15 = (Default)var11;
            String var16 = var15.value();
            var9 = var16;
            return var9;
         }
      }

      if (var3 != null) {
         Option var17 = (Option)var3._1();
         if (var17 instanceof Some) {
            Some var18 = (Some)var17;
            String value = (String)var18.x();
            var9 = value;
            return var9;
         }
      }

      throw new MatchError(var3);
   }

   public static Object option(Options $this, String optionName, WhatIfMissing whatIfMissing, Function2 convert) {
      String stringValue = $this.option(optionName, whatIfMissing);
      return ((Try)convert.apply(stringValue, optionName)).get();
   }

   public static Option option(Options $this, String optionName) {
      return $this.parameters().get(optionName);
   }

   public static Option option(Options $this, String optionName, Function2 convert) {
      return $this.option(optionName).map(new Serializable($this, optionName, convert) {
         public static final long serialVersionUID = 0L;
         private final String optionName$1;
         private final Function2 convert$1;

         public final T apply(String x$1) {
            return ((Try)this.convert$1.apply(x$1, this.optionName$1)).get();
         }

         public {
            this.optionName$1 = optionName$1;
            this.convert$1 = convert$1;
         }
      });
   }

   public static Function2 bool(Options $this) {
      return new Serializable($this) {
         public static final long serialVersionUID = 0L;

         public final Try<Object> apply(String value, String name) {
            Object var10000;
            try {
               var10000 = new Success(BoxesRunTime.boxToBoolean((new StringOps(scala.Predef..MODULE$.augmentString(value))).toBoolean()));
            } catch (IllegalArgumentException var3) {
               var10000 = new Failure(new IllegalArgumentException((new StringContext(scala.Predef..MODULE$.wrapRefArray((Object[])(new String[]{"requirement failed: Option '", "' should be either true or false."})))).s(scala.Predef..MODULE$.genericWrapArray(new Object[]{name}))));
            }

            return (Try)var10000;
         }
      };
   }

   public static Function2 naturalLong(Options $this) {
      return new Serializable($this) {
         public static final long serialVersionUID = 0L;

         public final Try<Object> apply(String value, String name) {
            Object var10000;
            try {
               var10000 = (new StringOps(scala.Predef..MODULE$.augmentString(value))).toLong() >= 0L ? new Success(BoxesRunTime.boxToLong((new StringOps(scala.Predef..MODULE$.augmentString(value))).toLong())) : new Failure(new IllegalArgumentException((new StringContext(scala.Predef..MODULE$.wrapRefArray((Object[])(new String[]{"requirement failed: Option '", "' should be zero or greater."})))).s(scala.Predef..MODULE$.genericWrapArray(new Object[]{name}))));
            } catch (NumberFormatException var3) {
               var10000 = new Failure(new IllegalArgumentException((new StringContext(scala.Predef..MODULE$.wrapRefArray((Object[])(new String[]{"requirement failed: Option '", "' should be a valid long."})))).s(scala.Predef..MODULE$.genericWrapArray(new Object[]{name}))));
            }

            return (Try)var10000;
         }
      };
   }

   public static Function2 int(Options $this) {
      return new Serializable($this) {
         public static final long serialVersionUID = 0L;

         public final Try<Object> apply(String value, String name) {
            Object var10000;
            try {
               var10000 = new Success(BoxesRunTime.boxToInteger((new StringOps(scala.Predef..MODULE$.augmentString(value))).toInt()));
            } catch (NumberFormatException var3) {
               var10000 = new Failure(new IllegalArgumentException((new StringContext(scala.Predef..MODULE$.wrapRefArray((Object[])(new String[]{"requirement failed: Option '", "' should be a valid integer."})))).s(scala.Predef..MODULE$.genericWrapArray(new Object[]{name}))));
            }

            return (Try)var10000;
         }
      };
   }

   public static Function2 positiveInt(Options $this) {
      return new Serializable($this) {
         public static final long serialVersionUID = 0L;
         // $FF: synthetic field
         private final Options $outer;

         public final Try<Object> apply(String value, String name) {
            return ((Try)this.$outer.int().apply(value, name)).flatMap(new Serializable(this, name) {
               public static final long serialVersionUID = 0L;
               private final String name$1;

               public final Try<Object> apply(int n) {
                  return (Try)(n > 0 ? new Success(BoxesRunTime.boxToInteger(n)) : new Failure(new IllegalArgumentException((new StringContext(scala.Predef..MODULE$.wrapRefArray((Object[])(new String[]{"requirement failed: Option '", "' should be a positive integer."})))).s(scala.Predef..MODULE$.genericWrapArray(new Object[]{this.name$1})))));
               }

               public {
                  this.name$1 = name$1;
               }
            });
         }

         public {
            if ($outer == null) {
               throw null;
            } else {
               this.$outer = $outer;
            }
         }
      };
   }

   public static Function2 nonNegativeInt(Options $this) {
      return new Serializable($this) {
         public static final long serialVersionUID = 0L;
         // $FF: synthetic field
         private final Options $outer;

         public final Try<Object> apply(String value, String name) {
            return ((Try)this.$outer.int().apply(value, name)).flatMap(new Serializable(this, name) {
               public static final long serialVersionUID = 0L;
               private final String name$2;

               public final Try<Object> apply(int n) {
                  return (Try)(n >= 0 ? new Success(BoxesRunTime.boxToInteger(n)) : new Failure(new IllegalArgumentException((new StringContext(scala.Predef..MODULE$.wrapRefArray((Object[])(new String[]{"requirement failed: Option '", "' should be zero or greater."})))).s(scala.Predef..MODULE$.genericWrapArray(new Object[]{this.name$2})))));
               }

               public {
                  this.name$2 = name$2;
               }
            });
         }

         public {
            if ($outer == null) {
               throw null;
            } else {
               this.$outer = $outer;
            }
         }
      };
   }

   public static void $init$(Options $this) {
   }
}
