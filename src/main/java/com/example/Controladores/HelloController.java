package com.example.Controladores;

import com.example.Modelo.Archivos;
import com.example.Modelo.Usuario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HelloController {

    @FXML
    private Button inicioSesion;

    @FXML
    private PasswordField password;

    @FXML
    private Button registro;

    @FXML
    private TextField user;

    private List<Usuario> usuarios = new ArrayList<Usuario>();

    public HelloController() throws FileNotFoundException {
         Archivos.llenarListaUsuario(usuarios);
    }


    @FXML
    void iniciarsesion(ActionEvent event) {

      if(usuarios.stream().anyMatch(p -> p.getUserName().equalsIgnoreCase(user.getText()
              )&& p.getPassword().equalsIgnoreCase(password.getText()))){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CoffeeApp.fxml"));
            try {
                Parent root = loader.load();
                CoffeeAppController cont = loader.getController();
                Scene sceneReg = new Scene(root);
                Stage stageReg = new Stage();
                stageReg.initModality(Modality.APPLICATION_MODAL);
                stageReg.setScene(sceneReg);
                stageReg.showAndWait();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else{
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
          alert.setTitle("Fallo al iniciar sesion");
          alert.setHeaderText("No se pudo iniciar sesion");
          alert.setContentText("Usuario o contraseña incorrecto");
          alert.setResizable(true);
          alert.showAndWait();
      }
    }

    @FXML
    void registrarse(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Registrar.fxml"));
        try {
            Parent root = loader.load();
            RegistrarCotroller cont = loader.getController();
            Scene sceneReg = new Scene(root);
            Stage stageReg = new Stage();
            stageReg.initModality(Modality.APPLICATION_MODAL);
            stageReg.setScene(sceneReg);
            stageReg.showAndWait();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}