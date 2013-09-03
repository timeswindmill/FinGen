package world;

import log.FileLogger;
import log.Log;

public enum Logger {

    INSTANCE;

    private final Log logger = new FileLogger();

    public Log getLogger() {
        return logger;
    }
}
