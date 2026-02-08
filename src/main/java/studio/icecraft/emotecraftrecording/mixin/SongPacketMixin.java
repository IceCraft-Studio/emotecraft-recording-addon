package studio.icecraft.emotecraftrecording.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import io.github.kosmx.emotes.common.network.objects.AbstractNetworkPacket;
import io.github.kosmx.emotes.common.network.objects.NetData;
import io.github.kosmx.emotes.common.network.objects.SongPacket;

@Mixin(SongPacket.class)
public abstract class SongPacketMixin {
	@Inject(method = "doWrite", at = @At("HEAD"), cancellable = true)
	private void emotecraftrecording$ensureVersion(NetData data, CallbackInfoReturnable<Boolean> cir) {
		if (data == null || data.versions == null) {
			cir.setReturnValue(false);
			return;
		}
		AbstractNetworkPacket self = (AbstractNetworkPacket)(Object)this;
		byte id = self.getID();
		if (!data.versions.containsKey(id)) {
			data.versions.put(id, self.getVer());
		}
	}
}
