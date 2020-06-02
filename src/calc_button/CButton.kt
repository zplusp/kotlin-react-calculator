package calc_button

data class CButton(
        val content: String,
        val type: ButtonType
)

enum class ButtonType {
    NUMBER, ADD, SUBTRACT, MULTIPLY, DIVIDE, EQUALS, CLEAR
}

fun getCalculatorList(): List<CButton> {
    return listOf(
            CButton(content = "7", type = ButtonType.NUMBER),
            CButton(content = "8", type = ButtonType.NUMBER),
            CButton(content = "9", type = ButtonType.NUMBER),
            CButton(content = "/", type = ButtonType.DIVIDE),
            CButton(content = "4", type = ButtonType.NUMBER),
            CButton(content = "5", type = ButtonType.NUMBER),
            CButton(content = "6", type = ButtonType.NUMBER),
            CButton(content = "x", type = ButtonType.MULTIPLY),
            CButton(content = "1", type = ButtonType.NUMBER),
            CButton(content = "2", type = ButtonType.NUMBER),
            CButton(content = "3", type = ButtonType.NUMBER),
            CButton(content = "-", type = ButtonType.SUBTRACT),
            CButton(content = "AC", type = ButtonType.CLEAR),
            CButton(content = "0", type = ButtonType.NUMBER),
            CButton(content = "=", type = ButtonType.EQUALS),
            CButton(content = "+", type = ButtonType.ADD)
    )
}