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
package de.codehat.spigot.commons.database.migration.manager;

import de.codehat.spigot.commons.database.migration.IMigration;
import de.codehat.spigot.commons.database.model.MigrationInfo;
import java.util.List;

public interface IMigrationManager {
  /**
   * Adds the given migration to the list of migrations to apply.
   *
   * @param migration the migration
   * @return {@code true} if the migration has not been added before
   */
  boolean addMigration(IMigration migration);

  /**
   * Returns the version of the last applied migration. This allows the calculation of the
   * migrations that are missing and have to applied to the database.
   *
   * @return the version of the last applied migration
   */
  long lastMigrationVersion();

  /**
   * Applies all previously added migrations. Calculates the already applied migrations beforehand
   * and only takes new migrations into account.
   *
   * @return a list of results of successfully applied migrations
   */
  List<MigrationInfo> migrate();
}
