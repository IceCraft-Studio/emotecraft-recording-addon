package studio.icecraft.emotecraftrecording;

import java.io.IOException;

import io.github.kosmx.emotes.arch.network.NetworkPlatformTools;
import com.replaymod.recording.ReplayModRecording;
import io.github.kosmx.emotes.common.network.EmotePacket.Builder;

public class EmotecraftReplayAddonClient {
	public static void recordPacket(Builder builder) {
		if (ReplayModRecording.instance == null)
			return;
		try {
			ReplayModRecording.instance.getConnectionEventHandler().getPacketListener().save(
					NetworkPlatformTools.playPacket(builder.build().write()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}