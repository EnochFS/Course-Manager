package Program;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;
// Helps users see what classes are stored on the database
public class classViewer implements Initializable {

    public Button back;
    @FXML
    private TableView<ClassInformation> courseView;
    @FXML
    private TableColumn<ClassInformation, String> courseName;
    @FXML
    private TableColumn<ClassInformation, String> instructorName;



    public ArrayList<String> courses = new ArrayList<String>();
    public ArrayList<String> instructors = new ArrayList<String>();
    public String course;
    public String instructor;
    public String url4 = "jdbc:sqlite:login.db";
    public String query6 = "select * from courses";
    public Connection conn;
    public Statement stmt = null;
    ResultSet rs;


    public void returnBack(ActionEvent actionEvent) throws SQLException {
        Stage stage = (Stage) back.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try{
                conn = DriverManager.getConnection(url4);
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query6);
                while(rs.next()){
                    course = rs.getString("coursename");
                    courses.add(course);
                }
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }


        try {
            conn = DriverManager.getConnection(url4);
            try{
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query6);
                while(rs.next()){
                    instructor = rs.getString("instructor");
                    instructors.add(instructor);
                }
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        instructorName.setCellValueFactory(new PropertyValueFactory<ClassInformation, String>("instructorName"));
        courseName.setCellValueFactory(new PropertyValueFactory<ClassInformation, String>("courseName"));
        courseView.setItems(getClassInformation());
    }

    // add your data here from any source
    public ObservableList<ClassInformation> getClassInformation(){
        ObservableList<ClassInformation> ClassInfo = FXCollections.observableArrayList();
        for(int i = 0; i < instructors.size(); i++) {
            instructor = instructors.get(i);
            course = courses.get(i);
            ClassInfo.add(new ClassInformation(instructor, course));
        }
        return ClassInfo;
    }
}




