package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.dto.UserCreateDTO;
import med.voll.api.dto.UserDetailDTO;
import med.voll.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserDetailDTO> create(@RequestBody @Valid UserCreateDTO userData, UriComponentsBuilder uriBuilder) {
        UserDetailDTO user = userService.create(userData);
        URI uri = uriBuilder.path("/users/{id}").buildAndExpand(user.id()).toUri();
        return ResponseEntity.created(uri).body(user);
    }
}
