package med.voll.api.dto;

import med.voll.api.entity.DoctorEntity;

public record DoctorsListDTO(
        String name,
        String crm,
        String email,
        SpecialityDTO speciality
) {
    public DoctorsListDTO(DoctorEntity doctor) {
        this(
                doctor.getName(),
                doctor.getCrm(),
                doctor.getEmail(),
                doctor.getSpeciality()
        );
    }
}
