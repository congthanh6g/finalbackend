package com.example.finalapi.Controller;


import com.example.finalapi.Entity.ERole;
import com.example.finalapi.Entity.Role;
import com.example.finalapi.Entity.User;
import com.example.finalapi.Repository.RoleRepository;
import com.example.finalapi.Repository.UserRepository;
import com.example.finalapi.Security.Services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*" , maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }

    @GetMapping("/mod")
    @PreAuthorize("hasRole('MODERATOR')")
    public String moderatorAccess() {
        return "Moderator Content";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity adminAccess() {
        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR).orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        List<User> lists = userRepository.findAll();
        List<User> newlst = new ArrayList<>();
        for (int i = 0 ; i < lists.size() ; i++)
        {
            User user = lists.get(i);
            if(user.getRoles().contains(modRole)){
                newlst.add(user);
            }
        }
        return ResponseEntity.ok(newlst);
    }

}
