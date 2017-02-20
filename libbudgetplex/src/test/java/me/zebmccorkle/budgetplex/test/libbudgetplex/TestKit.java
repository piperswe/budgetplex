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

package me.zebmccorkle.budgetplex.test.libbudgetplex;

import me.zebmccorkle.budgetplex.libbudgetplex.Kit;
import org.bukkit.inventory.ItemStack;
import org.junit.Test;

public class TestKit {
  public static final ItemStack[] NO_ITEMS = new ItemStack[0];

  @Test
  public void storesIdCorrectly() {
    String id;
    Kit kit;

    id = "gamename-archer";
    kit = new Kit(id, NO_ITEMS, NO_ITEMS, NO_ITEMS, null);
    assert kit.getId().equals(id);
    assert kit.hashCode() == id.hashCode();

    id = Double.toString(Math.random());
    kit = new Kit(id, NO_ITEMS, NO_ITEMS, NO_ITEMS, null);
    assert kit.getId().equals(id);
    assert kit.hashCode() == id.hashCode();
  }

  // Not testing ItemStacks until the full Spigot jar is included in builds.
  // Not sure when that'll be.
}
