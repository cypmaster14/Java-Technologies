DELETE FROM assignments
WHERE project_id = 1;

SELECT *
FROM assignments;

SELECT *
FROM students;

INSERT INTO Assignments (student_id, project_id) VALUES (3, 1);


DROP TRIGGER IF EXISTS TRG1;

DELIMITER $$
CREATE TRIGGER TRG1
  BEFORE INSERT
  ON assignments
  FOR EACH ROW
  BEGIN
    DECLARE numberOfStudentsAllocatedToProject SMALLINT;
    DECLARE projectCapacity SMALLINT;
    DECLARE message VARCHAR(255);
    SELECT COUNT(1)
    FROM assignments
    WHERE project_id = new.project_id
    INTO numberOfStudentsAllocatedToProject;
    SELECT capacity
    FROM projects
    WHERE id = new.project_id
    INTO projectCapacity;

    IF numberOfStudentsAllocatedToProject = projectCapacity
    THEN
      SET message = CONCAT("Assigns:", numberOfStudentsAllocatedToProject, " ; Capacity", projectCapacity);
      SIGNAL SQLSTATE '45000'
      SET MESSAGE_TEXT = message;

    END IF;
  END$$
DELIMITER ;

