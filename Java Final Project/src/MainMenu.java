
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;



public class MainMenu extends ConfirmBox {

    private Main login;
    private Stage menu;
    private Scene scene;
    private BorderPane borderPane;
    private SearchEntry searchEntry= new SearchEntry();

    MainMenu(Main login){
        this.login=login;
    }


    public NewEntry getAddEntry() {
        return newEntry;
    }

    public void setNewEntry(NewEntry newEntry) {
        this.newEntry = newEntry;
    }

    public SearchEntry getSearchEntry() {
        return searchEntry;
    }

    public void setSearchEntry(SearchEntry searchEntry) {
        this.searchEntry = searchEntry;
    }

    public BorderPane getBorderPane() {
        return borderPane;
    }

    public void setBorderPane(BorderPane borderPane) {
        this.borderPane = borderPane;
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void setMenu(Stage menu) {
        this.menu = menu;
    }

    public Stage getMennu() {
        return menu;
    }
    public Main getLogin() {
        return login;
    }

    public void setLogin(Main login) {
        this.login = login;
    }

    public NewEntry newEntry= new NewEntry();
    public void getMenu(){
        menu=new Stage();
        borderPane = new BorderPane();
        borderPane.setLeft(searchEntry.getLeft(this));
        borderPane.setRight(searchEntry.getRight() );

        Button logout= new Button();
        logout.setPrefHeight(32.0);
        logout.setPrefWidth(100.0);
        logout.setStyle("-fx-background-color: rgba(17,8,195,0.85); -fx-border-radius: 5px; -fx-border-color: #000000; -fx-background-radius: 5px; ");
        logout.setTextFill(Color.WHITE);
        logout.setText("Logout");
        logout.setOnAction(event -> {
            menu.close();
            Main main= new Main();
            main.getWindow().setScene(main.getScene());
            main.getWindow().setResizable(false);
            main.getWindow().show();
        });


        Button add= new Button();
        add.setPrefHeight(32.0);
        add.setPrefWidth(100.0);
        add.setStyle("-fx-background-color:#03910f; -fx-border-radius: 5px; -fx-border-color: #000000; -fx-background-radius: 5px; ");
        add.setTextFill(Color.WHITE);
        add.setText("Add New");
        add.setOnAction(event -> {
            menu.setScene(newEntry.getScene(this,false));
        });
        Button update= new Button();
        update.setPrefHeight(32.0);
        update.setPrefWidth(100.0);
        update.setStyle("-fx-background-color: #cbf477; -fx-border-color: #000000; -fx-background-radius: 5px; -fx-border-radius: 5px;");
        update.setText("Update");
        update.setTextFill(Color.BLACK);
        update.setOnAction(event -> {
            searchEntry.getLoad();
            System.out.println("CHALAINDEX --- "+searchEntry.getIndex());
            borderPane.setLeft(searchEntry.getLeft(this));
            menu.setScene(newEntry.getScene(this,true));

        });
        Button delete= new Button();
        delete.setMnemonicParsing(false);
        delete.setPrefHeight(32.0);
        delete.setPrefWidth(100.0);
        delete.setStyle("-fx-background-color: #9f2121; -fx-border-color: #000000; -fx-background-radius: 5px; -fx-border-radius: 5px;");
        delete.setText("Delete");
        delete.setTextFill(Color.WHITE);
        delete.setOnAction(event -> {
            System.out.println("delete pressed");
            searchEntry.getLoad();
            boolean confirmation=display("Conformation ", "Are you sure to delete? ");
            System.out.println("confirmation*********> "+confirmation);
            if(confirmation){
                newEntry.setFileName(System.getProperty("user.dir")+"\\"+login.getUsername()+".txt");
                newEntry.update(this,false);
                searchEntry.getLoad();
                borderPane.setLeft(searchEntry.getLeft(this));
            }
        });
        Label welcome= new Label("WELCOME BACK "+login.getUsername().toUpperCase()+"        ");
        welcome.setTextFill(Color.valueOf("darkred"));
        welcome.setFont(new Font("Arial Bold", 15.0));

        HBox hBox= new HBox();
        hBox.setMinHeight(100);
        hBox.setMinWidth(600);
        hBox.setSpacing(25);
        hBox.setPadding(new Insets(30,30,30,30));
        hBox.setStyle("-fx-border-color: #000000;-fx-border-style: solid; -fx-background-radius: 5px; -fx-border-radius: 5px;-fx-border-width: 2;");
        hBox.getChildren().addAll(welcome,add,update,delete,logout);
        hBox.setAlignment(Pos.CENTER);

        borderPane.setTop(hBox);
      
        borderPane.setPadding(new Insets(30,30,30,30));
        borderPane.setStyle("-fx-background-color:rgba(78,212,236,0.64);");
        scene= new Scene(borderPane,850,650);
        menu.setScene(scene );
        menu.setResizable(false);
        menu.setTitle("                                                                                                 SWIFT ADDRESS");
        menu.show();
    }

}
