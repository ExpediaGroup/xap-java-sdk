import * as fs from 'node:fs'
import { Edit, Lang, NapiConfig, parse, SgNode } from '@ast-grep/napi'
import { PathOrFileDescriptor } from 'fs'
import * as yaml from 'yaml'

function process (filePath: PathOrFileDescriptor): void {
    const source = fs.readFileSync(filePath, 'utf-8')
    const ast = parse(Lang.Kotlin, source)
    const root = ast.root()

    const edits = [removeRoomBuilderMethods, importRoom, addRoomsBuilderMethod].flatMap((func) => func(root))
    const newSource = root.commitEdits(edits)
    fs.writeFileSync(filePath, newSource)
}

function readRule (ruleName: string): NapiConfig {
    const rule = fs.readFileSync(`./rules/get-lodging-quotes-operation-params/${ruleName}.yaml`, 'utf-8')
    return yaml.parse(rule)
}

function removeRoomBuilderMethods(root: SgNode) : Edit[] {
    const config = readRule("remove-room-builder-methods")

    return root.findAll(config).map((node) => {
        return node.replace("")
    })
}

function importRoom(root: SgNode) : Edit[] {
    const config = readRule("import-room")

    return root.findAll(config).map((node) => {
        const room = 'import com.expediagroup.sdk.xap.models.Room'
        const header = node.getMatch('HEADER')?.text()

        return node.replace(`${room}\n${header}`)
    })
}

function addRoomsBuilderMethod(root: SgNode) : Edit[] {
    const config = readRule("add-rooms-builder-method")

    const source = fs.readFileSync('./resources/get-lodging-quotes-operation-params/rooms.kt', 'utf-8')

    return root.findAll(config).map((node => {
        const func = node.getMatch("FUNC")?.text()
        return node.replace(`${source}\n${func}`)
    }))
}

export { process as processGetLodgingQuotesOperationParams }
