package io.aspectleft.springbootadvanced.web;

import io.aspectleft.springbootadvanced.domain.User;
import io.aspectleft.springbootadvanced.domain.UserRepository;
import io.aspectleft.springbootadvanced.form.UserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/register")
    public String register(final Model model) {
        model.addAttribute("userForm", new UserForm());
        return "register";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String loginPost(@RequestParam final String username,
                            @RequestParam final String password,
                            final HttpSession session) {
        final User user = userRepository.findByUsernameAndPassword(username, password);
        if (user != null) {
            session.setAttribute("user", user);
            return "index";
        }
        return "login";
    }

    @GetMapping("/logout")
    public String logout(final HttpSession session) {
        session.removeAttribute("user");
        return "login";
    }

    @PostMapping("/register")
    public String registerPost(@Valid final UserForm userForm, final BindingResult result,
                               final Model model) {
        if (!result.hasErrors() && !userForm.confirmPassword()) {
            result.rejectValue("confirmPassword", "confirmError", "确认密码不一致");
        }
        if (result.hasErrors()) {
            result.getFieldErrors().forEach(e -> System.out.println(String.format("%s : %s : %s",
                    e.getField(), e.getDefaultMessage(), e.getCode())));
            return "register";
        }

        userRepository.save(userForm.toUser());
        return "redirect:/login";
    }

    @GetMapping("/exception")
    public String testException() {
        throw new RuntimeException("Test exception");
    }
}
