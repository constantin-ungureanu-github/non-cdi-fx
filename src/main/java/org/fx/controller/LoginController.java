package org.fx.controller;

import java.io.File;
import java.util.Objects;
import java.util.function.Consumer;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import org.fx.custom.LoginBox;
import org.fx.model.LoginViewModel;
import org.fx.services.LoginService;
import org.fx.services.PersistenceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginController implements Controller {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    public static final String FXML = "/fxml/view/login.fxml";
    private static final String CREDENTIALS_PATH = "credentials.xml";
    private final LoginService loginService;
    private final PersistenceService persistenceService;
    private LoginViewModel loginViewModel;
    private Consumer<Void> nextCallback;

    @FXML
    LoginBox loginBox;

    @FXML
    private Button loginButton;

    @FXML
    private Text feedback;

    public LoginController(final LoginService loginService, final PersistenceService persistenceService) {
        this.loginService = loginService;
        this.persistenceService = persistenceService;
    }

    @FXML
    public void initialize() {
        logger.info("Initialize LoginController");

        initializeViewModel();

        loginBox.userProperty().bindBidirectional(loginViewModel.getUserProperty());
        loginBox.passwordProperty().bindBidirectional(loginViewModel.getPasswordProperty());

        loginButton.disableProperty().bind(loginBox.userProperty().isNotEmpty().and(loginBox.passwordProperty().isNotEmpty()).not());
    }

    private void initializeViewModel() {
        loginViewModel = (LoginViewModel) persistenceService.load(new File(CREDENTIALS_PATH), LoginViewModel.class);
        if (loginViewModel == null) {
            loginViewModel = new LoginViewModel(loginBox.userProperty().get(), loginBox.passwordProperty().get());
        }
    }

    @FXML
    void handleLoginButtonAction() {
        feedback.setText(loginService.login(loginBox.getUser(), loginBox.getPassword()));
        persistenceService.save(new File(CREDENTIALS_PATH), loginViewModel);

        nextCallback.accept(null);
    }

    @Override
    public void setNextCallback(final Consumer<Void> nextCallback) {
        this.nextCallback = Objects.requireNonNull(nextCallback);
    }
}
