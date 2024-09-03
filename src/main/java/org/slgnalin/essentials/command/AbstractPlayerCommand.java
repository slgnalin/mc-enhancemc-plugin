package org.slgnalin.essentials.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * <p>
 * Provides a base implementation for commands that can only be executed by a {@link Player}
 * </p>
 *
 * <p>
 * Implements the {@link CommandExecutor} interface and includes a common check
 * to ensure the command sender is a {@link Player} before delegating the
 * actual command handling to another method
 * </p>
 *
 * <p>
 * Subclasses must implement
 * the {@link #handleCommand(Player, Command, String, String[])} method
 * to define the specific behavior of the command
 * </p>
 */
public abstract class AbstractPlayerCommand implements CommandExecutor {


    /**
     * <p>
     * Executes the command, ensuring that the command sender is a {@link Player}
     * </p>
     *
     * <p>
     * If the command sender <i>is not</i> a {@link Player}:
     * <uL>
     * <li>A message is sent to the {@link CommandSender} indicating that the command can only be executed by a {@link Player}</li>
     * <li>The command execution is interrupted and {@code true} is returned</li>
     * </uL>
     *
     * <p>
     * If the command sender <i>is</i> a {@link Player},
     * the command is passed to the {@link #handleCommand(Player, Command, String, String[])} method
     * for further processing
     * </p>
     *
     * @param commandSender the sender of the command
     * @param command       the command that was executed
     * @param commandAlias  the alias used for the command
     * @param args          the arguments passed with the command
     *
     * @return <ul>
     * <li>{@code true} if the "usage" {@code plugin.yml} entry for this command (if defined) <i>should not</i> be sent to the {@link CommandSender}</li>
     * <li>{@code false} if the "usage" {@code plugin.yml} entry for this command (if defined) <i>should</i> be sent to the {@link CommandSender}</li>
     * </ul>
     */
    public final boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String commandAlias, @NotNull String[] args) {

        if (!(commandSender instanceof Player player)) {
            commandSender.sendMessage("This command can only be executed by a player");
            return true;
        }

        return handleCommand(player, command, commandAlias, args);
    }

    /**
     * <p>
     * Handles the command logic for {@link Player} specific commands
     * </p>
     *
     * <p>
     * This method must be implemented by subclasses to define the specific behavior of the command.
     * </p>
     *
     * <p>This command is <i>only</i> called only if the command sender is a {@link Player}</p>
     *
     * @param player       the {@link Player} who executed the command
     * @param command      the command that was executed
     * @param commandAlias the alias used for the command
     * @param args         the arguments passed with the command
     *
     * @return <ul>
     * <li>{@code true} if the "usage" {@code plugin.yml} entry for this command (if defined) <i>should not</i> be sent to the {@link Player}</li>
     * <li>{@code false} if the "usage" {@code plugin.yml} entry for this command (if defined) <i>should</i> be sent to the {@link Player}</li>
     * </ul>
     */
    public abstract boolean handleCommand(@NotNull Player player, @NotNull Command command, @NotNull String commandAlias, @NotNull String[] args);

}