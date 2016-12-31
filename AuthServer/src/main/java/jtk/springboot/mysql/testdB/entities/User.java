package jtk.springboot.mysql.testdB.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jtk.springboot.db.util.IntToBooleanConvertor;

import javax.persistence.*;

/**
 * Created by jubin on 31/12/16.
 */
@Entity
@Table(name = "users" , schema = "testdb")
public class User {

    @Id
    @Column(name = "user_id")
    @JsonProperty("userId")
    private int userId;


    @JsonProperty("firstName")
    @Column(name = "first_name")
    private String firstName;

    @JsonProperty("lastName")
    @Column(name = "last_name")
    private String lastName;

    @Convert(converter = IntToBooleanConvertor.class)
    @JsonProperty("active")
    @Column(name = "active")
    private boolean active;

    @JsonProperty("username")
    @Column(name = "username")
    private String username;

    @JsonProperty("password")
    @Column(name = "password")
    @JsonIgnore
    private String password;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public boolean isActive() {
        return active;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
