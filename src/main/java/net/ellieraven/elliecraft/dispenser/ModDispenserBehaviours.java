package net.ellieraven.elliecraft.dispenser;

import net.ellieraven.elliecraft.item.ModItems;
import net.ellieraven.elliecraft.item.custom.blades.EndBladeItem;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockSource;
import net.minecraft.core.Direction;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.phys.Vec3;

public class ModDispenserBehaviours {
    public static void register() {
        DispenserBlock.registerBehavior(ModItems.END_BLADE.get(), new DefaultDispenseItemBehavior() {
            @Override
            protected ItemStack execute(BlockSource pSource, ItemStack pStack) {
                Level level = pSource.getLevel();
                Direction direction = pSource.getBlockState().getValue(DispenserBlock.FACING);
                Vec3 dirVec = new Vec3(direction.getStepX(), direction.getStepY(), direction.getStepZ());
                BlockPos frontPos = pSource.getPos().relative(direction);
                Vec3 frontVec = Vec3.atCenterOf(pSource.getPos())
                        .add(direction.getStepX() * 1.0,
                                direction.getStepY() * 1.0,
                                direction.getStepZ() * 1.0);

                if (!level.isClientSide) {
                    EndBladeItem.fireRay(level, frontVec, dirVec, null);
                }
                return pStack;
            }
        });
    }
}
