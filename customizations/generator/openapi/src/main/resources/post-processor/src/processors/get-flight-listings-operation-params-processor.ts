import * as fs from 'node:fs';
import {Edit, NapiConfig, SgNode} from '@ast-grep/napi';
import {Processor} from './processor';
import {RuleFunction} from './shared.types';

export class GetFlightListingsOperationParamsProcessor extends Processor {
  rules: RuleFunction[];
  id: String = 'get-flight-listings-operation-params';

  constructor() {
    super();
    this.rules = [
      this.removeSegmentBuilderMethods,
      this.importSegment,
      this.addSegmentsBuilderMethods,
    ].map(rule => rule.bind(this));
  }

  removeSegmentBuilderMethods(root: SgNode): Edit[] {
    const config = this.readRule('remove-segments-builder-methods');

    return root.findAll(config).map(node => {
      return node.replace('');
    });
  }

  importSegment(root: SgNode): Edit[] {
    const config = this.readRule('import-segment');

    return root.findAll(config).map(node => {
      const room = 'import com.expediagroup.sdk.xap.models.GetFlightListingsOperationSegmentParam';
      const header = node.getMatch('HEADER')?.text();

      return node.replace(`${room}\n${header}`);
    });
  }

  addSegmentsBuilderMethods(root: SgNode): Edit[] {
    const config = this.readRule('add-segments-builder-methods');

    const source = fs.readFileSync(
      './assets/templates/get-flight-listings-operation-params/segments.kt',
      'utf-8'
    );

    return root.findAll(config).map(node => {
      const func = node.getMatch('FUNC')?.text();
      return node.replace(`${source}\n${func}`);
    });
  }
}
