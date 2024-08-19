package com.iafenvoy.annoyingvillagers.item.sword;

import com.iafenvoy.annoyingvillagers.registry.AnnoyingModItemGroups;
import com.iafenvoy.neptune.object.SoundUtil;
import com.iafenvoy.neptune.object.item.ToolMaterialUtil;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class LegendarySwordItem extends SwordItem {
    public static final String AWAKENING = "awakening";

    public LegendarySwordItem() {
        super(ToolMaterialUtil.of(9999, 17, 19, 5, 27), 3, -2.4f, new Item.Settings().arch$tab(AnnoyingModItemGroups.ORDINARY_WEAPONS));
    }

    @Override
    public String getTranslationKey(ItemStack stack) {
        if (stack.getOrCreateNbt().getBoolean(AWAKENING))
            return "item.annoying_villagers.awakening_legendary_sword";
        return "item.annoying_villagers.legendary_sword";
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity entity, Hand hand) {
        TypedActionResult<ItemStack> ar = super.use(world, entity, hand);
        ItemStack stack = entity.getStackInHand(hand);
        double x = entity.getX();
        double y = entity.getY();
        double z = entity.getZ();
        if (entity.isSneaking()) {
            if (!stack.getOrCreateNbt().getBoolean(AWAKENING)) {
                world.addParticle(ParticleTypes.FLASH, x, y, z, 0, 0, 0);
                SoundUtil.playSound(world, x, y, z, new Identifier("item.totem.use"), 1, 1);
                if (world instanceof ServerWorld _level)
                    _level.spawnParticles(ParticleTypes.TOTEM_OF_UNDYING, x, y, z, 150, 0.5, 0.5, 0.5, 0.5);
            }
            stack.getOrCreateNbt().putBoolean(AWAKENING, !stack.getOrCreateNbt().getBoolean(AWAKENING));
            entity.getItemCooldownManager().set(entity.getMainHandStack().getItem(), 100);

        }
        return ar;
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return stack.getOrCreateNbt().getBoolean(AWAKENING);
    }
}
