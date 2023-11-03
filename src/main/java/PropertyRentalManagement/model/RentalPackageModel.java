package PropertyRentalManagement.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name="RentalPackage")
public class RentalPackageModel {
	
	@Id
	@GeneratedValue
	private long rentalPackageId;
	@Column(nullable=false)
	private String rentalPackageTitle;
	@Column(nullable=false)
	private  String termsConditions;
	@Column(nullable=false)
	private  String rentPerMonth;
	@Column(nullable=false)
	private  String depositeAmount;
	
	
	@JsonFormat(pattern="dd-MM-yyyy HH:mm")
	@Column(nullable = false)
	private Date date;
	
	@Transient
	private long houseId;
	
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	@ManyToOne(fetch =FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="houseId",nullable = true)
	private HouseModel houseModel;

	public long getRentalPackageId() {
		return rentalPackageId;
	}

	public void setRentalPackageId(long rentalPackageId) {
		this.rentalPackageId = rentalPackageId;
	}

	public String getRentalPackageTitle() {
		return rentalPackageTitle;
	}

	public void setRentalPackageTitle(String rentalPackageTitle) {
		this.rentalPackageTitle = rentalPackageTitle;
	}

	public String getTermsConditions() {
		return termsConditions;
	}

	public void setTermsConditions(String termsConditions) {
		this.termsConditions = termsConditions;
	}

	public String getRentPerMonth() {
		return rentPerMonth;
	}

	public void setRentPerMonth(String rentPerMonth) {
		this.rentPerMonth = rentPerMonth;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public long getHouseId() {
		return houseId;
	}

	public void setHouseId(long houseId) {
		this.houseId = houseId;
	}

	public HouseModel getHouseModel() {
		return houseModel;
	}

	public void setHouseModel(HouseModel houseModel) {
		this.houseModel = houseModel;
	}

	public String getDepositeAmount() {
		return depositeAmount;
	}

	public void setDepositeAmount(String depositeAmount) {
		this.depositeAmount = depositeAmount;
	}
		
}
