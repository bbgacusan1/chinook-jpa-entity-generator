package edu.gcu.cst339.chinook_jpa_entity_generator;

import java.sql.Connection;
import java.sql.DatabaseMetaData;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
class ConnectionCheckRunner implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(ConnectionCheckRunner.class);

    private final DataSource dataSource;

    ConnectionCheckRunner(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void run(String... args) throws Exception {
        try (Connection connection = dataSource.getConnection()) {
            DatabaseMetaData metaData = connection.getMetaData();
            log.info("Connected to {} {} at {} as {}",
                    metaData.getDatabaseProductName(),
                    metaData.getDatabaseProductVersion(),
                    metaData.getURL(),
                    metaData.getUserName());
        }
    }
}
