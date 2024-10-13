import {Edit, NapiConfig, SgNode} from '@ast-grep/napi';
import {Processor} from './processor';
import {RuleFunction} from './shared.types';

export class PenaltyRuleProcessor extends Processor {
  rules: RuleFunction[];
  id: String = 'penalty-rule';

  constructor() {
    super();
    this.rules = [
      this.changeClassParamType,
      this.changeBuilderMethodParamType,
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
}
