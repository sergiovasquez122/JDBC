CREATE TABlE WritingGroups(
GroupName VARCHAR(20) NOT NULL,
HeadWriter VARCHAR(20) NOT NULL,
YearFormed VARCHAR(20) NOT NULL,
Subject VARCHAR (20) NOT NULL,
constraint writing_groups_pk primary key(GroupName));

CREATE TABLE Publishers(
PublisherName VARCHAR(20) NOT NULL,
PublisherAddress VARCHAR(20) NOT NULL,
PublisherPhone VARCHAR(20) NOT NULL,
PublisherEmail VARCHAR(20) NOT NULL,
constraint publisher_pk primary key(PublisherName)
);

CREATE TABLE Books(
GroupName VARCHAR(20) NOT NULL,
BookTitle VARCHAR(20) NOT NULL,
PublisherName VARCHAR(20) NOT NULL,
YearPublished VARCHAR(20) NOT NULL,
NumberPages VARCHAR(20) NOT NULL,
constraint publisher_fk foreign key(PublisherName) references Publishers(PublisherName),
constraint writing_group_fk foreign key(GroupName) references WritingGroups(GroupName),
constraint books_pk primary key(GroupName, BookTitle)
);


-- Create values for table
Insert into PUBLISHERS(PUBLISHERNAME, PUBLISHERADDRESS, PUBLISHERPHONE, PUBLISHEREMAIL)
VALUES ('No Starch Press', '245 8th street', '800-420-7240', 'info@nostarch.com'),
('The MIT Press', 'One Rogers Street', '800-405-1619', 'info@mitpress.com'),
('Cambridge Press', '1 Liberty Plaza', '800-872-7423', 'info@cambridge.com'),
('Oreilly Media', '1005 Gravenstein', '800-889-8969', 'info@oreilly.com')

-- sample commands 
select * from WRITINGGROUPS -- List all writing groups

select * from PUBLISHERS -- list all publishers

select * from PUBLISHERS where PUBLISHERNAME = 'No Starch Press' -- Select all the content of a particular publisher in this case No Starch Press

select * from BOOKS -- list all book titles

