package com.linqs.logic.login;

import com.linqs.logic.MSGIDType;
import com.linqs.net.Pluto;

public class LoginPluto extends Pluto
{
    /// <summary>
    /// 将远程方法调用编码为二进制数组。
    /// </summary>
    /// <param name="passport">登录帐号</param>
    /// <param name="password">登录密码</param>
    /// <param name="loginArgs">登录类型参数</param>
    /// <returns>编码后的二进制数组</returns>
    public byte[] Encode(String passport, String password, String loginArgs)
    {
    	ioBuffer.putShort((short)MSGIDType.LOGINAPP_LOGIN);
        pushString(passport);
        pushString(password);
        pushString(loginArgs);
        return endPluto();
    }

    /// <summary>
    /// 将远程方法调用编码为二进制数组。
    /// </summary>
    /// <returns>编码后的二进制数组</returns>
	public byte[] Encode(String[] args)
    {
		ioBuffer.putShort((short)MSGIDType.LOGINAPP_LOGIN);
        for (String item : args)
        {
        	pushString(item);
        }
        return endPluto();
    }

    /// <summary>
    /// 将远程调用的方法解码为Login调用。
    /// </summary>
    /// <param name="data">远程调用方法的二进制数组</param>
    /// <param name="unLen">数据偏移量</param>
    protected void DoDecode(byte[] data)
    {
    }

    public void HandleData()
    {
    }

    /// <summary>
    /// 创建新LoginPluto实例。
    /// </summary>
    /// <returns>LoginPluto实例</returns>
    public static Pluto Create()
    {
        return new LoginPluto();
    }
}