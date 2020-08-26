package pl.gregorymartin.spacetravelagency.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.gregorymartin.spacetravelagency.travel.model.Booking;
import pl.gregorymartin.spacetravelagency.travel.model.Place;
import pl.gregorymartin.spacetravelagency.user.model.Role;
import pl.gregorymartin.spacetravelagency.user.model.RoleRepository;
import pl.gregorymartin.spacetravelagency.user.model.User;
import pl.gregorymartin.spacetravelagency.user.model.UserRepository;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
class UserApi {
    Logger logger = LoggerFactory.getLogger(UserApi.class);
    private final UserService userService;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserApi(final UserService userService, final UserRepository userRepository, final RoleRepository roleRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @GetMapping("/all")
    ResponseEntity<List<User>> readAllUsers() {
        logger.warn( "Exposing ALL the Users!" );
        return ResponseEntity.ok(userRepository.findAll());
    }

    @GetMapping("/role/all")
    ResponseEntity<List<Role>> readAllRoles() {
        logger.warn( "Exposing ALL the Roles!" );
        return ResponseEntity.ok(roleRepository.findAll());
    }

    @PostMapping("/new")
    ResponseEntity<User> createUser(@RequestBody User user, @RequestHeader(required = false) String role) {
        if(role.isEmpty()){
            role = null;
        }
        User result = userService.createUser( user , role );

        logger.info("New User with name=" + result.getName() + " CREATED ");
        return ResponseEntity.created( URI.create("/" + result.getId()) ).body(result);
    }

    @PostMapping("/role/new")
    ResponseEntity<Role> createRole(@RequestHeader String role) {
        Role result = userService.createRole(role);

        logger.info("New Role with name=" + result.getName() + " CREATED ");
        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
    }


    @PatchMapping("/{id}/edit")
    ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user ) {
        User result = userService.editUser(id, user);

        logger.info("New User with name=" + result.getName() + " UPDATED ");
        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
    }

    @PatchMapping("/role/{id}/edit")
    ResponseEntity<Role> updateRole(@PathVariable Long id, @RequestHeader String newName ) {
        Role result = userService.editRole(id, newName);

        logger.info("New Role with name=" + result.getName() + " UPDATED ");
        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
    }

    @DeleteMapping("/{id}/delete")
    ResponseEntity<Booking> deleteUser(@PathVariable Long id) {
        boolean result = userService.deleteUser(id);
        if(result){
            logger.info("User with id=" + id + " DELETED");
            return ResponseEntity.ok().build();
        }
        else
            logger.warn("User Is NOT EXISTS!");
            return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/role/{id}/delete")
    ResponseEntity<Place> deleteRole(@PathVariable Long id) {
        try{
            boolean result = userService.deleteRole(id);
            if(result){
                logger.info("Role with id=" + id + " DELETED");
                return ResponseEntity.ok().build();
            }
            else
                logger.warn("Role Is NOT EXISTS!");
                return ResponseEntity.notFound().build();
        }
        catch (Exception e){
            logger.warn("Role CANNOT BE on Use!");
            return ResponseEntity.notFound().build();
        }
    }
}
