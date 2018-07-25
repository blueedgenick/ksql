/**
 * Copyright 2018 Confluent Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 **/

package io.confluent.ksql.function.udf.string;

import io.confluent.ksql.function.udf.Udf;
import io.confluent.ksql.function.udf.UdfDescription;

@UdfDescription(name = "right", author = "Confluent",
    description = "Return the tightmost len chcarcters of the input string, or NULL if either"
    + " parameter is NULL.")
public class RightKudf {

  @Udf(description = "Returns .")
  public String right(final String input, final Integer len) {
    if (input == null || len == null) {
      return null;
    }
    if (len < 0) {
      return input;
    }
    int charsToKeep = Math.min(input.length(), len);
    return input.substring(input.length() - charsToKeep);
  }
}
