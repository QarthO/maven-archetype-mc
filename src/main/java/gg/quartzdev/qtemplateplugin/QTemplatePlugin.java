package gg.quartzdev.qtemplateplugin;

import org.bukkit.plugin.java.JavaPlugin;

public final class QTemplatePlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        TemplateAPI.enable(this, -1);

    }

    @Override
    public void onDisable() {
        TemplateAPI.disable();
    }
}