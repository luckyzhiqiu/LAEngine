package com.linqs.net;

import java.net.InetSocketAddress;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.SocketConnector;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

/**
 * 客户端连接代理
 * @author john
 *
 */
public class ClientProxy {
	
	private SocketConnector connector;
	
	private IoSession session ;
	
	public void connect(String ip, int port) {
		if(connector == null) {
			connector = new NioSocketConnector();
			connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(Encoder.class, Decoder.class));
			connector.setHandler(new ClientIoHandler());
		}
		
		if(session != null && session.isConnected()) {
			session.close(true);
		}
		
		ConnectFuture future = connector.connect(new InetSocketAddress(ip, port));
		future.awaitUninterruptibly();
		session = future.getSession();
		if(session != null) {
			
		}
	}

}
