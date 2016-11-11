package org.fx.loaders;

import javafx.stage.Stage;

import org.fx.services.LoginService;
import org.fx.services.PersistenceService;
import org.fx.services.implementation.SimpleLoginService;
import org.fx.services.implementation.SimplePersistenceService;
import org.fx.transition.EventType;
import org.fx.transition.TransitionFlow;
import org.fx.transition.implementation.SimpleTransitionEvent;
import org.fx.transition.implementation.SimpleTransitionFlow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApplicationLoader {
    private static final Logger logger = LoggerFactory.getLogger(ApplicationLoader.class);

    private final LoginService loginService;
    private final PersistenceService persistenceService;
    private final TransitionFlow transitionFlow;
    private final Stage stage;

    public ApplicationLoader(final Stage stage) {
        loginService = new SimpleLoginService();
        persistenceService = new SimplePersistenceService();
        transitionFlow = new SimpleTransitionFlow(loginService, persistenceService, stage);
        this.stage = stage;
    }

    public void load() {
        logger.info("Loading Application");
        stage.setTitle("Application");

        transitionFlow.postEvent(new SimpleTransitionEvent(EventType.LOGIN));
    }
}
