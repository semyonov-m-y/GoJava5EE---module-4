package ua.petshop.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;
import ua.petshop.dto.UserDto;
import ua.petshop.service.SecurityService;
import ua.petshop.service.UserService;
import ua.petshop.validator.UserValidator;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(MockitoJUnitRunner.class)
public class RegistrationControllerTest {

    private static final String NAME = "name";
    private static final String PASS = "pass";
    @Mock
    private UserService userService;
    @Mock
    private SecurityService securityService;
    @Mock
    private UserValidator userValidator;
    @Mock
    private Errors errors;
    @InjectMocks
    private RegistrationController controller;
    private MockMvc mvc;

    @Before
    public void setUp() {
        mvc = standaloneSetup(controller).build();
    }

    @Test
    public void register() throws Exception {
        mvc.perform(get("/register")).andExpect(view().name("registration")).andExpect(model().attribute("user", new
            UserDto()));
    }

    @Test
    public void processRegistration() throws Exception {
        UserDto user = new UserDto();
        user.setName(NAME);
        user.setPassword(PASS);
        ModelAndView modelAndView = controller.processRegistration(user, errors);
        verify(userValidator, times(1)).validate(user, errors, userService);
        verify(userService, times(1)).save(user.getName(), user.getPassword());
        verify(securityService, times(1)).autoLogin(user.getName(), user.getPassword());
        assertEquals(modelAndView.getViewName(), "redirect:/products/all");
    }
}
