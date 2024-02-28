package gg.quartzdev.qtemplateplugin.util;

import gg.quartzdev.qtemplateplugin.qTemplatePlugin;
import gg.quartzdev.qtemplateplugin.storage.QConfiguration;

public class QPlugin {

    private static qTemplatePlugin instance;
    private QConfiguration config;
//    private

    public QPlugin(qTemplatePlugin plugin){
        instance = plugin;
    }

    public static qTemplatePlugin getInstance(){
        return instance;
    }

    @SuppressWarnings("UnstableApiUsage")
    public static String getVersion(){
        return getInstance().getPluginMeta().getVersion();
    }

    public static String getName(){
        return getInstance().getName();
    }

    public void setupPluginConfig(){
        config = new QConfiguration("config.yml");
    }

    public QConfiguration qConfig(){
//        config = new YamlConfiguration()
        return null;
    }

}
