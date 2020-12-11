package io.pivotal.greenplum.spark;

import io.pivotal.greenplum.spark.conf.ConnectorOptions;
import java.net.InetAddress;
import org.apache.commons.lang.StringUtils;
import scala.Function1;
import scala.MatchError;
import scala.Product;
import scala.Serializable;
import scala.StringContext;
import scala.Predef.;
import scala.collection.immutable.List;
import scala.collection.immutable.StringOps;
import scala.collection.mutable.StringBuilder;
import scala.runtime.BoxesRunTime;
import scala.util.Failure;
import scala.util.Success;
import scala.util.Try;

public final class ConnectorUtils$ {
   public static final ConnectorUtils$ MODULE$;

   static {
      new ConnectorUtils$();
   }

   public List<Object> parsePortString(String portStr) {
      return .MODULE$.intArrayOps((int[]).MODULE$.intArrayOps((int[]).MODULE$.intArrayOps((int[]).MODULE$.refArrayOps((Object[])(new StringOps(.MODULE$.augmentString(portStr))).split(',')).flatMap(new Serializable(portStr) {
         public static final long serialVersionUID = 0L;
         public final String portStr$1;

         public final List<Object> apply(String s) {
            return (List)ConnectorUtils$.MODULE$.io$pivotal$greenplum$spark$ConnectorUtils$$parsePortOrRange(s).recoverWith(new Serializable(this) {
               public static final long serialVersionUID = 0L;
               // $FF: synthetic field
               private final <undefinedtype> $outer;

               public final <A1 extends Throwable, B1> B1 applyOrElse(A1 x1, Function1<A1, B1> var2) {
                  Object var5;
                  if (x1 instanceof Exception) {
                     Exception var4 = (Exception)x1;
                     var5 = new Failure(new IllegalArgumentException((new StringBuilder()).append((new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{"failed to parse port string '", "': "})))).s(.MODULE$.genericWrapArray(new Object[]{this.$outer.portStr$1}))).append(var4.getMessage()).toString(), var4));
                  } else {
                     var5 = var2.apply(x1);
                  }

                  return var5;
               }

               public final boolean isDefinedAt(Throwable x1) {
                  boolean var3;
                  if (x1 instanceof Exception) {
                     var3 = true;
                  } else {
                     var3 = false;
                  }

                  return var3;
               }

               public {
                  if ($outer == null) {
                     throw null;
                  } else {
                     this.$outer = $outer;
                  }
               }
            }).get();
         }

         public {
            this.portStr$1 = portStr$1;
         }
      }, scala.Array..MODULE$.canBuildFrom(scala.reflect.ClassTag..MODULE$.Int()))).distinct()).sorted(scala.math.Ordering.Int..MODULE$)).toList();
   }

   public Try<List<Object>> io$pivotal$greenplum$spark$ConnectorUtils$$parsePortOrRange(String str) {
      Try[] var2 = (Try[]).MODULE$.refArrayOps((Object[]).MODULE$.refArrayOps((Object[])(new StringOps(.MODULE$.augmentString(str))).split('-')).map(new Serializable() {
         public static final long serialVersionUID = 0L;

         public final Try<Object> apply(String s) {
            return scala.util.Try..MODULE$.apply(new Serializable(this, s) {
               public static final long serialVersionUID = 0L;
               private final String s$1;

               public final int apply() {
                  return this.apply$mcI$sp();
               }

               public int apply$mcI$sp() {
                  return Integer.parseInt(this.s$1.trim());
               }

               public {
                  this.s$1 = s$1;
               }
            });
         }
      }, scala.Array..MODULE$.canBuildFrom(scala.reflect.ClassTag..MODULE$.apply(Try.class)))).map(new Serializable() {
         public static final long serialVersionUID = 0L;

         public final Product apply(Try<Object> x0$1) {
            boolean var2 = false;
            Success var3 = null;
            Object var6;
            if (x0$1 instanceof Success) {
               var2 = true;
               var3 = (Success)x0$1;
               int px = BoxesRunTime.unboxToInt(var3.value());
               if (px < 0 || px > 65535) {
                  var6 = new Failure(new IllegalArgumentException((new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{"", " is not a valid port number. Specify a value between 0 and 65535"})))).s(.MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(px)}))));
                  return (Try)var6;
               }
            }

            if (var2) {
               int p = BoxesRunTime.unboxToInt(var3.value());
               var6 = new Success(BoxesRunTime.boxToInteger(p));
            } else {
               if (!(x0$1 instanceof Failure)) {
                  throw new MatchError(x0$1);
               }

               Failure var8 = (Failure)x0$1;
               Throwable e = var8.exception();
               var6 = new Failure(e);
            }

            return (Try)var6;
         }
      }, scala.Array..MODULE$.canBuildFrom(scala.reflect.ClassTag..MODULE$.apply(Try.class)));
      Object var3;
      if (var2.length == 1) {
         var3 = scala.util.Try..MODULE$.apply(new Serializable(var2) {
            public static final long serialVersionUID = 0L;
            private final Try[] x1$1;

            public final List<Object> apply() {
               return scala.collection.immutable.List..MODULE$.apply(.MODULE$.wrapIntArray(new int[]{BoxesRunTime.unboxToInt(this.x1$1[0].get())}));
            }

            public {
               this.x1$1 = x1$1;
            }
         });
      } else if (var2.length == 2) {
         var3 = scala.util.Try..MODULE$.apply(new Serializable(var2) {
            public static final long serialVersionUID = 0L;
            private final Try[] x1$1;

            public final List<Object> apply() {
               return (List)scala.collection.immutable.List..MODULE$.range(this.x1$1[0].get(), BoxesRunTime.boxToInteger(BoxesRunTime.unboxToInt(this.x1$1[1].get()) + 1), scala.math.Numeric.IntIsIntegral..MODULE$);
            }

            public {
               this.x1$1 = x1$1;
            }
         });
      } else {
         var3 = new Failure(new IllegalArgumentException("invalid port range"));
      }

      return (Try)var3;
   }

   public String getServerAddress(ConnectorOptions connectorOptions, NetworkWrapper networkWrapper) {
      String var10000;
      if (connectorOptions.useHostname()) {
         var10000 = networkWrapper.getLocalHostname();
      } else if (StringUtils.isNotBlank(connectorOptions.address())) {
         var10000 = networkWrapper.getInetAddressByName(connectorOptions.address()).getHostAddress();
      } else {
         InetAddress inetAddress = StringUtils.isNotBlank(connectorOptions.networkInterface()) ? networkWrapper.getInet4AddressNetworkInterfaceByName(connectorOptions.networkInterface()) : null;
         var10000 = inetAddress == null ? networkWrapper.getLocalHostAddress() : inetAddress.getHostAddress();
      }

      return var10000;
   }

   public NetworkWrapper getServerAddress$default$2() {
      return new NetworkWrapper();
   }

   private ConnectorUtils$() {
      MODULE$ = this;
   }
}
