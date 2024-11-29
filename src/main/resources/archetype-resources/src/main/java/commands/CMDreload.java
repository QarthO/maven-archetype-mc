package ${package}.commands;

import gg.quartzdev.lib.qlibpaper.Sender;
import gg.quartzdev.lib.qlibpaper.commands.QCommand;
import gg.quartzdev.lib.qlibpaper.lang.QPlaceholder;
import ${package}.${artifactId}API;
import ${package}.util.Messages;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.List;

public class CMDreload extends QCommand
{
    List<String> reloadableFiles = List.of("config", "messages", "transactions");

    public CMDreload(String commandName, String permissionGroup)
    {
        super(commandName, permissionGroup);
    }

    @Override
    public boolean logic(CommandSender sender, String label, String[] args)
    {
        Sender.message(sender, "<blue>Args[<yellow>" + args.length + "<blue>] <gray>- <red>" + Arrays.toString(args));
//        reload all configs
        reloadMessages(sender);
        return true;
    }

    @Override
    public Iterable<String> tabCompletionLogic(CommandSender sender, String[] args)
    {
        if (args.length == 2)
        {
            return reloadableFiles;
        }
        return null;
    }

    public void reloadConfig(CommandSender sender)
    {
        ${artifactId}API.getConfig().reload();
        Sender.message(sender, Messages.FILE_RELOAD.parse(QPlaceholder.FILE, "config"));
    }

    public void reloadMessages(CommandSender sender)
    {
        ${artifactId}API.loadCustomMessages();
        Sender.message(sender, Messages.FILE_RELOAD.parse(QPlaceholder.FILE, "messages"));
    }
}
