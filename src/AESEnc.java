import java.security.Key;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;


public class AESEnc {
	private Key aesKey;
	private Cipher cipher;
	
	public AESEnc(String aKey) throws Exception{
		// key string, but SecretKeySpec wants a byteArray + Algorithm 
		// for byte Array we use aKey.getbytes(), for algorithm we use "AES"
		// aesKey is of Key object Type therefore we need a SecretKeySpec Object type to use it with it.
		this.aesKey = new SecretKeySpec(aKey.getBytes(), "AES");
		this.cipher = Cipher.getInstance("AES");
	}
	
	public String encrypt(String text) throws Exception {
		 cipher.init(Cipher.ENCRYPT_MODE, aesKey);
	     byte[] encrypted = cipher.doFinal(text.getBytes());
	     return new String(Base64.getEncoder().encodeToString(encrypted));
	}
	public String decrypt(String text) throws Exception{
		byte[] encrypted = Base64.getDecoder().decode(text);
		cipher.init(Cipher.DECRYPT_MODE, aesKey);
		return new String(cipher.doFinal(encrypted));
	}
}
