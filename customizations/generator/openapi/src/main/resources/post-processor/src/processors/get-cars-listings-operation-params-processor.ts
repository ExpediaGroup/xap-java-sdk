import {Edit, NapiConfig, SgNode} from '@ast-grep/napi';
import {Processor} from './processor';
import {RuleFunction} from './shared.types';

export class GetCarsListingsOperationParamsProcessor extends Processor {
  rules: RuleFunction[];
  id: String = 'get-cars-listings-operation-params';

  constructor() {
    super();
    this.rules = [
      this.changeClassParamType,
      this.changeBuilderMethodParamType,
      this.importChronoUnit,
      this.truncatePickupDropoffTimes,
    ].map(rule => rule.bind(this));
  }

  changeClassParamType(root: SgNode): Edit[] {
    const config = this.readRule('change-class-params-type');

    return root.findAll(config).map(node => {
      return node.replace('java.time.LocalDateTime');
    });
  }

  changeBuilderMethodParamType(root: SgNode): Edit[] {
    const config = this.readRule('change-builder-method-params-type');

    return root.findAll(config).map(node => {
      return node.replace('java.time.LocalDateTime');
    });
  }

  importChronoUnit(root: SgNode): Edit[] {
    const config = this.readRule('import-chrono-unit');

    return root.findAll(config).map(node => {
      const room = 'import java.time.temporal.ChronoUnit';
      const header = node.getMatch('HEADER')?.text();

      return node.replace(`${room}\n${header}`);
    });

  }

  truncatePickupDropoffTimes(root: SgNode): Edit[] {
    const config = this.readRule('truncate-pickup-dropoff-times');

    return root.findAll(config).map(node => {
      return node.replace('it.truncatedTo(ChronoUnit.MINUTES).toString()');
    });
  }
}
