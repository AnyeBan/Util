package com.anye.util.util;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

import javax.crypto.Cipher;

import Decoder.BASE64Decoder;
import Decoder.BASE64Encoder;


public class RSA
{
	private static String modulus = "124154491807379356527297196116244187398273152668522158815656983660125289855679391879147158541293195275003868038178130285773496115879686500212701890526773738804744344079377128286954164101119976122831174092529201953258836547899462381646328232737663365272773821547834721606029953887435356969122008097322807069021";
	private static String publicExponent = "65537";

	public static PublicKey getPublicKey(String modulus, String publicExponent) throws Exception
	{
		BigInteger m = new BigInteger(modulus);
		BigInteger e = new BigInteger(publicExponent);
		RSAPublicKeySpec keySpec = new RSAPublicKeySpec(m,e);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PublicKey publicKey = keyFactory.generatePublic(keySpec);
		return publicKey;  
	}
	public static PrivateKey getPrivateKey(String modulus, String privateExponent) throws Exception
    {
		BigInteger m = new BigInteger(modulus);
		BigInteger e = new BigInteger(privateExponent);
		RSAPrivateKeySpec keySpec = new RSAPrivateKeySpec(m,e);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
		return privateKey; 
    }



	public static String encrypt(PublicKey publicKey, byte[] text)
		throws Exception
	{
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
//		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm(),new org.bouncycastle.jce.provider.BouncyCastleProvider());//����RSA/ECB/PKCS1Padding
		Cipher cipher = Cipher.getInstance("RSA/None/PKCS1Padding");//����RSA/ECB/PKCS1Padding
		cipher.init(Cipher.ENCRYPT_MODE, getPublicKey(modulus,
				publicExponent));
		BASE64Encoder encode = new BASE64Encoder();
        return encode.encode(cipher.doFinal(text));
//		return  DatatypeConverter.printBase64Binary(cipher.doFinal(text));
	}

	public static byte[] decrypt(PrivateKey privateKey, String encryptData)
    throws Exception
	{
		BASE64Decoder decode = new BASE64Decoder();
		byte[] dataBytes = decode.decodeBuffer(encryptData);
//				DatatypeConverter.parseBase64Binary(encryptData);
//		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		return cipher.doFinal(dataBytes);
      
	}

    public static void main(String[] args) throws Exception
    { 
		String modulus = "101061486198273384457448453703347305151538422326515658136562573484677619037885542942192101969880732297336504913442660267394247766552731511609084324643018962502376954999237417767434814875309432636797721037246208605270187728090800533984171457052569419053009491400900333698505012033811838849431429669391389749529";
        String publicExponent = "65537";
        String privateExponent = "33386975871321803666451263730887475835573162973456070835020160802550545947926513118412518115719177487062768632695226169176997091622033201975193931868196638713298309919409357016591683996259177257354897423241562278633316296652805265316086195554813413654345177218467289505127801198771631206809967423233540896605";

		RSA rsa = new RSA();
		RSAPublicKey publicKey = (RSAPublicKey)rsa.getPublicKey(modulus, publicExponent);
		
		AES aes = new AES();
		byte[] aesKeyBytes = "1234567812345678".getBytes();
//				aes.createKey();
		
		String encryptData = rsa.encrypt(publicKey,aesKeyBytes);
		System.out.println(""+encryptData);

		RSAPrivateKey privateKey = (RSAPrivateKey)rsa.getPrivateKey(modulus, privateExponent);
		byte[] decryptBytes = rsa.decrypt(privateKey,encryptData);
		System.out.println(""+new String(decryptBytes));

	}

}