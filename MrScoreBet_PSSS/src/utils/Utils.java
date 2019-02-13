package utils;

import java.util.regex.*;

public class Utils {

	public static boolean validaEmail(String email) {
		if (email == null) return false;
		Pattern p = Pattern.compile("([A-Za-z0-9_\\-\\.])+\\@([A-Za-z0-9_\\-\\.])+\\.([A-Za-z]{2,4})$", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(email);
		return m.matches();  
	}
	
	public static boolean validaPswUser(String text) {
		if (text == null) return false;
		Pattern p = Pattern.compile(".{6,20}$", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(text);
		return m.matches();
	}

}
