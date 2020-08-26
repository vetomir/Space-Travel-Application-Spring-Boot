package pl.gregorymartin.spacetravelagency.travel.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.gregorymartin.spacetravelagency.user.model.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Getter @Setter @NoArgsConstructor
public
class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToOne
    @JoinColumn(name = "origin_id")
    private Place origin;
    @OneToOne
    @JoinColumn(name = "destination_id")
    private Place destination;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "bookings")
    private Collection<User> users;

    private LocalDateTime date;

    public Booking(String name) {
        this.name = name;
    }

    public void toUpdate(Booking toUpdate){
        name = toUpdate.name;
        origin = toUpdate.origin;
        destination = toUpdate.destination;
        users = toUpdate.users;
        date = toUpdate.date;

    }
}
