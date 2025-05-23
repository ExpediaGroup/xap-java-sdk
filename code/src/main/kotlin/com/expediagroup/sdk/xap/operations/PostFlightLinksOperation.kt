/*
 * Copyright (C) 2022 Expedia, Inc.
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
package com.expediagroup.sdk.xap.operations

import com.expediagroup.sdk.core.model.Operation
import com.expediagroup.sdk.xap.models.FlightLinksRequest

/**
 * Request for flight links
 * @property requestBody [FlightLinksRequest]
 * @property params [PostFlightLinksOperationParams]
 */
class PostFlightLinksOperation(
    params: PostFlightLinksOperationParams,
    requestBody: FlightLinksRequest?
) : Operation<
        FlightLinksRequest
    >(
        "/flights/links",
        "POST",
        "postFlightLinks",
        requestBody,
        params
    )
