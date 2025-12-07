CREATE TABLE Student ( student_id INT PRIMARY KEY AUTO_INCREMENT, name
VARCHAR(100) NOT NULL, email VARCHAR(150) UNIQUE, phone VARCHAR(20) );

CREATE TABLE Course ( course_id INT PRIMARY KEY AUTO_INCREMENT,
course_name VARCHAR(100) NOT NULL, description VARCHAR(255) );

CREATE TABLE Student_Course ( id INT PRIMARY KEY AUTO_INCREMENT,
student_id INT, course_id INT, FOREIGN KEY (student_id) REFERENCES
Student(student_id), FOREIGN KEY (course_id) REFERENCES
Course(course_id), UNIQUE(student_id, course_id) );
