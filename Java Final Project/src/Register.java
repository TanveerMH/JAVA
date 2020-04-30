import javafx.geometry.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;



public  class Register {

    public Scene getScene(Main login){
        Label title= new Label("Sign Up !");
        title.setFont(Font.font(16));
        Label uLabel=  new Label(" User Name ");
        TextField uText =  new TextField();
        uText.setMaxWidth(190);
        uText.setStyle("-fx-background-color: #ffff; -fx-background-radius: 5px; -fx-border-radius: 5px;-fx-border-width: 3;");

        Label notification= new Label();
        Label notification2= new Label();
        Label passLabel = new Label("Password");
        PasswordField pass= new PasswordField();
        pass.setMaxWidth(190);
        pass.setStyle("-fx-background-color: #ffff; -fx-background-radius: 5px; -fx-border-radius: 5px;-fx-border-width: 3;");

        HBox hbox2=new HBox();
        hbox2.getChildren().addAll(uText,notification);
        hbox2.setSpacing(20);
        Label confirmPassLabel= new Label("Confirm Password");
        PasswordField confirmPass= new PasswordField();
        confirmPass.setMaxWidth(190);
        confirmPass.setStyle("-fx-background-color: #ffff; -fx-background-radius: 5px; -fx-border-radius: 5px;-fx-border-width: 3;");

        Label pincodeLabel= new Label("PIN ,for Password Resetting");
        PasswordField pincode = new PasswordField();
        pincode.setMaxWidth(190);
        pincode.setStyle("-fx-background-color: #ffff; -fx-background-radius: 5px; -fx-border-radius: 5px;-fx-border-width: 3;");

        HBox hBox3= new HBox();
        hBox3.setSpacing(190);
        hBox3.getChildren().addAll(pincodeLabel,notification2);

        uText.setOnMouseClicked(event -> {
            notification.setText("");
            notification2.setText("" );
            notification2.setText("");
        });
        confirmPass.setOnMouseClicked(event -> {
            confirmPassLabel.setText("Confirm Password");
            notification2.setText("");
            notification2.setText("");
        });
        pass.setOnMouseClicked(event -> {
            confirmPassLabel.setText("Confirm Password");
            notification2.setText("" );
            notification2.setText("");
        });
        pincode.setOnMouseClicked(event -> {
            notification2.setText("" );
            notification2.setText("");
        });
        Button register = new Button();
        register.setMinSize(100,30);
        register.setText("SignUp");
        register.setTextFill(Color.WHITE);
        register.setStyle("-fx-background-color:#03910f; -fx-border-radius: 5px; -fx-border-color: #000000; -fx-background-radius: 5px; ");
        register.setOnAction(event1 -> {
            // System.out.println("IN_>> "+uText.getText()+"  "+pass.getText()+"  "+confirmPass.getText()+"  "+pincode.getText());
            if(!uText.getText().isEmpty() && !pass.getText().isEmpty() && !confirmPass.getText().isEmpty() && !pincode.getText().isEmpty()
                    && pass.getText().equals(confirmPass.getText()) && !pass.getText().contains(" ")&& !uText.getText().contains(" ") && !pincode.getText().contains(" ")
                    ){

                if(getWrite(login,uText.getText(),pass.getText(),pincode.getText(),false))
                    login.getWindow().setScene(login.getScene());
                else{
                    notification.setText("USERNAME ALREADY EXIST !");
                }
            }
            if(!pass.getText().equals(confirmPass.getText())){
                confirmPassLabel.setText("PASSWORD DOESN'T MATCH");
            }

            if(pass.getText().contains(" ")||uText.getText().contains(" ") || pincode.getText().contains(" ")){
                notification2.setText("INPUT CAN'T HAVE SPACE" );
            }
            if(uText.getText().isEmpty() || pass.getText().isEmpty() || confirmPass.getText().isEmpty() || pincode.getText().isEmpty()
                    ){
                notification2.setText("SOMETHING IS MISSING");
            }
            //
        });

        Button goBack = new Button();
        goBack.setText("Go Back");
        goBack.setMinSize(100,30);
        goBack.setTextFill(Color.WHITE);
        goBack.setStyle("-fx-background-color:#cf2635; -fx-border-radius: 5px; -fx-border-color: #000000; -fx-background-radius: 5px; ");
        goBack.setOnAction(event -> {
            login.getWindow().setScene(login.getScene());
        });

        HBox hBox = new HBox();
        hBox.setSpacing(100);
        hBox.getChildren().addAll(goBack,register);
        hBox.setAlignment(Pos.CENTER);

        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER_LEFT);
        vbox.setSpacing(15);
        vbox.setPadding(new Insets(30,30,30,30));
       

        vbox.getChildren().addAll(title,uLabel,hbox2,passLabel,pass,confirmPassLabel,confirmPass,hBox3,pincode,hBox);

        vbox.setStyle("-fx-background-color:rgba(67,168,193,0.85)");
        return  new Scene(vbox,700,500);
    }
    public boolean getWrite(Main login,String username,String password,String pincode,boolean update){
        boolean exist=true;
        String fileName ="credential.txt";
        boolean write=true;

        try {

            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter contactWriter = new BufferedWriter(fileWriter);
            // System.out.println("Credential "+login.numberOfCredential);
            for(int i=0;i<login.getNumberOfCredential();i++){
                String currentLine=(login.getCredential()[i][0]+" "+login.getCredential()[i][1]+" "+login.getCredential()[i][2]+" ").toLowerCase();
                //   System.out.println("Current Line |"+ currentLine+"|");
                if((login.getCredential()[i][0]).equals(username.toLowerCase())){
                    if(update){
                        write=false;
                        contactWriter.append((username+" "+password+" "+pincode+" ").toLowerCase());
                    }
                    else{
                        //         System.out.println("already exist");
                        exist=  false;
                    }
                }
                else {

                    contactWriter.append(currentLine.toLowerCase());contactWriter.newLine();
                    //   System.out.println("Pass y "+currentLine);
                }
            }

            if (write){
                contactWriter.append((username.toLowerCase()+" "+password+" "+pincode+" ").toLowerCase());
                //System.out.println("ADD new "+username+" "+password+" "+pincode+" ");
            }
            contactWriter.newLine();
            contactWriter.close();
        }
        catch(IOException ex) {
            System.out.println(
                    "Error writing to file '"
                            + fileName + "'");

        }
        return exist;
    }

}

