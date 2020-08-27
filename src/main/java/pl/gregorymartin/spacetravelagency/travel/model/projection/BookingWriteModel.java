package pl.gregorymartin.spacetravelagency.travel.model.projection;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor
public class BookingWriteModel {

    @NotBlank
    private String name;
    @NotBlank
    private String origin;
    @NotBlank
    private String destination;
    @NotBlank
    private LocalDateTime date;

    public BookingWriteModel(@NotBlank final String name, @NotBlank final String origin, @NotBlank final String destination, final LocalDateTime date) {
        this.name = name;
        this.origin = origin;
        this.destination = destination;
        this.date = date;
    }
}
