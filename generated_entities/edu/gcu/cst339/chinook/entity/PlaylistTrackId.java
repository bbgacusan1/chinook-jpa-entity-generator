package edu.gcu.cst339.chinook.entity;

import java.io.Serializable;
import java.util.Objects;

public class PlaylistTrackId implements Serializable {

    private Integer playlist;
    private Integer track;

    public PlaylistTrackId() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PlaylistTrackId other)) {
            return false;
        }
        return Objects.equals(playlist, other.playlist)
                && Objects.equals(track, other.track);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playlist, track);
    }
}
