package org.slgnalin.essentials.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public abstract class AbstractPlayerCommand implements CommandExecutor {


    public final boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String commandAlias, @NotNull String[] args) {

        if (!(commandSender instanceof Player player)) {
            commandSender.sendMessage("This command can only be executed by a player");
            return true;
        }

        return handleCommand(player, command, commandAlias, args);
    }

    public abstract boolean handleCommand(@NotNull Player player, @NotNull Command command, @NotNull String commandAlias, @NotNull String[] args);

}