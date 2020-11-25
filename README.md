# CZ2002-Project

My STudent Automated Registration System (MySTARS)

## Overview

The following are information about the application:

- It is a university application meant for each School’s academic staff and
  undergraduate students.
- The application allows the creation of courses and adding of student records as well
  as registration of courses and students. There will be an administrator mode for
  academic staff and user mode for students.
- At the start of each semester registration period, students will be required to register
  for their courses. Each course (subject) will have its course code, its corresponding
  index number information, the class schedules and venue, and available vacancy.
- Courses may have lectures only, lectures and tutorial only or lectures, tutorial and
  laboratory sessions.
- Students on waitlist will be placed in a queue whereby when there is available slot,
  the first in queue will be allocated the slot. A notification will be sent to the student.

# Use STARSUI.java to run the program!

The images below show the console displays when logged in as a student and a staff respectively.

### Student Console

![StudentConsole](https://user-images.githubusercontent.com/70562873/100107529-b4eedf80-2ea4-11eb-9c60-d644fd2b956a.JPG)

### Staff Console

![StaffConsole](https://user-images.githubusercontent.com/70562873/100107664-d9e35280-2ea4-11eb-81ae-711c28a2b6b3.JPG)

## Javadoc

The Javadoc documents can be found [here](https://github.com/madrackwp/CZ2002-Project/tree/main/html)

### These are the following usernames and respective passwords for each student/staff:

| username  | password     |
| --------- | ------------ |
| student1  | P@ssword100  |
| student2  | P@ssword200  |
| student3  | P@ssword300  |
| student4  | P@ssword400  |
| student5  | P@ssword500  |
| student6  | P@ssword600  |
| student7  | P@ssword700  |
| student8  | P@ssword800  |
| student9  | P@ssword900  |
| student10 | P@ssword1000 |
| student11 | P@ssword1100 |
| student12 | P@ssword1200 |
| student13 | P@ssword1300 |
| student14 | P@ssword1400 |
| bgoh015   | P@ssword1500 |
| staff2    | helloworld   |

### NOTE:

Our project was done on VS code so there might be some incompatibility errors when opening it on other platforms such as Eclipse.

### Text files:

As the program runs, changes will be reflected directly in courseData.txt and studentData.txt. There will be no changes made to staffData.txt. For your reference, the following shows the starting state for both courseData.txt and studentData.txt. This is to demonstrate each function to clearly see the change in the database depending on the course of action taken in the program.

#### Start state for courseData.txt:
LJ9001 11333 3 SOH UE null 9 U1960617B 16:30,18:30,Tutorial,TUESDAY 16:30,18:30,Tutorial,THURSDAY <br/>
LJ9001 11334 3 SOH UE null 10 null 09:30,11:30,Tutorial,MONDAY 09:30,11:30,Tutorial,WEDNESDAY <br/>
EE8086 18481 2 EEE GERPE_STS,UE null 9 U1960617B 08:30,09:30,Tutorial,THURSDAY 13:30,16:30,Lecture,TUESDAY <br/>
EE8086 18482 2 EEE GERPE_STS,UE null 10 null 09:30,10:30,Tutorial,THURSDAY 13:30,16:30,Lecture,TUESDAY <br/>
BS8001 87250 3 SBS GERPE_STS,UE null 10 null 13:30,16:30,Tutorial,WEDNESDAY <br/>
CZ2001 10100 3 SCSE CORE null 10 null 14:30,15:30,Tutorial,TUESDAY 08:30,09:30,Lecture,MONDAY <br/>
CZ2001 10101 3 SCSE CORE null 10 null 12:30,13:30,Tutorial,TUESDAY 08:30,09:30,Lecture,MONDAY <br/>
CZ2001 10102 3 SCSE CORE null 10 null 09:30,10:30,Tutorial,FRIDAY 08:30,09:30,Lecture,MONDAY <br/>
CZ2002 10200 3 SCSE CORE null 10 null 10:30,11:30,Tutorial,THURSDAY 11:30,12:30,Lecture,FRIDAY <br/>
CZ2002 10201 3 SCSE CORE null 10 null 11:30,12:30,Tutorial,MONDAY 11:30,12:30,Lecture,FRIDAY <br/>
CZ2002 10202 3 SCSE CORE null 10 null 13:30,14:30,Tutorial,WEDNESDAY 11:30,12:30,Lecture,FRIDAY <br/>
CZ2002 10203 3 SCSE CORE null 10 null 15:30,16:30,Tutorial,TUESDAY 11:30,12:30,Lecture,FRIDAY <br/>
CZ2003 10128 3 SCSE CORE null 10 null 15:30,16:30,Tutorial,FRIDAY 11:30,12:30,Lecture,THURSDAY 12:30,16:30,Lab,WEDNESDAY <br/>
CZ2003 10129 3 SCSE CORE null 9 U1960617B 13:30,14:30,Tutorial,WEDNESDAY 11:30,12:30,Lecture,THURSDAY 12:30,14:30,Lab,MONDAY <br/>
CZ2003 10130 3 SCSE CORE null 3 U1997741M 10:30,11:30,Tutorial,TUESDAY 11:30,12:30,Lecture,THURSDAY 14:30,16:30,Lab,TUESDAY<br/>
CZ2003 12345 3 SCSE CORE null 1 null 10:30,11:30,Tutorial,TUESDAY 11:30,12:30,Lecture,THURSDAY 14:30,16:30,Lab,TUESDAY <br/>

#### Start state for studentData.txt:
student1 966610007 bobtan SCSE U1945797A 2 25/11/2020 null <br/>
student2 966610968 Luke SCSE U1960617B 2 24/11/2020 LJ9001,11333,UE CZ2002,10201,CORE EE8086,18481,GERPE_STS CZ2003,10129,CORE CZ2001,10102,CORE <br/>
student3 966611929 Atticus SPMS U1970579C 2 24/11/2020 null <br/>
student4 966612890 Mark SCSE U1953115D 2 22/11/2020 null <br/>
student5 966613851 Clarence SCSE U1933490E 2 22/11/2020 null <br/>
student6 966614812 Bryan SCSE U1955473F 2 22/11/2020 null <br/>
student7 966615773 JingHong SCSE U1959764G 2 22/11/2020 null <br/>
student8 966616734 WeiPin SCSE U1915125H 2 22/11/2020 null <br/>
student9 966617695 Jonathan SCSE U1922516J 2 22/11/2020 null <br/>
student10 -99860807 Kelvin SCSE U1913906K 2 22/11/2020 null <br/>
student11 -99859846 Melvin SCSE U1932008L 2 22/11/2020 null <br/>
student12 -99858885 Alicia SCSE U1997741M 2 23/11/2020 CZ2003,10130,CORE <br/>
student13 -99857924 JunXiong SCSE U1910439N 2 22/11/2020 null <br/>
student14 -99856963 Nicolette SCSE U1992616P 2 22/11/2020 null <br/>
bgoh015 -99856002 Bryann WKW U1920609E 1 22/11/2020 null <br/>

### Email notification

These are the log in details for the dummy gmail accounts created so that you are able to log in into both these accounts to verify that the email notification has been sent/received.

#### For the staff:

Email address: staffaccntustar@gmail.com <br/>
Password: P@ssword12345

#### For the student:

Email address: student1bobtan@gmail.com <br/>
Password: P@ssword12345

#### On adding relevant .jar files for email notification:

- Using the project manager for java extension in VScode,
- Add mail.jar and activation.jar from the JarFiles folder by clicking the + sign next to Referenced Libraries node in project view
- If this step is not done, the email notification class will not be able to import the packages needed to send an email!!
