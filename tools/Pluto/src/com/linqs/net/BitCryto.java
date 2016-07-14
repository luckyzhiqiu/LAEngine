package com.linqs.net;

public class BitCryto {
	private short[] crytoKey;
	private int offsetOfKey;

	public BitCryto(short[] sKey) {
		crytoKey = sKey;
	}

	public byte Encode(byte inputByte) {
		if (offsetOfKey >= crytoKey.length) {
			offsetOfKey = 0;
		}
		short offset = (short) crytoKey[offsetOfKey];
		++offsetOfKey;

		byte outputByte = (byte) ((offset + (short) inputByte) & 0xff);
		return outputByte;
	}

	public byte Decode(byte inputByte) {
		if (offsetOfKey >= crytoKey.length) {
			offsetOfKey = 0;
		}
		short offset = (short) crytoKey[offsetOfKey];
		++offsetOfKey;

		short outputByte = (short) ((short) inputByte - offset);
		if (outputByte < 0) {
			outputByte += 256;
		}
		return (byte) outputByte;
	}

	public void Reset() {
		offsetOfKey = 0;
	}

}
