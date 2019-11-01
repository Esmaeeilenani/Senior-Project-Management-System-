

import java.io.PrintWriter;

public class SeniorProjectSystem {

    private student head;
    private supervisor head2;

    public SeniorProjectSystem() {
        head = null;
        head2 = null;
    }

    public boolean StuisEmpty() {
        return head == null;
    }

    public boolean SupisEmpty() {
        return head2 == null;
    }

    public void InsertStudent(student stu) {

        if (head != null) {
            student hpt = head;
            while (hpt.getNext() != null) {

                hpt = hpt.getNext();
            }
            hpt.setNext(stu);
            return;
        }
        head = stu;

    }

    public void InsertSupervisor(supervisor SuVisor) {

        if (head2 != null) {
            supervisor hpt = head2;
            while (hpt.getNext() != null) {
                hpt = hpt.getNext();
            }
            hpt.setNext(SuVisor);
            return;
        }
        head2 = SuVisor;

    }

    public boolean check_Course(int courses[]) {
        for (int i = 0; i < courses.length; i++) {
            if (courses[i] != 1) {
                return false;
            }
        }

        return true;
    }

    public student FindStudent(String ID) {
        student hpt = head;

        while (hpt != null) {
            if (hpt.getStudentID().equals(ID)) {
                return hpt;
            }
            hpt = hpt.getNext();
        }

        return null;
    }

    public supervisor FindSpervisor(String researchInterest) {

        supervisor hpt = head2;
        while (hpt != null) {

            for (int i = 0; i < hpt.getIntrest().length; i++) {
                if (hpt.getIntrest()[i].trim().equals(researchInterest.trim())) {
                    return hpt;
                }
            }
            hpt = hpt.getNext();
        }
        return null;
    }

    //Remove by Student ID
    public student Remove(String StudentID) {

        student stu = null;
        
        if (head.getStudentID().equals(StudentID)) {
            stu = head;
            head = head.getNext();
            return stu;
        }
        
        student hpt = head;
        while (hpt.getNext() != null) {

            if (hpt.getNext().getStudentID().equals(StudentID)) {
                stu = hpt.getNext();
                hpt.setNext(hpt.getNext().getNext());
                break;
            }
            hpt = hpt.getNext();
        }

        return stu;
    }
    
    //Remove not Aproval
    public void Remove(PrintWriter output) {
        student hpt = head;

        if (!head.isApproval()) {
            System.out.println(head.toString());
            output.println(head.toString());
            head = head.getNext();

        }

        while (hpt != null && hpt.getNext() != null) {

            if (!hpt.getNext().isApproval()) {
                System.out.println(hpt.getNext().toString());
                output.println(hpt.getNext().toString());
                hpt.setNext(hpt.getNext().getNext());
            }

            hpt = hpt.getNext();
        }

    }

    public void PrintStudent(PrintWriter out) {
        student hpt = head;
        while (hpt != null) {

            System.out.println(hpt.toString());
            out.println(hpt.toString());
            hpt = hpt.getNext();
        }
    }

    public void PrintSuperVisor(PrintWriter out) {
        supervisor hpt = head2;
        while (hpt != null) {
            System.out.println(hpt.toString());
            out.println(hpt.toString());
            hpt = hpt.getNext();
        }
    }

    public void RegisterStudent(PrintWriter output) {

        student hpt = head;
        while (hpt != null) {

            if (hpt.isApproval()) {
                System.out.println(hpt.toString());
                output.println(hpt.toString());
            }

            hpt = hpt.getNext();
        }
    }

}
