package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.dto.DoctorDTO;
import med.voll.api.dto.DoctorUpdateDTO;
import med.voll.api.dto.DoctorsListDTO;
import med.voll.api.entity.AddressEntity;
import med.voll.api.entity.DoctorEntity;
import med.voll.api.repository.AddressRepository;
import med.voll.api.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctors")
public class DoctorController {
    @Autowired
    private DoctorRepository repository;

    @Autowired
    private AddressRepository addressRepository;

    @PostMapping
    @Transactional
    public void createDoctor(@RequestBody @Valid DoctorDTO doctor) {
        AddressEntity address = addressRepository.save(new AddressEntity(doctor.address()));
        repository.save(new DoctorEntity(doctor, address));
    }

    @GetMapping
    public Page<DoctorsListDTO> getDoctors(
            @PageableDefault(size = 10, sort = {"name"})
            Pageable pagination
    ) {
        return repository
                .findAll(pagination)
                .map(DoctorsListDTO::new);
    }

    @PutMapping
    @Transactional
    public void update(@RequestBody @Valid DoctorUpdateDTO doctor) {
        var doctorAddress = doctor.address();

        if (doctor.address() != null) {
            var addressEntity = addressRepository.getReferenceById(doctorAddress.id());
            addressEntity.update(doctorAddress);
        }

        var doctorEntity = repository.getReferenceById(doctor.id());
        doctorEntity.doctorUpdate(doctor);
    }
}
