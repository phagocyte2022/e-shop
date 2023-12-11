package edu.trainingprojects.eshop.service;

import edu.trainingprojects.eshop.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService { //for security reasons implemented through interface
    boolean save(UserDto userDTO);

}
