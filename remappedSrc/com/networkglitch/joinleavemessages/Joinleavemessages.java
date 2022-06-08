package com.networkglitch.joinleavemessages;

import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Joinleavemessages implements ModInitializer {

    public static Logger LOGGER = LogManager.getLogger();

    public static final String MOD_NAME = "JoinLeaveMessages";

    @Override
    public void onInitialize() {
        log(Level.INFO, "Initializing mode, registering commands");
    }

    public static void log(Level level, String message) {
        LOGGER.log(level, "[" + MOD_NAME + "] " + message);
    }

    public static boolean isNotNull(Object o) {
        return o != null;
    }

    public static boolean isNull(Object o) {
        return o == null;
    }
}