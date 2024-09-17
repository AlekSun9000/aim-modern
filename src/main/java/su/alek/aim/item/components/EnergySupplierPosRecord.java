package su.alek.aim.item.components;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public record EnergySupplierPosRecord(int x, int y, int z) {
    public static final Codec<EnergySupplierPosRecord> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.INT.fieldOf("x").forGetter(EnergySupplierPosRecord::x),
                    Codec.INT.fieldOf("y").forGetter(EnergySupplierPosRecord::y),
                    Codec.INT.fieldOf("z").forGetter(EnergySupplierPosRecord::z)
            ).apply(instance, EnergySupplierPosRecord::new)
    );
    public static final StreamCodec<ByteBuf, EnergySupplierPosRecord> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.INT,EnergySupplierPosRecord::x,
            ByteBufCodecs.INT,EnergySupplierPosRecord::y,
            ByteBufCodecs.INT,EnergySupplierPosRecord::z,
            EnergySupplierPosRecord::new
    );
}
