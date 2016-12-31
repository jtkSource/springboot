package jtk.springboot.mysql.sakila.entities;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.ResourceSupport;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

/**
 * Created by jubin on 29/12/16.
 */

@Entity
@Table(name = "film", schema="sakila")
public class Films extends ResourceSupport implements Serializable {

    @Id
    @Column(name = "film_id",nullable = false)
    @JsonProperty("filmId")
    private int filmId;

    @Column(name = "title",nullable = false)
    @JsonProperty("title")
    private String title;

    @Column(name = "description")
    @JsonProperty("description")
    private String description;

    @Column(name = "release_year")
    @JsonProperty("releaseYear")
    private int releaseYear;


    @Column(name = "rental_duration",nullable = false)
    @JsonProperty("rentalDuration")
    private Integer rentalDuration;


    @Column(name = "rental_rate",nullable = false)
    @JsonProperty("rentalRate")
    private float rentalRate;

    @Column(name = "length")
    @JsonProperty("length")
    private Integer length;

    @Column(name = "replacement_cost",nullable = false)
    @JsonProperty("replacementCost")
    private float replacementCost;


    @Convert(converter = RatingConvertor.class)
    @Column(name = "rating")
    @JsonProperty("rating")
    private Rating rating;

    @Column(name = "special_features")
    @JsonProperty("specialFeatures")
    private String specialFeatures;


    @JsonProperty("lastUpdate")
    @Column(name = "last_update")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date lastUpdate;


    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinTable(name = "film_actor",schema = "sakila",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id"))
    @JsonProperty("actors")
    private Collection<Actor> actors;


    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "language_id" ,
            insertable = false,updatable = false,nullable = true)
    @JsonProperty("language")
    private Language languages;


    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "original_language_id",
            insertable = false,updatable = false,nullable = true)
    @JsonProperty("origLanguage")
    private Language origLanguage;


    public static enum Rating {
        UNRATED("UNRATED"),
        G("G"),
        PG("PG"),
        PG13("PG-13"),
        R("R"),
        NC17("NC-17");

        private String name;

        private Rating(String name){
            this.name=name;
        }

        @Override
        public String toString() {
            return name;
        }

    }

}

