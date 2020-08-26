package pl.gregorymartin.spacetravelagency.user.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.gregorymartin.spacetravelagency.travel.model.Booking;

import javax.persistence.*;
import java.util.Collection;


@Entity
@Setter @Getter @NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String username;
    private String password;
    private String email;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    Collection<Role> roles;


    @JsonBackReference
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_bookings",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "booking_id", referencedColumnName = "id"))
    Collection<Booking> bookings;

    public User(final String name, final String username, final String password, final String email) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
    }
    public void newRole(Role newRole){
        this.roles.add(newRole);
    }
    public void newBooking(Booking newBooking){
        this.bookings.add(newBooking);
    }

    public void toUpdate(User toUpdate){
        this.name = toUpdate.name;
        this.username = toUpdate.username;
        this.password = toUpdate.password;
        this.email = toUpdate.email;
    }

}
