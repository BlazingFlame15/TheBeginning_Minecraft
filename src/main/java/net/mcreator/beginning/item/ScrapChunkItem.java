
package net.mcreator.beginning.item;

@BeginningModElements.ModElement.Tag
public class ScrapChunkItem extends BeginningModElements.ModElement {

	@ObjectHolder("beginning:scrap_chunk")
	public static final Item block = null;

	public ScrapChunkItem(BeginningModElements instance) {
		super(instance, 67);

	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}

	public static class ItemCustom extends Item {

		public ItemCustom() {
			super(new Item.Properties().group(BeginningItemGroup.tab).maxStackSize(16).rarity(Rarity.COMMON));
			setRegistryName("scrap_chunk");
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
