package edu.gcu.cst339.chinook.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "album_id")
    private Album album;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "media_type_id", nullable = false)
    private MediaType mediaType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "genre_id")
    private Genre genre;

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

    Album getAlbum() {
        return album;
    }

    void setAlbum(Album album) {
        this.album = album;
    }

    MediaType getMediaType() {
        return mediaType;
    }

    void setMediaType(MediaType mediaType) {
        this.mediaType = mediaType;
    }

    Genre getGenre() {
        return genre;
    }

    void setGenre(Genre genre) {
        this.genre = genre;
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
