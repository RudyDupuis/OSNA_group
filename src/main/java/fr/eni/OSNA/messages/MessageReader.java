package fr.eni.OSNA.messages;

import java.util.ResourceBundle;

public abstract class MessageReader {
	private static ResourceBundle rb;
	
	static {
		try {
			rb = ResourceBundle.getBundle("fr.eni.OSNA.code-messages.messages");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String getMessage(int Code ) {
		String message = "";
		
		try {
			if(rb!=null) {
				message = rb.getString(String.valueOf(Code));
			} else {
				message = "Problème à la lecture du fichier contenant les messages";
			}
		} catch (Exception e) {
			e.printStackTrace();
			message= "Une erreur inconnue s'est produite";
		}
		
		return message;
	}
}
