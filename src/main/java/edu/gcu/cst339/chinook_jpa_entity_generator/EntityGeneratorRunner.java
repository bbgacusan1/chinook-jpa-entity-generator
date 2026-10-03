package edu.gcu.cst339.chinook_jpa_entity_generator;

import java.util.List;
import java.nio.file.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
class EntityGeneratorRunner implements CommandLineRunner {
    
    private static final Logger log = LoggerFactory.getLogger(EntityGeneratorRunner.class);

        private final ChinookMetadataReader metadataReader;
        private final EntityGenerator entityGenerator;

        EntityGeneratorRunner(ChinookMetadataReader metadataReader, EntityGenerator entityGenerator) {
            this.metadataReader = metadataReader;
            this.entityGenerator = entityGenerator;
        }

        @Override
        public void run(String... args) throws Exception {
            List<TableMetadata> tables = metadataReader.readTables();
            log.info("Discovered {} tables in Chinook", tables.size());

            for (TableMetadata table : tables) {
                log.info("Table: {}", table.name());
                for (ColumnMetadata column : table.columns()) {
                log.info(String.format("%-20s %-10s size=%-4d %-9s %-3s %s%s",
                    column.name(),
                    column.sqlType(),
                    column.size(),
                    column.nullable() ? "NULL" : "NOT NULL",
                    column.primaryKey() ? "PK" : "",
                    column.autoIncrement() ? "AUTO" : "",
                    column.isForeignKey() ? "FK -> " + column.foreignKey().referencedTable() + "." + column.foreignKey().referencedColumn() : ""
                ));
                }
            }

            Path outputDir = Path.of("generated_entities");
            List<Path> files = entityGenerator.generate(tables, outputDir);
            for (Path file : files) {
                log.info("Generated {}", file);
            }
        log.info("Wrote {} entity classes to {}", files.size(), outputDir.toAbsolutePath());
    }
}
