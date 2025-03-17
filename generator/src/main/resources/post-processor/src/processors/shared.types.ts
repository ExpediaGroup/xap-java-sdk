import {Edit, SgNode} from '@ast-grep/napi';

export type RuleFunction = {(root: SgNode): Edit[]};
