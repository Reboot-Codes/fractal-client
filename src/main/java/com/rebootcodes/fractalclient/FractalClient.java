package com.rebootcodes.fractalclient;

import com.rebootcodes.fractalclient.services.config.Config;
import com.rebootcodes.fractalclient.services.eventbus.EventBus;
import com.rebootcodes.fractalclient.services.modules.Modules;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.fabricmc.loader.api.metadata.ModMetadata;
import net.minecraft.client.MinecraftClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.File;
import java.util.Optional;

public class FractalClient implements ClientModInitializer {
    public static final String MOD_ID = "fractal-client";
    public static final ModMetadata MOD_META;
    public static final String NAME;
    public static final String VERSION;
    public static final Logger LOG;
    public static final File FOLDER = FabricLoader.getInstance().getGameDir().resolve(MOD_ID).toFile();
    public static MinecraftClient mc;
    public static EventBus EVENT_BUS = new EventBus();
    public static Modules MODULES = new Modules();
    public static Config CONFIG;
    public Boolean ready = false;
    public Boolean externalBaritoneIsPresent = false;

    static {
        MOD_META = FabricLoader.getInstance().getModContainer(MOD_ID).orElseThrow().getMetadata();
        NAME = MOD_META.getName();
        LOG = LogManager.getLogger(NAME);

        String versionStr = MOD_META.getVersion().getFriendlyString();
        if (versionStr.contains("-")) versionStr = versionStr.split("-")[0];
        if (versionStr.equals("${version}")) versionStr = "0.0.0";
        VERSION = versionStr;
    }

    @Override
    public void onInitializeClient() {
        LOG.info("Initializing " + NAME + " v" + VERSION + "...");
        // Centralized client accessor.
        mc = MinecraftClient.getInstance();

        // Create the data folder if it doesn't exist yet.
        if (!FOLDER.exists()) {
            FOLDER.getParentFile().mkdirs();
            if (FOLDER.getParentFile().exists()) {
                FOLDER.mkdir();
                if (!FOLDER.exists()) {
                    LOG.error("Could not create data-folder!");
                }
            } else {
                LOG.error("Could not create data folder parent!");
            }
        }
        // Initialize the config service.
        CONFIG = new Config(!FOLDER.exists(), FOLDER.exists() ? FOLDER : null);

        Optional<ModContainer> externalBaritone = FabricLoader.getInstance().getModContainer("baritone");
        if (externalBaritone.isPresent()) {
            externalBaritoneIsPresent = true;
            LOG.info("Disabling baritone command handler as baritone is installed separately.");
        } else {
            // TODO: Enable baritone command handler.
        }

        // TODO: Initialize plugins

        LOG.info("Initialized " + NAME + " with " + MODULES.getModules().size() + " module(s)!");
        ready = true;
    }
}
