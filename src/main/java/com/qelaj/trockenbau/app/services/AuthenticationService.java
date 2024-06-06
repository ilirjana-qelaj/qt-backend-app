package com.qelaj.trockenbau.app.services;

import com.qelaj.trockenbau.app.dtos.UserDTO;
import com.qelaj.trockenbau.app.dtos.UserProfileDTO;
import com.qelaj.trockenbau.app.entities.User;
import com.qelaj.trockenbau.app.repositories.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Service;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private SecurityContextRepository securityContextRepository =
            new HttpSessionSecurityContextRepository();

    private final SecurityContextHolderStrategy securityContextHolderStrategy = SecurityContextHolder.getContextHolderStrategy();


    public AuthenticationService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User signup(User userInput) {
        User user = User.builder()
                .fullName(userInput.getFullName())
                .email(userInput.getEmail())
                .password(passwordEncoder.encode(userInput.getPassword())).build();

        return userRepository.save(user);
    }

    public User authenticate(User input, HttpServletRequest request, HttpServletResponse response) {
        Collection grantedAuthority = new ArrayList();

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                input.getEmail(),
                input.getPassword(), grantedAuthority
        );

        try {
            Authentication authentication = authenticationManager.authenticate(token);

            SecurityContext context = securityContextHolderStrategy.createEmptyContext();
            context.setAuthentication(authentication);
            securityContextHolderStrategy.setContext(context);
            securityContextRepository.saveContext(context, request, response);

            return userRepository.findByEmail(input.getEmail())
                    .orElse(null);
        }catch (AuthenticationException ex){
            return null;
        }
    }

    public UserDTO getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        User userFromDB = userRepository.findByEmail(user.getEmail()).orElse(null);
        if (userFromDB != null) {
            return UserDTO.builder().id(userFromDB.getId()).email(userFromDB.getEmail())
                    .fullName(userFromDB.getFullName()).fileType(userFromDB.getFileType())
                    .fileName(userFromDB.getFileName()).fileContent(userFromDB.getFileContent()).build();
        }
        return null;
    }

    public int updateUserInfo(Integer id, UserProfileDTO userDTO) throws SQLException, IOException {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            user.get().setFileName(userDTO.getFile().getOriginalFilename());
            user.get().setFileType(userDTO.getFile().getContentType());

            byte[] bytes = userDTO.getFile().getBytes();
            Blob blob = new SerialBlob(bytes);
            user.get().setFileContent(blob);

            user.get().setFullName(userDTO.getFullName());
            if(userDTO.getPassword() != null){
                user.get().setPassword(passwordEncoder.encode(userDTO.getPassword()));
            }
            userRepository.save(user.get());
            return 200;
        }else{
            return 404;
        }
    }

    public boolean checkCurrentPasswordMatches(String currentPassword){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        User userFromDB = userRepository.findByEmail(user.getEmail()).orElse(null);
        return passwordEncoder.matches(currentPassword,userFromDB.getPassword());
    }
}