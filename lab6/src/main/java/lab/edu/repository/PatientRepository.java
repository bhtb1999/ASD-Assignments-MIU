package lab.edu.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import lab.edu.model.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
