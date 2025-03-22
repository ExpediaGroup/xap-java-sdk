package com.expediagroup.sdk.xap.configuration

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonMapperBuilder

/**
 * Singleton instance of [ObjectMapper] configured for XAP.
 *
 * This instance is built using `jacksonMapperBuilder` and automatically registers all available modules.
 */
val XAP_OBJECT_MAPPER: ObjectMapper =
    jacksonMapperBuilder {
    }.build().findAndRegisterModules()
