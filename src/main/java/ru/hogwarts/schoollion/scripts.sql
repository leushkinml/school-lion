select * from student;
select * from faculty;
select * from student where age > 15 and age < 25;
select name from student;
select * from student where name like '%a%' or name like '%A%';
select * from student where age < student.id;
select * from student ORDER BY age;