package lab.edu.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import lab.edu.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
