package pl.gregorymartin.spacetravelagency.travel.model.projection;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor
public class BookingWriteModel {

    private String name;
    private String origin;
    private String destination;
    private LocalDateTime date;

    public BookingWriteModel(final String name, final String origin, final String destination, final LocalDateTime date) {
        this.name = name;
        this.origin = origin;
        this.destination = destination;
        this.date = date;
    }
}
