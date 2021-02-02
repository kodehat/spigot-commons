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
package de.codehat.spigot.commons.config;

import de.codehat.spigot.commons.config.key.ConfigKey;
import java.nio.file.Path;

public class BaseConfig extends AbstractConfig {

  private static final ConfigKey<String> DATABASE_TYPE = new ConfigKey<>("database.type", "sqlite");

  private static final ConfigKey<String> DATABASE_HOST =
      new ConfigKey<>("database.host", "127.0.0.1");

  private static final ConfigKey<Integer> DATABASE_PORT = new ConfigKey<>("database.port", 3306);

  private static final ConfigKey<String> DATABASE_NAME =
      new ConfigKey<>("database.name", "signcolors");

  private static final ConfigKey<String> DATABASE_USER = new ConfigKey<>("database.user", "steve");

  private static final ConfigKey<String> DATABASE_PASSWORD =
      new ConfigKey<>("database.name", "JustAnExamplePassword");

  protected BaseConfig(Path filePath) {
    super(filePath);
  }

  public String getDatabaseType() {
    return get(DATABASE_TYPE);
  }

  public String getDatabaseHost() {
    return get(DATABASE_HOST);
  }

  public Integer getDatabasePort() {
    return get(DATABASE_PORT);
  }

  public String getDatabaseName() {
    return get(DATABASE_NAME);
  }

  public String getDatabaseUser() {
    return get(DATABASE_USER);
  }

  public String getDatabasePassword() {
    return get(DATABASE_PASSWORD);
  }
}
