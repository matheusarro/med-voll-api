package med.voll.api.dto;

import med.voll.api.entity.PatientEntity;

public record PatientsListDTO(
        Long id,
        String name,
        String cpf,
        String email
) {
    public PatientsListDTO(PatientEntity patient) {
        this(
                patient.getId(),
                patient.getName(),
                patient.getCpf(),
                patient.getEmail()
        );
    }
}
