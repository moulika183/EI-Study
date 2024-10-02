abstract class Course {
    public abstract void printCourseType();
}

class OnlineCourse extends Course {
    public void printCourseType() {
        System.out.println("I am an online course");
    }
}

class OfflineCourse extends Course {
    public void printCourseType() {
        System.out.println("I am an offline course");
    }
}

interface CourseFactory {
    Course createCourse();
}

class OnlineCourseFactory implements CourseFactory {
    public Course createCourse() {
        return new OnlineCourse();
    }
}

class OfflineCourseFactory implements CourseFactory {
    public Course createCourse() {
        return new OfflineCourse();
    }
}

class Client {
    private Course pCourse;

    public Client(CourseFactory factory) {
        pCourse = factory.createCourse();
    }

    public Course getCourse() {
        return pCourse;
    }
}

public class EdTechPlatform {
    public static void main(String[] args) {
        CourseFactory onlineCourseFactory = new OnlineCourseFactory();
        Client onlineClient = new Client(onlineCourseFactory);
        Course onlineCourse = onlineClient.getCourse();
        onlineCourse.printCourseType();

        CourseFactory offlineCourseFactory = new OfflineCourseFactory();
        Client offlineClient = new Client(offlineCourseFactory);
        Course offlineCourse = offlineClient.getCourse();
        offlineCourse.printCourseType();
    }
}