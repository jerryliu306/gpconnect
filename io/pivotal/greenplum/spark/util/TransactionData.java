package io.pivotal.greenplum.spark.util;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import scala.Serializable;
import scala.Predef.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.util.Try;

@ScalaSignature(
   bytes = "\u0006\u0001Y3A!\u0001\u0002\u0001\u001b\tyAK]1og\u0006\u001cG/[8o\t\u0006$\u0018M\u0003\u0002\u0004\t\u0005!Q\u000f^5m\u0015\t)a!A\u0003ta\u0006\u00148N\u0003\u0002\b\u0011\u0005IqM]3f]BdW/\u001c\u0006\u0003\u0013)\tq\u0001]5w_R\fGNC\u0001\f\u0003\tIwn\u0001\u0001\u0014\u0005\u0001q\u0001CA\b\u0013\u001b\u0005\u0001\"\"A\t\u0002\u000bM\u001c\u0017\r\\1\n\u0005M\u0001\"AB!osJ+g\rC\u0003\u0016\u0001\u0011\u0005a#\u0001\u0004=S:LGO\u0010\u000b\u0002/A\u0011\u0001\u0004A\u0007\u0002\u0005!9!\u0004\u0001b\u0001\n\u0003Y\u0012!D#O\t~{eiX*U%\u0016\u000bU*F\u0001\u001d!\tyQ$\u0003\u0002\u001f!\t\u0019\u0011J\u001c;\t\r\u0001\u0002\u0001\u0015!\u0003\u001d\u00039)e\nR0P\r~\u001bFKU#B\u001b\u0002BqA\t\u0001A\u0002\u0013%1%\u0001\bti\u0006\u0014H/\u001a3SK\u0006$\u0017N\\4\u0016\u0003\u0011\u0002\"aD\u0013\n\u0005\u0019\u0002\"a\u0002\"p_2,\u0017M\u001c\u0005\bQ\u0001\u0001\r\u0011\"\u0003*\u0003I\u0019H/\u0019:uK\u0012\u0014V-\u00193j]\u001e|F%Z9\u0015\u0005)j\u0003CA\b,\u0013\ta\u0003C\u0001\u0003V]&$\bb\u0002\u0018(\u0003\u0003\u0005\r\u0001J\u0001\u0004q\u0012\n\u0004B\u0002\u0019\u0001A\u0003&A%A\bti\u0006\u0014H/\u001a3SK\u0006$\u0017N\\4!\u0011\u001d\u0011\u0004A1A\u0005\nM\n\u0011BY;gM\u0016\u0014X*\u00199\u0016\u0003Q\u0002B!N\u001d\u001dw5\taG\u0003\u0002\u0004o)\t\u0001(\u0001\u0003kCZ\f\u0017B\u0001\u001e7\u0005\ri\u0015\r\u001d\t\u00031qJ!!\u0010\u0002\u0003\u001f1\u000b'oZ3CsR,')\u001e4gKJDaa\u0010\u0001!\u0002\u0013!\u0014A\u00032vM\u001a,'/T1qA!)\u0011\t\u0001C\u0001\u0005\u0006qq-\u001a;J]B,Ho\u0015;sK\u0006lG#A\"\u0011\u0005\u00113U\"A#\u000b\u0005-9\u0014BA$F\u0005-Ie\u000e];u'R\u0014X-Y7\t\u000b%\u0003A\u0011\u0001&\u0002\u000b]\u0014\u0018\u000e^3\u0015\t-\u0003&\u000b\u0016\t\u0004\u0019:SS\"A'\u000b\u0005\r\u0001\u0012BA(N\u0005\r!&/\u001f\u0005\u0006#\"\u0003\r\u0001H\u0001\ng\u0016<W.\u001a8u\u0013\u0012DQa\u0015%A\u0002\r\u000b1\"\u001b8qkR\u001cFO]3b[\")Q\u000b\u0013a\u00019\u0005i1m\u001c8uK:$H*\u001a8hi\"\u0004"
)
public class TransactionData {
   private final int END_OF_STREAM = -1;
   private boolean io$pivotal$greenplum$spark$util$TransactionData$$startedReading = false;
   private final Map<Object, LargeByteBuffer> io$pivotal$greenplum$spark$util$TransactionData$$bufferMap = new HashMap();

   public int END_OF_STREAM() {
      return this.END_OF_STREAM;
   }

   private boolean io$pivotal$greenplum$spark$util$TransactionData$$startedReading() {
      return this.io$pivotal$greenplum$spark$util$TransactionData$$startedReading;
   }

   public void io$pivotal$greenplum$spark$util$TransactionData$$startedReading_$eq(boolean x$1) {
      this.io$pivotal$greenplum$spark$util$TransactionData$$startedReading = x$1;
   }

   public Map<Object, LargeByteBuffer> io$pivotal$greenplum$spark$util$TransactionData$$bufferMap() {
      return this.io$pivotal$greenplum$spark$util$TransactionData$$bufferMap;
   }

   public InputStream getInputStream() {
      return new InputStream(this) {
         private InputStream currStream;
         // $FF: synthetic field
         private final TransactionData $outer;

         private InputStream currStream() {
            return this.currStream;
         }

         private void currStream_$eq(InputStream x$1) {
            this.currStream = x$1;
         }

         public int read() {
            if (this.currStream() == null) {
               this.currStream_$eq(this.nextStream());
               if (this.currStream() == null) {
                  return this.$outer.END_OF_STREAM();
               }
            }

            int value = this.currStream().read();
            int var10000;
            if (value == this.$outer.END_OF_STREAM()) {
               this.currStream_$eq(this.nextStream());
               var10000 = this.currStream() == null ? this.$outer.END_OF_STREAM() : this.currStream().read();
            } else {
               var10000 = value;
            }

            return var10000;
         }

         public void close() {
            this.$outer.io$pivotal$greenplum$spark$util$TransactionData$$bufferMap().clear();
            this.currStream_$eq((InputStream)null);
         }

         private InputStream nextStream() {
            if (this.currStream() != null) {
               this.currStream().close();
            }

            return this.$outer.io$pivotal$greenplum$spark$util$TransactionData$$bufferMap().isEmpty() ? null : ((LargeByteBuffer)this.$outer.io$pivotal$greenplum$spark$util$TransactionData$$bufferMap().remove(this.$outer.io$pivotal$greenplum$spark$util$TransactionData$$bufferMap().keySet().iterator().next())).getInputStream();
         }

         public {
            if ($outer == null) {
               throw null;
            } else {
               this.$outer = $outer;
               $outer.io$pivotal$greenplum$spark$util$TransactionData$$startedReading_$eq(true);
            }
         }
      };
   }

   public Try<BoxedUnit> write(int segmentId, InputStream inputStream, int contentLength) {
      .MODULE$.require(!this.io$pivotal$greenplum$spark$util$TransactionData$$startedReading(), new Serializable(this) {
         public static final long serialVersionUID = 0L;

         public final String apply() {
            return "Cannot write to TransactionData after reading";
         }
      });
      LargeByteBuffer partitionBuffer = null;
      BoxedUnit var10000;
      if (this.io$pivotal$greenplum$spark$util$TransactionData$$bufferMap().containsKey(BoxesRunTime.boxToInteger(segmentId))) {
         partitionBuffer = (LargeByteBuffer)this.io$pivotal$greenplum$spark$util$TransactionData$$bufferMap().get(BoxesRunTime.boxToInteger(segmentId));
         var10000 = BoxedUnit.UNIT;
      } else {
         synchronized(this.io$pivotal$greenplum$spark$util$TransactionData$$bufferMap()) {
            if (this.io$pivotal$greenplum$spark$util$TransactionData$$bufferMap().containsKey(BoxesRunTime.boxToInteger(segmentId))) {
               partitionBuffer = (LargeByteBuffer)this.io$pivotal$greenplum$spark$util$TransactionData$$bufferMap().get(BoxesRunTime.boxToInteger(segmentId));
               var10000 = BoxedUnit.UNIT;
            } else {
               partitionBuffer = new LargeByteBuffer();
               this.io$pivotal$greenplum$spark$util$TransactionData$$bufferMap().put(BoxesRunTime.boxToInteger(segmentId), partitionBuffer);
            }
         }
      }

      return partitionBuffer.write(inputStream, contentLength);
   }
}
