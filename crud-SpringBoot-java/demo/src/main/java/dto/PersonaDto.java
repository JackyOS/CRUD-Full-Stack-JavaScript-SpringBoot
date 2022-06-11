package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonaDto {

    private long id;

    @NotEmpty(message = "El nombre es Obligatorio")
    @Size(min= 2, message = "El nombre  de la persona deberia tener al menos dos caracteres")
    private String nombre;

    @Min(5)
    @Max(99)
    private int edad;

}
