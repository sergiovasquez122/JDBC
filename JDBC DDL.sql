--/*    CECS323 - JDBC Group Project    */
--/*    Group Members: Darren Cobian    */
--/*                   Harry Tran       */
--/*                   Sergio Vasquez   */

--Creates the table for WritingGroups using GroupName, HeadWriter, Subject(VARCHARS) and YearFormed(INTEGER)
CREATE TABlE WritingGroups(
GroupName VARCHAR(50) NOT NULL,
HeadWriter VARCHAR(50) NOT NULL,
YearFormed INTEGER NOT NULL,
Subject VARCHAR (50) NOT NULL,
constraint writing_groups_pk primary key(GroupName)); --GroupNames becomes the primary key

--Creates the table for Publishers using PublisherName, PublisherAddress, PublisherPhone, PublisherEmail (VARCHARS)
CREATE TABLE Publishers(
PublisherName VARCHAR(50) NOT NULL,
PublisherAddress VARCHAR(50) NOT NULL,
PublisherPhone VARCHAR(50) NOT NULL,
PublisherEmail VARCHAR(50) NOT NULL,
constraint publisher_pk primary key(PublisherName) --Uses PublisherName as the primary key.
);

--Creates the table for Publishers using GroupName, BookTitle, PublisherName(VARCHARS) and YearPublished, NumberPages (INTEGER)
CREATE TABLE Books(
GroupName VARCHAR(50) NOT NULL,
BookTitle VARCHAR(50) NOT NULL,
PublisherName VARCHAR(50) NOT NULL,
YearPublished INTEGER NOT NULL,
NumberPages INTEGER NOT NULL,
constraint publisher_fk foreign key(PublisherName) references Publishers(PublisherName), --Foreign Key comes from Publishers
constraint writing_group_fk foreign key(GroupName) references WritingGroups(GroupName), --Foreign Key comes from WritingGroups
constraint books_pk primary key(GroupName, BookTitle) --Uses foreign keys as the primary key of Books
);