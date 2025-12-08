package com.navarrorncf.secretSanta

import com.navarrorncf.secretSanta.system.ISystemProcessHandler
import com.navarrorncf.secretSanta.system.IUserInterfaceHandler

fun getNames(ui: IUserInterfaceHandler, systemProcessHandler: ISystemProcessHandler): Array<String> {
    ui.write("Let's begin! Please type the participants' names separated by comma:")
    val (ok, result) = validateNames(ui.read())

    if (!ok) {
        ui.write("The application cannot proceed due to issues with provided list of names.")
        ui.write(result)
        systemProcessHandler.exit(1)
    }

    return result.split(",").toTypedArray()
}

private fun validateNames(namesInput: String): Pair<Boolean, String> {
    val namesList = namesInput.split(",")
        .map { it.trim() }
        .filter { it.isNotEmpty() }
    val issues = performValidations(namesList)

    return when {
        issues.isNotEmpty() -> Pair(false, issues)
        else -> Pair(true, namesList.joinToString(","))
    }
}

enum class ErrorCode(val message: String) {
    NAMES_BLANK("Name list cannot be empty."),
    NAMES_OUT_OF_RANGE("Name list should contain 3 to 10 participants."),
    NAMES_REPEAT("Names should not repeat."),
}

private fun performValidations(names: List<String>): String {
    val validations: List<(toValidate: List<String>) -> String> = listOf(
        { isNotEmpty(it) },
        { hasValidNamesAmount(it) },
        { hasNoRepeatedNames(it) },
    )

    return validations
        .map { validate -> validate(names) }
        .filter { issues -> issues.isNotEmpty() }
        .joinToString("\n")
}

private fun isNotEmpty(list: List<String>) =
    if (list.isEmpty()) ErrorCode.NAMES_BLANK.message else ""

private fun hasValidNamesAmount(list: List<String>) =
    if (list.size !in 3..10) ErrorCode.NAMES_OUT_OF_RANGE.message else ""

private fun hasNoRepeatedNames(namesList: List<String>): String {
    val namesSet = mutableSetOf<String>()
    val repeatedNames = mutableSetOf<String>()

    namesList.forEach {
        when {
            namesSet.contains(it) -> repeatedNames.add(it)
            else -> namesSet.add(it)
        }
    }

    return when {
        repeatedNames.isEmpty() -> ""
        else -> ErrorCode.NAMES_REPEAT.message +
                "\nThese names appear more than once: ${repeatedNames.joinToString()}"
    }
}