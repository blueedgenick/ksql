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

package io.confluent.ksql.function.udf.array;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import java.util.Arrays;
import java.util.List;

public class ArrayExceptKudfTest {
  private final ArrayExceptKudf udf = new ArrayExceptKudf();

  @SuppressWarnings("rawtypes")
  @Test
  public void happyPath() {
    final List input1 = Arrays.asList("foo", " ", "bar");
    final List input2 = Arrays.asList("foo", "bar");
    final List result = udf.except(input1, input2);
    assertThat(result, is(Arrays.asList(" ")));
  }

  @SuppressWarnings("rawtypes")
  @Test
  public void shouldReturnEmptyArrayIfAllExcepted() {
    final List input1 = Arrays.asList("foo", " ", "foo", "bar");
    final List input2 = Arrays.asList("foo", " ", "foo", "bar", "extra");
    final List result = udf.except(input1, input2);
    assertThat(result, is(Arrays.asList()));
  }

  @SuppressWarnings("rawtypes")
  public void shouldRetainOnlyDistinctValues() {
    final List input1 = Arrays.asList("foo", " ", "foo", "bar");
    final List input2 = Arrays.asList("bar");
    final List result = udf.except(input1, input2);
    assertThat(result, is(Arrays.asList("foo", " ")));
  }

  @SuppressWarnings("rawtypes")
  @Test
  public void shouldExceptMixedTypes() {
    final List input1 = Arrays.asList("foo", 1, "foo", 2, 3.5f);
    final List input2 = Arrays.asList("foo", "bar", 2);
    final List result = udf.except(input1, input2);
    assertThat(result, is(Arrays.asList(1, 3.5f)));
  }

  @SuppressWarnings("rawtypes")
  @Test
  public void shouldExceptEmptyArray() {
    final List input1 = Arrays.asList();
    final List input2 = Arrays.asList("foo");
    final List result = udf.except(input1, input2);
    assertThat(result, is(Arrays.asList()));
  }

//  @SuppressWarnings("rawtypes")
//  @Test
//  public void shouldDistinctValuesForEmptyExceptionArray() {
//    final List input1 = Arrays.asList("foo", "foo", "bar", "foo");
//    final List input2 = Arrays.asList();
//    final List result = udf.except(input1, input2);
//    assertThat(result, is(Arrays.asList("foo", "bar")));
//  }
//
  @SuppressWarnings("rawtypes")
  @Test
  public void shouldReturnNullForNullInput() {
    List result = udf.except(null, null);
    assertThat(result, is(nullValue()));
  }

}
