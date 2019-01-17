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
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.Assert.assertThat;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

public class ArrayIntersectTest {
  private final ArrayIntersect udf = new ArrayIntersect();

  @SuppressWarnings("rawtypes")
  @Test
  public void shouldIntersectTwoArrays() {
    final List result =
        udf.intersect(Arrays.asList("foo", " ", "foo", "bar"), Arrays.asList("foo", "baz"));
    assertThat(result, is(Arrays.asList("foo")));
  }

  @SuppressWarnings("unchecked")
  @Test
  public void shouldIntersectNonStringArrays() {
    final List<Object> result = udf.intersect(Arrays.asList(1, 2, 3, 2, 1), Arrays.asList(1, 2, 2));
    assertThat(result, containsInAnyOrder(1, 2));
  }

  @SuppressWarnings("unchecked")
  @Test
  public void shouldIntersectMixedContentArrays() {
    final List<Object> result =
        udf.intersect(Arrays.asList("foo", 1, 2, 2.0D, "foo", 2), Arrays.asList(2.0D, "foo"));
    assertThat(result, containsInAnyOrder(2.0, "foo"));
    assertThat(result, hasSize(2));
  }

  @SuppressWarnings("rawtypes")
  @Test
  public void shouldReturnNullForNullInput() {
    final List result = udf.intersect(null, null);
    assertThat(result, is(nullValue()));
  }

  @SuppressWarnings("rawtypes")
  @Test
  public void shouldIntersectArraysContainingNulls() {
    final List result = udf.intersect(Arrays.asList(null, "bar"), Arrays.asList("foo"));
    assertThat(result.isEmpty(), is(true));
  }

  @SuppressWarnings("rawtypes")
  @Test
  public void shouldReturnNullForArraysOfOnlyNulls() {
    final List result = udf.intersect(Arrays.asList(null, null), Arrays.asList(null, null));
    assertThat(result.get(0), is(nullValue()));
  }

}
