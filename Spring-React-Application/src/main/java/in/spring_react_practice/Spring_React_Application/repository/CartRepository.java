package in.spring_react_practice.Spring_React_Application.repository;

import in.spring_react_practice.Spring_React_Application.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {

}