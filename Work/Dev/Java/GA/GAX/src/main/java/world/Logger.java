package world;

import log.FileLogger;
import log.Log;

public enum Logger {

    INSTANCE;

    private Log logger = new FileLogger();

    public Log getLogger() {
        return logger;
    }
}
