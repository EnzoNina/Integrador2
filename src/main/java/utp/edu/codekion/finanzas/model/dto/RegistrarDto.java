package utp.edu.codekion.finanzas.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link utp.edu.codekion.finanzas.model.Usuario}
 */
@Getter
@Value
public class RegistrarDto implements Serializable {
    @NotNull
    @Size(max = 50)
    String email;
    @NotNull
    @Size(max = 50)
    String password;

    @NotNull
    @Size(max = 50)
    String nombres;
    @NotNull
    @Size(max = 50)
    String apellidop;
    @NotNull
    @Size(max = 50)
    String apellidom;
}