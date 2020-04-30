import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;



public class  ConfirmBox {
    static boolean answer;
    public static boolean display(String title, String message){
        Stage window= new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(300);

        Label label= new Label(message);
        label.setFont(Font.font(15));
        Button YesB= new Button();
        YesB.setPrefWidth(80);
        YesB.setStyle("-fx-background-color: #099129; -fx-border-radius: 5px; -fx-border-color: #ffff; -fx-background-radius: 5px; ");
        YesB.setText("YES");
        YesB.setTextFill(Color.WHITE);

        Button NoB= new Button();
        NoB.setPrefWidth(80);
        NoB.setStyle("-fx-background-color: #9f2121; -fx-border-color: #ffff; -fx-background-radius: 5px; -fx-border-radius: 5px;");
        NoB.setText("NO");
        NoB.setTextFill(Color.WHITE);
        YesB.setOnAction(event -> {
            answer=true;
            window.close();
        });
        NoB.setOnAction(event ->{
            answer=false;
            window.close();
        });
        VBox vBox= new VBox(20);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(label,YesB,NoB);
        vBox.setStyle("-fx-background-color:#cf2635;");
        Scene scene =new Scene(vBox,350,150);
        window.setScene(scene);
        window.showAndWait();
        return answer;
    }
}
