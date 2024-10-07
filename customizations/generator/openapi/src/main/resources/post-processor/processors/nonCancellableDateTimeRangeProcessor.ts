import { Edit, NapiConfig, SgNode } from '@ast-grep/napi'
import { Processor } from "./processor"

export class NonCancellableDateTimeRangeProcessor extends Processor {
    constructor() {
        super()
        this.rules = [
            this.changeClassParamType,
            this.changeBuilderMethodParamType
        ].map(rule => rule.bind(this))
    }

    readRule(ruleName: string): NapiConfig {
        return super.readRule("non-cancellable-date-time-range", ruleName);
    }

    changeClassParamType(root: SgNode) : Edit[] {
        const config = this.readRule("change-class-params-type")

        return root.findAll(config).map((node) => {
            return node.replace("java.time.LocalDateTime")
        })
    }

    changeBuilderMethodParamType(root: SgNode) : Edit[] {
        const config = this.readRule("change-builder-method-params-type")

        return root.findAll(config).map((node) => {
            return node.replace("java.time.LocalDateTime")
        })
    }

}
