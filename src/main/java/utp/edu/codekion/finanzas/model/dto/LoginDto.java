package utp.edu.codekion.finanzas.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link utp.edu.codekion.finanzas.model.Usuario}
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto implements Serializable {

    @Size(max = 50)
    String email;


    @Size(max = 50)
    String password;
}