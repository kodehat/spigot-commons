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
package de.codehat.spigot.commons.database;

public class H2Database extends AbstractDatabase {

  private static final String DRIVER_CLASS_NAME = "org.h2.Driver";
  private static final String JDBC_URL_TEMPLATE = "jdbc:h2:mem:sign_colors_test";

  @Override
  protected String getDriverClassName() {
    return DRIVER_CLASS_NAME;
  }

  @Override
  protected String getJdbcUrl() {
    return JDBC_URL_TEMPLATE;
  }
}
