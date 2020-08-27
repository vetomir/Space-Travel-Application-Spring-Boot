package pl.gregorymartin.spacetravelagency.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.gregorymartin.spacetravelagency.user.model.User;
import pl.gregorymartin.spacetravelagency.user.model.UserRepository;
import pl.gregorymartin.spacetravelagency.user.model.VerificationTokenRepo;

import java.util.List;

@RestController
class CheckController {
    private final UserRepository userRepository;
    private final VerificationTokenRepo verificationTokenRepo;

    public CheckController(final UserRepository userRepository, final VerificationTokenRepo verificationTokenRepo) {
        this.userRepository = userRepository;
        this.verificationTokenRepo = verificationTokenRepo;
    }

    @GetMapping("/all")
    public List<User> showAll(){
        verificationTokenRepo.findAll().forEach(System.out::println);
        return userRepository.findAll();
    }

}
