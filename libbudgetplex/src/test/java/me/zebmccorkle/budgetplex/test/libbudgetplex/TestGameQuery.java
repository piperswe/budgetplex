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

package me.zebmccorkle.budgetplex.test.libbudgetplex;

import java.io.IOException;
import me.zebmccorkle.budgetplex.libbudgetplex.Game;
import me.zebmccorkle.budgetplex.libbudgetplex.GameQueryClient;
import me.zebmccorkle.budgetplex.libbudgetplex.GameQueryServer;
import me.zebmccorkle.budgetplex.libbudgetplex.Kit;
import me.zebmccorkle.budgetplex.libbudgetplex.Team;
import org.bukkit.entity.Player;
import org.junit.Test;

public class TestGameQuery {
  private static class TestingGame extends Game {

    protected TestingGame() {
      super("TestingGame", new Team[0], new Kit[0]);
    }

    @Override
    public void onPlayerJoin(Player player) {

    }

    @Override
    public void onPlayerLeave(Player player) {

    }

    @Override
    public void onGameStart() {

    }

    @Override
    public void onGameEnd() {

    }
  }

  @Test
  public void isGameRunning() throws IOException {
    Game game = new TestingGame();
    GameQueryServer server = new GameQueryServer(9000, game);
    GameQueryClient client = new GameQueryClient("localhost", 9000);

    server.listen();

    assert client.isGameRunning() == game.isGameRunning();

    server.close();
  }
}
