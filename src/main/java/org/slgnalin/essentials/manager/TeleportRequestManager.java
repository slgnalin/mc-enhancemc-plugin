package org.slgnalin.essentials.manager;

import org.bukkit.entity.Player;

public interface TeleportRequestManager {

    void sendRequest(Player sourcePlayer, Player targetPlayer);

    void cancelRequests(Player player);

    void acceptRequest(Player targetPlayer);

    void denyRequest();

}
