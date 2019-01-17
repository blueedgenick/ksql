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

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import io.confluent.ksql.function.udf.Udf;
import io.confluent.ksql.function.udf.UdfDescription;
import java.util.List;
import java.util.Set;

@UdfDescription(name = "array_distinct",
    description = "Returns an array of all the distinct values, including NULL if present, from"
        + " the input array."
        + " The output array elements will be in order of their first occurrence in the input."
        + " Returns NULL if the input array is NULL.")
public class ArrayDistinct {

  @SuppressWarnings({"rawtypes", "unchecked"})
  @Udf
  public List distinct(final List input) {
    if (input == null) {
      return null;
    }
    final Set distinctVals = Sets.newHashSetWithExpectedSize(input.size());
    final List output = Lists.newArrayList();
    input.forEach(entry -> {
      if (!distinctVals.contains(entry)) {
        output.add(entry);
        distinctVals.add(entry);
      }
    });
    return output;
  }

}
