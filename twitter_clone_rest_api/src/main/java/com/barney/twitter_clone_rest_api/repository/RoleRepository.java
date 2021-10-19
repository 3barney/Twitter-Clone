package com.barney.twitter_clone_rest_api.repository;

import com.barney.twitter_clone_rest_api.model.Role;
import com.barney.twitter_clone_rest_api.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}
