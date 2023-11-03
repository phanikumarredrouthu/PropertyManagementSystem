package PropertyRentalManagement.service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
import PropertyRentalManagement.utils.ImageUpload;
import jakarta.transaction.Transactional;


@Service
@Transactional
public class HouseService {
	@Autowired
	private HouseOwnerRepository houseOwnerRepository;
	
	@Autowired
	private HouseRepository houseRepository;
	
	@Autowired
	private RentalPackageRepository rentalPackageRepository;
	
	@Autowired
	private ImageUpload imageupload;
	
	@Autowired
	private RentalRequestRepository rentalRequestRepository;
	
	@Autowired
	private TenentRepository tenentRepository;

	public String addhouse1(HouseModel houseModel, MultipartFile houseImage2, String name) {
		try {
			if(houseImage2==null) {
				houseModel.setHouseImage(null);
			}else {
				if(imageupload.uploadImage(houseImage2)) {
					System.out.println("picture added successfully");
				}
				houseModel.setHouseImage(Base64.getEncoder().encodeToString(houseImage2.getBytes()));
			}
			HouseOwnerModel houseOwnerModel =houseOwnerRepository.findByEmail(name).get(0);
			houseModel.setHouseOwnerModel(houseOwnerModel);
			houseModel.setStatus("Available");
			houseModel.setPostedDate(new Date());
			houseModel=houseRepository.save(houseModel);
			houseRepository.save(houseModel);
			return "House added successfully";
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return "House added";
	}

	public List<HouseModel> getHouse2(String role, String email, String type) {
		List<HouseModel> houseModelList = new ArrayList<HouseModel>();
		List<HouseModel> houseModelList2 = new ArrayList<HouseModel>();
		if(role.equalsIgnoreCase("ROLE_TENENT")) {
			if(type.equalsIgnoreCase("all")) {
				houseModelList = houseRepository.findByStatus("Posted For Rental");
			}else if(type.equalsIgnoreCase("requested")){
				TenentModel tenentModel = tenentRepository.findByEmail(email).get(0);
				List<RentalRequestModel> rentalRequestModelsList = rentalRequestRepository.findByTenentModel(tenentModel);
				Iterator<RentalRequestModel> rentalRequestModelIterator = rentalRequestModelsList.iterator();
				while (rentalRequestModelIterator.hasNext()) {
					RentalRequestModel rentalRequestModel = (RentalRequestModel) rentalRequestModelIterator.next();
					HouseModel houseModel = rentalRequestModel.getRentalPackageModel().getHouseModel();
					houseModel.setRentalRequestModel(rentalRequestModel);
					houseModelList.add(houseModel);
				}
			}else {
				TenentModel tenentModel = tenentRepository.findByEmail(email).get(0);
				List<RentalRequestModel> rentalRequestModelsList = rentalRequestRepository.findByTenentModelAndStatus(tenentModel,"Property Rented");
				
				Iterator<RentalRequestModel> rentalRequestModelIterator = rentalRequestModelsList.iterator();
				while (rentalRequestModelIterator.hasNext()) {
					RentalRequestModel rentalRequestModel = (RentalRequestModel) rentalRequestModelIterator.next();
					HouseModel houseModel = rentalRequestModel.getRentalPackageModel().getHouseModel();
					houseModel.setRentalRequestModel(rentalRequestModel);
					houseModelList.add(houseModel);
				}
				
			}
			List<HouseModel> houseModelList3 = new ArrayList<HouseModel>();
			Iterator<HouseModel> houseModelIterator = houseModelList.iterator();
			while(houseModelIterator.hasNext()) {
				HouseModel houseModel = houseModelIterator.next();
				TenentModel tenentModel = tenentRepository.findByEmail(email).get(0);
				List<RentalPackageModel>  rentalPackageModelList = rentalPackageRepository.findByHouseModel(houseModel);
				List<RentalRequestModel>  rentalRequestModelList = rentalRequestRepository.findByTenentModelAndRentalPackageModelIn(tenentModel,rentalPackageModelList );
				if(rentalRequestModelList.size()>0) {
					houseModel.setRequested(true);
					houseModel.setRentalRequestModel(rentalRequestModelList.get(0));
				}else {
					houseModel.setRequested(false);
				}
				houseModelList3.add(houseModel);
			}
			houseModelList = houseModelList3;
			
		}else {
			if(type.equalsIgnoreCase("all")) {
				HouseOwnerModel houseOwnerModel = houseOwnerRepository.findByEmail(email).get(0);
				houseModelList = houseRepository.findByHouseOwnerModel(houseOwnerModel);
			}else {
				HouseOwnerModel houseOwnerModel = houseOwnerRepository.findByEmail(email).get(0);
				List<HouseModel> houseModelsList = houseRepository.findByHouseOwnerModel(houseOwnerModel);
				List<RentalPackageModel> rentalPackageModelList = rentalPackageRepository.findByHouseModelIn(houseModelsList);
				List<RentalRequestModel> rentalRequestModelsList = rentalRequestRepository.findByRentalPackageModelInAndStatus(rentalPackageModelList, "Property Rented");
				Iterator<RentalRequestModel> rentalRequestModelIterator = rentalRequestModelsList.iterator();
				while (rentalRequestModelIterator.hasNext()) {
					RentalRequestModel rentalRequestModel = (RentalRequestModel) rentalRequestModelIterator.next();
					HouseModel houseModel = rentalRequestModel.getRentalPackageModel().getHouseModel();
					houseModel.setRentalRequestModel(rentalRequestModel);
					houseModelList.add(houseModel);
				}
			}
		}
		Iterator<HouseModel> houseModelIterator = houseModelList.iterator();
		while(houseModelIterator.hasNext()) {
			HouseModel houseModel = houseModelIterator.next();
			List<RentalPackageModel> rentalPackageModelList = rentalPackageRepository.findByHouseModel(houseModel);
			houseModel.setRentalPackageList(rentalPackageModelList);
			if(houseModel.getStatus().equalsIgnoreCase("Property Rented") && !role.equalsIgnoreCase("ROLE_TENENT")) {
				List<RentalRequestModel> requestModelList = rentalRequestRepository.findByRentalPackageModelInAndStatus(rentalPackageModelList,"Property Rented");
				if(requestModelList.size()!=0) {
					houseModel.setRentalRequestModel(requestModelList.get(0));
				}
			}
			houseModelList2.add(houseModel);
		}
		Collections.reverse(houseModelList2);
		return houseModelList2 ;
	}

	public String postRental(long houseId) {
		HouseModel houseModel = houseRepository.findById(houseId).get();
		houseModel.setStatus("Posted For Rental");
		houseModel.setPostedDate(new Date());
		houseRepository.saveAndFlush(houseModel);
		return "Posted For Rental successfully";
	}
	
}
