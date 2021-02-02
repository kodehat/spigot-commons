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

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class AbstractDatabaseTest {

  private static class Model extends AbstractModel {}

  private static class ABCModel extends AbstractModel {}

  private static class TestModel extends AbstractModel {}

  private static class AnotherTestModel extends AbstractModel {}

  @ParameterizedTest
  @MethodSource("getTableName")
  void testGetTableName(AbstractModel model, String expected) {
    assertThat(model.getTableName(), is(expected));
  }

  private static Stream<Arguments> getTableName() {
    return Stream.of(
        Arguments.of(new Model(), "model"),
        Arguments.of(new ABCModel(), "abc_model"),
        Arguments.of(new TestModel(), "test_model"),
        Arguments.of(new AnotherTestModel(), "another_test_model"));
  }
}
