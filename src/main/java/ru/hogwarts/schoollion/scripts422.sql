CREATE TABLE car (
    id SERIAL PRIMARY KEY,
    brand TEXT DEFAULT 'No name brand',
    model TEXT NOT NULL,
    price NUMERIC(9,2) CHECK ( price > 0 ),
);

CREATE TABLE driver (
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    age INTEGER CHECK ( age > 0 ),
    license_verified BOOLEAN,
    car_id TEXT REFERENCES car(id)
);

SELECT student.name, student.age, faculty.name
FROM student
INNER JOIN faculty ON student.faculty_id = faculty.id

SELECT student.name, student.age, avatar.file_path
FROM avatar
INNER JOIN student ON avatar.student_id = student.id