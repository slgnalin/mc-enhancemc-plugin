package org.slgnalin.essentials.util;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;

public class MessageUtils {

    private MessageUtils() {
        throw new IllegalArgumentException("This class cannot be instantiated");
    }

    public static void sendAcceptMessageToTargetPlayer(Player sourcePlayer, Player targetPlayer) {
        final Component targetPlayerMessage = Component.text("The teleport request from", NamedTextColor.WHITE)
                .append(Component.text(" " + sourcePlayer.getName() + " ", NamedTextColor.YELLOW))
                .append(Component.text("was accepted", NamedTextColor.WHITE))
                .append(Component.text("\nPlayer", NamedTextColor.WHITE))
                .append(Component.text(" " + sourcePlayer.getName() + " ", NamedTextColor.YELLOW))
                .append(Component.text("was teleported to you", NamedTextColor.WHITE));

        targetPlayer.sendMessage(targetPlayerMessage);
    }

    public static void sendAcceptMessageToSourcePlayer(Player sourcePlayer) {
        final Component sourcePlayerMessage = Component.text("Player " + sourcePlayer.getName() + " ", NamedTextColor.YELLOW)
                .append(Component.text("accepted your teleport request", NamedTextColor.WHITE))
                .append(Component.text("\nYou were teleported to", NamedTextColor.WHITE))
                .append(Component.text(" " + sourcePlayer.getName() + " ", NamedTextColor.YELLOW));

        sourcePlayer.sendMessage(sourcePlayerMessage);
    }

    public static void sendRequestMessageToSourcePlayer(Player sourcePlayer, Player targetPlayer) {
        final Component sourcePlayerMessage = Component.text("Teleport request sent to", NamedTextColor.WHITE)
                .append(Component.text(" " + targetPlayer.getName() + " ", NamedTextColor.YELLOW));

        sourcePlayer.sendMessage(sourcePlayerMessage);
    }

    public static void sendRequestMessageToTargetPlayer(Player sourcePlayer, Player targetPlayer) {
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
