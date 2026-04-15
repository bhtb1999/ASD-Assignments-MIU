package lab.edu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import lab.edu.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

    List<Address> findAllByOrderByCityAscStreetAscHouseNumberAsc();
}
