package ${package};

import org.bukkit.plugin.java.JavaPlugin;

public final class ${artifactId} extends JavaPlugin
{

    @Override
    public void onEnable()
    {
//        Lets a class be serializable for easing saving of an object
//        ConfigurationSerialization.registerClass(PUT_CLASS_HERE.class);

//        Enables the plugin
        ${artifactId}API.enable(this, -1);

    }

    @Override
    public void onDisable()
    {
        ${artifactId}API.disable();
    }
}