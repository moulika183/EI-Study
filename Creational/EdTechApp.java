import java.util.ArrayList;
import java.util.List;


interface Observer {
    void update(String courseUpdate);
}


interface Subject {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}


class LearningPlatform implements Subject {
    private List<Observer> observers = new ArrayList<>();
    private String courseUpdate;

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(courseUpdate);
        }
    }

    public void uploadNewCourseMaterial(String newMaterial) {
        this.courseUpdate = "New Course Material Uploaded: " + newMaterial;
        notifyObservers();
    }
}

class StudentDashboard implements Observer {
    private String courseUpdate;

    @Override
    public void update(String courseUpdate) {
        this.courseUpdate = courseUpdate;
        display();
    }

    private void display() {
        System.out.println("Student Dashboard: " + courseUpdate);
    }
}


class TeacherDashboard implements Observer {
    private String courseUpdate;

    @Override
    public void update(String courseUpdate) {
        this.courseUpdate = courseUpdate;
        display();
    }

    private void display() {
        System.out.println("Teacher Dashboard: " + courseUpdate);
    }
}

public class EdTechApp {
    public static void main(String[] args) {
        LearningPlatform learningPlatform = new LearningPlatform();

        Observer studentDashboard = new StudentDashboard();
        Observer teacherDashboard = new TeacherDashboard();

        learningPlatform.addObserver(studentDashboard);
        learningPlatform.addObserver(teacherDashboard);

        learningPlatform.uploadNewCourseMaterial("Chapter 1: Introduction to Data Science");

    }
}
