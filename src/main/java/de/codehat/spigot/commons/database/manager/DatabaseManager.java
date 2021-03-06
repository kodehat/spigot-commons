/*
 * spigot-commons is a library that holds common code for all of CodeHat's Spigot plugins.
 * Copyright (C) 2021 CodeHat
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package de.codehat.spigot.commons.database.manager;

import de.codehat.spigot.commons.database.IDatabase;
import java.util.Map;

public final class DatabaseManager implements IDatabaseManager {

  private final Map<String, IDatabase> databases;

  public DatabaseManager(Map<String, IDatabase> databases) {
    this.databases = databases;
  }

  @Override
  public IDatabase addDatabase(String databaseType, IDatabase database) {
    return databases.put(databaseType, database);
  }

  @Override
  public IDatabase removeDatabase(String databaseType) {
    return databases.remove(databaseType);
  }

  @Override
  public IDatabase getDatabase(String databaseType) {
    return databases.get(databaseType);
  }
}
