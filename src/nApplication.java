import java.io.*; /*saving space for the compiler */
import java.util.*;

public class nApplication {
	
	private static Admin admin;
	private static ArrayList<courses> courses = new ArrayList<>();
	private static final String COURSES_FILE="courses.ser";
	
   	
	public static void main(String[] args) {
		if (!deserializeCourses()) {
			loadCourseFromFile("MyUniversityCourses.csv");
		}
		admin = new Admin("Admin", "Admin001", "Admin", "User", courses);
		
		serializeCourses();
		
		deserializeCourses();
		
		Scanner scanner = new Scanner(System.in);
		
		while (true) {
            System.out.println("Are you an Admin or Student? (Enter 'A' for Admin or 'S' for Student, 'E' to exit)");
            String userType = scanner.nextLine().toUpperCase();
            if (userType.equals("E")) {
                break;
            } else if (userType.equals("A")) {
                adminMenu(scanner);
            } else if (userType.equals("S")) {
                studentMenu(scanner);
            } else {
                System.out.println("Invalid input. Please try again.");
            }
        }
        scanner.close();
        
    }
	
	   private static void serializeCourses() {
	        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(COURSES_FILE))) {
	            oos.writeObject(courses);
	        } catch (IOException ioe) {
	            System.err.println("Error during serialization: " + ioe.getMessage());
	        }
	    }

	   private static boolean deserializeCourses() {
	        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(COURSES_FILE))) {
	            Object obj = ois.readObject();
	            if (obj instanceof ArrayList<?>) {
	                ArrayList<?> list = (ArrayList<?>) obj;
	                if (!list.isEmpty() && list.get(0) instanceof courses) {
	                    @SuppressWarnings("unchecked")
	                    ArrayList<courses> loadedCourses = (ArrayList<courses>) list;
	                    courses = loadedCourses;
	                    return true; // Successful deserialization
	                }
	            }
	        } catch (FileNotFoundException e) {
	            // Serialized file doesn't exist yet
	        } catch (IOException | ClassNotFoundException e) {
	            System.err.println("Error during deserialization: " + e.getMessage());
	        }
	        return false; // Failed to deserialize
	    }
	    
	    private static void loadCourseFromFile(String fileName) {
	        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
	            bufferedReader.readLine(); // Skip header
	            String line;

	            while ((line = bufferedReader.readLine()) != null) {
	                String[] courseData = line.split(",");
	                String coursename = courseData[0];
	                String courseid = courseData[1];
	                int maxcoursestudents = Integer.parseInt(courseData[2]);
	                int curentcoursestudents = Integer.parseInt(courseData[3]);
	                String[] coursestudentnames = courseData[4].split(",");
	                String instructorN = courseData[5];
	                int sectionnum = Integer.parseInt(courseData[6]);
	                String courseloc = courseData[7];

	                courses course = new courses(coursename, courseid, maxcoursestudents, curentcoursestudents, coursestudentnames, instructorN, sectionnum, courseloc);
	                courses.add(course);
	            }
	        } catch (IOException ex) {
	            System.err.println("Error loading courses from file: " + ex.getMessage());
	        }
	    }

	private static void adminMenu(Scanner scanner) {
        System.out.println("Enter Admin Username:");
        String username = scanner.nextLine();
        System.out.println("Enter Admin Password:");
        String password = scanner.nextLine();

        if (username.equals(admin.getUsername()) && password.equals(admin.getPassword())) {
            while (true) {
                System.out.println("Admin Menu:");
                System.out.println("Courses Management:");
                System.out.println("1. Create a new course");
                System.out.println("2. Delete a course");
                System.out.println("3. Edit a course");
                System.out.println("4. Display information for a course");
                System.out.println("5. Register a student");
                System.out.println("Reports:");
                System.out.println("6. View all courses");
                System.out.println("7. View full courses");
                System.out.println("8. View students in a course");
                System.out.println("9. View courses of a student");
                System.out.println("10. Sort courses by current students");
                System.out.println("11. Write full courses to a file");
                System.out.println("12. Exit");

                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                    	System.out.print("Enter a name for your course: ");
                        String coursename = scanner.nextLine();
                        System.out.print("Enter course ID to for your course: ");
                        String courseid = scanner.nextLine();
                        System.out.print("Enter max number of students in this course: ");
                        int maxcoursestudents = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Enter Instructor name: ");
                        String instructorN = scanner.nextLine();
                        System.out.print("Enter course section number:");
                        int sectionnum = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Enter course location: ");
                        String courseloc = scanner.nextLine();
                        
                        admin.createCourse(coursename, courseid, maxcoursestudents, instructorN, sectionnum, courseloc);
                        break;
                    case 2:
                        System.out.print("Enter course ID to delete: ");
                        String courseIdToDelete = scanner.nextLine();
                        System.out.print("Enter the section number: ");
                        int Sectionnum = scanner.nextInt();
                        scanner.nextLine();
                        
                        admin.deleteCourse(courseIdToDelete,Sectionnum);
                        break;
                    case 3:
                        System.out.print("Enter course ID to edit: ");
                        String courseIdToEdit = scanner.nextLine();
                        System.out.print("Enter section number: ");
                        int SectionNum = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("New instructor name: ");
                        String newInstructor = scanner.nextLine();
                        System.out.print("New number of max students: ");
                        int newMaxStudents = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("New location: ");
                        String newLocation = scanner.nextLine();
        
                        admin.editCourse(courseIdToEdit,SectionNum, newInstructor,newMaxStudents,newLocation);
                        break;
                    case 4:
                        System.out.print("Enter course ID to display: ");
                        String courseIdToDisplay = scanner.nextLine();
                        System.out.print("Enter section number: ");
                        int SectionNumb = scanner.nextInt();
                        scanner.nextLine();
                        
                        admin.displayCourse(courseIdToDisplay,SectionNumb);
                        break;
                    case 5:
                        System.out.print("Enter student name to register: ");
                        String studentName = scanner.nextLine();
                        System.out.print("Enter course ID of the class you want to register: ");
                        String courseID = scanner.nextLine();
                        System.out.print("Enter section number: ");
                        int sectionNum = scanner.nextInt();
                        scanner.nextLine();
                        admin.registerStudent(studentName, courseID,sectionNum); 
                        break;
                    case 6:
                        admin.viewAllCourses();
                        break;
                    case 7:
                        admin.viewFullCourses();
                        break;
                    case 8:
                        System.out.print("Enter course ID to view students: ");
                        String courseIdForStudents = scanner.nextLine();
                        System.out.print("Enter the section");
                        int secnum = scanner.nextInt();
                        scanner.nextLine();

                        
                        admin.viewStudentsInCourse(courseIdForStudents,secnum);
                        break;
                    case 9:
                        System.out.print("Enter student name to view their courses: ");
                        String studentNameForCourses = scanner.nextLine();
                        
                        admin.viewCoursesOfStudent(studentNameForCourses);
                        break;
                    case 10:
                        admin.sortCoursesByCurrentStudents();
                        break;
                    case 11:
                    	admin.writeFullCoursesToFile();
                    	break;
                    case 12:
                        return;
                    default:
                        System.out.println("Invalid choice, please try again.");
                }
            }
        } else {
            System.out.println("Invalid credentials.");
        }
    }

	private static void studentMenu(Scanner scanner) {
	    Student student = new Student("SUser", "SPassword", "Kevin", "Kim");

	    System.out.println("Enter Student Username:");
	    String username = scanner.nextLine();
	    System.out.println("Enter Student Password:");
	    String password = scanner.nextLine();

	    // Validate credentials
	    if (username.equals(student.getUsername()) && password.equals(student.getPassword())) {
	        while (true) {
	            System.out.println("Student Menu:");
	            System.out.println("1. View all courses");
	            System.out.println("2. View available courses");
	            System.out.println("3. Register for a course");
	            System.out.println("4. Withdraw from a course");
	            System.out.println("5. View courses enrolled");
	            System.out.println("6. Exit");

	            int choice = Integer.parseInt(scanner.nextLine());
	            switch (choice) {
	                case 1:
	                    student.viewAllCourses(courses);
	                    break;
	                case 2:
	                    student.viewAvailableCourses(courses);
	                    break;
	                case 3:
	                    System.out.print("Enter course ID to register: ");
	                    String courseId = scanner.nextLine();
	                    System.out.print("Enter section number: ");
	                    int courseNumb = scanner.nextInt();
                        scanner.nextLine();
	                    student.registerInCourse(courseId, courseNumb, courses);
	                    break;
	                case 4:
	                    System.out.print("Enter course ID to withdraw: ");
	                    String withdrawCourseId = scanner.nextLine();
	                    System.out.print("Enter section number: ");
	                    int courseNumber = scanner.nextInt();
                        scanner.nextLine();
	                    student.withdrawFromCourse(withdrawCourseId, courseNumber);
	                    break;
	                case 5:
	                    student.viewRegisteredCourses();
	                    break;
	                case 6:
	                    return;
	                default:
	                    System.out.println("Invalid choice, please try again.");
	            }
	        }
	    } else {
	        System.out.println("Invalid credentials.");
	    }
	}
	}

