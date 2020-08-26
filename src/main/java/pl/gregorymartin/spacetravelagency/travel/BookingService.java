package pl.gregorymartin.spacetravelagency.travel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.gregorymartin.spacetravelagency.travel.model.Booking;
import pl.gregorymartin.spacetravelagency.travel.model.BookingRepository;
import pl.gregorymartin.spacetravelagency.travel.model.Place;
import pl.gregorymartin.spacetravelagency.travel.model.PlacesRepository;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicReference;

@Service
class BookingService {
    Logger logger = LoggerFactory.getLogger(BookingService.class);

    private BookingRepository bookingRepository;
    private PlacesRepository placesRepository;

    public BookingService(final BookingRepository bookingRepository, final PlacesRepository placesRepository) {
        this.bookingRepository = bookingRepository;
        this.placesRepository = placesRepository;
    }

    public Booking createNewBooking(String nameOfBooking, String nameOfOrigin, String nameOfDestination, LocalDateTime date){
        Booking booking = new Booking(nameOfBooking);
        try{
            booking.setOrigin(placesRepository.findByName(nameOfOrigin).get());
            booking.setDestination(placesRepository.findByName(nameOfDestination).get());
            booking.setDate(date);
        }
        catch (Exception e){
            logger.warn("New booking CANNOT be created, CHECK input parameters. " + e.getMessage());
        }
        return bookingRepository.save(booking);
    }

    public Place createNewPlace(String nameOfPlace){
        if(placesRepository.findByName(nameOfPlace).isEmpty()){
            return placesRepository.save(new Place(nameOfPlace));
        }
        else
            logger.warn("Place with name: " + nameOfPlace + " already EXISTS in repository");
            throw new IllegalArgumentException();
    }

    public Booking editBooking(Booking bookingToEdit, Long bookingId){
        final AtomicReference<Booking> result = new AtomicReference<Booking>();
        bookingRepository.findById(bookingId).ifPresent( x -> {
            x.toUpdate(bookingToEdit);
            result.set(bookingRepository.save(x));
        });
        return result.get();
    }

}
