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

import de.codehat.spigot.commons.config.key.BaseConfigKey;
import org.bukkit.configuration.file.FileConfiguration;

public class BaseConfig extends Config {

  public BaseConfig(FileConfiguration fileConfiguration) {
    super(fileConfiguration);
  }

  public String getDatabaseType() {
    return getValue(BaseConfigKey.DATABASE_TYPE);
  }

  public String getDatabaseHost() {
    return getValue(BaseConfigKey.DATABASE_HOST);
  }

  public Integer getDatabasePort() {
    return getValue(BaseConfigKey.DATABASE_PORT);
  }

  public String getDatabaseName() {
    return getValue(BaseConfigKey.DATABASE_NAME);
  }

  public String getDatabaseUser() {
    return getValue(BaseConfigKey.DATABASE_USER);
  }

  public String getDatabasePassword() {
    return getValue(BaseConfigKey.DATABASE_PASSWORD);
  }

  public boolean isSqlite() {
    return "sqlite".equals(getDatabaseType());
  }

  public boolean isMysql() {
    return "mysql".equals(getDatabaseType());
  }
}
