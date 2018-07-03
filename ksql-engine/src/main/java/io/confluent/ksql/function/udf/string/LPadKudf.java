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

@UdfDescription(name = "lpad", author = "Confluent",
    description = "Pads the input string, beginning from the left, with the specified padding"
        + " string until the target length is reached. If the input string is longer than the"
        + " specified target length it will be truncated. If the padding string is empty or"
        + " NULL then NULL is returned.")
public class LPadKudf {

  @Udf(description = "Returns a left-padded version of the input string.")
  public String lpad(final String input, final int targetLen, final String padding) {
    if (input == null) {
      return null;
    }
    if (padding == null || padding.isEmpty()) {
      return null;
    }
    StringBuilder sb = new StringBuilder(targetLen + padding.length());
    int padUpTo = Math.max(targetLen - input.length(), 0);
    for (int i = 0; i < padUpTo; i += padding.length()) {
      sb.append(padding);
    }
    sb.setLength(padUpTo);
    sb.append(input);
    sb.setLength(targetLen);
    return sb.toString();
  }
}
