package med.voll.api.service;

import med.voll.api.dto.UserCreateDTO;
import med.voll.api.dto.UserDetailDTO;
import med.voll.api.entity.UserEntity;
import med.voll.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public UserDetailDTO create(UserCreateDTO user) {
        UserEntity userEntity = userRepository.save(new UserEntity(
                user.username(),
                passwordEncoder.encode(user.password())
        ));
        return new UserDetailDTO(userEntity);
    }
}
