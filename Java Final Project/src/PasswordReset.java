import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;


public class PasswordReset {
    public Scene getScene(Main login){

        Label title = new Label("FORGOT PASSWORD!?");

        Label userLabel = new Label("USERNAME: ");
        TextField userText= new TextField();
        userText.setStyle("-fx-background-color: #ffff;-fx-border-color: #000000; -fx-background-radius: 5px; -fx-border-radius: 5px;");

        Label pincodeLabel = new Label("PINCODE: ");
        PasswordField pincode= new PasswordField();
        pincode.setStyle("-fx-background-color: #ffff;-fx-border-color: #000000; -fx-background-radius: 5px; -fx-border-radius: 5px;");

        Label newPassLabel= new Label("NEW PASSWORD: ");
        PasswordField passwordField= new PasswordField();
        passwordField.setStyle("-fx-background-color: #ffff;-fx-border-color: #000000; -fx-background-radius: 5px; -fx-border-radius: 5px;");

        Button done =  new Button();
        done.setPrefHeight(32.0);
        done.setPrefWidth(100.0);
        done.setStyle("-fx-background-color: #099129; -fx-border-radius: 5px; -fx-border-color: #000000; -fx-background-radius: 5px; ");
        done.setText("Done");
        done.setTextFill(Color.WHITE);
        done.setOnAction(event1 -> {
            if(login.getLoad(userText.getText(),pincode.getText(),true)){
                login.registerO.getWrite(login,userText.getText(),passwordField.getText(),pincode.getText(),true);
                login.getWindow().setScene(login.getScene());
            }
        });
        Button goBack = new Button("Go Back");
        goBack.setPrefHeight(32.0);
        goBack.setPrefWidth(100.0);
        goBack.setStyle("-fx-background-color: #307bf4; -fx-border-color: #000000; -fx-background-radius: 5px; -fx-border-radius: 5px;");
        goBack.setTextFill(Color.WHITE);
        goBack.setOnAction(event -> {
            login.getWindow().setScene(login.getScene());
        });
        GridPane gridPane= new GridPane();
        gridPane.setHgap(20);
        gridPane.setVgap(20);
        gridPane.setAlignment(Pos.CENTER);
        GridPane.setConstraints(userLabel,0,0);
        GridPane.setConstraints(userText,1,0);
        GridPane.setConstraints(pincodeLabel,0,1);
        GridPane.setConstraints(pincode,1,1);
        GridPane.setConstraints(newPassLabel,0,2);
        GridPane.setConstraints(passwordField,1,2);
        GridPane.setConstraints(done,0,5);
        GridPane.setConstraints(goBack,1,5);
        gridPane.getChildren().addAll(userLabel,userText,pincodeLabel,pincode,newPassLabel,passwordField,goBack,done);
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(50);
        vBox.setPadding(new Insets(30,30,30,30));
        vBox.getChildren().addAll(title,gridPane);
        vBox.setStyle("-fx-background-color:rgba(67,168,193,0.85);");

        return new Scene(vBox,700,500);
    }
}
