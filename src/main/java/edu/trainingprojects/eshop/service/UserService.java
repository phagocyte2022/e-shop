package edu.trainingprojects.eshop.service;

import edu.trainingprojects.eshop.domain.User;
import edu.trainingprojects.eshop.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService { //for security reasons implemented through interface
    boolean save(UserDTO userDTO);
    List<UserDTO> getAll();

    User findByName(String name);
    void updateProfile(UserDTO dto);


}

