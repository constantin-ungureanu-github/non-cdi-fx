package org.fx.transition.implementation;

import javafx.scene.Scene;
import javafx.stage.Stage;

import org.fx.controller.Controller;
import org.fx.controller.LoginController;
import org.fx.controller.NextController;
import org.fx.services.LoginService;
import org.fx.services.PersistenceService;
import org.fx.transition.EventType;
import org.fx.transition.TransitionEvent;
import org.fx.transition.TransitionFlow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

public class SimpleTransitionFlow implements TransitionFlow {
    private static final Logger logger = LoggerFactory.getLogger(SimpleTransitionFlow.class);
    private final LoginService loginService;
    private final PersistenceService persistenceService;
    private final Stage stage;
    private final EventBus eventBus;

    public SimpleTransitionFlow(final LoginService loginService, final PersistenceService persistenceService, final Stage stage) {
        this.loginService = loginService;
        this.persistenceService = persistenceService;
        this.stage = stage;
        eventBus = new EventBus();
        eventBus.register(this);
    }

    @Subscribe
    public void handleEvent(final TransitionEvent event) {
        logger.info("Handle of {}", event);

        stage.hide();
        switch (event.getEventType()) {
        case LOGIN:
            final Controller loginController = new LoginController(loginService, persistenceService);
            stage.setScene(new Scene(loginController.load(LoginController.FXML).getRoot()));
            loginController.setNextCallback(controller -> postEvent(new SimpleTransitionEvent(EventType.NEXT)));
            break;
        case NEXT:
            final Controller nextController = new NextController(persistenceService);
            stage.setScene(new Scene(nextController.load(NextController.FXML).getRoot()));
            nextController.setNextCallback(controller -> postEvent(new SimpleTransitionEvent(EventType.LOGIN)));
            break;
        default:
            break;
        }
        stage.show();
    }

    @Override
    public void postEvent(final TransitionEvent event) {
        eventBus.post(event);
    }
}
