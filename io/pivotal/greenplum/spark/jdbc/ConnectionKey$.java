package io.pivotal.greenplum.spark.jdbc;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.apache.commons.codec.digest.Crypt;
import scala.Option;
import scala.Serializable;
import scala.Some;
import scala.StringContext;
import scala.Tuple3;
import scala.Predef.;

public final class ConnectionKey$ implements Serializable {
   public static final ConnectionKey$ MODULE$;
   private final String salt;

   static {
      new ConnectionKey$();
   }

   private String salt() {
      return this.salt;
   }

   public ConnectionKey valueOf(String user, String url, String password) {
      return this.apply(user, url, this.hashPassword(password));
   }

   public ConnectionKey valueOf(String user, String url) {
      return this.apply(user, url, this.hashPassword(""));
   }

   public String hashPassword(String pass) {
      return Crypt.crypt(pass, (new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{"$6$", ""})))).s(.MODULE$.genericWrapArray(new Object[]{this.salt()})));
   }

   public ConnectionKey apply(String jdbcUrl, String userName, String hashedPassword) {
      return new ConnectionKey(jdbcUrl, userName, hashedPassword);
   }

   public Option<Tuple3<String, String, String>> unapply(ConnectionKey x$0) {
      return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple3(x$0.jdbcUrl(), x$0.userName(), x$0.hashedPassword())));
   }

   private Object readResolve() {
      return MODULE$;
   }

   private ConnectionKey$() {
      MODULE$ = this;
      SecureRandom sr = new SecureRandom();
      int saltLength = 16;
      int encodingBaseExponent = 4;
      int radix = (int)scala.math.package..MODULE$.pow(2.0D, (double)encodingBaseExponent);
      this.salt = (new BigInteger(encodingBaseExponent * saltLength, sr)).toString(radix);
   }
}
