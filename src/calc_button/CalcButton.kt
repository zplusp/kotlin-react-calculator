package calc_button

import kotlinx.html.js.onClickFunction
import react.*
import react.dom.div

interface CalcButtonProps : RProps {
    var cButton: CButton
    var onButtonPressed: (CButton) -> Unit
}

class CalcButton : RComponent<CalcButtonProps, RState>() {
    private fun getCss(cButton: CButton): String {
        return when (cButton.type) {
            ButtonType.NUMBER -> "calc-button-number"
            ButtonType.EQUALS -> "calc-button-other"
            ButtonType.CLEAR -> "calc-button-other"
            else -> "calc-button-operator"
        }
    }

    override fun RBuilder.render() {
        div("calc-button-outer") {
            div("calc-button-inner  ${getCss(props.cButton)}") {
                attrs {
                    onClickFunction = {
                        props.onButtonPressed(props.cButton)
                    }
                }
                +props.cButton.content
            }
        }
    }
}

fun RBuilder.CalcButton(handler: CalcButtonProps.() -> Unit): ReactElement {
    return child(CalcButton::class) {
        this.attrs(handler)
    }
}