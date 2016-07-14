package com.linqs.net;

import java.net.SocketAddress;

import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.linqs.logic.login.LoginPluto;

public class ClientIoHandler implements IoHandler {

	private static Logger logger = LoggerFactory.getLogger("Debug");

	@Override
	public void exceptionCaught(IoSession arg0, Throwable throwable)
			throws Exception {
		throwable.printStackTrace();
	}

	@Override
	public void messageReceived(IoSession arg0, Object arg1) throws Exception {

	}

	@Override
	public void messageSent(IoSession arg0, Object arg1) throws Exception {

	}

	@Override
	public void sessionClosed(IoSession arg0) throws Exception {

	}

	@Override
	public void sessionCreated(IoSession arg0) throws Exception {

	}

	@Override
	public void sessionIdle(IoSession arg0, IdleStatus arg1) throws Exception {

	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		SocketAddress address = session.getRemoteAddress();
		logger.info("connect success : " + address.toString());

		ClientSession clientSession = new ClientSession(session);
		session.setAttribute(ClientSession.SESSION_ATTR, clientSession);

		LoginPluto loginPluto = (LoginPluto) LoginPluto.Create();
		clientSession.send(loginPluto.Encode("111", "111", "111"));
	}

}
