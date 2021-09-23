package com.example.resourceservice.repo;

import com.example.resourceservice.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailRepository extends JpaRepository<UserDetails, Long> {
}
