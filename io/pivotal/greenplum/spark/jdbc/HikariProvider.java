package io.pivotal.greenplum.spark.jdbc;

import com.typesafe.scalalogging.Logger;
import io.pivotal.greenplum.spark.conf.ConnectionPoolOptions;
import javax.sql.DataSource;
import scala.Option;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u00019;Q!\u0001\u0002\t\u00025\ta\u0002S5lCJL\u0007K]8wS\u0012,'O\u0003\u0002\u0004\t\u0005!!\u000e\u001a2d\u0015\t)a!A\u0003ta\u0006\u00148N\u0003\u0002\b\u0011\u0005IqM]3f]BdW/\u001c\u0006\u0003\u0013)\tq\u0001]5w_R\fGNC\u0001\f\u0003\tIwn\u0001\u0001\u0011\u00059yQ\"\u0001\u0002\u0007\u000bA\u0011\u0001\u0012A\t\u0003\u001d!K7.\u0019:j!J|g/\u001b3feN!qB\u0005\r\u001c!\t\u0019b#D\u0001\u0015\u0015\u0005)\u0012!B:dC2\f\u0017BA\f\u0015\u0005\u0019\te.\u001f*fMB\u0011a\"G\u0005\u00035\t\u0011!\u0003R1uCN{WO]2f!J|g/\u001b3feB\u0011AdI\u0007\u0002;)\u0011adH\u0001\rg\u000e\fG.\u00197pO\u001eLgn\u001a\u0006\u0003A\u0005\n\u0001\u0002^=qKN\fg-\u001a\u0006\u0002E\u0005\u00191m\\7\n\u0005\u0011j\"a\u0003'bufdunZ4j]\u001eDQAJ\b\u0005\u0002\u001d\na\u0001P5oSRtD#A\u0007\t\u000b%zA\u0011\t\u0016\u0002!\r\u0014X-\u0019;f\t\u0006$\u0018mU8ve\u000e,G#B\u00164q\u00113\u0005C\u0001\u00172\u001b\u0005i#B\u0001\u00180\u0003\r\u0019\u0018\u000f\u001c\u0006\u0002a\u0005)!.\u0019<bq&\u0011!'\f\u0002\u000b\t\u0006$\u0018mU8ve\u000e,\u0007\"\u0002\u001b)\u0001\u0004)\u0014aA6fsB\u0011aBN\u0005\u0003o\t\u0011QbQ8o]\u0016\u001cG/[8o\u0017\u0016L\b\"B\u001d)\u0001\u0004Q\u0014\u0001\u00039bgN<xN\u001d3\u0011\u0007MYT(\u0003\u0002=)\t1q\n\u001d;j_:\u0004\"AP!\u000f\u0005My\u0014B\u0001!\u0015\u0003\u0019\u0001&/\u001a3fM&\u0011!i\u0011\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005\u0001#\u0002\"B#)\u0001\u0004i\u0014A\u00023sSZ,'\u000fC\u0003HQ\u0001\u0007\u0001*A\u0004paRLwN\\:\u0011\u0005%cU\"\u0001&\u000b\u0005-#\u0011\u0001B2p]\u001aL!!\u0014&\u0003+\r{gN\\3di&|g\u000eU8pY>\u0003H/[8og\u0002"
)
public final class HikariProvider {
   public static Logger logger() {
      return HikariProvider$.MODULE$.logger();
   }

   public static DataSource createDataSource(ConnectionKey var0, Option<String> var1, String var2, ConnectionPoolOptions var3) {
      return HikariProvider$.MODULE$.createDataSource(var0, var1, var2, var3);
   }
}
