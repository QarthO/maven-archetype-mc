package gg.quartzdev.qtemplateplugin;

import org.bukkit.plugin.java.JavaPlugin;

public final class qTemplatePlugin extends JavaPlugin {

    public static qTemplatePlugin instance;

    @Override
    public void onEnable() {
        instance = this;

    }

    @Override
    public void onDisable() {

    }
}
