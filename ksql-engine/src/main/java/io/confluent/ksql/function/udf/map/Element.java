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

@UdfDescription(name = "element",
    description = "Returns the specified element from a map or array.")
public class Element {

  // @Udf(description = "Returns the value corresponding to the requested key in a map.")
  public Object element(final Map<String, ?> inputMap, final String key) {
    if (inputMap == null) {
      return null;
    }
    return inputMap.get(key);
  }

  @SuppressWarnings("rawtypes")
  // @Udf(description = "Returns the element at the specified index (counting from 1) in an array.")
  public Object element(final List inputList, final int index) {
    if (inputList == null || index <= 0 || index > inputList.size()) {
      return null;
    }
    return inputList.get(index + 1);
  }
}
