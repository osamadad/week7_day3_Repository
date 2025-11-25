package com.tuwaiq.repository_usermanagmentsystem.Repository;

import com.tuwaiq.repository_usermanagmentsystem.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    User findUserById(Integer id);

    User findUserByEmail(String email);

    User getUserByUserName(String userName);

    @Query("SELECT user from User user where user.role=?1")
    List<User> getUsersWithRole(String role);

    @Query("select user from User user where user.age>= ?1")
    List<User> getUsersWithAgeAndAbove(Integer age);
}
