package ${package};

import com.zaxxer.hikari.HikariConfig;
import gg.quartzdev.lib.qlibpaper.QLogger;
import gg.quartzdev.lib.qlibpaper.QPluginAPI;
import gg.quartzdev.lib.qlibpaper.commands.QCommandMap;
import gg.quartzdev.lib.qlibpaper.lang.GenericMessages;
import gg.quartzdev.metrics.bukkit.Metrics;
import ${package}.commands.CMD;
{package}.commands.CMDreload;
{package}.listeners.ExampleListener;
{package}.listeners.UpdateCheckerListener;
{package}.storage.ConfigPath;
{package}.storage.YMLconfig;
{package}.util.Messages;
import org.bukkit.Bukkit;

import java.util.List;

@SuppressWarnings("FieldCanBeLocal")
public class ${artifactId}API implements QPluginAPI
{
    private static ${artifactId}API apiInstance;
    private static ${artifactId} pluginInstance;
    private static Messages messages;
    private static QCommandMap commandMap;
    private static gg.quartzdev.metrics.bukkit.Metrics metrics;
    private static YMLconfig config;
    private final String CONSOLE_PREFIX = "<white>[<red>q<aqua>${artifactId}<white>]";
    private final String CHAT_PREFIX = "<red>q<aqua>${artifactId} <bold><gray>>></bold>";
    private final String MODRINTH_SLUG = "${artifactId}".toLowerCase();
    private final String MODRINTH_LOADER = "paper";

    private ${artifactId}API()
    {
    }

    private ${artifactId}API(${artifactId} plugin, int bStatsPluginId)
    {

//        Used to get plugin instance in other classes
        pluginInstance = plugin;

//        Initializes custom logger
        QLogger.init(pluginInstance.getComponentLogger());

//        Loads custom messages defined in messages.yml
        setupMessages();

//        Sets up bStats metrics
        if (bStatsPluginId > 0)
        {
            setupMetrics(bStatsPluginId);
        }

//        Sets up config.yml
        setupConfig();

//        Initializes bukkit event listeners
        registerListeners();

//        Registers all commands
        registerCommands();
    }

    public static ${artifactId} getPlugin()
    {
        return pluginInstance;
    }

    public static YMLconfig getConfig()
    {
        return config;
    }

    @SuppressWarnings("SameParameterValue")
    protected static void enable(${artifactId} plugin, int bStatsPluginId)
    {
        if (apiInstance != null)
        {
            QLogger.error(GenericMessages.ERROR_PLUGIN_ENABLE);
            return;
        }
        apiInstance = new ${artifactId}API(plugin, bStatsPluginId);
    }

    protected static void disable()
    {

//        Logs plugin is being disabled
        QLogger.info(GenericMessages.PLUGIN_DISABLE);

//        Clears instances
        apiInstance = null;
        pluginInstance = null;
        config = null;
        if (commandMap != null)
        {
            commandMap.unregisterAll();
            commandMap = null;
        }
        if (metrics != null)
        {
            metrics.shutdown();
            metrics = null;
        }

        HikariConfig config = new HikariConfig();
        String host = "localhost";
        int port = 3306;
        String name = "test";
        String username = "root";
        String password = "test";
        config.setJdbcUrl("jdbc:mysql://" + host + ":" + port + "/" + name);
        config.setUsername(username);
        config.setPassword(password);

//        Stops async tasks
//        ...
    }

    @SuppressWarnings("UnstableApiUsage")
    public static String getVersion()
    {
        return pluginInstance.getPluginMeta().getVersion();
    }

    public static String getName()
    {
        return pluginInstance.getName();
    }

    public static void loadCustomMessages()
    {
        messages.reload();
    }

    public void setupMetrics(int pluginId)
    {
        metrics = new Metrics(pluginInstance, pluginId);
    }

    public void registerCommands()
    {
        commandMap = new QCommandMap();
        String label = "qclaimblocks";
        commandMap.create(label, new CMD("", QPerm.GROUP_PLAYER), List.of("claimblocks", "cb"));
        commandMap.addSubCommand(label, new CMDreload("reload", QPerm.GROUP_ADMIN));
    }

    public void registerListeners()
    {
        Bukkit.getPluginManager().registerEvents(new ExampleListener(), pluginInstance);
        if (config.get(ConfigPath.CHECK_UPDATES, true))
        {
            setupUpdater(MODRINTH_SLUG, MODRINTH_LOADER);
        }
    }

    public void setupConfig()
    {
        config = new YMLconfig(pluginInstance, "config.yml");
    }

    public void setupMessages()
    {
        messages = new Messages(CONSOLE_PREFIX, CHAT_PREFIX);
    }

    public void setupUpdater(String slug, String loader)
    {
        Bukkit.getPluginManager().registerEvents(new UpdateCheckerListener(slug, loader), pluginInstance);
    }

}
