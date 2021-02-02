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

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import javax.annotation.Nonnull;

public class MysqlDatabase extends AbstractDatabase {

  public static final String TYPE = "mysql";

  private static final String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
  private static final String JDBC_URL_TEMPLATE = "jdbc:mysql://%s:%s/%s?user=%s&password=%s&%s";
  private static final Map<String, Object> DEFAULT_ARGS;

  static {
    DEFAULT_ARGS = new HashMap<>();
    DEFAULT_ARGS.put("useSSL", false);
    DEFAULT_ARGS.put("autoReconnect", true);
  }

  private final String host;
  private final Integer port;
  private final String database;
  private final String user;
  private final String passwd;
  private final Map<String, Object> args;

  public MysqlDatabase(
      String host,
      Integer port,
      String database,
      String user,
      String passwd,
      Map<String, Object> args) {
    this.host = host;
    this.port = port;
    this.database = database;
    this.user = user;
    this.passwd = passwd;
    this.args = args;
  }

  public MysqlDatabase(String host, Integer port, String database, String user, String passwd) {
    this(host, port, database, user, passwd, Collections.unmodifiableMap(DEFAULT_ARGS));
  }

  @Override
  protected String getDriverClassName() {
    return DRIVER_CLASS_NAME;
  }

  @Override
  protected String getJdbcUrl() {
    return String.format(JDBC_URL_TEMPLATE, host, port, database, user, passwd, buildArgs());
  }

  @Nonnull
  String buildArgs() {
    return args.entrySet().stream().map(this::buildArg).collect(Collectors.joining("&"));
  }

  String buildArg(Map.Entry<String, Object> arg) {
    return arg.getKey() + "=" + arg.getValue().toString();
  }
}
