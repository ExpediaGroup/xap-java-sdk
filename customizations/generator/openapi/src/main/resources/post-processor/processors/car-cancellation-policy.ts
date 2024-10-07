import * as fs from 'node:fs'
import { Edit, Lang, NapiConfig, parse, SgNode } from '@ast-grep/napi'
import { PathOrFileDescriptor } from 'fs'
import * as yaml from 'yaml'

function process (filePath: PathOrFileDescriptor): void {
    const source = fs.readFileSync(filePath, 'utf-8')
    const ast = parse(Lang.Kotlin, source)
    const root = ast.root()

    const edits = [changeClassParamType, changeBuilderMethodParamType].flatMap((func) => func(root))
    const newSource = root.commitEdits(edits)
    fs.writeFileSync(filePath, newSource)
}

function readRule (ruleName: string): NapiConfig {
    const rule = fs.readFileSync(`./rules/car-cancellation-policy/${ruleName}.yaml`, 'utf-8')
    return yaml.parse(rule)
}

function changeClassParamType(root: SgNode) : Edit[] {
    const config = readRule("change-class-param-type")

    return root.findAll(config).map((node) => {
        return node.replace("java.time.LocalDateTime")
    })
}

function changeBuilderMethodParamType(root: SgNode) : Edit[] {
    const config = readRule("change-builder-method-param-type")

    return root.findAll(config).map((node) => {
        return node.replace("java.time.LocalDateTime")
    })
}

export { process as processCarCancellationPolicy }
