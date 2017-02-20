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

import me.zebmccorkle.budgetplex.libbudgetplex.Team;
import org.bukkit.ChatColor;
import org.junit.Test;

public class TestTeam {
    @Test
    public void storesNameCorrectly() {
        String name;
        Team team;

        name = "Team Name";
        team = new Team(name, ChatColor.AQUA, true);
        assert team.getName().equals(name);

        name = Double.toString(Math.random());
        team = new Team(name, ChatColor.GOLD, false);
        assert team.getName().equals(name);
    }

    @Test
    public void storesNametagColorCorrectly() {
        ChatColor color;
        Team team;

        color = ChatColor.AQUA;
        team = new Team("A Name", color, true);
        assert team.getNametagColor().equals(color);

        color = ChatColor.UNDERLINE;
        team = new Team("Another Name", color, false);
        assert team.getNametagColor().equals(color);
    }

    @Test
    public void storesCanBeChosenCorrectly() {
        boolean canBeChosen;
        Team team;

        canBeChosen = true;
        team = new Team("Woah names many names", ChatColor.BLACK, canBeChosen);
        assert team.getCanBeChosen() == canBeChosen;

        canBeChosen = false;
        team = new Team("fjhsjkhfjk", ChatColor.MAGIC, canBeChosen);
        assert team.getCanBeChosen() == canBeChosen;
    }
}
