package org.slgnalin.essentials.manager;

import org.bukkit.entity.Player;

/**
 * Defines the methods for managing teleport requests between players
 */
public interface TeleportRequestManager {

    /**
     * Registers a teleport request from one player to another
     *
     * @param sourcePlayer the player who initiates the teleport request
     * @param targetPlayer the player who is the target of the teleport request
     */
    void sendRequest(Player sourcePlayer, Player targetPlayer);

    /**
     * Cancels all active (outgoing) teleport requests for the specified player
     *
     * @param targetPlayer the player for which to cancel the requests
     */
    void cancelRequests(Player targetPlayer);

    /**
     * Accepts the most recent active (incoming) teleport request for the specified target player
     *
     * @param targetPlayer the target player for which to accept the request
     */
    void acceptRequest(Player targetPlayer);

    /**
     * Denies the most recent active (incoming) teleport request for the specified target player
     *
     * @param targetPlayer the target player for which to deny the request
     */
    void denyRequest(Player targetPlayer);

}
