
package net.mcreator.beginning.item;

@BeginningModElements.ModElement.Tag
public class BeginiumArrowItem extends BeginningModElements.ModElement {

	@ObjectHolder("beginning:beginium_arrow")
	public static final Item block = null;

	public BeginiumArrowItem(BeginningModElements instance) {
		super(instance, 64);

	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}

	public static class ItemCustom extends Item {

		public ItemCustom() {
			super(new Item.Properties().group(ItemGroup.MISC).maxStackSize(64).rarity(Rarity.COMMON));
			setRegistryName("beginium_arrow");
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
