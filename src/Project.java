import java.util.ArrayList;
import java.util.Random;


public class Project implements Runnable {
    private ArrayList<Student> preferences = new ArrayList<Student>();
    private ArrayList<Student> proposers = new ArrayList<Student>();
    public int ID;
    public Student student;

    public Project(int ID){
        this.ID = ID;
        this.student = null;
    }

    public void run(){
       printProposers();
        String s = "";
        if (this.student!=null) {
           s=s.concat("Project " + ID + ": previous student was " + student.ID);
            this.student.project = null;
        } else{
            s=s.concat("Project " + ID + ": No previous student");
        }

        for (Student student:proposers){
            student.project = null;
        }

        Student newStudent = findHighestPref();

        if (newStudent!=null) {
            newStudent.project = this;
        }

        this.student = newStudent;

        if (student!=null) {
           s=s.concat(", Current student is " + student.ID);
        } else{
           s=s.concat(", No current student");
        }
        System.out.println(s);
        proposers = new ArrayList<Student>();
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
        for (Student studentPref:preferences){
            if (this.student!=null)
                if (studentPref.ID==this.student.ID)
                    return studentPref;
           for (Student studentProp:proposers) {
                   if (studentPref.ID == studentProp.ID){
                       return studentPref;
                   }
            }
        }
        return null;
    }

    private void printProposers(){
        String s = "Project " + ID + ":proposers ";
        for (Student student:proposers){
           s = s.concat(student.ID+ ",");
        }
        System.out.println(s);
    }

}
