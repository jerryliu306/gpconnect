package io.pivotal.greenplum.spark.conf;

import scala.Product;
import scala.Serializable;
import scala.Product.class;
import scala.collection.Iterator;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime.;

public final class ErrorIfMissing$ implements WhatIfMissing, Product, Serializable {
   public static final ErrorIfMissing$ MODULE$;

   static {
      new ErrorIfMissing$();
   }

   public String productPrefix() {
      return "ErrorIfMissing";
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
      return x$1 instanceof ErrorIfMissing$;
   }

   public int hashCode() {
      return 709649537;
   }

   public String toString() {
      return "ErrorIfMissing";
   }

   private Object readResolve() {
      return MODULE$;
   }

   private ErrorIfMissing$() {
      MODULE$ = this;
      class.$init$(this);
   }
}
