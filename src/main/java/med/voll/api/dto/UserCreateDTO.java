package med.voll.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserCreateDTO(
        @NotBlank
        @Email
        String username,

        @NotBlank
        String password
) {
}
