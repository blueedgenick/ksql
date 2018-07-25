/*
 * Copyright 2018 Confluent Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package io.confluent.ksql.function.udf.string;

import org.junit.Test;
import org.junit.rules.ExpectedException;
import io.confluent.ksql.function.KsqlFunctionException;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import org.junit.Rule;

public class ConcatWSKudfTest {
  private final ConcatWSKudf udf = new ConcatWSKudf();

  @Rule
  public final ExpectedException expectedException = ExpectedException.none();

  @Test
  public void happyPath() {
    final Object result = udf.evaluate("SEP", "foo", "bar", "baz");
    assertThat(result, is("fooSEPbarSEPbaz"));
  }

  @Test
  public void shouldFailIfOnlyOneInput() {
    expectedException.expect(KsqlFunctionException.class);
    expectedException.expectMessage("expects at least two input arguments");
    udf.evaluate("SEP", "foo");
  }
  
  @Test
  public void shouldReturnNullForNullInput() {
    final Object result = udf.evaluate("SEP", null, null);
    assertThat(result, is(nullValue()));
  }

  @Test
  public void shouldReturnNullForAnyNullInput() {
    final Object result = udf.evaluate("SEP", "foo", null);
    assertThat(result, is(nullValue()));
  }


  @Test
  public void shouldReturnNullForNullSeparator() {
    final Object result = udf.evaluate(null, "foo", "bar");
    assertThat(result, is(nullValue()));
  }

  @Test
  public void shouldWorkWithEmptySeparator() {
    final Object result = udf.evaluate("", "foo", "bar");
    assertThat(result, is("foobar"));
  }

  @Test
  public void shouldHandleEmptyInputs() {
    final Object result = udf.evaluate("SEP", "foo", "", "bar");
    assertThat(result, is("fooSEPSEPbar"));
  }

  @Test
  public void shouldReturnEmptyIfEverythingEmpty() {
    final Object result = udf.evaluate("", "", "");
    assertThat(result, is(""));
  }

}
