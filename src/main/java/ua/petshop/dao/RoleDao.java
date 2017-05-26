package ua.petshop.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.petshop.model.Role;

public interface RoleDao extends JpaRepository<Role, Long> {

}
