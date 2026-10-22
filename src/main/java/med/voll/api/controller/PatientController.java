package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.dto.PatientCreateDTO;
import med.voll.api.dto.PatientDetailDTO;
import med.voll.api.dto.PatientUpdateDTO;
import med.voll.api.dto.PatientsListDTO;
import med.voll.api.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @PostMapping
    public ResponseEntity<PatientDetailDTO> create(@RequestBody @Valid PatientCreateDTO patient, UriComponentsBuilder uriBuilder) {
        PatientDetailDTO patientDTO = patientService.create(patient);
        URI uri = uriBuilder.path("/patients/{id}").buildAndExpand(patientDTO.id()).toUri();
        return ResponseEntity.created(uri).body(patientDTO);
    }

    @GetMapping
    public Page<PatientsListDTO> getList(
            @PageableDefault(size = 10, sort = {"name"}) Pageable pagination
    ) {
        return patientService.getList(pagination);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDetailDTO> get(@PathVariable Long id) {
        PatientDetailDTO patient = patientService.get(id);

        if (patient == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(patient);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientDetailDTO> update(@PathVariable Long id, @RequestBody @Valid PatientUpdateDTO patient) {
        PatientDetailDTO patientData = patientService.update(id, patient);
        return ResponseEntity.ok(patientData);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        patientService.delete(id);
        return ResponseEntity.noContent().build();
    }
}