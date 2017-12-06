/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package waze;
//test change
import java.io.IOException;
import java.net.URL;
import java.util.List;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Rolo
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML private Label username;
    @FXML private Label password;
    @FXML private TextField usernameTextField;
    @FXML private PasswordField passwordTextField;
    @FXML private VBox loginVBox;
    @FXML Button signUpButton;
    @FXML private Button loginButton;
    
    /*
        Method to change scene
    */
    public void changeScreenButtonPushed(ActionEvent e) throws IOException{
        
        
        
        if(usernameTextField.getText().equals("admin") && passwordTextField.getText().equals("1234")){
            Parent adminViewParent = FXMLLoader.load(getClass().getResource("FXMLAdminView.fxml"));
            Scene adminViewScene  = new Scene(adminViewParent);
        
            Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
       
            window.setScene(adminViewScene);
            window.show();
        }
        else if(returnUsername(usernameTextField.getText())!=null){
            if(passwordTextField.getText().equals(returnUsername(usernameTextField.getText()).getPassword())){
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("FXMLUserView.fxml"));
                System.out.println(loader.toString());
                Parent adminViewParent = loader.load();
                Scene adminViewScene  = new Scene(adminViewParent);
            
                FXMLUserViewController controller = loader.getController();
                controller.initUser(returnUsername(usernameTextField.getText()));
            
                Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
       
                window.setScene(adminViewScene);
                window.show();
            }
            else
                System.out.println("[USER ERROR] WRONG PASSWORD");
        }
        else   
           System.out.println("[LOGIN ERROR] Invalid Credentials");
        
    }
    
    public void signUpButtonPushed(ActionEvent e) throws IOException{
        Parent adminViewParent = FXMLLoader.load(getClass().getResource("FXMLSignUpView.fxml"));
            Scene adminViewScene  = new Scene(adminViewParent);
        
            Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
       
            window.setScene(adminViewScene);
            window.show();
    }
    public User returnUsername(String userName){
        UsersService service = new UsersService(new UsersDB());
        List <User> Users = service.getAll();
        User foundUser = null;
        
        for (User x: Users) {
            if (x.getUsername().equals(userName))
                foundUser = x;
       }
        
        return foundUser;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
