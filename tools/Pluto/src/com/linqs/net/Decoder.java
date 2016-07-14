package com.linqs.net;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoderAdapter;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

public class Decoder extends ProtocolDecoderAdapter {

	@Override
	public void decode(IoSession session, IoBuffer buffer, ProtocolDecoderOutput output)
			throws Exception {
		Object attrObject = session.getAttribute(ClientSession.SESSION_ATTR);
		ClientSession clientSession = (ClientSession)attrObject;
		if (clientSession != null) {
			IoBuffer inBuffer = clientSession.getInBuffer();
			if (inBuffer != null) {
				try {
					// 输入缓冲区接收数据
					inBuffer.put(buffer);
					inBuffer.flip();
	
					// 协议解码
					while (inBuffer.remaining() >= Pluto.HEADER_SIZE) {
						short plutoLength = inBuffer.getShort();
						if(plutoLength >= inBuffer.remaining()) {
							// 解码成协议返回
							byte[] bytes = new byte[plutoLength];
							inBuffer.get(bytes, 0, plutoLength);
							output.write(bytes);
						} else {
							break;
						}
					}
				} catch (Exception e) {
					// 协议解码异常
					e.printStackTrace();
				}

				// 缓冲区整理
				int pos = inBuffer.position();
				int remaining = inBuffer.remaining();
				inBuffer.clear();
				if (remaining > 0) {
					inBuffer.put(inBuffer.array(), pos, remaining);
				}
			}
		}
		
	}


}
