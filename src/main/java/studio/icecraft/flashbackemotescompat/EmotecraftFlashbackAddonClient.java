package studio.icecraft.flashbackemotescompat;

import java.io.IOException;

import net.minecraft.network.ConnectionProtocol;

import net.fabricmc.api.ClientModInitializer;

import io.github.kosmx.emotes.api.events.client.ClientNetworkEvents;
import io.github.kosmx.emotes.arch.network.NetworkPlatformTools;
import com.moulberry.flashback.Flashback;
import com.zigythebird.playeranimcore.event.EventResult;

public class EmotecraftFlashbackAddonClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ClientNetworkEvents.PACKET_SEND.register(builder -> {
			try {
				if (Flashback.RECORDER != null) {
					Flashback.RECORDER.writePacketAsync(
						NetworkPlatformTools.playPacket(builder.build().write()),
						ConnectionProtocol.PLAY
					);
				}
			} catch (IOException e) {
				e.printStackTrace(); // Or use a proper logger
			}
			return EventResult.PASS;
		});
	}
}