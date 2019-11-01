
import java.util.Arrays;

public class supervisor {

    private String supervisorID;
    private String supervisorName;
    private String Intrest[];
    private supervisor next;

    public supervisor(String supervisorID, String supervisorName, String[] Intrest) {
        this.supervisorID = supervisorID;
        this.supervisorName = supervisorName;
        this.Intrest = Intrest;
        next = null;
    }

    public String getSupervisorID() {
        return supervisorID;
    }

    public void setSupervisorID(String supervisorID) {
        this.supervisorID = supervisorID;
    }

    public String getSupervisorName() {
        return supervisorName;
    }

    public void setSupervisorName(String supervisorName) {
        this.supervisorName = supervisorName;
    }

    public String[] getIntrest() {
        return Intrest;
    }

    public void setIntrest(String[] Intrest) {
        this.Intrest = Intrest;
    }

    public supervisor getNext() {
        return next;
    }

    public void setNext(supervisor next) {
        this.next = next;
    }

    public String toString() {


        return String.format("%-15s %-25s %s", this.supervisorID, this.supervisorName, Arrays.toString(this.Intrest).replaceAll("[^a-zA-Z0-9]", " "));
    }
}
