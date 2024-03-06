package gg.quartzdev.qtemplateplugin;

import gg.quartzdev.qtemplateplugin.util.QPlugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class qTemplatePlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        QPlugin.enable(this, true, -1);
    }

    @Override
    public void onDisable() {
        QPlugin.disable();
    }
}
