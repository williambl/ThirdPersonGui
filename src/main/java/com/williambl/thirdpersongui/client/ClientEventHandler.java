package com.williambl.thirdpersongui.client;

import com.williambl.thirdpersongui.ThirdPersonGui;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value=Dist.CLIENT, modid=ThirdPersonGui.MODID)
public class ClientEventHandler {

    @SubscribeEvent
    public static void onOpenGui(GuiOpenEvent event) {
        if (Minecraft.getInstance().player == null)
            return;
        System.out.println("gui!");
    }
}
