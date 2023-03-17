package dev.mayaqq.crackstone.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.*;

public class CrackstoneConfig {
    public static Config CONFIG = new Config();

    static File configFile = new File(FabricLoader.getInstance().getConfigDir() + "/crackstone.json");
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void load() {
        if (!configFile.exists()) {
            try {
                configFile.createNewFile();
                save();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                CONFIG = gson.fromJson(new FileReader(configFile), Config.class);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void save() {
        try {
            FileWriter writer = new FileWriter(configFile);
            gson.toJson(CONFIG, writer);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static class Config {
        public boolean randomCrackstone = true;
        public int hardnessMultiplier = 3;

        public Config() {}
    }

}
