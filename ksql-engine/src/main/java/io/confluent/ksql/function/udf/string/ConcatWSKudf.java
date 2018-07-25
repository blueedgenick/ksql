/**
 * Copyright 2018 Confluent Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 **/

package io.confluent.ksql.function.udf.string;

import java.util.Arrays;
import io.confluent.ksql.function.KsqlFunctionException;
import io.confluent.ksql.function.udf.Kudf;

public class ConcatWSKudf implements Kudf {

  @Override
  public Object evaluate(Object... args) {
    if (args.length < 3) {
      throw new KsqlFunctionException("concat_ws function expects at least two input arguments.");
    }
    if (Arrays.asList(args).contains(null)) {
      return null;
    }
    String separator = args[0].toString();
    StringBuilder outputBuf = new StringBuilder(args[1].toString());
    for (int i = 2; i < args.length; i++) {
      outputBuf.append(separator).append(args[i].toString());
    }
    return outputBuf.toString();
  }
}
