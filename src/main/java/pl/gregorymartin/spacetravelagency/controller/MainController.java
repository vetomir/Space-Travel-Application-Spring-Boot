package pl.gregorymartin.spacetravelagency.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.gregorymartin.spacetravelagency.user.UserService;
import pl.gregorymartin.spacetravelagency.user.model.User;
import pl.gregorymartin.spacetravelagency.user.model.projection.UserWriteModel;

import javax.servlet.http.HttpServletRequest;

@Controller
class MainController {

    private UserService service;


    public MainController(final UserService service) {
        this.service = service;
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }


    @GetMapping("/signup")
    String signup(
            Model model,
            UserWriteModel userWriteModel
    ) {
        model.addAttribute("userWriteModel", new UserWriteModel());
        return "register";
    }

    @PostMapping("/register")
    String createUser(
            Model model,
            UserWriteModel userWriteModel,
            HttpServletRequest request
    ) {
        User user = new User(
                userWriteModel.getName(),
                userWriteModel.getUsername(),
                userWriteModel.getPassword(),
                userWriteModel.getEmail()
        );

        service.createUserWithToken(
                user,
                request,
                userWriteModel.getRole()
        );

        model.addAttribute("user", new User());
        model.addAttribute("userWriteModel", new UserWriteModel());
        return "redirect:/login";
    }


    @RequestMapping("/verify-token")
    public ModelAndView verifyToken(@RequestParam String token){
        service.verifyToken(token);

        return new ModelAndView("redirect:/login");
    }
}
