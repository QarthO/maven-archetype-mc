package gg.quartzdev.qtemplateplugin.util;

import gg.quartzdev.qtemplateplugin.listeners.ExampleListener;
import gg.quartzdev.qtemplateplugin.qTemplatePlugin;
import gg.quartzdev.qtemplateplugin.storage.QConfiguration;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;

public class QPlugin {

    public static QPlugin instance;
    private static qTemplatePlugin javaPlugin;

    public static qTemplatePlugin getPlugin(){
        return javaPlugin;
    }
    private QConfiguration config;
//    private

    private QPlugin(qTemplatePlugin plugin, boolean useConfig, int bStatsPluginId){
        javaPlugin = plugin;
        if(useConfig){
            setupPluginConfig();
        }

        if(bStatsPluginId > 0){
            setupMetrics(bStatsPluginId);
        }

        registerListeners();
    }

    public static void enable(qTemplatePlugin plugin, boolean useConfig, int bStatsPluginId){
        if(instance != null){
            QLogger.error("Error: Plugin already enabled");
            return;
        }
        instance = new QPlugin(plugin, useConfig, bStatsPluginId);
    }

    public static void disable(){

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

    public void setupPluginConfig(){
        config = new QConfiguration("config.yml");
    }

    public void registerListeners(){
        Bukkit.getPluginManager().registerEvents(new ExampleListener(), javaPlugin);
    }

}
