package studio.icecraft.emotecraftrecording;

import java.util.HashMap;
import java.util.Map;

import io.github.kosmx.emotes.common.network.EmotePacket;
import io.github.kosmx.emotes.common.network.EmotePacket.Builder;
import io.github.kosmx.emotes.common.network.objects.AbstractNetworkPacket;
import io.github.kosmx.emotes.common.network.objects.NetData;

final class EmotePacketVersionFix {
	private EmotePacketVersionFix() {
	}

	static void ensureVersions(Builder builder) {
		NetData data = builder.data();
		Map<Byte, Byte> merged = new HashMap<>();
		merged.putAll(EmotePacket.defaultVersions);
		merged.putAll(getDynamicVersions());
		if (data != null && data.versions != null) {
			merged.putAll(data.versions);
		}
		builder.setVersion(merged);
	}

	private static Map<Byte, Byte> getDynamicVersions() {
		Map<Byte, Byte> versions = new HashMap<>();
		try {
			java.lang.reflect.Field field = EmotePacket.class.getDeclaredField("SUB_PACKETS");
			field.setAccessible(true);
			Object map = field.get(null);
			if (map instanceof java.util.Map<?, ?> m) {
				for (Object value : m.values()) {
					if (value instanceof AbstractNetworkPacket packet) {
						versions.put(packet.getID(), packet.getVer());
					}
				}
			}
		} catch (Throwable ignored) {
		}
		return versions;
	}
}
