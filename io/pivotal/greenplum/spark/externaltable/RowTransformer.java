package io.pivotal.greenplum.spark.externaltable;

import com.typesafe.scalalogging.Logger;
import org.apache.spark.sql.Row;
import scala.Function1;
import scala.collection.Seq;
import scala.reflect.ScalaSignature;
import scala.util.Try;

@ScalaSignature(
   bytes = "\u0006\u0001u;Q!\u0001\u0002\t\u00025\taBU8x)J\fgn\u001d4pe6,'O\u0003\u0002\u0004\t\u0005iQ\r\u001f;fe:\fG\u000e^1cY\u0016T!!\u0002\u0004\u0002\u000bM\u0004\u0018M]6\u000b\u0005\u001dA\u0011!C4sK\u0016t\u0007\u000f\\;n\u0015\tI!\"A\u0004qSZ|G/\u00197\u000b\u0003-\t!![8\u0004\u0001A\u0011abD\u0007\u0002\u0005\u0019)\u0001C\u0001E\u0001#\tq!k\\<Ue\u0006t7OZ8s[\u0016\u00148cA\b\u00131A\u00111CF\u0007\u0002))\tQ#A\u0003tG\u0006d\u0017-\u0003\u0002\u0018)\t1\u0011I\\=SK\u001a\u0004\"!\u0007\u0011\u000e\u0003iQ!a\u0007\u000f\u0002\u0019M\u001c\u0017\r\\1m_\u001e<\u0017N\\4\u000b\u0005uq\u0012\u0001\u0003;za\u0016\u001c\u0018MZ3\u000b\u0003}\t1aY8n\u0013\t\t#DA\u0006MCjLHj\\4hS:<\u0007\"B\u0012\u0010\t\u0003!\u0013A\u0002\u001fj]&$h\bF\u0001\u000e\u0011\u001d1sB1A\u0005\u0002\u001d\n\u0001#\u001b3f]RLG/\u001f$v]\u000e$\u0018n\u001c8\u0016\u0003!\u0002BaE\u0015,W%\u0011!\u0006\u0006\u0002\n\rVt7\r^5p]F\u0002\"\u0001\f\u001b\u000e\u00035R!AL\u0018\u0002\u0007M\fHN\u0003\u0002\u0006a)\u0011\u0011GM\u0001\u0007CB\f7\r[3\u000b\u0003M\n1a\u001c:h\u0013\t)TFA\u0002S_^DaaN\b!\u0002\u0013A\u0013!E5eK:$\u0018\u000e^=Gk:\u001cG/[8oA!)\u0011h\u0004C\u0001u\u0005Yq-\u001a;Gk:\u001cG/[8o)\rY\u0014I\u0016\t\u0004y}BS\"A\u001f\u000b\u0005y\"\u0012\u0001B;uS2L!\u0001Q\u001f\u0003\u0007Q\u0013\u0018\u0010C\u0003Cq\u0001\u00071)A\u0005ta\u0006\u00148nQ8mgB\u0019A\tT(\u000f\u0005\u0015SeB\u0001$J\u001b\u00059%B\u0001%\r\u0003\u0019a$o\\8u}%\tQ#\u0003\u0002L)\u00059\u0001/Y2lC\u001e,\u0017BA'O\u0005\r\u0019V-\u001d\u0006\u0003\u0017R\u0001\"\u0001U*\u000f\u0005M\t\u0016B\u0001*\u0015\u0003\u0019\u0001&/\u001a3fM&\u0011A+\u0016\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005I#\u0002\"B,9\u0001\u0004\u0019\u0015\u0001C4qI\n\u001cu\u000e\\:\t\u000be{A\u0011\u0002.\u0002!\u0019|'/\\1u\u0007>dW/\u001c8MSN$HCA(\\\u0011\u0015a\u0006\f1\u0001D\u0003\u001d\u0019w\u000e\\;n]N\u0004"
)
public final class RowTransformer {
   public static Logger logger() {
      return RowTransformer$.MODULE$.logger();
   }

   public static Try<Function1<Row, Row>> getFunction(Seq<String> var0, Seq<String> var1) {
      return RowTransformer$.MODULE$.getFunction(var0, var1);
   }

   public static Function1<Row, Row> identityFunction() {
      return RowTransformer$.MODULE$.identityFunction();
   }
}
