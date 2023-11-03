package PropertyRentalManagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import PropertyRentalManagement.model.HouseModel;
import PropertyRentalManagement.model.HouseOwnerModel;

public interface HouseRepository extends JpaRepository<HouseModel, Long> {

	List<HouseModel> findByHouseType(String houseType);

	List<HouseModel> findByHouseOwnerModel(HouseOwnerModel houseOwnerModel);

	List<HouseModel> findByStatus(String string);

}
