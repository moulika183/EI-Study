interface ContentDelivery {
    void deliverContent();
}

class LegacyTextBasedCourse {
    public void deliverTextCourse() {
        System.out.println("Delivering course content through text-based lessons.");
    }
}

class CourseAdapter implements ContentDelivery {
    private LegacyTextBasedCourse legacyCourse;

    public CourseAdapter(LegacyTextBasedCourse legacyCourse) {
        this.legacyCourse = legacyCourse;
    }

    @Override
    public void deliverContent() {
        legacyCourse.deliverTextCourse();
    }
}

public class EdTechAdapterDemo {
    public static void main(String[] args) {
        LegacyTextBasedCourse legacyCourse = new LegacyTextBasedCourse();
        ContentDelivery contentDelivery = new CourseAdapter(legacyCourse);

        contentDelivery.deliverContent();
    }
}
