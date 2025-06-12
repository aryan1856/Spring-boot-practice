package in.spring_react_practice.Spring_React_Application.repository;

import in.spring_react_practice.Spring_React_Application.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

}