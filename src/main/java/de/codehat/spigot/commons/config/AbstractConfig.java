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
import java.io.IOException;
import java.nio.file.Path;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public abstract class AbstractConfig implements IConfig {

  private final Path filePath;
  private FileConfiguration fileConfiguration;

  protected AbstractConfig(Path filePath) {
    this.filePath = filePath;

    reload();
  }

  @Override
  @SuppressWarnings("unchecked")
  public <V> V get(ConfigKey<V> configKey) {
    return (V) fileConfiguration.get(configKey.getKey(), configKey.getDefaultValue());
  }

  public <V> void set(ConfigKey<V> configKey) {
    set(configKey, configKey.getDefaultValue());
  }

  @Override
  public <V> void set(ConfigKey<V> configKey, V value) {
    fileConfiguration.set(configKey.getKey(), value);
  }

  @Override
  public void save() throws IOException {
    fileConfiguration.save(filePath.toFile());
  }

  public void reload() {
    fileConfiguration = YamlConfiguration.loadConfiguration(filePath.toFile());
  }

  protected FileConfiguration getFileConfiguration() {
    return fileConfiguration;
  }

  protected Path getFilePath() {
    return filePath;
  }
}
