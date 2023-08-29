package com.rebootcodes.fractalclient.services.config;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.rebootcodes.fractalclient.FractalClient;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Config {
    public Boolean EPHEMERAL;
    public File FOLDER;
    public static final String FILE_NAME = "config.json";
    public File FILE;
    private static final Logger LOG = LogManager.getLogger(FractalClient.NAME + "/Config");

    public Config(Boolean dataIsEphemeral, File dataFolder) {
        this.EPHEMERAL = dataIsEphemeral;
        if (dataIsEphemeral) {
            LOG.warn("Data folder could not be created, all data will be ephemeral!");
        } else {
            this.FOLDER = dataFolder;
            initFolder();
        }
    }

    public void initFolder() {
        if (!EPHEMERAL) {
            File[] confFileArr = this.FOLDER.listFiles((dir, name) -> Objects.equals(name, FILE_NAME));
            if (confFileArr != null && confFileArr.length > 0) {
                this.FILE = confFileArr[0];
                loadData();
            } else {
                try {
                    File confFile = new File(this.FOLDER.getPath(), FILE_NAME);
                    boolean confFileCreated = confFile.createNewFile();
                    if (confFileCreated) {
                        this.FILE = confFile;
                        setDefaults();
                        loadData();
                    }
                } catch (IOException err) {
                    LOG.error("Could not create \".minecraft/{}/{}\"! Setting config service to ephemeral mode.",
                            this.FOLDER.getName(), FILE_NAME);
                    LOG.debug(err);
                    setEphemeral(true, false);
                }
            }
        }
    }

    public void setDefaults() {
        try {
            Files.write(Path.of(FILE.getPath()), "{\n  \"schema\": \"v1.0.0\"\n}\n".getBytes());
        } catch (IOException err) {
            LOG.error("Could not write to config file! Setting to ephemeral mode...");
            LOG.debug(err);
            setEphemeral(true);
        }
    }

    public void loadData() {
        try {
            try {
                StringBuilder confFileStr = new StringBuilder();
                for (String line : Files.readAllLines(Path.of(FILE.getPath()))) {
                    confFileStr.append(line);
                }
                JsonObject confFileObj = JsonParser.parseString(confFileStr.toString()).getAsJsonObject();
                LOG.debug(confFileObj);

                // TODO: Deserialize `confFileObj`!
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (JsonSyntaxException err) {
            try {
                LOG.error(
                        "There was a JSON error! Is the file corrupt? Making a backup and creating the default file.");
                Files.move(Path.of(FILE.getPath()), Path.of(FOLDER.getPath() + "/" + FILE_NAME.split("\\.")[0]
                        + "-broken-" + new SimpleDateFormat("yyyy-MM-dd'T'HH-mm-ss").format(new Date()) + ".json"));
                FILE = null;
                initFolder();
            } catch (IOException err2) {
                LOG.error("Could not move bad config! Setting to ephemeral mode...");
                LOG.debug(err2);
                setEphemeral(true);
            }
        }
    }

    public void setEphemeral(Boolean mode, Boolean verbose) {
        if (mode) {
            this.EPHEMERAL = true;
            if (verbose)
                LOG.warn("Config service set to ephemeral mode!");
        } else {
            this.EPHEMERAL = false;
            initFolder();
            if (verbose)
                LOG.info("Config service set to folder mode!");
        }
    }

    public void setEphemeral(Boolean mode) {
        setEphemeral(mode, true);
    }
}
