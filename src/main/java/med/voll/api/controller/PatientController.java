package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.dto.*;
import med.voll.api.entity.AddressEntity;
import med.voll.api.entity.PatientEntity;
import med.voll.api.repository.AddressRepository;
import med.voll.api.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patients")
public class PatientController {
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private AddressRepository addressRepository;

    @PostMapping
    @Transactional
    public void create(@RequestBody @Valid PatientDTO patient) {
        AddressEntity address = addressRepository.save(new AddressEntity(patient.address()));
        patientRepository.save(new PatientEntity(patient, address));
    }

    @GetMapping
    public Page<PatientsListDTO> getList(
            @PageableDefault(size = 10, sort = {"name"})
            Pageable pagination
    ) {
        return patientRepository
                .findAll(pagination)
                .map(PatientsListDTO::new);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDetailDTO> get(@PathVariable Long id) {
        return patientRepository.findById(id)
                .map(PatientDetailDTO::new)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping
    @Transactional
    public void update(@RequestBody @Valid PatientUpdateDTO patient) {
        var patientAddress = patient.address();

        if (patient.address() != null) {
            var addressEntity = addressRepository.getReferenceById(patientAddress.id());
            addressEntity.update(patientAddress);
        }

        var patientEntity = patientRepository.getReferenceById(patient.id());
        patientEntity.update(patient);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void delete(@PathVariable Long id) {
        var patientEntity = patientRepository.getReferenceById(id);
        patientEntity.delete();
    }
}
