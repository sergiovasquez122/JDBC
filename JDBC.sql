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



