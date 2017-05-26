package ua.petshop.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import ua.petshop.model.Role;
import ua.petshop.model.User;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SecurityServiceTest {

    private static final String PASS = "pass";
    private static final String NAME = "name";
    private static final String ROLE_NAME = "ROLE_USER";
    @Mock
    private UserService userService;
    @Mock
    private AuthenticationManager authenticationManager;
    @InjectMocks
    private SecurityService securityService;

    @Test
    public void autoLogin() throws Exception {
        User user = new User();
        user.setName(NAME);
        Role role = new Role();
        role.setName(ROLE_NAME);
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(ROLE_NAME));
        UsernamePasswordAuthenticationToken authenticationToken =
            new UsernamePasswordAuthenticationToken(user, PASS, authorities);
        when(userService.findByName(NAME)).thenReturn(user);
        securityService.autoLogin(NAME, PASS);
        verify(userService, times(1)).findByName(NAME);
        verify(authenticationManager, times(1)).authenticate(authenticationToken);
    }
}
