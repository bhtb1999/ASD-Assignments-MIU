package lab.edu.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import lab.edu.model.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}
