package Program;

import javafx.beans.property.SimpleStringProperty;

public class ClassInformation {

    // Class the helps pull course information for the user to view

    private final SimpleStringProperty instructorName, courseName;

    public ClassInformation(String instructorName, String courseName){
        this.instructorName = new SimpleStringProperty(instructorName);
        this.courseName = new SimpleStringProperty(courseName);
    }

    public String getInstructorName() {

        return instructorName.get();
    }

    public String getCourseName() {

        return courseName.get();
    }

    public void setInstructorName(String instructorName) {

        this.instructorName.set(instructorName);
    }

    public void setCourseName(String courseName) {

        this.courseName.set(courseName);
    }
}
