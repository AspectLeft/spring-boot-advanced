package io.aspectleft.springbootadvanced.form;

import io.aspectleft.springbootadvanced.domain.User;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


@Data
public class UserForm {
    public static final String PHONE_REG = "^1[3-9]\\d{9}$";

    @NotBlank
    private String username;

    @NotBlank
    @Length(min = 6, message = "密码至少6位")
    private String password;

    @NotBlank
    private String confirmPassword;

    // Chinese phone number
    @Pattern(regexp = PHONE_REG, message = "请输入有效的手机号")
    private String phone;

    @Email
    private String email;

    public boolean confirmPassword() {
        return (this.password.equals(this.confirmPassword));
    }

    public User toUser() {
        return new UserFormConvert().convert(this);
    }

    private static class UserFormConvert implements FormConvert<UserForm, User> {
        @Override
        public User convert(UserForm userForm) {
            final User user = new User();
            BeanUtils.copyProperties(userForm, user);
            return user;
        }
    }
}
