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
package org.openrewrite.java.trait.util;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Objects;
import java.util.function.Function;

/**
 * @param <E> Failing type
 * @param <V> Success type
 */
public interface Validation<E, V> {
    /**
     * @return true if the validation is invalid, false otherwise
     */
    default boolean isFail() {
        return !isSuccess();
    }

    /**
     * @return true if the validation is successful, false otherwise
     */
    boolean isSuccess();

    /**
     * @return the failure if the validation is invalid, or an exception if the validation is valid
     */
    E fail();

    /**
     * @return the value if the validation is valid, or an exception if the validation is invalid
     */
    V success();

    default V orSuccess(V defaultValue) {
        return isSuccess() ? success() : defaultValue;
    }

    default V orSuccess(Function<E, V> f) {
        return isSuccess() ? success() : f.apply(fail());
    }

    default <A> Validation<E, A> map(Function<V, A> f) {
        return isFail() ? fail(fail()) : success(f.apply(success()));
    }

    default <A> Validation<E, A> bind(Function<V, Validation<E, A>> f) {
        return isSuccess() ? f.apply(success()) : fail(fail());
    }

    static <E, V> Validation<E, V> success(V value) {
        return new ValidationSuccess<>(value);
    }

    static <E, V> Validation<E, V> fail(E error) {
        return new ValidationFail<>(error);
    }
}

@ToString
@EqualsAndHashCode
final class ValidationSuccess<E, V> implements Validation<E, V> {
    private final V value;

    ValidationSuccess(V value) {
        this.value = Objects.requireNonNull(value, "value cannot be null");
    }

    @Override
    public boolean isSuccess() {
        return true;
    }

    @Override
    public E fail() {
        throw new IllegalStateException("Cannot fail a successful validation");
    }

    @Override
    public V success() {
        return value;
    }
}

@ToString
@EqualsAndHashCode
final class ValidationFail<E, V> implements Validation<E, V> {
    private final E error;

    ValidationFail(E error) {
        this.error = Objects.requireNonNull(error, "error cannot be null");
    }

    @Override
    public boolean isSuccess() {
        return false;
    }

    @Override
    public E fail() {
        return error;
    }

    @Override
    public V success() {
        throw new IllegalStateException("Cannot get the success value of a failed validation");
    }
}
