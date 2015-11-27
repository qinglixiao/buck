/*
 * Copyright 2015-present Facebook, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may obtain
 * a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.facebook.buck.cxx;

import com.google.common.base.Optional;

import java.util.regex.Pattern;

public class CxxInferSourceFilter {

  private final Optional<Pattern> blacklistRegex;

  CxxInferSourceFilter(InferBuckConfig config) {
    Optional<String> rawFilterRegex = config.getValue("blacklist_regex");

    blacklistRegex = rawFilterRegex.isPresent() ?
        Optional.of(Pattern.compile(rawFilterRegex.get())) : Optional.<Pattern>absent();
  }

  public boolean isBlacklisted(CxxSource source) {
    return blacklistRegex.isPresent() &&
        blacklistRegex.get().matcher(source.getPath().toString()).matches();
  }

}