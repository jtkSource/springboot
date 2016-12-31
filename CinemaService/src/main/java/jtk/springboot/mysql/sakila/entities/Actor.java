package jtk.springboot.mysql.sakila.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.ResourceSupport;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

/**
 * Created by jubin on 29/12/16.
 */
@Entity
@Table(name = "actor", schema="sakila")
public class Actor extends ResourceSupport implements Serializable   {

    @Id
    @Column(name = "actor_id")
    @JsonProperty("actorId")
    private int actorId;

    @JsonProperty("firstName")
    @Column(name = "first_name")
    private String firstName;

    @JsonProperty("lastName")
    @Column(name = "last_name")
    private String lastName;

    @JsonProperty("lastUpdate")
    @Column(name = "last_update")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date lastUpdate;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinTable(name = "film_actor",schema = "sakila",
    joinColumns = @JoinColumn(name = "actor_id"),
    inverseJoinColumns = @JoinColumn(name = "film_id"))
    @JsonProperty("films")
    private Collection<Films> films;
}
