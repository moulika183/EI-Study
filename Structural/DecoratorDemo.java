// OnlineCourse.java (Component Interface)
interface OnlineCourse {
    String getDescription();
    double getCost();
}

// BasicCourse.java (Concrete Component)
class BasicCourse implements OnlineCourse {
    @Override
    public String getDescription() {
        return "Basic Online Course";
    }

    @Override
    public double getCost() {
        return 100.0;
    }
}

// CourseDecorator.java (Decorator)
abstract class CourseDecorator implements OnlineCourse {
    protected OnlineCourse decoratedCourse;

    public CourseDecorator(OnlineCourse decoratedCourse) {
        this.decoratedCourse = decoratedCourse;
    }

    @Override
    public String getDescription() {
        return decoratedCourse.getDescription();
    }

    @Override
    public double getCost() {
        return decoratedCourse.getCost();
    }
}

// QuizDecorator.java (Concrete Decorator 1)
class QuizDecorator extends CourseDecorator {
    public QuizDecorator(OnlineCourse decoratedCourse) {
        super(decoratedCourse);
    }

    @Override
    public String getDescription() {
        return decoratedCourse.getDescription() + ", with Quizzes";
    }

    @Override
    public double getCost() {
        return decoratedCourse.getCost() + 20.0;
    }
}

// CertificateDecorator.java (Concrete Decorator 2)
class CertificateDecorator extends CourseDecorator {
    public CertificateDecorator(OnlineCourse decoratedCourse) {
        super(decoratedCourse);
    }

    @Override
    public String getDescription() {
        return decoratedCourse.getDescription() + ", with Certificate of Completion";
    }

    @Override
    public double getCost() {
        return decoratedCourse.getCost() + 50.0;
    }
}

// HandsOnLabDecorator.java (Concrete Decorator 3)
class HandsOnLabDecorator extends CourseDecorator {
    public HandsOnLabDecorator(OnlineCourse decoratedCourse) {
        super(decoratedCourse);
    }

    @Override
    public String getDescription() {
        return decoratedCourse.getDescription() + ", with Hands-on Labs";
    }

    @Override
    public double getCost() {
        return decoratedCourse.getCost() + 75.0;
    }
}

// Main.java (Client Code)
public class DecoratorDemo {
    public static void main(String[] args) {
        // Basic Online Course
        OnlineCourse course = new BasicCourse();
        System.out.println("Description: " + course.getDescription());
        System.out.println("Cost: $" + course.getCost());

        // Course with Quizzes
        OnlineCourse quizCourse = new QuizDecorator(new BasicCourse());
        System.out.println("\nDescription: " + quizCourse.getDescription());
        System.out.println("Cost: $" + quizCourse.getCost());

        // Course with Quizzes and Certificate
        OnlineCourse quizCertCourse = new CertificateDecorator(new QuizDecorator(new BasicCourse()));
        System.out.println("\nDescription: " + quizCertCourse.getDescription());
        System.out.println("Cost: $" + quizCertCourse.getCost());

        // Course with Quizzes, Certificate, and Hands-on Labs
        OnlineCourse fullCourse = new HandsOnLabDecorator(new CertificateDecorator(new QuizDecorator(new BasicCourse())));
        System.out.println("\nDescription: " + fullCourse.getDescription());
        System.out.println("Cost: $" + fullCourse.getCost());
    }
}
