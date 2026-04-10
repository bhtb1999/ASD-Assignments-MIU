package lab.edu.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import lab.edu.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
