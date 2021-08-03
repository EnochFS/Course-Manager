package Program;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Locale;

// Creates courses thats will be used to help build classes for the database
public class courseCreator {

    public Button back, finalizeProfile;
    public TextField coursecode, courseid, coursename;
    public String codeString, idString, nameString;
    public PreparedStatement pst;

    public void backButton(ActionEvent actionEvent) {

        Stage stage = (Stage) back.getScene().getWindow();
        stage.close();

    }

    public void confButton(ActionEvent actionEvent) {
        codeString = coursecode.getText().toUpperCase(Locale.ROOT);
        idString = courseid.getText();
        nameString = coursename.getText();

        if(codeString.isEmpty() || idString.isEmpty() || nameString.isEmpty()){
            AlertBox.display("Error", "You're missing information");
        }else if(codeString.length() != 4){
            AlertBox.display("Error", "Course code has to be 4 Chars long");
        }else if(idString.length() != 3){
            AlertBox.display("Error", "Course id has to be 3 Chars long");

        }else{
            try {
                Connection conn = null;
                String url = "jdbc:sqlite:Login.db";
                conn = DriverManager.getConnection(url);
                String query = "INSERT INTO classes(coursecode, courseid, coursename) VALUES(?, ?, ?)";
                pst = conn.prepareStatement(query);
                pst.setString(1, codeString);
                pst.setString(2, idString);
                pst.setString(3, nameString);
                pst.executeUpdate();
                conn.close();
                Stage stage = (Stage) back.getScene().getWindow();
                stage.close();
                System.out.println("Well Done!");

            } catch (Exception e) {
                AlertBox.display("Notice", "This Username is already taken!");
            }
        }
    }


}
