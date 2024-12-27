import java.util.ArrayList;

public interface StudentInterface {
	void viewAllCourses(ArrayList<courses> courses);
    void viewAvailableCourses(ArrayList<courses> courses);
    void registerInCourse(String courseId, int sectionnum, ArrayList<courses> courses);
    void withdrawFromCourse(String courseId, int sectionnum);
    void viewRegisteredCourses();
}
