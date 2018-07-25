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

public class LPadKudfTest {
  private final LPadKudf udf = new LPadKudf();

  @Test
  public void happyPath() {
    final String result = udf.lpad("foo", 7, "bar");
    assertThat(result, is("barbfoo"));
  }

  @Test
  public void shouldReturnNullForNullInput() {
    final String result = udf.lpad(null, 4, "foo");
    assertThat(result, is(nullValue()));
  }

  @Test
  public void shouldReturnNullForNullPadding() {
    final String result = udf.lpad("foo", 4, null);
    assertThat(result, is(nullValue()));
  }

  @Test
  public void shouldReturnNullForEmptyPadding() {
    final String result = udf.lpad("foo", 4, "");
    assertThat(result, is(nullValue()));
  }

  @Test
  public void shouldPadEmptyInput() {
    final String result = udf.lpad("", 4, "foo");
    assertThat(result, is("foof"));
  }

  @Test
  public void shouldTruncateInputIfTargetLengthTooSmall() {
    final String result = udf.lpad("foo", 2, "bar");
    assertThat(result, is("fo"));
  }

  @Test
  public void shouldReturnNullForNegativeLength() {
    final String result = udf.lpad("foo", -1, "bar");
    assertThat(result, is(nullValue()));
  }
  
  @Test
  public void shouldReturnEmptyStringForZeroLength() {
    final String result = udf.lpad("foo", 0, "bar");
    assertThat(result, is(""));
  }

}
