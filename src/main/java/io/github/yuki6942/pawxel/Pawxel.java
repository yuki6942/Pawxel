package io.github.yuki6942.pawxel;


import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Pawxel implements ModInitializer, ClientModInitializer {


    public static final String name  = "Pawxel";
    public static final String version = "0.0.1";
    public static final int color = 0xFBA0E3;
    public static final Logger LOGGER = LoggerFactory.getLogger("Pawxel");

    @Override public void onInitialize() {
        LOGGER.info ("Starting " + name + " v" + version);
    }

    @Override public void onInitializeClient() {}

}
