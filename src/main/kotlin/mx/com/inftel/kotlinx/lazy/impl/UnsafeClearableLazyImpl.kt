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

package mx.com.inftel.kotlinx.lazy.impl

import mx.com.inftel.kotlinx.lazy.ClearableLazy

class UnsafeClearableLazyImpl<out T>(private val initializer: () -> T) : ClearableLazy<T> {

    private var _value: Any? = UNINITIALIZED

    override val value: T
        get() {
            if (_value === UNINITIALIZED) {
                _value = initializer()
            }
            @Suppress("UNCHECKED_CAST")
            return _value as T
        }

    override fun isInitialized(): Boolean {
        return _value !== UNINITIALIZED
    }

    override fun clear() {
        _value = UNINITIALIZED
    }

    companion object {

        private object UNINITIALIZED
    }
}