CREATE TABLE users
(
    age INTEGER CHECK ( age > 0 ),      // Ограничение возраста
    email TEXT NOT NULL,                // Ограничение НЕ НУЛ
    nickname TEXT UNIQUE,               // Уникальное значение
    // Первичные ключи. Уникальный индентификатор строк таблицы.
    username TEXT UNIQUE NOT NULL,
    username TEXT PRIMARY KEY,
    prifile_picture_url TEXT DEFAULT 'https://default_picture_url',
    email_verified BOOLEAN,             // Проверка на наличие почты, есть или нет.
    deleted BOOLEAN,                    // Проверка удалён элемент или нет.
    account_balance NUMERIC(16,9),      // Для денег. 16- общее количество цифр, 9- после запятой.
    varchar_column VARCHAR(100),
    char_column CHAR(100),
    text_column TEXT
);

// Добавить ограничение NOT NULL после создания таблицы:
ALTER TABLE users
ALTER COLUMN name SET NOT NULL;

// Добавить ограничение UNIQUE после создания таблицы:
ALTER TABLE users
ADD CONSTRAINT nickname_unique UNIQUE (nickname);
// Добавить ограничение UNIQUE по нескольким колонкам. У команды UNIQUE нет ограничений на количество колонок:
ALTER TABLE users
ADD CONSTRAINT login_pass_unique UNIQUE (login, password);

// Добавить ограничение PRIMARY KEY после создания таблицы:
ALTER TABLE users
ADD PRIMARY KEY (login);

// Создании таблицы сотрудников. таблицы сотрудников тоже есть первичный ключ,
и мы его указываем командой PRIMARY KEY.
А также командой REFERENCES указываем на первичный ключ таблицы подразделения:
CREATE TABLE users (
    id REAL,
    name TEXT PRIMARY KEY,
    department_id TEXT REFERENCES departments (id)
)



// Добавить ограничение с именем после создания таблицы:
ALTER TABLE users
ADD CONSTRAINT age_constraint CHECK (age > 0);  // age_constraint - имя ограничения
// Удалить ограничение по имени:
ALTER TABLE users DROP CONSTRAINT age_constraint;

CREATE TABLE table_name(
    ...
    culumn1 datatype,
    culumn2 datatype constraint,
    ...
);

// Создания таблицы с несколькими типами:
CREATE TABLE example (
    Id SERIAL,
    TotalWeight NUMERIC(9,2),
    Age INTEGER,
    Surplus REAL
);

// Создания таблицы с несколькими типами времени:
CREATE TABLE users (
    ...,
     sign_up_date TIMESTAMP,
     birth_date DATE,
     notification_send_time TIME,
    ...
);

DROP TABLE table_name;                              // Удаление таблицы
ALTER TABLE table_name ADD column_name datatype;    // Добавить колонку в таблицу. необходимо указать еще и ее тип.
ALTER TABLE table_name DROP COLUMN column_name;     // Удалить колонку
ALTER TABLE table_name RENAME COLUMN column_name TO new_column_name;    // Переименовать колонку
ALTER TABLE table_name ALTER COLUMN column_name TYPE new_datatype;      // Изменить тип колонки

// Пример вставки значения TRUE для колонки с типом BOOLEAN:
INSERT INTO users (email_verified) VALUES ('true')
INSERT INTO users (email_verified) VALUES ('yes')
INSERT INTO users (email_verified) VALUES ('on')
INSERT INTO users (email_verified) VALUES (1)
// Пример вставки значения FALSE  для колонки с типом BOOLEAN:
INSERT INTO users (email_verified) VALUES ('false')
INSERT INTO users (email_verified) VALUES ('no')
INSERT INTO users (email_verified) VALUES ('off')
INSERT INTO users (email_verified) VALUES (0)


JOIN:
SELECT users.name, users.positopn, department.department, department.description
FROM users
INNER JOIN department ON users.department_id = department.id

SELECT users.name, users.positopn, department.department, department.description
FROM users
LEFT JOIN department ON users.department_id = department.id

SELECT users.name, users.positopn, department.department, department.description
FROM users
RIGHT JOIN department ON users.department_id = department.id

SELECT users.name, users.positopn, department.department, department.description
FROM users
FULL JOIN department ON users.department_id = department.id

// Связь между таблицами:
Имеем две таблицы
CREATE TABLE users(
    username TEXT PRIMARY KEY,
    role TEXT
)
CREATE TABLE permissions(
    role TEXT PRIMARY KEY,
    permissions TEXT
)
// устанавливаем связь
CREATE TABLE users(
    username TEXT PRIMARY KEY,
    role TEXT REFERENCES roles (role)
)