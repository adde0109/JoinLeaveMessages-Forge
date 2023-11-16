package com.networkglitch.joinleavemessages.mixin;


import com.networkglitch.joinleavemessages.Joinleavemessages;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.server.players.PlayerList;
import org.apache.logging.log4j.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerList.class)
public class BroadcastChatMessageMixin {

    @Inject(at = @At("HEAD"), method = "broadcastSystemMessage(Lnet/minecraft/network/chat/Component;Z)V", cancellable = true)
    void filterBroadCastMessages(Component message, boolean overlay, CallbackInfo ci) {

        String Key = ((TranslatableContents) message.getContents()).getKey();
        if (
                Key.equals("multiplayer.player.joined.renamed") ||
                        Key.equals("multiplayer.player.joined") ||
                        Key.equals("multiplayer.player.left")
        ) {

            //Joinleavemessages.log(Level.INFO, "Suppressing a join/leave message");
            ci.cancel();
        }
    }
}