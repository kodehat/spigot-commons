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
import org.bukkit.configuration.file.FileConfiguration;

public class Config {

  private final FileConfiguration fileConfiguration;

  public Config(FileConfiguration fileConfiguration) {
    this.fileConfiguration = fileConfiguration;
  }

  @SuppressWarnings("unchecked")
  protected <V> V getValue(ConfigKey<V> configKey) {
    return (V) fileConfiguration.get(configKey.getKey(), configKey.getDefaultValue());
  }
}
