package com.expediagroup.sdk.xap.generator.mustache

import com.samskivert.mustache.Mustache
import com.samskivert.mustache.Template
import java.io.Serializable
import java.io.Writer
import org.openapitools.codegen.CodegenResponse

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
