package gg.quartzdev.qtemplateplugin.util;

import gg.quartzdev.qtemplateplugin.listeners.ExampleListener;
import gg.quartzdev.qtemplateplugin.QTemplatePlugin;
import gg.quartzdev.qtemplateplugin.storage.Config;
import gg.quartzdev.qtemplateplugin.storage.QConfiguration;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;

import java.io.IOException;

public class QPlugin {

    public static QPlugin instance;
    private static QTemplatePlugin javaPlugin;
    private static QConfiguration config;

    public static QTemplatePlugin getPlugin(){
        return javaPlugin;
    }
//    private

    private QPlugin(QTemplatePlugin plugin, boolean useConfig, int bStatsPluginId){
        javaPlugin = plugin;
        if(useConfig){
            setupPluginConfig();
        }

        if(bStatsPluginId > 0){
            setupMetrics(bStatsPluginId);
        }

        registerListeners();
    }

    public static void enable(QTemplatePlugin plugin, boolean useConfig, int bStatsPluginId){
        if(instance != null){
            QLogger.error("Error: Plugin already enabled");
            return;
        }
        instance = new QPlugin(plugin, useConfig, bStatsPluginId);
    }

    public static void disable(){
        QLogger.info("Disabling...");
        instance = null;
        javaPlugin = null;
        config = null;

//        Put logic to stop any async tasks
    }

    public void setupMetrics(int pluginId){
        Metrics metrics = new Metrics(javaPlugin, pluginId);
    }


    @SuppressWarnings("UnstableApiUsage")
    public static String getVersion(){
        return javaPlugin.getPluginMeta().getVersion();
    }

    public static String getName(){
        return javaPlugin.getName();
    }

    private void createDataFolder(){
        try{
            javaPlugin.getDataFolder().mkdirs();
        } catch(SecurityException exception){
            QLogger.error("oops data folder whoopsies :c");
        }
    }

    public void setupPluginConfig(){
        createDataFolder();
        config = new Config("config.yml");
    }

    public void registerListeners(){
        Bukkit.getPluginManager().registerEvents(new ExampleListener(), javaPlugin);
    }

}
