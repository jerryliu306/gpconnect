package io.pivotal.greenplum.spark.jdbc;

import com.typesafe.scalalogging.Logger;
import io.pivotal.greenplum.spark.GpfdistLocation;
import io.pivotal.greenplum.spark.externaltable.GreenplumQualifiedName;
import java.sql.Connection;
import java.sql.ResultSetMetaData;
import org.apache.spark.sql.types.StructType;
import scala.collection.Seq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0001\t\u0005q!B\u0001\u0003\u0011\u0003i\u0011\u0001\u0002&eE\u000eT!a\u0001\u0003\u0002\t)$'m\u0019\u0006\u0003\u000b\u0019\tQa\u001d9be.T!a\u0002\u0005\u0002\u0013\u001d\u0014X-\u001a8qYVl'BA\u0005\u000b\u0003\u001d\u0001\u0018N^8uC2T\u0011aC\u0001\u0003S>\u001c\u0001\u0001\u0005\u0002\u000f\u001f5\t!AB\u0003\u0011\u0005!\u0005\u0011C\u0001\u0003KI\n\u001c7cA\b\u00131A\u00111CF\u0007\u0002))\tQ#A\u0003tG\u0006d\u0017-\u0003\u0002\u0018)\t1\u0011I\\=SK\u001a\u0004\"!\u0007\u0011\u000e\u0003iQ!a\u0007\u000f\u0002\u0019M\u001c\u0017\r\\1m_\u001e<\u0017N\\4\u000b\u0005uq\u0012\u0001\u0003;za\u0016\u001c\u0018MZ3\u000b\u0003}\t1aY8n\u0013\t\t#DA\u0006MCjLHj\\4hS:<\u0007\"B\u0012\u0010\t\u0003!\u0013A\u0002\u001fj]&$h\bF\u0001\u000e\u0011\u00151s\u0002\"\u0001(\u0003M\u0019w\u000e]=UC\ndW\rV8FqR,'O\\1m)\u0019A3&N\u001fG\u001fB\u00111#K\u0005\u0003UQ\u0011A!\u00168ji\")A&\na\u0001[\u0005!1m\u001c8o!\tq3'D\u00010\u0015\t\u0001\u0014'A\u0002tc2T\u0011AM\u0001\u0005U\u00064\u0018-\u0003\u00025_\tQ1i\u001c8oK\u000e$\u0018n\u001c8\t\u000bY*\u0003\u0019A\u001c\u0002\u0011M\u00148\rV1cY\u0016\u0004\"\u0001O\u001e\u000e\u0003eR!A\u000f\u0003\u0002\u001b\u0015DH/\u001a:oC2$\u0018M\u00197f\u0013\ta\u0014H\u0001\fHe\u0016,g\u000e\u001d7v[F+\u0018\r\\5gS\u0016$g*Y7f\u0011\u0015qT\u00051\u0001@\u0003!)\u0007\u0010\u001e+bE2,\u0007C\u0001!D\u001d\tA\u0014)\u0003\u0002Cs\u00051rI]3f]BdW/\\)vC2Lg-[3e\u001d\u0006lW-\u0003\u0002E\u000b\nIA+Z7q)\u0006\u0014G.\u001a\u0006\u0003\u0005fBQaR\u0013A\u0002!\u000b\u0011\u0002\u001d:fI&\u001c\u0017\r^3\u0011\u0005%ceBA\nK\u0013\tYE#\u0001\u0004Qe\u0016$WMZ\u0005\u0003\u001b:\u0013aa\u0015;sS:<'BA&\u0015\u0011\u0015\u0001V\u00051\u0001R\u0003\u001d\u0019w\u000e\\;n]N\u00042A\u0015.I\u001d\t\u0019\u0006L\u0004\u0002U/6\tQK\u0003\u0002W\u0019\u00051AH]8pizJ\u0011!F\u0005\u00033R\tq\u0001]1dW\u0006<W-\u0003\u0002\\9\n\u00191+Z9\u000b\u0005e#\u0002\"\u00020\u0010\t\u0003y\u0016aE3yi\u0016\u0014h.\u00197UC\ndW-\u0012=jgR\u001cHc\u00011dIB\u00111#Y\u0005\u0003ER\u0011qAQ8pY\u0016\fg\u000eC\u0003-;\u0002\u0007Q\u0006C\u0003f;\u0002\u0007q(A\u0003uC\ndW\rC\u0003h\u001f\u0011\u0005\u0001.\u0001\nhKR\u001cu\u000e\\;n]NlU\r^1eCR\fG\u0003B)jU:DQ\u0001\f4A\u00025BQ!\u001a4A\u0002-\u0004\"\u0001\u00117\n\u00055,%!\u0002+bE2,\u0007\"B8g\u0001\u0004\t\u0016aC2pYVlgNT1nKNDQ!]\b\u0005\u0002I\f!e\u0019:fCR,w\t\u001d4eSN$xK]5uC\ndW-\u0012=uKJt\u0017\r\u001c+bE2,GC\u0002\u0015tiV4H\u0010C\u0003-a\u0002\u0007Q\u0006C\u00037a\u0002\u00071\u000eC\u0003?a\u0002\u0007q\bC\u0003xa\u0002\u0007\u00010A\bha\u001a$\u0017n\u001d;M_\u000e\fG/[8o!\tI(0D\u0001\u0005\u0013\tYHAA\bHa\u001a$\u0017n\u001d;M_\u000e\fG/[8o\u0011\u0015\u0001\u0006\u000f1\u0001R\u0011\u0015qx\u0002\"\u0001\u0000\u0003I\u0011X\r\u001e:jKZ,7+Z4nK:$\u0018\nZ:\u0015\t\u0005\u0005\u0011Q\u0002\t\u0006'\u0005\r\u0011qA\u0005\u0004\u0003\u000b!\"!B!se\u0006L\bcA\n\u0002\n%\u0019\u00111\u0002\u000b\u0003\u0007%sG\u000fC\u0003-{\u0002\u0007Q\u0006\u0003\u0005\u0002\u0012=!\t\u0001BA\n\u0003=\u0011X\r\u001e:jKZ,'+Z:vYR\u001cX\u0003BA\u000b\u0003?!\u0002\"a\u0006\u0002B\u0005\r\u0013q\t\u000b\u0005\u00033\t\t\u0004\u0005\u0003S5\u0006m\u0001\u0003BA\u000f\u0003?a\u0001\u0001\u0002\u0005\u0002\"\u0005=!\u0019AA\u0012\u0005\u0005!\u0016\u0003BA\u0013\u0003W\u00012aEA\u0014\u0013\r\tI\u0003\u0006\u0002\b\u001d>$\b.\u001b8h!\r\u0019\u0012QF\u0005\u0004\u0003_!\"aA!os\"Q\u00111GA\b\u0003\u0003\u0005\u001d!!\u000e\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$\u0013\u0007\u0005\u0004\u00028\u0005u\u00121D\u0007\u0003\u0003sQ1!a\u000f\u0015\u0003\u001d\u0011XM\u001a7fGRLA!a\u0010\u0002:\tA1\t\\1tgR\u000bw\r\u0003\u0004-\u0003\u001f\u0001\r!\f\u0005\b\u0003\u000b\ny\u00011\u0001I\u0003!\u0019\u0018\u000f\\)vKJL\b\u0002CA%\u0003\u001f\u0001\r!a\u0013\u0002\r\u001d,G\u000f^3s!\u001d\u0019\u0012QJA)\u00037I1!a\u0014\u0015\u0005%1UO\\2uS>t\u0017\u0007E\u0002/\u0003'J1!!\u00160\u0005%\u0011Vm];miN+G\u000fC\u0004\u0002Z=!\t!a\u0017\u0002/\r|W\u000e];uK\u000e{G.^7o-\u0006dW/\u001a*b]\u001e,G\u0003CA/\u0003G\n)'a\u001a\u0011\u00079\ty&C\u0002\u0002b\t\u0011\u0001cQ8mk6tg+\u00197vKJ\u000bgnZ3\t\r1\n9\u00061\u0001.\u0011\u0019)\u0017q\u000ba\u0001W\"9\u0011\u0011NA,\u0001\u0004A\u0015AC2pYVlgNT1nK\"9\u0011QN\b\u0005\n\u0005=\u0014!F9vKJL8i\u001c7v[:4\u0016\r\\;f%\u0006tw-\u001a\u000b\u0007\u0003c\n9(!\u001f\u0011\rI\u000b\u0019\bSA/\u0013\r\t)\b\u0018\u0002\u0007\u000b&$\b.\u001a:\t\r1\nY\u00071\u0001.\u0011\u001d\t)%a\u001bA\u0002!Cq!! \u0010\t\u0003\ty(\u0001\u0007sKN|GN^3UC\ndW\r\u0006\u0005\u0002\u0002\u0006e\u00151TAP!\u0011\t\u0019)!&\u000e\u0005\u0005\u0015%\u0002BAD\u0003\u0013\u000bQ\u0001^=qKNT1\u0001MAF\u0015\r)\u0011Q\u0012\u0006\u0005\u0003\u001f\u000b\t*\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0003\u0003'\u000b1a\u001c:h\u0013\u0011\t9*!\"\u0003\u0015M#(/^2u)f\u0004X\r\u0003\u0004-\u0003w\u0002\r!\f\u0005\b\u0003;\u000bY\b1\u0001I\u0003\r)(\u000f\u001c\u0005\u0007K\u0006m\u0004\u0019A\u001c\t\u000f\u0005\rv\u0002\"\u0001\u0002&\u0006qq-\u001a;D_2,XN\u001c(b[\u0016\u001cHcA)\u0002(\"A\u0011\u0011VAQ\u0001\u0004\tY+\u0001\u0003nKR\f\u0007c\u0001\u0018\u0002.&\u0019\u0011qV\u0018\u0003#I+7/\u001e7u'\u0016$X*\u001a;b\t\u0006$\u0018\rC\u0004\u00024>!\t!!.\u0002\u0013\u001d,GoU2iK6\fG\u0003BAA\u0003oC\u0001\"!+\u00022\u0002\u0007\u00111\u0016\u0005\b\u0003w{A\u0011BA_\u0003=9W\r^\"bi\u0006d\u0017p\u001d;UsB,GCCA`\u0003\u000b\fI-!4\u0002RB!\u00111QAa\u0013\u0011\t\u0019-!\"\u0003\u0011\u0011\u000bG/\u0019+za\u0016D\u0001\"a2\u0002:\u0002\u0007\u0011qA\u0001\bgFdG+\u001f9f\u0011!\tY-!/A\u0002\u0005\u001d\u0011!\u00039sK\u000eL7/[8o\u0011!\ty-!/A\u0002\u0005\u001d\u0011!B:dC2,\u0007bBAj\u0003s\u0003\r\u0001Y\u0001\u0007g&<g.\u001a3\t\u000f\u0005]w\u0002\"\u0003\u0002Z\u0006Y1m\u001c7mK\u000e$hI]8n+\u0011\tY.!;\u0015\t\u0005u\u0017Q\u001f\u000b\u0005\u0003?\f\t\u0010\u0006\u0003\u0002b\u0006-\b#\u0002*\u0002d\u0006\u001d\u0018bAAs9\n1a+Z2u_J\u0004B!!\b\u0002j\u0012A\u0011\u0011EAk\u0005\u0004\t\u0019\u0003\u0003\u0006\u0002n\u0006U\u0017\u0011!a\u0002\u0003_\f!\"\u001a<jI\u0016t7-\u001a\u00133!\u0019\t9$!\u0010\u0002h\"A\u0011\u0011JAk\u0001\u0004\t\u0019\u0010E\u0004\u0014\u0003\u001b\n\t&a:\t\u0011\u0005]\u0018Q\u001ba\u0001\u0003#\n\u0011B]3tk2$8+\u001a;\t\u000f\u0005mx\u0002\"\u0001\u0002~\u0006Yr-\u001a;ESN$(/\u001b2vi\u0016$GK]1og\u0006\u001cG/[8o\u0013\u0012$2\u0001SA\u0000\u0011\u0019a\u0013\u0011 a\u0001[\u0001"
)
public final class Jdbc {
   public static Logger logger() {
      return Jdbc$.MODULE$.logger();
   }

   public static String getDistributedTransactionId(Connection var0) {
      return Jdbc$.MODULE$.getDistributedTransactionId(var0);
   }

   public static StructType getSchema(ResultSetMetaData var0) {
      return Jdbc$.MODULE$.getSchema(var0);
   }

   public static Seq<String> getColumnNames(ResultSetMetaData var0) {
      return Jdbc$.MODULE$.getColumnNames(var0);
   }

   public static StructType resolveTable(Connection var0, String var1, GreenplumQualifiedName var2) {
      return Jdbc$.MODULE$.resolveTable(var0, var1, var2);
   }

   public static ColumnValueRange computeColumnValueRange(Connection var0, GreenplumQualifiedName.Table var1, String var2) {
      return Jdbc$.MODULE$.computeColumnValueRange(var0, var1, var2);
   }

   public static int[] retrieveSegmentIds(Connection var0) {
      return Jdbc$.MODULE$.retrieveSegmentIds(var0);
   }

   public static void createGpfdistWritableExternalTable(Connection var0, GreenplumQualifiedName.Table var1, GreenplumQualifiedName.TempTable var2, GpfdistLocation var3, Seq<String> var4) {
      Jdbc$.MODULE$.createGpfdistWritableExternalTable(var0, var1, var2, var3, var4);
   }

   public static Seq<String> getColumnsMetadata(Connection var0, GreenplumQualifiedName.Table var1, Seq<String> var2) {
      return Jdbc$.MODULE$.getColumnsMetadata(var0, var1, var2);
   }

   public static boolean externalTableExists(Connection var0, GreenplumQualifiedName.TempTable var1) {
      return Jdbc$.MODULE$.externalTableExists(var0, var1);
   }

   public static void copyTableToExternal(Connection var0, GreenplumQualifiedName var1, GreenplumQualifiedName.TempTable var2, String var3, Seq<String> var4) {
      Jdbc$.MODULE$.copyTableToExternal(var0, var1, var2, var3, var4);
   }
}
