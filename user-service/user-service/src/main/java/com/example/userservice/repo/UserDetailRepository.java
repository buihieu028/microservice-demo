package com.example.userservice.repo;

import com.example.userservice.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailRepository extends JpaRepository<UserDetails, Long> {
}
