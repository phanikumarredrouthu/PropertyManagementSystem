package PropertyRentalManagement.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name="HouseOwner")

public class HouseOwnerModel {
	@Id
	@GeneratedValue
	private long houseownerId;
	@Column(nullable=false)
	private String name;
	@Column(nullable=false,unique=true)
	private String email;
	@Column(nullable=false,unique=true)
	private String phone;
	@Column(nullable=false)
	private String password;
	@Column(nullable=false)
	private String address;
	@Column(nullable=false)
	private  String gender;
	public long getHouseownerId() {
		return houseownerId;
	}
	public void setHouseownerId(long houseownerId) {
		this.houseownerId = houseownerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	


}
