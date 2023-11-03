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
@Table(name="RentalRequest")
public class RentalRequestModel {
	@Id
	@GeneratedValue
	private long rentalRequestId;
	
	@Column(nullable=false)
	private String status;
	
	@JsonFormat(pattern="dd-MM-yyyy HH:mm")
	@Column(nullable = false)
	private Date date;
		
	@Transient
	private long rentalPackageId;
	
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	@ManyToOne(fetch =FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="rentalPackageId",nullable = true)
	private RentalPackageModel rentalPackageModel;
	
	@Transient
	private long tenentId;
	
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	@ManyToOne(fetch =FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="tenentId",nullable = true)
	private TenentModel tenentModel;

	public long getRentalRequestId() {
		return rentalRequestId;
	}

	public void setRentalRequestId(long rentalRequestId) {
		this.rentalRequestId = rentalRequestId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public long getRentalPackageId() {
		return rentalPackageId;
	}

	public void setRentalPackageId(long rentalPackageId) {
		this.rentalPackageId = rentalPackageId;
	}

	public RentalPackageModel getRentalPackageModel() {
		return rentalPackageModel;
	}

	public void setRentalPackageModel(RentalPackageModel rentalPackageModel) {
		this.rentalPackageModel = rentalPackageModel;
	}

	public long getTenentId() {
		return tenentId;
	}

	public void setTenentId(long tenentId) {
		this.tenentId = tenentId;
	}

	public TenentModel getTenentModel() {
		return tenentModel;
	}

	public void setTenentModel(TenentModel tenentModel) {
		this.tenentModel = tenentModel;
	}
	
	
}
