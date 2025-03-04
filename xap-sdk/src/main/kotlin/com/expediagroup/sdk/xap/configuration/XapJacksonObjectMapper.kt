package com.expediagroup.sdk.xap.configuration

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonMapperBuilder

val XAP_OBJECT_MAPPER: ObjectMapper =
    jacksonMapperBuilder {
    }.build().findAndRegisterModules()
