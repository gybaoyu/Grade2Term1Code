package ink.abalone.week16.T2;

import ink.abalone.week16.T1.Item;

import java.util.*;

/*
test input:
A
*/

public class GradeSystemTest{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GradeSystem gs = new GradeSystem(sc);
        gs.defaultInit();
        System.out.println("""
                        commands:
                        A: addStudent
                        D: deleteStudent
                        C: changeStudent
                        S: selectStudent
                        P: printStudentsSortedByGrade
                        SA: changeAttendance
                        PA: printAttendance
                        AP: attendancePP
                        Exit: exitSystem
                        """);
        label:
        while(sc.hasNext()){
            String cmd = sc.next();
            switch (cmd) {
                case "A":
                    gs.addStudent();
                    break;
                case "D":
                    gs.deleteStudentByName();
                    break;
                case "C":
                    gs.changeGrade();
                    break;
                case "S":
                    gs.selectStudentByName();
                    break;
                case "P":
                    gs.printStudentSortedByGrade();
                    break;
                case "SA":
                    gs.setAttendance();
                    break;
                case "PA":
                    gs.printAttendance();
                    break;
                case "AP":
                    gs.attendancePP();
                    break;
                case "Exit":
                    break label;
                default:
                    System.out.println("Invalid command");
                    break;
            }
        }
    }
}

class GradeSystem{
    private LinkedList<Student> students;
    private TreeSet<Student> sortedStudents;
    private HashMap<String,Integer> attendance;
    private Scanner sc;

    public GradeSystem(Scanner sc){
        students = new LinkedList<>();
        sortedStudents = new TreeSet<>(Comparator.comparing(Student::getGrade));
        attendance = new HashMap<>();
        this.sc = sc;
    }

    public void defaultInit(){
        students = new LinkedList<>();
        sortedStudents = new TreeSet<>(Comparator.comparing(Student::getGrade));
        attendance = new HashMap<>();
        students.add(new Student("AAA",90));
        students.add(new Student("BBB",70));
        students.add(new Student("CCC",60));
        students.add(new Student("DDD",80));
        students.add(new Student("EEE",100));
        sortedStudents.addAll(students);
        for (Student student : students) {
            attendance.put(student.getName(), 0);
        }
    }

    public void addStudent(){
        System.out.println("Enter the name of the student: ");
        String name = sc.next();
        System.out.println("Enter the grade of the student: ");
        int grade = sc.nextInt();
        Student student = new Student(name, grade);
        students.add(student);
        System.out.println("added "+student);
        attendance.put(student.getName(), 0);
    }
    public void deleteStudentByName(){
        System.out.println("Enter the name of the student: ");
        String name = sc.next();
        ListIterator<Student> iterator = students.listIterator();
        while(iterator.hasNext()){
            Student student = iterator.next();
            if (student.getName().equals(name)){
                iterator.remove();
                System.out.println("deleted: " + student);
                break;
            }
        }
        attendance.remove(name);
    }
    void changeGrade(){
        System.out.println("Enter the name of the student: ");
        String name = sc.next();
        System.out.println("Enter the grade of the student: ");
        int grade = sc.nextInt();
        Student student = new Student(name, grade);
        ListIterator<Student> iterator = students.listIterator();
        while(iterator.hasNext()){
            Student student1 = iterator.next();
            if (name.equals(student1.getName())){
                System.out.println("before: "+student1+"\nafter: "+student);
                iterator.set(student);
                break;
            }
        }
    }
    void selectStudentByName(){
        System.out.println("Enter the name of the student: ");
        String name = sc.next();
        for (Student student : students) {
            if (student.getName().equals(name)) {
                System.out.println(student.getName() + "'s grade is " + student.getGrade());
                break;
            }
        }
    }
    public void printStudentSortedByGrade(){
        sortedStudents.addAll(students);
        for (Student student : sortedStudents) {
            System.out.println(student);
        }
    }
    public void attendancePP(){
        System.out.println("Enter the name of the student: ");
        String name = sc.next();
        attendance.put(name,attendance.get(name)+1);
    }
    public void setAttendance(){
        System.out.println("Enter the name of the student: ");
        String name = sc.next();
        System.out.println("Enter times of the attendance: ");
        int times = sc.nextInt();
        attendance.put(name,times);
    }
    public void printAttendance(){
        attendance.forEach((k,v)-> System.out.println("Student: "+k+" Attendance: "+v));
    }
}

class Student {
    private String name;
    private int grade;

    public Student(String name, int grade){
        this.name = name;
        this.grade = grade;
    }

    public int getGrade() {
        return grade;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", grade=" + grade +
                '}';
    }
}
