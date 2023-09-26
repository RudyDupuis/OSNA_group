package fr.eni.OSNA.bo;

public class User {
	private int id;
	private String pseudo;
	private String firstName;
	private String lastName;
	private String mail;
	private String phone;
	private String street;
	private int postalCode;
	private String city;
	private String password;
	private int points;
	
	public User(int id, String firstName, String lastName, String pseudo, String mail, String phone, String street,
			int postalCode, String city, String password, int points) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.pseudo = pseudo;
		this.mail = mail;
		this.phone = phone;
		this.street = street;
		this.postalCode = postalCode;
		this.city = city;
		this.password = password;
		this.points = points;
	}
	
	public User(int id, String firstName, String lastName, String pseudo, String mail, String phone, String street,
			int postalCode, String city, int points) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.pseudo = pseudo;
		this.mail = mail;
		this.phone = phone;
		this.street = street;
		this.postalCode = postalCode;
		this.city = city;
		this.points = points;
	}
	
	public User(int id, String firstName, String lastName, String pseudo, String mail, String phone, String street,
			int postalCode, String city, String password) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.pseudo = pseudo;
		this.mail = mail;
		this.phone = phone;
		this.street = street;
		this.postalCode = postalCode;
		this.city = city;
		this.password = password;
	}
	
	public User(String firstName, String lastName, String pseudo, String mail, String phone, String street,
			int postalCode, String city, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.pseudo = pseudo;
		this.mail = mail;
		this.phone = phone;
		this.street = street;
		this.postalCode = postalCode;
		this.city = city;
		this.password = password;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPseudo() {
		return pseudo;
	}
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public int getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
}
