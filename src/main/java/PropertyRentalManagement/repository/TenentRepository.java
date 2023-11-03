package PropertyRentalManagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import PropertyRentalManagement.model.TenentModel;

public interface TenentRepository extends JpaRepository<TenentModel, Long> {

	List<TenentModel> findByEmail(String email);

	List<TenentModel> findByPhone(String phone);

	List<TenentModel> findByEmailOrPhone(String email, String phone);
	

}
