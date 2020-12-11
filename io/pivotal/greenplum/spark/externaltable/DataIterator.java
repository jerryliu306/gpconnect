package io.pivotal.greenplum.spark.externaltable;

import com.typesafe.scalalogging.LazyLogging;
import com.typesafe.scalalogging.LazyLogging$class;
import com.typesafe.scalalogging.Logger;
import io.pivotal.greenplum.spark.GreenplumCSVFormat$;
import java.io.InputStream;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import shadeio.univocity.parsers.common.ParsingContext;
import shadeio.univocity.parsers.common.ResultIterator;
import shadeio.univocity.parsers.csv.CsvParser;

@ScalaSignature(
   bytes = "\u0006\u0001\r4Q!\u0001\u0002\u0001\u00051\u0011A\u0002R1uC&#XM]1u_JT!a\u0001\u0003\u0002\u001b\u0015DH/\u001a:oC2$\u0018M\u00197f\u0015\t)a!A\u0003ta\u0006\u00148N\u0003\u0002\b\u0011\u0005IqM]3f]BdW/\u001c\u0006\u0003\u0013)\tq\u0001]5w_R\fGNC\u0001\f\u0003\tIwnE\u0002\u0001\u001by\u00012AD\b\u0012\u001b\u0005\u0011\u0011B\u0001\t\u0003\u00051qU\r\u001f;Ji\u0016\u0014\u0018\r^8s!\r\u0011RcF\u0007\u0002')\tA#A\u0003tG\u0006d\u0017-\u0003\u0002\u0017'\t)\u0011I\u001d:bsB\u0011\u0001d\u0007\b\u0003%eI!AG\n\u0002\rA\u0013X\rZ3g\u0013\taRD\u0001\u0004TiJLgn\u001a\u0006\u00035M\u0001\"a\b\u0014\u000e\u0003\u0001R!!\t\u0012\u0002\u0019M\u001c\u0017\r\\1m_\u001e<\u0017N\\4\u000b\u0005\r\"\u0013\u0001\u0003;za\u0016\u001c\u0018MZ3\u000b\u0003\u0015\n1aY8n\u0013\t9\u0003EA\u0006MCjLHj\\4hS:<\u0007\u0002C\u0015\u0001\u0005\u000b\u0007I\u0011A\u0016\u0002\u0017%t\u0007/\u001e;TiJ,\u0017-\\\u0002\u0001+\u0005a\u0003CA\u00172\u001b\u0005q#BA\u00060\u0015\u0005\u0001\u0014\u0001\u00026bm\u0006L!A\r\u0018\u0003\u0017%s\u0007/\u001e;TiJ,\u0017-\u001c\u0005\ti\u0001\u0011\t\u0011)A\u0005Y\u0005a\u0011N\u001c9viN#(/Z1nA!)a\u0007\u0001C\u0001o\u00051A(\u001b8jiz\"\"\u0001O\u001d\u0011\u00059\u0001\u0001\"B\u00156\u0001\u0004a\u0003\u0002C\u001e\u0001\u0011\u000b\u0007I\u0011\u0001\u001f\u0002\u001dA\f'o]3s\u0013R,'/\u0019;peV\tQ\b\u0005\u0003?\u000b\u001ekU\"A \u000b\u0005\u0001\u000b\u0015AB2p[6|gN\u0003\u0002C\u0007\u00069\u0001/\u0019:tKJ\u001c(B\u0001#%\u0003%)h.\u001b<pG&$\u00180\u0003\u0002G\u007f\tq!+Z:vYRLE/\u001a:bi>\u0014\bc\u0001\n\u0016\u0011B\u0011\u0011\nT\u0007\u0002\u0015*\u00111jL\u0001\u0005Y\u0006tw-\u0003\u0002\u001d\u0015B\u0011aHT\u0005\u0003\u001f~\u0012a\u0002U1sg&twmQ8oi\u0016DH\u000f\u0003\u0005R\u0001!\u0005\t\u0015)\u0003>\u0003=\u0001\u0018M]:fe&#XM]1u_J\u0004\u0003\"B*\u0001\t#\"\u0016aB4fi:+\u0007\u0010\u001e\u000b\u0002#!)a\u000b\u0001C!)\u0006!a.\u001a=u\u0011\u0015A\u0006\u0001\"\u0011Z\u0003\u001dA\u0017m\u001d(fqR,\u0012A\u0017\t\u0003%mK!\u0001X\n\u0003\u000f\t{w\u000e\\3b]\")a\f\u0001C)?\u0006)1\r\\8tKR\t\u0001\r\u0005\u0002\u0013C&\u0011!m\u0005\u0002\u0005+:LG\u000f"
)
public class DataIterator extends NextIterator<String[]> implements LazyLogging {
   private final InputStream inputStream;
   private ResultIterator<String[], ParsingContext> parserIterator;
   private final transient Logger logger;
   private volatile boolean bitmap$0;
   private transient volatile boolean bitmap$trans$0;

   private ResultIterator parserIterator$lzycompute() {
      synchronized(this) {
         if (!this.bitmap$0) {
            this.parserIterator = (new CsvParser(GreenplumCSVFormat$.MODULE$.DEFAULT())).iterate(this.inputStream(), GreenplumCSVFormat$.MODULE$.DEFAULT_ENCODING()).iterator();
            this.bitmap$0 = true;
         }

         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      return this.parserIterator;
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

   public InputStream inputStream() {
      return this.inputStream;
   }

   public ResultIterator<String[], ParsingContext> parserIterator() {
      return this.bitmap$0 ? this.parserIterator : this.parserIterator$lzycompute();
   }

   public String[] getNext() {
      return this.next();
   }

   public String[] next() {
      String[] var10000;
      if (this.parserIterator().hasNext()) {
         var10000 = (String[])this.parserIterator().next();
      } else {
         this.finished_$eq(true);
         var10000 = null;
      }

      return var10000;
   }

   public boolean hasNext() {
      return this.parserIterator().hasNext();
   }

   public void close() {
      this.inputStream().close();
   }

   public DataIterator(InputStream inputStream) {
      this.inputStream = inputStream;
      LazyLogging$class.$init$(this);
   }
}
