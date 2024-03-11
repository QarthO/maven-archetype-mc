package gg.quartzdev.qtemplateplugin.util;

import gg.quartzdev.qtemplateplugin.listeners.ExampleListener;
import gg.quartzdev.qtemplateplugin.QTemplatePlugin;
import gg.quartzdev.qtemplateplugin.storage.Config;
import gg.quartzdev.qtemplateplugin.storage.QConfiguration;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;

public class QPlugin {

    public static QPlugin instance;
    private static QTemplatePlugin javaPlugin;
    private static QConfiguration config;
    private boolean selfDisabled;

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
            QLogger.error(Messages.ERROR_PLUGIN_ENABLE);
            return;
        }
        instance = new QPlugin(plugin, useConfig, bStatsPluginId);
    }

    public static void disable(){
        final boolean isStopping = Bukkit.getServer().isStopping();
        if(!isStopping && !instance.selfDisabled){
            QLogger.warning(Messages.PLUGIN_UNSAFE_DISABLE);
        }

        QLogger.info(Messages.PLUGIN_DISABLE);
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
            QLogger.error(Messages.ERROR_CREATE_FILE.parse("file", "Plugin Data Folder"));
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
