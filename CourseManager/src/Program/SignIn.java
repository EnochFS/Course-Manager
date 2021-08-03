package Program;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.sql.*;

import java.io.IOException;

public class SignIn {

    public Button signUp, signIn;
    public TextField userField,passField;
    public String userString, passString;
    public FXMLLoader signInLoader, mangerLoader;





    public void signInButton(ActionEvent actionEvent) throws IOException, SQLException {
        userString = userField.getText();
        passString = passField.getText();


        // Calls database to pull Sign in credentials for admin users
        Connection conn = null;
        String url = "jdbc:sqlite:Login.db";
        conn = DriverManager.getConnection(url);
        String query = "select * from 'AdminLoginCredentials' WHERE username=? and password=?";
        ResultSet rs = null;
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, userString);
        stmt.setString(2, passString);
        rs = stmt.executeQuery();



        // Error checks to see whether or not fields are empty or
        // Sign in information is incorrect

        if( userString.isEmpty() && passString.isEmpty()){
            AlertBox.display("Notice!", "Enter your username and password.");
        }else if(userString.isEmpty()){
            AlertBox.display("Notice!", "Enter your username");
        }else if(passString.isEmpty()){
            AlertBox.display("Notice!", "Enter your password");
        }else if(rs.next()){
            // If nothing is wrong the sign in will be successful and take the user
            // to the home page
            Stage stage = (Stage) signIn.getScene().getWindow();
            stage.close();
            mangerLoader = new FXMLLoader(getClass().getResource("homePage.fxml"));
            Parent manager = mangerLoader.load();
            Stage manageScene = new Stage();
            manageScene.setTitle("Home page");
            manageScene.setScene(new Scene(manager));
            manageScene.setResizable(false);
            manageScene.initModality(Modality.APPLICATION_MODAL);
            manageScene.show();

        }else{
            AlertBox.display("Notice!", "Incorrect Username and/or Password");
        }
    }

}
