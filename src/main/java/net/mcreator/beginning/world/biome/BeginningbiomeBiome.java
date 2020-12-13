
package net.mcreator.beginning.world.biome;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.biome.Biome;

import net.mcreator.beginning.block.BeginningGrassBlock;
import net.mcreator.beginning.block.BeginningDirtBlock;
import net.mcreator.beginning.BeginningModElements;

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
	}
	static class CustomBiome extends Biome {
		public CustomBiome() {
			super(new Biome.Builder().downfall(0.5f).depth(0.1f).scale(0.6f).temperature(0.5f).precipitation(Biome.RainType.RAIN)
					.category(Biome.Category.NONE).waterColor(-10053121).waterFogColor(329011)
					.surfaceBuilder(SurfaceBuilder.DEFAULT, new SurfaceBuilderConfig(BeginningGrassBlock.block.getDefaultState(),
							BeginningDirtBlock.block.getDefaultState(), BeginningDirtBlock.block.getDefaultState())));
			setRegistryName("beginningbiome");
		}

		@OnlyIn(Dist.CLIENT)
		@Override
		public int getSkyColor() {
			return -13108;
		}
	}
}
