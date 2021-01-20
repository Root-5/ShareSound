import ui.Window
import functional.FindUsers

fun main(args: Array<String>) {
    val findClone = FindUsers("224.0.0.0", 4441)
    val fc = Thread(findClone)
    fc.start()
    val window = Window()
    window.run()
}