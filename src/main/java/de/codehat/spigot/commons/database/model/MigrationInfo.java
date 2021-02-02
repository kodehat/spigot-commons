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
package de.codehat.spigot.commons.database.model;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public final class MigrationInfo extends AbstractModel {

  private String name;
  private long version;
  private LocalDateTime appliedAt;

  public MigrationInfo() {
    // Required.
  }

  public MigrationInfo(String name, long version, LocalDateTime appliedAt) {
    this.name = name;
    this.version = version;
    this.appliedAt = appliedAt;
  }

  public long getAppliedAtUnix() {
    return appliedAt.toEpochSecond(ZoneOffset.UTC);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public long getVersion() {
    return version;
  }

  public void setVersion(long version) {
    this.version = version;
  }

  public LocalDateTime getAppliedAt() {
    return appliedAt;
  }

  public void setAppliedAt(LocalDateTime appliedAt) {
    this.appliedAt = appliedAt;
  }
}
