import java.io.BufferedReader;
import java.io.FileReader;

/**
 * You have to decrypt a text file that contains bytes that are simply encrypted with a password
 * of the length 3, I bruteforced it.
 * @author LuxXx
 * https://github.com/LuxXx
 * https://projecteuler.net/problem=59
 *
 */
public class ASCIIBruteforce {
	private static String alphabet = "abcdefghijklmnopqrstuvwxyz";
	private final char[] txt;
	private char[] currentPassword;
	
	public static void main(String[] args) throws Throwable {
		ASCIIBruteforce ab = new ASCIIBruteforce();
		
		while (true) {
			// The exercise gives us a hint that the text is in english
			// Therefore I was looking for a decrypted message
			// that contains the word "the"
			ab.bruteForce("the");
			Thread.sleep(400);	// I was watching the bruteforce live, that's why I added a sleep. I want to watch the messages.
		}
	}
	
	private void bruteForce(String inText) {
		while (true) {
			currentPassword = generateRandomPassword();
			String decryptedMessage = decrypt(new String(currentPassword));
			if (decryptedMessage.contains(inText)) {
				// print the password
				System.out.println(new String(currentPassword));
				// print the text
				System.out.println(decryptedMessage);
				break;
			}
		}
	}
	private String decrypt(String pw) {
		
		if (pw.length() != 3) return null;
		
		char[] dTxt = getTextCopy();
		
		for (int i = 0; i < dTxt.length; i++) {
			dTxt[i] = (char) (dTxt[i] ^ pw.charAt(i % 3));
		}
		
		return new String(dTxt);
	}
	
	public ASCIIBruteforce() throws Throwable {
		BufferedReader br = new BufferedReader(new FileReader("p059_cipher.txt"));
		String[] arr = br.readLine().split(",");
		txt = new char[arr.length];
		for (int i = 0; i < txt.length; i++) {
			txt[i] = (char) Integer.parseInt(arr[i]);
		}
		br.close();
	}
	
	private char[] getTextCopy() {
		char[] txtCopy = new char[txt.length];
		for (int i = 0; i < txt.length; i++) {
			txtCopy[i] = txt[i];
		}
		return txtCopy;
	}
	
	private char[] generateRandomPassword() {
		char a = alphabet.charAt((int) (Math.random()*alphabet.length()));
		char b = alphabet.charAt((int) (Math.random()*alphabet.length()));
		char c = alphabet.charAt((int) (Math.random()*alphabet.length()));
		return new char[] {a,b,c};
	}
	
	
	
}
