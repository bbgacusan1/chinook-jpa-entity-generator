package edu.gcu.cst339.chinook.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
@Table(name = "track")
class Track {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "track_id", nullable = false)
    private Integer trackId;

    @NotNull
    @Size(max = 200)
    @Column(name = "name", nullable = false, length = 200)
    private String name;

    // FK -> album.album_id
    @Column(name = "album_id")
    private Integer albumId;

    // FK -> media_type.media_type_id
    @NotNull
    @Column(name = "media_type_id", nullable = false)
    private Integer mediaTypeId;

    // FK -> genre.genre_id
    @Column(name = "genre_id")
    private Integer genreId;

    @Size(max = 220)
    @Column(name = "composer", length = 220)
    private String composer;

    @NotNull
    @Column(name = "milliseconds", nullable = false)
    private Integer milliseconds;

    @Column(name = "bytes")
    private Integer bytes;

    @NotNull
    @Column(name = "unit_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal unitPrice;

    protected Track() {
    }

    Integer getTrackId() {
        return trackId;
    }

    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    Integer getAlbumId() {
        return albumId;
    }

    void setAlbumId(Integer albumId) {
        this.albumId = albumId;
    }

    Integer getMediaTypeId() {
        return mediaTypeId;
    }

    void setMediaTypeId(Integer mediaTypeId) {
        this.mediaTypeId = mediaTypeId;
    }

    Integer getGenreId() {
        return genreId;
    }

    void setGenreId(Integer genreId) {
        this.genreId = genreId;
    }

    String getComposer() {
        return composer;
    }

    void setComposer(String composer) {
        this.composer = composer;
    }

    Integer getMilliseconds() {
        return milliseconds;
    }

    void setMilliseconds(Integer milliseconds) {
        this.milliseconds = milliseconds;
    }

    Integer getBytes() {
        return bytes;
    }

    void setBytes(Integer bytes) {
        this.bytes = bytes;
    }

    BigDecimal getUnitPrice() {
        return unitPrice;
    }

    void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }
}
