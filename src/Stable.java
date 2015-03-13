
import java.util.*;
public class Stable extends Thread{
    ArrayList<Student> students;
    ArrayList<Project> projects;

    public Stable() {

    }

    public static void main(String[] args) {
        (new Stable()).start();
    }

    public void run(){
        initialise();
        do {
            for (Student student : students) {
                Thread t = (new Thread(student));
                t.start();
            }

            try {
                this.sleep(5000);
            } catch (InterruptedException ex) {

            }

            for (Project project : projects) {
                Thread t = (new Thread(project));
                t.start();
            }
            try {
                this.sleep(1000);
            } catch (InterruptedException ex) {

            }
        } while (!checkFinished());
    }

    public void initialise(){
        students = new ArrayList<Student>();
        projects = new ArrayList<Project>();
        int max = 5;
        for (int i=0;i<max;i++){
            students.add(new Student(i));
            projects.add(new Project(i));
        }
        for (int i=0;i<max;i++){
            projects.get(i).generatePreferences(students);
            students.get(i).generatePreferences(projects);
        }
    }

    public boolean checkFinished(){
        for (Student student:students){
            if (student.project ==null){
                return false;
            }
        }
        return true;
    }
}