package pl.gregorymartin.spacetravelagency.travel.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlacesRepository extends JpaRepository<Place, Long> {
    Optional<Place> findByName(String name);
}
