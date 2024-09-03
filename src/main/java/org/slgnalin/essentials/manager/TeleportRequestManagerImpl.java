package org.slgnalin.essentials.manager;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.slgnalin.essentials.util.MessageUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * <p>
 * Manages teleport requests between players
 * </p>
 *
 * @see TeleportRequestManagerImpl#teleportRequests
 */
public class TeleportRequestManagerImpl implements TeleportRequestManager {

    /**
     * <p>
     * Stores the active teleport requests between players
     * </p>
     *
     * <p>
     * The key represents the {@code UUID} of the player who sent the request,
     * while the value represents the {@code UUID} of the player to whom the request was sent
     * </p>
     */
    private final Map<UUID, UUID> teleportRequests;

    public TeleportRequestManagerImpl() {
        this.teleportRequests = new HashMap<>();
    }

    /**
     * {@inheritDoc}
     *
     * @param sourcePlayer the player who initiates the teleport request
     * @param targetPlayer the player who is the target of the teleport request
     */
    @Override
    public void sendRequest(Player sourcePlayer, Player targetPlayer) {
        teleportRequests.put(targetPlayer.getUniqueId(), sourcePlayer.getUniqueId());

        MessageUtils.sendRequestMessageToSourcePlayer(sourcePlayer, targetPlayer);
        MessageUtils.sendRequestMessageToTargetPlayer(sourcePlayer, targetPlayer);
    }

    /**
     * {@inheritDoc}
     *
     * @param targetPlayer the player for which to cancel the requests
     */
    @Override
    public void cancelRequests(Player targetPlayer) {
        teleportRequests.entrySet().removeIf(entry -> entry.getValue().equals(targetPlayer.getUniqueId()));

        targetPlayer.sendMessage(MessageUtils.MSG_TP_REQUEST_CANCEL);
    }

    /**
     * {@inheritDoc}
     *
     * @param targetPlayer the target player for which to accept the request
     */
    @Override
    public void acceptRequest(Player targetPlayer) {
        final UUID sourcePlayerId = teleportRequests.get(targetPlayer.getUniqueId());

        if (sourcePlayerId == null) {
            targetPlayer.sendMessage(MessageUtils.MSG_TP_NO_ACTIVE_REQUESTS_INCOMING);
            return;
        }

        final Player sourcePlayer = Bukkit.getPlayer(sourcePlayerId);

        if (sourcePlayer != null && sourcePlayer.isOnline()) {
            sourcePlayer.teleport(targetPlayer);

            teleportRequests.remove(targetPlayer.getUniqueId());

            MessageUtils.sendAcceptMessageToSourcePlayer(sourcePlayer);
            MessageUtils.sendAcceptMessageToTargetPlayer(sourcePlayer, targetPlayer);

        } else {
            targetPlayer.sendMessage(MessageUtils.MSG_TP_SOURCE_PLAYER_NO_LONGER_ONLINE);
        }

    }

    /**
     * {@inheritDoc}
     *
     * @param targetPlayer the target player for which to deny the request
     */
    @Override
    public void denyRequest(Player targetPlayer) {
        final UUID sourcePlayerId = teleportRequests.get(targetPlayer.getUniqueId());

        if (sourcePlayerId == null) {
            targetPlayer.sendMessage(MessageUtils.MSG_TP_NO_ACTIVE_REQUESTS_INCOMING);
            return;
        }

        teleportRequests.remove(targetPlayer.getUniqueId());

        final Player sourcePlayer = Bukkit.getPlayer(sourcePlayerId);

        if (sourcePlayer != null && sourcePlayer.isOnline()) {
            MessageUtils.sendDenyMessageToTargetPlayer(targetPlayer);
            MessageUtils.sendDenyMessageToSourcePlayer(sourcePlayer, targetPlayer);
        } else {
            targetPlayer.sendMessage(MessageUtils.MSG_TP_SOURCE_PLAYER_NO_LONGER_ONLINE);
        }

    }

}
