package com.example.finalapi.Repository;

import com.example.finalapi.Entity.ERole;
import com.example.finalapi.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role , Integer> {
    Optional<Role> findByName(ERole name);
}
