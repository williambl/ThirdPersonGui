package com.williambl.thirdpersongui.common.networking;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.Objects;
import java.util.function.Supplier;

public class ShowThirdPersonGuiMessage {

    public ShowThirdPersonGuiMessage() {
    }

    public ShowThirdPersonGuiMessage(PacketBuffer buf) {
    }

    public void encode(PacketBuffer buf) {
    }

    public void handle(Supplier<NetworkEvent.Context> context) {
        if (context.get().getDirection().getReceptionSide() != LogicalSide.SERVER)
            return;
        context.get().enqueueWork(
                () -> {
                    Vec3d guiPosition = getOffsetPosition(Objects.requireNonNull(context.get().getSender()));
                    ((ServerWorld)(Objects.requireNonNull(context.get().getSender()).world)).spawnParticle(
                            ParticleTypes.BARRIER,
                            guiPosition.getX(),
                            guiPosition.getY(),
                            guiPosition.getZ(),
                            1,
                            0,
                            0,
                            0,
                            0
                    );

                    System.out.println(guiPosition);
                }
        );
        context.get().setPacketHandled(true);
    }

    private Vec3d getOffsetPosition(PlayerEntity player) {
        return player.getPositionVec().add(0, player.getEyeHeight(), 0).add(player.getLookVec());
    }
}
