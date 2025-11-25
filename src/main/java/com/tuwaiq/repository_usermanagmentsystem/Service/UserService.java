package com.tuwaiq.repository_usermanagmentsystem.Service;

import com.tuwaiq.repository_usermanagmentsystem.Model.User;
import com.tuwaiq.repository_usermanagmentsystem.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void addUser(User user){
        userRepository.save(user);
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public Boolean updateUser(Integer id, User user){
        User oldUser= userRepository.findUserById(id);
        if (oldUser==null){
            return false;
        }else {
            oldUser.setName(user.getName());
            oldUser.setUserName(user.getUserName());
            oldUser.setPassword(user.getPassword());
            oldUser.setEmail(user.getEmail());
            oldUser.setRole(user.getRole());
            oldUser.setAge(user.getAge());
            userRepository.save(oldUser);
            return true;
        }
    }

    public Boolean deleteUser(Integer id){
        User user=userRepository.findUserById(id);
        if (user==null){
            return false;
        }else {
            userRepository.delete(user);
            return true;
        }
    }

    public Boolean checkUsernameAndPassword(String username,String password){
        List<User> users=userRepository.getUserByUserName(username);
        if (users.isEmpty()){
            return false;
        }

        for (User user:users) {
            if (user.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }

    public User getUserByEmail(String email){
        return userRepository.findUserByEmail(email);
    }

    public List<User> getUsersWithRole(String role){
        return userRepository.getUsersWithRole(role);
    }

    public List<User> getUsersWithAgeAndAbove(Integer age){
        return userRepository.getUsersWithAgeAndAbove(age);
    }

}
