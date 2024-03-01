package kz.example.practiceJavaEE.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users {
    private int id;
    private String email;
    private String password;
    private String fullName;
    private int roleId;
}