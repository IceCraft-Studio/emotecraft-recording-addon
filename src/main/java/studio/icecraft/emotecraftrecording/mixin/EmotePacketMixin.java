package studio.icecraft.emotecraftrecording.mixin;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import io.github.kosmx.emotes.common.network.EmotePacket;
import io.github.kosmx.emotes.common.network.objects.AbstractNetworkPacket;
import io.github.kosmx.emotes.common.network.objects.NetData;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

@Mixin(EmotePacket.class)
public class EmotePacketMixin {
	@Shadow @Final public NetData data;

	@Inject(method = "writeSubPacket", at = @At("HEAD"))
	private void emotecraftrecording$ensureVersion(AbstractNetworkPacket packet, ByteBufAllocator allocator, CallbackInfoReturnable<ByteBuf> cir) {
		if (data == null || data.versions == null) {
			return;
		}
		byte id = packet.getID();
		if (!data.versions.containsKey(id)) {
			data.versions.put(id, packet.getVer());
		}
	}
}
