package util;

import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class PasswordUtil {
	public static String hashPassword(String password) throws Exception{
		byte[] salt = new byte[16];
		new SecureRandom().nextBytes(salt);
		
		int iterations = 10000;
		int keyLength = 256;
		
		PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterations, keyLength);
		SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		byte[] hash = factory.generateSecret(spec).getEncoded();
		
		return Base64.getEncoder().encodeToString(salt)+ "$" + Base64.getEncoder().encodeToString(hash) ;
	}
	public static boolean verifyPassword(String originalPassword, String storedPassword) throws Exception {
		String[] parts = storedPassword.split("\\$");
		byte[] salt = Base64.getDecoder().decode(parts[0]);
		byte[] storedHash = Base64.getDecoder().decode(parts[1]);
		
		PBEKeySpec spec = new PBEKeySpec(originalPassword.toCharArray(), salt, 10000, 256);
		SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		byte[] hash = factory.generateSecret(spec).getEncoded();
		
		for (int i=0; i < storedHash.length; i++) {
			if(storedHash[i] != hash[i]) {
				return false;
			}
		}
		return true;
	}
}
