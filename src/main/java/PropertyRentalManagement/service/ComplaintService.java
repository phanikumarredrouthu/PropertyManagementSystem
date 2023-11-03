package PropertyRentalManagement.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import PropertyRentalManagement.model.ComplaintModel;
import PropertyRentalManagement.model.RentalRequestModel;
import PropertyRentalManagement.repository.ComplaintRepository;
import PropertyRentalManagement.repository.RentalRequestRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ComplaintService {
	
	@Autowired
	private ComplaintRepository complaintRepository;
	
	@Autowired
	private RentalRequestRepository rentalRequestRepository;

	public String risecomplaints1(ComplaintModel complaintModel) {
		RentalRequestModel rentalRequestModel = rentalRequestRepository.findById(complaintModel.getRentalRequestId()).get();
		complaintModel.setStatus("Raised Complaint");
		complaintModel.setSolution("");
		complaintModel.setRentalRequestModel(rentalRequestModel);
		complaintModel.setComplaintDate(""+new Date());
		complaintRepository.save(complaintModel);
		rentalRequestRepository.saveAndFlush(rentalRequestModel);
		return "Complaint Rised Successfully";
	}

	public List<ComplaintModel> viewcomplaints(long houseId) {
		RentalRequestModel rentalRequestModel=rentalRequestRepository.findById(houseId).get();
		List<ComplaintModel>complaintList=complaintRepository.findByRentalRequestModel(rentalRequestModel);
		return complaintList;
	}

	public String resolved1(long complaintId, String solution) {
		ComplaintModel complaintModel = complaintRepository.findById(complaintId).get();
		complaintModel.setSolution(solution);
		complaintModel.setStatus("solution solved");
		complaintModel.setResponseDate(""+new Date());
		complaintRepository.saveAndFlush(complaintModel);

		return "Complaint Resolved  successfully";
	}

	public ComplaintModel getComplintModel(long complaintId) {
		
		return complaintRepository.findById(complaintId).get();
	}

}
