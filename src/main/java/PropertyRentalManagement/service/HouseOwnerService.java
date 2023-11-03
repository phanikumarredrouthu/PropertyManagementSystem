package PropertyRentalManagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import PropertyRentalManagement.model.HouseOwnerModel;
import PropertyRentalManagement.repository.HouseOwnerRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class HouseOwnerService {

	
	@Autowired
	private HouseOwnerRepository  houseOwnerRepository;

	public String HouseOwnerRegistration(HouseOwnerModel houseOwnerModel) {
		List<HouseOwnerModel>houseOwnerModelList = houseOwnerRepository.findByEmail(houseOwnerModel.getEmail());
		if(houseOwnerModelList.size()>0) {
			return "Duplicate Email Address";
		}
		List<HouseOwnerModel>houseOwnerModelList2 = houseOwnerRepository.findByPhone(houseOwnerModel.getPhone());
		if(houseOwnerModelList2.size()>0) {
			return "Duplicate Phone number";
		}
		houseOwnerRepository.save(houseOwnerModel);
		return "HouseOwners Register Successfully";
	}
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public String HouseOwnerRegistration1(HouseOwnerModel houseOwnerModel) {
		try {
			List<HouseOwnerModel> houseOwnerModelList = houseOwnerRepository.findByEmailOrPhone(houseOwnerModel.getEmail(), houseOwnerModel.getPhone());
			if(houseOwnerModelList.size()==0) {
			
				houseOwnerModel.setPassword(bCryptPasswordEncoder.encode(houseOwnerModel.getPassword()));
				houseOwnerRepository.save(houseOwnerModel);
				return "HouseOwners added Successfully";
			}else {
				return "Duplicate Details";
			}
			
		}catch (Exception e) {
			System.out.println(e);
		}
		return "Owners Added";
	}
	public List<HouseOwnerModel> getHouseOwner2() {
		List<HouseOwnerModel> houseOwnerModelList = houseOwnerRepository.findAll();
		return houseOwnerModelList;	
	}
	public HouseOwnerModel getByEmail(String name) {
		return houseOwnerRepository.findByEmail(name).get(0);
	}
}
	
		
	


