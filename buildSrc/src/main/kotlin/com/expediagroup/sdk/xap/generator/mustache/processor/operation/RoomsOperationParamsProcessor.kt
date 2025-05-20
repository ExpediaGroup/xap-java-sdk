package com.expediagroup.sdk.xap.generator.mustache.processor.operation

import org.openapitools.codegen.CodegenOperation
import java.io.Serializable

/**
 * Marks individual `roomN…` query parameters for replacement and injects
 * a consolidated `rooms(...)` builder helper into targeted operations.
 *
 * For each `CodegenOperation` whose `baseName` is in [targetedOperations]:
 *  1. Finds query parameters matching [roomRegex].
 *  2. Flags each matched parameter with `x-replacedByBuilderHelper`.
 *  3. If any were found, sets:
 *     - `x-needsBuilderHelper` to true, and
 *     - `x-builderHelpersCode` to the Kotlin implementation returned by [roomsHelperTextImpl].
 *
 * Usage: register the processor in the generator's config (`com.expediagroup.sdk.openapigenerator`):
 * - `additionalProperties.put("operationProcessors", listOf(RoomsOperationParamsProcessor()))`
 *
 * **Note: Must be used with the default EG SDK mustache templates**
 */
class RoomsOperationParamsProcessor : Serializable, (CodegenOperation) -> CodegenOperation {

    /** Regex to identify room parameters like `room1Adult`, `room2ChildAges`, etc. */
    private val roomRegex = Regex("""^room\d+\w*$""", RegexOption.IGNORE_CASE)

    /** List of operation names where the rooms helper should be applied. */
    private val targetedOperations = listOf("GetLodgingQuotes", "GetLodgingListings")

    /**
     * Scans `queryParams` for matches against [roomRegex].
     * Flags each match, and if any are found, injects the builder helper code.
     *
     * @param operation the operation to inspect and modify
     * @return the same [CodegenOperation], with vendor extensions set as needed
     */

    override fun invoke(operation: CodegenOperation): CodegenOperation = operation.apply {
        if (baseName in targetedOperations) {
            queryParams.filter {
                roomRegex.matches(it.paramName)
            }.takeIf {
                it.isNotEmpty()
            }?.also { rooms ->
                rooms.forEach { p ->
                    p.vendorExtensions["x-replacedByBuilderHelper"] = true
                }
                vendorExtensions["x-needsBuilderHelper"] = true
                vendorExtensions["x-builderHelpersCode"] = roomsHelperTextImpl().plus("\n")
            }
        }
    }

    /**
     * Returns the Kotlin code for the `rooms(...)` builder extension.
     * This snippet replaces individual `roomN…` methods with a loop over a `List<Room>`.
     */
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
