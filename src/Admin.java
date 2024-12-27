import java.io.*; /*saving space for the compiler */
import java.util.*;

public class Admin extends user implements AdminInterface,java.io.Serializable{
    private ArrayList<courses> courseList;

    public Admin(String username, String password, String fname, String lname, ArrayList<courses> courses) {
        super(username, password, fname, lname);
        this.courseList = courses;
    }

    public void createCourse(String coursename, String courseid, int maxcoursestudents, String instructorN, int sectionnum, String courseloc) {
        courses newCourse = new courses(coursename, courseid, maxcoursestudents, 0, new String[maxcoursestudents], instructorN, sectionnum, courseloc);
        courseList.add(newCourse);
        System.out.println("Course " + coursename + " has been created.");
    }
    
    public void createCourse(String courseName, String courseId, int maxStudents) {
        courses course = new courses(courseName, courseId, maxStudents);
        courseList.add(course);
        System.out.println("Course created with basic details.");
    }

    public void deleteCourse(String courseId, int sectionnum) {
        Iterator<courses> iterator = courseList.iterator();
        while (iterator.hasNext()) {
            courses course = iterator.next();
            if (course.getCourseid().equalsIgnoreCase(courseId) && course.getSectionnum() == sectionnum) {
                iterator.remove();
                System.out.println("Course with ID " + courseId + " and Section " + sectionnum + " has been deleted.");
                return;
            }
        }
        System.out.println("Course not found.");
    }

    public void editCourse(String courseId, int sectionnum, String newInstructor, int newMaxStudents, String newLocation) {
        for (int i = 0; i < courseList.size(); i++) {
            courses course = courseList.get(i);
            if (course.getCourseid().equalsIgnoreCase(courseId) && course.getSectionnum() == sectionnum) {
                course.setInstructorN(newInstructor);
                course.setMaxcoursestudents(newMaxStudents);
                course.setCourseloc(newLocation);
                System.out.println("Course " + courseId + " (Section " + sectionnum + ") has been updated.");
                return;
            }
        }
        System.out.println("Course not found.");
    }

    @Override
    public void displayCourse(String courseid, int sectionnum) {
        for (int i = 0; i < courseList.size(); i++) {
            courses course = courseList.get(i);
            if (course.getCourseid().equalsIgnoreCase(courseid) && course.getSectionnum() == sectionnum) {
                System.out.println("Course Name: " + course.getCoursename());
                System.out.println("Instructor: " + course.getInstructorN());
                System.out.println("Max Students: " + course.getMaxcoursestudents());
                System.out.println("Current Students: " + course.getCurrentcoursestudents());
                System.out.println("Location: " + course.getCourseloc());
                return;
            }
        }
        System.out.println("Course not found.");
    }

    public void registerStudent(String studentName, String courseId, int sectionnum) {
        for (int i = 0; i < courseList.size(); i++) {
            courses course = courseList.get(i);
            if (course.getCourseid().equalsIgnoreCase(courseId) && course.getSectionnum() == sectionnum) {
                if (!course.isFull()) {
                    if (course.addStudent(studentName)) {
                        System.out.println("Student " + studentName + " has been registered in " + course.getCoursename() + " (Section " + sectionnum + ")");
                    } else {
                        System.out.println("Student registration failed.");
                    }
                } else {
                    System.out.println("Course is full.");
                }
                return;
            }
        }
        System.out.println("Course not found.");
    }

    public void viewAllCourses() {
        System.out.println("All Courses:");
        for (int i = 0; i < courseList.size(); i++) {
            courses course = courseList.get(i);
            System.out.println(course.getCoursename() + " (" + course.getCourseid() + ")");
        }
    }

    public void viewFullCourses() {
        System.out.println("Full Courses:");
        int fullCourseCount = 0; 

        for (int i = 0; i < courseList.size(); i++) {
            courses course = courseList.get(i);
            if (course.getCurrentcoursestudents() == course.getMaxcoursestudents()) {
                System.out.println(course.getCoursename() + " (" + course.getCourseid() + ") is full.");
                fullCourseCount++; 
            }
        }

        if (fullCourseCount == 0) {
            System.out.println("There are no full courses at this time.");
        }
    }


    public void writeFullCoursesToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("FullCourses.txt"))) {
            for (int i = 0; i < courseList.size(); i++) {
                courses course = courseList.get(i);
                if (course.isFull()) {
                	writer.write(course.getCoursename() + " (" + course.getCourseid() + ", Section " + course.getSectionnum() + ")");
                    writer.newLine();
                }
            }
            System.out.println("Full courses have been written to FullCourses.txt");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }

    public void viewStudentsInCourse(String courseId,int sectionnum) {
    	for (int i = 0; i < courseList.size(); i++) {
            courses course = courseList.get(i);
            if (course.getCourseid().equalsIgnoreCase(courseId) && course.getSectionnum() == sectionnum) {
                System.out.println("Students in " + course.getCoursename() + " (Section " + sectionnum + "):");

                boolean hasStudents = false; 

                for (int j = 0; j < course.getCoursestudentnames().length; j++) {
                    String student = course.getCoursestudentnames()[j];
                    if (student != null) {
                        System.out.println(student);
                        hasStudents = true; 
                    }
                }

                
                if (!hasStudents) {
                    System.out.println("No students are enrolled in this course.");
                }

                return; 
            }
        }

        System.out.println("Course not found.");
    }

    public void viewCoursesOfStudent(String studentName) {
        System.out.println("Courses for student " + studentName + ":");
        for (int i = 0; i < courseList.size(); i++) {
            courses course = courseList.get(i);
            for (int j = 0; j < course.getCoursestudentnames().length; j++) {
                String student = course.getCoursestudentnames()[j];
                if (studentName.equals(student)) {
                    System.out.println(course.getCoursename() + " (" + course.getCourseid() + ")");
                }
            }
        }
    }

    public void sortCoursesByCurrentStudents() {
        Collections.sort(courseList, (course1, course2) -> Integer.compare(course2.getCurrentcoursestudents(), course1.getCurrentcoursestudents()));
        System.out.println("Courses sorted by current number of students:");
        for (int i = 0; i < courseList.size(); i++) {
            courses course = courseList.get(i);
            System.out.println(course.getCoursename() + " (" + course.getCourseid() + ") - " + course.getCurrentcoursestudents() + " students");
        }
    }
}
