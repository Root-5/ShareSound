import ui.Window
import functional.FindUsers

fun main(args: Array<String>) {
    val window = Window()
    window.launch()
    val findClone = FindUsers("224.0.0.0", 4441)
}