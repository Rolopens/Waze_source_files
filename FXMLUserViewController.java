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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
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
    
    public void changeScreenButtonPushed(ActionEvent e) throws IOException{
    Parent adminViewParent = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
    Scene adminViewScene  = new Scene(adminViewParent);
        
    Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
       
    window.setScene(adminViewScene);
    window.show();
    //will add lastlogin code here
    java.sql.Date sqlDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
    currentUser.setLastLogin(sqlDate);
    System.out.println(currentUser.getLastLogin().toString());
    //still need to fix update function in service file
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
    
    /**
     * Initializes the controller class.
     */
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
        imageView.setImage(new Image("waze/avatars/" + "default.png"));
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
    }   
    
    public ObservableList<User> getPeople(){
       ObservableList<User> people = FXCollections.observableArrayList();
       
       UsersService service = new UsersService(new UsersDB());
       List <User> Users = service.getAll();
		
       for (User User: Users) {
            people.add(User);
       }
       
       return people;
    }    
    
}
