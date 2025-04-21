import {PathOrFileDescriptor} from 'fs';
import * as fs from 'node:fs';
import {Lang, NapiConfig, parse} from '@ast-grep/napi';
import * as yaml from 'yaml';
import {RuleFunction} from './shared.types';

export abstract class Processor {
  abstract rules: RuleFunction[];
  abstract id: String;

  process(filePath: PathOrFileDescriptor): void {
    const source = fs.readFileSync(filePath, 'utf-8');
    const ast = parse(Lang.Kotlin, source);
    const root = ast.root();

    const edits = this.rules.flatMap(func => func(root));
    const newSource = root.commitEdits(edits);
    fs.writeFileSync(filePath, newSource);
  }

  readRule(ruleName: string): NapiConfig {
    const rule = fs.readFileSync(
      `./assets/rules/${this.id}/${ruleName}.yaml`,
      'utf-8'
    );
    return yaml.parse(rule);
  }
}
