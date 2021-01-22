package ui

import functional.FindUsers
import javafx.application.Application
import javafx.application.Platform
import javafx.scene.Group
import javafx.scene.Scene
import javafx.scene.control.Label
import javafx.stage.Stage

class Window : Application(), Runnable {

    override fun start(p0: Stage) {
        val group = Group()
        val scene = Scene(group, 1600.0, 900.0)
        val ip = Label()
        
        group.children.add(ip)
        p0.title = "ShareSoundApp"
        p0.scene = scene
        p0.show()
        while (true) {
            printUsers(ip, getNearUsers())
            Thread.sleep(1000)
        }
    }

    override fun run() {
        Application.launch()
    }

    private fun printUsers(ip: Label, hm: HashMap<String, Long>) {
        /*for (user in hm) {
            println(user.key)
        }*/
        var text = ""
        for (user in hm) {
            text += user.key
        }
        ip.text = text
        println(text)
    }

    private fun getNearUsers(): HashMap<String, Long> {
        return FindUsers.users
    }
}