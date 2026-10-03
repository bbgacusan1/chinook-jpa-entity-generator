package edu.gcu.cst339.chinook_jpa_entity_generator;

record ColumnMetadata(
        String name,
        String sqlType,
        int jdbcType,
        int size,
        int decimalDigits,
        boolean nullable,
        boolean autoIncrement,
        boolean primaryKey,
        ForeignKeyMetadata foreignKey) {

    boolean isForeignKey() {
        return foreignKey != null;
    }
}