package ua.petshop.dao;

import org.springframework.data.repository.CrudRepository;
import ua.petshop.model.User;

public interface UserDao extends CrudRepository<User, Long> {
    User findByName(String name);
}
