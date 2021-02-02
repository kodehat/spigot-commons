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

import javax.annotation.Nonnull;
import org.springframework.jdbc.core.JdbcTemplate;

public final class CreateMigrationInfoTableMigration extends AbstractMigration {

  public CreateMigrationInfoTableMigration(JdbcTemplate jdbcTemplate) {
    super(jdbcTemplate);
  }

  @Nonnull
  @Override
  public String getSql() {
    return "CREATE TABLE migration_info("
        + "name VARCHAR(255) NOT NULL,"
        + "version INTEGER PRIMARY KEY /*!40101 AUTO_INCREMENT */,"
        + "applied_at INTEGER NOT NULL"
        + ")";
  }

  @Override
  public long getVersion() {
    return 1;
  }

  @Override
  public String getName() {
    return "create_migrationinfo_table";
  }
}
