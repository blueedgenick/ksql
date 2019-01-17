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
import io.confluent.ksql.function.udf.UdfDescription;
import java.util.List;
import java.util.Set;

@UdfDescription(name = "array_intersect",
    description = "Returns an array of all the distinct elements from the intersection of both"
        + " input arrays, or NULL if either input array is NULL.")
public class ArrayIntersect {

  @SuppressWarnings({"rawtypes", "unchecked"})
  // @Udf
  public List intersect(final List lhs, final List rhs) {
    if (lhs == null || rhs == null) {
      return null;
    }
    final Set intersection = Sets.newHashSet(lhs);
    intersection.retainAll(Sets.newHashSet(rhs));
    return Lists.newArrayList(intersection);
  }

}
