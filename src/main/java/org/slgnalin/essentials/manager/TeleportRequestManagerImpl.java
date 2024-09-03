package org.slgnalin.essentials.manager;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TeleportRequestManagerImpl implements TeleportRequestManager {

    private final Map<UUID, UUID> requests;

    public TeleportRequestManagerImpl() {
        this.requests = new HashMap<>();
    }

    @Override
    public void sendRequest(Player sourcePlayer, Player targetPlayer) {
        if (targetPlayer == null) {
            final Component message = Component.text("The teleport request could not be sent to the target player", NamedTextColor.RED);
            sourcePlayer.sendMessage(message);

            return;
        }

        if (!targetPlayer.isOnline()) {
            final Component message = Component.text("Player is not online", NamedTextColor.RED);
            sourcePlayer.sendMessage(message);

            return;
        }

        requests.put(targetPlayer.getUniqueId(), sourcePlayer.getUniqueId());

        sendRequestMessageToSourcePlayer(sourcePlayer, targetPlayer);
        sendRequestMessageToTargetPlayer(sourcePlayer, targetPlayer);

    }

    @Override
    public void cancelRequests(Player player) {
        requests.entrySet().removeIf(entry -> entry.getValue().equals(player.getUniqueId()));

        player.sendMessage(Component.text("All sent teleport request were canceled", NamedTextColor.WHITE));
    }

    @Override
    public void acceptRequest(Player targetPlayer) {
        final UUID sourcePlayerId = requests.get(targetPlayer.getUniqueId());

        if (sourcePlayerId == null) {
            final Component message = Component.text("You currently have no active incoming teleport requests", NamedTextColor.RED);
            targetPlayer.sendMessage(message);
            return;
        }

        final Player sourcePlayer = Bukkit.getPlayer(sourcePlayerId);

        if (sourcePlayer != null && sourcePlayer.isOnline()) {
            sourcePlayer.teleport(targetPlayer);

            requests.remove(targetPlayer.getUniqueId());

            sendAcceptMessageToSourcePlayer(sourcePlayer);
            sendAcceptMessageToTargetPlayer(sourcePlayer, targetPlayer);

        } else {
            final Component message = Component.text("The player who sent you this teleport request is no longer online", NamedTextColor.RED);
            targetPlayer.sendMessage(message);
        }

    }

    @Override
    public void denyRequest() {

    }

    private static void sendAcceptMessageToTargetPlayer(Player sourcePlayer, Player targetPlayer) {
        final Component targetPlayerMessage = Component.text("The teleport request from", NamedTextColor.WHITE)
                .append(Component.text(" " + sourcePlayer.getName() + " ", NamedTextColor.YELLOW))
                .append(Component.text("was accepted", NamedTextColor.WHITE))
                .append(Component.text("\nPlayer", NamedTextColor.WHITE))
                .append(Component.text(" " + sourcePlayer.getName() + " ", NamedTextColor.YELLOW))
                .append(Component.text("was teleported to you", NamedTextColor.WHITE));

        targetPlayer.sendMessage(targetPlayerMessage);
    }

    private static void sendAcceptMessageToSourcePlayer(Player sourcePlayer) {
        final Component sourcePlayerMessage = Component.text("Player " + sourcePlayer.getName() + " ", NamedTextColor.YELLOW)
                .append(Component.text("accepted your teleport request", NamedTextColor.WHITE))
                .append(Component.text("\nYou were teleported to", NamedTextColor.WHITE))
                .append(Component.text(" " + sourcePlayer.getName() + " ", NamedTextColor.YELLOW));

        sourcePlayer.sendMessage(sourcePlayerMessage);
    }

    private static void sendRequestMessageToSourcePlayer(Player sourcePlayer, Player targetPlayer) {
        final Component sourcePlayerMessage = Component.text("Teleport request sent to", NamedTextColor.WHITE)
                .append(Component.text(" " + targetPlayer.getName() + " ", NamedTextColor.YELLOW));

        Bukkit.getServer().getLogger().info("Sending message %s to player %s".formatted(sourcePlayerMessage, sourcePlayer));

        sourcePlayer.sendMessage(sourcePlayerMessage);
    }

    private static void sendRequestMessageToTargetPlayer(Player sourcePlayer, Player targetPlayer) {
        final Component targetPlayerMessage = Component.text("Player", NamedTextColor.WHITE)
                .append(Component.text(" " + sourcePlayer.getName() + " ", NamedTextColor.YELLOW))
                .append(Component.text("sent you a teleport request", NamedTextColor.WHITE))
                .append(Component.text("\nType", NamedTextColor.WHITE))
                .append(Component.text(" /tpaccept ", NamedTextColor.GREEN))
                .append(Component.text("to accept the request", NamedTextColor.WHITE))
                .append(Component.text("\nType", NamedTextColor.WHITE))
                .append(Component.text(" /tpdeny ", NamedTextColor.RED))
                .append(Component.text("to deny the request", NamedTextColor.WHITE));

        targetPlayer.sendMessage(targetPlayerMessage);
    }

}
