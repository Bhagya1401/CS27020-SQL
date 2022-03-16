DROP TABLE IF EXISTS schememodules;
DROP TABLE IF EXISTS blocks;
DROP TABLE IF EXISTS schemes;
DROP TABLE IF EXISTS modules;
drop table if exists awards;

create table awards (
  Award varchar(10) PRIMARY key,
  AwardName Varchar(50)
  );
  
CREATE TABLE schemes (
  SchemeID varchar(4) PRIMARY Key,
  Scheme_Name Varchar(50),
  Award varchar(10),
  FOREIGN key(Award) REFERENCES awards(Award)
  );
  
  create table modules (
    ModuleID varchar(5) Primary key,
    Module_Name Varchar(50),
    Credits INTEGER
    );
    
  Create table Blocks (
    SchemeID varchar(4),
    Year integer,
    Block VARCHAR(1),
    size INTEGER,
    Primary key(SchemeID, Year, Block),
    FOREIGN KEY(SchemeID) REFERENCES schemes(SchemeID)
  );
  
  create table SchemeModules (
    SchemeID varchar(4),
    ModuleID varchar(5),
    Year Integer,
    Block Varchar(1),
    FOREIGN key(SchemeID) REFERENCES Schemes(SchemeID),
    FOREIGN key(ModuleID) REFERENCES Modules(ModuleID),
    FOREIGN key(SchemeID,Year, Block) References Blocks(SchemeID, Year, Block)
);

Insert into awards VALUES
('BSc', 'Bachelor of Science');

INSERT INTO schemes VALUES
('G444','Deep Learning','BSc'),
('P555','Physics','BSc');

INSERT INTO modules VALUES
('CS120','Introduction to Programming',20),
('CS123','Object Oriented Programming',20),
('CS180','Professional Issues',10),
('CS104','Maths Driving License',10),
('CS125','Functional Programming',10),
('CS211','Algorithms and Data Structures',20),
('CS270','Modelling Persistent Data',20),
('CS244','Scientific Programming',20),
('CS260','Artificial Intelligence',20),
('PH100','Introduction to Forces',20),
('PH120','Introduction to Electromagnetism',20),
('PH150','Professional Issues',10),
('PH180','Laboratory Techniques',20),
('PH200','Thermodynamics',20),
('PH240','Electricity and Magnetism',20),
('PH290','Astronomy',20);

Insert into blocks VALUES
('G444',1,'A',110),
('G444',1,'B',10),
('G444',2,'A',80),
('G444',2,'B',40),
('P555',1,'A',110),
('P555',1,'B',10),
('P555',2,'A',80),
('P555',2,'B',40);

insert into schememodules VALUES
('G444','CS120',1,'A'),
('G444','CS123',1,'A'),
('G444','CS180',1,'A'),
('G444','CS104',1,'B'),
('G444','CS125',1,'B'),
('G444','CS211',2,'A'),
('G444','CS270',2,'A'),
('G444','CS244',2,'B'),
('G444','CS260',2,'B'),
('P555','PH100',1,'A'),
('P555','PH120',1,'A'),
('P555','PH150',1,'A'),
('P555','CS104',1,'B'),
('P555','PH180',1,'A'),
('P555','PH200',2,'A'),
('P555','PH240',2,'A'),
('P555','CS244',2,'B'),
('P555','PH290',2,'B');



