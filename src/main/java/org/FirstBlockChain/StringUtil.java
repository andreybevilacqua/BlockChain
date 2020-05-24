package org.FirstBlockChain;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.management.RuntimeErrorException;
import javax.swing.plaf.synth.SynthSeparatorUI;

public class StringUtil {

	//Applies Sha256 to a string and returns the result. 
	public static String applySha256(String input) {
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			messageDigest.update(input.getBytes());
			
			// Apply the SHA-256.
			byte[] hash = messageDigest.digest(input.getBytes("UTF-8"));
			
			StringBuffer hexString = new StringBuffer();
			
			/*for(int i = 0; i < hash.length; i++) {
				String hex = Integer.toHexString(0xff & hash[i]);
				if(hex.length() == 1) {
					hexString.append('0');
					hexString.append(hex);
				}
			}*/
			
			for(int i = 0; i < hash.length; i++) {
				hexString.append(Integer.toString((hash[i]&0xff)+0x100,16).substring(1));
			}
			// https://docs.oracle.com/javase/7/docs/api/java/lang/Integer.html#toHexString(int)
			// https://stackoverflow.com/questions/11380062/what-does-value-0xff-do-in-java
			return hexString.toString();
		} catch(Error | NoSuchAlgorithmException | UnsupportedEncodingException e) {
			throw new RuntimeErrorException((Error) e);
		}
	}
}
