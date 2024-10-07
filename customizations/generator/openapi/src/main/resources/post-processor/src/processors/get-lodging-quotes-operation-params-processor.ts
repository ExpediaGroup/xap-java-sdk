import * as fs from 'node:fs';
import {Edit, NapiConfig, SgNode} from '@ast-grep/napi';
import {Processor} from './processor';
import {RuleFunction} from './shared.types';

export class GetLodgingQuotesOperationParamsProcessor extends Processor {
  rules: RuleFunction[];

  constructor() {
    super();
    this.rules = [
      this.removeRoomBuilderMethods,
      this.importRoom,
      this.addRoomsBuilderMethod,
    ].map(rule => rule.bind(this));
  }

  readRule(ruleName: string): NapiConfig {
    return super.readRule('get-lodging-quotes-operation-params', ruleName);
  }

  removeRoomBuilderMethods(root: SgNode): Edit[] {
    const config = this.readRule('remove-room-builder-methods');

    return root.findAll(config).map(node => {
      return node.replace('');
    });
  }

  importRoom(root: SgNode): Edit[] {
    const config = this.readRule('import-room');

    return root.findAll(config).map(node => {
      const room = 'import com.expediagroup.sdk.xap.models.Room';
      const header = node.getMatch('HEADER')?.text();

      return node.replace(`${room}\n${header}`);
    });
  }

  addRoomsBuilderMethod(root: SgNode): Edit[] {
    const config = this.readRule('add-rooms-builder-method');

    const source = fs.readFileSync(
      './assets/templates/get-lodging-quotes-operation-params/rooms.kt',
      'utf-8'
    );

    return root.findAll(config).map(node => {
      const func = node.getMatch('FUNC')?.text();
      return node.replace(`${source}\n${func}`);
    });
  }
}
