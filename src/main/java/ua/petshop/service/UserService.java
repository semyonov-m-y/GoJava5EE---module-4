package ua.petshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.petshop.dao.RoleDao;
import ua.petshop.dao.UserDao;
import ua.petshop.model.Role;
import ua.petshop.model.User;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class UserService {

    private UserDao userDao;
    private RoleDao roleDao;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserDao userDao, RoleDao roleDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.passwordEncoder = passwordEncoder;
    }

    public User save(String name, String password) {
        User user = new User();
        user.setName(name);
        user.setPassword(passwordEncoder.encode(password));
        Set<Role> roles = new HashSet<>();
        roles.add(roleDao.findOne(1L));
        user.setRoles(roles);
        return userDao.save(user);
    }

    public User findByName(String name) {
        return userDao.findByName(name);
    }
}
