package ua.petshop.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import ua.petshop.dao.RoleDao;
import ua.petshop.dao.UserDao;
import ua.petshop.model.Role;
import ua.petshop.model.User;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    private static final String NAME = "name";
    private static final String PASS = "pass";
    @Mock
    private UserDao userDao;
    @Mock
    private RoleDao roleDao;
    @Mock
    private PasswordEncoder encoder;
    @InjectMocks
    private UserService userService;

    @Test
    public void saveShouldReturnUser() throws Exception {
        User user = new User();
        user.setName(NAME);
        user.setPassword(PASS);
        Role role = new Role();
        Set<Role> roles = new HashSet<>();
        role.setName("ROLE");
        roles.add(role);
        user.setRoles(roles);
        when(encoder.encode(PASS)).thenReturn(PASS);
        when(roleDao.findOne(1L)).thenReturn(role);
        when(userDao.save(user)).thenReturn(user);
        assertEquals(user, userService.save(NAME, PASS));
        verify(userDao, times(1)).save(user);
    }

    @Test
    public void findByName() throws Exception {
    }

}