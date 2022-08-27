package com.networkglitch.joinleavemessages.mixin;


import com.networkglitch.joinleavemessages.Joinleavemessages;
import net.minecraft.network.message.MessageType;
import net.minecraft.server.PlayerManager;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableTextContent;
import net.minecraft.util.registry.RegistryKey;
import org.apache.logging.log4j.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.UUID;

@Mixin(PlayerManager.class)
public class BroadcastChatMessageMixin {

    @Inject(at = @At("HEAD"), method = "broadcast(Lnet/minecraft/text/Text;Z)V", cancellable = true)
    void filterBroadCastMessages(Text message, boolean overlay, CallbackInfo ci) {

        String Key = ((TranslatableTextContent) message.getContent()).getKey();
        if (
                Key.equals("multiplayer.player.joined.renamed") ||
                        Key.equals("multiplayer.player.joined") ||
                        Key.equals("multiplayer.player.left")
        ) {

            Joinleavemessages.log(Level.INFO, "Suppressing a join/leave message");
            ci.cancel();
        }
    }
}