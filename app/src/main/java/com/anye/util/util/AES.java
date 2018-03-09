package com.anye.util.util;

import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

import Decoder.BASE64Decoder;
import Decoder.BASE64Encoder;


public class AES {
	public static byte[] createKey()
		throws Exception
	{
		KeyGenerator keygen = KeyGenerator.getInstance("AES");
		SecureRandom random = new SecureRandom();
		keygen.init(128);
		Key key = keygen.generateKey();
		byte[] keyBytes = key.getEncoded();
		return keyBytes;
	}

	public static String encrypt(byte[] aesKeyBytes, byte[] text)
		throws Exception
	{
        SecretKeySpec key = new SecretKeySpec(aesKeyBytes, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        BASE64Encoder encode = new BASE64Encoder();
        return encode.encode(cipher.doFinal(text));
	}

	public static byte[] decrypt(byte[] aesKeyBytes, String encryptData)
		throws Exception
	{
		BASE64Decoder decode = new BASE64Decoder();
		byte[] dataBytes = decode.decodeBuffer(encryptData);
//				DatatypeConverter.parseBase64Binary(encryptData);
        SecretKeySpec key = new SecretKeySpec(aesKeyBytes, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, key);
		return  cipher.doFinal(dataBytes);
	}

    public static void main(String[] args) throws Exception {
		AES aes = new AES();
		byte[] aesKeyBytes = aes.createKey();
		System.out.println(aesKeyBytes.length);

		//AES��ԿRSA ���ܺ���
		
		String encryptData = aes.encrypt(aesKeyBytes,"hello!".getBytes());
		System.out.println(encryptData);
		byte[] decryptBytes = aes.decrypt(aesKeyBytes,encryptData);
		System.out.println(new String(decryptBytes));
    }
}

