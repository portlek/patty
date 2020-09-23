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

import io.netty.buffer.ByteBuf

interface PacketHeader {
  val isLengthVariable: Boolean
  val lengthSize: Int

  fun getLengthSize(length: Int): Int

  fun readLength(input: ByteBuf, available: Int): Int

  fun writeLength(output: ByteBuf, length: Int)

  fun readPacketId(input: ByteBuf): Int

  fun writePacketId(output: ByteBuf, packetId: Int)

  class EmptyPacketHeader : PacketHeader {
    override val isLengthVariable = false
    override val lengthSize = 0

    override fun getLengthSize(length: Int) = -1

    override fun readLength(input: ByteBuf, available: Int) = -1

    override fun writeLength(output: ByteBuf, length: Int) {
    }

    override fun readPacketId(input: ByteBuf) = -1

    override fun writePacketId(output: ByteBuf, packetId: Int) {
    }
  }

  companion object {
    val EMPTY = EmptyPacketHeader()
  }
}