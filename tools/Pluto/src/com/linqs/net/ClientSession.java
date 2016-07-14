package com.linqs.net;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

public class ClientSession {

	public static String SESSION_ATTR = "sessionAttr";
	
	/**
	 * 实际会话对象
	 */
	protected IoSession session;
	/**
	 * 输入io缓冲区
	 */
	private IoBuffer inBuffer;
	/**
	 * 输出io缓冲区
	 */
	private IoBuffer outBuffer;
	
	public ClientSession(IoSession session) {
		this.session = session;
		setInBuffer(IoBuffer.allocate(1024).setAutoExpand(true));
		setOutBuffer(IoBuffer.allocate(1024).setAutoExpand(true));
	}

	public IoBuffer getInBuffer() {
		return inBuffer;
	}

	public void setInBuffer(IoBuffer inBuffer) {
		this.inBuffer = inBuffer;
	}

	public IoBuffer getOutBuffer() {
		return outBuffer;
	}

	public void setOutBuffer(IoBuffer outBuffer) {
		this.outBuffer = outBuffer;
	}
	
	public void send(byte[] bytes) {
		if(bytes == null || this.session == null || !this.session.isConnected()) {
			return ;
		}
		
		this.session.write(bytes);
	}
	
}
