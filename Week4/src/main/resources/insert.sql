INSERT INTO Skills (NAME) VALUES ("JavaEE");
INSERT INTO Skills (name) VALUES ("JavaScript");
INSERT INTO Skills (name) VALUES ("Spring");
INSERT INTO Skills (name) VALUES ("Python");
INSERT INTO Skills (name) VALUES ("Node.js");


INSERT INTO Students (name, email) VALUES ("Lazar Lila-Ciprian", "lila.lazar@info.uaic.ro");
INSERT INTO Students (name, email) VALUES ("Gabor Silviu", "silviu.gabor@info.uaic.ro");
INSERT INTO Students (name, email) VALUES ("Groza Vasile", "vasile.groza@info.uaic.ro");
INSERT INTO Students (name, email) VALUES ("Stefan Cernescu", "stefan.cernescu@info.uaic.ro");
INSERT INTO Students (name, email) VALUES ("Lupu Silviu", "silviu.lupu@info.uaic.ro");


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


INSERT INTO Projects (name, description, capacity) VALUES ("Java EE Project", "Some description", 2);
INSERT INTO Projects (name, description, capacity) VALUES ("Python Scrapper", "Some description", 1);
INSERT INTO Projects (name, description, capacity) VALUES ("OPID", "Some description", 1);
INSERT INTO Projects (name, description, capacity) VALUES ("EDeC", "Some description", 1);


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


INSERT INTO Students_Projects (student_id, project_id, level_of_preferences) VALUES (1, 3, 0);
INSERT INTO Students_Projects (student_id, project_id, level_of_preferences) VALUES (1, 4, 1);

INSERT INTO Students_Projects (student_id, project_id, level_of_preferences) VALUES (2, 1, 0);
INSERT INTO Students_Projects (student_id, project_id, level_of_preferences) VALUES (2, 2, 1);

INSERT INTO Students_Projects (student_id, project_id, level_of_preferences) VALUES (3, 4, 0);
INSERT INTO Students_Projects (student_id, project_id, level_of_preferences) VALUES (3, 3, 1);

INSERT INTO Students_Projects (student_id, project_id, level_of_preferences) VALUES (4, 1, 0);
INSERT INTO Students_Projects (student_id, project_id, level_of_preferences) VALUES (4, 2, 1);

INSERT INTO Students_Projects (student_id, project_id, level_of_preferences) VALUES (5, 2, 0);
INSERT INTO Students_Projects (student_id, project_id, level_of_preferences) VALUES (5, 3, 1);










