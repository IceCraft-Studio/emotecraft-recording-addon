package studio.icecraft.emotecraftrecording.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import io.github.kosmx.emotes.common.network.EmotePacket;
import io.github.kosmx.emotes.common.network.objects.AbstractNetworkPacket;
import io.github.kosmx.emotes.common.network.objects.NetData;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

@Mixin(EmotePacket.class)
public class EmotePacketMixin {
	@Shadow @Final public NetData data;

	@Inject(
			method = "write",
			at = @At(
					value = "INVOKE",
					target = "Lio/github/kosmx/emotes/common/network/objects/AbstractNetworkPacket;isOptional()Z"
			)
	)
	private void emotecraftrecording$ensureVersion(ByteBuf buf, CallbackInfo ci, @Local(name = "packet") AbstractNetworkPacket packet) {
		if (data == null || data.versions == null) {
			return;
		}
		byte id = packet.getID();
		if (!data.versions.containsKey(id)) {
			data.versions.put(id, packet.getVer());
		}
	}
}