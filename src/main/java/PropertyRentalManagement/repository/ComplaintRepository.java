package PropertyRentalManagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import PropertyRentalManagement.model.ComplaintModel;
import PropertyRentalManagement.model.RentalRequestModel;

public interface ComplaintRepository extends JpaRepository<ComplaintModel, Long> {

	List<ComplaintModel> findByRentalRequestModel(RentalRequestModel rentalRequestModel);

}
