// Made with Blockbench 3.7.4
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports

public static class Modelbeginiumarrow extends EntityModel<Entity> {
	private final ModelRenderer feather;
	private final ModelRenderer tip;
	private final ModelRenderer bb_main;

	public Modelbeginiumarrow() {
		textureWidth = 16;
		textureHeight = 16;

		feather = new ModelRenderer(this);
		feather.setRotationPoint(0.0F, 23.0F, 0.0F);
		feather.setTextureOffset(12, 11).addBox(1.0F, -3.0F, 1.0F, 1.0F, 4.0F, 1.0F, 0.0F, false);
		feather.setTextureOffset(12, 11).addBox(1.0F, -3.0F, -2.0F, 1.0F, 4.0F, 1.0F, 0.0F, false);
		feather.setTextureOffset(12, 11).addBox(-2.0F, -3.0F, -2.0F, 1.0F, 4.0F, 1.0F, 0.0F, false);
		feather.setTextureOffset(12, 11).addBox(-2.0F, -3.0F, 1.0F, 1.0F, 4.0F, 1.0F, 0.0F, false);
		feather.setTextureOffset(0, 0).addBox(-2.0F, -4.0F, -1.0F, 4.0F, 1.0F, 2.0F, 0.0F, false);

		tip = new ModelRenderer(this);
		tip.setRotationPoint(1.0F, 12.0F, -1.0F);
		tip.setTextureOffset(0, 12).addBox(-2.0F, -2.0F, 0.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
		tip.setTextureOffset(0, 16).addBox(-2.0F, 0.0F, 2.0F, 2.0F, -1.0F, 1.0F, 0.0F, false);
		tip.setTextureOffset(0, 14).addBox(1.0F, 0.0F, 0.0F, -1.0F, -1.0F, 2.0F, 0.0F, false);
		tip.setTextureOffset(0, 12).addBox(-3.0F, -1.0F, 0.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
		tip.setTextureOffset(0, 13).addBox(-2.0F, -1.0F, -1.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		tip.setTextureOffset(1, 13).addBox(-4.0F, 0.0F, 0.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
		tip.setTextureOffset(0, 13).addBox(1.0F, 0.0F, 0.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
		tip.setTextureOffset(1, 14).addBox(-2.0F, 0.0F, -2.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		tip.setTextureOffset(3, 13).addBox(-2.0F, 0.0F, 3.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);

		bb_main = new ModelRenderer(this);
		bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
		bb_main.setTextureOffset(0, 0).addBox(-1.0F, -12.0F, -1.0F, 2.0F, 7.0F, 2.0F, 0.0F, false);
		bb_main.setTextureOffset(0, 0).addBox(-1.0F, -5.0F, -2.0F, 2.0F, 1.0F, 4.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks,
			float netHeadYaw, float headPitch) {
		// previously the render function, render code was moved to a method below
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red,
			float green, float blue, float alpha) {
		feather.render(matrixStack, buffer, packedLight, packedOverlay);
		tip.render(matrixStack, buffer, packedLight, packedOverlay);
		bb_main.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}