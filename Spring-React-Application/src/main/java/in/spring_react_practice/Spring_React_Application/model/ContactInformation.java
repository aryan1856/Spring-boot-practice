package in.spring_react_practice.Spring_React_Application.model;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class ContactInformation {

    private String email;
    private String mobile;
    private String instagram;
    private String twitter;

}