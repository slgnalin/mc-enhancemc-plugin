package org.slgnalin.enhance.command;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slgnalin.enhance.manager.TeleportRequestManager;

import java.util.List;

/**
 * <p>
 * Handles the command responsible for registering teleport requests
 * </p>
 *
 * @see TeleportRequestManager
 */
public class TeleportRequestCommand extends AbstractPlayerCommand implements TabExecutor {

    private final TeleportRequestManager teleportRequestManager;

    public TeleportRequestCommand(TeleportRequestManager teleportRequestManager) {
        this.teleportRequestManager = teleportRequestManager;
    }

    @Override
    public boolean handleCommand(@NotNull Player player, @NotNull Command command, @NotNull String commandAlias, @NotNull String[] args) {
        if (args.length == 0) {
            return false;
        }

        final String targetPlayerName = args[0];

        if (player.getName().equals(targetPlayerName)) {
            player.sendMessage(Component.text("You can not send a teleport request to yourself", NamedTextColor.RED));
            return true;
        }

        final Player targetPlayer = Bukkit.getServer().getPlayerExact(targetPlayerName);

        if (targetPlayer == null) {
            final Component message = Component.text("The teleport request could not be sent to the target player", NamedTextColor.RED);
            player.sendMessage(message);

            return true;
        }

        if (!targetPlayer.isOnline()) {
            final Component message = Component.text("Player is not online", NamedTextColor.RED);
            player.sendMessage(message);

            return true;
        }

        teleportRequestManager.sendRequest(player, targetPlayer);

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