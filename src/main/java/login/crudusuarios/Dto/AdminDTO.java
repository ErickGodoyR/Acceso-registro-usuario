package login.crudusuarios.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminDTO {

    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private String password;
    private boolean enabled = true;

}
