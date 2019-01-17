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

package io.confluent.ksql.function.udf.map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import com.google.common.collect.Maps;
import java.util.Map;
import org.junit.Test;

public class MapUnionTest {
  private final MapUnion udf = new MapUnion();

  @SuppressWarnings("rawtypes")
  @Test
  public void shouldUnionNonEmptyMaps() {
    final Map<String, Object> input1 = Maps.newHashMap();
    input1.put("foo", "bar");
    input1.put("baz", "baloney");
    final Map<String, Object> input2 = Maps.newHashMap();
    input2.put("one", 1);
    input2.put("two", 2);
    input2.put("three", 3);
    final Map result = udf.union(input1, input2);
    assertThat(result.size(), is(5));
    assertThat(result.get("foo"), is("bar"));
    assertThat(result.get("two"), is(2));
  }

  @SuppressWarnings("rawtypes")
  @Test
  public void shouldReturnNullForNullInput() {
    final Map result = udf.union(null, null);
    assertThat(result, is(nullValue()));
  }

}
