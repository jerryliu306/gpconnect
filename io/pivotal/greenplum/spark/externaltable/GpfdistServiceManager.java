package io.pivotal.greenplum.spark.externaltable;

import com.typesafe.scalalogging.Logger;
import io.pivotal.greenplum.spark.conf.ConnectorOptions;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0001E<Q!\u0001\u0002\t\u00025\tQc\u00129gI&\u001cHoU3sm&\u001cW-T1oC\u001e,'O\u0003\u0002\u0004\t\u0005iQ\r\u001f;fe:\fG\u000e^1cY\u0016T!!\u0002\u0004\u0002\u000bM\u0004\u0018M]6\u000b\u0005\u001dA\u0011!C4sK\u0016t\u0007\u000f\\;n\u0015\tI!\"A\u0004qSZ|G/\u00197\u000b\u0003-\t!![8\u0004\u0001A\u0011abD\u0007\u0002\u0005\u0019)\u0001C\u0001E\u0001#\t)r\t\u001d4eSN$8+\u001a:wS\u000e,W*\u00198bO\u0016\u00148cA\b\u00131A\u00111CF\u0007\u0002))\tQ#A\u0003tG\u0006d\u0017-\u0003\u0002\u0018)\t1\u0011I\\=SK\u001a\u0004\"!\u0007\u0011\u000e\u0003iQ!a\u0007\u000f\u0002\u0019M\u001c\u0017\r\\1m_\u001e<\u0017N\\4\u000b\u0005uq\u0012\u0001\u0003;za\u0016\u001c\u0018MZ3\u000b\u0003}\t1aY8n\u0013\t\t#DA\u0006MCjLHj\\4hS:<\u0007\"B\u0012\u0010\t\u0003!\u0013A\u0002\u001fj]&$h\bF\u0001\u000e\u0011\u001d1sB1A\u0005\n\u001d\n\u0011BY;gM\u0016\u0014X*\u00199\u0016\u0003!\u0002B!\u000b\u00193s5\t!F\u0003\u0002,Y\u0005Q1m\u001c8dkJ\u0014XM\u001c;\u000b\u00055r\u0013\u0001B;uS2T\u0011aL\u0001\u0005U\u00064\u0018-\u0003\u00022U\t\t2i\u001c8dkJ\u0014XM\u001c;ICNDW*\u00199\u0011\u0005M2dBA\n5\u0013\t)D#\u0001\u0004Qe\u0016$WMZ\u0005\u0003oa\u0012aa\u0015;sS:<'BA\u001b\u0015!\rQDHP\u0007\u0002w)\u0011Q\u0006F\u0005\u0003{m\u00121\u0001\u0016:z!\ty\u0014)D\u0001A\u0015\tiC!\u0003\u0002C\u0001\nyAK]1og\u0006\u001cG/[8o\t\u0006$\u0018\r\u0003\u0004E\u001f\u0001\u0006I\u0001K\u0001\u000bEV4g-\u001a:NCB\u0004\u0003b\u0002$\u0010\u0005\u0004%IaR\u0001\u000eg\u0016tGMQ;gM\u0016\u0014X*\u00199\u0016\u0003!\u0003B!\u000b\u00193\u0013B\u0011aBS\u0005\u0003\u0017\n\u0011Q\u0002U1si&$\u0018n\u001c8ECR\f\u0007BB'\u0010A\u0003%\u0001*\u0001\btK:$')\u001e4gKJl\u0015\r\u001d\u0011\t\u000f={!\u0019!C\u0005!\u0006Y1/\u001a:wS\u000e,7/T1q+\u0005\t\u0006\u0003B\u00151%V\u0003\"AD*\n\u0005Q\u0013!AC*feZL7-Z&fsB\u0011aBV\u0005\u0003/\n\u0011ab\u00129gI&\u001cHoU3sm&\u001cW\r\u0003\u0004Z\u001f\u0001\u0006I!U\u0001\rg\u0016\u0014h/[2fg6\u000b\u0007\u000f\t\u0005\u00067>!\t\u0001X\u0001\u000bO\u0016$8+\u001a:wS\u000e,GCA+^\u0011\u0015q&\f1\u0001`\u0003A\u0019wN\u001c8fGR|'o\u00149uS>t7\u000f\u0005\u0002aG6\t\u0011M\u0003\u0002c\t\u0005!1m\u001c8g\u0013\t!\u0017M\u0001\tD_:tWm\u0019;pe>\u0003H/[8og\")am\u0004C\u0001O\u0006i1\u000f^8q\u0003:$'+Z7pm\u0016$\"\u0001[6\u0011\u0005MI\u0017B\u00016\u0015\u0005\u0011)f.\u001b;\t\u000b1,\u0007\u0019\u0001*\u0002\u0007-,\u0017\u0010C\u0003o\u001f\u0011%q.A\u0007hKR\u001cVM\u001d<fe\"{7\u000f\u001e\u000b\u0003eADQAX7A\u0002}\u0003"
)
public final class GpfdistServiceManager {
   public static Logger logger() {
      return GpfdistServiceManager$.MODULE$.logger();
   }

   public static void stopAndRemove(ServiceKey var0) {
      GpfdistServiceManager$.MODULE$.stopAndRemove(var0);
   }

   public static GpfdistService getService(ConnectorOptions var0) {
      return GpfdistServiceManager$.MODULE$.getService(var0);
   }
}
