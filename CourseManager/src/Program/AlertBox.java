package Program;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {

    // Standard Alert Box that handles error messages for the user to see
    public static void display(String title, String message){
        Stage alerts = new Stage();

        alerts.initModality(Modality.APPLICATION_MODAL);
        alerts.setTitle(title);
        alerts.setMinWidth(250);

        Label label = new Label();
        label.setText(message);
        Button close = new Button("Ok!");
        close.setOnAction(e -> alerts.close());

        VBox layout = new VBox(15);
        layout.getChildren().addAll(label, close);
        layout.setAlignment(Pos.CENTER);

        Scene alertScene = new Scene(layout);
        alerts.setScene(alertScene);
        alerts.setResizable(false);
        alerts.showAndWait();
    }
}
