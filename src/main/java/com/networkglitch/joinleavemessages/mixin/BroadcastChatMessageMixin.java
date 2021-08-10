package com.networkglitch.joinleavemessages.mixin;


import com.networkglitch.joinleavemessages.Joinleavemessages;
import net.minecraft.network.MessageType;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.PlayerManager;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.UUID;

@Mixin(PlayerManager.class)
public class BroadcastChatMessageMixin {

    @Inject(at = @At("INVOKE"), method = "broadcastChatMessage", cancellable = true)
    void filterBroadCastMessages(Text message, MessageType type, UUID sender, CallbackInfo ci) {

        if (message instanceof TranslatableText) {
            String Key = ((TranslatableText) message).getKey();
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
}