package edu.gcu.cst339.chinook_jpa_entity_generator;

import java.util.List;

record TableMetadata(String name, List<ColumnMetadata> columns) {
    List<ColumnMetadata> primaryKeyColumns() {
        return columns.stream().filter(ColumnMetadata::primaryKey).toList();
    }

    boolean hasCompositeKey() {
        return primaryKeyColumns().size() > 1;
    }
}