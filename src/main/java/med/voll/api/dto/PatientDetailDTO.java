package med.voll.api.dto;

import med.voll.api.entity.PatientEntity;

public record PatientDetailDTO(
        Long id,
        String name,
        String cpf,
        String email,
        String phone,
        AddressDetailDTO address
) {
    public PatientDetailDTO(PatientEntity patient) {
        this(
                patient.getId(),
                patient.getName(),
                patient.getCpf(),
                patient.getEmail(),
                patient.getPhone(),
                patient.getAddress() != null ? new AddressDetailDTO(patient.getAddress()) : null
        );
    }
}
