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

public class ArrayUnionTest {
  private final ArrayUnion udf = new ArrayUnion();

  @SuppressWarnings("unchecked")
  @Test
  public void shouldUnionArraysOfLikeType() {
    final List<Object> result = udf.union(Arrays.asList("foo", " ", "bar"), Arrays.asList("baz"));
    assertThat(result, containsInAnyOrder("foo", " ", "bar", "baz"));
    assertThat(result, hasSize(4));
  }

  @SuppressWarnings("rawtypes")
  @Test
  public void shouldReturnNullForNullInput() {
    final List result = udf.union(null, null);
    assertThat(result, is(nullValue()));
  }

}
