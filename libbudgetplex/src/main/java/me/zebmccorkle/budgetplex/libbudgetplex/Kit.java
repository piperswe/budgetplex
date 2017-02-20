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

import org.bukkit.inventory.ItemStack;

/**
 * A set of items for players to respawn with
 */
public class Kit {

  /**
   * Amount of {@link org.bukkit.inventory.ItemStack}s that will fit into the player armor slots
   */
  public static final int STACKS_IN_ARMOR = 4;
  /**
   * Amount of rows in the player inventory
   */
  public static final int ROWS_IN_INVENTORY = 4;
  /**
   * Amount of columns in the player inventory, also amount of {@link
   * org.bukkit.inventory.ItemStack}s that will fit into the hotbar
   */
  public static final int COLUMNS_IN_INVENTORY = 9;
  /**
   * Amount of {@link org.bukkit.inventory.ItemStack}s that will fit into the player inventory
   */
  // Subtract one from ROWS_IN_INVENTORY as the hotbar shouldn't be included
  public static final int STACKS_IN_INVENTORY = (ROWS_IN_INVENTORY - 1) * COLUMNS_IN_INVENTORY;

  private String id;
  private ItemStack[] hotbar;
  private ItemStack[] armor;
  private ItemStack[] inventory;
  private ItemStack offhand;

  /**
   * Create a "kit," or set of items players spawn with in a game
   *
   * @param id Unique ID, preferably a UUID. Must not change between server restarts or plugin
   * versions.
   * @param hotbar {@link org.bukkit.inventory.ItemStack}s for the player to spawn with in its
   * hotbar
   * @param armor {@link org.bukkit.inventory.ItemStack}s for the player to spawn equipped with as
   * armor
   * @param inventory {@link org.bukkit.inventory.ItemStack}s for the player to spawn with in its
   * main inventory
   * @param offhand {@link org.bukkit.inventory.ItemStack} for the player to spawn with in its
   * offhand
   */
  public Kit(String id, ItemStack[] hotbar, ItemStack[] armor, ItemStack[] inventory,
      ItemStack offhand) {
    if (hotbar.length > COLUMNS_IN_INVENTORY) {
      throw new ArrayIndexOutOfBoundsException(
          "Hotbar must contain at most " + COLUMNS_IN_INVENTORY + " ItemStacks");
    }
    if (armor.length > STACKS_IN_ARMOR) {
      throw new ArrayIndexOutOfBoundsException(
          "Armor must contain at most " + STACKS_IN_ARMOR + " ItemStacks");
    }
    if (inventory.length > STACKS_IN_INVENTORY) {
      throw new ArrayIndexOutOfBoundsException(
          "Inventory must contain at most " + STACKS_IN_INVENTORY + "ItemStacks");
    }

    this.hotbar = hotbar;
    this.armor = armor;
    this.inventory = inventory;
    this.offhand = offhand;
  }

  /**
   * Get the unique ID of the kit
   *
   * @return A unique ID that can be used to identify this kit
   */
  public String getID() {
    return id;
  }

  /**
   * Get the hash code of the <em>ID</em> of the kit
   *
   * @return {@link String#hashCode()} of the result of {@link #getID()}
   */
  @Override
  public int hashCode() {
    return getID().hashCode();
  }

  /**
   * Get the {@link org.bukkit.inventory.ItemStack}s for the player to respawn with in its hotbar
   *
   * @return Array of {@link org.bukkit.inventory.ItemStack}s in player hotbar (max size {@link
   * #COLUMNS_IN_INVENTORY})
   */
  public ItemStack[] getHotbar() {
    return hotbar;
  }

  /**
   * Get the {@link org.bukkit.inventory.ItemStack}s for the player to respawn with in its armor
   * slots
   *
   * @return Array of {@link org.bukkit.inventory.ItemStack}s in player armor slots (max size {@link
   * #STACKS_IN_ARMOR})
   */
  public ItemStack[] getArmor() {
    return armor;
  }

  /**
   * Get the {@link org.bukkit.inventory.ItemStack}s for the player to respawn with in its main
   * inventory
   *
   * @return Array of {@link org.bukkit.inventory.ItemStack}s in player main inventory (max size
   * {@link #STACKS_IN_INVENTORY})
   */
  public ItemStack[] getInventory() {
    return inventory;
  }

  /**
   * Get the {@link org.bukkit.inventory.ItemStack} for the player to respawn with in its offhand
   *
   * @return {@link org.bukkit.inventory.ItemStack} in player offhand
   */
  public ItemStack getOffhand() {
    return offhand;
  }
}
