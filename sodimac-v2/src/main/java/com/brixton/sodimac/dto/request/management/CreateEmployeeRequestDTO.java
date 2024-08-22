package com.brixton.sodimac.dto.request.management;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class CreateEmployeeRequestDTO {
    @NotNull(message = "Nombres no puede ser vacio")
    private String nombres;
    @NotNull(message = "Apellidos no puede ser vacio")
    private String apellidos;
    private String direccion;
    private String telefono;
    @Email
    @NotNull(message = "Email no puede ser vacio")
    private String email;
    @NotNull(message = "Debe pertenecer a un área")
    @Min(1)
    @Max(100)
    private String area;

    @NotNull(message = "Debe asociarse a un perfil")
    @Min(1)
    @Max(100)
    private String perfil;

//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
//    private LocalDate anDate;
//
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
//    private LocalDateTime anDateTime;

}
