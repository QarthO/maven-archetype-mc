package gg.quartzdev.qtemplateplugin.commands;

import gg.quartzdev.lib.qlibpaper.QPerm;
import gg.quartzdev.lib.qlibpaper.Sender;
import gg.quartzdev.lib.qlibpaper.commands.QCommand;
import gg.quartzdev.qtemplateplugin.QTemplateAPI;
import gg.quartzdev.qtemplateplugin.QTemplatePlugin;
import org.bukkit.command.CommandSender;

public class CMD extends QCommand {
    public CMD(String commandName, QPerm permissionGroup) {
        super(commandName, permissionGroup);
    }

    @Override
    public boolean logic(CommandSender commandSender, String s, String[] strings) {
        Sender.message(commandSender, "<green>" + QTemplateAPI.getName() + " v" + QTemplateAPI.getVersion());
        return true;
    }

    @Override
    public Iterable<String> tabCompletionLogic(CommandSender commandSender, String[] strings) {
        return null;
    }
}
