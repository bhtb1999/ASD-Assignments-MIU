package lab.edu.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import lab.edu.model.Surgery;

public interface SurgeryRepository extends JpaRepository<Surgery, Long> {
}
