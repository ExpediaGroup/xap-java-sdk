import { processCarCancellationPolicy } from "./processors/car-cancellation-policy";
import { processGetLodgingListingsOperationParams } from './processors/get-lodging-listings-operation-params'
import { processGetLodgingQuotesOperationParams } from "./processors/get-lodging-quotes-operation-params";
import { processNonCancellableDateTimeRange } from "./processors/non-cancellable-date-time-range";
import { processPenaltyRule } from "./processors/penalty-rule"
import { processVendorLocationDetails} from "./processors/vendor-location-details";

import * as path from 'path'

const args = process.argv.slice(2)
const filePath = args[0]
const fileName = path.parse(filePath).name

switch (fileName) {
    case 'CarCancellationPolicy':
        processCarCancellationPolicy(filePath)
        break
    case 'GetLodgingListingsOperationParams':
        processGetLodgingListingsOperationParams(filePath)
        break
    case 'GetLodgingQuotesOperationParams':
        processGetLodgingQuotesOperationParams(filePath)
        break
    case 'NonCancellableDateTimeRange':
        processNonCancellableDateTimeRange(filePath)
        break
    case 'PenaltyRule':
        processPenaltyRule(filePath)
        break
    case 'VendorLocationDetails':
        processVendorLocationDetails(filePath)
        break
}
