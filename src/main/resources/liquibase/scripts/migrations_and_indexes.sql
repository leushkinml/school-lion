-- liquibase formatted sql

-- changeset mleushkin:1
CREATE INDEX students_name_index ON student (name);

-- changeset mleushkin:2
CREATE INDEX faculty_name_and_color_index ON faculty (name, color);

