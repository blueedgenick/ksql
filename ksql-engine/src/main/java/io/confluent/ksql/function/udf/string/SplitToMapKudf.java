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

import java.util.Map;
import com.google.common.collect.Maps;
import io.confluent.ksql.function.udf.Udf;
import io.confluent.ksql.function.udf.UdfDescription;

@UdfDescription(name = "split_to_map", author = "Confluent",
    description = "Returns a version of the input string with every character replaced by a mask."
        + " Default masking rules will replace all upper-case characters with 'X', all lower-case"
        + " characters with 'x', all digits with 'n', and any other character with '-'.")
public class SplitToMapKudf {

  @Udf(description = "Returns a masked version of the input string. All characters of the input"
      + " will be replaced according to the default masking rules.")
  public Map<String, String> split(final String input, final String entryDelimiter,
      final String kvDelimiter) {
    if (input == null) {
      return null;
    }
    if (entryDelimiter == null || entryDelimiter.isEmpty()) {
      return Maps.newHashMapWithExpectedSize(0);
    }
    //    final int delim = separator.codePointAt(0);
    // NPD change to do manual indexOf searching and allow len > 1
    //  return Lists.newArrayList(input.split(String.valueOf(delim)));
    return Maps.newHashMapWithExpectedSize(0);
  }

}
