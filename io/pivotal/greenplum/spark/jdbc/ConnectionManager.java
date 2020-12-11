package io.pivotal.greenplum.spark.jdbc;

import com.typesafe.scalalogging.LazyLogging;
import com.typesafe.scalalogging.LazyLogging$class;
import com.typesafe.scalalogging.Logger;
import io.pivotal.greenplum.spark.conf.ConnectionPoolOptions;
import io.pivotal.greenplum.spark.conf.GreenplumOptions;
import java.sql.Connection;
import java.util.HashMap;
import javax.sql.DataSource;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0001\u0005Ur!B\u0001\u0003\u0011\u0003i\u0011!E\"p]:,7\r^5p]6\u000bg.Y4fe*\u00111\u0001B\u0001\u0005U\u0012\u00147M\u0003\u0002\u0006\r\u0005)1\u000f]1sW*\u0011q\u0001C\u0001\nOJ,WM\u001c9mk6T!!\u0003\u0006\u0002\u000fALgo\u001c;bY*\t1\"\u0001\u0002j_\u000e\u0001\u0001C\u0001\b\u0010\u001b\u0005\u0011a!\u0002\t\u0003\u0011\u0003\t\"!E\"p]:,7\r^5p]6\u000bg.Y4feN\u0011qB\u0005\t\u0003'Yi\u0011\u0001\u0006\u0006\u0002+\u0005)1oY1mC&\u0011q\u0003\u0006\u0002\u0007\u0003:L(+\u001a4\t\u000beyA\u0011\u0001\u000e\u0002\rqJg.\u001b;?)\u0005i\u0001\u0002\u0003\u000f\u0010\u0011\u000b\u0007I\u0011A\u000f\u0002#\r|gN\\3di&|g.T1oC\u001e,'/F\u0001\u001f!\tqqD\u0002\u0003\u0011\u0005\u0001\u00013cA\u0010\u0013CA\u0011!%K\u0007\u0002G)\u0011A%J\u0001\rg\u000e\fG.\u00197pO\u001eLgn\u001a\u0006\u0003M\u001d\n\u0001\u0002^=qKN\fg-\u001a\u0006\u0002Q\u0005\u00191m\\7\n\u0005)\u001a#a\u0003'bufdunZ4j]\u001eD\u0001\u0002L\u0010\u0003\u0002\u0003\u0006I!L\u0001\taJ|g/\u001b3feB\u0011aBL\u0005\u0003_\t\u0011!\u0003R1uCN{WO]2f!J|g/\u001b3fe\")\u0011d\bC\u0001cQ\u0011aD\r\u0005\u0006YA\u0002\r!\f\u0005\bi}\u0011\r\u0011\"\u00036\u0003\u0015\u0001xn\u001c7t+\u00051\u0004\u0003B\u001c=}\u0005k\u0011\u0001\u000f\u0006\u0003si\nA!\u001e;jY*\t1(\u0001\u0003kCZ\f\u0017BA\u001f9\u0005\u001dA\u0015m\u001d5NCB\u0004\"AD \n\u0005\u0001\u0013!!D\"p]:,7\r^5p].+\u0017\u0010\u0005\u0002C\u000f6\t1I\u0003\u0002E\u000b\u0006\u00191/\u001d7\u000b\u0003\u0019\u000bQA[1wCbL!\u0001S\"\u0003\u0015\u0011\u000bG/Y*pkJ\u001cW\r\u0003\u0004K?\u0001\u0006IAN\u0001\u0007a>|Gn\u001d\u0011\t\u000b1{B\u0011A'\u0002\u001b\u001d,GoQ8o]\u0016\u001cG/[8o)\rq5k\u0017\t\u0003\u001fFk\u0011\u0001\u0015\u0006\u0003\tjJ!A\u0015)\u0003\u0015\r{gN\\3di&|g\u000eC\u0003U\u0017\u0002\u0007Q+\u0001\u0004eE>\u0003Ho\u001d\t\u0003-fk\u0011a\u0016\u0006\u00031\u0012\tAaY8oM&\u0011!l\u0016\u0002\u0011\u000fJ,WM\u001c9mk6|\u0005\u000f^5p]NDq\u0001X&\u0011\u0002\u0003\u0007Q,\u0001\u0006bkR|7i\\7nSR\u0004\"a\u00050\n\u0005}#\"a\u0002\"p_2,\u0017M\u001c\u0005\u0007C~!\tA\u00012\u0002'\u001d,G\u000fU8pY\u0016$7i\u001c8oK\u000e$\u0018n\u001c8\u0015\r9\u001bGN\\:v\u0011\u0015!\u0007\r1\u0001f\u0003\u001dQGMY2Ve2\u0004\"AZ5\u000f\u0005M9\u0017B\u00015\u0015\u0003\u0019\u0001&/\u001a3fM&\u0011!n\u001b\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005!$\u0002\"B7a\u0001\u0004)\u0017\u0001C;tKJt\u0015-\\3\t\u000b=\u0004\u0007\u0019\u00019\u0002\u0011A\f7o]<pe\u0012\u00042aE9f\u0013\t\u0011HC\u0001\u0004PaRLwN\u001c\u0005\u0006i\u0002\u0004\r!Z\u0001\u0007IJLg/\u001a:\t\u000fY\u0004\u0007\u0013!a\u0001o\u00069q\u000e\u001d;j_:\u001c\bC\u0001,y\u0013\tIxKA\u000bD_:tWm\u0019;j_:\u0004vn\u001c7PaRLwN\\:\t\rm|B\u0011\u0001\u0002}\u0003M9W\r\u001e)p_2,G\rR1uCN{WO]2f)!\tUP`@\u0002\u0002\u0005\r\u0001\"\u00023{\u0001\u0004)\u0007\"B7{\u0001\u0004)\u0007\"B8{\u0001\u0004\u0001\b\"\u0002;{\u0001\u0004)\u0007\"\u0002<{\u0001\u00049\b\"CA\u0004?E\u0005I\u0011AA\u0005\u0003]9W\r^\"p]:,7\r^5p]\u0012\"WMZ1vYR$#'\u0006\u0002\u0002\f)\u001aQ,!\u0004,\u0005\u0005=\u0001\u0003BA\t\u00037i!!a\u0005\u000b\t\u0005U\u0011qC\u0001\nk:\u001c\u0007.Z2lK\u0012T1!!\u0007\u0015\u0003)\tgN\\8uCRLwN\\\u0005\u0005\u0003;\t\u0019BA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016D\u0011\"!\t #\u0003%\t!a\t\u0002;\u001d,G\u000fU8pY\u0016$7i\u001c8oK\u000e$\u0018n\u001c8%I\u00164\u0017-\u001e7uIU*\"!!\n+\u0007]\fi\u0001C\u0005\u0002*=A\t\u0011)Q\u0005=\u0005\u00112m\u001c8oK\u000e$\u0018n\u001c8NC:\fw-\u001a:!\u0011\u0019au\u0002\"\u0001\u0002.Q)a*a\f\u00022!1A+a\u000bA\u0002UC\u0001\u0002XA\u0016!\u0003\u0005\r!\u0018\u0005\n\u0003\u000fy\u0011\u0013!C\u0001\u0003\u0013\u0001"
)
public class ConnectionManager implements LazyLogging {
   private final DataSourceProvider provider;
   private final HashMap<ConnectionKey, DataSource> pools;
   private final transient Logger logger;
   private transient volatile boolean bitmap$trans$0;

   public static ConnectionManager connectionManager() {
      return ConnectionManager$.MODULE$.connectionManager();
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

   private HashMap<ConnectionKey, DataSource> pools() {
      return this.pools;
   }

   public Connection getConnection(GreenplumOptions dbOpts, boolean autoCommit) {
      Connection connection = this.getPooledConnection(dbOpts.url(), dbOpts.user(), dbOpts.password(), dbOpts.driver(), dbOpts.connectionPoolOptions());
      connection.setAutoCommit(autoCommit);
      return connection;
   }

   public boolean getConnection$default$2() {
      return true;
   }

   public Connection getPooledConnection(String jdbcUrl, String userName, Option<String> password, String driver, ConnectionPoolOptions options) {
      return this.getPooledDataSource(jdbcUrl, userName, password, driver, options).getConnection();
   }

   public ConnectionPoolOptions getPooledConnection$default$5() {
      return new ConnectionPoolOptions();
   }

   public DataSource getPooledDataSource(String jdbcUrl, String userName, Option<String> password, String driver, ConnectionPoolOptions options) {
      ConnectionKey var10;
      if (password instanceof Some) {
         Some var8 = (Some)password;
         String pass = (String)var8.x();
         var10 = ConnectionKey$.MODULE$.valueOf(jdbcUrl, userName, pass);
      } else {
         if (!.MODULE$.equals(password)) {
            throw new MatchError(password);
         }

         var10 = ConnectionKey$.MODULE$.valueOf(jdbcUrl, userName);
      }

      ConnectionKey connectionKey = var10;
      BoxedUnit var10000;
      if (this.pools().containsKey(var10)) {
         var10000 = BoxedUnit.UNIT;
      } else {
         synchronized(this.pools()) {
            if (this.pools().containsKey(connectionKey)) {
               var10000 = BoxedUnit.UNIT;
            } else {
               DataSource dataSource = this.provider.createDataSource(connectionKey, password, driver, options);
               this.pools().put(connectionKey, dataSource);
            }
         }
      }

      return (DataSource)this.pools().get(var10);
   }

   public ConnectionManager(DataSourceProvider provider) {
      this.provider = provider;
      LazyLogging$class.$init$(this);
      this.pools = new HashMap();
   }
}
