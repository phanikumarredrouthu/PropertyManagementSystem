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
@Table(name="Payment")
public class PaymentModel {
	@Id
	@GeneratedValue
	private long paymentId;
	@Column(nullable=false)
	private String paymentType;
	@Column(nullable=false)
	private String amount;
	@Column(nullable=false)
	private String status;
	@Column(nullable=false)
	private String paymentFor;
	
	@JsonFormat(pattern="dd-MM-yyyy HH:mm")
	@Column(nullable = false)
	private Date date;
	
	@Transient
	private long rentalRequestId;
	
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	@ManyToOne(fetch =FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="rentalRequestId",nullable = true)
	private RentalRequestModel rentalRequestModel;

	public long getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(long paymentId) {
		this.paymentId = paymentId;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
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

	public String getPaymentFor() {
		return paymentFor;
	}

	public void setPaymentFor(String paymentFor) {
		this.paymentFor = paymentFor;
	}
	
	
	
}
