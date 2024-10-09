import { CarCancellationPolicyProcessor } from './processors/carCancellationPolicyProcessor'
import { GetLodgingListingsOperationParamsProcessor } from './processors/getLodgingListingsOperationParamsProcessor'
import { GetLodgingQuotesOperationParamsProcessor } from './processors/getLodgingQuotesOperationParamsProcessor'
import { NonCancellableDateTimeRangeProcessor } from './processors/nonCancellableDateTimeRangeProcessor'
import { PenatlyRuleProcessor } from './processors/penatlyRuleProcessor'
import { VendorLocationDetailsProcessor } from './processors/vendorLocationDetailsProcessor'

import * as path from 'path'

const args = process.argv.slice(2)
const filePath = args[0]
const fileName = path.parse(filePath).name

switch (fileName) {
    case 'CarCancellationPolicy':
        new CarCancellationPolicyProcessor().process(filePath)
        break
    case 'GetLodgingListingsOperationParams':
        new GetLodgingListingsOperationParamsProcessor().process(filePath)
        break
    case 'GetLodgingQuotesOperationParams':
        new GetLodgingQuotesOperationParamsProcessor().process(filePath)
        break
    case 'NonCancellableDateTimeRange':
        new NonCancellableDateTimeRangeProcessor().process(filePath)
        break
    case 'PenaltyRule':
        new PenatlyRuleProcessor().process(filePath)
        break
    case 'VendorLocationDetails':
        new VendorLocationDetailsProcessor().process(filePath)
        break
}
