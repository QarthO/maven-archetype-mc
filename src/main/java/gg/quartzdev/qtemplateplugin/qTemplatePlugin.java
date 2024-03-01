package gg.quartzdev.qtemplateplugin;

import gg.quartzdev.qtemplateplugin.listeners.ExampleListener;
import gg.quartzdev.qtemplateplugin.util.QPlugin;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class qTemplatePlugin extends JavaPlugin {

    public static qTemplatePlugin instance;

    @Override
    public void onEnable() {
        instance = this;
        new QPlugin(instance);
//        setupMetrics(2133);
        registerListeners();
    }

    @Override
    public void onDisable() {

    }

    public void setupMetrics(int pluginId){
        Metrics metrics = new Metrics(instance, pluginId);
    }

    public void registerListeners(){
        Bukkit.getPluginManager().registerEvents(new ExampleListener(), instance);
    }

}
