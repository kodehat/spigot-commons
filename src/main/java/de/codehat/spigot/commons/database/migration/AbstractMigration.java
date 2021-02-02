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
import de.codehat.spigot.commons.repository.MigrationInfoRepository;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

public abstract class AbstractMigration implements IMigration {

  private final JdbcTemplate jdbcTemplate;
  private final TransactionTemplate transactionTemplate;
  private final MigrationInfoRepository migrationInfoRepository;

  public AbstractMigration(JdbcTemplate jdbcTemplate) {
    this(
        jdbcTemplate,
        new DataSourceTransactionManager(Objects.requireNonNull(jdbcTemplate.getDataSource())));
  }

  private AbstractMigration(
      JdbcTemplate jdbcTemplate, DataSourceTransactionManager dataSourceTransactionManager) {
    this.jdbcTemplate = jdbcTemplate;
    this.transactionTemplate = new TransactionTemplate(dataSourceTransactionManager);
    this.migrationInfoRepository = new MigrationInfoRepository(jdbcTemplate);
  }

  @Nonnull
  public abstract String getSql();

  @Override
  @Nullable
  public MigrationInfo migrate() {
    return transactionTemplate.execute(
        status -> {
          MigrationInfo migrationInfo;
          Object beforeMigration = status.createSavepoint();
          try {
            jdbcTemplate.execute(getSql());
            migrationInfo = new MigrationInfo(getName(), getVersion(), LocalDateTime.now());
            migrationInfoRepository.insert(migrationInfo);
          } catch (Exception e) {
            status.rollbackToSavepoint(beforeMigration);
            migrationInfo = null;
          } finally {
            status.releaseSavepoint(beforeMigration);
          }
          return migrationInfo;
        });
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AbstractMigration that = (AbstractMigration) o;
    return Objects.equals(getVersion(), that.getVersion())
        && Objects.equals(getName(), that.getName());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getVersion(), getName());
  }
}
