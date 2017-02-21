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

import java.io.IOException;
import org.bukkit.plugin.java.JavaPlugin;

public class LibPlugin extends JavaPlugin {

  private GameQueryServer queryServer;
  private Game currentGame;

  @Override
  public void onEnable() {
    saveDefaultConfig();
    queryServer = new GameQueryServer(getConfig().getInt("game-query-port"), currentGame);
    try {
      queryServer.listen();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void onDisable() {
    queryServer.close();
  }

  public Game getGame() {
    return currentGame;
  }

  private void setGame(Game game) {
    currentGame.endGame();
    currentGame = game;
    queryServer.setGame(game);
  }
}
