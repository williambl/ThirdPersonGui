package com.williambl.thirdpersongui.common.networking;

import com.williambl.thirdpersongui.ThirdPersonGui;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class ModPackets {
    private static final String protocol_version = "1";

    public static final SimpleChannel instance = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(ThirdPersonGui.MODID, "main"),
            () -> protocol_version,
            protocol_version::equals,
            protocol_version::equals
    );

    public static void registerPackets() {
        int discriminator = 0;
        instance.registerMessage(
                discriminator++,
                ThirdPersonGuiMessage.class,
                ThirdPersonGuiMessage::encode,
                ThirdPersonGuiMessage::new,
                ThirdPersonGuiMessage::handle
        );
    }
}
