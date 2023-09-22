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
	private LocalDate startDate;
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
	
	public Article(int id, int idSeller, String name, String categorie, Blob image, String description,
			int startingPrice, int bestOffer, int idUserBestOffer, LocalDate startDate, LocalDate endDate, String street,
			int postalCode, String city) {
		this.id = id;
		this.idSeller = idSeller;
		this.name = name;
		this.categorie = categorie;
		this.image = image;
		this.description = description;
		this.startingPrice = startingPrice;
		this.bestOffer = bestOffer;
		this.idUserBestOffer = idUserBestOffer;
		this.startDate = startDate;
		this.endDate = endDate;
		this.street = street;
		this.postalCode = postalCode;
		this.city = city;
	}
	
	public Article(int id, int idSeller, String name, String categorie, Blob image, String description,
			int startingPrice, LocalDate startDate, LocalDate endDate, String street,
			int postalCode, String city) {
		this.id = id;
		this.idSeller = idSeller;
		this.name = name;
		this.categorie = categorie;
		this.image = image;
		this.description = description;
		this.startingPrice = startingPrice;
		this.startDate = startDate;
		this.endDate = endDate;
		this.street = street;
		this.postalCode = postalCode;
		this.city = city;
	}
	
	public Article(int idSeller, String name, String categorie, Blob image, String description, int startingPrice,
			LocalDate startDate, LocalDate endDate, String street, int postalCode, String city) {
		this.idSeller = idSeller;
		this.name = name;
		this.categorie = categorie;
		this.image = image;
		this.description = description;
		this.startingPrice = startingPrice;
		this.startDate = startDate;
		this.endDate = endDate;
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

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
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

	public boolean isPickedUp() {
		return pickedUp;
	}

	public void setPickedUp(boolean pickedUp) {
		this.pickedUp = pickedUp;
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

	public String getNameSeller() {
		return nameSeller;
	}

	public void setNameSeller(String nameSeller) {
		this.nameSeller = nameSeller;
	}
}
