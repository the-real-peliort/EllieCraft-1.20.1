package net.ellieraven.elliecraft.armor;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

public enum ModArmorMaterials implements ArmorMaterial {

    GIRL_CLOTHES;

    private static final int[] BASE_DURABILITY = new int[]{13, 15, 16, 11};

    @Override
    public int getDurabilityForType(ArmorItem.Type type) {
        return BASE_DURABILITY[type.getSlot().getIndex()] * 25;
    }

    @Override
    public int getDefenseForType(ArmorItem.Type type) {
        return switch (type) {
            case BOOTS -> 1;
            case LEGGINGS -> 3;
            case CHESTPLATE -> 4;
            case HELMET -> 2;
        };
    }

    @Override
    public int getEnchantmentValue() {
        return 5;
    }

    @Override
    public net.minecraft.sounds.SoundEvent getEquipSound() {
        return SoundEvents.WOOL_HIT;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.of(Items.WHITE_WOOL);
    }

    @Override
    public String getName() {
        return "elliecraft:girl_clothes";
    }

    @Override
    public float getToughness() {
        return 1.0F;
    }

    @Override
    public float getKnockbackResistance() {
        return 0.0F;
    }
}