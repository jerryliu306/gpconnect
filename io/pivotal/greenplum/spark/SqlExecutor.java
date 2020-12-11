package io.pivotal.greenplum.spark;

import com.typesafe.scalalogging.LazyLogging;
import com.typesafe.scalalogging.LazyLogging$class;
import com.typesafe.scalalogging.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import resource.ExtractableManagedResource;
import resource.ManagedResource;
import resource.Resource$;
import resource.package$;
import scala.Function1;
import scala.MatchError;
import scala.Serializable;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.mutable.StringBuilder;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassManifestFactory.;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.util.Try;

@ScalaSignature(
   bytes = "\u0006\u0001\u0005ua\u0001B\u0001\u0003\u0001-\u00111bU9m\u000bb,7-\u001e;pe*\u00111\u0001B\u0001\u0006gB\f'o\u001b\u0006\u0003\u000b\u0019\t\u0011b\u001a:fK:\u0004H.^7\u000b\u0005\u001dA\u0011a\u00029jm>$\u0018\r\u001c\u0006\u0002\u0013\u0005\u0011\u0011n\\\u0002\u0001'\r\u0001AB\u0005\t\u0003\u001bAi\u0011A\u0004\u0006\u0002\u001f\u0005)1oY1mC&\u0011\u0011C\u0004\u0002\u0007\u0003:L(+\u001a4\u0011\u0005MQR\"\u0001\u000b\u000b\u0005U1\u0012\u0001D:dC2\fGn\\4hS:<'BA\f\u0019\u0003!!\u0018\u0010]3tC\u001a,'\"A\r\u0002\u0007\r|W.\u0003\u0002\u001c)\tYA*\u0019>z\u0019><w-\u001b8h\u0011!i\u0002A!A!\u0002\u0013q\u0012AC2p]:,7\r^5p]B\u0011q\u0004J\u0007\u0002A)\u0011\u0011EI\u0001\u0004gFd'\"A\u0012\u0002\t)\fg/Y\u0005\u0003K\u0001\u0012!bQ8o]\u0016\u001cG/[8o\u0011\u00159\u0003\u0001\"\u0001)\u0003\u0019a\u0014N\\5u}Q\u0011\u0011f\u000b\t\u0003U\u0001i\u0011A\u0001\u0005\u0006;\u0019\u0002\rA\b\u0005\u0006[\u0001!\tAL\u0001\bKb,7-\u001e;f)\ty\u0003\bE\u00021gUj\u0011!\r\u0006\u0003e9\tA!\u001e;jY&\u0011A'\r\u0002\u0004)JL\bCA\u00077\u0013\t9dBA\u0004C_>dW-\u00198\t\u000b\u0005b\u0003\u0019A\u001d\u0011\u0005ijdBA\u0007<\u0013\tad\"\u0001\u0004Qe\u0016$WMZ\u0005\u0003}}\u0012aa\u0015;sS:<'B\u0001\u001f\u000f\u0011\u0015\t\u0005\u0001\"\u0001C\u00031)\u00070Z2vi\u0016\fV/\u001a:z+\t\u0019u\tF\u0002E!F\u00032\u0001M\u001aF!\t1u\t\u0004\u0001\u0005\u000b!\u0003%\u0019A%\u0003\u0007=+H/\u0005\u0002K\u001bB\u0011QbS\u0005\u0003\u0019:\u0011qAT8uQ&tw\r\u0005\u0002\u000e\u001d&\u0011qJ\u0004\u0002\u0004\u0003:L\b\"B\u0011A\u0001\u0004I\u0004\"\u0002*A\u0001\u0004\u0019\u0016a\u0003;sC:\u001chm\u001c:nKJ\u0004B!\u0004+W\u000b&\u0011QK\u0004\u0002\n\rVt7\r^5p]F\u0002\"aH,\n\u0005a\u0003#!\u0003*fgVdGoU3u\u0011\u0015Q\u0006\u0001\"\u0001\\\u00035)\u00070Z2vi\u0016,\u0006\u000fZ1uKR\u0011A\f\u0019\t\u0004aMj\u0006CA\u0007_\u0013\tyfBA\u0002J]RDQ!I-A\u0002eBQ!\u0011\u0001\u0005\u0002\t,\"a\u00194\u0015\t\u0011<\u0007.\u001c\t\u0004aM*\u0007C\u0001$g\t\u0015A\u0015M1\u0001J\u0011\u0015\t\u0013\r1\u0001:\u0011\u0015I\u0017\r1\u0001k\u0003\u0011\t'oZ:\u0011\u00075YW*\u0003\u0002m\u001d\t)\u0011I\u001d:bs\")!+\u0019a\u0001]B!Q\u0002\u0016,f\u0011\u0015\u0001\b\u0001\"\u0003r\u00039!(/\u001f$s_6l\u0015M\\1hK\u0012,\"A];\u0015\u0005M<\bc\u0001\u00194iB\u0011a)\u001e\u0003\u0006m>\u0014\r!\u0013\u0002\u0002)\")\u0001p\u001ca\u0001s\u0006yQ.\u00198bO\u0016$'+Z:pkJ\u001cW\rE\u0002{{Rl\u0011a\u001f\u0006\u0002y\u0006A!/Z:pkJ\u001cW-\u0003\u0002\u007fw\nyQ*\u00198bO\u0016$'+Z:pkJ\u001cW\rC\u0004\u0002\u0002\u0001!I!a\u0001\u0002\u001b9|w\u000e]\"m_N,\u0017M\u00197f+\t\t)A\u0005\u0004\u0002\b\u0005-\u0011q\u0003\u0004\u0007\u0003\u0013y\b!!\u0002\u0003\u0019q\u0012XMZ5oK6,g\u000e\u001e \u0011\t\u00055\u00111C\u0007\u0003\u0003\u001fQ1!!\u0005#\u0003\u0011a\u0017M\\4\n\t\u0005U\u0011q\u0002\u0002\u0007\u001f\nTWm\u0019;\u0011\t\u00055\u0011\u0011D\u0005\u0005\u00037\tyAA\u0007BkR|7\t\\8tK\u0006\u0014G.\u001a"
)
public class SqlExecutor implements LazyLogging {
   public final Connection io$pivotal$greenplum$spark$SqlExecutor$$connection;
   private final transient Logger logger;
   private transient volatile boolean bitmap$trans$0;

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

   public Try<Object> execute(String sql) {
      BoxedUnit var10000;
      if (this.logger().underlying().isDebugEnabled()) {
         this.logger().underlying().debug("executing query: {}", sql);
         var10000 = BoxedUnit.UNIT;
      } else {
         var10000 = BoxedUnit.UNIT;
      }

      return this.tryFromManaged(package$.MODULE$.managed(new Serializable(this) {
         public static final long serialVersionUID = 0L;
         // $FF: synthetic field
         private final SqlExecutor $outer;

         public final Statement apply() {
            return this.$outer.io$pivotal$greenplum$spark$SqlExecutor$$connection.createStatement();
         }

         public {
            if ($outer == null) {
               throw null;
            } else {
               this.$outer = $outer;
            }
         }
      }, Resource$.MODULE$.statementResource(), .MODULE$.classType(Statement.class)).map(new Serializable(this, sql) {
         public static final long serialVersionUID = 0L;
         private final String sql$1;

         public final boolean apply(Statement statement) {
            return statement.execute(this.sql$1);
         }

         public {
            this.sql$1 = sql$1;
         }
      }));
   }

   public <Out> Try<Out> executeQuery(String sql, Function1<ResultSet, Out> transformer) {
      BoxedUnit var10000;
      if (this.logger().underlying().isDebugEnabled()) {
         this.logger().underlying().debug("executing query: {}", sql);
         var10000 = BoxedUnit.UNIT;
      } else {
         var10000 = BoxedUnit.UNIT;
      }

      return this.tryFromManaged(package$.MODULE$.managed(new Serializable(this) {
         public static final long serialVersionUID = 0L;
         // $FF: synthetic field
         private final SqlExecutor $outer;

         public final Statement apply() {
            return this.$outer.io$pivotal$greenplum$spark$SqlExecutor$$connection.createStatement();
         }

         public {
            if ($outer == null) {
               throw null;
            } else {
               this.$outer = $outer;
            }
         }
      }, Resource$.MODULE$.statementResource(), .MODULE$.classType(Statement.class)).flatMap(new Serializable(this, sql, transformer) {
         public static final long serialVersionUID = 0L;
         public final String sql$2;
         public final Function1 transformer$1;

         public final ExtractableManagedResource<Out> apply(Statement statement) {
            return package$.MODULE$.managed(new Serializable(this, statement) {
               public static final long serialVersionUID = 0L;
               // $FF: synthetic field
               private final <undefinedtype> $outer;
               private final Statement statement$1;

               public final ResultSet apply() {
                  return this.statement$1.executeQuery(this.$outer.sql$2);
               }

               public {
                  if ($outer == null) {
                     throw null;
                  } else {
                     this.$outer = $outer;
                     this.statement$1 = statement$1;
                  }
               }
            }, Resource$.MODULE$.resultSetResource(), .MODULE$.classType(ResultSet.class)).map(new Serializable(this) {
               public static final long serialVersionUID = 0L;
               // $FF: synthetic field
               private final <undefinedtype> $outer;

               public final Out apply(ResultSet resultSet) {
                  return this.$outer.transformer$1.apply(resultSet);
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
            this.sql$2 = sql$2;
            this.transformer$1 = transformer$1;
         }
      }));
   }

   public Try<Object> executeUpdate(String sql) {
      BoxedUnit var10000;
      if (this.logger().underlying().isDebugEnabled()) {
         this.logger().underlying().debug("executing query: {}", sql);
         var10000 = BoxedUnit.UNIT;
      } else {
         var10000 = BoxedUnit.UNIT;
      }

      return this.tryFromManaged(package$.MODULE$.managed(new Serializable(this) {
         public static final long serialVersionUID = 0L;
         // $FF: synthetic field
         private final SqlExecutor $outer;

         public final Statement apply() {
            return this.$outer.io$pivotal$greenplum$spark$SqlExecutor$$connection.createStatement();
         }

         public {
            if ($outer == null) {
               throw null;
            } else {
               this.$outer = $outer;
            }
         }
      }, Resource$.MODULE$.statementResource(), .MODULE$.classType(Statement.class)).map(new Serializable(this, sql) {
         public static final long serialVersionUID = 0L;
         private final String sql$3;

         public final int apply(Statement statement) {
            return statement.executeUpdate(this.sql$3);
         }

         public {
            this.sql$3 = sql$3;
         }
      }));
   }

   public <Out> Try<Out> executeQuery(String sql, Object[] args, Function1<ResultSet, Out> transformer) {
      BoxedUnit var10000;
      if (this.logger().underlying().isDebugEnabled()) {
         this.logger().underlying().debug("executing query: {}", sql);
         var10000 = BoxedUnit.UNIT;
      } else {
         var10000 = BoxedUnit.UNIT;
      }

      return scala.Predef..MODULE$.genericArrayOps(args).isEmpty() ? this.executeQuery(sql, transformer) : this.tryFromManaged(package$.MODULE$.managed(new Serializable(this, sql) {
         public static final long serialVersionUID = 0L;
         // $FF: synthetic field
         private final SqlExecutor $outer;
         private final String sql$4;

         public final PreparedStatement apply() {
            return this.$outer.io$pivotal$greenplum$spark$SqlExecutor$$connection.prepareStatement(this.sql$4);
         }

         public {
            if ($outer == null) {
               throw null;
            } else {
               this.$outer = $outer;
               this.sql$4 = sql$4;
            }
         }
      }, Resource$.MODULE$.statementResource(), .MODULE$.classType(PreparedStatement.class)).flatMap(new Serializable(this, sql, args, transformer) {
         public static final long serialVersionUID = 0L;
         // $FF: synthetic field
         private final SqlExecutor $outer;
         public final String sql$4;
         public final Object[] args$1;
         public final Function1 transformer$2;

         public final ManagedResource<Out> apply(PreparedStatement preparedStatement) {
            return package$.MODULE$.managed(new Serializable(this, preparedStatement) {
               public static final long serialVersionUID = 0L;
               // $FF: synthetic field
               private final <undefinedtype> $outer;
               public final PreparedStatement preparedStatement$1;

               public final AutoCloseable apply() {
                  scala.Predef..MODULE$.refArrayOps((Object[])scala.Predef..MODULE$.genericArrayOps(this.$outer.args$1).zipWithIndex(scala.Array..MODULE$.canBuildFrom(scala.reflect.ClassTag..MODULE$.apply(Tuple2.class)))).foreach(new Serializable(this) {
                     public static final long serialVersionUID = 0L;
                     // $FF: synthetic field
                     private final <undefinedtype> $outer;

                     public final void apply(Tuple2<Object, Object> x0$1) {
                        BoxedUnit var7;
                        if (x0$1 != null) {
                           Object argxx = x0$1._1();
                           int ix = x0$1._2$mcI$sp();
                           if (argxx instanceof Integer) {
                              int var5 = BoxesRunTime.unboxToInt(argxx);
                              this.$outer.preparedStatement$1.setInt(ix + 1, var5);
                              var7 = BoxedUnit.UNIT;
                              return;
                           }
                        }

                        if (x0$1 != null) {
                           Object argx = x0$1._1();
                           int i = x0$1._2$mcI$sp();
                           if (argx instanceof String) {
                              String var10 = (String)argx;
                              this.$outer.preparedStatement$1.setString(i + 1, var10);
                              var7 = BoxedUnit.UNIT;
                              return;
                           }
                        }

                        if (x0$1 != null) {
                           Object arg = x0$1._1();
                           throw new IllegalArgumentException((new StringBuilder()).append((new StringContext(scala.Predef..MODULE$.wrapRefArray((Object[])(new String[]{"Unsupported type ", " for prepared "})))).s(scala.Predef..MODULE$.genericWrapArray(new Object[]{arg.getClass().getSimpleName()}))).append((new StringContext(scala.Predef..MODULE$.wrapRefArray((Object[])(new String[]{"statement argument ", ". SQL was: ", ""})))).s(scala.Predef..MODULE$.genericWrapArray(new Object[]{arg, this.$outer.io$pivotal$greenplum$spark$SqlExecutor$$anonfun$$anonfun$$$outer().sql$4}))).toString());
                        } else {
                           throw new MatchError(x0$1);
                        }
                     }

                     public {
                        if ($outer == null) {
                           throw null;
                        } else {
                           this.$outer = $outer;
                        }
                     }
                  });
                  return this.$outer.io$pivotal$greenplum$spark$SqlExecutor$$anonfun$$$outer().io$pivotal$greenplum$spark$SqlExecutor$$noopCloseable();
               }

               // $FF: synthetic method
               public <undefinedtype> io$pivotal$greenplum$spark$SqlExecutor$$anonfun$$anonfun$$$outer() {
                  return this.$outer;
               }

               public {
                  if ($outer == null) {
                     throw null;
                  } else {
                     this.$outer = $outer;
                     this.preparedStatement$1 = preparedStatement$1;
                  }
               }
            }, Resource$.MODULE$.reflectiveCloseableResource(), .MODULE$.classType(AutoCloseable.class)).flatMap(new Serializable(this, preparedStatement) {
               public static final long serialVersionUID = 0L;
               // $FF: synthetic field
               private final <undefinedtype> $outer;
               public final PreparedStatement preparedStatement$1;

               public final ExtractableManagedResource<Out> apply(Object _) {
                  return package$.MODULE$.managed(new Serializable(this) {
                     public static final long serialVersionUID = 0L;
                     // $FF: synthetic field
                     private final <undefinedtype> $outer;

                     public final ResultSet apply() {
                        return this.$outer.preparedStatement$1.executeQuery();
                     }

                     public {
                        if ($outer == null) {
                           throw null;
                        } else {
                           this.$outer = $outer;
                        }
                     }
                  }, Resource$.MODULE$.resultSetResource(), .MODULE$.classType(ResultSet.class)).map(new Serializable(this) {
                     public static final long serialVersionUID = 0L;
                     // $FF: synthetic field
                     private final <undefinedtype> $outer;

                     public final Out apply(ResultSet resultSet) {
                        return this.$outer.io$pivotal$greenplum$spark$SqlExecutor$$anonfun$$anonfun$$$outer().transformer$2.apply(resultSet);
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

               // $FF: synthetic method
               public <undefinedtype> io$pivotal$greenplum$spark$SqlExecutor$$anonfun$$anonfun$$$outer() {
                  return this.$outer;
               }

               public {
                  if ($outer == null) {
                     throw null;
                  } else {
                     this.$outer = $outer;
                     this.preparedStatement$1 = preparedStatement$1;
                  }
               }
            });
         }

         // $FF: synthetic method
         public SqlExecutor io$pivotal$greenplum$spark$SqlExecutor$$anonfun$$$outer() {
            return this.$outer;
         }

         public {
            if ($outer == null) {
               throw null;
            } else {
               this.$outer = $outer;
               this.sql$4 = sql$4;
               this.args$1 = args$1;
               this.transformer$2 = transformer$2;
            }
         }
      }));
   }

   private <T> Try<T> tryFromManaged(ManagedResource<T> managedResource) {
      return managedResource.map(new Serializable(this) {
         public static final long serialVersionUID = 0L;

         public final T apply(T x) {
            return x;
         }
      }).tried();
   }

   public AutoCloseable io$pivotal$greenplum$spark$SqlExecutor$$noopCloseable() {
      return new AutoCloseable(this) {
         public void close() {
         }
      };
   }

   public SqlExecutor(Connection connection) {
      this.io$pivotal$greenplum$spark$SqlExecutor$$connection = connection;
      LazyLogging$class.$init$(this);
   }
}
