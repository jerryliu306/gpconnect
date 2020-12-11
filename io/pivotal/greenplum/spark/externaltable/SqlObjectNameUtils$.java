package io.pivotal.greenplum.spark.externaltable;

import scala.StringContext;
import scala.Predef.;
import scala.collection.immutable.StringOps;

public final class SqlObjectNameUtils$ {
   public static final SqlObjectNameUtils$ MODULE$;

   static {
      new SqlObjectNameUtils$();
   }

   public String escape(String s) {
      return (new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{"\"", "\""})))).s(.MODULE$.genericWrapArray(new Object[]{(new StringOps(.MODULE$.augmentString(s))).replaceAllLiterally("\"", "\"\"")}));
   }

   private SqlObjectNameUtils$() {
      MODULE$ = this;
   }
}
