CREATE DATABASE SportsComplex;
USE SportsComplex;
CREATE TABLE PERSON (
	cnic char(13),
    firstName varchar(100),
    lastname varchar(100),
    dob date,
    gender enum('M', 'F'),
    contact char(13),
    emerContact char(13),
    email varchar(100),
    bloodGroup enum ('A+','A-','B+','B-','AB+','AB-','O+','O-'),
    address text,
    
    constraint person_pk PRIMARY KEY (cnic),
CONSTRAINT check_cnic_length CHECK (length(cnic)=13)
);

CREATE TABLE MEMBER(
member_id int(5) auto_increment,
    cnic char(13),
    
    constraint member_pk PRIMARY KEY (member_id),
    constraint member_fk fOREIGN KEY (cnic) REFERENCES person (cnic) ON DELETE cascade
);

ALTER TABLE member AUTO_INCREMENT=10000;

CREATE TABLE GUEST(
    cnic char(13),
    member_id int(5),
    firstName varchar(100),
    lastName varchar(100),
	
    constraint guest_pk PRIMARY KEY (cnic),
    constraint guest_fk fOREIGN KEY (member_id) REFERENCES MEMBER(member_id)   ON DELETE cascade,
	CONSTRAINT check_cnicLength CHECK (length(cnic)=13)
);

CREATE TABLE  DEPARTMENT
(dept_id int(5) AUTO_INCREMENT,
 deptName varchar(100), 
 supervisor_id int(5),
salary float,
 constraint department_pk PRIMARY KEY (dept_id),
CONSTRAINT check_salary_positive CHECK (salary >= 0)
);

CREATE TABLE  EMPLOYEE
(emp_id int  AUTO_INCREMENT , 
cnic char(13) , 
dept_id int(5) ,
constraint emp_fk1 fOREIGN KEY (cnic) REFERENCES PERSON(cnic) ON DELETE cascade
,
constraint emp_fk2 fOREIGN KEY (dept_id) REFERENCES DEPARTMENT(dept_id) ON DELETE cascade
,
constraint department_pk PRIMARY KEY (emp_id)
);

ALTER TABLE employee AUTO_INCREMENT=10000;




CREATE TABLE SPORT(
	sport_id int (5) auto_increment,
    sportName varchar(100),
    teamMember int(3) DEFAULT NULL,
    constraint sport_pk PRIMARY KEY (sport_id)
);

CREATE TABLE  COACH
(coach_id int(5),
sport_id int(5),
constraint coach_fk1 fOREIGN KEY (coach_id) REFERENCES EMPLOYEE(emp_id)  ON DELETE cascade,
constraint coach_fk2 fOREIGN KEY (sport_id) REFERENCES sport(sport_id)  ON DELETE cascade,
constraint coach_pk PRIMARY KEY (coach_id)
);

CREATE TABLE   CLASS
(class_id int(5)  AUTO_INCREMENT,
day enum('Monday','Tuesday','Wednesday','Thursday','Friday','Saturday','Sunday'), 
startTime time,
endTime time,
coach_id int(5),
constraint class_fk fOREIGN KEY (coach_id) REFERENCES COACH(coach_id) ON DELETE cascade,
constraint class_pk PRIMARY KEY (class_id)
);

CREATE TABLE   Trainee
(class_id int(5),
member_id int (5),
 constraint trainee_fk1 fOREIGN KEY (class_id) REFERENCES CLASS(class_id) ON DELETE cascade,
 constraint trainee_fk2 fOREIGN KEY (member_id)  REFERENCES MEMBER(member_id) ON DELETE cascade,
 constraint trainee_Pk PRIMARY KEY (class_id,member_id)
);

CREATE TABLE  ALLERGIES
(cnic char(13), 
allergy varchar(100),
constraint allergies_fk fOREIGN KEY (cnic) REFERENCES PERSON(cnic) ON DELETE cascade, 
constraint allergies_pk PRIMARY KEY (cnic, allergy)
);





CREATE TABLE  security_qs
(ques_id int(5) AUTO_INCREMENT,
 ques text,
 constraint department_pk PRIMARY KEY (ques_id)
);

CREATE TABLE  USERS
(username varchar(30), 
emp_id int(5),
password varchar(100),
security_qs_id int(5),
securityAns varchar(100),

constraint user_pk PRIMARY KEY (username),
constraint user_fk1 fOREIGN KEY (emp_id) REFERENCES EMPLOYEE(emp_id) ON DELETE cascade, 
constraint user_fk2 fOREIGN KEY (security_qs_id) REFERENCES security_qs(ques_id) ,
CONSTRAINT check_username_Length CHECK(length(username)>=6  AND length(username)<=30),
CONSTRAINT check_password_Length CHECK(length(password)>=8 )

);

CREATE TABLE TEAM(
	team_id int(5)  auto_increment,
    sport_id int(5),
    package enum('training', 'non-training'),
    
    constraint team_pk PRIMARY KEY (team_id),
	constraint team_fk FOREIGN KEY (sport_id) references SPORT(sport_id) ON DELETE cascade
);

CREATE TABLE TEAM_SCHEDULE(
team_id int(5) ,
    class_id int(5),
    constraint teamSchedule_fk1 FOREIGN KEY (team_id) references TEAM(team_id) ON DELETE cascade,
    constraint teamSchedule_fk2 FOREIGN KEY (class_id) references CLASS(class_id) ON DELETE cascade,
    constraint teamSchedule_pk PRIMARY KEY (team_id,class_id)	
);

CREATE TABLE ATTENDANCE(
	emp_id int(5) ,
    date date,
    status enum('P','A') default 'A',
    constraint attendance_fk FOREIGN KEY (emp_id) references EMPLOYEE(emp_id) ON DELETE cascade,
    constraint attendance_pk PRIMARY KEY (emp_id,date)	
);

CREATE TABLE Schedule (
    event_id int(5) NOT NULL AUTO_INCREMENT,
    eventName text,
    date date,
    time time,
    venue varchar(250),
    constraint schedule_pk PRIMARY KEY (event_id)
);

CREATE TABLE EMERGENCY(
	emer_id int(5) auto_increment,
    patient_id int(5),
    problem text,
    date date,
    time time,
    status enum('resolved', 'unresolved'),
    
    constraint emergency_pk PRIMARY KEY (emer_id),
    constraint emergency_fk FOREIGN KEY (patient_id) references MEMBER (member_id) 	ON DELETE cascade
);

CREATE TABLE INVENTORY(
	item_id int(5) auto_increment,
    sport_id int(5),
    itemName varchar(200),
    quantity int,
    
    constraint inventory_pk PRIMARY KEY (item_id),
    constraint inventory_fk FOREIGN KEY (sport_id) REFERENCES SPORT(sport_id) ON DELETE cascade
    	
);

CREATE TABLE ISSUED_ITEMS(
	issue_id int(5) auto_increment,
    member_id int(5),
    item_id int(5),
    time time,
    quantity int,
    
    constraint issue_pk PRIMARY KEY (issue_id),
    constraint issue_member_fk FOREIGN KEY (member_id) REFERENCES MEMBER (member_id),
     constraint issue_item_fk FOREIGN KEY (item_id) REFERENCES INVENTORY (item_id) ON DELETE cascade
);

CREATE TABLE INVENTORY_LOG(
    member_id int(5),
    date date,
    borrowedTime time,
    returnedTime time,
    damaged bool default false,
    
    constraint inventory_log_pk PRIMARY KEY (member_id,date,borrowedTime),
    constraint inventory_log_fk FOREIGN KEY (member_id) REFERENCES MEMBER (member_id)

);

CREATE TABLE MAINTENANCE(
	maintenance_id int(5) auto_increment,
    sport_id int(5),
    date date,
    activity varchar(200),
    level enum('Partial', 'Compelete', 'Not Started') default 'Not Started',
    
    constraint maintenance_pk PRIMARY KEY (maintenance_id),
    constraint maintenance_fk FOREIGN KEY (sport_id) REFERENCES sport (sport_id) ON DELETE cascade

);

CREATE TABLE MEDICAL_LOG(
	emer_id int(5),
    item_id int(5),
    quantity int,
    
    constraint medical_pk PRIMARY KEY (emer_id, item_id),
    constraint medical_emer_fk FOREIGN KEY (emer_id) references EMERGENCY (emer_id) ON DELETE cascade,
    constraint medical_item_fk FOREIGN KEY (item_id) references INVENTORY (item_id) ON DELETE cascade

);

create table REPAIRS(
	repair_id int(5) auto_increment,
    sport_id int(5),
    purpose text,
    amount float,
    status enum('Allocated', 'Refused', 'Pending'),
    
    constraint repairs_pk primary key (repair_id),
    constraint repairs_fk foreign key (sport_id) references sport (sport_id) ON DELETE cascade,
    CONSTRAINT check_amounts_positive CHECK (amount >= 0)

);
CREATE TABLE Report (
    report_id int(5) NOT NULL AUTO_INCREMENT,
    details varchar(500) NOT NULL,
    type ENUM ('complaint', 'suggestion'),
    status ENUM('addressed', 'unaddressed') default 'unaddressed',
    constraint report_pk PRIMARY KEY (report_id)
);

CREATE TABLE Notice (
    notice_id int(5) NOT NULL AUTO_INCREMENT,
    title varchar(100),
    text text,
    date date,
    constraint notice_pk PRIMARY KEY (notice_id)
);




CREATE TABLE Transactions (
    transaction_id int(10) NOT NULL AUTO_INCREMENT,
    type varchar(100),
    amount double,
    constraint transactions_pk PRIMARY KEY (transaction_id),
    CONSTRAINT check_amount_positive CHECK (amount >= 0)
);

CREATE TABLE credit_membership (
    member_id int(5) ,
    date date,
    amount float,
    status enum('paid', 'unpaid') default 'unpaid',
    constraint credit_Membership_fk FOREIGN KEY (member_id) REFERENCES MEMBER (member_id) ON DELETE cascade
);





  
 INSERT INTO person values 
(3740520208071,'Sana','Zehra','2001-11-12','F','03034650870','03006609887','s.zehra@hsh.com','A+','DHA_phase2_street12'),
(3740520208082,'Hamna','Rauf','2003-02-4','F','03003609887','03034780870',	'h.rauf@hsh.com','O+','Bharia_street6'),
(3740520208093,'Hafsa','Nadeem','2002-12-07','F','03215583698','03394766653','h.nadeem@hsh.com','AB+','TajResidency_street4'),
(3740520208096,'Hamza','Ahmed','2006-04-03','M','03355766653','03215584598','h.ahmed@hsh.com','O-','DHA_phase4_street5'),
(3740520208010,'Rafay','Waheed','2002-01-21','M','03205587643','03005987873','r.waheed@hsh.com','A-','I10_Block4_street2'),
(3740520208011,'Muskan','Amin','2003-07-30','F','03356829764','03556882764','m.amin@hsh.com','O-','TajResidency_street10'),
(3740520208012,'Uzair','Tauheed','1995-07-30','M','03356829364','03005987673','u.tauheed@hsh.com','A+','Bharia_street4'),
(3740520208013,'Umar','Malik','2002-01-21','M','03006605887','03205500643','u.malik@hsh.com','O+','I9_Block4_street2'),
(3740520208014,'Ubaida','Waheed','2002-09-04','F','03200087643','03205980073','u.waheed@hsh.com','AB-','DHA_phase5_street12'),
(3740520208015,'Maryam','Amjad','1998-08-21','F','03205987600','03215583098','m.amjad@hsh.com','A+','TajResidency_street 2'),
(3740520208016,'Ali','Faheem','2002-11-21','M','03210583698','03003609887','a.faheem@hsh.com','O-','DHA_phase6_street 8'),
(3740520208017,'Javeria','Nadeem','1999-11-01','F','03203609000','03210583008','j.nadeem@hsh.com','AB+','I9_Block2_street17'),
(3740520208018,	'Hassan',	'Malik','2000-01-01','M','03207709900','03000583128','h.malik@hsh.com','B-','TajResidency_street 9'),
(3740520208019,	'Bilal','Farooq','1999-01-12','M','03355766854','03215583560','b.farooq@hsh.com','O-','DHA_phase2_street5');

INSERT INTO member(cnic) values 
(3740520208071),
(3740520208082),
(3740520208093),
(3740520208096);


INSERT INTO guest values 
(2740520208010,10003,'Aliza','shah'),
(2740520208011,10000,'Sara','khan'),
(2740520208012,10002,'Usman','Ali'),
(2740520208013,10001,'Bilal','Saif');

INSERT INTO department(deptName,supervisor_id,salary)   values 
('registration',	10006,  45000),
('inventory',	10005,  25000),
('medical',	10004, 25000),
('finance',	10003, 50000),
('emergency',	10002, 25000),
('maintenance',	10001, 25000),
('coach',	10000, 40000),
('attendant',	10008,	25000),
('manager',	10009,	50000);



INSERT INTO employee(cnic,dept_id)   values 
(3740520208010,	7),
(3740520208011,	6),
(3740520208012,	5),
(3740520208013,	4),
(3740520208014,	3),
(3740520208015,	2),
(3740520208016,	1),
(3740520208017,	7),
(3740520208018,	8),
(3740520208019,	9);

ALTER TABLE department
ADD FOREIGN KEY (supervisor_id) REFERENCES Employee(emp_id) ON DELETE SET NULL;

INSERT INTO sport(sportName,teamMember) values 
('bowling',2),
('gym',null),
('swimming',null),
('Basket ball',5);

INSERT INTO coach values 
(10007	,3);

INSERT INTO class (day,startTime,endtime,coach_id) values 
('Monday',	'10:00:00',	'13:00:00',	10007),
('Tuesday',	'10:30:00',	'12:30:00',	10007),
('Wednesday','11:30:00','13:30:00',	10007),
('Thursday','13:30:00',	'16:30:00',	10007),
('Friday',	'9:30:00',	'10:30:00',	10007),
('Saturday','10:30:00',	'11:30:00',	10007);

INSERT INTO trainee  values 
(1,	10000),
(1,	10001),
(3,	10003),
(6,	10002);

INSERT INTO allergies values
(3740520208071,"pollen"),
(3740520208096,"Dust"),
(3740520208017,"Dust");

INSERT INTO security_qs (ques) values 
('Nick name'),
('Favourite colour'),
('Favourite pet'),
('Mother name');

INSERT INTO users  values 
('R.waheed02',	10000	,'Rfy@2100',	1,	'bunny'),
('M.amin03',	10001	,'Feb@2021',	2,	'black'),
('U.tauheed95',	10002	,'Sept@217',	3,	'cat'),
('U.malik02',	10003	,'Jan21@47',	2,	'blue'),
('U.waheed02',	10004	,'ubaida@150',	4,	'abaida'),
('M.amjad98',	10005	,'Mary@1998',	4,	'farzana'),
('A.faheem02',	10006	,'March#543',	2,	'brown'),
('J.nadeem99',	10007	,'Gvry@875',	3,	'fish'),
('h.malik00',	10008	,'hsaan@195',	3,	'fish'),
('b.farooq99',	10009	,'bilal@999',	2,	'white');




INSERT INTO team(sport_id,package)   values 
(4	,'training'),
(1	,'non-training');

INSERT INTO team_schedule  values 
(1,	4);

INSERT INTO attendance values 
(10000,	'2021-12-07',	'P'),
(10001,	'2021-12-07',	'A'),
(10002,	'2021-12-07',	'P'),
(10003,	'2021-12-07',	'P'),
(10004,	'2021-12-07',	'P'),
(10005,	'2021-12-07',	'P'),
(10006,	'2021-12-07',	'P'),
(10007,	'2021-12-07',	'P'),
(10008,	'2021-12-07',	'P'),
(10009,	'2021-12-07',	'P');

INSERT INTO schedule(eventName,date,time,venue)   values 
('Swimming Tournament',	'2021-12-6',	'12:30:00',	'Main Swimming Pool,floor1'),
('Bowling Competition',	'2021-12-20',	'14:00:00',	'Bowling Court 4,floor2');

INSERT INTO emergency(patient_id,problem,date,time,status)  values 
(10000,'fractured','2021-06-19','10:30:49','resolved'),
(10003,'injured','2021-11-17','15:15:26','resolved'),
(10002,'wrist sprains','2021-03-21','17:18:30','resolved'),
(10003,'Asthma attack','2021-12-04','12:30:49','unresolved');

INSERT INTO inventory(sport_id,itemName,quantity) values 
(1,'Bowling balls',35),
(1,'Bowling pins',60),
(2,'Dumbbells',9),
(4,'Balls',6),
(4,'Med First Aid Kit',4),
(1,'Med First Aid Kit',4),
(3	,'Med Oxygen tank',	3),
(2	,'Med  Hot bags',	7),
(2	,'Med  Drips',	8);


INSERT INTO issued_items(member_id,item_id,time,quantity) values 
(10003 , 3,'10:15:20',4),
(10001 ,1 ,'12:25:30',10),
(10001,2,'12:30:45',12),
(10000 , 4,'9:12:25',2);


INSERT INTO inventory_log values 
(10003,'2021-10-15','10:15:20','17:30:49',0),
(10001,'2021-10-15','12:25:30','15:30:30',0),
(10001,'2021-10-15','12:30:45','15:34:45',1),
(10000,'2021-10-25','9:12:25','13:12:25',0);

DELETE FROM issued_items WHERE issue_id =1;
DELETE FROM issued_items WHERE issue_id =2;
DELETE FROM issued_items WHERE issue_id =3;
DELETE FROM issued_items WHERE issue_id =4;

INSERT INTO issued_items(member_id,item_id,time,quantity) values 
(10003 , 8,'12:15:20',1),
(10002 ,4 ,'10:25:30',2),
(10001,9,'13:30:45',2),
(10000 , 2,'11:12:25',2);






INSERT INTO maintenance(sport_id,date,activity,level)  values 
(3,'2021-01-21','Checking','Partial'),
(3,'2021-02-07','Cleaning','Compelete'),
(2,'2021-02-16','Checking','Compelete'),
(4,'2021-05-19','Cleaning','Not Started');

INSERT INTO medical_log   values 
(1,    5,             1),
(2,    6,             1),
(3,	6,	1),
(4,	5,	1);

INSERT INTO repairs(sport_id,purpose,amount,status)   values 
(2,	'Repair treadmill' ,	10000 ,	'Pending'),
(3	,'Repair filter pump'	,50000,'Allocated');

INSERT INTO report(details,type,status)   values 
('Treadmill not working',	'complaint',	'addressed'),
('New AC for bowling court',	'suggestion',	'unaddressed'),
('More swimming dresses', 'suggestion', 'unaddressed'),
('Filter pump of swimming pool 3 not working', 'complaint', 'unaddressed');

INSERT INTO notice(title,text,date)   values 
('Swimming tournament','Join us for Swimming tournament on 6 DEC','2021-12-6'),
('Bowling competition','Gear up for Bowling competition on  20 DEC','2021-12-20'),
('Christmas Holiday','Enjoy Christmas & spread happiness','2021-12-25');

INSERT INTO transactions(type,amount) values 
('Electricity_bill',	30000),
('maintenanceFunds',	15000),
('inventoryFunds',	25000),
('repairFunds',	7000);

INSERT INTO credit_membership(member_id,date,amount,status) values 
(10000,	'2020-11-01' ,5000,	'paid'),
(10001,	null,	8000,	'unpaid'),
(10002	,null,	5000,	'unpaid'),
(10003	,'2020-10-06',	10000,	'paid');

