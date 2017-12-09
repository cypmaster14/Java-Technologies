DROP TABLE IF EXISTS Assignments;
DROP TABLE IF EXISTS Students_Projects;
DROP TABLE IF EXISTS Projects_Skills;
DROP TABLE IF EXISTS Students_Skills;
DROP TABLE IF EXISTS Skills;
DROP TABLE IF EXISTS Students;
DROP TABLE IF EXISTS Projects;


CREATE TABLE Skills (

  id   INTEGER AUTO_INCREMENT NOT NULL  PRIMARY KEY,
  name VARCHAR(255)           NOT NULL UNIQUE
);

CREATE TABLE Students (
  id    INTEGER AUTO_INCREMENT NOT NULL PRIMARY KEY,
  name  VARCHAR(255)           NOT NULL,
  email VARCHAR(255)           NOT NULL
);


CREATE TABLE Students_Skills
(
  student_id INTEGER,
  skill_id   INTEGER,
  CONSTRAINT Students_Skills_pk PRIMARY KEY (student_id, skill_id),
  CONSTRAINT Students_Skills_students_id_fk FOREIGN KEY (student_id) REFERENCES students (id),
  CONSTRAINT Students_Skills_skills_id_fk FOREIGN KEY (skill_id) REFERENCES studentSkills (id)
);

CREATE TABLE Projects (
  id          INTEGER AUTO_INCREMENT NOT NULL PRIMARY KEY,
  name        VARCHAR(255)           NOT NULL,
  description VARCHAR(255)           NOT NULL,
  capacity    INTEGER                NOT NULL
);

CREATE TABLE Projects_Skills (
  project_id           INTEGER,
  skill_id             INTEGER,
  level_of_preferences INTEGER NOT NULL,
  CONSTRAINT Projects_Skills_PK PRIMARY KEY (project_id, skill_id),
  CONSTRAINT Projects_Skills_project_id_fk FOREIGN KEY (project_id) REFERENCES Projects (id),
  CONSTRAINT Projects_Skills_skill_id_fk FOREIGN KEY (skill_id) REFERENCES Skills (id),
  CONSTRAINT Projects_Skills_project_preference_unique UNIQUE (project_id, level_of_preferences)
);

CREATE TABLE Students_Projects (
  student_id           INTEGER,
  project_id           INTEGER,
  level_of_preferences INTEGER NOT NULL,
  CONSTRAINT Students_Projects_PK PRIMARY KEY (student_id, project_id),
  CONSTRAINT Students_Projects_student_id_fk FOREIGN KEY (student_id) REFERENCES Students (id),
  CONSTRAINT Students_Projects_project_id_fk FOREIGN KEY (project_id) REFERENCES Projects (id)
);

CREATE TABLE Assignments (
  student_id INTEGER UNIQUE,
  project_id INTEGER,
  CONSTRAINT Assignments_PK PRIMARY KEY (student_id, project_id),
  CONSTRAINT Assignments_student_id_fk FOREIGN KEY (student_id) REFERENCES Students (id),
  CONSTRAINT Assignments_project_id_fk FOREIGN KEY (project_id) REFERENCES Projects (id)
);



