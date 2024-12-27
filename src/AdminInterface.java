
public interface AdminInterface {
	    void deleteCourse(String courseId, int sectionnum);
	    void editCourse(String courseId, int sectionnum, String newInstructor, int newMaxStudents, String newLocation);	   
	    void displayCourse(String courseid, int sectionnum);
	    void registerStudent(String studentName, String courseId, int sectionnum);
	    void viewAllCourses();
	    void viewFullCourses();
	    void writeFullCoursesToFile();
	    void viewStudentsInCourse(String courseId,int sectionnum);
	    void viewCoursesOfStudent(String studentName);
	    void sortCoursesByCurrentStudents();
		void createCourse(String coursename, String courseid, int maxcoursestudents, String instructorN, int sectionnum,
				String courseloc);
	    void createCourse(String courseName, String courseId, int maxStudents);

}

