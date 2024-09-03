package org.slgnalin.essentials.manager;

import org.bukkit.entity.Player;

/**
 * <p>
 * Defines the methods for managing teleport requests between players
 * </p>
 */
public interface TeleportRequestManager {

    /**
     * <p>
     * Registers a teleport request from one player to another
     * </p>
     *
     * @param sourcePlayer the player who initiates the teleport request
     * @param targetPlayer the player who is the target of the teleport request
     */
    void sendRequest(Player sourcePlayer, Player targetPlayer);

    /**
     * <p>
     * Cancels all active (outgoing) teleport requests for the specified player
     * </p>
     *
     * @param targetPlayer the player for which to cancel the requests
     */
    void cancelRequests(Player targetPlayer);

    /**
     * <p>
     * Accepts the most recent active (incoming) teleport request for the specified target player
     * </p>
     *
     * @param targetPlayer the target player for which to accept the request
     */
    void acceptRequest(Player targetPlayer);

    /**
     * <p>
     * Denies the most recent active (incoming) teleport request for the specified target player
     * </p>
     *
     * @param targetPlayer the target player for which to deny the request
     */
    void denyRequest(Player targetPlayer);

}
