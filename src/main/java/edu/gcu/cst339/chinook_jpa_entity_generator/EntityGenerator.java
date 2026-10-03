package edu.gcu.cst339.chinook_jpa_entity_generator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.stereotype.Component;

@Component
class EntityGenerator {
    
    static final String ENTITY_PACKAGE = "edu.gcu.cst339.chinook.entity";

    List<Path> generate(List<TableMetadata> tables, Path outputDir) throws IOException {
        Files.createDirectories(outputDir);
        List<Path> written = new ArrayList<>();
        for (TableMetadata table : tables) {
            Path file = outputDir.resolve(className(table.name()) + ".java");
            Files.writeString(file, buildSource(table));
            written.add(file);
        }
        return written;
    }

    String buildSource(TableMetadata table) {
        Set<String> imports = new TreeSet<>();
        imports.add("jakarta.persistence.Column");
        imports.add("jakarta.persistence.Entity");
        imports.add("jakarta.persistence.Table");
        
        StringBuilder fields = new StringBuilder();
        StringBuilder accessors = new StringBuilder();

        for (ColumnMetadata column : table.columns()) {
            String type = javaType(column, imports);
            String field = fieldName(column.name());
            String property = className(column.name());

            if (column.isForeignKey()) {
                fields.append("    // FK -> ").append(column.foreignKey().referencedTable()).append('.').append(column.foreignKey().referencedColumn()).append('\n');
            }
            if (column.primaryKey()) {
                imports.add("jakarta.persistence.Id");
                fields.append("    @Id\n");
            }
            if (column.autoIncrement()) {
                imports.add("jakarta.persistence.GeneratedValue");
                imports.add("jakarta.persistence.GenerationType");
                fields.append("    @GeneratedValue(strategy = GenerationType.IDENTITY)\n");
            }
            if (!column.nullable() && !column.autoIncrement()) {
                imports.add("jakarta.validation.constraints.NotNull");
                fields.append("    @NotNull\n");
            }
            if (isText(column)) {
                imports.add("jakarta.validation.constraints.Size");
                fields.append("    @Size(max = ").append(column.size()).append(")\n");
            }
            fields.append("    @Column(").append(columnAttributes(column)).append(")\n");
            fields.append("    private ").append(type).append(' ').append(field).append(";\n\n");

            accessors.append("    ").append(type).append(" get").append(property).append("() {\n")
                    .append("        return ").append(field).append(";\n")
                    .append("    }\n\n");
            if (!column.autoIncrement()) {
                accessors.append("    void set").append(property).append('(').append(type).append(' ').append(field).append(") {\n")
                        .append("        this.").append(field).append(" = ").append(field).append(";\n")
                        .append("    }\n\n");
            }
        }

        String className = className(table.name());
        StringBuilder source = new StringBuilder();
        source.append("package ").append(ENTITY_PACKAGE).append(";\n\n");
        for (String imp : imports) {
            source.append("import ").append(imp).append(";\n");
        }
        source.append('\n');
        source.append("@Entity\n");
        source.append("@Table(name = \"").append(table.name()).append("\")\n");
        source.append("class ").append(className).append(" {\n\n");
        source.append(fields);
        source.append("    protected ").append(className).append("() {\n");
        source.append("    }\n\n");
        source.append(accessors);
        source.setLength(source.length() - 1); // remove last newline
        source.append("}\n");
        return source.toString();
    }

    private String columnAttributes(ColumnMetadata column) {
        StringBuilder attrs = new StringBuilder("name = \"").append(column.name()).append('"');
        if (!column.nullable()) {
            attrs.append(", nullable = false");
        }
        if (isText(column)) {
            attrs.append(", length = ").append(column.size());
        }
        if (column.jdbcType() == Types.DECIMAL || column.jdbcType() == Types.NUMERIC) {
            attrs.append(", precision = ").append(column.size()).append(", scale = ").append(column.decimalDigits());
        }
        return attrs.toString();
    }

    private String javaType(ColumnMetadata column, Set<String> imports) {
        return switch (column.jdbcType()) {
            case Types.INTEGER -> "Integer";
            case Types.BIGINT -> "Long";
            case Types.SMALLINT, Types.TINYINT -> "Short";
            case Types.VARCHAR, Types.CHAR, Types.LONGVARCHAR, Types.NVARCHAR, Types.NCHAR -> "String";
            case Types.BOOLEAN, Types.BIT -> "Boolean";
            case Types.DOUBLE, Types.FLOAT -> "Double";
            case Types.REAL -> "Float";
            case Types.NUMERIC, Types.DECIMAL -> {
                imports.add("java.math.BigDecimal");
                yield "BigDecimal";
            }
            case Types.TIMESTAMP -> {
                imports.add("java.time.LocalDateTime");
                yield "LocalDateTime";
            }
            case Types.DATE -> {
                imports.add("java.time.LocalDate");
                yield "LocalDate";
            }
            default -> throw new IllegalArgumentException(
                    "Unsupported SQL type " + column.sqlType() + " for column " + column.name());
        };
    }

    private boolean isText(ColumnMetadata column) {
        return switch (column.jdbcType()) {
            case Types.VARCHAR, Types.CHAR, Types.NVARCHAR, Types.NCHAR -> column.size() > 0 && column.size() < Integer.MAX_VALUE;
            default -> false;
        };
    }

    static String className(String sqlName) {
        StringBuilder name = new StringBuilder();
        for (String part : sqlName.split("_")) {
            if (!part.isEmpty()) {
                name.append(Character.toUpperCase(part.charAt(0))).append(part.substring(1).toLowerCase());
            }
        }
        return name.toString();
    }

    static String fieldName(String sqlName) {
        String name = className(sqlName);
        return Character.toLowerCase(name.charAt(0)) + name.substring(1);
    }
}
