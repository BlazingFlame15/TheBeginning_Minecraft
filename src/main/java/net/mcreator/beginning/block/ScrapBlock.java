
package net.mcreator.beginning.block;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.BlockItem;
import net.minecraft.block.material.Material;
import net.minecraft.block.SoundType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Block;

import net.mcreator.beginning.itemgroup.BeginningItemGroup;
import net.mcreator.beginning.BeginningModElements;

import java.util.List;
import java.util.Collections;

@BeginningModElements.ModElement.Tag
public class ScrapBlock extends BeginningModElements.ModElement {
	@ObjectHolder("beginning:scrap")
	public static final Block block = null;
	public ScrapBlock(BeginningModElements instance) {
		super(instance, 44);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new CustomBlock());
		elements.items.add(() -> new BlockItem(block, new Item.Properties().group(BeginningItemGroup.tab)).setRegistryName(block.getRegistryName()));
	}
	public static class CustomBlock extends Block {
		public CustomBlock() {
			super(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(0.5f, 10f).lightValue(0));
			setRegistryName("scrap");
		}

		@Override
		public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
			List<ItemStack> dropsOriginal = super.getDrops(state, builder);
			if (!dropsOriginal.isEmpty())
				return dropsOriginal;
			return Collections.singletonList(new ItemStack(this, 1));
		}
	}
}
