package com.expediagroup.sdk.xap.generator.mustache.processor.operation

import org.openapitools.codegen.CodegenOperation
import java.io.Serializable

/**
 * Finds properties named room1, room2, … and replaces them with
 * a single `rooms: List<Room>` property.
 */
class RoomsOperationParamsProcessor : Serializable, (CodegenOperation) -> CodegenOperation {

    private val roomRegex = Regex("""^room\d+\w*$""", RegexOption.IGNORE_CASE)
    private val targetedOperations = listOf("GetLodgingQuotes", "GetLodgingListings")

    override fun invoke(operation: CodegenOperation): CodegenOperation {
        if (targetedOperations.contains(operation.baseName)) {
            var needsRoomsBuilderHelper = false

            operation.queryParams.forEach {
                if (roomRegex.matches(it.paramName)) {
                    needsRoomsBuilderHelper = true
                    it.vendorExtensions["x-replacedByBuilderHelper"] = true
                }
            }

            if (needsRoomsBuilderHelper) {
                operation.vendorExtensions["x-needsBuilderHelper"] = true
                operation.vendorExtensions["x-builderHelpersCode"] = "${roomsHelperTextImpl()} \n"
            }
        }

        return operation
    }

    private fun roomsHelperTextImpl() = """
        fun rooms(rooms: List<com.expediagroup.sdk.xap.models.Room>) =
            apply {
                if (rooms.size > 8) {
                    throw com.expediagroup.sdk.rest.exception.client.PropertyConstraintViolationException(
                        constraintViolations = listOf("rooms: size must be between 0 and 8")
                    )
                }

                rooms.elementAtOrNull(0)?.let {
                    this.room1Adults = it.adults
                    this.room1ChildAges = it.childAges?.map { item -> item.toString() }
                }

                rooms.elementAtOrNull(1)?.let {
                    this.room2Adults = it.adults
                    this.room2ChildAges = it.childAges?.map { item -> item.toString() }
                }

                rooms.elementAtOrNull(2)?.let {
                    this.room3Adults = it.adults
                    this.room3ChildAges = it.childAges?.map { item -> item.toString() }
                }

                rooms.elementAtOrNull(3)?.let {
                    this.room4Adults = it.adults
                    this.room4ChildAges = it.childAges?.map { item -> item.toString() }
                }

                rooms.elementAtOrNull(4)?.let {
                    this.room5Adults = it.adults
                    this.room5ChildAges = it.childAges?.map { item -> item.toString() }
                }

                rooms.elementAtOrNull(5)?.let {
                    this.room6Adults = it.adults
                    this.room6ChildAges = it.childAges?.map { item -> item.toString() }
                }

                rooms.elementAtOrNull(6)?.let {
                    this.room7Adults = it.adults
                    this.room7ChildAges = it.childAges?.map { item -> item.toString() }
                }

                rooms.elementAtOrNull(7)?.let {
                    this.room8Adults = it.adults
                    this.room8ChildAges = it.childAges?.map { item -> item.toString() }
                }
            }
    """.trimIndent()
}
