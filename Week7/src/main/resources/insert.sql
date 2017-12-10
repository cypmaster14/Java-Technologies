INSERT INTO Skills (id, name) VALUES (1, "JavaEE");
INSERT INTO Skills (id, name) VALUES (2, "JavaScript");
INSERT INTO Skills (id, name) VALUES (3, "Spring");
INSERT INTO Skills (id, name) VALUES (4, "Python");
INSERT INTO Skills (id, name) VALUES (5, "Node.js");


INSERT INTO Students (id, name, email) VALUES (1, "Lazar Lila-Ciprian", "lila.lazar@info.uaic.ro");
INSERT INTO Students (id, name, email) VALUES (2, "Gabor Silviu", "silviu.gabor@info.uaic.ro");
INSERT INTO Students (id, name, email) VALUES (3, "Groza Vasile", "vasile.groza@info.uaic.ro");
INSERT INTO Students (id, name, email) VALUES (4, "Stefan Cernescu", "stefan.cernescu@info.uaic.ro");
INSERT INTO Students (id, name, email) VALUES (5, "Lupu Silviu", "silviu.lupu@info.uaic.ro");


INSERT INTO Students_Skills (student_id, skill_id) VALUES (1, 1);
INSERT INTO Students_Skills (student_id, skill_id) VALUES (1, 2);
INSERT INTO Students_Skills (student_id, skill_id) VALUES (1, 3);
INSERT INTO Students_Skills (student_id, skill_id) VALUES (1, 4);
INSERT INTO Students_Skills (student_id, skill_id) VALUES (1, 5);

INSERT INTO Students_Skills (student_id, skill_id) VALUES (2, 1);
INSERT INTO Students_Skills (student_id, skill_id) VALUES (2, 3);
INSERT INTO Students_Skills (student_id, skill_id) VALUES (2, 4);

INSERT INTO Students_Skills (student_id, skill_id) VALUES (3, 2);
INSERT INTO Students_Skills (student_id, skill_id) VALUES (3, 4);
INSERT INTO Students_Skills (student_id, skill_id) VALUES (3, 5);

INSERT INTO Students_Skills (student_id, skill_id) VALUES (4, 1);
INSERT INTO Students_Skills (student_id, skill_id) VALUES (4, 3);
INSERT INTO Students_Skills (student_id, skill_id) VALUES (4, 4);
INSERT INTO Students_Skills (student_id, skill_id) VALUES (4, 5);

INSERT INTO Students_Skills (student_id, skill_id) VALUES (5, 2);
INSERT INTO Students_Skills (student_id, skill_id) VALUES (5, 4);


INSERT INTO Projects (id, name, description) VALUES (1, "Java EE Project", "Some description");
INSERT INTO Projects (id, name, description) VALUES (2, "Python Scrapper", "Some description");
INSERT INTO Projects (id, name, description) VALUES (3, "OPID", "Some description");
INSERT INTO Projects (id, name, description) VALUES (4, "EDeC", "Some description");


INSERT INTO Assignments (student_id, project_id) VALUES (1, 3);
INSERT INTO Assignments (student_id, project_id) VALUES (2, 1);
INSERT INTO Assignments (student_id, project_id) VALUES (3, 2);
INSERT INTO Assignments (student_id, project_id) VALUES (4, 3);
INSERT INTO Assignments (student_id, project_id) VALUES (5, 4);


INSERT INTO Projects_Skills (project_id, skill_id, level_of_preferences) VALUES (1, 1, 0);
INSERT INTO Projects_Skills (project_id, skill_id, level_of_preferences) VALUES (1, 3, 1);
INSERT INTO Projects_Skills (project_id, skill_id, level_of_preferences) VALUES (1, 2, 2);

INSERT INTO Projects_Skills (project_id, skill_id, level_of_preferences) VALUES (2, 4, 0);
INSERT INTO Projects_Skills (project_id, skill_id, level_of_preferences) VALUES (2, 2, 1);

INSERT INTO Projects_Skills (project_id, skill_id, level_of_preferences) VALUES (3, 5, 0);
INSERT INTO Projects_Skills (project_id, skill_id, level_of_preferences) VALUES (3, 2, 1);
INSERT INTO Projects_Skills (project_id, skill_id, level_of_preferences) VALUES (3, 4, 2);

INSERT INTO Projects_Skills (project_id, skill_id, level_of_preferences) VALUES (4, 5, 0);
INSERT INTO Projects_Skills (project_id, skill_id, level_of_preferences) VALUES (4, 2, 1);


INSERT INTO Students_Projects (students_id, projects_id, level_of_preferences) VALUES (1, 3, 0);
INSERT INTO Students_Projects (students_id, projects_id, level_of_preferences) VALUES (1, 4, 1);

INSERT INTO Students_Projects (students_id, projects_id, level_of_preferences) VALUES (2, 1, 0);
INSERT INTO Students_Projects (students_id, projects_id, level_of_preferences) VALUES (2, 2, 1);

INSERT INTO Students_Projects (students_id, projects_id, level_of_preferences) VALUES (3, 4, 0);
INSERT INTO Students_Projects (students_id, projects_id, level_of_preferences) VALUES (3, 3, 1);

INSERT INTO Students_Projects (students_id, projects_id, level_of_preferences) VALUES (4, 1, 0);
INSERT INTO Students_Projects (students_id, projects_id, level_of_preferences) VALUES (4, 2, 1);

INSERT INTO Students_Projects (students_id, projects_id, level_of_preferences) VALUES (5, 2, 0);
INSERT INTO Students_Projects (students_id, projects_id, level_of_preferences) VALUES (5, 3, 1);










