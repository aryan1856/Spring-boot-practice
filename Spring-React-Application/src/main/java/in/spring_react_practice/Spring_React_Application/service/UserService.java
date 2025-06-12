package in.spring_react_practice.Spring_React_Application.service;

import in.spring_react_practice.Spring_React_Application.model.User;

public interface UserService {

    public User findUserByJwtToken(String jwt) throws Exception;

    public User findUserByEmail(String email) throws Exception;

}