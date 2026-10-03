package edu.gcu.cst339.chinook.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "playlist_track")
@IdClass(PlaylistTrackId.class)
class PlaylistTrack {

    @Id
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "playlist_id", nullable = false)
    private Playlist playlist;

    @Id
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "track_id", nullable = false)
    private Track track;

    protected PlaylistTrack() {
    }

    Playlist getPlaylist() {
        return playlist;
    }

    void setPlaylist(Playlist playlist) {
        this.playlist = playlist;
    }

    Track getTrack() {
        return track;
    }

    void setTrack(Track track) {
        this.track = track;
    }
}
