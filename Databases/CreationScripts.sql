create table Instructor(
  instructorID int primary key,
  firstName varchar(50),
  lastName varchar(50)
);

create table University(
  universityID int primary key,
  address varchar(100),
  universityName varchar(100),
  repFirstName varchar(100),
  repLastName varchar(100),
  universityEmail varchar(50),
  univeristyPhone varchar(50)
);

create table Department(
  departmentName varchar(50),
  universityID int,
  primary key (departmentName),
  foreign key (universityID) references University(universityID)
);

create table Course(
  courseID int primary key,
  courseName varchar(50),
  year int,
  semester varchar(50),
  departmentName varchar(50),
  foreign key (departmentName) references Department(departmentName)
);
  
create table Teaches(
  instructorID int,
  courseID int,
  primary key (instructorID, courseID),
  foreign key (instructorID) references Instructor(instructorID),
  foreign key (courseID) references Course(courseID)
);

create table Works(
  universityID int,
  instructorID int,
  primary key (universityID, instructorID),
  foreign key (universityID) references University(universityID),
  foreign key (instructorID) references Instructor(instructorID)
);

create table UniversityCourse(
  universityID int,
  courseID int,
  primary key (universityID, courseID),
  foreign key (universityID) references University(universityID),
  foreign key (courseID) references Course(courseID)
);

create table User(
  userID int primary key,
  firstName varchar(50),
  lastName varchar(50)
);

create table Student(
  studentID int primary key,
  userID int,
  birthday varchar(50),
  universityID int,
  major varchar(50),
  studentStatus varchar(50),
  year int,
  foreign key (universityID) references University(universityID),
  foreign key (userID) references User(userID)
);
 
create table Takes(
  courseID int,
  studentID int,
  primary key (courseID, studentID),
  foreign key (courseID) references Course(courseID),
  foreign key (studentID) references Student(studentID)
);

create table Employee(
  employeeID int primary key,
  userID int,
  employeeType varchar(50),
  SSN int,
  gender varchar(50),
  salary int,
  emailAddress varchar(50),
  address varchar(100),
  phoneNumber int,
  company varchar(50)
  foreign key (userID) references User(userID)
);

create table Cart(
  cartID int primary key,
  studentID int,
  dateCreated varchar(50),
  dateLastUpdated varchar(50),
  foreign key (studentID) references Student(studentID)
);

create table CartOrder(
  orderID int primary key,
  cartID int,
  studentID int,
  dateCreated varchar(50),
  dateFulfilled varchar(50),
  shippingType varchar(50),
  orderStatus varchar(50),
  foreign key (studentID) references Student(studentID),
  foreign key (cartID) references Cart(cartID)
);

create table CreditCardInfo(
  studentID int primary key,
  CCNumber int,
  CCExpiration varchar(50),
  CCName varchar(50),
  CCType varchar(50),
  foreign key (studentID) references Student(studentID)
);

create table Sale(
  cartID int,
  orderID int,
  saleDate varchar(50),
  studentID int,
  primary key (cartID, orderID),
  foreign key (studentID) references Student(studentID)
);

create table Ticket(
  ticketNumber int primary key,
  userID int,
  userType varchar(50),
  status varchar(50),
  foreign key (userID) references User(userID)
);

create table TroubleTicket(
  ticketNumber int primary key,
  category varchar(50),
  dateLogged varchar(50),
  createdBy varchar(50),
  title varchar(50),
  completionDate varchar(50),
  problemDescription varchar(500),
  fixDescription varchar(500),
  adminWhoFixed int,
  foreign key (ticketNumber) references Ticket(ticketNumber),
  foreign key (adminWhoFixed) references Employee(employeeID)
);

create table TicketHistory(
  ticketNumber int primary key,
  status varchar(50),
  date varchar(50),
  changeDescription varchar(500),
  changedBy varchar(50),
  foreign key (ticketNumber) references Ticket(ticketNumber)
);

create table Book(
  bookID int primary key,
  isbn int,
  bookTitle varchar(100),
  authorID int,
  isbn13 int,
  publisher varchar(100),
  publishedDate varchar(50),
  editionNumber int
);

create table BooksInCart(
  cartID int,
  bookID int,
  transactionType varchar(50),
  primary key (cartID, BookID, transactionType),
  foreign key (cartID) references Cart(cartID),
  foreign key (bookID) references Book(bookID)
); 

create table Author(
  authorID int primary key,
  bookID int,
  firstName varchar(50),
  lastName varchar(50),
  foreign key (bookID) references Book(bookID)
);

create table Inventory(
  bookID int primary key,
  enteredBy int,
  dateEntered varchar(50),
  quantity int,
  rating int,
  foreign key (enteredBy) references Employee(employeeID)
);

create table BookLanguage(
  bookID int,
  language varchar(50),
  primary key (bookID, language),
  foreign key (bookID) references Inventory(bookID)
);

create table BookFormat(
  bookID int,
  format varchar(50),
  price int,
  weight int,
  primary key (bookID, format),
  foreign key (bookID) references Inventory(bookID)
);

/* A bit can be either 1 or 0 */
create table BookCondition(
  bookID int primary key,
  new BIT,
  used BIT,
  foreign key (bookID) references Inventory(bookID)
);

create table Recommendation(
  studentID int,
  bookID int,
  primary key (studentID, bookID),
  foreign key (studentID) references Student(studentID),
  foreign key (bookID) references Inventory(bookID)
);

create table CourseBook(
  courseID int,
  year int,
  semester varchar(50),
  instructorID int,
  bookID int,
  primary key (courseID, year, semester, instructorID),
  foreign key (courseID) references Course(courseID),
  foreign key (bookID) references Inventory(bookID) 
);
create table CreditCardInfo(
  studentID int primary key,
  CCNumber int,
  CCExpiration varchar(50),
  CCName varchar(50),
  CCType varchar(50),
  foreign key (studentID) references Student(studentID)
);

create table Sale(
  cartID int,
  orderID int,
  saleDate varchar(50),
  studentID int,
  primary key (cartID, orderID),
  foreign key (studentID) references Student(studentID)
);

create table Ticket(
  ticketNumber int primary key,
  userID int,
  userType varchar(50),
  status varchar(50),
  foreign key (userID) references User(userID)
);

create table TroubleTicket(
  ticketNumber int primary key,
  category varchar(50),
  dateLogged varchar(50),
  createdBy varchar(50),
  title varchar(50),
  completionDate varchar(50),
  problemDescription varchar(500),
  fixDescription varchar(500),
  adminWhoFixed int,
  foreign key (ticketNumber) references Ticket(ticketNumber),
  foreign key (adminWhoFixed) references Employee(employeeID)
);

create table TicketHistory(
  ticketNumber int primary key,
  status varchar(50),
  date varchar(50),
  changeDescription varchar(500),
  changedBy varchar(50),
  foreign key (ticketNumber) references Ticket(ticketNumber)
);

create table Book(
  bookID int primary key,
  isbn int,
  bookTitle varchar(100),
  authorID int,
  isbn13 int,
  publisher varchar(100),
  publishedDate varchar(50),
  editionNumber int
);

create table BooksInCart(
  cartID int,
  bookID int,
  transactionType varchar(50),
  primary key (cartID, BookID, transactionType),
  foreign key (cartID) references Cart(cartID),
  foreign key (bookID) references Book(bookID)
); 

create table Author(
  authorID int primary key,
  bookID int,
  firstName varchar(50),
  lastName varchar(50),
  foreign key (bookID) references Book(bookID)
);

create table Inventory(
  bookID int primary key,
  enteredBy int,
  dateEntered varchar(50),
  quantity int,
  rating int,
  foreign key (enteredBy) references Employee(employeeID)
);

create table BookLanguage(
  bookID int,
  language varchar(50),
  primary key (bookID, language),
  foreign key (bookID) references Inventory(bookID)
);

create table BookFormat(
  bookID int,
  format varchar(50),
  price int,
  weight int,
  primary key (bookID, format),
  foreign key (bookID) references Inventory(bookID)
);

/* A bit can be either 1 or 0 */
create table BookCondition(
  bookID int primary key,
  new BIT,
  used BIT,
  foreign key (bookID) references Inventory(bookID)
);

create table Recommendation(
  studentID int,
  bookID int,
  primary key (studentID, bookID),
  foreign key (studentID) references Student(studentID),
  foreign key (bookID) references Inventory(bookID)
);

create table CourseBook(
  courseID int,
  year int,
  semester varchar(50),
  instructorID int,
  bookID int,
  primary key (courseID, year, semester, instructorID),
  foreign key (courseID) references Course(courseID),
  foreign key (bookID) references Inventory(bookID) 
);
