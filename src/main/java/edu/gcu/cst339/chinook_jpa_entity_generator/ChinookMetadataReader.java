package edu.gcu.cst339.chinook_jpa_entity_generator;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.springframework.stereotype.Component;

@Component
class ChinookMetadataReader {
    
    private static final String SCHEMA = "public";

    private final DataSource dataSource;

    ChinookMetadataReader(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    List<TableMetadata> readTables() throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            DatabaseMetaData metaData = connection.getMetaData();
            List<TableMetadata> tables = new ArrayList<>();
            try (ResultSet rs = metaData.getTables(null, SCHEMA, "%", new String[] {"TABLE"})) {
                while (rs.next()) {
                    tables.add(readTable(metaData, rs.getString("TABLE_NAME")));
                }
            }
            return tables;
        }
    }

    private TableMetadata readTable(DatabaseMetaData metaData, String table) throws SQLException {
        Set<String> primaryKeys = readPrimaryKeys(metaData, table);
        Map<String, ForeignKeyMetadata> foreignKeys = readForeignKeys(metaData, table);
        List<ColumnMetadata> columns = new ArrayList<>();
        try (ResultSet rs = metaData.getColumns(null, SCHEMA, table, "%")) {
            while (rs.next()) {
                String column = rs.getString("COLUMN_NAME");
                columns.add(new ColumnMetadata(
                    column,
                    rs.getString("TYPE_NAME"),
                    rs.getInt("DATA_TYPE"),
                    rs.getInt("COLUMN_SIZE"),
                    rs.getInt("DECIMAL_DIGITS"),
                    "YES".equals(rs.getString("IS_NULLABLE")),
                    "YES".equals(rs.getString("IS_AUTOINCREMENT")),
                    primaryKeys.contains(column),
                    foreignKeys.get(column)
                ));
            }
        }
        return new TableMetadata(table, columns);
    }

    private Set<String> readPrimaryKeys(DatabaseMetaData metaData, String table) throws SQLException {
        Set<String> keys = new HashSet<>();
        try (ResultSet rs = metaData.getPrimaryKeys(null, SCHEMA, table)) {
            while (rs.next()) {
                keys.add(rs.getString("COLUMN_NAME"));
            }
        }
        return keys;
    }

    private Map<String, ForeignKeyMetadata> readForeignKeys(DatabaseMetaData metaData, String table) throws SQLException {
        Map<String, ForeignKeyMetadata> keys = new HashMap<>();
        try (ResultSet rs = metaData.getImportedKeys(null, SCHEMA, table)) {
            while (rs.next()) {
                keys.put(rs.getString("FKCOLUMN_NAME"), new ForeignKeyMetadata(
                    rs.getString("PKTABLE_NAME"),
                    rs.getString("PKCOLUMN_NAME")
                ));
            }
        }
        return keys;
    }
}
