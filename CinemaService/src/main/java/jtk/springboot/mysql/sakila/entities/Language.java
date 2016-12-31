package jtk.springboot.mysql.sakila.entities;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.ResourceSupport;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by jubin on 1/1/17.
 */
@Entity
@Table(name = "language", schema="sakila")
public class Language extends ResourceSupport implements Serializable {

    @Id
    @Column(name = "language_id",nullable = false)
    @JsonProperty("languageId")
    private int languageId;


    @Column(name = "name")
    @JsonProperty("name")
    private String name;


    @JsonProperty("lastUpdate")
    @Column(name = "last_update")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date lastUpdate;

    @Override
    public String toString() {
        return name;
    }
}
