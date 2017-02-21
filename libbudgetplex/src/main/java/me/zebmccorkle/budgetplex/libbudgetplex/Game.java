/*-----------------------------------------------------------------------------
 - Budgetplex                                                                 -
 - Copyright (C) 2017 Zebulon McCorkle                                        -
 -                                                                            -
 - This program is free software: you can redistribute it and/or modify       -
 - it under the terms of the GNU Affero General Public License as             -
 - published by the Free Software Foundation, either version 3 of the         -
 - License, or (at your option) any later version.                            -
 -                                                                            -
 - This program is distributed in the hope that it will be useful,            -
 - but WITHOUT ANY WARRANTY; without even the implied warranty of             -
 - MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the              -
 - GNU Affero General Public License for more details.                        -
 -                                                                            -
 - You should have received a copy of the GNU Affero General Public License   -
 - along with this program.  If not, see <http://www.gnu.org/licenses/>.      -
 -----------------------------------------------------------------------------*/

package me.zebmccorkle.budgetplex.libbudgetplex;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;


/**
 * An abstract class for games to inherit from. Provides all events needed to run a game.
 */
public abstract class Game {
  // Data and Constructor

  private String name;
  private Team[] teams;
  private Kit[] kits;

  private ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();
  private Scoreboard scoreboard = scoreboardManager.getNewScoreboard();
  private org.bukkit.scoreboard.Team[] scoreboardTeams;
  private Map<String, Integer> teamIndices = new HashMap<>();
  private Map<Player, String> teamOfPlayer = new HashMap<>();
  private Map<Player, Map<String, Object>> playerState = new HashMap<>();
  private boolean gameRunning = false;

  /**
   * Set the main properties of the game, should only be called as {@code super(...)}.
   *
   * @param name Game name, will be displayed to players
   * @param teams Teams players can choose from
   * @param kits Kits players can choose from
   */
  protected Game(String name, Team[] teams, Kit[] kits) {
    this.name = name;
    this.teams = teams;
    this.kits = kits;

    scoreboardTeams = (org.bukkit.scoreboard.Team[]) Arrays.stream(teams)
        .map(team -> {
          org.bukkit.scoreboard.Team scoreboardTeam = scoreboard.registerNewTeam(team.getName());
          scoreboardTeam.setPrefix(team.getNametagColor().toString());
          return scoreboardTeam;
        }).toArray();

    for (int i = 0; i < teams.length; i++) {
      teamIndices.put(teams[i].getName(), i);
    }
  }

  /**
   * Get the display name of the game.
   *
   * @return Game name
   */
  public String getName() {
    return name;
  }

  /**
   * Get the teams available.
   *
   * @return Teams players can choose from
   */
  public Team[] getTeams() {
    return teams;
  }

  // Helper private methods

  /**
   * Get the kits available.
   *
   * @return Kits players can choose from
   */
  public Kit[] getKits() {
    return kits;
  }

  /**
   * Get a {@link Team} by its name.
   *
   * @param teamName Name of the team
   * @return {@link Team} where its {@link Team#getName()} is {@code teamName}
   */
  private Team getTeamByName(String teamName) {
    return teams[teamIndices.get(teamName)];
  }

  // Methods for subclasses

  /**
   * Get a {@link org.bukkit.scoreboard.Team} by its name.
   *
   * @param teamName Name of the team
   * @return {@link org.bukkit.scoreboard.Team} where the {@link Team#getName()} of its
   * corresponding {@link Team} is {@code teamName}
   */
  private org.bukkit.scoreboard.Team getScoreboardTeamByName(String teamName) {
    return scoreboardTeams[teamIndices.get(teamName)];
  }

  /**
   * Add a player to a team.
   *
   * @param player {@link Player} to be added
   * @param teamName Name of the team to add {@code player} to
   */
  protected void addPlayerToTeam(Player player, String teamName) {
    // Remove the player from its current team
    org.bukkit.scoreboard.Team originalTeam = getScoreboardTeamByName(getTeamOfPlayer(player));
    originalTeam.removeEntry(player.getDisplayName());

    // Add the player to its new team
    org.bukkit.scoreboard.Team scoreboardTeam = getScoreboardTeamByName(teamName);
    scoreboardTeam.addEntry(player.getDisplayName());
    teamOfPlayer.put(player, teamName);
  }

  /**
   * Get the team a player is on.
   *
   * @param player {@link Player} to return the team of
   * @return The team {@code player} belongs to
   */
  protected String getTeamOfPlayer(Player player) {
    return teamOfPlayer.get(player);
  }

  // State

  /**
   * Get the scoreboard, for adding and using objectives.
   *
   * @return Scoreboard used by the game
   */
  protected Scoreboard getScoreboard() {
    return scoreboard;
  }

  /**
   * Set a property on a player.
   *
   * @param player The {@link Player} to set a property on
   * @param property The name of the property to set
   * @param value The value to set the property to
   */
  protected void setPlayerProperty(Player player, String property, Object value) {
    if (!playerState.containsKey(player)) {
      playerState.put(player, new HashMap<>());
    }

    playerState.get(player).put(property, value);
  }

  /**
   * Get a player's property.
   *
   * @param player The {@link Player} to get a property from
   * @param property The name of the property to get
   * @return The value of the property
   */
  protected Object getPlayerProperty(Player player, String property) {
    if (!playerState.containsKey(player) || !playerState.get(player).containsKey(property)) {
      return null;
    } else {
      return playerState.get(player).get(property);
    }
  }

  /**
   * Gets whether or not the game is running.
   *
   * @return Whether or not the game is running
   */
  protected boolean isGameRunning() {
    return gameRunning;
  }

  /**
   * Starts the game.
   */
  protected void startGame() {
    gameRunning = true;
    onGameStart();
  }

  /**
   * Ends the game, prematurely or not.
   */
  protected void endGame() {
    gameRunning = false;
    onGameEnd();
  }

  // Events

  /**
   * Called when a player joins the game.
   *
   * @param player The {@link Player} who has joined
   */
  public abstract void onPlayerJoin(Player player);

  /**
   * Called when a player leaves the game.
   *
   * @param player The {@link Player} who has left
   */
  public abstract void onPlayerLeave(Player player);

  /**
   * Called when the game starts.
   */
  public abstract void onGameStart();

  /**
   * Called when the game ends.
   */
  public abstract void onGameEnd();
}
