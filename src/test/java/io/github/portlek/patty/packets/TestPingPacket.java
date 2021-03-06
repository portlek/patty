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

package io.github.portlek.patty.packets;

import io.github.portlek.patty.Connection;
import io.github.portlek.patty.Packet;
import io.netty.buffer.ByteBuf;
import java.nio.charset.StandardCharsets;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class TestPingPacket extends Packet {

  @Nullable
  public String message;

  public TestPingPacket(@Nullable final String message) {
    super(TestPingPacket.class);
    this.message = message;
  }

  public TestPingPacket() {
    super(TestPingPacket.class);
  }

  @Override
  public void read(@NotNull final ByteBuf buffer, @NotNull final Connection connection) {
    final int length = buffer.readInt();
    final byte[] bytes = new byte[length];
    buffer.readBytes(bytes);
    this.message = new String(bytes, StandardCharsets.UTF_8);
  }

  @Override
  public void write(@NotNull final ByteBuf buffer, @NotNull final Connection connection) {
    if (this.message == null) {
      return;
    }
    buffer.writeInt(this.message.length());
    buffer.writeBytes(this.message.getBytes(StandardCharsets.UTF_8));
  }

  @Override
  public String toString() {
    return this.message != null ? this.message : "null message";
  }
}
