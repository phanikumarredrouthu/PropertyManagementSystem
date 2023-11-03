package PropertyRentalManagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import PropertyRentalManagement.model.HouseModel;
import PropertyRentalManagement.model.RentalPackageModel;

public interface RentalPackageRepository extends JpaRepository<RentalPackageModel, Long> {

	List<RentalPackageModel> findByHouseModel(HouseModel houseModel);

	List<RentalPackageModel> findByHouseModelIn(List<HouseModel> houseModelList);


	List<RentalPackageModel> findByHouseModelAndRentalPackageTitle(HouseModel houseModel, String rentalPackageTitle);


}
