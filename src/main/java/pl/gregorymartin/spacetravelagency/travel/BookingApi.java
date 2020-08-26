package pl.gregorymartin.spacetravelagency.travel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.gregorymartin.spacetravelagency.travel.model.Booking;
import pl.gregorymartin.spacetravelagency.travel.model.BookingRepository;
import pl.gregorymartin.spacetravelagency.travel.model.Place;
import pl.gregorymartin.spacetravelagency.travel.model.PlacesRepository;
import pl.gregorymartin.spacetravelagency.travel.model.projection.BookingWriteModel;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
class BookingApi {
    Logger logger = LoggerFactory.getLogger(BookingApi.class);
    private final BookingService bookingService;
    private final BookingRepository bookingRepository;
    private final PlacesRepository placesRepository;


    public BookingApi(final BookingService bookingService, final BookingRepository bookingRepository, final PlacesRepository placesRepository) {
        this.bookingService = bookingService;
        this.bookingRepository = bookingRepository;
        this.placesRepository = placesRepository;
    }

    @GetMapping("/booking/all")
    ResponseEntity<List<Booking>> readAllBooking() {
        logger.warn("Exposing all the Bookings!");
        return ResponseEntity.ok(bookingRepository.findAll());
    }

    @GetMapping("/place/all")
    ResponseEntity<List<Place>> readAllPlaces() {
        logger.warn("Exposing all the Places!");
        return ResponseEntity.ok(placesRepository.findAll());
    }

    @PostMapping("/booking/new")
    ResponseEntity<Booking> createBooking(@RequestBody BookingWriteModel booking) {
        Booking result = bookingService.createNewBooking(
                booking.getName(),
                booking.getOrigin(),
                booking.getDestination(),
                booking.getDate()
        );
        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
    }

    @PostMapping("/place/new")
    ResponseEntity<Place> createPlace(@RequestHeader String place) {
        Place result = bookingService.createNewPlace(place);
        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
    }


    @PatchMapping("/booking/{id}/edit")
    ResponseEntity<Booking> editBooking(@PathVariable Long id, @RequestBody Booking newBooking ) {
        Booking result = bookingService.editBooking(newBooking, id);
        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
    }

    @PatchMapping("/booking/{bookingid}/add/{userid}")
    ResponseEntity<?> addUserToBooking(@PathVariable Long bookingid, @PathVariable Long userid ) {
        boolean result = bookingService.addUserToBooking(userid,bookingid);
        if(result){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
    @PatchMapping("/booking/{bookingid}/remove/{userid}")
    ResponseEntity<?> removeUserToBooking(@PathVariable Long bookingid, @PathVariable Long userid ) {
        boolean result = bookingService.deleteUserFromBooking( userid , bookingid );
        if(result){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }


    @PatchMapping("/place/{id}/edit")
    ResponseEntity<Place> editPlace(@PathVariable Long id, @RequestHeader String newName ) {
        Place result = bookingService.editPlace(newName, id);
        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
    }

    @DeleteMapping("/booking/{id}/delete")
    ResponseEntity<Booking> deleteBooking(@PathVariable Long id) {
        boolean result = bookingService.deleteBooking(id);
        if(result){
            return ResponseEntity.ok().build();
        }
        else
            return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/place/{id}/delete")
    ResponseEntity<Place> deletePlace(@PathVariable Long id) {
        try{
            boolean result = bookingService.deletePlace(id);
            if(result){
                return ResponseEntity.ok().build();
            }
            else
                logger.warn("Place Is NOT EXISTS!");
                return ResponseEntity.notFound().build();
        }
        catch (Exception e){
            logger.warn("Place CANNOT BE on Use!");
            return ResponseEntity.notFound().build();
        }
    }
}
