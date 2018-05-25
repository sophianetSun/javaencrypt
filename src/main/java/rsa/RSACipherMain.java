package rsa;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;

public class RSACipherMain {
	static Cipher cipher;
	static {
		try {
			cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e ) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		getKey();
		encryptFile("p1.txt", "c.txt");
		decryptFile("c.txt", "p2.txt");
	}

	private static void decryptFile(String cipherFile, String plainFile) {
		try {
			ObjectInputStream ois = new ObjectInputStream(
					new FileInputStream("privatekey.ser"));
			PrivateKey priKey = (PrivateKey)ois.readObject();
			ois.close();
			cipher.init(Cipher.DECRYPT_MODE, priKey);
			FileInputStream fis = new FileInputStream(cipherFile);
			FileOutputStream fos = new FileOutputStream(plainFile);
			CipherOutputStream cos = new CipherOutputStream(fos, cipher);
			byte[] buf = new byte[1024];
			int len;
			while ((len=fis.read(buf)) != -1) {
				cos.write(buf, 0, len);
			}
			fis.close();
			cos.flush();
			cos.close();
			fos.flush();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void encryptFile(String plainFile, String cipherFile) {
		try {
			ObjectInputStream ois = new ObjectInputStream(
					new FileInputStream("publickey.ser"));
			PublicKey pubkey = (PublicKey)ois.readObject();
			ois.close();
			cipher.init(Cipher.ENCRYPT_MODE, pubkey);
			FileInputStream fis = new FileInputStream(plainFile);
			FileOutputStream fos = new FileOutputStream(cipherFile);
			CipherOutputStream cos = new CipherOutputStream(fos, cipher);
			byte[] buf = new byte[1024];
			int len;
			while ((len=fis.read(buf)) != -1) {
				cos.write(buf, 0, len);
			}
			fis.close();
			cos.flush();
			cos.close();
			fos.flush();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void getKey() {
		try {
			KeyPairGenerator key = KeyPairGenerator.getInstance("RSA");
			key.initialize(2048);
			KeyPair keyPair = key.genKeyPair();
			PrivateKey priKey = keyPair.getPrivate();
			PublicKey pubKey = keyPair.getPublic();
			ObjectOutputStream out = new ObjectOutputStream(
					new FileOutputStream("privatekey.ser"));
			out.writeObject(priKey);
			out.flush();
			out.close();
			out = new ObjectOutputStream(
					new FileOutputStream("publickey.ser"));
			out.writeObject(pubKey);
			out.flush();
			out.close();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
