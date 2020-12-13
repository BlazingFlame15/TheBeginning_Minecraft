
package net.mcreator.beginning.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.Rarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.block.BlockState;

import net.mcreator.beginning.itemgroup.BeginningItemGroup;
import net.mcreator.beginning.BeginningModElements;

@BeginningModElements.ModElement.Tag
public class YellowstoneIngotItem extends BeginningModElements.ModElement {
	@ObjectHolder("beginning:yellowstone_ingot")
	public static final Item block = null;
	public YellowstoneIngotItem(BeginningModElements instance) {
		super(instance, 52);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}
	public static class ItemCustom extends Item {
		public ItemCustom() {
			super(new Item.Properties().group(BeginningItemGroup.tab).maxStackSize(64).rarity(Rarity.COMMON));
			setRegistryName("yellowstone_ingot");
		}

		@Override
		public int getItemEnchantability() {
			return 0;
		}

		@Override
		public int getUseDuration(ItemStack itemstack) {
			return 0;
		}

		@Override
		public float getDestroySpeed(ItemStack par1ItemStack, BlockState par2Block) {
			return 1F;
		}
	}
}
