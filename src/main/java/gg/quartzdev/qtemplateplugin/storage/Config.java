package gg.quartzdev.qtemplateplugin.storage;

import gg.quartzdev.lib.qlibpaper.storage.QConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Config extends QConfiguration {
    public Config(JavaPlugin plugin, String fileName) {
        super(plugin, fileName);
    }

    @Override
    public void loadAllData() {

    }

    @Override
    public void saveAllData() {

    }
}
