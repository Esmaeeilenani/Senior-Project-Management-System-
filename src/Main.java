

import java.io.*;
import java.util.Scanner;

public class Main {

    /*
    
    
    Esmaeeil enani
    
     */
    public static void main(String[] args) throws FileNotFoundException {
        new Main().Run();

    }

    public void Run() throws FileNotFoundException {

        Scanner in = null;

        //Senior Project List
        SeniorProjectSystem SPList = new SeniorProjectSystem();

        //Student File to insert in the Linked List
        File SystemFiles = new File("student.txt");
        FilesReader(SystemFiles, in, SPList);

        //Supervisor File to insert in the Linked List
        SystemFiles = new File("supervisor.txt");
        FilesReader(SystemFiles, in, SPList);

        MenueActivity(in, SPList);

    }

    //Read both files
    public void FilesReader(File SystemFiles, Scanner in, SeniorProjectSystem SPList) throws FileNotFoundException {

        if (!SystemFiles.exists()) {
            System.out.println("Sorry can't Read the File \n\tFile not exists ");
            System.exit(0);
        }
        in = new Scanner(SystemFiles);

        while (in.hasNext()) {

            if (SystemFiles.getName().equals("supervisor.txt")) {

                SPList.InsertSupervisor(new supervisor(in.next().trim().replaceAll(",", ""), in.next() + in.next().trim().replaceAll(",", ""), in.nextLine().split(",|#")));

            } else if (SystemFiles.getName().equals("student.txt")) {

                String Tokens[] = in.nextLine().trim().split(",|#");

                SPList.InsertStudent(new student(Tokens[0].trim(), Tokens[1].trim(), Tokens[2].trim(), Tokens[3].trim(), Tokens, Boolean.parseBoolean(Tokens[10].trim()), Tokens[11].trim()));
            }

        }

    }

    //Menu List
    public void Menu() {
        System.out.println("1. Add a new student."
                + "\n2. Print supervisor list."
                + "\n3. Print student list."
                + "\n4. Add research topic. "
                + "\n5. Remove student. "
                + "\n6. Print senior project list in ascending order "
                + "\n7. Exit. ");
    }

    //Read From User input
    public void MenueActivity(Scanner in, SeniorProjectSystem SPList) throws FileNotFoundException {

        in = new Scanner(System.in);
        PrintWriter out = new PrintWriter("output.txt");
        while (true) {

            Menu();
            String userInput = in.next();
            CheckUserActivity(userInput, SPList, in, out);
        }

    }

    //Check the input From User
    public void CheckUserActivity(String userInput, SeniorProjectSystem SPList, Scanner in, PrintWriter out) {

        switch (userInput) {
            case "1":
                ConsoleAdd(in, SPList, out);
                break;
            case "2":
                SuperVisorFormat(out);
                SPList.PrintSuperVisor(out);

                break;
            case "3":
                StudentFormat(out);
                SPList.PrintStudent(out);

                break;
            case "4":
                AssignTopic(in, SPList);
                break;
            case "5":
                deleteStudent(in, SPList, out);
                break;
            case "6":
                SPList.RegisterStudent(out);
                break;
            case "7":
                in.close();
                out.close();
                System.exit(0);
                break;
            default:
                System.out.println("\nWrong input please try Again");
                break;
        }
        System.out.println("");

    }

    //Adding Student from Console  
    public void ConsoleAdd(Scanner in, SeniorProjectSystem SPList, PrintWriter out) {
        System.out.println("Please enter the student information:\n");

        System.out.print("student ID:");
        String ID = in.next().trim();
        student std = SPList.FindStudent(ID);

        //null mean the Student not in the LinkedList
        if (std == null) {

            System.out.print("Student Name: ");
            String Name = in.next();

            System.out.print("research interest:");
            in.nextLine();
            String research = in.nextLine().trim();

            System.out.print(" courses:");
            String[] Courses = in.nextLine().split(",");

            System.out.println("The student is added!\n");
            out.println("The student is added!\n");

            std = new student(ID, Name, research, Courses);

            //get the superVisor with same intrest
            supervisor SP = SPList.FindSpervisor(std.getResearch_intrest());

            //if the student complete the requirement and thir is supervisor with same intrest
            std.setApproval((SP != null) && SPList.check_Course(std.getCourse()));

            //get SuperVisor ID
            std.setSupervisorID((std.isApproval()) ? SP.getSupervisorID() : "0");

            //Insert to LinkedList
            SPList.InsertStudent(std);

        } else {
            System.out.println("this Student with ID (" + ID + ") already exists\n");
        }

    }

    //Assign topic to Student
    public void AssignTopic(Scanner in, SeniorProjectSystem SPList) {
        System.out.print("\nPlease enter the student ID: ");
        String ID = in.next();

        student Stu = SPList.FindStudent(ID);

        if (Stu != null) {

            if (Stu.isApproval()) {
                System.out.print("\nThe student studied all required subject, and please enter the \n"
                        + "topic: ");
                in.nextLine();
                Stu.setTopic(in.nextLine());

            } else {

                System.out.println("\nThe student has not studied all required subject,\n");
            }

        } else {
            System.out.println("\nThis Student with ID (" + ID + ") doesn't exists\n");
        }

    }

    //Remove Student by ID or not Aproval  
    public void deleteStudent(Scanner in, SeniorProjectSystem SPList, PrintWriter out) {
        System.out.println("");
        StudentFormat(out);
        SPList.PrintStudent(out);

        System.out.println("\n Please select your option:");
        System.out.println("1- Remove student by his/her ID "
                + "\n2- Remove students that have not to complete the minimum requirement. ");
        int userInput = in.nextInt();

        switch (userInput) {
            case 1:

                System.out.print("\nPlease enter the student ID that you would like to remove: ");
                String ID = in.next();
                student RemovedStu = SPList.Remove(ID);
                if (RemovedStu != null) {

                    StudentFormat(out);

                    System.out.println("\n" + RemovedStu.toString());

                    out.println("\n" + RemovedStu.toString() + "\n");

                } else {
                    System.out.println("\nThis Student with ID (" + ID + ") doesn't exists");
                }
                break;

            case 2:

                SPList.Remove(out);
                out.println("");
                break;

            default:
                System.out.println("Wrong input please try Again");
                break;
        }

    }

    //Required Fromat for Student      
    public void StudentFormat(PrintWriter out) {
        System.out.printf("%-17s%-30s%-50s%-60s%-15s%s%n", "Student ID", "Research interests",
                "Suggested topic", "courses", "Approval", "SupervisorID ");
        System.out.printf("%100s%10s%10s%10s%10s%10s%n", "CPCS223", "CPCS343", "CPCS351", "CPCS241", "CPCS361", "CPCS331");

        out.printf("%-17s%-30s%-50s%-60s%-15s%s%n", "Student ID", "Research interests",
                "Suggested topic", "courses", "Approval", "SupervisorID ");
        out.printf("%100s%10s%10s%10s%10s%10s%n", "CPCS223", "CPCS343", "CPCS351", "CPCS241", "CPCS361", "CPCS331");
    }

    //Required Fromat for SuperVisor
    public void SuperVisorFormat(PrintWriter out) {
        System.out.printf("%-18s %-22s  %s\n\n", "Supervisor ID", "Name", "Research interests");

        out.printf("%-18s %-22s  %s\n\n", "Supervisor ID", "Name", "Research interests");

    }
}
