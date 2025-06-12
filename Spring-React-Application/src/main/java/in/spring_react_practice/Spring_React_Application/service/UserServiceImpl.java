package in.spring_react_practice.Spring_React_Application.service;

import in.spring_react_practice.Spring_React_Application.config.JwtProvider;
import in.spring_react_practice.Spring_React_Application.model.User;
import in.spring_react_practice.Spring_React_Application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public User findUserByJwtToken(String jwt) throws Exception {
        String email = jwtProvider.getEmailFromJwt(jwt);
        User user = userRepository.findByEmail(email);
        return user;
    }

    @Override
    public User findUserByEmail(String email) throws Exception {
        User user = userRepository.findByEmail(email);
        if(user==null)
            throw new Exception("User not found");
        return user;
    }
}