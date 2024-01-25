package edu.sru.thangiah.group2.fall2023registrationsystem.controller.tests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import edu.sru.thangiah.group2.fall2023registrationsystem.controller.AccountController;
import edu.sru.thangiah.group2.fall2023registrationsystem.model.User;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

public class AccountControllerTest {

    private MockMvc mockMvc;
    private AccountController accountController;

    @BeforeEach
    public void setUp(WebApplicationContext context) {
        accountController = new AccountController();
        mockMvc = webAppContextSetup(context).build();
    }

    @Test
    public void testViewAccount() throws Exception {
        mockMvc.perform(get("/account/view"))
                .andExpect(status().isOk())
                .andExpect(view().name("account_view"));
    }

    @Test
    public void testShowChangePasswordForm() throws Exception {
        mockMvc.perform(get("/account/changePasswordForm")
                .param("error", "error_message")
                .param("success", "success_message"))
                .andExpect(status().isOk())
                .andExpect(view().name("change_password_form"))
                .andExpect(model().attributeExists("error", "success"));
    }

    @Test
    public void testUpdateAccount() throws Exception {
        User updateUser = new User(); 
        mockMvc.perform(post("/account/update")
                .flashAttr("updateUser", updateUser))
                .andExpect(status().isOk())
                .andExpect(view().name("account_update"));
    }

    @Test
    public void testChangePassword() throws Exception {
        mockMvc.perform(post("/account/changePassword")
                .param("oldPassword", "old_password")
                .param("newPassword", "new_password"))
                .andExpect(status().isOk())
                .andExpect(view().name("password_changed"));
    }
}