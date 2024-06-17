package com.naveenk.Movies_Library2.repository;

import com.naveenk.Movies_Library2.entity.Role;
import com.naveenk.Movies_Library2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
    User findByRole(Role role);
}
