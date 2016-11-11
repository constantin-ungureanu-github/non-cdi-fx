package org.fx.loaders;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

public final class CustomControlLoader {
    private CustomControlLoader() {
    }

    public static void load(final Node node, final String fxmlFile) {
        final FXMLLoader fxmlLoader = new FXMLLoader(node.getClass().getResource(fxmlFile));
        fxmlLoader.setRoot(node);
        fxmlLoader.setController(node);

        try {
            fxmlLoader.load();
        } catch (final IOException ioe) {
            throw new IllegalStateException("Cannot load FXML", ioe);
        }
    }
}
