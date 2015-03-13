import java.util.ArrayList;
import java.util.Random;


public class Project implements Runnable {
    ArrayList<Student> preferences = new ArrayList<Student>();
    ArrayList<Student> proposers = new ArrayList<Student>();
    public int ID;
    public Student student;

    public Project(int ID){
        this.ID = ID;
    }

    public void run(){
        if (this.student!=null) {
            System.out.println("Project " + ID + ": previous student was " + student.ID);
            this.student.project = null;
        } else{
            System.out.println("Project " + ID + ": No previous student");
        }
        for (Student student:proposers){
            student.project = null;
        }
        Student student = findHighestPref();
        if (student!=null) {
            student.project = this;
        }
        this.student = student;

        if (student!=null) {
            System.out.println("Project " + ID + ":current student is " + student.ID);
        } else{
            System.out.println("Project " + ID + ": No current student");
        }
        proposers.clear();
    }

    public void generatePreferences(ArrayList<Student> students){
        int max = students.size();
        while (preferences.size()<max){
            int random = new Random().nextInt(max);
            while (preferences.contains(students.get(random))){
                random = new Random().nextInt(max);
            }
            preferences.add(students.get(random));
        }
    }

    public synchronized void propose(Student student){
        proposers.add(student);
    }

    private Student findHighestPref(){
        for (Student student:preferences){
            if (proposers.contains(student)||student.equals(this.student)){
                return student;
            }
        }
        return null;
    }
}
