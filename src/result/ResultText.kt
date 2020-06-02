package result

import react.*
import react.dom.div

interface ResultTextProps : RProps {
    var text: String
}

class ResultText : RComponent<ResultTextProps, RState>() {
    override fun RBuilder.render() {
        div("Result-Outer") {
            div("Result-Inner") {
                +props.text
            }
        }
    }
}

fun RBuilder.ResultText(handler: ResultTextProps.() -> Unit): ReactElement {
    return child(ResultText::class) {
        this.attrs(handler)
    }
}