package com.blogapp.blog.Controllers;

import java.util.List;
import org.springframework.security.core.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogapp.blog.Entities.LoginRequest;
import com.blogapp.blog.JWT.UserAuthService;

import com.blogapp.blog.Payloads.ApiResponse;
import com.blogapp.blog.Payloads.UserDTO;
import com.blogapp.blog.Repositories.UserRepository;
import com.blogapp.blog.Services.UserService;
import com.blogapp.blog.Utils.JwtUtils;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/users/")
public class UserCotroller {

  @Autowired

  private UserService userService;

  @Autowired
  private UserRepository userRepository;



  @Autowired
  private UserAuthService userAuthService;

  @Autowired
  private JwtUtils jwtUtils;

  @Autowired
  private AuthenticationManager authenticationManager;


  // create
  @PostMapping("/")
  public ResponseEntity<UserDTO> createUser( @Valid @RequestBody UserDTO userDTO) {

    UserDTO createdUserDTO = this.userService.createUser(userDTO);
    return new ResponseEntity<>(createdUserDTO, HttpStatus.CREATED);

  }

  // update user

  @PutMapping("/{userId}")
  public ResponseEntity<UserDTO> updateUser( @Valid @RequestBody UserDTO userDTO, @PathVariable("userId") Integer userId) {

    UserDTO updatedUserDTO = this.userService.updateUser(userDTO, userId);

    return ResponseEntity.ok(updatedUserDTO);

    // return new ResponseEntity<>(updatedUserDTO,HttpStatus.OK);
  }

  // get all users

  @GetMapping("/")
  public ResponseEntity<List<UserDTO>> getAllUsers() {

    return ResponseEntity.ok(this.userService.getAllUsers());

  }

  // delete user
  @DeleteMapping("/{userId}")
  public ResponseEntity<ApiResponse> deleteuser(@PathVariable("userId") Integer userId) {
    this.userService.deleteUser(userId);

   // return new ResponseEntity(java.util.Map.of("message", "user Deleted Sucessfully"), HttpStatus.OK);
  return new ResponseEntity(new ApiResponse("Sucessfully Deleted User",true),HttpStatus.OK);

    
  }

  // get by id

  @GetMapping("/{userId}")
  public ResponseEntity<UserDTO> getuserById(@PathVariable("userId") Integer userId) {
    UserDTO userDTO = this.userService.getUserById(userId);

    return new ResponseEntity<>(userDTO, HttpStatus.OK);

  }




  @PostMapping("/login")
  public ResponseEntity<LoginResposne> login(@RequestBody LoginRequest loginRequest)
    {
            
      System.out.println("Received Login Request: " + loginRequest);
      System.out.println("User Email: " + loginRequest.getUserEmail());
  


        try {
            this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUserEmail(), loginRequest.getUserPassword())
            );
        } catch (AuthenticationException e) {

          System.out.println(" authentication manager Failed");
             return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        UserDetails userDetails;

        try{
            userDetails = this.userAuthService.loadUserByUsername(loginRequest.getUserEmail());
            if(userDetails!= null)
            {
                System.out.println("Got user Details");
            }

          
        }catch(UsernameNotFoundException e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        }

        String token = this.jwtUtils.generateToken(userDetails.getUsername()); 
        System.out.println("token = == "+token);
        LoginResposne resposne = new LoginResposne();
        resposne.setToken(token);


        return new ResponseEntity<LoginResposne>(resposne,HttpStatus.OK);

      }



@GetMapping("/security")
public String test()
{

  String userEmail = "z@gmail.com";
  try{
    var username = this.userRepository.findByUserEmail(userEmail).toString();
      return username.toString();
  }
 catch(Exception e)
 {
  return e.getMessage();
 }

}



}


  
