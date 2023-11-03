package PropertyRentalManagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import PropertyRentalManagement.model.RentalPackageModel;
import PropertyRentalManagement.model.RentalRequestModel;
import PropertyRentalManagement.model.TenentModel;

public interface RentalRequestRepository extends JpaRepository<RentalRequestModel, Long> {

	List<RentalRequestModel> findByRentalPackageModelIn(List<RentalPackageModel> rentalPackageModelList);

	List<RentalRequestModel> findByTenentModel(TenentModel tenentModel);


	List<RentalRequestModel> findByRentalRequestIdNot(long rentalRequestId);

	List<RentalRequestModel> findByRentalPackageModelInAndStatus(List<RentalPackageModel> rentalPackageModelList,
			String string);

	List<RentalRequestModel> findByTenentModelAndStatus(TenentModel tenentModel, String string);

	List<RentalRequestModel> findByTenentModelAndStatusOrStatus(TenentModel tenentModel, String string, String string2);

	List<RentalRequestModel> findByTenentModelAndRentalPackageModelIn(TenentModel tenentModel,
			List<RentalPackageModel> rentalPackageModelList);



}
