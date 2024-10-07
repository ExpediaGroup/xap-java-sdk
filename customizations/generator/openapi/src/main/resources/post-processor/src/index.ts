import {CarCancellationPolicyProcessor} from './processors/car-cancellation-policy-processor';
import {GetLodgingListingsOperationParamsProcessor} from './processors/get-lodging-listings-operation-params-processor';
import {GetLodgingQuotesOperationParamsProcessor} from './processors/get-lodging-quotes-operation-params-processor';
import {NonCancellableDateTimeRangeProcessor} from './processors/non-cancellable-date-time-range-processor';
import {PenaltyRuleProcessor} from './processors/penalty-rule-processor';
import {VendorLocationDetailsProcessor} from './processors/vendor-location-details-processor';

import * as path from 'path';

const args = process.argv.slice(2);
const filePath = args[0];
const fileName = path.parse(filePath).name;

switch (fileName) {
  case 'CarCancellationPolicy':
    new CarCancellationPolicyProcessor().process(filePath);
    break;
  case 'GetLodgingListingsOperationParams':
    new GetLodgingListingsOperationParamsProcessor().process(filePath);
    break;
  case 'GetLodgingQuotesOperationParams':
    new GetLodgingQuotesOperationParamsProcessor().process(filePath);
    break;
  case 'NonCancellableDateTimeRange':
    new NonCancellableDateTimeRangeProcessor().process(filePath);
    break;
  case 'PenaltyRule':
    new PenaltyRuleProcessor().process(filePath);
    break;
  case 'VendorLocationDetails':
    new VendorLocationDetailsProcessor().process(filePath);
    break;
}
