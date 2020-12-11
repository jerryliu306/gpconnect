package io.pivotal.greenplum.spark.util;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.IOUtils;
import scala.Serializable;
import scala.StringContext;
import scala.Predef.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.util.Try;

@ScalaSignature(
   bytes = "\u0006\u0001\u00153A!\u0001\u0002\u0001\u001b\tyA*\u0019:hK\nKH/\u001a\"vM\u001a,'O\u0003\u0002\u0004\t\u0005!Q\u000f^5m\u0015\t)a!A\u0003ta\u0006\u00148N\u0003\u0002\b\u0011\u0005IqM]3f]BdW/\u001c\u0006\u0003\u0013)\tq\u0001]5w_R\fGNC\u0001\f\u0003\tIwn\u0001\u0001\u0014\u0005\u0001q\u0001CA\b\u0013\u001b\u0005\u0001\"\"A\t\u0002\u000bM\u001c\u0017\r\\1\n\u0005M\u0001\"AB!osJ+g\rC\u0003\u0016\u0001\u0011\u0005a#\u0001\u0004=S:LGO\u0010\u000b\u0002/A\u0011\u0001\u0004A\u0007\u0002\u0005!9!\u0004\u0001b\u0001\n\u0013Y\u0012A\u00032vM\u001a,'\u000fT5tiV\tA\u0004E\u0002\u001eC\rj\u0011A\b\u0006\u0003\u0007}Q\u0011\u0001I\u0001\u0005U\u00064\u0018-\u0003\u0002#=\t!A*[:u!\ryAEJ\u0005\u0003KA\u0011Q!\u0011:sCf\u0004\"aD\u0014\n\u0005!\u0002\"\u0001\u0002\"zi\u0016DaA\u000b\u0001!\u0002\u0013a\u0012a\u00032vM\u001a,'\u000fT5ti\u0002BQ\u0001\f\u0001\u0005\u00025\nabZ3u\u0013:\u0004X\u000f^*ue\u0016\fW\u000eF\u0001/!\ty\u0013'D\u00011\u0015\tYq$\u0003\u00023a\tY\u0011J\u001c9viN#(/Z1n\u0011\u0015!\u0004\u0001\"\u00016\u0003\u00159(/\u001b;f)\r1d\b\u0011\t\u0004oeZT\"\u0001\u001d\u000b\u0005\r\u0001\u0012B\u0001\u001e9\u0005\r!&/\u001f\t\u0003\u001fqJ!!\u0010\t\u0003\tUs\u0017\u000e\u001e\u0005\u0006\u007fM\u0002\rAL\u0001\fS:\u0004X\u000f^*ue\u0016\fW\u000eC\u0003Bg\u0001\u0007!)\u0001\u0003tSj,\u0007CA\bD\u0013\t!\u0005CA\u0002J]R\u0004"
)
public class LargeByteBuffer {
   private final List<byte[]> io$pivotal$greenplum$spark$util$LargeByteBuffer$$bufferList = new ArrayList();

   public List<byte[]> io$pivotal$greenplum$spark$util$LargeByteBuffer$$bufferList() {
      return this.io$pivotal$greenplum$spark$util$LargeByteBuffer$$bufferList;
   }

   public InputStream getInputStream() {
      return new InputStream(this) {
         private int pos;
         private int listPos;
         private byte[] currArray;
         // $FF: synthetic field
         private final LargeByteBuffer $outer;

         private int pos() {
            return this.pos;
         }

         private void pos_$eq(int x$1) {
            this.pos = x$1;
         }

         private int listPos() {
            return this.listPos;
         }

         private void listPos_$eq(int x$1) {
            this.listPos = x$1;
         }

         private byte[] currArray() {
            return this.currArray;
         }

         private void currArray_$eq(byte[] x$1) {
            this.currArray = x$1;
         }

         public int read() {
            if (this.listPos() == -1) {
               this.currArray_$eq(this.nextArray());
            }

            if (this.currArray() == null) {
               return -1;
            } else {
               byte value = this.currArray()[this.pos()];
               this.pos_$eq(this.pos() + 1);
               if (this.pos() >= .MODULE$.byteArrayOps(this.currArray()).size()) {
                  this.currArray_$eq(this.nextArray());
                  this.pos_$eq(0);
               }

               return value;
            }
         }

         public void close() {
            this.$outer.io$pivotal$greenplum$spark$util$LargeByteBuffer$$bufferList().clear();
         }

         private byte[] nextArray() {
            do {
               if (this.listPos() >= 0 && this.listPos() < this.$outer.io$pivotal$greenplum$spark$util$LargeByteBuffer$$bufferList().size()) {
                  this.$outer.io$pivotal$greenplum$spark$util$LargeByteBuffer$$bufferList().set(this.listPos(), (Object)null);
               } else {
                  BoxedUnit var10000 = BoxedUnit.UNIT;
               }

               this.listPos_$eq(this.listPos() + 1);
            } while(this.listPos() < this.$outer.io$pivotal$greenplum$spark$util$LargeByteBuffer$$bufferList().size() && ((byte[])this.$outer.io$pivotal$greenplum$spark$util$LargeByteBuffer$$bufferList().get(this.listPos())).length == 0);

            return this.listPos() < this.$outer.io$pivotal$greenplum$spark$util$LargeByteBuffer$$bufferList().size() ? (byte[])this.$outer.io$pivotal$greenplum$spark$util$LargeByteBuffer$$bufferList().get(this.listPos()) : null;
         }

         public {
            if ($outer == null) {
               throw null;
            } else {
               this.$outer = $outer;
               this.pos = 0;
               this.listPos = -1;
            }
         }
      };
   }

   public Try<BoxedUnit> write(InputStream inputStream, int size) {
      return scala.util.Try..MODULE$.apply(new Serializable(this, inputStream, size) {
         public static final long serialVersionUID = 0L;
         // $FF: synthetic field
         private final LargeByteBuffer $outer;
         private final InputStream inputStream$1;
         private final int size$1;

         public final void apply() {
            this.apply$mcV$sp();
         }

         public void apply$mcV$sp() {
            byte[] buffer = new byte[this.size$1];
            int bytesCopied = IOUtils.read(this.inputStream$1, buffer);
            if (bytesCopied != this.size$1) {
               throw new IllegalStateException((new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{"Expected to read ", " bytes from stream, but read ", ""})))).s(.MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(this.size$1), BoxesRunTime.boxToInteger(bytesCopied)})));
            } else if (this.inputStream$1.read() != -1) {
               throw new IllegalStateException((new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{"Expected to read ", " bytes from stream, but the stream has more data"})))).s(.MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(this.size$1)})));
            } else {
               this.$outer.io$pivotal$greenplum$spark$util$LargeByteBuffer$$bufferList().add(buffer);
            }
         }

         public {
            if ($outer == null) {
               throw null;
            } else {
               this.$outer = $outer;
               this.inputStream$1 = inputStream$1;
               this.size$1 = size$1;
            }
         }
      });
   }
}
