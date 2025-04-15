package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<?> createUser(User user){
        try {
            User find = userRepository.findByUsername(user.getUsername()).orElse(null);
            if(find != null)
                return ResponseEntity.status(HttpStatus.CONFLICT).body("username already exists");
            userRepository.save(user);
            return ResponseEntity.status(HttpStatus.OK).body("user created successfully");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }

    public ResponseEntity<?> getAll(){
        try{
            List<User> userList = userRepository.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(userList);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }

    public ResponseEntity<?> updateUser(User user, String username) {
        try {
            User existingUser = userRepository.findByUsername(username).orElse(null);
            if(existingUser == null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("user doesn't exists");
            if(user.getUsername() != null)
                existingUser.setUsername(user.getUsername());
            if(user.getAge() != null)
                existingUser.setAge((user.getAge()));
            if(user.getPassword() != null)
                existingUser.setPassword(user.getPassword());
            userRepository.save(existingUser);
            return ResponseEntity.status(HttpStatus.OK).body("user updated successfully");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }
}
