package lab.edu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import lab.edu.model.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    @Query("""
            select p
            from Patient p
            order by p.lName asc, p.fName asc
            """)
    List<Patient> findAllPatientsOrderByLastNameAscFirstNameAsc();

    @Query("""
            select p
            from Patient p
            left join p.address a
            where lower(p.fName) like lower(concat('%', :searchString, '%'))
               or lower(p.lName) like lower(concat('%', :searchString, '%'))
               or cast(p.age as string) like concat('%', :searchString, '%')
               or lower(a.city) like lower(concat('%', :searchString, '%'))
               or lower(a.street) like lower(concat('%', :searchString, '%'))
               or cast(a.houseNumber as string) like concat('%', :searchString, '%')
            """)
    List<Patient> searchPatients(String searchString);

    @Query("""
            select p
            from Patient p
            where p.address.id = :addressId
            order by p.lName asc, p.fName asc
            """)
    List<Patient> findPatientsByAddressId(Long addressId);
}
