package PropertyRentalManagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import PropertyRentalManagement.model.TenentModel;
import PropertyRentalManagement.repository.TenentRepository;
import jakarta.transaction.Transactional;
 @Service
 @Transactional
public class TenentService {
	 
	 @Autowired
	private TenentRepository tenentRepository;
//	public String tenentRegistration(TenentModel tenentModel) {
//			List<TenentModel>tenentModelList = tenentRepository.findByEmail(tenentModel.getEmail());
//			if(tenentModelList.size()>0) {
//				return "Duplicate Email Address";
//			}
//			List<TenentModel>tenentModelList2 = tenentRepository.findByPhone(tenentModel.getPhone());
//			if(tenentModelList2.size()>0) {
//				return "Duplicate Phone number";
//			}
//			tenentRepository.save(tenentModel);
//			return "Tenent Register Successfully";
//		}
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public String tenentRegistration(TenentModel tenentModel) {
		try {
			List<TenentModel> tenentModelList = tenentRepository.findByEmailOrPhone(tenentModel.getEmail(), tenentModel.getPhone());
			if(tenentModelList.size()==0) {
				tenentModel.setPassword(bCryptPasswordEncoder.encode(tenentModel.getPassword()));
				tenentRepository.save(tenentModel);
				return "Tenent added Successfully";
			}else {
				return "Duplicate Details";
			}
			
		}catch (Exception e) {
			System.out.println(e);
		}
		return "Tenents Added";
	}
	
	
		public List<TenentModel> getTenent2() {
			List<TenentModel>tenentModelList = tenentRepository.findAll();
			return tenentModelList;	
		}


		public TenentModel getByEmail(String name) {
			return tenentRepository.findByEmail(name).get(0);
		}
		
	}
