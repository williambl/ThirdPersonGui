package com.williambl.thirdpersongui.common.networking;

import net.minecraft.network.PacketBuffer;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.Objects;
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
        if (context.get().getDirection().getReceptionSide() != LogicalSide.SERVER)
            return;
        context.get().enqueueWork(
                () -> {
                    ((ServerWorld)(Objects.requireNonNull(context.get().getSender()).world)).spawnParticle(
                            ParticleTypes.BARRIER,
                            this.pos.getX(),
                            this.pos.getY(),
                            this.pos.getZ(),
                            1,
                            0,
                            0,
                            0,
                            0
                    );
                }
        );
        context.get().setPacketHandled(true);
    }
}
