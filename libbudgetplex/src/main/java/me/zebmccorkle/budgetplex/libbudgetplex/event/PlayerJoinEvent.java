package me.zebmccorkle.budgetplex.libbudgetplex.event;

import me.zebmccorkle.budgetplex.libbudgetplex.Game;
import org.bukkit.entity.Player;

public class PlayerJoinEvent {

  private Player player;

  /**
   * Create an event to pass to {@link Game#onPlayerJoin(PlayerJoinEvent)}.
   *
   * @param player Player that has joined
   */
  public PlayerJoinEvent(Player player) {
    this.player = player;
  }

  /**
   * Get the player that has joined.
   *
   * @return Player that has joined
   */
  public Player getPlayer() {
    return player;
  }
}
