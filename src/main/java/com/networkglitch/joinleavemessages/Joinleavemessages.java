package com.networkglitch.joinleavemessages;

import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("joinleavemessages")
public class Joinleavemessages {

    public static Logger LOGGER = LogManager.getLogger();

    public static final String MOD_NAME = "JoinLeaveMessages";


    public Joinleavemessages() {
        log(Level.INFO, "Initializing...");
    }

    public static void log(Level level, String message) {
        LOGGER.log(level, "[" + MOD_NAME + "] " + message);
    }
}