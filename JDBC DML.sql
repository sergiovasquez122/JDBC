--/*    CECS323 - JDBC Group Project    */
--/*    Group Members: Darren Cobian    */
--/*                   Harry Tran       */
--/*                   Sergio Vasquez   */

-- Create values for table
Insert into PUBLISHERS(PUBLISHERNAME, PUBLISHERADDRESS, PUBLISHERPHONE, PUBLISHEREMAIL)
VALUES ('No Starch Press', '245 8th street', '800-420-7240', 'info@nostarch.com'),
('The MIT Press', 'One Rogers Street', '800-405-1619', 'info@mitpress.com'),
('Cambridge Press', '1 Liberty Plaza', '800-872-7423', 'info@cambridge.com'),
('Oreilly Media', '1005 Gravenstein', '800-889-8969', 'info@oreilly.com');
Insert into WritingGroups(GROUPNAME, HEADWRITER, YEARFORMED, SUBJECT)
VALUES ('CLRS', 'Thomas Cormen', 1986, 'Computer Science'),
 ('LinAlg4All', 'Gilbert Strang', 1975, 'Mathematics'),
 ('SixDegrees', 'Jeremy Watts', 2015, 'Computer Science'),
 ('Count Bayesie', 'Will Kurt', 2015, 'Mathematics'),
 ('Database Design', 'Tom Jewett', 2002, 'Computer Science');
Insert into BOOKS(GROUPNAME, BOOKTITLE, PUBLISHERNAME, YEARPUBLISHED, NUMBERPAGES)
values ('CLRS', 'Introduction to Algorithms', 'The MIT Press', 1990, 1336),
        ('CLRS', 'Algorithms Unlocked', 'The MIT Press', 2014, 250),
        ('SixDegrees', 'Machine Learning', 'Cambridge Press', 2018, 750);
-- sample commands
select * from WRITINGGROUPS; -- List all writing groups
select * from PUBLISHERS; -- list all publishers
select * from PUBLISHERS where PUBLISHERNAME = 'No Starch Press'; -- Select all the content of a particular publisher in this case No Starch Press
select * from BOOKS; -- list all book titles
Insert into BOOKS(GROUPNAME, BOOKTITLE, PUBLISHERNAME, YEARPUBLISHED, NUMBERPAGES)
values ('CLRS', 'Introduction to Algorithms 2nd edition', 'The MIT Press', 1990, 1336);
DELETE FROM Books WHERE BookTitle = 'Introduction to Algorithms 2nd edition';
Insert into PUBLISHERS(PUBLISHERNAME, PUBLISHERADDRESS, PUBLISHERPHONE, PUBLISHEREMAIL)
VALUES ('CSULB Press', '245 8th street', '800-420-7240', 'csulb@nostarch.com');
UPDATE BOOKS
SET PUBLISHERNAME = 'CSULB Press'
WHERE PUBLISHERNAME = 'The MIT Press';