package lab.edu.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import lab.edu.model.Dentist;

public interface DentistRepository extends JpaRepository<Dentist, Long> {
}
