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

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * A server to the gamequery protocol.
 */
public class GameQueryServer implements Closeable {

  private int port;
  private ServerSocket serverSocket;
  private Thread thread;
  private AtomicBoolean closed = new AtomicBoolean(false);
  private Game game;

  /**
   * Create a server to the gamequery protocol.
   *
   * @param port Port to listen on
   * @param game Game the server is currently running
   */
  public GameQueryServer(int port, Game game) {
    this.port = port;
    this.game = game;
  }

  /**
   * Listen on {@link #getPort()}.
   *
   * @throws IOException Produced on network error
   */
  public void listen() throws IOException {
    closed.set(false);
    serverSocket = new ServerSocket(port);
    thread = new Thread(() -> {
      while (!closed.get()) {
        try {
          Socket socket = serverSocket.accept();
          BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
          DataOutputStream out = new DataOutputStream(socket.getOutputStream());

          String query = in.readLine();
          String[] splitQuery = query.split(" ");
          String command = splitQuery[0];

          switch (command) {
            case "isGameRunning":
              out.writeBytes(Boolean.toString(game.isGameRunning()) + "\n");
              break;
            case "gameName":
              out.writeBytes(game.getName() + "\n");
              break;
            default:
              out.writeBytes("Unknown command\n");
              break;
          }

          socket.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }

      try {
        serverSocket.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    });
  }

  /**
   * Close the connection.
   */
  @Override
  public void close() {
    closed.set(true);
  }

  /**
   * Get the current game
   *
   * @return Game the server is currently running
   */
  public Game getGame() {
    return game;
  }

  /**
   * Change the current game.
   *
   * @param game Game the server is now running
   */
  public void setGame(Game game) {
    this.game = game;
  }

  /**
   * Get the port this object is or will be listening on
   *
   * @return TCP port this object is or will be listening on
   */
  public int getPort() {
    return port;
  }
}
