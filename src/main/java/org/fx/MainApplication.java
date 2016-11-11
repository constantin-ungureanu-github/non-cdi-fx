package org.fx;

import javafx.application.Application;
import javafx.stage.Stage;

import org.fx.loaders.ApplicationLoader;

public class MainApplication extends Application {
    public static void main(final String[] args) {
        launch(MainApplication.class);
    }

    @Override
    public void start(final Stage primaryStage) {
        new ApplicationLoader(primaryStage).load();
    }
}
