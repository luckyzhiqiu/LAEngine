package com.linqs.net;

import java.nio.ByteOrder;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

public class Encoder extends ProtocolEncoderAdapter {

	@Override
	public void encode(IoSession session, Object params, ProtocolEncoderOutput output)
			throws Exception {
		if(params instanceof byte[]) {
			byte[] bytes =(byte[])(params);
			IoBuffer buff = IoBuffer.allocate(bytes.length + 6);
			buff.order(ByteOrder.LITTLE_ENDIAN);
			buff.putInt(bytes.length + Pluto.HEADER_SIZE + Pluto.RESERVE_SIZE);
			buff.putShort((short)0);
			buff.put(bytes);
			buff.flip();
			output.write(buff);
		}
	}

}
