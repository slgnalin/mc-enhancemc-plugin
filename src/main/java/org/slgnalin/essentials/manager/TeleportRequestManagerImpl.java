package org.slgnalin.essentials.manager;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.slgnalin.essentials.util.MessageUtils;

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
        requests.put(targetPlayer.getUniqueId(), sourcePlayer.getUniqueId());

        MessageUtils.sendRequestMessageToSourcePlayer(sourcePlayer, targetPlayer);
        MessageUtils.sendRequestMessageToTargetPlayer(sourcePlayer, targetPlayer);
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

            MessageUtils.sendAcceptMessageToSourcePlayer(sourcePlayer);
            MessageUtils.sendAcceptMessageToTargetPlayer(sourcePlayer, targetPlayer);

        } else {
            final Component message = Component.text("The player who sent you this teleport request is no longer online", NamedTextColor.RED);
            targetPlayer.sendMessage(message);
        }

    }

    @Override
    public void denyRequest(Player player) {
        final UUID sourcePlayerId = requests.get(player.getUniqueId());

        if (sourcePlayerId == null) {
            final Component message = Component.text("You currently have no active incoming teleport requests", NamedTextColor.RED);
            player.sendMessage(message);
            return;
        }

        requests.remove(player.getUniqueId());

        final Player sourcePlayer = Bukkit.getPlayer(sourcePlayerId);

        if (sourcePlayer != null && sourcePlayer.isOnline()) {
            final Component requestTargetPlayerMessage = Component.text("The teleport request from", NamedTextColor.WHITE)
                    .append(Component.text(" " + sourcePlayer.getName() + " ", NamedTextColor.YELLOW))
                    .append(Component.text("was denied", NamedTextColor.WHITE));

            player.sendMessage(requestTargetPlayerMessage);

            final Component requestSourcePlayerMessage =
                    Component.text("The teleport request you sent to", NamedTextColor.WHITE)
                            .append(Component.text(" " + player.getName() + " ", NamedTextColor.YELLOW))
                            .append(Component.text("was denied", NamedTextColor.WHITE));

            sourcePlayer.sendMessage(requestSourcePlayerMessage);
        } else {
            final Component message =
                    Component.text("The player who sent you this teleport request is no longer online", NamedTextColor.RED);
            player.sendMessage(message);
        }

    }

}
