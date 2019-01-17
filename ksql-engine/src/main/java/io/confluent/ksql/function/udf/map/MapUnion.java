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
import java.util.Map;

@UdfDescription(name = "map_concat",
    description = "Returns the union of the specified maps. If a given key is present in more"
        + " than one input map then the value for that key in the output map will be from the"
        + " last input map where it is found.")
public class MapUnion {

  // @Udf
  public Map<String, Object> union(final Map<String, Object> input1,
      final Map<String, Object> input2) {
    // TODO once UDF framework supports varargs, extend this method to take 1..n input maps
    if (input1 == null || input2 == null) {
      return null;
    }
    input1.putAll(input2);
    return input1;
  }

}
