package run.tere.plugin.mineboss.configs;

import org.bukkit.configuration.file.FileConfiguration;
import run.tere.plugin.mineboss.MineBoss;

import java.util.ArrayList;
import java.util.List;

public class ConfigHandler {
    private List<String> worldList;

    public ConfigHandler() {
        initConfig();
    }

    private void initConfig() {
        MineBoss.getPlugin().saveDefaultConfig();
        FileConfiguration config = MineBoss.getPlugin().getConfig();
        this.worldList = new ArrayList<>();
        this.worldList.addAll(config.getStringList("DisableWorlds"));
    }

    public List<String> getWorldList() {
        return worldList;
    }
}
