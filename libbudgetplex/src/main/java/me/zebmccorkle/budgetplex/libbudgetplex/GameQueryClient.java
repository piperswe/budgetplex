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
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * A client to the gamequery protocol.
 */
public class GameQueryClient {

  private String ip;
  private int port;

  /**
   * Create a client to the gamequery protocol.
   *
   * @param ip IP address of the server
   * @param port Port the server is listening on
   */
  public GameQueryClient(String ip, int port) {
    this.ip = ip;
    this.port = port;
  }

  private String request(String command) throws IOException {
    Socket socket = new Socket(ip, port);
    DataOutputStream out = new DataOutputStream(socket.getOutputStream());
    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

    out.writeBytes(command + "\n");
    return in.readLine();
  }

  /**
   * Get the server's game status.
   *
   * @return Whether or not the game is running on the server
   * @throws IOException Produced on network error
   */
  public boolean isGameRunning() throws IOException {
    return Boolean.parseBoolean(request("isGameRunning"));
  }

  /**
   * Get the server's current game.
   *
   * @return The server's current game's display name
   * @throws IOException Produced on network error
   */
  public String gameName() throws IOException {
    return request("gameName");
  }
}
