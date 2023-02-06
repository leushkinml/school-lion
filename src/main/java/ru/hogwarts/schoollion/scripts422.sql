CREATE TABLE car (
    car_id SERIAL,
    car_brand TEXT DEFAULT 'No name brand',
    car_model TEXT PRIMARY KEY,
    car_price NUMERIC(9,2) CHECK ( car_price > 0 ),
);

CREATE TABLE driver (
    driver_id SERIAL,
    driver_name TEXT PRIMARY KEY,
    driver_age INTEGER CHECK ( age > 0 ),
    driver_license_verified BOOLEAN,
    car_model TEXT
);

SELECT student.name, student.age, faculty.name
FROM student
INNER JOIN faculty ON student.faculty_id = faculty.id

SELECT student.name, student.age, avatar.file_path
FROM avatar
INNER JOIN student ON avatar.student_id = student.id