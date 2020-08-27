package pl.gregorymartin.spacetravelagency.user.model.projection;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class UserWriteModel {
    private String name;
    private String username;
    private String password;
    private String email;
    private String role;

    public UserWriteModel(final String name, final String username, final String password, final String email, final String role) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }
}
