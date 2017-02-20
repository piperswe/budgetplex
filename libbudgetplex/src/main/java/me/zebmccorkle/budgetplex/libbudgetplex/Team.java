/*******************************************************************************
 * Budgetplex
 * Copyright (C) 2017 Zebulon McCorkle
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/

package me.zebmccorkle.budgetplex.libbudgetplex;

import org.bukkit.ChatColor;
/**
 * A group of players, typically working together
 */
public class Team {
    private String name;
    private ChatColor nametagColor;
    private boolean canBeChosen;

    /**
     * Create a team with a name and nametag color
     *
     * @param name Team name, will be displayed at team selection
     * @param nametagColor Color of team members' nametags
     * @param canBeChosen Whether or not the team should be able to be chosen by players
     */
    public Team(String name, ChatColor nametagColor, boolean canBeChosen) {
        this.name = name;
        this.nametagColor = nametagColor;
        this.canBeChosen = canBeChosen;
    }

    /**
     * Get the team name
     *
     * @return Team name
     */
    public String getName() {
        return name;
    }

    /**
     * Get the team nametag color
     *
     * @return Color of team members' nametags
     */
    public ChatColor getNametagColor() {
        return nametagColor;
    }

    /**
     * Get whether or not the team can be chosen by players
     *
     * @return Whether or not the team should be able to be chosen by players
     */
    public boolean getCanBeChosen() {
        return canBeChosen;
    }
}
