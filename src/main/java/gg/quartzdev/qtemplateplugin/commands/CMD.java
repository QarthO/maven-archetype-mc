package gg.quartzdev.qtemplateplugin.commands;

import gg.quartzdev.qtemplateplugin.util.QPerm;
import gg.quartzdev.qtemplateplugin.util.Sender;
import org.bukkit.command.CommandSender;

public class CMD extends QCommand{
    public CMD(String name, QPerm permissionGroup) {
        super(name, permissionGroup);
    }

    @Override
    public boolean logic(CommandSender sender, String label, String[] args) {

        Sender.message(sender, "cool stuff");

        return true;
    }

    @Override
    public Iterable<String> tabCompletionLogic(CommandSender sender, String[] args) {
        return null;
    }
}
