package dev.mayaqq.crackstone.mixin;

import dev.mayaqq.crackstone.Crackstone;
import dev.mayaqq.crackstone.config.CrackstoneConfig;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Block.class)
public class BlockMixin {
    @Inject(at = @At("HEAD"), method = "onSteppedOn")
    private void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity, CallbackInfo ci) {
        if (state.isIn(Crackstone.CRACKABLE)) {
            if (CrackstoneConfig.CONFIG.randomCrackstone) {
                float hardness = 0;
                for (int x = -1; x <= 1; x++) {
                    for (int y = -1; y <= 1; y++) {
                        for (int z = -1; z <= 1; z++) {
                            hardness += world.getBlockState(pos.add(x, y, z)).getBlock().getHardness();
                        }
                    }
                }
                if (Math.random() * 100 > 50 + hardness * CrackstoneConfig.CONFIG.hardnessMultiplier) {
                    if (!world.isClient) {
                        world.breakBlock(pos, false);
                    }
                }
            } else {
                if (!world.isClient && world.getTickOrder() % 10 == 0) {
                    world.breakBlock(pos, false);
                }
            }
        }
    }
}