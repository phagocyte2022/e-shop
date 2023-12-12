package edu.trainingprojects.eshop.service;

import edu.trainingprojects.eshop.dao.UserRepository;
import edu.trainingprojects.eshop.domain.Role;
import edu.trainingprojects.eshop.domain.User;
import edu.trainingprojects.eshop.dto.UserDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean save(UserDTO userDTO) {
        if (!Objects.equals(userDTO.getPassword(), userDTO.getMatchingPassword())){
            throw new RuntimeException("password does not match");
        }
        User user = User.builder()
                .name(userDTO.getUsername())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .email(userDTO.getEmail())
                .role(Role.CLIENT)
                .build();

        userRepository.save(user);
        return true;
    }

    @Override
    public List<UserDTO> getAll() {
        return userRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private UserDTO toDto(User user) {
        return UserDTO.builder()
                .username(user.getName())
                .email(user.getEmail())
                .build();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       User user = userRepository.findFirstByName(username);
       if (user == null){
           throw new UsernameNotFoundException(username + ": User with such name not found");
       }

       List<GrantedAuthority> roles = new ArrayList<>();
       roles.add(new SimpleGrantedAuthority(user.getRole().name()));

       return new org.springframework.security.core.userdetails.User(
               user.getName(),
               user.getPassword(),
               roles
       );

    }
}
