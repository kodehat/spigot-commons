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
package de.codehat.spigot.commons.logging;

import java.util.logging.Level;
import java.util.logging.Logger;

public final class SimpleLogger {

  private final Logger logger;

  public SimpleLogger(Logger logger) {
    this.logger = logger;
  }

  public void info(String msg, Object... params) {
    logger.log(Level.INFO, msg, params);
  }

  public void warn(String msg, Object... params) {
    logger.log(Level.WARNING, msg, params);
  }

  public void error(String msg, Throwable thrown) {
    logger.log(Level.SEVERE, msg, thrown);
  }
}
