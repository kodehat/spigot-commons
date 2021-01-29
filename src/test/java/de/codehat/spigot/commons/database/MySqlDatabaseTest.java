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

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.google.common.collect.Maps;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class MySqlDatabaseTest {

  private static MysqlDatabase dummyDatabase() {
    return new MysqlDatabase("localhost", 3306, "dummy", "user", "passwd");
  }

  private static MysqlDatabase dummyDatabase(Map<String, Object> args) {
    return new MysqlDatabase("localhost", 3306, "dummy", "user", "passwd", args);
  }

  @ParameterizedTest
  @MethodSource("buildArgs")
  void testBuildArgs(Map<String, Object> args, String expected) {
    assertThat(dummyDatabase(args).buildArgs(), is(expected));
  }

  private static Stream<Arguments> buildArgs() {
    // Order of key/value-pairs in map is important for the test only.
    final Map<String, Object> first = new LinkedHashMap<>();
    first.put("trueBool", true);
    first.put("aString", "text");
    final Map<String, Object> second = new LinkedHashMap<>();
    second.put("falseBool", false);
    second.put("anInt", 42);
    return Stream.of(
        Arguments.of(first, "trueBool=true&aString=text"),
        Arguments.of(second, "falseBool=false&anInt=42"));
  }

  @ParameterizedTest
  @MethodSource("buildArg")
  void testBuildArg(String key, Object value, String expected) {
    assertThat(dummyDatabase().buildArg(Maps.immutableEntry(key, value)), is(expected));
  }

  private static Stream<Arguments> buildArg() {
    return Stream.of(
        Arguments.of("trueBool", true, "trueBool=true"),
        Arguments.of("falseBool", false, "falseBool=false"),
        Arguments.of("aString", "text", "aString=text"),
        Arguments.of("anInt", 42, "anInt=42"));
  }
}
