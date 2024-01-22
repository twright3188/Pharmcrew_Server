package com.bumdori.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

public class NetworkUtils {
    public static String getIp() {
        try {
            InetAddress local = InetAddress.getLocalHost();
            return local.getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
//		try {
//			InetAddress address = getAddress();
//			return address.getHostAddress();
//		} catch (SocketException e) {
//			e.printStackTrace();
//		}

        return null;
    }

    public static String getHost() {
        try {
            InetAddress local = InetAddress.getLocalHost();
            return local.getHostName();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
//		try {
//			InetAddress address = getAddress();
//			return address.getHostName();
//		} catch (SocketException e) {
//			e.printStackTrace();
//		}

        return null;
    }

    // https://stackoverflow.com/questions/1881546/inetaddress-getlocalhost-throws-unknownhostexception
    private static InetAddress getAddress() throws SocketException {
        Enumeration<NetworkInterface> iterNetwork;
        Enumeration<InetAddress> iterAddress;
        NetworkInterface network;
        InetAddress address;

        iterNetwork = NetworkInterface.getNetworkInterfaces();

        while (iterNetwork.hasMoreElements())
        {
            network = iterNetwork.nextElement();

            if (!network.isUp())
                continue;

            if (network.isLoopback())  // If I want loopback, I would use "localhost" or "127.0.0.1".
                continue;

            iterAddress = network.getInetAddresses();

            while (iterAddress.hasMoreElements())
            {
                address = iterAddress.nextElement();
//		     System.out.println("host: " + address.getHostName() + ", address: " + address.getHostAddress());

                if (address.isAnyLocalAddress())
                    continue;

                if (address.isLoopbackAddress())
                    continue;

                if (address.isMulticastAddress())
                    continue;

//		     return(address.getHostAddress());
                return address;
            }
        }

        return null;
    }
}
