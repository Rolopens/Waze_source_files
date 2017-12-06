/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package waze;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


/**
 * FXML Controller class
 *
 * @author Rolo
 */
public class FXMLUserViewController implements Initializable {
    
    private User currentUser;
    private RoadDensity currentRD;
    @FXML Label welcome;
    @FXML Button logoutButton;
    
    @FXML TextField EmailUpdate;
    @FXML TextField PasswordUpdate;
    @FXML TextField PhoneNoUpdate;
    @FXML TextField LastNameUpdate;
    @FXML TextField FirstNameUpdate;
   
    @FXML Label userIDLabel;
    @FXML Label lastNameLabel;
    @FXML Label firstNameLabel;
    @FXML Label emailAddressLabel;
    @FXML Label phoneNoLabel;
    
    @FXML TableView<User> friendsTable;
    @FXML TableColumn<User, Image> avatarColumn;
    @FXML TableColumn<User, String> firstNameColumn;
    @FXML TableColumn<User, String> lastNameColumn;
    @FXML TableColumn<User, java.sql.Date> dateColumn;
    
    @FXML ImageView usericon;
    @FXML ImageView updateDetailsAvatar;
    
    @FXML private ChoiceBox location;
    @FXML private ChoiceBox destination;
    @FXML private ChoiceBox streets;
    @FXML private ImageView map;
    @FXML private Button travel;
    @FXML private Button report;
    
    @FXML TextField usernameAddFriend;

    
    public void changeScreenButtonPushed(ActionEvent e) throws IOException{
    
    
    Parent adminViewParent = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
    Scene adminViewScene  = new Scene(adminViewParent);
        
    Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
       
    window.setScene(adminViewScene);
    window.show();
    
    
    
    //will add lastlogin code here
    LocalDate today = LocalDate.now();
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
    currentUser.setLastLogin(dtf.format(today));
    //still need to fix update function in service file
    UsersService service1 = new UsersService(new UsersDB());
    service1.updateUser(currentUser);
}
    
    public void updateDetailsButtonPushed(ActionEvent e) throws IOException{
        
        UsersService service1 = new UsersService(new UsersDB());
        
        if(!EmailUpdate.getText().equals("")) {
            currentUser.setEmail(EmailUpdate.getText());
            EmailUpdate.setText("");
            emailAddressLabel.setText(currentUser.getEmail());
        }
        else if(!PasswordUpdate.getText().equals("")) {
            currentUser.setPassword(PasswordUpdate.getText());
            PasswordUpdate.setText("");
        }
        else if(!PhoneNoUpdate.getText().equals("")) {
            currentUser.setPhoneNo(PhoneNoUpdate.getText());
            PhoneNoUpdate.setText("");
            phoneNoLabel.setText(currentUser.getPhoneNo());
        }
        else if(!LastNameUpdate.getText().equals("")) {
            currentUser.setLastName(LastNameUpdate.getText());
            LastNameUpdate.setText("");
            lastNameLabel.setText(currentUser.getLastName());
        }
        else if(!FirstNameUpdate.getText().equals("")) {
            currentUser.setFirstName(FirstNameUpdate.getText());
            FirstNameUpdate.setText("");
            firstNameLabel.setText(currentUser.getFirstName());
            welcome.setText("Hello, " + currentUser.getFirstName() + "!");
        }
        
        service1.updateUser(currentUser);
        /*
        Parent adminViewParent = FXMLLoader.load(getClass().getResource("FXMLUserView.fxml"));
        Scene adminViewScene  = new Scene(adminViewParent);
        
        Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
       
        window.setScene(adminViewScene);
        window.show();*/               
        
    }
    
    public void addFriendButtonPushed(ActionEvent e) throws IOException {
        FriendsService service1 = new FriendsService(new UsersDB());
           
        if(!usernameAddFriend.getText().equals("")) {
            if(isUniqueUsername(currentUser.getUsername(), usernameAddFriend.getText()) && userExists(usernameAddFriend.getText())) {
            service1.addFriend(toFriend());
            clear();
            friendsTable.setItems(getPeople());}
            else
                System.out.println("[ADDING ERROR] Invalid!");
        }
        else {
            System.out.println("[ADDING ERROR] No input!");
        }
    }
    
    public void deleteFriendButtonPushed(ActionEvent e) throws IOException {
        FriendsService service2 = new FriendsService(new UsersDB());
        User selectedRow = friendsTable.getSelectionModel().getSelectedItem();
        
        service2.deleteFriend(selectedRow.getUsername(), currentUser.getUsername());
    
        friendsTable.setItems(getPeople());
    }
    
    public void travelButtonPushed(ActionEvent e) throws IOException {
        if(location.getValue().toString() == "San Beda" && destination.getValue().toString() == "Park" || location.getValue().toString() == "Park" && destination.getValue().toString() == "San Beda"){
            map.setImage(new Image("waze/SBP1.png"));
        }else if(location.getValue().toString() == "San Beda" && destination.getValue().toString() == "Gas Station" || location.getValue().toString() == "Gas Station" && destination.getValue().toString() == "San Beda"){
            map.setImage(new Image("waze/SBG1.png"));
        }else if(location.getValue().toString() == "Gas Station" && destination.getValue().toString() == "Park" || location.getValue().toString() == "Park" && destination.getValue().toString() == "Gas Station"){
            map.setImage(new Image("waze/GSP1.png"));
        }else
            map.setImage(new Image("waze/aHills.png"));
    }
    
    public void reportButtonPushed(ActionEvent e) throws IOException {
        RoadDensityService serviceR = new RoadDensityService(new UsersDB());
        
        serviceR.addTraficReport(currentRD, streets.getValue().toString());
        
        //System.out.println(currentRD.toString());
        //currentRD.setTrafficReports(currentRD.getTrafficReports());
    }
    
    private Friend toFriend() {
        Friend Friend = new Friend();
		
	Friend.setUsername(currentUser.getUsername());
        Friend.setFriendsUsername(usernameAddFriend.getText());
		
	return Friend;
    }
    
    public void clear(){
        usernameAddFriend.setText("");
    }
    
    public boolean isUniqueUsername(String currentUserName, String friendUserName){
        FriendsService service = new FriendsService(new UsersDB());
        List <Friend> Friends = service.getAll();
        boolean unique = true;
        
        for (Friend x: Friends) {
            if (x.getUsername().equals(currentUserName) && x.getFriendsUsername().equals(friendUserName))
                unique = false;
       }
        
        return unique;
    }
    
    public boolean userExists(String friendUserName){
        UsersService service = new UsersService(new UsersDB());
        List <User> Users = service.getAll();
        boolean unique = false;
        
        for (User x: Users) {
            if (x.getUsername().equals(friendUserName))
                unique = true;
       }
        
        return unique;
    }
    
    public void initUser(User user){
        this.currentUser = user;
        welcome.setText("Hello, " + currentUser.getFirstName() + "!");
        
        userIDLabel.setText(currentUser.getUsername());
        lastNameLabel.setText(currentUser.getLastName());
        firstNameLabel.setText(currentUser.getFirstName());
        emailAddressLabel.setText(currentUser.getEmail());
        phoneNoLabel.setText(currentUser.getPhoneNo());
        
        //System.out.println(currentUser.getAvatar());
        usericon.setImage(new Image("waze/" +currentUser.getAvatar()));
        updateDetailsAvatar.setImage(new Image("waze/" +currentUser.getAvatar()));
    }
    
    private class ImageTableCell<S> extends TableCell<S, Image> {
    final ImageView imageView = new ImageView();

    ImageTableCell() {
        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
    }

    @Override
    protected void updateItem(Image item, boolean empty) {
        super.updateItem(item, empty);
        imageView.setImage(new Image("waze/" + "default.png"));
        setGraphic(imageView);
    }
}
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        // TODO
        
        avatarColumn.setCellValueFactory(new PropertyValueFactory<>("image"));
        avatarColumn.setCellFactory(param -> new ImageTableCell<>());
        
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("FirstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("LastName"));
        // dateColumn.setCellValueFactory(new PropertyValueFactory<User, Date>("LastLogin"));
        
        friendsTable.setItems(getPeople());
        friendsTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        
        //for the choicebox
        
        location.getItems().add("San Beda");
        location.getItems().add("Gas Station");
        location.getItems().add("Park");
        
        destination.getItems().add("San Beda");
        destination.getItems().add("Gas Station");
        destination.getItems().add("Park");
        
        streets.getItems().add("Don Manolo");
        streets.getItems().add("Don Jesus");
        streets.getItems().add("Angeles I");
        streets.getItems().add("Angeles II");
        streets.getItems().add("Cebu");
        streets.getItems().add("Dona Annie");
        streets.getItems().add("Bacolod");
        streets.getItems().add("Dona Mary");
        streets.getItems().add("Ormoc");
        streets.getItems().add("Don Ines I");
        streets.getItems().add("Don Ines II");
        streets.getItems().add("Tacloban");
        streets.getItems().add("Tagbilaran");
        streets.getItems().add("Trece Martires");
    }   
    
    public ObservableList<User> getPeople(){
       ObservableList<User> people = FXCollections.observableArrayList();
       
       UsersService service = new UsersService(new UsersDB());
       List <User> Users = service.getAll(); // change to friends
		
       for (User User: Users) {
            people.add(User);
       }
       
       return people;
    }    
    
}
