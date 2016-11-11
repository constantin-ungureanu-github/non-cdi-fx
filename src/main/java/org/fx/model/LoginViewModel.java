package org.fx.model;

import javax.xml.bind.annotation.XmlRootElement;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@XmlRootElement(name = "credentials")
public class LoginViewModel {
    private final StringProperty user;
    private final StringProperty password;

    public LoginViewModel() {
        user = new SimpleStringProperty();
        password = new SimpleStringProperty();
    }

    public LoginViewModel(final String user, final String password) {
        this.user = new SimpleStringProperty(user);
        this.password = new SimpleStringProperty(password);
    }

    public StringProperty getUserProperty() {
        return user;
    }

    public String getUser() {
        return user.get();
    }

    public void setUser(final String user) {
        this.user.set(user);
    }

    public StringProperty getPasswordProperty() {
        return password;
    }

    public String getPassword() {
        return password.get();
    }

    public void setPassword(final String password) {
        this.password.set(password);
    }
}
