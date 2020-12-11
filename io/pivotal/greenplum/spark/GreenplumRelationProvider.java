package io.pivotal.greenplum.spark;

import com.typesafe.scalalogging.LazyLogging;
import com.typesafe.scalalogging.LazyLogging$class;
import com.typesafe.scalalogging.Logger;
import io.pivotal.greenplum.spark.conf.GreenplumOptions;
import io.pivotal.greenplum.spark.conf.GreenplumOptions$;
import io.pivotal.greenplum.spark.externaltable.GreenplumQualifiedName;
import io.pivotal.greenplum.spark.externaltable.GreenplumQualifiedName$;
import io.pivotal.greenplum.spark.externaltable.GreenplumTableManager;
import io.pivotal.greenplum.spark.externaltable.GreenplumTableManager$;
import io.pivotal.greenplum.spark.externaltable.PartitionWriter;
import io.pivotal.greenplum.spark.externaltable.RowTransformer$;
import io.pivotal.greenplum.spark.jdbc.ColumnValueRange;
import io.pivotal.greenplum.spark.jdbc.ConnectionManager$;
import io.pivotal.greenplum.spark.jdbc.Jdbc$;
import java.sql.Connection;
import java.util.Properties;
import org.apache.spark.SparkContext;
import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.DataFrameReader;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.SaveMode;
import org.apache.spark.sql.sources.BaseRelation;
import org.apache.spark.sql.sources.CreatableRelationProvider;
import org.apache.spark.sql.sources.DataSourceRegister;
import org.apache.spark.sql.sources.RelationProvider;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.Option;
import scala.Serializable;
import scala.Some;
import scala.StringContext;
import scala.collection.Iterator;
import scala.collection.Seq;
import scala.collection.immutable.Map;
import scala.collection.mutable.StringBuilder;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ObjectRef;
import scala.runtime.VolatileByteRef;
import scala.util.Either;
import scala.util.Left;
import scala.util.Right;

@ScalaSignature(
   bytes = "\u0006\u0001\tmh\u0001B\u0001\u0003\u0001-\u0011\u0011d\u0012:fK:\u0004H.^7SK2\fG/[8o!J|g/\u001b3fe*\u00111\u0001B\u0001\u0006gB\f'o\u001b\u0006\u0003\u000b\u0019\t\u0011b\u001a:fK:\u0004H.^7\u000b\u0005\u001dA\u0011a\u00029jm>$\u0018\r\u001c\u0006\u0002\u0013\u0005\u0011\u0011n\\\u0002\u0001'\u0019\u0001ABE\u0010#KA\u0011Q\u0002E\u0007\u0002\u001d)\tq\"A\u0003tG\u0006d\u0017-\u0003\u0002\u0012\u001d\t1\u0011I\\=SK\u001a\u0004\"aE\u000f\u000e\u0003QQ!!\u0006\f\u0002\u000fM|WO]2fg*\u0011q\u0003G\u0001\u0004gFd'BA\u0002\u001a\u0015\tQ2$\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u00029\u0005\u0019qN]4\n\u0005y!\"\u0001\u0005*fY\u0006$\u0018n\u001c8Qe>4\u0018\u000eZ3s!\t\u0019\u0002%\u0003\u0002\")\t\u0011B)\u0019;b'>,(oY3SK\u001eL7\u000f^3s!\t\u00192%\u0003\u0002%)\tI2I]3bi\u0006\u0014G.\u001a*fY\u0006$\u0018n\u001c8Qe>4\u0018\u000eZ3s!\t1S&D\u0001(\u0015\tA\u0013&\u0001\u0007tG\u0006d\u0017\r\\8hO&twM\u0003\u0002+W\u0005AA/\u001f9fg\u00064WMC\u0001-\u0003\r\u0019w.\\\u0005\u0003]\u001d\u00121\u0002T1{s2{wmZ5oO\")\u0001\u0007\u0001C\u0001c\u00051A(\u001b8jiz\"\u0012A\r\t\u0003g\u0001i\u0011A\u0001\u0005\u0006k\u0001!\tEN\u0001\ng\"|'\u000f\u001e(b[\u0016$\u0012a\u000e\t\u0003qmr!!D\u001d\n\u0005ir\u0011A\u0002)sK\u0012,g-\u0003\u0002={\t11\u000b\u001e:j]\u001eT!A\u000f\b\t\u000b}\u0002A\u0011\u0001!\u0002\u001d\r\u0014X-\u0019;f%\u0016d\u0017\r^5p]R\u0019\u0011\t\u0012&\u0011\u0005M\u0011\u0015BA\"\u0015\u00051\u0011\u0015m]3SK2\fG/[8o\u0011\u0015)e\b1\u0001G\u0003)\u0019\u0018\u000f\\\"p]R,\u0007\u0010\u001e\t\u0003\u000f\"k\u0011AF\u0005\u0003\u0013Z\u0011!bU)M\u0007>tG/\u001a=u\u0011\u0015Ye\b1\u0001M\u0003)\u0001\u0018M]1nKR,'o\u001d\t\u0005q5;t'\u0003\u0002O{\t\u0019Q*\u00199\t\rA\u0003A\u0011\u0001\u0002R\u0003E\u0019w.\u001c9vi\u0016\u0004\u0016M\u001d;ji&|gn\u001d\u000b\b%b\u000bW.^<\u0000!\ri1+V\u0005\u0003):\u0011Q!\u0011:sCf\u0004\"a\r,\n\u0005]\u0013!AE$sK\u0016t\u0007\u000f\\;n!\u0006\u0014H/\u001b;j_:DQ!W(A\u0002i\u000bAaY8o]B\u00111lX\u0007\u00029*\u0011q#\u0018\u0006\u0002=\u0006!!.\u0019<b\u0013\t\u0001GL\u0001\u0006D_:tWm\u0019;j_:DQAY(A\u0002\r\fQ\u0001^1cY\u0016\u0004\"\u0001\u001a6\u000f\u0005\u0015DW\"\u00014\u000b\u0005\u001d\u0014\u0011!D3yi\u0016\u0014h.\u00197uC\ndW-\u0003\u0002jM\u00061rI]3f]BdW/\\)vC2Lg-[3e\u001d\u0006lW-\u0003\u0002lY\n)A+\u00192mK*\u0011\u0011N\u001a\u0005\u0006]>\u0003\ra\\\u0001\u0007g\u000eDW-\\1\u0011\u0005A\u001cX\"A9\u000b\u0005I4\u0012!\u0002;za\u0016\u001c\u0018B\u0001;r\u0005)\u0019FO];diRK\b/\u001a\u0005\u0006m>\u0003\raN\u0001\u0014a\u0006\u0014H/\u001b;j_:\u001cu\u000e\\;n]:\u000bW.\u001a\u0005\u0006q>\u0003\r!_\u0001\u0010a\u0006\u0014H/\u001b;j_:\u001c8i\\;oiB\u0019QB\u001f?\n\u0005mt!AB(qi&|g\u000e\u0005\u0002\u000e{&\u0011aP\u0004\u0002\u0004\u0013:$\bbBA\u0001\u001f\u0002\u0007\u00111A\u0001\rOB\u001cVmZ7f]RLEm\u001d\t\u0004\u001bMc\bBB \u0001\t\u0003\n9\u0001F\u0005B\u0003\u0013\tY!!\u0006\u0002\u0018!1Q)!\u0002A\u0002\u0019C\u0001\"!\u0004\u0002\u0006\u0001\u0007\u0011qB\u0001\u0005[>$W\rE\u0002H\u0003#I1!a\u0005\u0017\u0005!\u0019\u0016M^3N_\u0012,\u0007BB&\u0002\u0006\u0001\u0007A\n\u0003\u0005\u0002\u001a\u0005\u0015\u0001\u0019AA\u000e\u0003\u0011!\u0017\r^1\u0011\t\u0005u\u0011\u0011\b\b\u0005\u0003?\t)D\u0004\u0003\u0002\"\u0005Mb\u0002BA\u0012\u0003cqA!!\n\u000209!\u0011qEA\u0017\u001b\t\tICC\u0002\u0002,)\ta\u0001\u0010:p_Rt\u0014\"\u0001\u000f\n\u0005iY\u0012BA\u0002\u001a\u0013\t9\u0002$C\u0002\u00028Y\tq\u0001]1dW\u0006<W-\u0003\u0003\u0002<\u0005u\"!\u0003#bi\u00064%/Y7f\u0015\r\t9D\u0006\u0005\b\u0003\u0003\u0002A\u0011CA\"\u0003=9W\r\u001e+bE2,W*\u00198bO\u0016\u0014H\u0003BA#\u0003\u0017\u00022!ZA$\u0013\r\tIE\u001a\u0002\u0016\u000fJ,WM\u001c9mk6$\u0016M\u00197f\u001b\u0006t\u0017mZ3s\u0011!\ti%a\u0010A\u0002\u0005=\u0013\u0001C3yK\u000e,Ho\u001c:\u0011\u0007M\n\t&C\u0002\u0002T\t\u00111bU9m\u000bb,7-\u001e;pe\"9\u0011q\u000b\u0001\u0005\u0012\u0005e\u0013AE4fiB\u000b'\u000f^5uS>twK]5uKJ$\u0002\"a\u0017\u0002z\u0005%\u0015Q\u0013\t\t\u001b\u0005uC0!\u0019\u0002x%\u0019\u0011q\f\b\u0003\u0013\u0019+hn\u0019;j_:\u0014\u0004CBA2\u0003W\n\tH\u0004\u0003\u0002f\u0005%d\u0002BA\u0014\u0003OJ\u0011aD\u0005\u0004\u0003oq\u0011\u0002BA7\u0003_\u0012\u0001\"\u0013;fe\u0006$xN\u001d\u0006\u0004\u0003oq\u0001cA$\u0002t%\u0019\u0011Q\u000f\f\u0003\u0007I{w\u000fE\u0003\u0002d\u0005-D\u0010\u0003\u0005\u0002|\u0005U\u0003\u0019AA?\u0003A9'/Z3oa2,Xn\u00149uS>t7\u000f\u0005\u0003\u0002\u0000\u0005\u0015UBAAA\u0015\r\t\u0019IA\u0001\u0005G>tg-\u0003\u0003\u0002\b\u0006\u0005%\u0001E$sK\u0016t\u0007\u000f\\;n\u001fB$\u0018n\u001c8t\u0011!\tY)!\u0016A\u0002\u00055\u0015\u0001D:qCJ\\7i\u001c8uKb$\b\u0003BAH\u0003#k\u0011\u0001G\u0005\u0004\u0003'C\"\u0001D*qCJ\\7i\u001c8uKb$\b\u0002CAL\u0003+\u0002\r!!'\u0002\u001dI|w\u000f\u0016:b]N4wN]7feB9Q\"a'\u0002r\u0005E\u0014bAAO\u001d\tIa)\u001e8di&|g.\r\u0005\b\u0003C\u0003A\u0011CAR\u0003=9W\r^*qCJ\\7i\u001c8uKb$H\u0003BAG\u0003KCa!RAP\u0001\u00041\u0005bBAU\u0001\u0011E\u00111V\u0001\u000fO\u0016$8+\u001d7Fq\u0016\u001cW\u000f^8s)\u0011\ty%!,\t\u0011\u0005m\u0014q\u0015a\u0001\u0003{Bq!!-\u0001\t\u0013\t\u0019,A\u0007tCZ,G)\u0019;b\rJ\fW.\u001a\u000b\fy\u0006U\u0016\u0011XAb\u0003\u000b\f9\r\u0003\u0005\u00028\u0006=\u0006\u0019AA#\u00031!\u0018M\u00197f\u001b\u0006t\u0017mZ3s\u0011!\tY,a,A\u0002\u0005u\u0016!\u00033fgR$\u0016M\u00197f!\r)\u0017qX\u0005\u0004\u0003\u00034'AF$sK\u0016t\u0007\u000f\\;n#V\fG.\u001b4jK\u0012t\u0015-\\3\t\u0011\u0005e\u0011q\u0016a\u0001\u00037A\u0001\"a\u001f\u00020\u0002\u0007\u0011Q\u0010\u0005\t\u0003\u0017\u000by\u000b1\u0001\u0002\u000e\u001e9\u00111\u001a\u0002\t\u0002\u00055\u0017!G$sK\u0016t\u0007\u000f\\;n%\u0016d\u0017\r^5p]B\u0013xN^5eKJ\u00042aMAh\r\u0019\t!\u0001#\u0001\u0002RN\u0019\u0011q\u001a\u0007\t\u000fA\ny\r\"\u0001\u0002VR\u0011\u0011Q\u001a\u0005\t\u00033\fy\r\"\u0001\u0002\\\u0006)\u0011\r\u001d9msV!\u0011Q\\Au)\u0011\ty.!:\u0011\u0007\u001d\u000b\t/C\u0002\u0002dZ\u0011q\u0002R1uC\u001a\u0013\u0018-\\3SK\u0006$WM\u001d\u0005\t\u0003O\f9\u000eq\u0001\u0002`\u0006\tA\u000f\u0002\u0005\u0002l\u0006]'\u0019AAw\u0005\u0005\t\u0015\u0003BAx\u0003k\u00042!DAy\u0013\r\t\u0019P\u0004\u0002\b\u001d>$\b.\u001b8h!\ri\u0011q_\u0005\u0004\u0003st!aA!os\u001a9\u0011Q`Ah\u0007\u0005}(\u0001G$sK\u0016t\u0007\u000f\\;n\t\u0006$\u0018M\u0012:b[\u0016\u0014V-\u00193feN!\u00111 B\u0001!\ri!1A\u0005\u0004\u0005\u000bq!AB!osZ\u000bG\u000eC\u0006\u0003\n\u0005m(Q1A\u0005\u0002\t-\u0011A\u0002:fC\u0012,'/\u0006\u0002\u0002`\"Y!qBA~\u0005\u0003\u0005\u000b\u0011BAp\u0003\u001d\u0011X-\u00193fe\u0002Bq\u0001MA~\t\u0003\u0011\u0019\u0002\u0006\u0003\u0003\u0016\te\u0001\u0003\u0002B\f\u0003wl!!a4\t\u0011\t%!\u0011\u0003a\u0001\u0003?Dq!BA~\t\u0003\u0011i\u0002\u0006\u0005\u0002\u001c\t}!1\u0005B\u0014\u0011\u001d\u0011\tCa\u0007A\u0002]\nqA\u001b3cGV\u0013H\u000eC\u0004\u0003&\tm\u0001\u0019A\u001c\u0002\u0013Q\f'\r\\3OC6,\u0007\u0002\u0003B\u0015\u00057\u0001\rAa\u000b\u0002\u0015A\u0014x\u000e]3si&,7\u000f\u0005\u0003\u0003.\tMRB\u0001B\u0018\u0015\r\u0011\t$X\u0001\u0005kRLG.\u0003\u0003\u00036\t=\"A\u0003)s_B,'\u000f^5fg\"9Q!a?\u0005\u0002\teB\u0003EA\u000e\u0005w\u0011iDa\u0010\u0003B\t\u0015#\u0011\nB'\u0011\u001d\u0011\tCa\u000eA\u0002]BqA!\n\u00038\u0001\u0007q\u0007\u0003\u0004o\u0005o\u0001\ra\u000e\u0005\b\u0005\u0007\u00129\u00041\u00018\u0003!)8/\u001a:oC6,\u0007b\u0002B$\u0005o\u0001\raN\u0001\ta\u0006\u001c8o^8sI\"9!1\nB\u001c\u0001\u00049\u0014a\u00049beRLG/[8o\u0007>dW/\u001c8\t\u0015\t%\"q\u0007I\u0001\u0002\u0004\u0011Y\u0003\u0003\u0006\u0003R\u0005m\u0018\u0013!C\u0001\u0005'\n1c\u001a:fK:\u0004H.^7%I\u00164\u0017-\u001e7uI]*\"A!\u0016+\t\t-\"qK\u0016\u0003\u00053\u0002BAa\u0017\u0003f5\u0011!Q\f\u0006\u0005\u0005?\u0012\t'A\u0005v]\u000eDWmY6fI*\u0019!1\r\b\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0003\u0003h\tu#!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\"Q!1NA~\u0003\u0003%\tE!\u001c\u0002\u0011!\f7\u000f[\"pI\u0016$\u0012\u0001 \u0005\u000b\u0005c\nY0!A\u0005B\tM\u0014AB3rk\u0006d7\u000f\u0006\u0003\u0003v\tm\u0004cA\u0007\u0003x%\u0019!\u0011\u0010\b\u0003\u000f\t{w\u000e\\3b]\"Q!Q\u0010B8\u0003\u0003\u0005\r!!>\u0002\u0007a$\u0013\u0007\u0003\u0006\u0003\u0002\u0006=\u0017\u0011!C\u0002\u0005\u0007\u000b\u0001d\u0012:fK:\u0004H.^7ECR\fgI]1nKJ+\u0017\rZ3s)\u0011\u0011)B!\"\t\u0011\t%!q\u0010a\u0001\u0003?D\u0001B!#\u0002P\u0012\u0005!1R\u0001\u0019G\",7m\u001b)beRLG/[8o\u0007>dW/\u001c8UsB,G\u0003\u0002BG\u0005?\u0003\u0002\"a\u0019\u0003\u0010\nM%\u0011T\u0005\u0005\u0005#\u000byG\u0001\u0004FSRDWM\u001d\t\u0004\u001b\tU\u0015b\u0001BL\u001d\t!QK\\5u!\u0011\t\u0019Ga'\n\t\tu\u0015q\u000e\u0002\u0019\u00132dWmZ1m\u0003J<W/\\3oi\u0016C8-\u001a9uS>t\u0007\u0002\u0003BQ\u0005\u000f\u0003\rAa)\u0002\r\r|G.^7o!\r\u0001(QU\u0005\u0004\u0005O\u000b(aC*ueV\u001cGOR5fY\u0012<!B!!\u0002P\u0006\u0005\t\u0012\u0001BV!\u0011\u00119B!,\u0007\u0015\u0005u\u0018qZA\u0001\u0012\u0003\u0011ykE\u0002\u0003.2Aq\u0001\rBW\t\u0003\u0011\u0019\f\u0006\u0002\u0003,\"A!q\u0017BW\t\u000b\u0011I,\u0001\u000bhe\u0016,g\u000e\u001d7v[\u0012*\u0007\u0010^3og&|g\u000e\r\u000b\u0005\u0005w\u0013\u0019\r\u0006\u0005\u0002\u001c\tu&q\u0018Ba\u0011\u001d\u0011\tC!.A\u0002]BqA!\n\u00036\u0002\u0007q\u0007\u0003\u0005\u0003*\tU\u0006\u0019\u0001B\u0016\u0011!\u0011)M!.A\u0002\tU\u0011!\u0002\u0013uQ&\u001c\b\u0002\u0003Be\u0005[#)Aa3\u0002)\u001d\u0014X-\u001a8qYVlG%\u001a=uK:\u001c\u0018n\u001c82)\u0011\u0011iM!8\u0015!\u0005m!q\u001aBi\u0005'\u0014)Na6\u0003Z\nm\u0007b\u0002B\u0011\u0005\u000f\u0004\ra\u000e\u0005\b\u0005K\u00119\r1\u00018\u0011\u0019q'q\u0019a\u0001o!9!1\tBd\u0001\u00049\u0004b\u0002B$\u0005\u000f\u0004\ra\u000e\u0005\b\u0005\u0017\u00129\r1\u00018\u0011)\u0011ICa2\u0011\u0002\u0003\u0007!1\u0006\u0005\t\u0005\u000b\u00149\r1\u0001\u0003\u0016!Q!\u0011\u001dBW#\u0003%)Aa9\u0002;\u001d\u0014X-\u001a8qYVlG\u0005Z3gCVdG\u000fJ\u001c%Kb$XM\\:j_:$BA!\u0016\u0003f\"A!Q\u0019Bp\u0001\u0004\u0011)\u0002\u0003\u0006\u0003j\n5\u0016\u0011!C\u0003\u0005W\f!\u0003[1tQ\u000e{G-\u001a\u0013fqR,gn]5p]R!!Q\u000eBw\u0011!\u0011)Ma:A\u0002\tU\u0001B\u0003By\u0005[\u000b\t\u0011\"\u0002\u0003t\u0006\u0001R-];bYN$S\r\u001f;f]NLwN\u001c\u000b\u0005\u0005k\u0014I\u0010\u0006\u0003\u0003v\t]\bB\u0003B?\u0005_\f\t\u00111\u0001\u0002v\"A!Q\u0019Bx\u0001\u0004\u0011)\u0002"
)
public class GreenplumRelationProvider implements RelationProvider, DataSourceRegister, CreatableRelationProvider, LazyLogging {
   private final transient Logger logger;
   private transient volatile boolean bitmap$trans$0;

   public static Either<BoxedUnit, IllegalArgumentException> checkPartitionColumnType(StructField var0) {
      return GreenplumRelationProvider$.MODULE$.checkPartitionColumnType(var0);
   }

   public static DataFrameReader GreenplumDataFrameReader(DataFrameReader var0) {
      return GreenplumRelationProvider$.MODULE$.GreenplumDataFrameReader(var0);
   }

   public static <A> DataFrameReader apply(DataFrameReader var0) {
      return GreenplumRelationProvider$.MODULE$.apply(var0);
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

   public String shortName() {
      return "greenplum";
   }

   public BaseRelation createRelation(SQLContext sqlContext, Map<String, String> parameters) {
      GreenplumOptions greenplumOptions = new GreenplumOptions(parameters);
      this.getSparkContext(sqlContext);
      Connection conn = ConnectionManager$.MODULE$.getConnection(greenplumOptions, ConnectionManager$.MODULE$.getConnection$default$2());

      GreenplumRelation var16;
      try {
         GreenplumQualifiedName.Table table = GreenplumQualifiedName$.MODULE$.forTable(greenplumOptions.dbSchema(), greenplumOptions.dbTable());
         String url = greenplumOptions.url();
         StructType schema = Jdbc$.MODULE$.resolveTable(conn, url, table);
         String partitionColumnName = greenplumOptions.partitionColumn();
         Option partitionsCount = greenplumOptions.partitions();
         int[] gpSegmentIds = Jdbc$.MODULE$.retrieveSegmentIds(conn);
         GreenplumPartition[] partitions = this.computePartitions(conn, table, schema, partitionColumnName, partitionsCount, gpSegmentIds);
         BoxedUnit var10000;
         if (this.logger().underlying().isDebugEnabled()) {
            this.logger().underlying().debug("NumPartitions = {}", BoxesRunTime.boxToInteger(partitions.length));
            var10000 = BoxedUnit.UNIT;
         } else {
            var10000 = BoxedUnit.UNIT;
         }

         var16 = new GreenplumRelation(schema, partitions, greenplumOptions, sqlContext);
      } finally {
         conn.close();
      }

      return var16;
   }

   public GreenplumPartition[] computePartitions(Connection conn, GreenplumQualifiedName.Table table, StructType schema, String partitionColumnName, Option<Object> partitionsCount, int[] gpSegmentIds) {
      GreenplumPartition[] var10000;
      label40: {
         String var7 = GreenplumOptions$.MODULE$.DEFAULT_PARTITION_COLUMN_NAME();
         if (partitionColumnName == null) {
            if (var7 == null) {
               break label40;
            }
         } else if (partitionColumnName.equals(var7)) {
            break label40;
         }

         Option var9 = scala.Predef..MODULE$.refArrayOps((Object[])schema.fields()).find(new Serializable(this, partitionColumnName) {
            public static final long serialVersionUID = 0L;
            private final String partitionColumnName$1;

            public final boolean apply(StructField x$1) {
               boolean var3;
               label23: {
                  String var10000 = x$1.name();
                  String var2 = this.partitionColumnName$1;
                  if (var10000 == null) {
                     if (var2 == null) {
                        break label23;
                     }
                  } else if (var10000.equals(var2)) {
                     break label23;
                  }

                  var3 = false;
                  return var3;
               }

               var3 = true;
               return var3;
            }

            public {
               this.partitionColumnName$1 = partitionColumnName$1;
            }
         });
         if (!(var9 instanceof Some)) {
            if (scala.None..MODULE$.equals(var9)) {
               throw new IllegalArgumentException((new StringContext(scala.Predef..MODULE$.wrapRefArray((Object[])(new String[]{"'", "' does not exist in ", " table"})))).s(scala.Predef..MODULE$.genericWrapArray(new Object[]{partitionColumnName, table})));
            }

            throw new MatchError(var9);
         }

         Some var10 = (Some)var9;
         StructField structField = (StructField)var10.x();
         int numPartitions = BoxesRunTime.unboxToInt(partitionsCount.getOrElse(new Serializable(this, gpSegmentIds) {
            public static final long serialVersionUID = 0L;
            private final int[] gpSegmentIds$1;

            public final int apply() {
               return this.apply$mcI$sp();
            }

            public int apply$mcI$sp() {
               return scala.Predef..MODULE$.intArrayOps(this.gpSegmentIds$1).size();
            }

            public {
               this.gpSegmentIds$1 = gpSegmentIds$1;
            }
         }));
         Either var15 = GreenplumRelationProvider$.MODULE$.checkPartitionColumnType(structField);
         if (!(var15 instanceof Left)) {
            if (var15 instanceof Right) {
               Right var17 = (Right)var15;
               IllegalArgumentException e = (IllegalArgumentException)var17.b();
               throw e;
            }

            throw new MatchError(var15);
         }

         ColumnValueRange var16 = Jdbc$.MODULE$.computeColumnValueRange(conn, table, partitionColumnName);
         var10000 = GreenplumRelation$.MODULE$.columnPartition(numPartitions, structField, var16);
         return var10000;
      }

      scala.Predef..MODULE$.assert(partitionsCount.isEmpty(), new Serializable(this, partitionColumnName) {
         public static final long serialVersionUID = 0L;
         private final String partitionColumnName$1;

         public final String apply() {
            return (new StringBuilder()).append((new StringContext(scala.Predef..MODULE$.wrapRefArray((Object[])(new String[]{"When using '", "' for '", "', "})))).s(scala.Predef..MODULE$.genericWrapArray(new Object[]{this.partitionColumnName$1, GreenplumOptions$.MODULE$.GPDB_PARTITION_COLUMN()}))).append((new StringContext(scala.Predef..MODULE$.wrapRefArray((Object[])(new String[]{"'", "' is expected to be empty."})))).s(scala.Predef..MODULE$.genericWrapArray(new Object[]{GreenplumOptions$.MODULE$.GPDB_PARTITIONS()}))).toString();
         }

         public {
            this.partitionColumnName$1 = partitionColumnName$1;
         }
      });
      var10000 = (GreenplumPartition[])scala.Predef..MODULE$.refArrayOps((Object[])GreenplumRelation$.MODULE$.segmentPartitions(gpSegmentIds)).toArray(scala.reflect.ClassTag..MODULE$.apply(GreenplumPartition.class));
      return var10000;
   }

   public BaseRelation createRelation(SQLContext sqlContext, SaveMode mode, Map<String, String> parameters, Dataset<Row> data) {
      ObjectRef relation$lzy = ObjectRef.zero();
      VolatileByteRef bitmap$0 = VolatileByteRef.create((byte)0);
      GreenplumOptions greenplumOptions = new GreenplumOptions(parameters);
      SparkContext sparkContext = this.getSparkContext(sqlContext);
      GreenplumQualifiedName.Table destTable = GreenplumQualifiedName$.MODULE$.forTable(greenplumOptions.dbSchema(), greenplumOptions.dbTable());
      String tablePrefix = GreenplumTableManager$.MODULE$.generateExternalTableNamePrefix(sparkContext.applicationId(), greenplumOptions.dbTable());
      SqlExecutor executor = this.getSqlExecutor(greenplumOptions);
      GreenplumTableManager tableManager = this.getTableManager(executor);
      boolean tableCreated = BoxesRunTime.unboxToBoolean(tableManager.prepareTableForWrite(destTable, data.schema(), greenplumOptions, mode).get());
      if (!tableCreated) {
         label32: {
            SaveMode var14 = SaveMode.Ignore;
            if (mode == null) {
               if (var14 != null) {
                  break label32;
               }
            } else if (!mode.equals(var14)) {
               break label32;
            }

            BoxedUnit var10000;
            if (this.logger().underlying().isDebugEnabled()) {
               this.logger().underlying().debug("Table {} already exists with SaveMode.Ignore. Data is ignored.", destTable);
               var10000 = BoxedUnit.UNIT;
            } else {
               var10000 = BoxedUnit.UNIT;
            }

            return new BaseRelation(this, sqlContext, parameters, relation$lzy, bitmap$0) {
               private StructType schema;
               private SQLContext sqlContext;
               // $FF: synthetic field
               private final GreenplumRelationProvider $outer;
               private final SQLContext sqlContext$1;
               private final Map parameters$1;
               private final ObjectRef relation$lzy$1;
               private final VolatileByteRef bitmap$0$1;
               private volatile byte bitmap$0;

               private StructType schema$lzycompute() {
                  synchronized(this) {
                     if ((byte)(this.bitmap$0 & 1) == 0) {
                        this.schema = this.$outer.io$pivotal$greenplum$spark$GreenplumRelationProvider$$relation$1(this.sqlContext$1, this.parameters$1, this.relation$lzy$1, this.bitmap$0$1).schema();
                        this.bitmap$0 = (byte)(this.bitmap$0 | 1);
                     }

                     BoxedUnit var10000 = BoxedUnit.UNIT;
                  }

                  return this.schema;
               }

               private SQLContext sqlContext$lzycompute() {
                  synchronized(this) {
                     if ((byte)(this.bitmap$0 & 2) == 0) {
                        this.sqlContext = this.$outer.io$pivotal$greenplum$spark$GreenplumRelationProvider$$relation$1(this.sqlContext$1, this.parameters$1, this.relation$lzy$1, this.bitmap$0$1).sqlContext();
                        this.bitmap$0 = (byte)(this.bitmap$0 | 2);
                     }

                     BoxedUnit var10000 = BoxedUnit.UNIT;
                  }

                  return this.sqlContext;
               }

               public StructType schema() {
                  return (byte)(this.bitmap$0 & 1) == 0 ? this.schema$lzycompute() : this.schema;
               }

               public SQLContext sqlContext() {
                  return (byte)(this.bitmap$0 & 2) == 0 ? this.sqlContext$lzycompute() : this.sqlContext;
               }

               public {
                  if ($outer == null) {
                     throw null;
                  } else {
                     this.$outer = $outer;
                     this.sqlContext$1 = sqlContext$1;
                     this.parameters$1 = parameters$1;
                     this.relation$lzy$1 = relation$lzy$1;
                     this.bitmap$0$1 = bitmap$0$1;
                  }
               }
            };
         }
      }

      BoxesRunTime.boxToInteger(this.saveDataFrame(tableManager, destTable, data, greenplumOptions, sparkContext));
      return new BaseRelation(this, sqlContext, parameters, relation$lzy, bitmap$0) {
         private StructType schema;
         private SQLContext sqlContext;
         // $FF: synthetic field
         private final GreenplumRelationProvider $outer;
         private final SQLContext sqlContext$1;
         private final Map parameters$1;
         private final ObjectRef relation$lzy$1;
         private final VolatileByteRef bitmap$0$1;
         private volatile byte bitmap$0;

         private StructType schema$lzycompute() {
            synchronized(this) {
               if ((byte)(this.bitmap$0 & 1) == 0) {
                  this.schema = this.$outer.io$pivotal$greenplum$spark$GreenplumRelationProvider$$relation$1(this.sqlContext$1, this.parameters$1, this.relation$lzy$1, this.bitmap$0$1).schema();
                  this.bitmap$0 = (byte)(this.bitmap$0 | 1);
               }

               BoxedUnit var10000 = BoxedUnit.UNIT;
            }

            return this.schema;
         }

         private SQLContext sqlContext$lzycompute() {
            synchronized(this) {
               if ((byte)(this.bitmap$0 & 2) == 0) {
                  this.sqlContext = this.$outer.io$pivotal$greenplum$spark$GreenplumRelationProvider$$relation$1(this.sqlContext$1, this.parameters$1, this.relation$lzy$1, this.bitmap$0$1).sqlContext();
                  this.bitmap$0 = (byte)(this.bitmap$0 | 2);
               }

               BoxedUnit var10000 = BoxedUnit.UNIT;
            }

            return this.sqlContext;
         }

         public StructType schema() {
            return (byte)(this.bitmap$0 & 1) == 0 ? this.schema$lzycompute() : this.schema;
         }

         public SQLContext sqlContext() {
            return (byte)(this.bitmap$0 & 2) == 0 ? this.sqlContext$lzycompute() : this.sqlContext;
         }

         public {
            if ($outer == null) {
               throw null;
            } else {
               this.$outer = $outer;
               this.sqlContext$1 = sqlContext$1;
               this.parameters$1 = parameters$1;
               this.relation$lzy$1 = relation$lzy$1;
               this.bitmap$0$1 = bitmap$0$1;
            }
         }
      };
   }

   public GreenplumTableManager getTableManager(SqlExecutor executor) {
      return new GreenplumTableManager(executor);
   }

   public Function2<Object, Iterator<Row>, Iterator<Object>> getPartitionWriter(GreenplumOptions greenplumOptions, SparkContext sparkContext, Function1<Row, Row> rowTransformer) {
      return (new PartitionWriter(sparkContext.applicationId(), greenplumOptions, rowTransformer)).getClosure();
   }

   public SparkContext getSparkContext(SQLContext sqlContext) {
      return sqlContext.sparkContext();
   }

   public SqlExecutor getSqlExecutor(GreenplumOptions greenplumOptions) {
      return new SqlExecutor(ConnectionManager$.MODULE$.getConnection(greenplumOptions, ConnectionManager$.MODULE$.getConnection$default$2()));
   }

   private int saveDataFrame(GreenplumTableManager tableManager, GreenplumQualifiedName destTable, Dataset<Row> data, GreenplumOptions greenplumOptions, SparkContext sparkContext) {
      Seq gpdbColumns = (Seq)tableManager.getColumnNames(destTable).get();
      Function1 rowTransformer = (Function1)RowTransformer$.MODULE$.getFunction(scala.Predef..MODULE$.wrapRefArray((Object[])data.columns()), gpdbColumns).get();
      Function2 partitionWriter = this.getPartitionWriter(greenplumOptions, sparkContext, rowTransformer);
      RDD rowCounts = data.rdd().mapPartitionsWithIndex(partitionWriter, data.rdd().mapPartitionsWithIndex$default$2(), scala.reflect.ClassTag..MODULE$.Int());
      return BoxesRunTime.unboxToInt(rowCounts.fold(BoxesRunTime.boxToInteger(0), new Serializable(this) {
         public static final long serialVersionUID = 0L;

         public final int apply(int x$2, int x$3) {
            return this.apply$mcIII$sp(x$2, x$3);
         }

         public int apply$mcIII$sp(int x$2, int x$3) {
            return x$2 + x$3;
         }
      }));
   }

   private final BaseRelation relation$lzycompute$1(SQLContext sqlContext$1, Map parameters$1, ObjectRef relation$lzy$1, VolatileByteRef bitmap$0$1) {
      synchronized(this) {
         if ((byte)(bitmap$0$1.elem & 1) == 0) {
            relation$lzy$1.elem = this.createRelation(sqlContext$1, parameters$1);
            bitmap$0$1.elem = (byte)(bitmap$0$1.elem | 1);
         }

         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      return (BaseRelation)relation$lzy$1.elem;
   }

   public final BaseRelation io$pivotal$greenplum$spark$GreenplumRelationProvider$$relation$1(SQLContext sqlContext$1, Map parameters$1, ObjectRef relation$lzy$1, VolatileByteRef bitmap$0$1) {
      return (byte)(bitmap$0$1.elem & 1) == 0 ? this.relation$lzycompute$1(sqlContext$1, parameters$1, relation$lzy$1, bitmap$0$1) : (BaseRelation)relation$lzy$1.elem;
   }

   public GreenplumRelationProvider() {
      LazyLogging$class.$init$(this);
   }

   public static final class GreenplumDataFrameReader {
      private final DataFrameReader reader;

      public DataFrameReader reader() {
         return this.reader;
      }

      public Dataset<Row> greenplum(String jdbcUrl, String tableName, Properties properties) {
         return GreenplumRelationProvider.GreenplumDataFrameReader$.MODULE$.greenplum$extension0(this.reader(), jdbcUrl, tableName, properties);
      }

      public Dataset<Row> greenplum(String jdbcUrl, String tableName, String schema, String username, String password, String partitionColumn, Properties properties) {
         return GreenplumRelationProvider.GreenplumDataFrameReader$.MODULE$.greenplum$extension1(this.reader(), jdbcUrl, tableName, schema, username, password, partitionColumn, properties);
      }

      public Properties greenplum$default$7() {
         return GreenplumRelationProvider.GreenplumDataFrameReader$.MODULE$.greenplum$default$7$extension(this.reader());
      }

      public int hashCode() {
         return GreenplumRelationProvider.GreenplumDataFrameReader$.MODULE$.hashCode$extension(this.reader());
      }

      public boolean equals(Object x$1) {
         return GreenplumRelationProvider.GreenplumDataFrameReader$.MODULE$.equals$extension(this.reader(), x$1);
      }

      public GreenplumDataFrameReader(DataFrameReader reader) {
         this.reader = reader;
      }
   }

   public static class GreenplumDataFrameReader$ {
      public static final GreenplumRelationProvider.GreenplumDataFrameReader$ MODULE$;

      static {
         new GreenplumRelationProvider.GreenplumDataFrameReader$();
      }

      public final Dataset<Row> greenplum$extension0(DataFrameReader $this, String jdbcUrl, String tableName, Properties properties) {
         return $this.format("greenplum").options((scala.collection.Map)scala.collection.JavaConverters..MODULE$.propertiesAsScalaMapConverter(properties).asScala()).option(GreenplumOptions$.MODULE$.GPDB_URL(), jdbcUrl).option(GreenplumOptions$.MODULE$.GPDB_TABLE_NAME(), tableName).load();
      }

      public final Dataset<Row> greenplum$extension1(DataFrameReader $this, String jdbcUrl, String tableName, String schema, String username, String password, String partitionColumn, Properties properties) {
         return $this.format("greenplum").options((scala.collection.Map)scala.collection.JavaConverters..MODULE$.propertiesAsScalaMapConverter(properties).asScala()).option(GreenplumOptions$.MODULE$.GPDB_URL(), jdbcUrl).option(GreenplumOptions$.MODULE$.GPDB_SCHEMA_NAME(), schema).option(GreenplumOptions$.MODULE$.GPDB_TABLE_NAME(), tableName).option(GreenplumOptions$.MODULE$.GPDB_USER(), username).option(GreenplumOptions$.MODULE$.GPDB_PASSWORD(), password).option(GreenplumOptions$.MODULE$.GPDB_PARTITION_COLUMN(), partitionColumn).load();
      }

      public final Properties greenplum$default$7$extension(DataFrameReader $this) {
         return new Properties();
      }

      public final int hashCode$extension(DataFrameReader $this) {
         return $this.hashCode();
      }

      public final boolean equals$extension(DataFrameReader $this, Object x$1) {
         boolean var4;
         if (x$1 instanceof GreenplumRelationProvider.GreenplumDataFrameReader) {
            var4 = true;
         } else {
            var4 = false;
         }

         boolean var10000;
         if (var4) {
            label31: {
               label30: {
                  DataFrameReader var5 = x$1 == null ? null : ((GreenplumRelationProvider.GreenplumDataFrameReader)x$1).reader();
                  if ($this == null) {
                     if (var5 == null) {
                        break label30;
                     }
                  } else if ($this.equals(var5)) {
                     break label30;
                  }

                  var10000 = false;
                  break label31;
               }

               var10000 = true;
            }

            if (var10000) {
               var10000 = true;
               return var10000;
            }
         }

         var10000 = false;
         return var10000;
      }

      public GreenplumDataFrameReader$() {
         MODULE$ = this;
      }
   }
}
