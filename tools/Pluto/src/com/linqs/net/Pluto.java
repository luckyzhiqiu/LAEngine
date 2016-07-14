package com.linqs.net;

import java.nio.ByteOrder;
import java.nio.charset.Charset;

import org.apache.mina.core.buffer.IoBuffer;

/**
 * 数据包编解码处理类
 * @author linqs
 *
 */
public class Pluto {
	
	
	public static final int HEADER_SIZE = 2;
	
	public static final int RESERVE_SIZE = 2;
	
	private final int DEFAULT_PLUTO_BUFF_SIZE = 1024;
	
	protected Byte[] m_szBuff;
    protected int m_unLen;
    private int m_unMaxLen;
    
    protected IoBuffer ioBuffer = IoBuffer.allocate(DEFAULT_PLUTO_BUFF_SIZE);
	
    public Pluto() {
    	ioBuffer.order(ByteOrder.LITTLE_ENDIAN);
    }
    
	/**
	 * 简易加密工具
	 */
	protected static BitCryto encryto = new BitCryto(new short[] {15, 180, 213, 37, 40, 98, 85, 7, 152, 223, 48, 168, 71, 102, 191, 194});

	public static Pluto decode(byte[] data) {
		
		return null;
	}
	
	 protected void push(byte[] data)
     {
         if (m_unLen + data.length > m_unMaxLen)
         {
             m_unMaxLen = m_unMaxLen + DEFAULT_PLUTO_BUFF_SIZE;
             Byte[] newArray = new Byte[m_unMaxLen];
             System.arraycopy(m_szBuff, 0, newArray, 0, m_unLen);
             m_szBuff = newArray;
         }
         System.arraycopy(data, 0, m_szBuff, m_unLen, data.length);
         m_unLen += data.length;
     }
	 
	 protected byte[] endPluto() {
		 encryto.Reset();
		 byte[] bytes = new byte[ioBuffer.position()];
		 ioBuffer.flip();
		 ioBuffer.get(bytes);
         for (int i = 2; i < bytes.length; i++)
         {
             bytes[i] = encryto.Encode(bytes[i]);
         }
         return bytes;
	 }
	 
	 protected void pushString(String val) {
		 byte[] bytes = val.getBytes(Charset.forName("UTF8"));
		 ioBuffer.putShort((short)bytes.length);
		 ioBuffer.put(bytes);
	 }
	
}
