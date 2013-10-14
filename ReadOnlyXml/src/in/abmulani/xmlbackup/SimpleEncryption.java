package in.abmulani.xmlbackup;

public class SimpleEncryption {
	   static final String key = "AABIDMULANI";

	   protected String encryptString(String str)
	   {
	      StringBuffer sb = new StringBuffer (str);

	      int lenStr = str.length();
	      int lenKey = key.length();
	      for ( int i = 0, j = 0; i < lenStr; i++, j++ ) 
	      {
	         if ( j >= lenKey ) j = 0;  // Wrap 'round to beginning of key string.
	         sb.setCharAt(i, (char)(str.charAt(i) ^ key.charAt(j))); 
	      }
	      return sb.toString();
	   }
	   
	   protected String decryptString(String str)
	   {
	      return encryptString(str);
	   }
	   
	   
}
