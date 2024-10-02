interface ContentElement {
    int accept(EdTechVisitor visitor);
}

class Course implements ContentElement {
    private int pricePerLesson;
    private int numLessons;
    private String courseName;

    public Course(int pricePerLesson, int numLessons, String courseName) {
        this.pricePerLesson = pricePerLesson;
        this.numLessons = numLessons;
        this.courseName = courseName;
    }

    public int getPricePerLesson() {
        return pricePerLesson;
    }

    public int getNumLessons() {
        return numLessons;
    }

    public String getCourseName() {
        return courseName;
    }

    @Override
    public int accept(EdTechVisitor visitor) {
        return visitor.visit(this);
    }
}

class Certificate implements ContentElement {
    private int programLength;  
    private int baseCost;
    private String certificateName;

    public Certificate(int programLength, int baseCost, String certificateName) {
        this.programLength = programLength;
        this.baseCost = baseCost;
        this.certificateName = certificateName;
    }

    public int getProgramLength() {
        return programLength;
    }

    public int getBaseCost() {
        return baseCost;
    }

    public String getCertificateName() {
        return certificateName;
    }

    @Override
    public int accept(EdTechVisitor visitor) {
        return visitor.visit(this);
    }
}

interface EdTechVisitor {
    int visit(Course course);
    int visit(Certificate certificate);
}

class EdTechVisitorImpl implements EdTechVisitor {

    @Override
    public int visit(Course course) {
        int cost;
        if (course.getNumLessons() > 10) {
            cost = (course.getPricePerLesson() * course.getNumLessons()) - 50;
        } else {
            cost = course.getPricePerLesson() * course.getNumLessons();
        }
        System.out.println("Course: " + course.getCourseName() + " cost = $" + cost);
        return cost;
    }

    @Override
    public int visit(Certificate certificate) {
        int cost;
        if (certificate.getProgramLength() > 8) {
            cost = certificate.getBaseCost() - 100; 
        } else {
            cost = certificate.getBaseCost();
        }
        System.out.println("Certificate: " + certificate.getCertificateName() + " cost = $" + cost);
        return cost;
    }
}

class EdTechClient {

    public static void main(String[] args) {
        ContentElement[] contents = new ContentElement[]{
                new Course(20, 12, "Java Programming"),
                new Course(15, 8, "Python for Data Science"),
                new Certificate(10, 500, "Machine Learning Certificate"),
                new Certificate(6, 300, "Web Development Certificate")
        };

        int total = calculateTotalCost(contents);
        System.out.println("Total Cost = $" + total);
    }

    private static int calculateTotalCost(ContentElement[] contents) {
        EdTechVisitor visitor = new EdTechVisitorImpl();
        int totalCost = 0;
        for (ContentElement content : contents) {
            totalCost += content.accept(visitor);
        }
        return totalCost;
    }
}
