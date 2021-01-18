package functional

import java.net.*
import javax.xml.crypto.Data

class FindUsers(private val multicastAddress: String, private val port: Int) {

    private val timeout = 1000
    private val helloString = "Hello"

    private val socket = MulticastSocket(port)
    private val address: SocketAddress

    private var lastTimeReceive: Long = 0
    private var lastSendTime: Long = 0

    private var users = HashMap<String, Long>()

    init {
        socket.soTimeout = timeout
        address = InetSocketAddress(multicastAddress, port)
        socket.joinGroup(address, NetworkInterface.getByInetAddress(InetAddress.getLocalHost()))
        users.clear()
        mainCycle()
    }

    private fun mainCycle() {
        lastSendTime = System.currentTimeMillis()
        while (true) {
            if (System.currentTimeMillis() - lastSendTime > timeout) sendHelloPacket()
            val userIp = receiveMessage()
            if(userIp.isNotEmpty()) users.put(userIp, lastTimeReceive)
            for(user in users)
            {
                if(System.currentTimeMillis() - user.value > timeout) users.remove(user.key)
            }
            println("Number of live devices: ${users.size}")
        }
    }

    private fun receiveMessage(): String {
        val requestPacket = DatagramPacket(ByteArray(256), ByteArray(256).size)
        try {
            socket.receive(requestPacket)
        } catch (ex : SocketTimeoutException) {
            return ""
        }
        lastTimeReceive = System.currentTimeMillis()
        return requestPacket.address.toString()
    }


    private fun sendHelloPacket() {
        socket.send(DatagramPacket(helloString.toByteArray(), helloString.toByteArray().size, address))
        lastSendTime = System.currentTimeMillis()
    }
}