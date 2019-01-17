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

@UdfDescription(name = "array_union",
    description = "Returns an array of all the distinct elements from both input arrays.")
public class ArrayUnion {

  @SuppressWarnings({"unchecked", "rawtypes"})
  // @Udf
  public List union(final List input1, final List input2) {
    if (input1 == null || input2 == null) {
      return null;
    }
    final Set combined = Sets.newHashSet(input1);
    combined.addAll(input2);
    return Lists.newArrayList(combined);
  }

}
