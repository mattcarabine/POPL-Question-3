import java.util.ArrayList;
import java.util.Random;

public class Student implements Runnable {
    private ArrayList<Project> preferences = new ArrayList<Project>();
    public int ID;
    public Project project;

    public Student(int ID){
        this.ID = ID;
        project = null;
    }

    public void generatePreferences(ArrayList<Project> projects){
        int max = projects.size();
        while (preferences.size()<max){
            int random = new Random().nextInt(max);
            while (preferences.contains(projects.get(random))){
                random = new Random().nextInt(max);
            }
            preferences.add(projects.get(random));
        }
    }

    public void run(){
        if (project==null){
            preferences.get(0).propose(this);
            preferences.remove(0);
        }
    }

}
