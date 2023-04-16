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
package org.openrewrite.internal;

import lombok.RequiredArgsConstructor;
import org.openrewrite.LargeIterable;
import org.openrewrite.internal.lang.Nullable;

import java.util.Iterator;
import java.util.List;
import java.util.function.BiFunction;

@RequiredArgsConstructor
public class InMemoryLargeIterable<T> implements LargeIterable<T> {
    private final List<T> ls;

    @Override
    public LargeIterable<T> map(@Nullable List<T> ls, BiFunction<Integer, T, T> map) {
        return new InMemoryLargeIterable<>(ListUtils.map(ls, map));
    }

    @Override
    public LargeIterable<T> flatMap(@Nullable List<T> ls, BiFunction<Integer, T, Object> flatMap) {
        return new InMemoryLargeIterable<>(ListUtils.flatMap(ls, flatMap));
    }

    @Override
    public LargeIterable<T> concat(@Nullable T t) {
        return new InMemoryLargeIterable<>(ListUtils.concat(ls, t));
    }

    @Override
    public LargeIterable<T> concatAll(@Nullable List<? extends T> ls2) {
        return new InMemoryLargeIterable<>(ListUtils.concatAll(ls, ls2));
    }

    @Override
    public Iterator<T> iterator() {
        return ls.iterator();
    }
}
