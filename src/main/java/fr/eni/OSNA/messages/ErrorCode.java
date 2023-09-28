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
	public static final int NOT_ENOUGH_POINTS = 20005;
	public static final int REGEX_NAME = 20006;
	public static final int REGEX_MAIL = 20007;
	public static final int REGEX_PHONE = 20008;
	public static final int REGEX_PSEUDO = 200009;
	public static final int REGEX_PASSWORD = 20010;
	public static final int OFFER_HIGHER_STARTING_PRICE = 20011;
	public static final int OFFER_HIGHER_BEST_OFFER = 20012;
	public static final int OFFER_IS_BEST_OFFER = 20013;
	public static final int STARTDATE_SMALLER_TODAY = 20014;
	public static final int ENDDATE_SMALLER_STARTDATE = 20015;
}
