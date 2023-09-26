package fr.eni.OSNA.messages;

public abstract class ErrorCode {
	//DAL
	public static final int ERROR_CONNECTION = 10000;
	public static final int ERROR_INSERT= 10001;
	public static final int ERROR_SELECT = 10002;
	public static final int ERROR_UPDATE = 10003;
	public static final int ERROR_DELETE = 10004;
	
	//BLL
	public static final int ERROR_MAIL_NOTUNIQUE = 20000;
	public static final int ERROR_PSEUDO_NOTUNIQUE = 20001;
	public static final int ERROR_LOGIN = 20002;
	public static final int ERROR_DIFF_PASSWORD = 20003;
	public static final int ERROR_IMAGE_SIZE = 20004;
}
