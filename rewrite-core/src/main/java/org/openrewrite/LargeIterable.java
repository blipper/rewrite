/*
 * Copyright 2023 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * https://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.openrewrite;

import org.openrewrite.internal.lang.Nullable;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.UnaryOperator;

/**
 * A large iterable is an iterable that may be too large
 * to be materialized in memory. It contains operations for
 * filtering and mapping that are optimized for large data sets.
 *
 * @param <T> The type of the elements in the iterable.
 */
public interface LargeIterable<T> extends Iterable<T> {

    LargeIterable<T> map(@Nullable List<T> ls, BiFunction<Integer, T, T> map);

    default LargeIterable<T> map(@Nullable List<T> ls, UnaryOperator<T> map) {
        return map(ls, (i, t) -> map.apply(t));
    }

    LargeIterable<T> flatMap(@Nullable List<T> ls, BiFunction<Integer, T, Object> flatMap);

    LargeIterable<T> concat(@Nullable T t);

    LargeIterable<T> concatAll(@Nullable List<? extends T> ls2);
}
