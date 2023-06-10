/*
 * Copyright 2021 Santos Zatarain Vera
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
 */

package mx.com.inftel.kotlinx.lazy

/**
 * Represents a (clearable) value with lazy initialization.
 */
interface ClearableLazy<out T> : Lazy<T> {

    /**
     * Gets the lazily initialized value of the current ClearableLazy instance.
     * Once the value was initialized it must not change during the rest of lifetime of this ClearableLazy instance,
     * unless is cleared. After a clear, the value will be initialized again.
     */
    override val value: T

    /**
     * Returns `true` if a value for this ClearableLazy instance has been already initialized, and `false` otherwise.
     * Once this function has returned `true` it stays `true` for the rest of lifetime of this ClearableLazy instance,
     * unless is cleared. After a clear, the value will be initialized again.
     */
    override fun isInitialized(): Boolean

    /**
     * Clear the value of this ClearableLazy instance. After a clear, the value will be initialized again.
     */
    fun clear()

}