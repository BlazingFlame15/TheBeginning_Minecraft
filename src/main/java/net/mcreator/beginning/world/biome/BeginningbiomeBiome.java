
package net.mcreator.beginning.world.biome;

import net.minecraft.block.material.Material;

@BeginningModElements.ModElement.Tag
public class BeginningbiomeBiome extends BeginningModElements.ModElement {

	@ObjectHolder("beginning:beginningbiome")
	public static final CustomBiome biome = null;

	public BeginningbiomeBiome(BeginningModElements instance) {
		super(instance, 25);
	}

	@Override
	public void initElements() {
		elements.biomes.add(() -> new CustomBiome());
	}

	@Override
	public void init(FMLCommonSetupEvent event) {
		BiomeManager.addSpawnBiome(biome);
		BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(biome, 10));
	}

	static class CustomBiome extends Biome {

		public CustomBiome() {
			super(new Biome.Builder().downfall(0.5f).depth(0.1f).scale(0.2f).temperature(0.5f).precipitation(Biome.RainType.RAIN)
					.category(Biome.Category.NONE).waterColor(4159204).waterFogColor(329011)
					.surfaceBuilder(SurfaceBuilder.DEFAULT, new SurfaceBuilderConfig(BeginningGrassBlock.block.getDefaultState(),
							BeginningDirtBlock.block.getDefaultState(), BeginningDirtBlock.block.getDefaultState())));

			setRegistryName("beginningbiome");

			DefaultBiomeFeatures.addCarvers(this);
			DefaultBiomeFeatures.addMonsterRooms(this);
			DefaultBiomeFeatures.addStructures(this);
			DefaultBiomeFeatures.addOres(this);

		}

		@OnlyIn(Dist.CLIENT)
		@Override
		public int getSkyColor() {
			return -13108;
		}

	}

}
