package org.slgnalin.essentials.command;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slgnalin.essentials.manager.TeleportRequestManager;

import java.util.List;

/**
 * Handles the command responsible for registering teleport requests
 *
 * @see TeleportRequestManager
 */
public class TeleportRequestCommand implements CommandExecutor, TabExecutor {

    private final TeleportRequestManager teleportRequestManager;

    public TeleportRequestCommand(TeleportRequestManager teleportRequestManager) {
        this.teleportRequestManager = teleportRequestManager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {

        if (!(commandSender instanceof Player sourcePlayer)) {
            commandSender.sendMessage("This command can only be executed by a player");
            return true;
        }

        final String targetPlayerName = args[0];

        if (commandSender.getName().equals(targetPlayerName)) {
            commandSender.sendMessage(Component.text("You can not send a teleport request to yourself", NamedTextColor.RED));
            return true;
        }

        final Player targetPlayer = Bukkit.getServer().getPlayerExact(targetPlayerName);

        if (targetPlayer == null) {
            final Component message = Component.text("The teleport request could not be sent to the target player", NamedTextColor.RED);
            sourcePlayer.sendMessage(message);

            return true;
        }

        if (!targetPlayer.isOnline()) {
            final Component message = Component.text("Player is not online", NamedTextColor.RED);
            sourcePlayer.sendMessage(message);

            return true;
        }

        teleportRequestManager.sendRequest(sourcePlayer, targetPlayer);

        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return Bukkit.getOnlinePlayers()
                .stream()
                .map(Player::getName)
                .toList();
    }

}