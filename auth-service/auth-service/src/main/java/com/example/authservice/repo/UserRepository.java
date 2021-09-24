package com.example.authservice.repo;

import com.example.commonservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  User findByEmail(String email);

  User findByIdAndEmail(Long id, String email);

}
