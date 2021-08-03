package Program;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;


// Main menu that gives you 5 different options:
// 1) Course creator = Create a course that can be used help create classes for the database
// 2) Add a student = Adds a student to the database
// 3) Add Instructor = Adds an instructor to the database
// 4) Class Creator = Creates classes by selecting an instructor, course, and the students who'll attend
// 5) View Classes = Classes that have been created and are stored in the database
public class homePage {

    public Button courseCreate, studentAdd, courseCreator;
    public FXMLLoader nextPage;

    public void closeMainPage(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void editProfile(ActionEvent actionEvent) {
    }

    public void aboutTheProject(ActionEvent actionEvent) {
    }

    public void courseCreate(ActionEvent actionEvent) {
        try {
            nextPage = new FXMLLoader(getClass().getResource("courseCreate.fxml"));
            Parent signUp = nextPage.load();
            Stage signUpScene = new Stage();
            signUpScene.setTitle("Create a Course");
            signUpScene.setScene(new Scene(signUp));
            signUpScene.setResizable(false);
            signUpScene.initModality(Modality.APPLICATION_MODAL);
            signUpScene.showAndWait();
        } catch (Exception e) {
            AlertBox.display("Sorry!", "Could not open please try again later.");
        }
    }


    public void studentAdd(ActionEvent actionEvent) {
        try{
            nextPage = new FXMLLoader(getClass().getResource("studentAddition.fxml"));
            Parent signUp = nextPage.load();
            Stage signUpScene = new Stage();
            signUpScene.setTitle("Add Student Info");
            signUpScene.setScene(new Scene(signUp));
            signUpScene.setResizable(false);
            signUpScene.initModality(Modality.APPLICATION_MODAL);
            signUpScene.showAndWait();
        }catch (Exception e){
            AlertBox.display("Sorry!", "Could not open please try again later.");
        }
    }

    public void classCreate(ActionEvent actionEvent) {
        try{
            nextPage = new FXMLLoader(getClass().getResource("courseAdder.fxml"));
            Parent signUp = nextPage.load();
            Stage signUpScene = new Stage();
            signUpScene.setTitle("Create a class");
            signUpScene.setScene(new Scene(signUp));
            signUpScene.setResizable(false);
            signUpScene.initModality(Modality.APPLICATION_MODAL);
            signUpScene.showAndWait();
        }catch (Exception e){
            AlertBox.display("Sorry!", "Could not open please try again later.");
        }
    }

    public void intructorAdd(ActionEvent actionEvent) {
        try{
            nextPage = new FXMLLoader(getClass().getResource("instructorAddition.fxml"));
            Parent signUp = nextPage.load();
            Stage signUpScene = new Stage();
            signUpScene.setTitle("Add an Instructor Info");
            signUpScene.setScene(new Scene(signUp));
            signUpScene.setResizable(false);
            signUpScene.initModality(Modality.APPLICATION_MODAL);
            signUpScene.showAndWait();
        }catch (Exception e){
            AlertBox.display("Sorry!", "Could not open please try again later.");
        }
    }

    public void viewClasses(ActionEvent actionEvent) {
        try{
            nextPage = new FXMLLoader(getClass().getResource("classViewer.fxml"));
            Parent signUp = nextPage.load();
            Stage signUpScene = new Stage();
            signUpScene.setTitle("View Current Classes");
            signUpScene.setScene(new Scene(signUp));
            signUpScene.setResizable(false);
            signUpScene.initModality(Modality.APPLICATION_MODAL);
            signUpScene.showAndWait();
        }catch (Exception e){
            AlertBox.display("Sorry!", "Could not open please try again later.");
        }
    }
}

