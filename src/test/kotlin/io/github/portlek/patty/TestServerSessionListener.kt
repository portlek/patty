/*
 * MIT License
 *
 * Copyright (c) 2020 Hasan Demirtaş
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package io.github.portlek.patty

import io.github.portlek.patty.packets.TestPingPacket
import io.netty.buffer.ByteBuf

class TestServerSessionListener : SessionListener {
  override fun packetReceived(packet: Packet, connection: Connection) {
    if (packet is TestPingPacket) {
      println("server packet received ${packet.message}")
      connection.sendPacket(packet)
    }
  }

  override fun packetSending(packet: Packet, connection: Connection): Boolean {
    if (packet is TestPingPacket) {
      println("server packet sending ${packet.message}")
    }
    return true
  }

  override fun packetSent(packet: Packet, connection: Connection) {
    if (packet is TestPingPacket) {
      println("server packet sent ${packet.message}")
    }
  }

  override fun packetError(throwable: Throwable, connection: Connection): Boolean {
    TODO("Not yet implemented")
  }

  override fun connected(connection: Connection) {
    println("server connected")
  }

  override fun disconnecting(connection: Connection, reason: String, cause: Throwable?) {
    println("server disconnecting")
  }

  override fun disconnected(connection: Connection, reason: String, cause: Throwable?) {
    println("server disconnected")
  }
}