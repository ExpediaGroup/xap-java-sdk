package com.expediagroup.sdk.xap.generator.mustache.lambda

import com.samskivert.mustache.Mustache
import com.samskivert.mustache.Template
import org.openapitools.codegen.CodegenResponse
import java.io.Serializable
import java.io.Writer

class AllowedMediaTypesLambda : Mustache.Lambda, Serializable {
    override fun execute(
        fragment: Template.Fragment,
        writer: Writer,
    ) {
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
