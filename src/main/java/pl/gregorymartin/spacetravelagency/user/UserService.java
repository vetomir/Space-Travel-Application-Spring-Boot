package pl.gregorymartin.spacetravelagency.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.gregorymartin.spacetravelagency.MailSenderService;
import pl.gregorymartin.spacetravelagency.user.model.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
public
class UserService {
    Logger logger = LoggerFactory.getLogger(UserService.class);

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    private final VerificationTokenRepo verificationTokenRepo;
    private MailSenderService mailSenderService;

    public UserService(final RoleRepository roleRepository, final UserRepository userRepository, final VerificationTokenRepo verificationTokenRepo, final PasswordEncoder passwordEncoder, final MailSenderService mailSenderService) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.verificationTokenRepo = verificationTokenRepo;
        this.passwordEncoder = passwordEncoder;
        this.mailSenderService = mailSenderService;
    }

    public List<User> readAllUsers(){
        return userRepository.findAll();
    }

    public User readUserById(Long id){
        return userRepository.findById(id).get();
    }

    public List<Role> readAllRoles(){
        return roleRepository.findAll();
    }

    public User createUser(User toCreate, String role) {

        return userRepository.save(addNewUser(toCreate, role));
    }

    public User createUserWithToken(User create, HttpServletRequest request, String role){
        User toCreate = addNewUser(create, role);
        //
        String token = UUID.randomUUID().toString();

        VerificationToken verificationToken = new VerificationToken(token,toCreate);
        verificationTokenRepo.save(verificationToken);

        String url = "http://" +
                request.getServerName() +
                ":" + request.getServerPort() +
                request.getContextPath() +
                "/verify-token?token=" + token;

        try {
            mailSenderService.sendMail(
                    toCreate.getUsername(), "Verification Token", url, false);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return userRepository.save(toCreate);
    }

    public void verifyToken(final String token) {
        User appUser = verificationTokenRepo.findByValue(token).getUser();
        appUser.toggleEnable();
        userRepository.save(appUser);
    }

    public Role createRole(String nameOfRole){
        nameOfRole = "ROLE_" + nameOfRole.toUpperCase();
        return roleRepository.save(new Role(nameOfRole));
    }

    public User editUser(Long id, User toEdit){
        Optional<User> updatedUser = userRepository.findById(id);
        updatedUser.ifPresent(x -> {
            x.toUpdate(toEdit);
            userRepository.save(x);
        });
        return updatedUser.get();
    }

    public Role editRole(Long id, String toEdit){
        Optional<Role> updated = roleRepository.findById(id);
        updated.get().setName(toEdit);
        return updated.get();
    }

    public boolean deleteUser(Long id){
        try{
            userRepository.deleteById(id);
            return true;
        }
        catch (Exception e){
            logger.warn("DELETE req of user with id= " + id + " FAILED!. " + e.getMessage());
        }
        return false;
    }
    public boolean deleteRole(Long id){
        try{
            roleRepository.deleteById(id);
            return true;
        }
        catch (Exception e){
            logger.warn("DELETE req of role with id= " + id + " FAILED!. " + e.getMessage());
        }
        return false;
    }

    //

    private User addNewUser(final User toCreate, final String role) {
        toCreate.newRole(roleRepository.findByName("ROLE_USER").get());
        if(!role.isBlank()){
            Optional<Role> roleByName = roleRepository.findByName("ROLE_" + role.toUpperCase());
            toCreate.newRole(roleByName.get());
        }
        toCreate.setPassword(passwordEncoder.encode(toCreate.getPassword()));
        return toCreate;
    }

}
