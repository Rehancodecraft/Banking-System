package Bank;

public class Encryption {
	public static String encryptPassword(String password){
		StringBuilder result = new StringBuilder();
		
		for (int i = 0; i < password.length(); i++) {
			char singleCharacter = password.charAt(i);
			
			if (Character.isDigit(singleCharacter)) {
				
				int asciiValue = (int) singleCharacter - 10;
				result.append((char) asciiValue);
			} else {
				
				char upperCh = Character.toUpperCase(singleCharacter);
				int asciiValue = (int) upperCh - 30;
				result.append((char)  asciiValue);
			}
		}
		return result.toString();
	}
}