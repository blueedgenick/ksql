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

public class RightKudfTest {
  private final RightKudf udf = new RightKudf();

  @Test
  public void happyPath() {
    final String result = udf.right("hello world", 4);
    assertThat(result, is("orld"));
  }

  @Test
  public void shouldReturnNullForNullInput() {
    final String result = udf.right(null, 4);
    assertThat(result, is(nullValue()));
  }

  @Test
  public void shouldReturnNullForEmptyInput() {
    final String result = udf.right("", 4);
    assertThat(result, is(nullValue()));
  }

  @Test
  public void shouldReturnNullForNegativeLength() {
    final String result = udf.right("hello world", -1);
    assertThat(result, is(nullValue()));
  }
  
  @Test
  public void shouldReturnXXXXXXXForZeroLength() {
    final String result = udf.right("hello world", 0);
    assertThat(result, is("hello world"));
  }

}
