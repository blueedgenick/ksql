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
import io.confluent.ksql.function.udf.UdfDescription;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@UdfDescription(name = "array_except", author = "Confluent",
    description = "Returns an array of all the elements in an array except for those also present"
        + " in a second array. Any duplicates are removed. Returns NULL if either input is NULL")
public class ArrayExcept {

  @SuppressWarnings({"rawtypes", "unchecked"})
  // @Udf
  public List except(final List lhs, final List rhs) {
    if (lhs == null || rhs == null) {
      return null;
    }
    final Set distinct =
        (Set) lhs.stream().filter(e -> !rhs.contains(e)).collect(Collectors.toSet());
    final List result = Lists.newArrayList(distinct);
    return result;
  }
}