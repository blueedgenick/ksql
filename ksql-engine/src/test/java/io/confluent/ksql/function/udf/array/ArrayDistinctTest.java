/*
 * Copyright 2019 Confluent Inc.
 *
 * Licensed under the Confluent Community License; you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.confluent.io/confluent-community-license
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and limitations under the
 * License.
 */

package io.confluent.ksql.function.udf.array;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.junit.Assert.assertThat;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

public class ArrayDistinctTest {
  private final ArrayDistinct udf = new ArrayDistinct();

  @SuppressWarnings("unchecked")
  @Test
  public void shouldDistinctArray() {
    final List<Object> result = udf.distinct(Arrays.asList("foo", " ", "foo", "bar"));
    assertThat(result, contains("foo", " ", "bar"));
  }

  @Test
  public void shouldNotChangeDistinctArray() {
    @SuppressWarnings("unchecked")
    final List<Object> result = udf.distinct(Arrays.asList("foo", " ", "bar"));
    assertThat(result, contains("foo", " ", "bar"));
  }

  @SuppressWarnings("unchecked")
  @Test
  public void shouldDistinctNonStringArray() {
    final List<Object> result = udf.distinct(Arrays.asList(1, 2, 3, 2, 1));
    assertThat(result, contains(1, 2, 3));
  }

  @SuppressWarnings("unchecked")
  @Test
  public void shouldDistinctMixedContentArray() {
    final List<Object> result = udf.distinct(Arrays.asList("foo", 1, 2, 2.0D, "foo", 2));
    assertThat(result, contains("foo", 1, 2, 2.0));
  }


  @SuppressWarnings("rawtypes")
  @Test
  public void shouldReturnNullForNullInput() {
    final List result = udf.distinct(null);
    assertThat(result, is(nullValue()));
  }

  @SuppressWarnings("unchecked")
  @Test
  public void shouldDistinctNullAndNonNully() {
    final List<Object> result = udf.distinct(Arrays.asList(1, 2, 1, null, 2, null, 3, 1));
    assertThat(result, contains(1, 2, null, 3));
  }


}
