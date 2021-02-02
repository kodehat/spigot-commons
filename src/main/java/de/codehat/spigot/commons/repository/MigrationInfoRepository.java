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
package de.codehat.spigot.commons.repository;

import de.codehat.spigot.commons.database.model.MigrationInfo;
import org.springframework.jdbc.core.JdbcTemplate;

public class MigrationInfoRepository extends AbstractRepository<MigrationInfo> {

  public MigrationInfoRepository(JdbcTemplate jdbcTemplate) {
    super(MigrationInfo.class, jdbcTemplate);
  }

  @Override
  public void insert(MigrationInfo model) {
    String sql = "INSERT INTO migration_info(name, version, applied_at) VALUES (?, ?, ?)";
    Object[] params = new Object[] {model.getName(), model.getVersion(), model.getAppliedAtUnix()};
    getJdbcTemplate().update(sql, params);
  }

  @Override
  public void update(MigrationInfo model, Object... params) {
    String sql =
        "UPDATE migration_info SET name = ?, version = ?, applied_at = ? WHERE version = ?";
    Object[] newValues =
        new Object[] {model.getName(), model.getVersion(), model.getAppliedAtUnix()};
    getJdbcTemplate().update(sql, newValues, params);
  }

  @Override
  public void delete(MigrationInfo model) {
    String sql =
        String.format("DELETE FROM %s WHERE name = ? AND version = ?", model.getTableName());
    getJdbcTemplate().update(sql, model.getName(), model.getVersion());
  }

  public Long findMaxVersion() {
    String sql = "SELECT MAX(version) FROM migration_info";
    return getJdbcTemplate().queryForObject(sql, Long.class);
  }

  @Override
  public MigrationInfo find(Object... params) {
    String sql = "SELECT * FROM migration_info WHERE version = ?";
    return getJdbcTemplate().queryForObject(sql, MigrationInfo.class, params);
  }
}
