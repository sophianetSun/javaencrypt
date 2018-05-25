package hash;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DigestMain {

	public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		byte[] plain = null;
		byte[] hash = null;
		String[] algo = {"MD5", "SHA-1", "SHA-256"};
		System.out.println("�ؽ����� ���� ���ڿ��� �Է��ϼ���");
		plain = br.readLine().getBytes();
		System.out.println("�� : " + new String(plain));
		for(String al : algo) {
			// MessageDigest : �ؽ� �˰��� ������  �� �ִ� ��ü
			MessageDigest md = MessageDigest.getInstance(al);
			hash = md.digest(plain);	// �ؽ� �Լ� �����Ͽ� �ؽ����� ���ϱ�
			System.out.println(al + "�ؽ��� ũ�� : " + (hash.length*8) + "bits");
			System.out.println("��ȣ�� : ");
			for(byte b : hash) {
				System.out.printf("%02X", b);
			}
			System.out.println();
		}
	}

}
