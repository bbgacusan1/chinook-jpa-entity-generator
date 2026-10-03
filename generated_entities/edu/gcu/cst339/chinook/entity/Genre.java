package edu.gcu.cst339.chinook.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "genre")
class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "genre_id", nullable = false)
    private Integer genreId;

    @Size(max = 120)
    @Column(name = "name", length = 120)
    private String name;

    protected Genre() {
    }

    Integer getGenreId() {
        return genreId;
    }

    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }
}
