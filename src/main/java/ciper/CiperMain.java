package ciper;

public class CiperMain {

	public static void main(String[] args) {
		String plainText = "안녕하세요. 홍길동 입니다.";
		String ciperText = CiperUtil.encrypt(plainText, "abc123456");
		System.out.println("암호문 : " + ciperText);
		String plainText2 = CiperUtil.decrypt(ciperText, "abc123456");
		System.out.println("복호문 : " + plainText2);
	}

}
