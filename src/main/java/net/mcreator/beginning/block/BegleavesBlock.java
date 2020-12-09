
package net.mcreator.beginning.block;

import net.minecraft.block.material.Material;

@BeginningModElements.ModElement.Tag
public class BegleavesBlock extends BeginningModElements.ModElement {

	@ObjectHolder("beginning:begleaves")
	public static final Block block = null;

	public BegleavesBlock(BeginningModElements instance) {
		super(instance, 7);

	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new CustomBlock());
		elements.items.add(() -> new BlockItem(block, new Item.Properties().group(ItemGroup.DECORATIONS)).setRegistryName(block.getRegistryName()));
	}

	public static class CustomBlock extends Block {

		public CustomBlock() {
			super(

					Block.Properties.create(Material.LEAVES).sound(SoundType.PLANT).hardnessAndResistance(0.2f, 1f).lightValue(0).harvestLevel(-1)
							.harvestTool(ToolType.AXE));

			setRegistryName("begleaves");
		}

		@Override
		public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
			return true;
		}

		@Override
		public int getFlammability(BlockState state, IBlockReader world, BlockPos pos, Direction face) {
			return 30;
		}

		@Override
		public MaterialColor getMaterialColor(BlockState state, IBlockReader blockAccess, BlockPos pos) {
			return MaterialColor.FOLIAGE;
		}

	}

}
