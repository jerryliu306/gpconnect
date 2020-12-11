package io.pivotal.greenplum.spark;

import scala.Product;
import scala.Serializable;
import scala.Product.class;
import scala.collection.Iterator;
import scala.collection.immutable.StringOps;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime.;

public final class BuildInfo$ implements Product, Serializable {
   public static final BuildInfo$ MODULE$;
   private final String name;
   private final String version;
   private final String scalaVersion;
   private final String sbtVersion;
   private final String toString;

   static {
      new BuildInfo$();
   }

   public String name() {
      return this.name;
   }

   public String version() {
      return this.version;
   }

   public String scalaVersion() {
      return this.scalaVersion;
   }

   public String sbtVersion() {
      return this.sbtVersion;
   }

   public String toString() {
      return this.toString;
   }

   public String productPrefix() {
      return "BuildInfo";
   }

   public int productArity() {
      return 0;
   }

   public Object productElement(int x$1) {
      throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
   }

   public Iterator<Object> productIterator() {
      return .MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(Object x$1) {
      return x$1 instanceof BuildInfo$;
   }

   public int hashCode() {
      return 602658844;
   }

   private Object readResolve() {
      return MODULE$;
   }

   private BuildInfo$() {
      MODULE$ = this;
      class.$init$(this);
      this.name = "greenplum-spark";
      this.version = "2.1.0";
      this.scalaVersion = "2.11.12";
      this.sbtVersion = "1.4.1";
      this.toString = (new StringOps(scala.Predef..MODULE$.augmentString("name: %s, version: %s, scalaVersion: %s, sbtVersion: %s"))).format(scala.Predef..MODULE$.genericWrapArray(new Object[]{this.name(), this.version(), this.scalaVersion(), this.sbtVersion()}));
   }
}
