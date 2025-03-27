package com.example.library_management.mapper;

import com.example.library_management.dto.UserDTO;
import com.example.library_management.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "id", ignore = true) 
    @Mapping(target = "favoriteBooks", ignore = true)
    @Mapping(target = "role", ignore = true)
    User toUser(UserDTO userDTO);

    UserDTO toUserDTO(User user);
}
