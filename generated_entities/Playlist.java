package edu.gcu.cst339.chinook.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "playlist")
class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "playlist_id", nullable = false)
    private Integer playlistId;

    @Size(max = 120)
    @Column(name = "name", length = 120)
    private String name;

    protected Playlist() {
    }

    Integer getPlaylistId() {
        return playlistId;
    }

    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }
}
