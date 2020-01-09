CREATE DATABASE tutoring;
USE tutoring;
CREATE TABLE user(
       id SMALLINT NOT NULL AUTO_INCREMENT,
       privilege ENUM('admin','client') NOT NULL,
       username VARCHAR(20) NOT NULL,
       password VARCHAR(20) NOT NULL,
       PRIMARY KEY(id)
);
CREATE TABLE teacher(
       id TINYINT NOT NULL AUTO_INCREMENT,
       name VARCHAR(20) NOT NULL,
       surname VARCHAR(20) NOT NULL,
       PRIMARY KEY(id)  
);
CREATE TABLE subject(
       name VARCHAR(20) PRIMARY KEY
);
CREATE TABLE teacher_subject(
       teacher TINYINT NOT NULL,
       subject VARCHAR(20) NOT NULL
);
CREATE TABLE lesson(
       user SMALLINT NOT NULL,
       teacher TINYINT NOT NULL,
       subject VARCHAR(20) NOT NULL,
       appointment DATE NOT NULL,
       daydate ENUM('mon','tue','wed','thu','fry','sat','sun') NOT NULL,
       hourdate INT NOT NULL,
       status ENUM('done','book','annul') NOT NULL,
       CHECK(hourdate>14 AND hourdate<19),
       PRIMARY KEY (user, teacher, subject, appointment, hourdate)
);

ALTER TABLE teacher_subject ADD FOREIGN KEY (teacher)
      	    		    REFERENCES teacher(id)
			    ON DELETE CASCADE
			    ON UPDATE CASCADE;

ALTER TABLE teacher_subject ADD FOREIGN KEY (subject)
      	    		    REFERENCES subject(name)
			    ON DELETE CASCADE
			    ON UPDATE CASCADE;
ALTER TABLE lesson ADD FOREIGN KEY (user)
      	    	       REFERENCES user(id);

ALTER TABLE lesson ADD FOREIGN KEY (subject)
          	       REFERENCES subject(name)
       	       	       ON DELETE NO ACTION
	       	       ON UPDATE CASCADE;


ALTER TABLE lesson ADD FOREIGN KEY (teacher)
      	    	       REFERENCES teacher(id)
       	       	       ON DELETE NO ACTION
	       	       ON UPDATE CASCADE;

