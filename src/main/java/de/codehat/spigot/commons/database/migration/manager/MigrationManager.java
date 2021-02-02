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

import de.codehat.spigot.commons.database.migration.CreateMigrationInfoTableMigration;
import de.codehat.spigot.commons.database.migration.IMigration;
import de.codehat.spigot.commons.database.model.MigrationInfo;
import de.codehat.spigot.commons.repository.MigrationInfoRepository;
import java.util.*;
import java.util.stream.Collectors;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

public final class MigrationManager implements IMigrationManager {

  private final MigrationInfoRepository migrationInfoRepository;
  private final List<IMigration> migrations;

  public MigrationManager(JdbcTemplate jdbcTemplate) {
    this.migrationInfoRepository = new MigrationInfoRepository(jdbcTemplate);
    this.migrations = new ArrayList<>();

    migrations.add(new CreateMigrationInfoTableMigration(jdbcTemplate));
  }

  @Override
  public boolean addMigration(IMigration migration) {
    return migrations.add(migration);
  }

  @Override
  public long lastMigrationVersion() {
    Long currentVersion;
    try {
      currentVersion = migrationInfoRepository.findMaxVersion();
    } catch (DataAccessException e) {
      currentVersion = 0L;
    }
    return currentVersion;
  }

  @Override
  public List<MigrationInfo> migrate() {
    final List<MigrationInfo> migrationResults = new ArrayList<>();
    final Set<IMigration> toMigrate = calcMigrationsToApply();
    toMigrate.forEach(migration -> migrationResults.add(migration.migrate()));
    return migrationResults.stream()
        .filter(Objects::nonNull) // TODO: Do not filter unsuccessful migrations? Map? New Object?
        .collect(Collectors.toList());
  }

  private Set<IMigration> calcMigrationsToApply() {
    return migrations.stream()
        .filter(migration -> migration.getVersion() > lastMigrationVersion())
        .sorted(Comparator.comparing(IMigration::getVersion))
        .collect(Collectors.toCollection(LinkedHashSet::new));
  }
}
