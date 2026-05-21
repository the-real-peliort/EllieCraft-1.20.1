package net.ellieraven.elliecraft.item.custom.blades;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.*;

import static net.minecraft.commands.arguments.coordinates.Vec3Argument.vec3;

public class EndBladeItem extends SwordItem {
    public static void blinkAbility(Player player) {
        player.level().playSound(
                null,
                player.getX(),
                player.getY(),
                player.getZ(),
                SoundEvents.ENDERMAN_TELEPORT,
                SoundSource.PLAYERS,
                1.0f,
                1.0f
        );

        if (player.level() instanceof ServerLevel level){
            level.sendParticles( //purple
                    ParticleTypes.DRAGON_BREATH,
                    player.position().x, player.position().y, player.position().z,
                    3,
                    .75, .75, .75,
                    1
            );
        }

        float blinkDistance = 5;

        player.teleportTo(
                player.getX() + player.getLookAngle().x * blinkDistance,
                player.getY(),
                player.getZ() + player.getLookAngle().z * blinkDistance
        );

        if (player.level() instanceof ServerLevel level){
            level.sendParticles( //purple
                    ParticleTypes.DRAGON_BREATH,
                    player.position().x, player.position().y, player.position().z,
                    20,
                    .75, .75, .75,
                    1
            );
        }

        ServerPlayer serverPlayer = (ServerPlayer) player;

        float newYaw = serverPlayer.getYRot() + 180.0F;

        serverPlayer.setYRot(newYaw);
        serverPlayer.setYHeadRot(newYaw);
        serverPlayer.setYBodyRot(newYaw);

        serverPlayer.yRotO = newYaw;
        serverPlayer.yHeadRotO = newYaw;
        serverPlayer.yBodyRotO = newYaw;

        serverPlayer.connection.teleport(
                serverPlayer.getX(),
                serverPlayer.getY(),
                serverPlayer.getZ(),
                newYaw,
                serverPlayer.getXRot()
        );
    }

    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (!level.isClientSide()) {

            fireRay(level, player.getEyePosition(), player.getLookAngle(), player, stack);
            player.getCooldowns().addCooldown(stack.getItem(), 20);
        }

        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
    }

    public static void fireRay(Level level, Vec3 start, Vec3 direction, Player player, ItemStack stack) {
        final double range = 75.0;


        Vec3 end = start.add(direction.scale(range));

        // --- BLOCK HIT ---
        BlockHitResult blockHit = level.clip(new ClipContext(
                start,
                end,
                ClipContext.Block.COLLIDER,
                ClipContext.Fluid.NONE,
                player
        ));

        double blockDist = blockHit.getType() != HitResult.Type.MISS
                ? blockHit.getLocation().distanceTo(start)
                : range;

        // --- ENTITY HIT ---
        AABB box = player.getBoundingBox()
                .expandTowards(direction.scale(range))
                .inflate(2.5);

        EntityHitResult entityHit = ProjectileUtil.getEntityHitResult(
                level,
                player,
                start,
                end,
                box,
                e -> !e.isSpectator() && e.isPickable()
        );

        double entityDist = Double.MAX_VALUE;
        Entity target = null;

        if (entityHit != null) {
            entityDist = entityHit.getLocation().distanceTo(start);

            if (entityDist < blockDist) {
                target = entityHit.getEntity();
            }
        }

        // --- DAMAGE ---
        if (target != null) {
            target.hurt(level.damageSources().playerAttack(player), 15.0f);
        }

        // --- SOUND (HERE IS THE CORRECT PLACE) ---
        level.playSound(
                null,
                player.getX(),
                player.getY(),
                player.getZ(),
                SoundEvents.ENDERMAN_HURT,
                SoundSource.PLAYERS,
                1.0f,
                1.0f
        );

        // --- PARTICLES ---
        double stopDistance = Math.min(blockDist, entityDist);
        Vec3 rayDir = direction.normalize();
        double step = 0.25;

        if (level instanceof ServerLevel serverLevel) {
            for (double d = 0; d < stopDistance; d += step) {
                Vec3 point = start.add(rayDir.scale(d));

                serverLevel.sendParticles( //white
                        ParticleTypes.END_ROD,
                        point.x, point.y, point.z,
                        1,
                        0, 0, 0,
                        0
                );
                serverLevel.sendParticles( //purple
                        ParticleTypes.DRAGON_BREATH,
                        point.x, point.y, point.z,
                        3,
                        .1, .1, .1,
                        0
                );
            }
        }
    }

    public EndBladeItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }

    public static void setCooldown(ItemStack stack, String key, int ticks) {
        stack.getOrCreateTag().putInt(key, ticks);
    }

    public static int getCooldown(ItemStack stack, String key) {
        return stack.getOrCreateTag().getInt(key);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean selected) {
        if (!(entity instanceof Player)) return;

        if (!level.isClientSide) {
            decrementCooldown(stack, "blink_cd");
        }
    }

    private void decrementCooldown(ItemStack stack, String key) {
        int cd = getCooldown(stack, key);
        if (cd > 0) {
            setCooldown(stack, key, cd - 1);
        }
    }
}
