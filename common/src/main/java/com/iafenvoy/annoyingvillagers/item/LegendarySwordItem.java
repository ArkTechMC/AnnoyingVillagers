package com.iafenvoy.annoyingvillagers.item;

import com.iafenvoy.annoyingvillagers.registry.util.ToolMaterialUtil;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import com.iafenvoy.annoyingvillagers.procedures.LegendarySwordRightClick;

public class LegendarySwordItem extends SwordItem {
	public LegendarySwordItem() {
		super(ToolMaterialUtil.of(9999,17,19,5,27), 3, -2.4f, new Item.Settings());
	}

	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity entity, Hand hand) {
		TypedActionResult<ItemStack> ar = super.use(world, entity, hand);
		LegendarySwordRightClick.execute(world, entity.getX(), entity.getY(), entity.getZ(), entity, ar.getValue());
		return ar;
	}
}
