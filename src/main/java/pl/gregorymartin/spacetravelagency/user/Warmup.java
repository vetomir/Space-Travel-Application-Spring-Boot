package pl.gregorymartin.spacetravelagency.user;

import org.springframework.stereotype.Component;
import pl.gregorymartin.spacetravelagency.travel.BookingService;
import pl.gregorymartin.spacetravelagency.user.model.User;

import java.time.LocalDateTime;

@Component
public class Warmup {

    private final UserService userService;
    private final BookingService bookingService;

    public Warmup(final UserService userService, final BookingService bookingService) {
        this.userService = userService;
        this.bookingService = bookingService;


        // ROLES

        userService.createRole("User");
        userService.createRole("Author");
        userService.createRole("Admin");

        // USERS

        userService.createUser(new User("Administrator", "admin", "admin123", "vedsgf"),"admin");
        userService.createUser(new User("Zenon Martyniuk", "wieszcz", "jestemgównem123", "vedsgf"),"author");
        userService.createUser(new User("Zenon Martyniuk", "wieszcz", "jestemgównem123", "vedsgf"),"admin");
        userService.createUser(new User("Zenon Martyniuk", "wieszcz", "jestemgównem123", "vedsgf"),"user");

        // PLACES

        bookingService.createNewPlace("Poznań");
        bookingService.createNewPlace("Wrocław");
        bookingService.createNewPlace("Warszawa");
        bookingService.createNewPlace("Szczecin");
        bookingService.createNewPlace("Kraków");

        // BOOKINGS

        bookingService.createNewBooking("PL001", "Wrocław", "Szczecin", LocalDateTime.now());
        bookingService.createNewBooking("PL002", "Warszawa", "Kraków", LocalDateTime.now());
        bookingService.createNewBooking("PL003", "Poznań", "Warszawa", LocalDateTime.now());
        bookingService.createNewBooking("PL004", "Szczecin", "Kraków", LocalDateTime.now());
        bookingService.createNewBooking("PL005", "Warszawa", "Poznań", LocalDateTime.now());
        bookingService.createNewBooking("PL006", "Szczecin", "Kraków", LocalDateTime.now());
        bookingService.addUserToBooking(2L,1L);
        bookingService.addUserToBooking(1L,1L);
        bookingService.addUserToBooking(3L,1L);
        /*bookingService.addUserToBooking(1L,2L);
        bookingService.addUserToBooking(3L,3L);
        bookingService.addUserToBooking(1L,4L);
        bookingService.addUserToBooking(2L,5L);
        bookingService.addUserToBooking(2L,6L);
        bookingService.addUserToBooking(3L,7L);*/

    }

}
