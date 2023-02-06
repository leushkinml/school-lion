ALTER TABLE student
    ADD CONSTRAINT age_min CHECK (age > 16);

ALTER TABLE student
    ADD PRIMARY KEY (name);

ALTER TABLE faculty
    ADD CONSTRAINT name_color_unique UNIQUE (name, color);

ALTER TABLE student
    ADD CONSTRAINT age_default DEFAULT 20;