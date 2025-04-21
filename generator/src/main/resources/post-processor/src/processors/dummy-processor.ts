import {Processor} from './processor';
import {RuleFunction} from './shared.types';
import { SgNode } from '@ast-grep/napi';

class DummyProcessor extends Processor {
  id = 'dummy';
  rules: RuleFunction[] = [
    (root: SgNode) => {
      // Dummy rule: No edits
      return [];
    }
  ];
}

export default DummyProcessor;
