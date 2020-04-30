import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.*;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import java.io.*;



public class SearchEntry {
    private String fileName;
    private int count=0, index=0;
    private String[][] contact= new String[1000][12];
    private String selected="";
    private MainMenu menu;

    public TextField getSearchT() {
        return searchT;
    }

    public void setSearchT(TextField searchT) {
        this.searchT = searchT;
    }

    public MainMenu getMenu() {
        return menu;
    }

    public void setMenu(MainMenu menu) {
        this.menu = menu;
    }

    public String getSelected() {
        return selected;
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }

    public String[][] getContact() {
        return contact;
    }

    public void setContact(String[][] contact) {
        this.contact = contact;
    }

    public void setIndex(int index) {
        this.index = index;
    }
    public int getIIndex() {
        return index;
    }
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public TextField searchT= new TextField();
    public VBox getLeft(MainMenu temp){
        menu=temp;
        searchT.setPromptText("search by name");
        searchT.setStyle("-fx-font: 15px Tahoma; -fx-stroke: black; -fx-stroke-width: 1");
        searchT.setAlignment(Pos.CENTER);
        searchT.setOnKeyReleased(event -> {
            System.out.println(searchT.getText());
            handleSearch(searchT.getText());
        });
        fileName=System.getProperty("user.dir")+"\\"+menu.getLogin().getUsername()+".txt";
        //menu.getSearchEntry().getLoad();
        getLoad();
        ObservableList<String> names = FXCollections.observableArrayList();

        for(int i=0;i<count;i++){
            names.add(contact[i][0]+" "+contact[i][1]+" "+contact[i][2]+"\n"+contact[i][10]);
        }
        ListView<String>listView = new ListView<>(names);
        listView.setStyle("-fx-font-size:15;");
        listView.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends String> ov, String old_val,
                 String new_val) -> {
                    System.out.println(new_val);
                    selected=new_val;
                    getIndex();
                    getLoad();
                    menu.getBorderPane().setRight(getRight());

                });
        VBox vboxs= new VBox();
        vboxs.getChildren().addAll(searchT,listView);
        vboxs.setAlignment(Pos.TOP_CENTER);
        vboxs.setSpacing(25);
        vboxs.setMinWidth(300);
        vboxs.setMinHeight(300);
        vboxs.setPadding(new Insets(30,30,30,30));
        vboxs.setStyle("-fx-background-color: #4CAF;-fx-background-radius: 5px; -fx-border-radius: 5px;-fx-border-color:#000000;-fx-border-width: 2;");
        return vboxs;
    }





    public int getIndex(){
        int i;
        for( i=0;i<count;i++){
            if((contact[i][0]+" "+contact[i][1]+" "+contact[i][2]+"\n"+contact[i][10]).equals(selected)){
                menu.getSearchEntry().setIndex(i);
                System.out.println("Pressed Index--- "+ menu.getSearchEntry().getIIndex()+"  "+i);
                break;
            }
        }
        return i;
    }

    public StackPane getRight(){
        StackPane stackPane=new StackPane();
        stackPane.setMinWidth(500);
        stackPane.setMinHeight(300);

        stackPane.setAlignment(Pos.TOP_CENTER);
        stackPane.setPadding(new Insets(30,30,30,30));


        GridPane details= new GridPane();
        Label nameL= new Label("\tName \n\t"+contact[index][0]+" "+contact[index][1]+" "+contact[index][2]);
        nameL.setStyle("-fx-background-color: #ffff;-fx-border-color: #000000; -fx-background-radius: 5px; -fx-border-radius: 5px;");
        nameL.setMinHeight(50);

        nameL.setPrefHeight(50.0);
        nameL.setPrefWidth(200.0);


        Label emailL= new Label("\tEmail Address \n\t"+contact[index][3]);
        emailL.setStyle("-fx-background-color: #ffff;-fx-border-color: #000000; -fx-background-radius: 5px; -fx-border-radius: 5px;");
        emailL.setMinHeight(50);
        emailL.setPrefHeight(50.0);
        emailL.setPrefWidth(200.0);


        Label countryL= new Label("\tCountry \n\t"+contact[index][4]);
        countryL.setMinHeight(50);
        countryL.setPrefHeight(50.0);
        countryL.setPrefWidth(200.0);
        countryL.setStyle("-fx-background-color: #ffff;-fx-border-color: #000000; -fx-background-radius: 5px; -fx-border-radius: 5px;");

        Label cityL= new Label("\tCity \n\t"+contact[index][5]);
        cityL.setMinHeight(50);
        cityL.setPrefHeight(50.0);
        cityL.setPrefWidth(200.0);
        cityL.setStyle("-fx-background-color: #ffff;-fx-border-color: #000000; -fx-background-radius: 5px; -fx-border-radius: 5px;");

        Label zipL= new Label("\tZip \n\t"+contact[index][6]);
        zipL.setMinHeight(50);
        zipL.setPrefHeight(50.0);
        zipL.setPrefWidth(200.0);
        zipL.setStyle("-fx-background-color: #ffff;-fx-border-color: #000000; -fx-background-radius: 5px; -fx-border-radius: 5px;");

        Label birthdayL= new Label("\tBirthday  \n\t"+contact[index][7]+" - "+contact[index][8]+" - "+contact[index][9]);
        birthdayL.setMinHeight(50);
        birthdayL.setPrefHeight(50.0);
        birthdayL.setPrefWidth(200.0);
        birthdayL.setStyle("-fx-background-color: #ffff;-fx-border-color: #000000; -fx-background-radius: 5px; -fx-border-radius: 5px;");

        Label phoneL= new Label("\tPhone No \n\t"+contact[index][10]);
        phoneL.setMinHeight(50);
        phoneL.setPrefHeight(100.0);
        phoneL.setPrefWidth(200.0);
        phoneL.setStyle("-fx-background-color: #ffff;-fx-border-color: #000000; -fx-background-radius: 5px; -fx-border-radius: 5px;");


        Label addressL= new Label("\tAddress \n\t"+contact[index][11]);
        addressL.setMinHeight(50);
        addressL.setPrefHeight(100.0);
        addressL.setPrefWidth(200.0);
        addressL.setStyle("-fx-background-color: #ffff;-fx-border-color: #000000; -fx-background-radius: 5px; -fx-border-radius: 5px;");

        details.add(nameL,0,3);
        nameL.setFont(javafx.scene.text.Font.font(15));
        details.add(emailL,1,3);
        emailL.setFont(javafx.scene.text.Font.font(15));
        details.add(countryL,0,5);
        countryL.setFont(javafx.scene.text.Font.font(15));
        details.add(cityL,1,5);
        cityL.setFont(javafx.scene.text.Font.font(15));
        details.add(zipL,0,7);
        zipL.setFont(javafx.scene.text.Font.font(15));
        details.add(birthdayL,1,7);
        birthdayL.setFont(javafx.scene.text.Font.font(15));
        details.add(phoneL,0,9);
        phoneL.setFont(javafx.scene.text.Font.font(15));
        details.add(addressL,1,9 );
        addressL.setFont(javafx.scene.text.Font.font(15));
        details.setAlignment(Pos.TOP_CENTER);
        details.setHgap(10);
        details.setVgap(10);
        details.setMinWidth(100);
        stackPane.getChildren().add(details);
        stackPane.setStyle("-fx-background-color:#1C2833;-fx-border-color:#000000;-fx-background-radius: 5px; -fx-border-radius: 5px;-fx-border-width: 2;");
        return stackPane;
    }




    public void getLoad(){

        String line = null;
        count=0;
        System.out.println("Reader called");
        try {
            FileReader fileReader = new FileReader(fileName);

            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {

                for(int i=0,j=0;i<line.length();i++){
                    String temp="";

                    if(line.charAt(i)=='|'){
                        temp=line.substring(0,i);
                        line=line.substring(i+1);
                        i=0;
                        contact[count][j]=temp;
                        j++;

                    }
                }
                count++;
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

    }
    public void handleSearch(String query){
        ObservableList<String> names = FXCollections.observableArrayList();


        for(int i=0;i<count;i++){
            String match=contact[i][0]+" "+contact[i][1]+" "+contact[i][2]+"\n"+contact[i][10];
            //System.out.println("Match "+match+" Queary -->>>"+query);

            if(match.toLowerCase().contains(query.toLowerCase())){
                names.add(match);
            }
        }

        ListView<String>listView = new ListView<>(names);
        listView.setStyle("-fx-font-size:15;");
        listView.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends String> ov, String old_val,
                 String new_val) -> {
                    System.out.println(new_val);
                    selected=new_val;
                    getIndex();
                    getLoad();
                    menu.getBorderPane().setRight(getRight());

                });
        VBox vbox= new VBox();
        vbox.getChildren().addAll(searchT,listView);
        vbox.setAlignment(Pos.CENTER);
        vbox.setStyle("-fx-background-color:#4CAF;-fx-background-radius: 5px; -fx-border-radius: 5px;-fx-border-color:#000000;-fx-border-width: 2;");
        vbox.setSpacing(30);
        vbox.setMinWidth(200);
        vbox.setMinHeight(300);
        vbox.setPadding(new Insets(30,30,30,30));
        menu.getBorderPane().setLeft(vbox);
    }

}
