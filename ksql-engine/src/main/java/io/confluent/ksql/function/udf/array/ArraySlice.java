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

import io.confluent.ksql.function.udf.UdfDescription;
import java.util.List;

@UdfDescription(name = "array_slice",
    description = "Returns a subsection of"
        + "an array, beginning at the specified offset (counted from 0), and of requested length. "
        + "If either the requested length or starting offset are negative then the entire array is"
        + " returned.")
public class ArraySlice {

  @SuppressWarnings("rawtypes")
  // @Udf
  public List slice(final List input, final int start, final int length) {
    if (input == null) {
      return null;
    }
    if (start < 0 || length < 0) {
      return input;
    }
    return input.subList(start, start + length);
  }
}
