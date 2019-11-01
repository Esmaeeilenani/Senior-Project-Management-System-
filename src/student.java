

public class student {

    private String studentID;
    private String studentName;
    private String research_intrest;
    private String Topic;
    private int course[];
    private boolean approval;
    private String supervisorID;
    private student next;

    // Use it when Adding from file
    public student(String studentID, String studentName, String research_intrest, String Topic, String[] Courses, boolean approval, String supervisorID) {
        this.studentID = studentID;
        this.studentName = studentName;
        this.research_intrest = research_intrest;
        this.Topic = Topic.equals("nan") ? "" : Topic;
        this.approval = approval;
        this.supervisorID = supervisorID;
        course = new int[6];
        AssignCourseFile(Courses);
        this.next = null;

    }

    //Adding Student From Console
    public student(String studentID, String studentName, String research_intrest, String[] Courses) {
        this.studentID = studentID;
        this.studentName = studentName;
        this.research_intrest = research_intrest;
        Topic = "";
        course = new int[6];
        AssignCourse(Courses);
        this.next = null;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getResearch_intrest() {
        return research_intrest;
    }

    public void setResearch_intrest(String research_intrest) {
        this.research_intrest = research_intrest;
    }

    public String getTopic() {
        return Topic;
    }

    public void setTopic(String Topic) {
        this.Topic = Topic;
    }

    public int[] getCourse() {
        return course;
    }

    public void setCourse(int[] course) {
        this.course = course;
    }

    public boolean isApproval() {
        return approval;
    }

    public void setApproval(boolean approval) {
        this.approval = approval;
    }

    public String getSupervisorID() {
        return supervisorID;
    }

    public void setSupervisorID(String supervisorID) {
        this.supervisorID = supervisorID;
    }

    public student getNext() {
        return next;
    }

    public void setNext(student next) {
        this.next = next;
    }

    //Adding requirement From file
    private void AssignCourseFile(String[] Courses) {

        for (int i = 0; i < course.length; i++) {

            this.course[i] = Integer.parseInt(Courses[i + 4].trim());
        }
    }

    //Adding requirement From Console
    private void AssignCourse(String[] Courses) {

        for (int i = 0; i < Courses.length; i++) {
            course[i] = 1;
        }

    }

    @Override
    public String toString() {
        String INFO = "";

        for (int i = 0; i < course.length; i++) {
            INFO += String.format("%-10d", course[i]);
        }

        return String.format("%-15s %-30s %-50s %-20s %-15s %s", this.studentID, this.research_intrest, this.Topic, INFO, approval, supervisorID);
    }

}
