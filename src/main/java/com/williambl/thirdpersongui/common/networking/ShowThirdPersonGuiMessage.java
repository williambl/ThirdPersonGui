package com.williambl.thirdpersongui.common.networking;

import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class ShowThirdPersonGuiMessage {

    private final BlockPos pos;

    public ShowThirdPersonGuiMessage(BlockPos pos) {
        this.pos = pos;
    }

    public ShowThirdPersonGuiMessage(PacketBuffer buf) {
        this.pos = buf.readBlockPos();
    }

    public void encode(PacketBuffer buf) {
        buf.writeBlockPos(pos);
    }

    public void handle(Supplier<NetworkEvent.Context> context) {
        System.out.println("recieved!");
    }
}
