package com.iafenvoy.annoyingvillagers.item;

import com.iafenvoy.annoyingvillagers.registry.AnnoyingModItemGroups;
import com.iafenvoy.annoyingvillagers.registry.AnnoyingModItems;
import com.iafenvoy.neptune.object.SoundUtil;
import com.iafenvoy.neptune.object.item.SwordItemBase;
import com.iafenvoy.neptune.object.item.ToolMaterialUtil;
import com.iafenvoy.neptune.util.Timeout;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ExplosiveProjectileEntity;
import net.minecraft.entity.projectile.SmallFireballEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

public class RGSItem extends SwordItemBase {
    public RGSItem() {
        super(ToolMaterialUtil.of(1731, 17, 20, 5, 23), 3, -3f, new Settings().arch$tab(AnnoyingModItemGroups.ORDINARY_WEAPONS));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity entity, Hand hand) {
        TypedActionResult<ItemStack> ar = super.use(world, entity, hand);
        double x = entity.getX();
        double y = entity.getY();
        double z = entity.getZ();
        ItemStack itemstack = ar.getValue();
        if (entity.isSneaking()) {
            if (entity.getMainHandStack().getItem() == itemstack.getItem()) {
                world.addParticle(ParticleTypes.FLASH, x, y, z, 0, 0, 0);
                Timeout.create(1, () -> {
                    SoundUtil.playSound(world, x, y, z, new Identifier("entity.blaze.shoot"), 1, 1);
                    if (world instanceof ServerWorld _level)
                        _level.spawnParticles(ParticleTypes.FLAME, x, y, z, 300, 0.5, 0.5, 0.5, 0.5);
                    ItemStack _setstack = new ItemStack(AnnoyingModItems.RUBY_KNIGHT_SWORD.get());
                    _setstack.setCount(1);
                    entity.setStackInHand(Hand.MAIN_HAND, _setstack);
                    entity.getInventory().markDirty();
                    NbtCompound _nbtTag = itemstack.getNbt();
                    if (_nbtTag != null)
                        entity.getMainHandStack().setNbt(_nbtTag.copy());
                    entity.getItemCooldownManager().set(entity.getMainHandStack().getItem(), 100);
                });
            } else {
                world.addParticle(ParticleTypes.FLASH, x, y, z, 0, 0, 0);
                Timeout.create(1, () -> {
                    SoundUtil.playSound(world, x, y, z, new Identifier("entity.blaze.shoot"), 1, 1);
                    if (world instanceof ServerWorld _level)
                        _level.spawnParticles(ParticleTypes.FLAME, x, y, z, 300, 0.5, 0.5, 0.5, 0.5);
                    ItemStack _setstack = new ItemStack(AnnoyingModItems.RUBY_KNIGHT_SWORD.get());
                    _setstack.setCount(1);
                    entity.setStackInHand(Hand.MAIN_HAND, _setstack);
                    entity.getInventory().markDirty();
                    NbtCompound _nbtTag = itemstack.getNbt();
                    if (_nbtTag != null)
                        entity.getOffHandStack().setNbt(_nbtTag.copy());
                    entity.getItemCooldownManager().set(entity.getOffHandStack().getItem(), 100);
                });
            }
        }
        return ar;
    }

    @Override
    public boolean onEntitySwing(ItemStack itemstack, Entity entity) {
        boolean retval = super.onEntitySwing(itemstack, entity);
        WorldAccess world = entity.getWorld();
        if (!(entity instanceof PlayerEntity _plrCldCheck1 && _plrCldCheck1.getItemCooldownManager().isCoolingDown(itemstack.getItem()))) {
            if (entity.isSneaking()) {
                if (entity instanceof PlayerEntity _player)
                    _player.getItemCooldownManager().set(itemstack.getItem(), 100);
                Runnable runnable = () -> {
                    if (world instanceof World _level)
                        SoundUtil.playSound(_level, entity.getX(), entity.getY(), entity.getZ(), new Identifier("entity.blaze.shoot"), 1, 1);
                    World projectileLevel = entity.getWorld();
                    if (!projectileLevel.isClient) {
                        ExplosiveProjectileEntity _entityToSpawn = new SmallFireballEntity(EntityType.SMALL_FIREBALL, projectileLevel);
                        _entityToSpawn.setOwner(entity);
                        _entityToSpawn.setPosition(entity.getX(), entity.getEyeY() - 0.1, entity.getZ());
                        _entityToSpawn.setVelocity(entity.getRotationVector().x, entity.getRotationVector().y, entity.getRotationVector().z, 4, 0);
                        projectileLevel.spawnEntity(_entityToSpawn);
                    }
                };
                Timeout.create(0, runnable);
                Timeout.create(10, runnable);
                Timeout.create(20, runnable);
            }
        }
        return retval;
    }

}
