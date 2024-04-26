package com.example.csiauth.repository.user;

import com.example.csiauth.model.topics.Topic;
import com.example.csiauth.model.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    UserDetails findByLogin(String login);
    boolean existsByLogin(String login);

    @Query("FROM User AS u1 WHERE u1.login = :login")
    User getUserLogin(@Param("login") String login);
}
