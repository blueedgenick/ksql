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
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

public class LeftKudfTest {
  private final LeftKudf udf = new LeftKudf();

  @Test
  public void happyPath() {
    final String result = udf.left("hello world", 4);
    assertThat(result, is("hell"));
  }

  @Test
  public void shouldReturnNullForNullInput() {
    final String result = udf.left(null, 4);
    assertThat(result, is(nullValue()));
  }

  @Test
  public void shouldReturnNullForNullLength() {
    final String result = udf.left("hello world", null);
    assertThat(result, is(nullValue()));
  }

  @Test
  public void shouldReturnEmptyForEmptyInput() {
    final String result = udf.left("", 4);
    assertThat(result, is(""));
  }

  @Test
  public void shouldReturnWholeInputForExcessiveLength() {
    final String result = udf.left("hello world", 100000);
    assertThat(result, is("hello world"));
  }

  @Test
  public void shouldReturnOriginalInputForNegativeLength() {
    final String result = udf.left("hello world", -1);
    assertThat(result, is("hello world"));
  }
  
  @Test
  public void shouldReturnEmptyStringForZeroLength() {
    final String result = udf.left("hello world", 0);
    assertThat(result, is(""));
  }

}
