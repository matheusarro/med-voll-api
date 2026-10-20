package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.dto.DoctorsListDTO;
import med.voll.api.dto.PatientDTO;
import med.voll.api.dto.PatientsListDTO;
import med.voll.api.entity.AddressEntity;
import med.voll.api.entity.PatientEntity;
import med.voll.api.repository.AddressRepository;
import med.voll.api.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patients")
public class PatientController {
    @Autowired
    private PatientRepository repository;

    @Autowired
    private AddressRepository addressRepository;

    @PostMapping
    @Transactional
    public void createPatient(@RequestBody @Valid PatientDTO patient) {
        AddressEntity address = addressRepository.save(new AddressEntity(patient.address()));
        repository.save(new PatientEntity(patient, address));
    }

    @GetMapping
    public Page<PatientsListDTO> getPatients(
            @PageableDefault(size = 10, sort = {"name"})
            Pageable pagination
    ) {
        return repository
                .findAll(pagination)
                .map(PatientsListDTO::new);
    }
}
