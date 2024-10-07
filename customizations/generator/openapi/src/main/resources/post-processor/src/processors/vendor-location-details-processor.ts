import {Edit, NapiConfig, SgNode} from '@ast-grep/napi';
import {Processor} from './processor';
import {RuleFunction} from './shared.types';

export class VendorLocationDetailsProcessor extends Processor {
  rules: RuleFunction[];

  constructor() {
    super();
    this.rules = [
      this.changeClassParamType,
      this.changeBuilderMethodParamType,
    ].map(rule => rule.bind(this));
  }

  readRule(ruleName: string): NapiConfig {
    return super.readRule('vendor-location-details', ruleName);
  }

  changeClassParamType(root: SgNode): Edit[] {
    const config = this.readRule('change-class-param-type');

    return root.findAll(config).map(node => {
      return node.replace('java.time.LocalDateTime');
    });
  }

  changeBuilderMethodParamType(root: SgNode): Edit[] {
    const config = this.readRule('change-builder-method-param-type');

    return root.findAll(config).map(node => {
      return node.replace('java.time.LocalDateTime');
    });
  }
}