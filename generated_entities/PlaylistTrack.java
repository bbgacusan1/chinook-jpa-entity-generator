package edu.gcu.cst339.chinook.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "playlist_track")
class PlaylistTrack {

    // FK -> playlist.playlist_id
    @Id
    @NotNull
    @Column(name = "playlist_id", nullable = false)
    private Integer playlistId;

    // FK -> track.track_id
    @Id
    @NotNull
    @Column(name = "track_id", nullable = false)
    private Integer trackId;

    protected PlaylistTrack() {
    }

    Integer getPlaylistId() {
        return playlistId;
    }

    void setPlaylistId(Integer playlistId) {
        this.playlistId = playlistId;
    }

    Integer getTrackId() {
        return trackId;
    }

    void setTrackId(Integer trackId) {
        this.trackId = trackId;
    }
}
