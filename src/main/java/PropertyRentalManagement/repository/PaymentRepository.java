package PropertyRentalManagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import PropertyRentalManagement.model.PaymentModel;
import PropertyRentalManagement.model.RentalRequestModel;

public interface PaymentRepository extends JpaRepository<PaymentModel, Long> {

	List<PaymentModel> findByRentalRequestModel(RentalRequestModel rentalRequestModel);

	List<PaymentModel> findByRentalRequestModelAndPaymentFor(RentalRequestModel rentalRequestModel, String string);
	

}
