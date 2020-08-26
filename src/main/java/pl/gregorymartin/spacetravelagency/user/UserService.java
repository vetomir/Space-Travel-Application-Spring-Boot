package pl.gregorymartin.spacetravelagency.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.gregorymartin.spacetravelagency.user.model.Role;
import pl.gregorymartin.spacetravelagency.user.model.RoleRepository;
import pl.gregorymartin.spacetravelagency.user.model.User;
import pl.gregorymartin.spacetravelagency.user.model.UserRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
class UserService {
    Logger logger = LoggerFactory.getLogger(UserService.class);

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    //todo
    //private PasswordEncoder passwordEncoder;

    public UserService(final RoleRepository roleRepository, final UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
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

    public User createUser(User toCreate, String role){
        Collection<Role> rolesToSave = new ArrayList<>();

        rolesToSave.add(roleRepository.findByName("ROLE_USER").get());
        if(!role.isEmpty()){
            Optional<Role> roleByName = roleRepository.findByName("ROLE_" + role.toUpperCase());
            roleByName.ifPresent(rolesToSave::add);
        }

        //

        toCreate.setRoles(rolesToSave);


        return userRepository.save(toCreate);
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

}
