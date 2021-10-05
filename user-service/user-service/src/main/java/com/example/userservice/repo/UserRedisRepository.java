package com.example.userservice.repo;

import com.example.userservice.model.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRedisRepository {

  public static final String HASH_KEY = "user_detail";

  @Autowired
  private RedisTemplate template;

  public UserDetails save(UserDetails userDetails) {
    template.opsForHash().put(HASH_KEY, userDetails.getId(), userDetails);
    return userDetails;
  }

  public List<UserDetails> findAll(){
    return template.opsForHash().values(HASH_KEY);
  }

  public UserDetails findUserDetailsByID(int id) {
    System.out.println("call findUserDetail from redis");
    return (UserDetails) template.opsForHash().get(HASH_KEY, id);

  }

  public String deleteUserDetails(int id) {
    template.opsForHash().delete(HASH_KEY, id);
    return "user_removed";
  }
}
