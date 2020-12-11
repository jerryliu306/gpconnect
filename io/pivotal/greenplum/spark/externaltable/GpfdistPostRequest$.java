package io.pivotal.greenplum.spark.externaltable;

import javax.servlet.http.HttpServletRequest;
import scala.Serializable;
import scala.StringContext;
import scala.Predef.;
import scala.collection.immutable.List;
import scala.collection.immutable.StringOps;
import scala.runtime.NonLocalReturnControl;

public final class GpfdistPostRequest$ {
   public static final GpfdistPostRequest$ MODULE$;
   private final String DISTRIBUTED_TRANSACTION_ID_HEADER;
   private final String SEGMENT_ID_HEADER;
   private final String SEGMENT_COUNT_HEADER;
   private final List<String> requiredHeaders;

   static {
      new GpfdistPostRequest$();
   }

   public GpfdistPostRequest.RequestValidation validate(HttpServletRequest request) {
      Object var2 = new Object();

      Object var10000;
      try {
         if (request.getContentLength() < 0) {
            return new GpfdistPostRequest.InvalidRequest("Size of request is too large", 413);
         }

         this.requiredHeaders().foreach(new Serializable(request, var2) {
            public static final long serialVersionUID = 0L;
            private final HttpServletRequest request$1;
            private final Object nonLocalReturnKey1$1;

            public final void apply(String header) {
               String headerValue = this.request$1.getHeader(header);
               if (headerValue == null) {
                  throw new NonLocalReturnControl(this.nonLocalReturnKey1$1, new GpfdistPostRequest.InvalidRequest((new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{"", " header is required for a request"})))).s(.MODULE$.genericWrapArray(new Object[]{header})), 400));
               }
            }

            public {
               this.request$1 = request$1;
               this.nonLocalReturnKey1$1 = nonLocalReturnKey1$1;
            }
         });
         var10000 = new GpfdistPostRequest.ValidRequest(request.getContentLength(), request.getHeader(this.DISTRIBUTED_TRANSACTION_ID_HEADER()), (new StringOps(.MODULE$.augmentString(request.getHeader(this.SEGMENT_ID_HEADER())))).toInt());
      } catch (NonLocalReturnControl var4) {
         if (var4.key() != var2) {
            throw var4;
         }

         var10000 = (GpfdistPostRequest.RequestValidation)var4.value();
      }

      return (GpfdistPostRequest.RequestValidation)var10000;
   }

   public String DISTRIBUTED_TRANSACTION_ID_HEADER() {
      return this.DISTRIBUTED_TRANSACTION_ID_HEADER;
   }

   public String SEGMENT_ID_HEADER() {
      return this.SEGMENT_ID_HEADER;
   }

   public String SEGMENT_COUNT_HEADER() {
      return this.SEGMENT_COUNT_HEADER;
   }

   private List<String> requiredHeaders() {
      return this.requiredHeaders;
   }

   private GpfdistPostRequest$() {
      MODULE$ = this;
      this.DISTRIBUTED_TRANSACTION_ID_HEADER = "X-GP-XID";
      this.SEGMENT_ID_HEADER = "X-GP-SEGMENT-ID";
      this.SEGMENT_COUNT_HEADER = "X-GP-SEGMENT-COUNT";
      this.requiredHeaders = scala.collection.immutable.List..MODULE$.apply(.MODULE$.wrapRefArray((Object[])(new String[]{this.DISTRIBUTED_TRANSACTION_ID_HEADER(), this.SEGMENT_ID_HEADER()})));
   }
}
