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

import de.codehat.spigot.commons.database.model.IModel;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public abstract class AbstractRepository<V extends IModel> implements IRepository<V> {

  private final Class<V> modelClass;
  private final JdbcTemplate jdbcTemplate;
  private final V dummyInstance;

  public AbstractRepository(Class<V> modelClass, JdbcTemplate jdbcTemplate) {
    this.modelClass = modelClass;
    this.jdbcTemplate = jdbcTemplate;
    try {
      this.dummyInstance = modelClass.getConstructor().newInstance();
    } catch (InstantiationException
        | IllegalAccessException
        | InvocationTargetException
        | NoSuchMethodException e) {
      // TODO: Create custom exception.
      throw new RuntimeException("Unable to call default constructor on model class!");
    }
  }

  protected JdbcTemplate getJdbcTemplate() {
    return jdbcTemplate;
  }

  @Override
  public void update(V model, Object... params) {
    throw new NotImplementedException();
  }

  @Override
  public List<V> all() {
    String sql = String.format("SELECT * FROM %s", dummyInstance.getTableName());
    return getJdbcTemplate().query(sql, BeanPropertyRowMapper.newInstance(modelClass));
  }
}
