import java.util.ArrayList;

public class user implements java.io.Serializable {
	protected String username;
	protected String password;
	protected String fname;
	protected String lname;
	
	public user(String username, String password, String fname, String lname) {
        this.username = username;
        this.password = password;
        this.fname = fname;
        this.lname = lname;
    }

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public void viewAllCourses(ArrayList<courses> courses) {
		System.out.println("All Courses:");
    	for (int i = 0; i < courses.size(); i++) {
            courses course = courses.get(i);
            System.out.println(course.getCoursename() + " (" + course.getCourseid() + ")");
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
                System.out.println("Location: " + course.getCourseloc());
                System.out.println();
                hasAvailableCourses = true;
            }
        }

        if (!hasAvailableCourses) {
            System.out.println("No available courses at this time.");
        }
	}

	
	
}
