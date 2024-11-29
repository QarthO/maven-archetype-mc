package ${package}.commands;

import gg.quartzdev.lib.qlibpaper.Sender;
import gg.quartzdev.lib.qlibpaper.commands.QCommand;
import ${package}.${artifactId}API;
import org.bukkit.command.CommandSender;

public class CMD extends QCommand
{
    public CMD(String commandName, String permissionGroup)
    {
        super(commandName, permissionGroup);
    }

    @Override
    public boolean logic(CommandSender sender, String label, String[] args)
    {
        Sender.message(sender, "<green>" + ${artifactId}API.getName() + " v" + ${artifactId}API.getVersion());
        return true;
    }

    @Override
    public Iterable<String> tabCompletionLogic(CommandSender commandSender, String[] strings)
    {
        return null;
    }
}
