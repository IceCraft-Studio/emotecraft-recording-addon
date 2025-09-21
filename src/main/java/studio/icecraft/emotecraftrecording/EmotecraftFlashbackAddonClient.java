package studio.icecraft.emotecraftrecording;

import java.io.IOException;

import net.minecraft.network.ConnectionProtocol;

import io.github.kosmx.emotes.arch.network.NetworkPlatformTools;
import io.github.kosmx.emotes.common.network.EmotePacket.Builder;
import com.moulberry.flashback.Flashback;

public class EmotecraftFlashbackAddonClient {
	public static void recordPacket(Builder builder) {
		if (Flashback.RECORDER == null)
			return;
		try {
			EmotecraftRecordingInit.log.info("Emote packet record attempt for Flashback.");
			Flashback.RECORDER.writePacketAsync(
					NetworkPlatformTools.playPacket(builder.build().write()),
					ConnectionProtocol.PLAY);
			EmotecraftRecordingInit.log.info("Recorded.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}