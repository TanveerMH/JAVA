import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class Main extends Application {



    private String username="";
    private int numberOfCredential=0;
    private String[][] credential= new String[5][3];
    private Stage window = new Stage();

    public Register getRegisterO() {
        return registerO;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public Scene getScene(){

        Label title = new Label();
        title.setTextFill(Color.valueOf("#29078c"));
        title.setFont(new Font("Arial Bold", 24.0));
        title.setText("  USER LOGIN  ");
        title.setStyle("-fx-background-color: #ffff;-fx-border-color: #000000; -fx-background-radius: 5px; -fx-border-radius: 5px;");
        title.setAlignment(Pos.TOP_CENTER);



        Label userLabel= new Label("User Name");
        TextField userText= new TextField();
        userText.setPromptText("\t\t\t Email/Phone No");
        userText.setLayoutX(340.0);
        userText.setLayoutY(275.0);
        userText.setPrefHeight(35.0);
        userText.setPrefWidth(300.0);
        userText.setStyle("-fx-background-color: #ffff;-fx-border-color: #000000; -fx-background-radius: 5px; -fx-border-radius: 5px;");
        userText.setOnMouseClicked(event1 -> {
            userLabel.setText("User Name");
            userLabel.setTextFill(Color.BLACK);
        });


        

        Label passwordLabel=  new Label("Password");
        PasswordField passwordText= new PasswordField();
        passwordText.setStyle("-fx-background-color: #ffff; -fx-border-color: #000000; -fx-background-radius: 5px; -fx-border-radius: 5px;");
        passwordText.setPromptText("\t\t\tPassword   ");
        passwordText.setOnMouseClicked(event1 -> {
            passwordLabel.setText("Password");
            passwordLabel.setTextFill(Color.BLACK);
        });
        Button login= new Button();
        login.setLayoutX(560.0);
        login.setLayoutY(337.0);
        login.setMnemonicParsing(false);
        login.setPrefHeight(32.0);
        login.setPrefWidth(300.0);
        login.setStyle("-fx-background-color: #099129; -fx-border-radius: 5px; -fx-border-color: #000000; -fx-background-radius: 5px; ");
        login.setText("Login");
        login.setTextFill(Color.WHITE);
        login.setOnAction(event -> {
            if(getLoad(userText.getText(),passwordText.getText(),false)) {

                username=userText.getText();
                window.close();
                new MainMenu(this).getMenu();
            }
            else{
                passwordLabel.setText(" Wrong user name or password ");
                passwordLabel.setTextFill(Color.DARKRED);
            }

        });

        Image ex= new Image("ex.jpg");
        ImageView ivEx= new ImageView(ex);
        ivEx.setSmooth(true);
        ivEx.setCache(true);
        ivEx.setPreserveRatio(true);
        ivEx.setFitWidth(28);

        Image logo= new Image("address book.png");
        ImageView ivLogo= new ImageView(logo);
        ivLogo.setSmooth(true);
        ivLogo.setCache(true);
        ivLogo.setPreserveRatio(true);
        ivLogo.setFitWidth(250);

        ivLogo.setFitWidth(250);


        HBox hbox= new HBox();
        hbox.setSpacing(10);
        hbox.setAlignment(Pos.CENTER_RIGHT);
        Button reset = new Button();
        reset.setLayoutY(0.0);
        reset.setMnemonicParsing(false);
        reset.setPrefHeight(32.0);
        reset.setPrefWidth(260.0);
        reset.setStyle("-fx-background-color: #9f2121; -fx-border-color: #000000; -fx-background-radius: 5px; -fx-border-radius: 5px;");
        reset.setText("Reset Password");
        reset.setTextFill(Color.WHITE);
        reset.setOnAction(e->{
            window.setScene(new PasswordReset().getScene(this));
        });
        hbox.getChildren().addAll(reset,ivEx);

        Button signUp= new Button("Sign Up");
        signUp.setLayoutX(309.0);
        signUp.setLayoutY(17.0);
        signUp.setMnemonicParsing(false);
        signUp.setPrefHeight(32.0);
        signUp.setPrefWidth(300.0);
        signUp.setStyle("-fx-background-color: #307bf4; -fx-border-color: #000000; -fx-background-radius: 5px; -fx-border-radius: 5px;");
        signUp.setTextFill(Color.WHITE);
        signUp.setOnAction(e->{
            getLoad("","",false);
            window.setScene(registerO.getScene(this));
        });

        VBox vBox = new VBox();
        vBox.setSpacing(20);
        vBox.setPadding(new Insets(0,0,0,30));
        vBox.getChildren().addAll(userLabel,userText,passwordLabel,passwordText,login,hbox,signUp);
     
        vBox.setAlignment(Pos.CENTER_LEFT);

        HBox hBox1= new HBox();
        hBox1.getChildren().addAll(ivLogo,vBox);
        hBox1.setAlignment(Pos.TOP_LEFT);


        VBox finish = new VBox();
        finish.setSpacing(20);
        finish.setPadding(new Insets(40,40,40,40));
        finish.getChildren().addAll(title,hBox1);
        finish.setAlignment(Pos.CENTER);
        finish.setStyle("-fx-background-color:rgba(78,212,236,0.64)");
        return new Scene(finish,700,500);
    }

    public boolean getLoad(String user,String pass,boolean forgot){

        String fileName = "credential.txt";
        String line = null;
        System.out.println("credential called");
        numberOfCredential=0;
        try {
            FileReader fileReader = new FileReader(fileName);

            bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                for(int i=0,j=0;i<line.length();i++){
                    String temp="";

                    if(line.charAt(i)==' '){
                        temp=line.substring(0,i);
                        line=line.substring(i+1);
                        i=0;
                        //System.out.println("Temp "+ temp);
                        credential[numberOfCredential][j]=temp;
                        j++;

                    }
                }
                if(forgot && credential[numberOfCredential][2].equals(pass)){
                    return  true;
                }
                else if(credential[numberOfCredential][0].equals(user) && credential[numberOfCredential][1].equals(pass))
                    return true;
                numberOfCredential++;


            }
            System.out.println("Login numbe of cd "+numberOfCredential);
            for(int i=0;i<numberOfCredential;i++){
                System.out.println(credential[i][0]+"  "+credential[i][1]+"  "+credential[i][2]);
            }
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" +
                            fileName + "'");
        }
        catch(IOException ex) {
            System.out.println(
                    "Error reading file '"
                            + fileName + "'");

        }
        System.out.println("credential called passed");
        return false;

    }
    public void setRegisterO(Register registerO) {
        this.registerO = registerO;
    }

    public int getNumberOfCredential() {
        return numberOfCredential;
    }

    public void setNumberOfCredential(int numberOfCredential) {
        this.numberOfCredential = numberOfCredential;
    }

    public String[][] getCredential() {
        return credential;
    }

    public void setCredential(String[][] credential) {
        this.credential = credential;
    }

    public Stage getWindow() {
        return window;
    }

    public void setWindow(Stage window) {
        this.window = window;
    }

    Register registerO = new Register();
	private BufferedReader bufferedReader;


    public static void main(String [] args){
        launch(args);
    }
    public void start(Stage primary){
        window= primary;
        window.setScene(getScene());
        window.setResizable(false);
        window.setTitle("                                                             SWIFT ADDRESS");
        window.show();
    }
}
