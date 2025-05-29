/**
 * Copyright (C) 2025 Expedia, Inc.
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
package com.expediagroup.sdk.xap.core

import com.expediagroup.sdk.core.auth.common.Credentials
import com.expediagroup.sdk.core.auth.oauth.OAuthCredentials
import com.expediagroup.sdk.core.common.getOrThrow

/**
 * Data class representing XAP OAuth credentials.
 *
 * @property partnerKey The XAP key used as key header in the SDK requests.
 * @property oAuthCredentials The OAuth credentials containing key and secret.
 */
data class XapOAuthCredentials(
    val partnerKey: String,
    val oAuthCredentials: OAuthCredentials,
) : Credentials {
    class Builder {
        private var key: String? = null
        private var secret: String? = null
        private var partnerKey: String? = null

        fun key(key: String) = apply { this.key = key }

        fun secret(secret: String) = apply { this.secret = secret }

        fun partnerKey(partnerKey: String) = apply { this.partnerKey = partnerKey }

        fun build(): XapOAuthCredentials {
            val key = this.key.getOrThrow {
                IllegalArgumentException("key must not be null")
            }

            val secret = this.secret.getOrThrow {
                IllegalArgumentException("secret must not be null")
            }

            val partnerKey = this.partnerKey.getOrThrow {
                IllegalArgumentException("partnerKey must not be null")
            }

            return XapOAuthCredentials(
                partnerKey = partnerKey,
                oAuthCredentials = OAuthCredentials(key, secret),
            )
        }
    }

    companion object {
        @JvmStatic
        fun builder(): Builder = Builder()
    }
}
