package edu.gcu.cst339.chinook_jpa_entity_generator;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.persistence.autoconfigure.EntityScan;

import jakarta.persistence.EntityManager;
import jakarta.persistence.metamodel.EntityType;

/**
 * Verifies the generated entities against the real Chinook schema.
 * ddl-auto=validate makes Hibernate check every table, column, type, and key
 * mapping at startup without modifying the database.
 */
@DataJpaTest(properties = "spring.jpa.hibernate.ddl-auto=validate")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@EntityScan("edu.gcu.cst339.chinook.entity")
class GeneratedEntitiesSchemaTests {

    @Autowired
    private EntityManager entityManager;

    @Test
    void everyTableHasAnEntity() {
        assertThat(entityManager.getMetamodel().getEntities()).hasSize(11);
    }

    @Test
    void everyEntityCanQueryItsTable() {
        for (EntityType<?> entity : entityManager.getMetamodel().getEntities()) {
            Long rows = entityManager
                    .createQuery("select count(e) from " + entity.getName() + " e", Long.class)
                    .getSingleResult();
            assertThat(rows).as(entity.getName()).isPositive();
        }
    }

    @Test
    void manyToOneJoinsToReferencedTable() {
        String artist = entityManager
                .createQuery("select a.artist.name from Album a where a.albumId = 1", String.class)
                .getSingleResult();
        assertThat(artist).isEqualTo("AC/DC");
    }

    @Test
    void selfReferencingForeignKeyResolves() {
        Long managed = entityManager
                .createQuery("select count(e) from Employee e where e.reportsTo is not null", Long.class)
                .getSingleResult();
        assertThat(managed).isPositive();
    }

    @Test
    void compositeKeyMapsPlaylistTrack() {
        Long tracks = entityManager
                .createQuery("select count(pt) from PlaylistTrack pt where pt.playlist.playlistId = 1", Long.class)
                .getSingleResult();
        assertThat(tracks).isPositive();
    }
}
