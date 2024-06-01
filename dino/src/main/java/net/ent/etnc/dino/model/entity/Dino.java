package net.ent.etnc.dino.model.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
public class Dino {

    private UUID id = UUID.randomUUID();

    @NotBlank
    @Size(min = 2)
    private String nom;

    @NotNull
    @PastOrPresent
    private LocalDate dateNaissance;
}
