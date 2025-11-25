package com.tuwaiq.repository_usermanagmentsystem.Controller;

import Api.ApiResponse;
import com.tuwaiq.repository_usermanagmentsystem.Model.User;
import com.tuwaiq.repository_usermanagmentsystem.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/add")
    public ResponseEntity<?> addUser(@RequestBody @Valid User user, Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }else {
            userService.addUser(user);
            return ResponseEntity.status(200).body(new ApiResponse("The user have been added successfully"));
        }
    }

    @GetMapping("/get")
    public ResponseEntity<?> getUsers(){
        List<User> users=userService.getUsers();
        if (users.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("There are no users to show"));
        }else {
            return ResponseEntity.status(200).body(users);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Integer id, @RequestBody @Valid User user,Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }else {
            if (userService.updateUser(id,user)){
                return ResponseEntity.status(200).body(new ApiResponse("The user have been added successfully"));
            }else {
                return ResponseEntity.status(400).body(new ApiResponse("There are no users with this id found"));
            }
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id){
        if (userService.deleteUser(id)){
            return ResponseEntity.status(200).body(new ApiResponse("The user have been deleted successfully"));
        }else {
            return ResponseEntity.status(400).body(new ApiResponse("There are no user with this id found"));
        }
    }

    @GetMapping("/login")
    public ResponseEntity<?> checkUsernameAndPassword(@RequestBody String username, @RequestBody String password){
        if (userService.checkUsernameAndPassword(username,password)){
            return ResponseEntity.status(200).body(new ApiResponse("The username and password match, you may login"));
        }else {
            return ResponseEntity.status(400).body(new ApiResponse("The username or/and password may be wrong, please try again"));
        }
    }

    @GetMapping("/get-by-email/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email){
        User user=userService.getUserByEmail(email);
        if (user==null){
            return ResponseEntity.status(400).body(new ApiResponse("There are no user with this email found"));
        }else {
            return ResponseEntity.status(200).body(user);
        }
    }

    @GetMapping("/get-by-role/{role}")
    public ResponseEntity<?> getUserByRole(@PathVariable String role){
        List<User> users=userService.getUsersWithRole(role);
        if (users.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("There are no users with this role found"));
        }else {
            return ResponseEntity.status(200).body(users);
        }
    }

    @GetMapping("/get-by-age/{age}")
    public ResponseEntity<?> getUserByAgeAndAbove(@PathVariable Integer age){
        List<User> users=userService.getUsersWithAgeAndAbove(age);
        if (users.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("There are no users with higher age found"));
        }else {
            return ResponseEntity.status(200).body(users);
        }
    }

}
