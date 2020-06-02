package app

import calc_button.ButtonType
import calc_button.CButton
import calc_button.CalcButton
import calc_button.getCalculatorList
import logo.logo
import react.*
import react.dom.div
import react.dom.h2
import result.ResultText

interface AppState : RState {
    var currenttext: String
    var bufferNumber: Double
    var bufferOperator: ButtonType
}

class App : RComponent<RProps, AppState>() {
    override fun AppState.init() {
        currenttext = "0"
        bufferNumber = 0.0
        bufferOperator = ButtonType.CLEAR
    }

    private fun onButtonClicked(cButton: CButton) {
        var currtext = state.currenttext
        var bufferNo = state.bufferNumber
        var bufferOp = state.bufferOperator

        when (cButton.type) {
            ButtonType.CLEAR -> {
                currtext = "0"
                bufferNo = 0.0
                bufferOp = ButtonType.CLEAR

            }
            ButtonType.NUMBER -> {
                val parseNumber = currtext.toIntOrNull()
                currtext = if (bufferOp == ButtonType.EQUALS || parseNumber == 0 || parseNumber == null) {
                    cButton.content
                } else {
                    "$currtext${cButton.content}"
                }

            }
            ButtonType.EQUALS -> {
                currtext = (when (bufferOp) {
                    ButtonType.ADD -> bufferNo + currtext.toDouble()
                    ButtonType.SUBTRACT -> bufferNo - currtext.toDouble()
                    ButtonType.MULTIPLY -> bufferNo * currtext.toDouble()
                    ButtonType.DIVIDE -> (bufferNo / currtext.toDouble()).format(2)
                    else -> currtext
                }).toString()
                bufferOp = ButtonType.EQUALS
                bufferNo = currtext.toDouble()
            }
            else -> {
                bufferNo = when (bufferOp) {
                    ButtonType.ADD -> bufferNo + currtext.toDouble()
                    ButtonType.SUBTRACT -> bufferNo - currtext.toDouble()
                    ButtonType.MULTIPLY -> bufferNo * currtext.toDouble()
                    ButtonType.DIVIDE -> (bufferNo / currtext.toDouble()).format(2).toDouble()
                    else -> currtext.toDouble()
                }
                currtext = cButton.content
                bufferOp = cButton.type
            }
        }

        setState {
            currenttext = currtext
            bufferNumber = bufferNo
            bufferOperator = bufferOp
        }
    }

    private fun Double.format(digits: Int): String = this.asDynamic().toFixed(digits) as String

    override fun RBuilder.render() {
        div("App-header") {
            logo()
            h2 {
                +"Welcome to React with Kotlin/JS"
            }
        }
        div("Result-Text") {
            ResultText {
                text = state.currenttext
            }
        }
        div("App-intro") {
            getCalculatorList().map {
                CalcButton {
                    cButton = it
                    onButtonPressed = {
                        onButtonClicked(it)
                    }
                }
            }
        }
    }
}

fun RBuilder.app() = child(App::class) {}
