package ui

import javafx.application.Application
import javafx.scene.Group
import javafx.scene.Scene
import javafx.stage.Stage

class Window : Application() {

    fun launch() {
        Application.launch()
    }

    override fun start(p0: Stage) {
        val group = Group()
        val scene = Scene(group)

        p0.title = "ShareSoundApp"
        p0.scene = scene
        p0.show()
    }
}