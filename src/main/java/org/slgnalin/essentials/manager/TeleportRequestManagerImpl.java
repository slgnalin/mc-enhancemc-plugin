package org.slgnalin.essentials.manager;

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

        player.sendMessage(MessageUtils.MSG_TP_REQUEST_CANCEL);
    }

    @Override
    public void acceptRequest(Player targetPlayer) {
        final UUID sourcePlayerId = requests.get(targetPlayer.getUniqueId());

        if (sourcePlayerId == null) {
            targetPlayer.sendMessage(MessageUtils.MSG_TP_NO_ACTIVE_REQUESTS_INCOMING);
            return;
        }

        final Player sourcePlayer = Bukkit.getPlayer(sourcePlayerId);

        if (sourcePlayer != null && sourcePlayer.isOnline()) {
            sourcePlayer.teleport(targetPlayer);

            requests.remove(targetPlayer.getUniqueId());

            MessageUtils.sendAcceptMessageToSourcePlayer(sourcePlayer);
            MessageUtils.sendAcceptMessageToTargetPlayer(sourcePlayer, targetPlayer);

        } else {
            targetPlayer.sendMessage(MessageUtils.MSG_TP_SOURCE_PLAYER_NO_LONGER_ONLINE);
        }

    }

    @Override
    public void denyRequest(Player targetPlayer) {
        final UUID sourcePlayerId = requests.get(targetPlayer.getUniqueId());

        if (sourcePlayerId == null) {
            targetPlayer.sendMessage(MessageUtils.MSG_TP_NO_ACTIVE_REQUESTS_INCOMING);
            return;
        }

        requests.remove(targetPlayer.getUniqueId());

        final Player sourcePlayer = Bukkit.getPlayer(sourcePlayerId);

        if (sourcePlayer != null && sourcePlayer.isOnline()) {
            MessageUtils.sendDenyMessageToTargetPlayer(targetPlayer);
            MessageUtils.sendDenyMessageToSourcePlayer(sourcePlayer, targetPlayer);
        } else {
            targetPlayer.sendMessage(MessageUtils.MSG_TP_SOURCE_PLAYER_NO_LONGER_ONLINE);
        }

    }

}
