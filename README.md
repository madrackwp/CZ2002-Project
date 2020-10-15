# CZ2002-Project

Potential classes:

1. Students

   - Add course
   - Drop course
   - Check/Print courses registered
   - Check vacancies
   - Change index
   - Swap index with another student

2. Admins

   - Edit student access period
   - Add a student
   - Add/Update a course
   - Cehck available slot for an index number
   - Print student list by index number
   - Print studnet list by course (prints only name, gender and nationality)

3. Courses

   - Each course will have:
     - Course code
     - School
     - Index numbers
     - Vacancies
     - Return noVacancies(courseCode)
     - editCourse(courseCode)

User Interface:

- login(UserName, password)

Data files:

- Users (password/usernames)
- Passwords should have a hash function --> hashCode() method
- Courses
- Courses and Users should have a method to store all information into a textfile
