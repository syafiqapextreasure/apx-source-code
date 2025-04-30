package com.ppk;


import java.security.Key;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Decription {
	private static final String ALGO = "AES";
	  //driverClassName=floo/OgpU1gwUI2UaVLm4lO9s2yzdkYlEZ51fGe0w1w=
	//url=Iec183KyiSpXNEc/Urfb564e1x6z6O/a5GKwqwh7eruFOXnnSnBJk+KKkRVuU593
	//		username=SHfKVoaagH6MWHQKEgvV0Q==
	//		password=SHfKVoaagH6MWHQKEgvV0Q==
	//		dbtype=mysql
	//
	  private static final byte[] keyValue = new byte[] { 
	      73, 76, 77, 85, 83, 69, 75, 79, 76, 65, 
	      72, 49, 50, 
	      51, 52, 53 };
	public static void main(String[] args) {
		// TODO Auto-generated method stub
try {
	System.out.println(decrypt("floo/OgpU1gwUI2UaVLm4lO9s2yzdkYlEZ51fGe0w1w="));
} catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
	}
  
  
  public static String decrypt(String encryptedData) throws Exception {
    Key key = generateKey();
    Cipher c = Cipher.getInstance("AES");
    c.init(2, key);
    byte[] decordedValue = Base64.getDecoder().decode(encryptedData);
    byte[] decValue = c.doFinal(decordedValue);
    String decryptedValue = new String(decValue);
    return decryptedValue;
  }
  
  private static Key generateKey() throws Exception {
    Key key = new SecretKeySpec(keyValue, "AES");
    return key;
  }
  
  public static String encrypt(String Data) throws Exception {
    Key key = generateKey();
    Cipher c = Cipher.getInstance("AES");
    c.init(1, key);
    byte[] encVal = c.doFinal(Data.getBytes());
    String encryptedValue = Base64.getEncoder().encodeToString(encVal);
    return encryptedValue;
  }
}



