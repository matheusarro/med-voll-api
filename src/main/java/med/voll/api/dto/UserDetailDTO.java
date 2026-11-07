package med.voll.api.dto;

import med.voll.api.entity.UserEntity;

public record UserDetailDTO(
        Long id,
        String username
) {
    public UserDetailDTO(UserEntity user) {
        this(
                user.getId(),
                user.getUsername()
        );
    }
}
