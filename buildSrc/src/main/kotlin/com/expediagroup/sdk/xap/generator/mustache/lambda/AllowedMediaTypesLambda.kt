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
package com.expediagroup.sdk.xap.generator.mustache.lambda

import com.samskivert.mustache.Mustache
import com.samskivert.mustache.Template
import org.openapitools.codegen.CodegenResponse
import java.io.Serializable
import java.io.Writer

/**
 * Mustache lambda that filters out XML media types from the accept header.
 */
class AllowedMediaTypesLambda : Mustache.Lambda, Serializable {
    override fun execute(fragment: Template.Fragment, writer: Writer) {
        val response: CodegenResponse = fragment.context() as CodegenResponse

        if (response.is2xx) {
            val mediaTypes: List<String> =
                response.content.keys.filter {
                    !it.contains("xml", ignoreCase = true)
                }

            val context = mapOf("mediaTypes" to mediaTypes)
            fragment.execute(context, writer)
        }
    }
}
