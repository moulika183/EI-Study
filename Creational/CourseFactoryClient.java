interface CourseFactory {
    Course createCourse();
    CourseSpecification createSpecification();
}

class NorthAmericaCourseFactory implements CourseFactory {
    public Course createCourse() {
        return new DataScienceCourse();
    }

    public CourseSpecification createSpecification() {
        return new NorthAmericaSpecification();
    }
}

class EuropeCourseFactory implements CourseFactory {
    public Course createCourse() {
        return new ArtificialIntelligenceCourse();
    }

    public CourseSpecification createSpecification() {
        return new EuropeSpecification();
    }
}

interface Course {
    void develop();
}

interface CourseSpecification {
    void display();
}

class DataScienceCourse implements Course {
    public void develop() {
        System.out.println("Developing Data Science course.");
    }
}

class ArtificialIntelligenceCourse implements Course {
    public void develop() {
        System.out.println("Developing Artificial Intelligence course.");
    }
}

class NorthAmericaSpecification implements CourseSpecification {
    public void display() {
        System.out.println("North America Course Specification: Follows guidelines for remote learning and accreditation.");
    }
}

class EuropeSpecification implements CourseSpecification {
    public void display() {
        System.out.println("Europe Course Specification: Complies with GDPR and sustainability education standards.");
    }
}

public class CourseFactoryClient {
    public static void main(String[] args) {
        CourseFactory northAmericaFactory = new NorthAmericaCourseFactory();
        Course northAmericaCourse = northAmericaFactory.createCourse();
        CourseSpecification northAmericaSpec = northAmericaFactory.createSpecification();

        northAmericaCourse.develop();
        northAmericaSpec.display();

        CourseFactory europeFactory = new EuropeCourseFactory();
        Course europeCourse = europeFactory.createCourse();
        CourseSpecification europeSpec = europeFactory.createSpecification();

        europeCourse.develop();
        europeSpec.display();
    }
}
