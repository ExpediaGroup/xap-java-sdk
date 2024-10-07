import { PathOrFileDescriptor } from "fs"
import * as fs from 'node:fs'
import { Edit, Lang, NapiConfig, parse, SgNode } from "@ast-grep/napi"
import * as yaml from "yaml"

export abstract class Processor {
    rules: { (root: SgNode) : Edit[] }[]

    process(filePath: PathOrFileDescriptor): void {
        const source = fs.readFileSync(filePath, 'utf-8')
        const ast = parse(Lang.Kotlin, source)
        const root = ast.root()

        const edits = this.rules.flatMap((func) => func(root))
        const newSource = root.commitEdits(edits)
        fs.writeFileSync(filePath, newSource)
    }

    readRule(path: string, ruleName: string): NapiConfig {
        const rule = fs.readFileSync(`./rules/${path}/${ruleName}.yaml`, 'utf-8')
        return yaml.parse(rule)
    }
}
