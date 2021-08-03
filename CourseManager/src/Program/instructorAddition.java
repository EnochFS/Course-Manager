package Program;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class instructorAddition {

    public Button backPage, finalizeProfile;
    public TextField firstName, lastName, newUserName, firstPass, confPass, userid;
    public RadioButton male, female;
    public String firstNameString, lastNameString, userNameString, firstPassString, confPassString, gender, iduser;
    public PreparedStatement pst;
    private Object Stage;


    public void closeSignUpApplication(ActionEvent actionEvent) {
        Stage stage = (Stage) backPage.getScene().getWindow();
        stage.close();
    }

    public void finalizeProfile(ActionEvent actionEvent) {
        firstNameString = firstName.getText();
        lastNameString = lastName.getText();
        userNameString = newUserName.getText();
        firstPassString = firstPass.getText();
        confPassString = confPass.getText();
        iduser = userid.getText();
        if(male.isSelected() == true){
            gender = "Male";
        }else{
            gender = "Female";
        }

        if(firstNameString.isEmpty() || lastNameString.isEmpty() || userNameString.isEmpty() || firstPassString.isEmpty() ||
                confPassString.isEmpty()){
            AlertBox.display("Notice", "You are missing information");
        }else if(iduser.length() != 4) {
            AlertBox.display("Notice", "The User id must be 4 characters long");
        }else{
            try {
                Connection conn = null;
                String url = "jdbc:sqlite:Login.db";
                conn = DriverManager.getConnection(url);
                String query = "INSERT INTO facultyinformation(firstname, lastname, username, id, password, gender) VALUES(?, ?, ?, ?, ?, ?)";
                pst = conn.prepareStatement(query);
                pst.setString(1, firstNameString);
                pst.setString(2, lastNameString);
                pst.setString(3, userNameString);
                pst.setString(4, iduser);
                pst.setString(5, firstPassString);
                pst.setString(6, gender);
                pst.executeUpdate();
                conn.close();
                Stage stage = (Stage) backPage.getScene().getWindow();
                stage.close();
                System.out.println("Well Done!");
            }catch (Exception e){
                AlertBox.display("Notice", "This Username is already taken!");
            }
        }

    }
}

