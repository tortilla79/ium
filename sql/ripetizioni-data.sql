USE tutoring;

INSERT INTO user (privilege, username, password)
VALUES ('admin','guglielmo','beccuti');
INSERT INTO user (privilege, username, password)
VALUES ('client','landolfo','rufolo');
INSERT INTO user (privilege, username, password)
VALUES ('client','marco','valdo');

INSERT INTO teacher (name, surname)
VALUES ('mario','rossi');
INSERT INTO teacher (name, surname)
VALUES ('roberto','scarto');
INSERT INTO teacher (name, surname)
VALUES ('gianluca','retto');
INSERT INTO teacher (name, surname)
VALUES ('milena','gatrio');
INSERT INTO teacher (name, surname)
VALUES ('giorgia','panice');
INSERT INTO teacher (name, surname)
VALUES ('lucia','artesa');
INSERT INTO teacher (name, surname)
VALUES ('franco','filet');
INSERT INTO teacher (name, surname)
VALUES ('beatrice','guarda');
INSERT INTO teacher (name, surname)
VALUES ('dario','impara');
INSERT INTO teacher (name, surname)
VALUES ('serena','dente');

INSERT INTO subject (name)
VALUES ('matematica');
INSERT INTO subject (name)
VALUES ('chimica');
INSERT INTO subject (name)
VALUES ('informatica');
INSERT INTO subject (name)
VALUES ('fisica');
INSERT INTO subject (name)
VALUES ('inglese');

INSERT INTO teacher_subject (teacher, subject)
VALUES('1','matematica');
INSERT INTO teacher_subject (teacher, subject)
VALUES('2','matematica');
INSERT INTO teacher_subject (teacher, subject)
VALUES('3','chimica');
INSERT INTO teacher_subject (teacher, subject)
VALUES('4','chimica');
INSERT INTO teacher_subject (teacher, subject)
VALUES('5','informatica');
INSERT INTO teacher_subject (teacher, subject)
VALUES('6','informatica');
INSERT INTO teacher_subject (teacher, subject)
VALUES('7','fisica');
INSERT INTO teacher_subject (teacher, subject)
VALUES('8','fisica');
INSERT INTO teacher_subject (teacher, subject)
VALUES('9','inglese');
INSERT INTO teacher_subject (teacher, subject)
VALUES('10','inglese');

INSERT INTO lesson (user, teacher, subject,appointment, hourdate, status)
VALUES('2','5','informatica','2019-11-23','15','annul');
INSERT INTO lesson (user, teacher, subject,appointment, hourdate, status)
VALUES('2','6','informatica','2019-11-29','17','done');
INSERT INTO lesson (user, teacher, subject,appointment, hourdate, status)
VALUES('3','10','inglese','2019-12-3','16','done');
