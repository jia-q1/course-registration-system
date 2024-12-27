
public class courses implements java.io.Serializable{
	private String coursename;
	private String courseid;
	private int maxcoursestudents;
	private int currentcoursestudents;
	private String[] coursestudentnames; /* use an array*/
	private String instructorN;
	private int sectionnum;
	private String courseloc;
	
	public courses() {
		coursename="-";
		courseid="-";
		maxcoursestudents=0;
		currentcoursestudents=0;
		coursestudentnames=new String[maxcoursestudents];
		instructorN="-";
		sectionnum=0;
		courseloc="-";
	}
	
	public courses(String courseName, String courseId, int maxStudents) {
        this.coursename = courseName;
        this.courseid = courseId;
        this.maxcoursestudents = maxStudents;
        this.currentcoursestudents = 0;
        this.instructorN = "";  
        this.sectionnum = 0;         
        this.courseloc = "";       
    }
	
	public courses(String coursename, String courseid, int maxcoursestudents, int currentcoursestudents, String[] coursestudentnames, String instructorN, int sectionnum, String courseloc) {
		this.coursename=coursename;
		this.courseid=courseid;
		this.maxcoursestudents=maxcoursestudents;
		this.currentcoursestudents=currentcoursestudents;
		this.coursestudentnames=new String[maxcoursestudents];
		this.instructorN=instructorN;
		this.sectionnum= sectionnum;
		this.courseloc=courseloc;
	}

	public String getCoursename() {
		return coursename;
	}

	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}

	public String getCourseid() {
		return courseid;
	}

	public void setCourseid(String courseid) {
		this.courseid = courseid;
	}

	public int getMaxcoursestudents() {
		return maxcoursestudents;
	}

	public void setMaxcoursestudents(int maxcoursestudents) {
		this.maxcoursestudents = maxcoursestudents;
	}

	public int getCurrentcoursestudents() {
		return currentcoursestudents;
	}

	public void setCurrentcoursestudents(int currentcoursestudents) {
		this.currentcoursestudents = currentcoursestudents;
	}

	public String[] getCoursestudentnames() {
		return coursestudentnames;
	}

	public void setCoursestudentnames(String[] coursestudentnames) {
		this.coursestudentnames = coursestudentnames;
	}

	public String getInstructorN() {
		return instructorN;
	}

	public void setInstructorN(String instructorN) {
		this.instructorN = instructorN;
	}

	public int getSectionnum() {
		return sectionnum;
	}

	public void setSectionnum(int sectionnum) {
		this.sectionnum = sectionnum;
	}

	public String getCourseloc() {
		return courseloc;
	}

	public void setCourseloc(String courseloc) {
		this.courseloc = courseloc;
	}
	
	
	public boolean addStudent(String studentName) {
		if (currentcoursestudents < maxcoursestudents) {
            for (int i = 0; i < coursestudentnames.length; i++) {
                if (coursestudentnames[i] == null) {  // Find the first empty spot
                	coursestudentnames[i] = studentName;
                	currentcoursestudents++;
                    return true;  
                }
            }
        }
        return false;
    }
	
	public boolean removeStudent(String studentName) {
        for (int i = 0; i < coursestudentnames.length; i++) {
            if (coursestudentnames[i] != null && coursestudentnames[i].equals(studentName)) {
            	coursestudentnames[i] = null;  
            	currentcoursestudents--;
                return true;  
            }
        }
        return false;  
    }
	
	public boolean isFull() {
		return currentcoursestudents == maxcoursestudents;
	
	}
}
