package lab.edu.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import lab.edu.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
