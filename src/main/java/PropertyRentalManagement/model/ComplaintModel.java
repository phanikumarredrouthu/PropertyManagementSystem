package PropertyRentalManagement.model;

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
@Table(name="Complaint")
public class ComplaintModel {
	@Id
	@GeneratedValue
	private long complaintId;
	@Column(nullable=false)
	private String status;
	@Column(nullable=false)
	private String complaint;
	@Column(nullable=false)
	private String solution;
	
	private String complaintDate;
	private String responseDate;
	
	@Transient
	private long rentalRequestId;
	
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	@ManyToOne(fetch =FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="rentalRequestId",nullable = true)
	private RentalRequestModel rentalRequestModel;

	public long getComplaintId() {
		return complaintId;
	}

	public void setComplaintId(long complaintId) {
		this.complaintId = complaintId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getComplaint() {
		return complaint;
	}

	public void setComplaint(String complaint) {
		this.complaint = complaint;
	}

	public String getSolution() {
		return solution;
	}

	public void setSolution(String solution) {
		this.solution = solution;
	}

	public long getRentalRequestId() {
		return rentalRequestId;
	}

	public void setRentalRequestId(long rentalRequestId) {
		this.rentalRequestId = rentalRequestId;
	}

	public RentalRequestModel getRentalRequestModel() {
		return rentalRequestModel;
	}

	public void setRentalRequestModel(RentalRequestModel rentalRequestModel) {
		this.rentalRequestModel = rentalRequestModel;
	}

	public String getComplaintDate() {
		return complaintDate;
	}

	public void setComplaintDate(String complaintDate) {
		this.complaintDate = complaintDate;
	}

	public String getResponseDate() {
		return responseDate;
	}

	public void setResponseDate(String responseDate) {
		this.responseDate = responseDate;
	}

	
}
