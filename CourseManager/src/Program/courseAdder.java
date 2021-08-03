package Program;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


// Course allows users to add courses to the database,
// the users will be prompted to select an instructor, course
// and the students who will be attending the course

public class courseAdder implements Initializable {

    public Connection conn;
    public PreparedStatement pst;
    public Button backPage, finalizeProfile;
    public ChoiceBox<String> instructor, courses;
    public ListView avstudents, selstudents;
    public String firstPart, secondPart, thirdPart, combo;
    public String url3 = "jdbc:sqlite:login.db";
    public ArrayList<String> studentsAvailable = new ArrayList<String>();
    public ArrayList<String> studentsSelected = new ArrayList<String>();
    public ArrayList<String> courseSelection = new ArrayList<String>();
    public ArrayList<String> instructorSelection = new ArrayList<String>();




    public void backPageButton(ActionEvent actionEvent) {
        Stage stage = (Stage) backPage.getScene().getWindow();
        stage.close();
    }

    public void confButton(ActionEvent actionEvent) {
        try{
            conn = DriverManager.getConnection(url3);
            String queryZero = "INSERT INTO courses(coursename, instructor, student, student1, student2, student3, student4, student5, student6, student7, student8, student9, student10, student11, student12, student13, student14, student15, student16, student17, student18, student19, student20) VALUES(?, ?, ? , ?, ?, ?, ?, ?, ? , ?, ?, ?, ?, ?, ? , ?, ?, ?, ?, ?, ? , ?, ?)";
            pst = conn.prepareStatement(queryZero);
            pst.setString(1, courses.getSelectionModel().getSelectedItem());
            pst.setString(2, instructor.getSelectionModel().getSelectedItem());
            for(int i = 0; i < studentsSelected.size(); i++){
                pst.setString(i+3, studentsSelected.get(i));
            }
            pst.executeUpdate();
            conn.close();
            System.out.println("Success");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void addStudentButton(ActionEvent actionEvent) {
        String selectedItem = (String) avstudents.getSelectionModel().getSelectedItem();
        studentsSelected.add(selectedItem);
        studentsAvailable.remove(selectedItem);
        selstudents.getItems().add(selectedItem);
        avstudents.getItems().remove(selectedItem);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Statement stmt = null;
        ResultSet rs;
        String query4 = "select * from classes";
        String query5 = "select * from facultyinformation";
        conn = null;


        try{
            conn = DriverManager.getConnection(url3);
            String query3 = "select * from studentinformation";
            try{
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query3);
                while(rs.next()){
                    firstPart = rs.getString("first");
                    secondPart = rs.getString("last");
                    studentsAvailable.add(firstPart + " " + secondPart);
                }
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try{
            conn = DriverManager.getConnection(url3);
            try{
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query4);
                while(rs.next()){
                    firstPart = rs.getString("coursecode");
                    secondPart = rs.getString("courseid");
                    thirdPart = rs.getString("coursename");
                    courseSelection.add(firstPart + " " + secondPart + ": " + thirdPart);
                }
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try{
            conn = DriverManager.getConnection(url3);

            try{
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query5);
                while(rs.next()){
                    firstPart = rs.getString("firstname");
                    secondPart = rs.getString("lastname");
                    instructorSelection.add(firstPart + " " + secondPart);
                }
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }




        instructor.getItems().addAll(instructorSelection);
        courses.getItems().addAll(courseSelection);

        avstudents.getItems().addAll(studentsAvailable);
        selstudents.getItems().addAll(studentsSelected);

    }


}
