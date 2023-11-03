package PropertyRentalManagement.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name="House")
public class HouseModel {
	
	@Id
	@GeneratedValue
	private long houseId;
	@Column(nullable=false)
	private String houseType;
	@Lob
	@Column(nullable = false,columnDefinition="MEDIUMBLOB")
	private String houseImage;
	@Column(nullable=false)
	private String area;
	@Column(nullable=false)
	private String address;
	@Column(nullable=false)
	private String aboutHome;
	@Column(nullable=false)
	private String noOfFlors;
	@Column(nullable=false)
	private String noOfWashrooms;
	@Column(nullable=false)
	private String status;
	@Column(nullable=false)
	private String noOfBedrooms;
	
	@Transient
	private boolean isRequested;
	
	@JsonFormat(pattern="dd-MM-yyyy HH:mm")
	@Column(nullable = false)
	private Date postedDate;
	
	
	@Transient
	private List<RentalPackageModel> rentalPackageList;
	
	@Transient
	private long houseownerId;
	
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	@ManyToOne(fetch =FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="houseownerId",nullable = true)
	private HouseOwnerModel houseOwnerModel;
	
	@Transient
	private RentalRequestModel rentalRequestModel;

	public long getHouseId() {
		return houseId;
	}

	public void setHouseId(long houseId) {
		this.houseId = houseId;
	}

	public String getHouseType() {
		return houseType;
	}

	public void setHouseType(String houseType) {
		this.houseType = houseType;
	}

	public String getHouseImage() {
		return houseImage;
	}

	public void setHouseImage(String houseImage) {
		this.houseImage = houseImage;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAboutHome() {
		return aboutHome;
	}

	public void setAboutHome(String aboutHome) {
		this.aboutHome = aboutHome;
	}

	public String getNoOfFlors() {
		return noOfFlors;
	}

	public void setNoOfFlors(String noOfFlors) {
		this.noOfFlors = noOfFlors;
	}

	public String getNoOfWashrooms() {
		return noOfWashrooms;
	}

	public void setNoOfWashrooms(String noOfWashrooms) {
		this.noOfWashrooms = noOfWashrooms;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNoOfBedrooms() {
		return noOfBedrooms;
	}

	public void setNoOfBedrooms(String noOfBedrooms) {
		this.noOfBedrooms = noOfBedrooms;
	}

	public Date getPostedDate() {
		return postedDate;
	}

	public void setPostedDate(Date postedDate) {
		this.postedDate = postedDate;
	}

	public List<RentalPackageModel> getRentalPackageList() {
		return rentalPackageList;
	}

	public void setRentalPackageList(List<RentalPackageModel> rentalPackageList) {
		this.rentalPackageList = rentalPackageList;
	}

	public long getHouseownerId() {
		return houseownerId;
	}

	public void setHouseownerId(long houseownerId) {
		this.houseownerId = houseownerId;
	}

	public HouseOwnerModel getHouseOwnerModel() {
		return houseOwnerModel;
	}

	public void setHouseOwnerModel(HouseOwnerModel houseOwnerModel) {
		this.houseOwnerModel = houseOwnerModel;
	}

	public RentalRequestModel getRentalRequestModel() {
		return rentalRequestModel;
	}

	public void setRentalRequestModel(RentalRequestModel rentalRequestModel) {
		this.rentalRequestModel = rentalRequestModel;
	}

	public boolean isRequested() {
		return isRequested;
	}

	public void setRequested(boolean isRequested) {
		this.isRequested = isRequested;
	}


	
}
