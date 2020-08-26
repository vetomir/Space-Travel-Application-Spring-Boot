package pl.gregorymartin.spacetravelagency.travel.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.gregorymartin.spacetravelagency.user.model.Role;

import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    Optional<Booking> findByName(String nameOfBooking);
}
