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
package de.codehat.spigot.commons.database.migration;

import de.codehat.spigot.commons.database.model.MigrationInfo;
import javax.annotation.Nullable;

public interface IMigration {
  long getVersion();

  String getName();

  /**
   * Applies the underlying migration SQL to the database and returns the migration info as the
   * result.
   *
   * <p>Returns {@code null} if applying the migration was not successful.
   *
   * @return the migration info on success or {@code null} on failure
   */
  @Nullable
  MigrationInfo migrate();
}
