package io.pivotal.greenplum.spark.externaltable;

import scala.Enumeration;
import scala.Enumeration.Value;

public final class GpfdistServiceState$ extends Enumeration {
   public static final GpfdistServiceState$ MODULE$;
   private final Value Stopped;
   private final Value Failed;
   private final Value Starting;
   private final Value Started;
   private final Value Stopping;
   private final Value Running;

   static {
      new GpfdistServiceState$();
   }

   public Value Stopped() {
      return this.Stopped;
   }

   public Value Failed() {
      return this.Failed;
   }

   public Value Starting() {
      return this.Starting;
   }

   public Value Started() {
      return this.Started;
   }

   public Value Stopping() {
      return this.Stopping;
   }

   public Value Running() {
      return this.Running;
   }

   private GpfdistServiceState$() {
      MODULE$ = this;
      this.Stopped = this.Value();
      this.Failed = this.Value();
      this.Starting = this.Value();
      this.Started = this.Value();
      this.Stopping = this.Value();
      this.Running = this.Value();
   }
}
