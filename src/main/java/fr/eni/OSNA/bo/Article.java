package fr.eni.OSNA.bo;


import java.sql.Blob;
import java.time.LocalDate;

public class Article {
	
	private int id;
	private int idSeller;
	private String name;
	private String description;
	private String categorie;
	private Blob image;
	private LocalDate startdate;
	private LocalDate endDate;
	private int startingPrice;
	private String street;           
	private int postalCode;       
	private String city;          
	private int bestOffer;       
	private int idUserBestOffer; 
	private boolean pickedUp;
	private String nameSeller;
	private String nameUserBestOffer;
	private boolean expiredDate;
	
	
	
	
	public Article(int idSeller, String name, String description, String categorie, Blob image,
			LocalDate startdate, LocalDate endDate, int startingPrice, String street, int postalCode, String city) {
		this.idSeller=idSeller;
		this.name = name;
		this.description = description;
		this.categorie = categorie;
		this.image = image;
		this.startdate = startdate;
		this.endDate = endDate;
		this.startingPrice = startingPrice;
		this.street = street;
		this.postalCode = postalCode;
		this.city = city;
	}
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdSeller() {
		return idSeller;
	}

	public void setIdSeller(int idSeller) {
		this.idSeller = idSeller;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	public Blob getImage() {
		return image;
	}

	public void setImage(Blob image) {
		this.image = image;
	}

	public LocalDate getStartdate() {
		return startdate;
	}

	public void setStartdate(LocalDate startdate) {
		this.startdate = startdate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public int getStartingPrice() {
		return startingPrice;
	}

	public void setStartingPrice(int startingPrice) {
		this.startingPrice = startingPrice;
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

	public int getBestOffer() {
		return bestOffer;
	}

	public void setBestOffer(int bestOffer) {
		this.bestOffer = bestOffer;
	}

	public int getIdUserBestOffer() {
		return idUserBestOffer;
	}

	public void setIdUserBestOffer(int idUserBestOffer) {
		this.idUserBestOffer = idUserBestOffer;
	}

	public boolean getPickedUp() {
		return pickedUp;
	}

	public void setPickedUp(boolean pickedUp) {
		this.pickedUp = pickedUp;
	}

	public String getNameSeller() {
		return nameSeller;
	}

	public void setNameSeller(String nameSeller) {
		this.nameSeller = nameSeller;
	}

	public String getNameUserBestOffer() {
		return nameUserBestOffer;
	}

	public void setNameUserBestOffer(String nameUserBestOffer) {
		this.nameUserBestOffer = nameUserBestOffer;
	}

	public boolean isExpiredDate() {
		return expiredDate;
	}

	public void setExpiredDate(boolean expiredDate) {
		this.expiredDate = expiredDate;
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", idSeller=" + idSeller + ", name=" + name + ", description=" + description
				+ ", categorie=" + categorie + ", image=" + image + ", startdate=" + startdate + ", endDate=" + endDate
				+ ", startingPrice=" + startingPrice + ", street=" + street + ", postalCode=" + postalCode + ", city="
				+ city + ", bestOffer=" + bestOffer + ", idUserBestOffer=" + idUserBestOffer + ", pickedUp=" + pickedUp
				+ "]";
	}  
	
}
