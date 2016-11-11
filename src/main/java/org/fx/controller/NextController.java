package org.fx.controller;

import java.io.File;
import java.util.Objects;
import java.util.function.Consumer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;

import org.fx.model.NextViewModel;
import org.fx.services.PersistenceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NextController implements Controller {
    private static final Logger logger = LoggerFactory.getLogger(NextController.class);
    public static final String FXML = "/fxml/view/next.fxml";
    private static final String NEXT_PATH = "text.xml";
    private final PersistenceService persistenceService;
    private NextViewModel nextViewModel;
    private Consumer<Void> nextCallback;

    @FXML
    TextArea textArea;

    public NextController(final PersistenceService persistenceService) {
        this.persistenceService = persistenceService;
    }

    @FXML
    public void initialize() {
        logger.info("Initialize NextController");

        initializeViewModel();

        textArea.textProperty().bindBidirectional(nextViewModel.getTextProperty());
    }

    private void initializeViewModel() {
        nextViewModel = (NextViewModel) persistenceService.load(new File(NEXT_PATH), NextViewModel.class);
        if (nextViewModel == null) {
            nextViewModel = new NextViewModel(textArea.textProperty().get());
        }
    }

    @FXML
    public void onClose(final ActionEvent event) {
        persistenceService.save(new File(NEXT_PATH), nextViewModel);

        nextCallback.accept(null);
    }

    @FXML
    public void onDelete(final ActionEvent event) {
        textArea.textProperty().set("");
    }

    @FXML
    public void onAbout(final ActionEvent event) {
        final Alert alert = new Alert(AlertType.NONE, "Application", ButtonType.OK);
        alert.showAndWait();
    }

    @Override
    public void setNextCallback(final Consumer<Void> nextCallback) {
        this.nextCallback = Objects.requireNonNull(nextCallback);
    }
}
