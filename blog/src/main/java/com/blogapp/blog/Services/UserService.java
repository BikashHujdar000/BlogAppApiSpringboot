package com.blogapp.blog.Services;



import java.util.List;

import com.blogapp.blog.Payloads.UserDTO;

public interface UserService {

  UserDTO createUser(UserDTO userDTO);
    

   UserDTO updateUser(UserDTO userDTO, Integer userId);

  UserDTO getUserById(Integer userrId);
     
   List<UserDTO> getAllUsers();

 void deleteUser(Integer userId);



    
}
