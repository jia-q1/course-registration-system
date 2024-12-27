import java.util.*;

public class Student extends user implements StudentInterface, java.io.Serializable{
	private ArrayList<courses> registeredCourses;
	public Student(String username, String password, String firstName, String lastName) {
        super(username, password, firstName, lastName);
        this.registeredCourses = new ArrayList<>();
    }

    public void viewAllCourses(ArrayList<courses> courses) {
    	System.out.println("All Courses:");
    	for (int i = 0; i < courses.size(); i++) {
            courses course = courses.get(i);
            System.out.println(course.getCoursename() + " (" + course.getCourseid() + ") - Instructor: " + course.getInstructorN());
        }
    }

    public void viewAvailableCourses(ArrayList<courses> courses) {
    	 System.out.println("Available Courses:");
    	 boolean hasAvailableCourses = false; 
    	 for (int i = 0; i < courses.size(); i++) {
             courses course = courses.get(i);
             if (!course.isFull()) {
                 System.out.println("Course Name: " + course.getCoursename());
                 System.out.println("Instructor: " + course.getInstructorN());
                 System.out.println("Max Students: " + course.getMaxcoursestudents());
                 System.out.println("Current Students: " + course.getCurrentcoursestudents());
                 System.out.println("Section: " + course.getSectionnum());
                 System.out.println("Location: " + course.getCourseloc());
                 System.out.println();
                 hasAvailableCourses = true;
             }
         }

         if (!hasAvailableCourses) {
             System.out.println("No available courses at this time."); // Message if no courses are available
         }
    }

    public void registerInCourse(String courseId, int sectionnum, ArrayList<courses> courses) {
    	for (int i = 0; i < courses.size(); i++) {
            courses course = courses.get(i);
            if (course.getCourseid().equals(courseId) && course.getSectionnum() == sectionnum && !course.isFull()) {
                if (course.addStudent(getFname() + "," + getLname())) {
                    registeredCourses.add(course);
                    System.out.println("Successfully registered in " + course.getCoursename() + " (Section " + course.getSectionnum() + ")");
                } else {
                    System.out.println("Registration failed. Course is full.");
                }
                return;
            }
        }
        System.out.println("Course not found or already full.");
    }

    

    @Override
    public void withdrawFromCourse(String courseId, int sectionnum) {
    	for (int i = 0; i < registeredCourses.size(); i++) {
            courses course = registeredCourses.get(i);
            if (course.getCourseid().equals(courseId) && course.getSectionnum() == sectionnum) { // Check courseId and sectionnum
                if (course.removeStudent(getFname() + "," + getLname())) {
                    registeredCourses.remove(i);
                    System.out.println("Successfully withdrew from " + course.getCoursename() + " (Section " + sectionnum + ")");
                } else {
                    System.out.println("Withdrawal failed. You are not registered in this course.");
                }
                return;
            }
        }
        System.out.println("You are not registered in this course (Section " + sectionnum + ").");
    }

    @Override
    public void viewRegisteredCourses() {
        if (registeredCourses.isEmpty()) {
            System.out.println("No courses registered.");
        } else {
            System.out.println("Registered Courses:");
            for (int i = 0; i < registeredCourses.size(); i++) {
                courses course = registeredCourses.get(i);
                System.out.println(course.getCoursename() + " (" + course.getCourseid() + "), Section " + course.getSectionnum());
            }
        }
    }
	
}
