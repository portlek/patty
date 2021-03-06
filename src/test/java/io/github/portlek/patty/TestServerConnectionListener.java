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

package io.github.portlek.patty;

import io.github.portlek.patty.packets.TestPingPacket;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class TestServerConnectionListener implements ConnectionListener {

  @Override
  public void packetReceived(@NotNull final Packet packet, @NotNull final Connection connection) {
    if (packet instanceof TestPingPacket) {
      System.out.println("server packet received " + ((TestPingPacket) packet).message);
      connection.sendPacket(packet);
    }
  }

  @Override
  public boolean packetSending(@NotNull final Packet packet, @NotNull final Connection connection) {
    if (packet instanceof TestPingPacket) {
      System.out.println("server packet sending " + ((TestPingPacket) packet).message);
    }
    return true;
  }

  @Override
  public void packetSent(@NotNull final Packet packet, @NotNull final Connection connection) {
    if (packet instanceof TestPingPacket) {
      System.out.println("server packet sent " + ((TestPingPacket) packet).message);
    }
  }

  @Override
  public boolean packetError(final @NotNull Throwable throwable, final @NotNull Connection connection) {
    return true;
  }

  @Override
  public void connected(@NotNull final Connection connection) {
    System.out.println("server connected");
  }

  @Override
  public void disconnecting(@NotNull final Connection connection, @NotNull final String reason, @Nullable final Throwable cause) {
    System.out.println("server disconnecting: " + reason);
  }

  @Override
  public void disconnected(@NotNull final Connection connection, @NotNull final String reason, @Nullable final Throwable cause) {
    System.out.println("server disconnected");
  }
}
