package su.alek.aim.item.components;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public record ItemColorRecord(int color) {
    public static final Codec<ItemColorRecord> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.INT.fieldOf("color").forGetter(ItemColorRecord::color)
            ).apply(instance, ItemColorRecord::new)
    );
    public static final StreamCodec<ByteBuf, ItemColorRecord> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.INT, ItemColorRecord::color,
            ItemColorRecord::new
    );
}
