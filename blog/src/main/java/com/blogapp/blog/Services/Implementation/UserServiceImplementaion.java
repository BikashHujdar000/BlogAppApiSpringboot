package com.blogapp.blog.Services.Implementation;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.blogapp.blog.Entities.User;
import com.blogapp.blog.Exceptions.ResourceNotFoundException;
import com.blogapp.blog.Payloads.UserDTO;
import com.blogapp.blog.Repositories.UserRepository;
import com.blogapp.blog.Services.UserService;

@Service
public class UserServiceImplementaion implements UserService {



    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDTO createUser(UserDTO userDTO) {

        // why we using this because the repository is getting extended with the user
        // entity not useDto
        // why user dto because we can have many attributes of user but those attributes
        // that are necessary initally to create user profile are kept in useDtaTransfer

        User user = new User();
        user = this.dtoToUser(userDTO);

        String HashedPassword = this.passwordEncoder.encode(user.getUser_password());
        user.setUser_password(HashedPassword);

        // saving the users in database entity
        this.userRepository.save(user);

        return this.userToDTO(user);

    }

    @Override
    public UserDTO updateUser(UserDTO userDTO, Integer userId) {

        // okay lets build the logical part over here
        // first we will look for the user with the userId upcomming in params and get
        // the user obj if found
        // then we will set the new updates from the userDto that has come with new
        // update
        // finally we will save that user with returning again the dto object

        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("user", "userId", userId));

                user.setUser_name(userDTO.getUser_name());
                user.setUser_email(userDTO.getUser_email());
                user.setUser_password(userDTO.getUser_password());
                user.setUser_about(userDTO.getUser_about());

                User updatedUser = this.userRepository.save(user);



            return this.userToDTO(updatedUser);
    }

    @Override
    public UserDTO getUserById(Integer userrId) {
       
        User user = this.userRepository.findById(userrId).
      
        orElseThrow(()-> new ResourceNotFoundException("user", "Id", userrId));

        return this.userToDTO(user);
    }
   

    @Override
    public List<UserDTO> getAllUsers() {
       

    List<User> users = this.userRepository.findAll();

          List<UserDTO> userDTOs =   users.stream().map(user->this.userToDTO(user)).collect(Collectors.toList());

        return userDTOs;
    }

    @Override
    public void deleteUser(Integer userId) {
       

        User user = this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id", userId) );

            this.userRepository.delete(user);

    }

    public User dtoToUser(UserDTO userDTO) {

        //lets use  the model mapper 


        User user = this.modelMapper.map(userDTO, User.class);



        // when we mapping two models manually 


        // user.setUser_id(userDTO.getUser_id());
        // user.setUser_name(userDTO.getUser_name());
        // user.setUser_email(userDTO.getUser_email());
        // user.setUser_password(userDTO.getUser_password());
        // user.setUser_about(userDTO.getUser_about());
        return user;

    }

    public UserDTO userToDTO(User user) {

        UserDTO userDTO = this.modelMapper.map(user, UserDTO.class);

        // userDTO.setUser_id(user.getUser_id());
        // userDTO.setUser_name(user.getUser_name());
        // userDTO.setUser_email(user.getUser_email());
        // userDTO.setUser_password(user.getUser_password());
        // userDTO.setUser_about(user.getUser_about());
        return userDTO;
    }

}
