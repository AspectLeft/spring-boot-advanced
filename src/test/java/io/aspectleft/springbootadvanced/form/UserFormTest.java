package io.aspectleft.springbootadvanced.form;

import io.aspectleft.springbootadvanced.domain.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserFormTest {

    @Test
    void toUser() {
        UserForm userForm = new UserForm();
        userForm.setUsername("username");
        userForm.setPassword("123456");
        userForm.setConfirmPassword("123456");
        userForm.setEmail("a@b.com");
        userForm.setPhone("13200001111");

        User user = userForm.toUser();
        assertEquals(userForm.getUsername(), user.getUsername());
    }
}