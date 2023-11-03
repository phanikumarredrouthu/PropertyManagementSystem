package PropertyRentalManagement.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import PropertyRentalManagement.model.HouseModel;
import PropertyRentalManagement.model.RentalPackageModel;
import PropertyRentalManagement.repository.HouseRepository;
import PropertyRentalManagement.repository.RentalPackageRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class RentalPackageService {
	
	@Autowired
	private RentalPackageRepository rentalPackageRepository;
	
	@Autowired
	private HouseRepository houseRepository;

	public String rentalPackage1(RentalPackageModel rentalPackageModel) {
		HouseModel houseModel = houseRepository.findById(rentalPackageModel.getHouseId()).get();
		List<RentalPackageModel> rentalPackageModelList = rentalPackageRepository.findByHouseModelAndRentalPackageTitle(houseModel, rentalPackageModel.getRentalPackageTitle());
		if(rentalPackageModelList.size()>0) {
			return "Duplicate Package";
		}
		rentalPackageModel.setHouseModel(houseModel);
		rentalPackageModel.setDate(new Date());
		rentalPackageRepository.save(rentalPackageModel);
		
		return "Rental package successfully";
	}

	public List<RentalPackageModel> viewPackage(long houseId) {
		HouseModel houseModel = houseRepository.findById(houseId).get();
		List<RentalPackageModel>rentalPackageList=rentalPackageRepository.findByHouseModel(houseModel);
		return rentalPackageList;
	}

}
