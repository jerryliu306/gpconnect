package io.pivotal.greenplum.spark;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

public final class NetworkWrapper$ {
   public static final NetworkWrapper$ MODULE$;

   static {
      new NetworkWrapper$();
   }

   public InetAddress getInet4AddressByNetworkInterfaceName(String name) {
      NetworkInterface nic = NetworkInterface.getByName(name);
      if (nic != null && nic.getInetAddresses().hasMoreElements()) {
         Enumeration addresses = nic.getInetAddresses();

         while(addresses.hasMoreElements()) {
            InetAddress addr = (InetAddress)addresses.nextElement();
            if (addr instanceof Inet4Address) {
               return addr;
            }
         }
      }

      return null;
   }

   private NetworkWrapper$() {
      MODULE$ = this;
   }
}
