package PropertyRentalManagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import PropertyRentalManagement.model.HouseOwnerModel;

public interface HouseOwnerRepository extends JpaRepository<HouseOwnerModel, Long> {



	List<HouseOwnerModel> findByEmail(String email);

	List<HouseOwnerModel> findByPhone(String phone);

	List<HouseOwnerModel> findByEmailOrPhone(String email, String phone);
	

}
