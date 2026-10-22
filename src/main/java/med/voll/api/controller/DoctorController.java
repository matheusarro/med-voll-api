package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.dto.*;
import med.voll.api.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @PostMapping
    public ResponseEntity<DoctorDetailDTO> create(@RequestBody @Valid DoctorCreateDTO doctor, UriComponentsBuilder uriBuilder) {
        var doctorDTO = doctorService.create(doctor);
        var uri = uriBuilder.path("/doctors/{id}").buildAndExpand(doctorDTO.id()).toUri();
        return ResponseEntity.created(uri).body(doctorDTO);
    }

    @GetMapping
    public Page<DoctorsListDTO> getList(
            @PageableDefault(size = 10, sort = {"name"}) Pageable pagination
    ) {
        return doctorService.getList(pagination);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorDetailDTO> get(@PathVariable Long id) {
        DoctorDetailDTO doctor = doctorService.get(id);

        if (doctor == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(doctor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody @Valid DoctorUpdateDTO doctor) {
        doctorService.update(id, doctor);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        doctorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}