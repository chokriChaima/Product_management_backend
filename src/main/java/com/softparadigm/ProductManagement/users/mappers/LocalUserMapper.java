package com.softparadigm.ProductManagement.users.mappers;

import com.softparadigm.ProductManagement.users.dtos.UserDTO;
import com.softparadigm.ProductManagement.users.entities.LocalUser;

public class LocalUserMapper {

    public static UserDTO convertLocalUserTDto(LocalUser user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(user.getEmail());
        userDTO.setName(user.getName());
        return userDTO;
    }


    public static LocalUser convertUserDtoToLocalUser(UserDTO userDTO){
        LocalUser localUser = new LocalUser();
        localUser.setEmail(userDTO.getEmail());
        localUser.setName(userDTO.getName());
        localUser.setPassword(userDTO.getPassword());
        return localUser;
    }
}
