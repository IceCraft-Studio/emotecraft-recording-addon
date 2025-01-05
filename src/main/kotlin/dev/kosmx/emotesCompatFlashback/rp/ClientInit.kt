package dev.kosmx.emotesCompatFlashback.rp

import io.github.kosmx.emotes.api.events.client.ClientNetworkEvents
import io.github.kosmx.emotes.arch.network.NetworkPlatformTools
import net.minecraft.network.NetworkPhase
import com.moulberry.flashback.Flashback
import dev.kosmx.playerAnim.core.impl.event.EventResult

fun initializeClient() {
    ClientNetworkEvents.PACKET_SEND.register { builder ->
        Flashback.RECORDER?.writePacketAsync(NetworkPlatformTools.playPacket(builder.build().write()), NetworkPhase.PLAY)
        EventResult.PASS
    }
}