package ciper;

public class CiperMain {

	public static void main(String[] args) {
		String plainText = "�ȳ��ϼ���. ȫ�浿 �Դϴ�.";
		String ciperText = CiperUtil.encrypt(plainText, "abc123456");
		System.out.println("��ȣ�� : " + ciperText);
		String plainText2 = CiperUtil.decrypt(ciperText, "abc123456");
		System.out.println("��ȣ�� : " + plainText2);
	}

}
