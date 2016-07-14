package com.linqs;

import com.linqs.net.ClientProxy;

public class ClientMain {

	public static void main(String[] args) {
		ClientProxy proxy = new ClientProxy();
		proxy.connect("192.168.231.128" , 8005);
	}
	
}	
