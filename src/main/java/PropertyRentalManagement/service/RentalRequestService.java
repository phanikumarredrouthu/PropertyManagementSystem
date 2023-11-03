package PropertyRentalManagement.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import PropertyRentalManagement.model.HouseModel;
import PropertyRentalManagement.model.HouseOwnerModel;
import PropertyRentalManagement.model.RentalPackageModel;
import PropertyRentalManagement.model.RentalRequestModel;
import PropertyRentalManagement.model.TenentModel;
import PropertyRentalManagement.repository.HouseOwnerRepository;
import PropertyRentalManagement.repository.HouseRepository;
import PropertyRentalManagement.repository.RentalPackageRepository;
import PropertyRentalManagement.repository.RentalRequestRepository;
import PropertyRentalManagement.repository.TenentRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class RentalRequestService {
	
	@Autowired
	RentalRequestRepository rentalRequestRepository;
	
	@Autowired
	RentalPackageRepository rentalPackageRepository;
	
	@Autowired
	TenentRepository tenentRepository;
	
	@Autowired
	private HouseOwnerRepository houseOwnerRepository;
	
	@Autowired
	private HouseRepository houseRepository;
	

	public RentalRequestModel rentalRequest(long rentalPackageId, String Email) {
		TenentModel tenentModel = tenentRepository.findByEmail(Email).get(0);
		RentalPackageModel rentalPackageModel = rentalPackageRepository.findById(rentalPackageId).get();
		RentalRequestModel rentalRequestModel = new RentalRequestModel();
		rentalRequestModel.setDate(new Date());
		rentalRequestModel.setStatus("Requested for Rental");
		rentalRequestModel.setTenentModel(tenentModel);
		rentalRequestModel.setRentalPackageModel(rentalPackageModel);
		rentalRequestModel = rentalRequestRepository.save(rentalRequestModel);
		return rentalRequestModel;
	}

	public List<RentalRequestModel> viewRequest(String houseId, String name, String authority) {
		List<RentalRequestModel> requestModelList = new ArrayList<RentalRequestModel>();
		if(authority.equalsIgnoreCase("ROLE_HOUSEOWNER")) {
			HouseOwnerModel houseOwnerModel = houseOwnerRepository.findByEmail(name).get(0);
			if(houseId.equalsIgnoreCase("")) {
				List<HouseModel> houseModelList = houseRepository.findByHouseOwnerModel(houseOwnerModel);
				List<RentalPackageModel> rentalPackageModelList = rentalPackageRepository.findByHouseModelIn(houseModelList);
				requestModelList = rentalRequestRepository.findByRentalPackageModelIn(rentalPackageModelList);
			}else {
				HouseModel houseModel = houseRepository.findById(Long.parseLong(houseId)).get();
				List<RentalPackageModel> rentalPackageModelList = rentalPackageRepository.findByHouseModel(houseModel);
				requestModelList = rentalRequestRepository.findByRentalPackageModelIn(rentalPackageModelList);
			}	
		}else if(authority.equalsIgnoreCase("ROLE_TENENT")) {
			TenentModel tenentModel = tenentRepository.findByEmail(name).get(0);
			requestModelList = rentalRequestRepository.findByTenentModel(tenentModel);
		}
		System.out.println(requestModelList.size());
		Collections.reverse(requestModelList);
		return requestModelList;
	}

	public String Accept(long rentalRequestId) {
		RentalRequestModel rentalRequestModel =rentalRequestRepository.findById(rentalRequestId).get();
		rentalRequestModel.setStatus("Accepted for Rental");
		rentalRequestRepository.saveAndFlush(rentalRequestModel);
		List<RentalRequestModel> requestModelList = rentalRequestRepository.findByRentalRequestIdNot(rentalRequestModel.getRentalRequestId());
		Iterator<RentalRequestModel> requestModelIterator = requestModelList.iterator();
		while (requestModelIterator.hasNext()) {
			RentalRequestModel rentalRequestModel2 = (RentalRequestModel) requestModelIterator.next();
			rentalRequestModel2.setStatus("Rental Request Suspended");
			rentalRequestRepository.saveAndFlush(rentalRequestModel2);
		}
		HouseModel houseModel = rentalRequestModel.getRentalPackageModel().getHouseModel();
		houseModel.setStatus("Not Available");
		houseRepository.saveAndFlush(houseModel);
		return "Accepted for Rental successfully";
	}

	public String Reject(long rentalRequestId) {
		RentalRequestModel rentalRequestModel =rentalRequestRepository.findById(rentalRequestId).get();
		rentalRequestModel.setStatus("Rejected for Rental");
		rentalRequestRepository.saveAndFlush(rentalRequestModel);
		return "Rental Request Rejected";
	
	}

	public String Cancel(long rentalRequestId) {
		RentalRequestModel rentalRequestModel =rentalRequestRepository.findById(rentalRequestId).get();
		rentalRequestModel.setStatus("Rental Request Cancelled");
		rentalRequestRepository.saveAndFlush(rentalRequestModel);
		return "Rental Request Cancelled";
	}

	public RentalRequestModel getRentalRequest(long rentalRequestId) {
		RentalRequestModel rentalRequestModel =rentalRequestRepository.findById(rentalRequestId).get();
		return rentalRequestModel;
	}

}
