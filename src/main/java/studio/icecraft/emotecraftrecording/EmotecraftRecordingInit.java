package studio.icecraft.emotecraftrecording;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import io.github.kosmx.emotes.api.events.client.ClientNetworkEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmotecraftRecordingInit implements ClientModInitializer {
	public static final Logger log = LoggerFactory.getLogger("studio.icecraft.emotecraftrecording");
	@Override
	public void onInitializeClient() {
		FabricLoader fabricInstance = FabricLoader.getInstance();
		if (fabricInstance.isModLoaded(Constants.FLASHBACK_ID)) {
			ClientNetworkEvents.PACKET_SEND.register(builder -> {
				EmotecraftFlashbackAddonClient.recordPacket(builder);
			});
			log.info("Emotecraft addon for Flashback loaded!");
		}
		if (fabricInstance.isModLoaded(Constants.REPLAY_MOD_ID)) {
			ClientNetworkEvents.PACKET_SEND.register(builder -> {
				EmotecraftReplayAddonClient.recordPacket(builder);
			});
			log.info("Emotecraft addon for Replay Mod loaded!");
		}
	}
}