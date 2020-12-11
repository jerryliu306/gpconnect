package io.pivotal.greenplum.spark.externaltable;

import com.typesafe.scalalogging.LazyLogging;
import com.typesafe.scalalogging.LazyLogging$class;
import com.typesafe.scalalogging.Logger;
import io.pivotal.greenplum.spark.GreenplumCSVFormat$;
import io.pivotal.greenplum.spark.util.TransactionData;
import java.io.PrintWriter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.spark.sql.Row;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Serializable;
import scala.Some;
import scala.StringContext;
import scala.collection.Iterator;
import scala.collection.TraversableOnce;
import scala.collection.immutable.List;
import scala.collection.mutable.StringBuilder;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.util.Failure;
import scala.util.Success;
import scala.util.Try;
import scala.util.Try.;

@ScalaSignature(
   bytes = "\u0006\u0001\u0005Md\u0001B\u0001\u0003\u00015\u0011ab\u00129gI&\u001cH\u000fS1oI2,'O\u0003\u0002\u0004\t\u0005iQ\r\u001f;fe:\fG\u000e^1cY\u0016T!!\u0002\u0004\u0002\u000bM\u0004\u0018M]6\u000b\u0005\u001dA\u0011!C4sK\u0016t\u0007\u000f\\;n\u0015\tI!\"A\u0004qSZ|G/\u00197\u000b\u0003-\t!![8\u0004\u0001M\u0019\u0001A\u0004\u000f\u0011\u0005=QR\"\u0001\t\u000b\u0005E\u0011\u0012a\u00025b]\u0012dWM\u001d\u0006\u0003'Q\taa]3sm\u0016\u0014(BA\u000b\u0017\u0003\u0015QW\r\u001e;z\u0015\t9\u0002$A\u0004fG2L\u0007o]3\u000b\u0003e\t1a\u001c:h\u0013\tY\u0002CA\bBEN$(/Y2u\u0011\u0006tG\r\\3s!\tiB%D\u0001\u001f\u0015\ty\u0002%\u0001\u0007tG\u0006d\u0017\r\\8hO&twM\u0003\u0002\"E\u0005AA/\u001f9fg\u00064WMC\u0001$\u0003\r\u0019w.\\\u0005\u0003Ky\u00111\u0002T1{s2{wmZ5oO\"Aq\u0005\u0001B\u0001B\u0003%\u0001&A\u0005ck\u001a4WM]'baB!\u0011F\f\u0019;\u001b\u0005Q#BA\u0016-\u0003\u0011)H/\u001b7\u000b\u00035\nAA[1wC&\u0011qF\u000b\u0002\u0004\u001b\u0006\u0004\bCA\u00198\u001d\t\u0011T'D\u00014\u0015\u0005!\u0014!B:dC2\f\u0017B\u0001\u001c4\u0003\u0019\u0001&/\u001a3fM&\u0011\u0001(\u000f\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005Y\u001a\u0004cA\u001e>\u007f5\tAH\u0003\u0002,g%\u0011a\b\u0010\u0002\u0004)JL\bC\u0001!C\u001b\u0005\t%BA\u0016\u0005\u0013\t\u0019\u0015IA\bUe\u0006t7/Y2uS>tG)\u0019;b\u0011!)\u0005A!A!\u0002\u00131\u0015!D:f]\u0012\u0014UO\u001a4fe6\u000b\u0007\u000f\u0005\u0003*]A:\u0005C\u0001%J\u001b\u0005\u0011\u0011B\u0001&\u0003\u00055\u0001\u0016M\u001d;ji&|g\u000eR1uC\")A\n\u0001C\u0001\u001b\u00061A(\u001b8jiz\"2AT(Q!\tA\u0005\u0001C\u0003(\u0017\u0002\u0007\u0001\u0006C\u0003F\u0017\u0002\u0007a\tC\u0003M\u0001\u0011\u0005!\u000bF\u0001O\u0011\u0015!\u0006\u0001\"\u0011V\u0003\u0019A\u0017M\u001c3mKR)a+W.b[B\u0011!gV\u0005\u00031N\u0012A!\u00168ji\")!l\u0015a\u0001a\u0005!\u0001/\u0019;i\u0011\u0015a6\u000b1\u0001^\u0003-\u0011\u0017m]3SKF,Xm\u001d;\u0011\u0005y{V\"\u0001\n\n\u0005\u0001\u0014\"a\u0002*fcV,7\u000f\u001e\u0005\u0006EN\u0003\raY\u0001\be\u0016\fX/Z:u!\t!7.D\u0001f\u0015\t1w-\u0001\u0003iiR\u0004(B\u00015j\u0003\u001d\u0019XM\u001d<mKRT\u0011A[\u0001\u0006U\u00064\u0018\r_\u0005\u0003Y\u0016\u0014!\u0003\u0013;uaN+'O\u001e7fiJ+\u0017/^3ti\")an\u0015a\u0001_\u0006A!/Z:q_:\u001cX\r\u0005\u0002ea&\u0011\u0011/\u001a\u0002\u0014\u0011R$\boU3sm2,GOU3ta>t7/\u001a\u0005\u0006g\u0002!I\u0001^\u0001\nQ\u0006tG\r\\3H\u000bR#2!\u001e<x!\rYTH\u0016\u0005\u00065J\u0004\r\u0001\r\u0005\u0006]J\u0004\ra\u001c\u0005\u0006s\u0002!IA_\u0001\u0015aJ|7-Z:t!\u0006\u0014H/\u001b;j_:$\u0015\r^1\u0015\u0007U\\H\u0010C\u0003oq\u0002\u0007q\u000eC\u0003[q\u0002\u0007\u0001\u0007C\u0003\u007f\u0001\u0011\u0005q0\u0001\u0007hKR\u0004\u0016M\u001d;ji&|g\u000e\u0006\u0004\u0002\u0002\u0005\u001d\u0011\u0011\u0002\t\u0005e\u0005\rq)C\u0002\u0002\u0006M\u0012aa\u00149uS>t\u0007\"\u0002.~\u0001\u0004\u0001\u0004BBA\u0006{\u0002\u0007a)A\u0002nCBDq!a\u0004\u0001\t\u0013\t\t\"A\u0005tKJ4X\rR1uCR)Q/a\u0005\u0002\u0016!1a.!\u0004A\u0002=Dq!a\u0006\u0002\u000e\u0001\u0007q)A\u0007qCJ$\u0018\u000e^5p]\u0012\u000bG/\u0019\u0005\b\u00037\u0001A\u0011BA\u000f\u0003)A\u0017M\u001c3mKB{5\u000b\u0016\u000b\b-\u0006}\u0011\u0011EA\u0012\u0011\u0019Q\u0016\u0011\u0004a\u0001a!1!-!\u0007A\u0002\rDaA\\A\r\u0001\u0004y\u0007bBA\u0014\u0001\u0011%\u0011\u0011F\u0001\u000fe><Hk\\\"T-N#(/\u001b8h)\r\u0001\u00141\u0006\u0005\t\u0003[\t)\u00031\u0001\u00020\u0005\u0019!o\\<\u0011\t\u0005E\u0012QH\u0007\u0003\u0003gQA!!\u000e\u00028\u0005\u00191/\u001d7\u000b\u0007\u0015\tIDC\u0002\u0002<a\ta!\u00199bG\",\u0017\u0002BA \u0003g\u00111AU8x\u0011\u001d\t\u0019\u0005\u0001C\u0005\u0003\u000b\nA\u0002\u001d:pG\u0016\u001c8/\u0012:s_J$2BVA$\u0003\u0013\nY%!\u0014\u0002X!1!,!\u0011A\u0002ABaAYA!\u0001\u0004\u0019\u0007B\u00028\u0002B\u0001\u0007q\u000e\u0003\u0005\u0002P\u0005\u0005\u0003\u0019AA)\u0003\u0011\u0019w\u000eZ3\u0011\u0007I\n\u0019&C\u0002\u0002VM\u00121!\u00138u\u0011!\tI&!\u0011A\u0002\u0005m\u0013!A3\u0011\t\u0005u\u0013Q\u000e\b\u0005\u0003?\nIG\u0004\u0003\u0002b\u0005\u001dTBAA2\u0015\r\t)\u0007D\u0001\u0007yI|w\u000e\u001e \n\u0003QJ1!a\u001b4\u0003\u001d\u0001\u0018mY6bO\u0016LA!a\u001c\u0002r\tIA\u000b\u001b:po\u0006\u0014G.\u001a\u0006\u0004\u0003W\u001a\u0004"
)
public class GpfdistHandler extends AbstractHandler implements LazyLogging {
   private final Map<String, Try<TransactionData>> bufferMap;
   private final Map<String, PartitionData> sendBufferMap;
   private final transient Logger logger;
   private transient volatile boolean bitmap$trans$0;

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

   public void handle(String path, Request baseRequest, HttpServletRequest request, HttpServletResponse response) {
      String var6 = request.getMethod();
      Object var7;
      if ("GET".equals(var6)) {
         var7 = this.handleGET(path, response);
      } else if ("POST".equals(var6)) {
         var7 = .MODULE$.apply(new Serializable(this, path, request, response) {
            public static final long serialVersionUID = 0L;
            // $FF: synthetic field
            private final GpfdistHandler $outer;
            private final String path$1;
            private final HttpServletRequest request$1;
            private final HttpServletResponse response$1;

            public final void apply() {
               this.apply$mcV$sp();
            }

            public void apply$mcV$sp() {
               this.$outer.io$pivotal$greenplum$spark$externaltable$GpfdistHandler$$handlePOST(this.path$1, this.request$1, this.response$1);
            }

            public {
               if ($outer == null) {
                  throw null;
               } else {
                  this.$outer = $outer;
                  this.path$1 = path$1;
                  this.request$1 = request$1;
                  this.response$1 = response$1;
               }
            }
         });
      } else {
         var7 = new Failure(new WebException(405, (new StringContext(scala.Predef..MODULE$.wrapRefArray((Object[])(new String[]{"Method ", " is not supported"})))).s(scala.Predef..MODULE$.genericWrapArray(new Object[]{request.getMethod()})), WebException$.MODULE$.apply$default$3()));
      }

      BoxedUnit var10000;
      if (var7 instanceof Success) {
         response.setStatus(200);
         if (this.logger().underlying().isDebugEnabled()) {
            this.logger().underlying().debug("Successfully handled {} request for {}", (Object[])((Object[])(new String[]{request.getMethod(), path})));
            var10000 = BoxedUnit.UNIT;
         } else {
            var10000 = BoxedUnit.UNIT;
         }
      } else {
         if (!(var7 instanceof Failure)) {
            throw new MatchError(var7);
         }

         Failure var10 = (Failure)var7;
         Throwable e = var10.exception();
         int var16;
         if (e instanceof WebException) {
            WebException var14 = (WebException)e;
            int c = var14.code();
            var16 = c;
         } else {
            var16 = 500;
         }

         if (this.logger().underlying().isDebugEnabled()) {
            e.printStackTrace();
            var10000 = BoxedUnit.UNIT;
         } else {
            var10000 = BoxedUnit.UNIT;
         }

         this.processError(path, request, response, var16, e);
         BoxedUnit var9 = BoxedUnit.UNIT;
      }

      baseRequest.setHandled(true);
   }

   private Try<BoxedUnit> handleGET(String path, HttpServletResponse response) {
      BoxedUnit var10000;
      if (this.logger().underlying().isDebugEnabled()) {
         this.logger().underlying().debug("Processing GET for {}", path);
         var10000 = BoxedUnit.UNIT;
      } else {
         var10000 = BoxedUnit.UNIT;
      }

      return this.processPartitionData(response, path);
   }

   private Try<BoxedUnit> processPartitionData(HttpServletResponse response, String path) {
      Option var3 = this.getPartition(path, this.sendBufferMap);
      Object var4;
      if (scala.None..MODULE$.equals(var3)) {
         var4 = new Failure(new WebException(400, (new StringContext(scala.Predef..MODULE$.wrapRefArray((Object[])(new String[]{"no data available for ", ""})))).s(scala.Predef..MODULE$.genericWrapArray(new Object[]{path})), WebException$.MODULE$.apply$default$3()));
      } else {
         if (!(var3 instanceof Some)) {
            throw new MatchError(var3);
         }

         Some var5 = (Some)var3;
         PartitionData partitionData = (PartitionData)var5.x();
         var4 = partitionData.handled().compareAndSet(false, true) ? this.serveData(response, partitionData) : new Success(BoxedUnit.UNIT);
      }

      return (Try)var4;
   }

   public Option<PartitionData> getPartition(String path, Map<String, PartitionData> map) {
      boolean var4;
      if (path == null) {
         var4 = true;
      } else if ("".equals(path)) {
         var4 = true;
      } else if ("/".equals(path)) {
         var4 = true;
      } else {
         var4 = false;
      }

      Object var5;
      if (var4) {
         var5 = scala.None..MODULE$;
      } else {
         var5 = scala.Option..MODULE$.apply(map.get(scala.collection.immutable.StringOps..MODULE$.slice$extension(scala.Predef..MODULE$.augmentString(path), 1, path.length())));
      }

      return (Option)var5;
   }

   private Try<BoxedUnit> serveData(HttpServletResponse response, PartitionData partitionData) {
      return .MODULE$.apply(new Serializable(this, response, partitionData) {
         public static final long serialVersionUID = 0L;
         // $FF: synthetic field
         private final GpfdistHandler $outer;
         private final HttpServletResponse response$2;
         private final PartitionData partitionData$1;

         public final void apply() {
            this.apply$mcV$sp();
         }

         public void apply$mcV$sp() {
            this.response$2.setContentType("text/plain");
            this.response$2.setCharacterEncoding(GreenplumCSVFormat$.MODULE$.DEFAULT_ENCODING());
            PrintWriter writer = this.response$2.getWriter();
            Function1 reorder = this.partitionData$1.rowTransformer();
            if (this.partitionData$1.rowIterator() == null) {
               ((List)this.partitionData$1.rows().map(reorder, scala.collection.immutable.List..MODULE$.canBuildFrom())).foreach(new Serializable(this, writer) {
                  public static final long serialVersionUID = 0L;
                  // $FF: synthetic field
                  private final <undefinedtype> $outer;
                  private final PrintWriter writer$1;

                  public final void apply(Row row) {
                     this.writer$1.println(this.$outer.io$pivotal$greenplum$spark$externaltable$GpfdistHandler$$anonfun$$$outer().io$pivotal$greenplum$spark$externaltable$GpfdistHandler$$rowToCSVString(row));
                  }

                  public {
                     if ($outer == null) {
                        throw null;
                     } else {
                        this.$outer = $outer;
                        this.writer$1 = writer$1;
                     }
                  }
               });
               this.partitionData$1.rows_$eq((List)null);
            } else {
               Iterator iterator = this.partitionData$1.rowIterator();

               while(iterator.hasNext()) {
                  writer.println(this.$outer.io$pivotal$greenplum$spark$externaltable$GpfdistHandler$$rowToCSVString((Row)reorder.apply(iterator.next())));
               }
            }

            writer.flush();
         }

         // $FF: synthetic method
         public GpfdistHandler io$pivotal$greenplum$spark$externaltable$GpfdistHandler$$anonfun$$$outer() {
            return this.$outer;
         }

         public {
            if ($outer == null) {
               throw null;
            } else {
               this.$outer = $outer;
               this.response$2 = response$2;
               this.partitionData$1 = partitionData$1;
            }
         }
      });
   }

   public void io$pivotal$greenplum$spark$externaltable$GpfdistHandler$$handlePOST(String path, HttpServletRequest request, HttpServletResponse response) {
      BoxedUnit var10000;
      if (this.logger().underlying().isDebugEnabled()) {
         this.logger().underlying().debug("Processing POST for {}", path);
         var10000 = BoxedUnit.UNIT;
      } else {
         var10000 = BoxedUnit.UNIT;
      }

      boolean var4 = false;
      GpfdistPostRequest.ValidRequest var5 = null;
      GpfdistPostRequest.RequestValidation var6 = GpfdistPostRequest$.MODULE$.validate(request);
      if (var6 instanceof GpfdistPostRequest.InvalidRequest) {
         GpfdistPostRequest.InvalidRequest var7 = (GpfdistPostRequest.InvalidRequest)var6;
         String errorMessage = var7.message();
         int statusCode = var7.statusCode();
         throw new WebException(statusCode, errorMessage, WebException$.MODULE$.apply$default$3());
      } else {
         BoxedUnit var10;
         if (var6 instanceof GpfdistPostRequest.ValidRequest) {
            var4 = true;
            var5 = (GpfdistPostRequest.ValidRequest)var6;
            int var11 = var5.contentLength();
            if (0 == var11) {
               var10 = BoxedUnit.UNIT;
               return;
            }
         }

         if (!var4) {
            throw new MatchError(var6);
         } else {
            int contentLength = var5.contentLength();
            String transactionId = var5.transactionId();
            int segmentId = var5.segmentId();
            Option var15 = scala.Option..MODULE$.apply(request.getInputStream());
            if (scala.None..MODULE$.equals(var15)) {
               if (this.logger().underlying().isWarnEnabled()) {
                  this.logger().underlying().warn("Received POST request with no body");
                  var10000 = BoxedUnit.UNIT;
               } else {
                  var10000 = BoxedUnit.UNIT;
               }

               throw new WebException(400, WebException$.MODULE$.apply$default$2(), WebException$.MODULE$.apply$default$3());
            } else if (!(var15 instanceof Some)) {
               throw new MatchError(var15);
            } else {
               Some var17 = (Some)var15;
               ServletInputStream requestInputStream = (ServletInputStream)var17.x();
               this.bufferMap.putIfAbsent(transactionId, new Success(new TransactionData()));
               Try var19 = (Try)this.bufferMap.get(transactionId);
               if (var19 instanceof Failure) {
                  throw new WebException(500, WebException$.MODULE$.apply$default$2(), WebException$.MODULE$.apply$default$3());
               } else if (!(var19 instanceof Success)) {
                  throw new MatchError(var19);
               } else {
                  Success var21 = (Success)var19;
                  TransactionData txData = (TransactionData)var21.value();
                  Try var23 = txData.write(segmentId, requestInputStream, contentLength);
                  if (var23 instanceof Failure) {
                     Failure var24 = (Failure)var23;
                     Throwable exception = var24.exception();
                     this.bufferMap.put(transactionId, new Failure(exception));
                     throw new WebException(400, exception.getMessage(), WebException$.MODULE$.apply$default$3());
                  } else {
                     BoxedUnit var26 = BoxedUnit.UNIT;
                     BoxedUnit var20 = BoxedUnit.UNIT;
                     BoxedUnit var16 = BoxedUnit.UNIT;
                     var10 = BoxedUnit.UNIT;
                  }
               }
            }
         }
      }
   }

   public String io$pivotal$greenplum$spark$externaltable$GpfdistHandler$$rowToCSVString(Row row) {
      return ((TraversableOnce)row.toSeq().map(new Serializable(this) {
         public static final long serialVersionUID = 0L;

         public final Object apply(Object x0$1) {
            Object var3;
            if (x0$1 == null) {
               var3 = GreenplumCSVFormat$.MODULE$.VALUE_OF_NULL();
            } else if (x0$1 instanceof String) {
               String var4 = (String)x0$1;
               var3 = (new StringBuilder()).append(GreenplumCSVFormat$.MODULE$.QUOTE()).append(var4.replace(GreenplumCSVFormat$.MODULE$.QUOTE_STRING(), GreenplumCSVFormat$.MODULE$.ESCAPED_QUOTE())).append(BoxesRunTime.boxToCharacter(GreenplumCSVFormat$.MODULE$.QUOTE())).toString();
            } else {
               var3 = x0$1;
            }

            return var3;
         }
      }, scala.collection.Seq..MODULE$.canBuildFrom())).mkString(BoxesRunTime.boxToCharacter(GreenplumCSVFormat$.MODULE$.CHAR_DELIMITER()).toString());
   }

   private void processError(String path, HttpServletRequest request, HttpServletResponse response, int code, Throwable e) {
      BoxedUnit var10000;
      if (this.logger().underlying().isErrorEnabled()) {
         this.logger().underlying().error("Failed to handle {} request for {} : {}", request.getMethod(), path, e.toString());
         var10000 = BoxedUnit.UNIT;
      } else {
         var10000 = BoxedUnit.UNIT;
      }

      response.setStatus(code);
      if (e != null && e.getMessage() != null) {
         response.getWriter().write(e.getMessage());
      }

   }

   public GpfdistHandler(Map<String, Try<TransactionData>> bufferMap, Map<String, PartitionData> sendBufferMap) {
      this.bufferMap = bufferMap;
      this.sendBufferMap = sendBufferMap;
      LazyLogging$class.$init$(this);
   }

   public GpfdistHandler() {
      this(new ConcurrentHashMap(), new ConcurrentHashMap());
   }
}
