package com.williambl.thirdpersongui.client;

import com.williambl.thirdpersongui.ThirdPersonGui;
import com.williambl.thirdpersongui.common.networking.ModPackets;
import com.williambl.thirdpersongui.common.networking.ThirdPersonGuiMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value=Dist.CLIENT, modid=ThirdPersonGui.MODID)
public class ClientEventHandler {

    static boolean isGuiOpen = false;

    @SubscribeEvent
    public static void onOpenGui(GuiOpenEvent event) {
        if (Minecraft.getInstance().player == null)
            return;

        if (event.getGui() == null && isGuiOpen) {
            isGuiOpen = false;
            ModPackets.instance.sendToServer(new ThirdPersonGuiMessage(false));
        }

        if (event.getGui() instanceof ContainerScreen && !isGuiOpen) {
            isGuiOpen = true;
            ModPackets.instance.sendToServer(new ThirdPersonGuiMessage(true));
        }

    }
}
