package org.fx.services.implementation;

import org.fx.services.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleLoginService implements LoginService {
    private static final Logger logger = LoggerFactory.getLogger(SimpleLoginService.class);

    @Override
    public String login(final String login, final String password) {
        if ((password != null) && (password.trim().length() > 0)) {
            logger.info("{} logged in successfully", login);
            return String.format("%s logged in successfully", login);
        }

        logger.info("{} failed to login", login);
        return String.format("%s failed to login", login);
    }
}
