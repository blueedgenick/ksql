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

import io.confluent.ksql.function.udf.UdfDescription;
import java.util.List;
import java.util.Map;

@UdfDescription(name = "cardinality",
    description = "Returns the number of keys in the specified map or entries in an array.")
public class Cardinality {

  // @Udf(description = "Returns the number of keys in the specified map.")
  public Integer cardinality(final Map<String, ?> input) {
    if (input == null) {
      return null;
    }
    return input.keySet().size();
  }

  // @Udf(description = "Returns the number of keys in the specified array.")
  public Integer cardinality(@SuppressWarnings("rawtypes") final List input) {
    if (input == null) {
      return null;
    }
    return input.size();
  }
}
