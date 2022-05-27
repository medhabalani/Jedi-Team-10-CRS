/**
 * 
 */
package com.crs.flipkart.constants;

/**
 * @author devanshugarg
 *
 */

/*
 * This is the SQL Query file where we have written all the required queries
 * from different actors as Student, Admin and Professor where all the 
 * operations are communicating with the mySql database.
 */

public class SQLQueriesConstant {

	public static final String ADD_USER_QUERY = "insert into User(userId, userName, userEmailId, userPassword, role, phoneNo, gender, address) values (?, ?, ?, ?, ?, ?, ?, ?)";
	public static final String ADD_PROFESSOR_QUERY = "insert into Professor(userId, professorId, department, designation) values (?, ?, ?, ?)";
	public static final String VIEW_PROFESSOR_QUERY = "select professorId, userName, gender, department, designation, U.userEmailId, U.userId, U.phoneNo, U.role, U.address, P.courseId, U.userPassword from Professor P natural join User U where P.userId = U.userId";
	public static final String DELETE_PROFESSOR_QUERY = "delete from Professor where professorId = ?";
	public static final String GET_USER_ID_FROM_PROFESSOR = "select userId from professor where professorId = ?";
	public static final String FIND_STUDENT_QUERY="select studentId from student where studentId=?";
	public static final String DELETE_USER = "delete from user where userId = ?";
	public static final String ADD_COURSE_QUERY = "insert into Course(courseId, courseName, courseDescription, courseFee, courseSeats) values (?, ?, ?, ?, ?)";
	public static final String VIEW_COURSE_QUERY = "select courseId, courseName, courseDescription, courseFee, courseSeats from Course";
	public static final String DELETE_COURSE_QUERY = "delete from Course where courseId = ?";
	public static final String APPROVE_STUDENT_QUERY = "update Student set isApproved = 1 where studentId = ?";
	public static final String GET_STUDENT_ID = "select studentId from student where userId = ?";
	public static final String GET_PROFESSOR_ID = "select professorId from professor where userId = ?";
	public static final String IS_APPROVED = "select isApproved from student where studentId = ?";
	public static final String VIEW_COURSES_GRADE = "select courseId, gpa from gradecard where studentId = ?";
	public static final String ADD_STUDENT_QUERY = "insert into Student(userId, branchName, batch, studentId) values (?, ?, ?, ?)";
	public static final String GET_REGISTERED_COURSE_FEE = "select courseFee from course where courseId IN (select courseId from registeredcourse where studentId = ?)";
 	public static final String PAYMENT_STATUS = "select status from payment where studentId = ?";
 	public static final String ADD_PAYMENT = "insert into payment(invoiceId, studentId, amount, status) values(?, ?, ?, ?)";
 	public static final String PAYMENT_CARD = "insert into card(invoiceId, cardType, cardNumber, cardHolderName, cvv, bankName, expiryDate) values(?, ?, ?, ?, ?, ?, ?)";
 	public static final String PAYMENT_CHEQUE = "insert into cheque(invoiceId, chequeNo, bankAccountHolderName, bankAccountNumber, ifsc, bankName, bankBranchName, chequeDate) values(?, ?, ?, ?, ?, ?, ?, ?)";
 	public static final String PAYMENT_NETBANKING = "insert into netbanking(bankName, bankAccountHolderName, invoiceId) values(?, ?, ?)";
 	public static final String SET_GRADECARD_STATUS = "update Student set isGenerated = 1 where studentId = ?";
 	public static final String IS_GENERATED = "select isGenerated from student where studentId = ?";
 	public static final String CHECK_COURSE = "select courseId from course where courseId = ?";
 	public static final String GET_COURSES = "select professor.professorId, course.courseId, course.courseName, course.courseSeats from course INNER JOIN professor where course.courseId = professor.courseId and professorId = ?";
 	public static final String GET_ENROLLED_STUDENTS = "select professor.professorId as professorId, course.courseId as courseId, course.courseName as courseName, registeredcourse.studentId as studentId from course inner join registeredcourse on course.courseId = registeredcourse.courseId INNER JOIN professor on course.courseId = professor.courseId where professor.professorId = ? order by course.courseId";
 	public static final String ADD_GRADE = "insert into gradecard (courseId,studentId, semesterId, gpa) values (?,?,?,?)";
 	public static final String GET_PROF_NAME = "SELECT professor.professorId, user.userName FROM user INNER JOIN professor WHERE professorId = ?";
 	public static final String ADD_COURSE_TO_PROFESSOR = "update Professor set courseId = ? where professorId = ?";
 	public static final String GET_AVAILABLE_COURSES = "select course.courseId, course.courseName, course.courseDescription, course.courseFee, course.courseSeats from course where courseId not in (select courseId from Professor)";
 	public static final String CHECK_SELECTED_COURSE = "select courseId from professor where professorId = ?";
 	public static final String CHECK_SEMESTER_REGISTRATION = "select semesterId from semesterRegistration where studentId = ?";
 	public static final String VIEW_AVAILABLE_COURSES = "select * from Course where courseSeats > 0 and courseId not in (select courseId from registeredCourse where studentId = ?)";
 	public static final String TOTAL_REGISTERED_COURSES = "select courseId from registeredCourse where studentId = ?";
 	public static final String AVAILABLE_SEATS = "select courseSeats from Course where courseId = ?";
 	public static final String ADD_COURSE = "insert into registeredcourse (studentId,courseId) values (?, ?)";
 	public static final String DECREMENT_SEAT = "update Course set courseSeats = courseSeats-1 where courseId = ? ";
 	public static final String DROP_COURSE = "delete from registeredcourse where studentId = ? AND courseId = ?";
 	public static final String INCREMENT_SEAT = "update Course set courseSeats = courseSeats + 1 where courseId = ? ";
 	public static final String VIEW_REGISTERED_COURSES = "select * from Course inner join registeredCourse on Course.courseId = registeredCourse.courseId where registeredCourse.studentId = ?";
 	public static final String CHECK_STUDENT_AND_SEM = "select studentId from semesterRegistration where studentId = ? and semester = ?";
 	public static final String ADD_SEMESTER = "insert into semesterRegistration(semesterId, studentId, semester, date) values (?, ?, ?, curdate())";
 	public static final String UPDATE_PASSWORD = "update user set userPassword=? where userEmailId = ?";
 	public static final String VERIFY_CREDENTIALS = "select userPassword from user where userEmailId = ?";
 	public static final String GET_ROLE = "select userId, role from user where userEmailId = ?";
 	public static final String GET_USER_ID = "select userId from user where userEmailId = ?";
 	public static final String INSERT_NOTIFICATION = "insert into notification(studentId, notificationType, notificationId, referenceId, notificationContent) values(?, ?, ?, ?, ?);";
 	public static final String VIEW_PENDING_ADMISSION_QUERY = "select user.userId, user.userName, user.userPassword, user.role, user.gender, user.address, studentId, student.batch, student.branchName, user.phoneNo, user.userEmailId from user inner join student where isApproved = 0 and student.userId = user.userId";
 	public static final String ADD_NOTIFICATION = "insert into notification(notificationId, studentId, notificationType, referenceId, notificationContent) values (?, ?, ?, ?, ?)";
 	public static final String IS_REGISTERED = "select courseId from registeredCourse where courseId = ? and studentId = ?";
 	public static final String GET_NOT_GRADED_STUDENTS = "select professor.professorId as professorId, course.courseId as courseId, course.courseName as courseName, registeredcourse.studentId as studentId \n"
  			+ "from course \n"
  			+ "inner join registeredcourse on course.courseId = registeredcourse.courseId INNER JOIN professor on course.courseId = professor.courseId \n"
  			+ "where professor.professorId = ? and registeredcourse.studentId not in (SELECT studentId from gradecard)\n"
  			+ "order by course.courseId;";
}
