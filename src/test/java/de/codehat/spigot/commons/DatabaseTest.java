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
package de.codehat.spigot.commons;

import de.codehat.spigot.commons.database.H2Database;
import de.codehat.spigot.commons.database.IDatabase;
import java.util.Objects;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.jdbc.core.JdbcTemplate;

class DatabaseTest {

  private static IDatabase database;
  private static JdbcTemplate jdbcTemplate;

  @BeforeAll
  public static void setup() throws ClassNotFoundException {
    database = new H2Database();
    jdbcTemplate = new JdbcTemplate(Objects.requireNonNull(database.create()));
  }

  @AfterAll
  public static void cleanup() {
    jdbcTemplate = null;
    database = null;
  }
}
