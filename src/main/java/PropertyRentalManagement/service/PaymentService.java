package PropertyRentalManagement.service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import PropertyRentalManagement.model.HouseModel;
import PropertyRentalManagement.model.PaymentModel;
import PropertyRentalManagement.model.RentalRequestModel;
import PropertyRentalManagement.repository.HouseRepository;
import PropertyRentalManagement.repository.PaymentRepository;
import PropertyRentalManagement.repository.RentalRequestRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class PaymentService {
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private RentalRequestRepository rentalRequestRepository;
	
	@Autowired
	private HouseRepository houseRepository;
		

	public String payment1(PaymentModel paymentModel) {
		RentalRequestModel rentalRequestModel = rentalRequestRepository.findById(paymentModel.getRentalRequestId()).get();
		paymentModel.setStatus("Payment successfully");
		paymentModel.setDate(new Date());
		paymentModel.setRentalRequestModel(rentalRequestModel);
		paymentModel.setPaymentFor("Deposite Amount");
		paymentRepository.save(paymentModel);
		rentalRequestModel.setStatus("Property Rented");
		rentalRequestRepository.saveAndFlush(rentalRequestModel);
		HouseModel houseModel = rentalRequestModel.getRentalPackageModel().getHouseModel();
		houseModel.setStatus("Property Rented");
		houseRepository.saveAndFlush(houseModel);
		return "Advance Paid Successfully";
	}


	public List<PaymentModel> viewpayment(long rentalRequestId) {
		RentalRequestModel rentalRequestModel=rentalRequestRepository.findById(rentalRequestId).get();
		List<PaymentModel>paymentList=paymentRepository.findByRentalRequestModel(rentalRequestModel);
		Collections.reverse(paymentList);
		return paymentList;
	}


	public String paymentRent1(PaymentModel paymentModel, String month, String year) {
		RentalRequestModel rentalRequestModel = rentalRequestRepository.findById(paymentModel.getRentalRequestId()).get();
		List<PaymentModel>  paymentModelList = paymentRepository.findByRentalRequestModelAndPaymentFor(rentalRequestModel,month+"-"+year);
		if(paymentModelList.size()>0) {
			return "Rent for "+month+"-"+year+" is Already Paid";
		}
		paymentModel.setStatus("Payment successfully");
		paymentModel.setDate(new Date());
		paymentModel.setRentalRequestModel(rentalRequestModel);
		paymentModel.setPaymentFor("Deposite Amount");
		paymentModel.setPaymentFor(month+"-"+year);
		paymentRepository.save(paymentModel);
		return "Rent Paid Successfully";
	}
}
