package med.voll.api.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record DoctorUpdateDTO (
        String name,

        String phone,

        @Valid
        AddressUpdateDTO address
) {}
