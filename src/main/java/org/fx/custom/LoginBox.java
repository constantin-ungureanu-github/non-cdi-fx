package org.fx.custom;

import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import org.fx.loaders.CustomControlLoader;

public class LoginBox extends VBox {
    private static final String FXML_FILE = "/fxml/view/custom/loginBox.fxml";

    @FXML
    private TextField user;

    @FXML
    private PasswordField password;

    @FXML
    public void initialize() {
    }

    public LoginBox() {
        CustomControlLoader.load(this, FXML_FILE);
    }

    public String getUser() {
        return userProperty().get();
    }

    public void setUser(final String value) {
        userProperty().setValue(value);
    }

    public StringProperty userProperty() {
        return user.textProperty();
    }

    public String getPassword() {
        return passwordProperty().get();
    }

    public void setPassword(final String value) {
        passwordProperty().setValue(value);
    }

    public StringProperty passwordProperty() {
        return password.textProperty();
    }
}
