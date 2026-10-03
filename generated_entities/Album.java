package edu.gcu.cst339.chinook.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "album")
class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "album_id", nullable = false)
    private Integer albumId;

    @NotNull
    @Size(max = 160)
    @Column(name = "title", nullable = false, length = 160)
    private String title;

    // FK -> artist.artist_id
    @NotNull
    @Column(name = "artist_id", nullable = false)
    private Integer artistId;

    protected Album() {
    }

    Integer getAlbumId() {
        return albumId;
    }

    String getTitle() {
        return title;
    }

    void setTitle(String title) {
        this.title = title;
    }

    Integer getArtistId() {
        return artistId;
    }

    void setArtistId(Integer artistId) {
        this.artistId = artistId;
    }
}
