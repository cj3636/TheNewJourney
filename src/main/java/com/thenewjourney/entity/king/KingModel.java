package com.thenewjourney.entity.king;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class KingModel extends ModelBase {
    //fields
    ModelRenderer Beard;
    ModelRenderer Crown1;
    ModelRenderer Crown2;
    ModelRenderer Crown3;
    ModelRenderer Crown4;
    ModelRenderer CrownA;
    ModelRenderer CrownB;
    ModelRenderer CrownC;
    ModelRenderer CrownD;
    ModelRenderer CrownE;
    ModelRenderer CrownF;
    ModelRenderer CrownG;
    ModelRenderer CrownH;
    ModelRenderer CrownI;
    ModelRenderer CrownJ;
    ModelRenderer CrownK;
    ModelRenderer CrownL;
    ModelRenderer CrownM;
    ModelRenderer head;
    ModelRenderer body;
    ModelRenderer rightarm;
    ModelRenderer leftarm;
    ModelRenderer rightleg;
    ModelRenderer leftleg;
    ModelRenderer Cape1;
    ModelRenderer Cape2;
    ModelRenderer Cape3;
    ModelRenderer Cape4;
    ModelRenderer Cape5;
    ModelRenderer Cape6;
    ModelRenderer Cape7;
    ModelRenderer Cape8;
    ModelRenderer Cape9;
    ModelRenderer Cape10;
    ModelRenderer Cape11;
    ModelRenderer Cape12;

    public KingModel() {
        textureWidth = 128;
        textureHeight = 64;

        Beard = new ModelRenderer(this, 65, 0);
        Beard.addBox(0F, 0F, 0F, 6, 1, 1);
        Beard.setRotationPoint(-3F, 0F, -4F);
        Beard.setTextureSize(128, 64);
        Beard.mirror = true;
        setRotation(Beard, 0F, 0F, 0F);
        Crown1 = new ModelRenderer(this, 1, 40);
        Crown1.addBox(0F, 0F, 0F, 9, 1, 1);
        Crown1.setRotationPoint(-4.5F, -8.25F, -4.5F);
        Crown1.setTextureSize(128, 64);
        Crown1.mirror = true;
        setRotation(Crown1, 0F, 0F, 0F);
        Crown2 = new ModelRenderer(this, 0, 40);
        Crown2.addBox(0F, 0F, 0F, 9, 1, 1);
        Crown2.setRotationPoint(-4.5F, -8.3F, 3.5F);
        Crown2.setTextureSize(128, 64);
        Crown2.mirror = true;
        setRotation(Crown2, 0F, 0F, 0F);
        Crown3 = new ModelRenderer(this, 40, 40);
        Crown3.addBox(0F, 0F, 0F, 1, 1, 9);
        Crown3.setRotationPoint(3.5F, -8.3F, -4.5F);
        Crown3.setTextureSize(128, 64);
        Crown3.mirror = true;
        setRotation(Crown3, 0F, 0F, 0F);
        Crown4 = new ModelRenderer(this, 0, 40);
        Crown4.addBox(0F, 0F, 0F, 1, 1, 9);
        Crown4.setRotationPoint(-4.5F, -8.3F, -4.5F);
        Crown4.setTextureSize(128, 64);
        Crown4.mirror = true;
        setRotation(Crown4, 0F, 0F, 0F);
        CrownA = new ModelRenderer(this, 0, 0);
        CrownA.addBox(0F, 0F, 0F, 1, 1, 2);
        CrownA.setRotationPoint(3.5F, -9.3F, -4.5F);
        CrownA.setTextureSize(128, 64);
        CrownA.mirror = true;
        setRotation(CrownA, 0F, 0F, 0F);
        CrownB = new ModelRenderer(this, 0, 0);
        CrownB.addBox(0F, 0F, 0F, 1, 1, 2);
        CrownB.setRotationPoint(3.5F, -9.3F, 2.5F);
        CrownB.setTextureSize(128, 64);
        CrownB.mirror = true;
        setRotation(CrownB, 0F, 0F, 0F);
        CrownC = new ModelRenderer(this, 0, 0);
        CrownC.addBox(0F, 0F, 0F, 1, 1, 2);
        CrownC.setRotationPoint(-4.5F, -9.3F, -4.5F);
        CrownC.setTextureSize(128, 64);
        CrownC.mirror = true;
        setRotation(CrownC, 0F, 0F, 0F);
        CrownD = new ModelRenderer(this, 0, 0);
        CrownD.addBox(0F, 0F, 0F, 1, 1, 2);
        CrownD.setRotationPoint(-4.5F, -9.3F, 2.5F);
        CrownD.setTextureSize(128, 64);
        CrownD.mirror = true;
        setRotation(CrownD, 0F, 0F, 0F);
        CrownE = new ModelRenderer(this, 0, 0);
        CrownE.addBox(0F, 0F, 0F, 1, 1, 1);
        CrownE.setRotationPoint(3.5F, -9.3F, -1.5F);
        CrownE.setTextureSize(128, 64);
        CrownE.mirror = true;
        setRotation(CrownE, 0F, 0F, 0F);
        CrownF = new ModelRenderer(this, 0, 0);
        CrownF.addBox(0F, 0F, 0F, 1, 1, 1);
        CrownF.setRotationPoint(3.5F, -9.3F, 0.5F);
        CrownF.setTextureSize(128, 64);
        CrownF.mirror = true;
        setRotation(CrownF, 0F, 0F, 0F);
        CrownG = new ModelRenderer(this, 0, 0);
        CrownG.addBox(0F, 0F, 0F, 1, 1, 1);
        CrownG.setRotationPoint(-4.5F, -9.3F, 0.5F);
        CrownG.setTextureSize(128, 64);
        CrownG.mirror = true;
        setRotation(CrownG, 0F, 0F, 0F);
        CrownH = new ModelRenderer(this, 0, 0);
        CrownH.addBox(0F, 0F, 0F, 1, 1, 1);
        CrownH.setRotationPoint(-4.5F, -9.3F, -1.5F);
        CrownH.setTextureSize(128, 64);
        CrownH.mirror = true;
        setRotation(CrownH, 0F, 0F, 0F);
        CrownI = new ModelRenderer(this, 0, 0);
        CrownI.addBox(0F, 0F, 0F, 1, 1, 1);
        CrownI.setRotationPoint(-2.5F, -9.3F, 3.5F);
        CrownI.setTextureSize(128, 64);
        CrownI.mirror = true;
        setRotation(CrownI, 0F, 0F, 0F);
        CrownJ = new ModelRenderer(this, 0, 0);
        CrownJ.addBox(0F, 0F, 0F, 1, 1, 1);
        CrownJ.setRotationPoint(1.5F, -9.3F, 3.5F);
        CrownJ.setTextureSize(128, 64);
        CrownJ.mirror = true;
        setRotation(CrownJ, 0F, 0F, 0F);
        CrownK = new ModelRenderer(this, 0, 0);
        CrownK.addBox(0F, 0F, 0F, 1, 1, 1);
        CrownK.setRotationPoint(-0.5F, -9.3F, 3.5F);
        CrownK.setTextureSize(128, 64);
        CrownK.mirror = true;
        setRotation(CrownK, 0F, 0F, 0F);
        CrownL = new ModelRenderer(this, 0, 50);
        CrownL.addBox(0F, 0F, 0F, 5, 1, 1);
        CrownL.setRotationPoint(-2.5F, -9.3F, -4.5F);
        CrownL.setTextureSize(128, 64);
        CrownL.mirror = true;
        setRotation(CrownL, 0F, 0F, 0F);
        CrownM = new ModelRenderer(this, 0, 0);
        CrownM.addBox(0F, 0F, 0F, 3, 1, 1);
        CrownM.setRotationPoint(-1.5F, -10.3F, -4.5F);
        CrownM.setTextureSize(128, 64);
        CrownM.mirror = true;
        setRotation(CrownM, 0F, 0F, 0F);
        head = new ModelRenderer(this, 0, 0);
        head.addBox(-4F, -8F, -4F, 8, 8, 8);
        head.setRotationPoint(0F, 0F, 0F);
        head.setTextureSize(128, 64);
        head.mirror = true;
        setRotation(head, 0F, 0F, 0F);
        body = new ModelRenderer(this, 16, 16);
        body.addBox(-4F, 0F, -2F, 8, 12, 4);
        body.setRotationPoint(0F, 0F, 0F);
        body.setTextureSize(128, 64);
        body.mirror = true;
        setRotation(body, 0F, 0F, 0F);
        rightarm = new ModelRenderer(this, 40, 16);
        rightarm.addBox(-3F, -2F, -2F, 4, 12, 4);
        rightarm.setRotationPoint(-5F, 2F, 0F);
        rightarm.setTextureSize(128, 64);
        rightarm.mirror = true;
        setRotation(rightarm, 0F, 0F, 0F);
        leftarm = new ModelRenderer(this, 48, 0);
        leftarm.addBox(-1F, -2F, -2F, 4, 12, 4);
        leftarm.setRotationPoint(5F, 2F, 0F);
        leftarm.setTextureSize(128, 64);
        leftarm.mirror = true;
        setRotation(leftarm, 0.0174533F, 0F, 0F);
        rightleg = new ModelRenderer(this, 0, 16);
        rightleg.addBox(-2F, 0F, -2F, 4, 12, 4);
        rightleg.setRotationPoint(-2F, 12F, 0F);
        rightleg.setTextureSize(128, 64);
        rightleg.mirror = true;
        setRotation(rightleg, 0F, 0F, 0F);
        leftleg = new ModelRenderer(this, 32, 0);
        leftleg.addBox(-2F, 0F, -2F, 4, 12, 4);
        leftleg.setRotationPoint(2F, 12F, 0F);
        leftleg.setTextureSize(128, 64);
        leftleg.mirror = true;
        setRotation(leftleg, 0F, 0F, 0F);
        Cape1 = new ModelRenderer(this, 0, 34);
        Cape1.addBox(0F, 0F, 0F, 16, 2, 1);
        Cape1.setRotationPoint(-8F, 0F, 1.9F);
        Cape1.setTextureSize(128, 64);
        Cape1.mirror = true;
        setRotation(Cape1, 0.1396263F, 0F, 0F);
        Cape2 = new ModelRenderer(this, 0, 34);
        Cape2.addBox(0F, 0F, 0F, 16, 2, 1);
        Cape2.setRotationPoint(-8F, 2F, 2.2F);
        Cape2.setTextureSize(128, 64);
        Cape2.mirror = true;
        setRotation(Cape2, 0F, 0F, 0F);
        Cape3 = new ModelRenderer(this, 0, 34);
        Cape3.addBox(0F, 0F, 0F, 16, 2, 1);
        Cape3.setRotationPoint(-8F, 4F, 2.2F);
        Cape3.setTextureSize(128, 64);
        Cape3.mirror = true;
        setRotation(Cape3, 0F, 0F, 0F);
        Cape4 = new ModelRenderer(this, 0, 34);
        Cape4.addBox(0F, 0F, 0F, 16, 2, 1);
        Cape4.setRotationPoint(-8F, 6F, 2.2F);
        Cape4.setTextureSize(128, 64);
        Cape4.mirror = true;
        setRotation(Cape4, 0F, 0F, 0F);
        Cape5 = new ModelRenderer(this, 0, 34);
        Cape5.addBox(0F, 0F, 0F, 16, 2, 1);
        Cape5.setRotationPoint(-8F, 8F, 2.2F);
        Cape5.setTextureSize(128, 64);
        Cape5.mirror = true;
        setRotation(Cape5, 0F, 0F, 0F);
        Cape6 = new ModelRenderer(this, 0, 34);
        Cape6.addBox(0F, 0F, 0F, 16, 2, 1);
        Cape6.setRotationPoint(-8F, 10F, 2.2F);
        Cape6.setTextureSize(128, 64);
        Cape6.mirror = true;
        setRotation(Cape6, 0F, 0F, 0F);
        Cape7 = new ModelRenderer(this, 0, 34);
        Cape7.addBox(0F, 0F, 0F, 16, 2, 1);
        Cape7.setRotationPoint(-8F, 12F, 2.2F);
        Cape7.setTextureSize(128, 64);
        Cape7.mirror = true;
        setRotation(Cape7, 0F, 0F, 0F);
        Cape8 = new ModelRenderer(this, 0, 34);
        Cape8.addBox(0F, 0F, 0F, 16, 2, 1);
        Cape8.setRotationPoint(-8F, 14F, 2.2F);
        Cape8.setTextureSize(128, 64);
        Cape8.mirror = true;
        setRotation(Cape8, 0F, 0F, 0F);
        Cape9 = new ModelRenderer(this, 0, 34);
        Cape9.addBox(0F, 0F, 0F, 16, 2, 1);
        Cape9.setRotationPoint(-8F, 16F, 2.2F);
        Cape9.setTextureSize(128, 64);
        Cape9.mirror = true;
        setRotation(Cape9, 0F, 0F, 0F);
        Cape10 = new ModelRenderer(this, 0, 34);
        Cape10.addBox(0F, 0F, 0F, 16, 2, 1);
        Cape10.setRotationPoint(-8F, 18F, 2.2F);
        Cape10.setTextureSize(128, 64);
        Cape10.mirror = true;
        setRotation(Cape10, 0F, 0F, 0F);
        Cape11 = new ModelRenderer(this, 0, 34);
        Cape11.addBox(0F, 0F, 0F, 16, 2, 1);
        Cape11.setRotationPoint(-8F, 20F, 2.2F);
        Cape11.setTextureSize(128, 64);
        Cape11.mirror = true;
        setRotation(Cape11, 0F, 0F, 0F);
        Cape12 = new ModelRenderer(this, 0, 37);
        Cape12.addBox(0F, 0F, 0F, 16, 2, 1);
        Cape12.setRotationPoint(-8F, 22F, 2.2F);
        Cape12.setTextureSize(128, 64);
        Cape12.mirror = true;
        setRotation(Cape12, 0F, 0F, 0F);
        this.head.addChild(this.Crown1);
        this.head.addChild(this.Crown2);
        this.head.addChild(this.Crown3);
        this.head.addChild(this.Crown4);
        this.head.addChild(this.CrownA);
        this.head.addChild(this.CrownB);
        this.head.addChild(this.CrownC);
        this.head.addChild(this.CrownD);
        this.head.addChild(this.CrownE);
        this.head.addChild(this.CrownF);
        this.head.addChild(this.CrownG);
        this.head.addChild(this.CrownH);
        this.head.addChild(this.CrownI);
        this.head.addChild(this.CrownJ);
        this.head.addChild(this.CrownK);
        this.head.addChild(this.CrownL);
        this.head.addChild(this.CrownM);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        Beard.render(f5);
/*        Crown1.render(f5);
        Crown2.render(f5);
        Crown3.render(f5);
        Crown4.render(f5);
        CrownA.render(f5);
        CrownB.render(f5);
        CrownC.render(f5);
        CrownD.render(f5);
        CrownE.render(f5);
        CrownF.render(f5);
        CrownG.render(f5);
        CrownH.render(f5);
        CrownI.render(f5);
        CrownJ.render(f5);
        CrownK.render(f5);
        CrownL.render(f5);
        CrownM.render(f5);*/
        head.render(f5);
        body.render(f5);
        rightarm.render(f5);
        leftarm.render(f5);
        rightleg.render(f5);
        leftleg.render(f5);
        Cape1.render(f5);
        Cape2.render(f5);
        Cape3.render(f5);
        Cape4.render(f5);
        Cape5.render(f5);
        Cape6.render(f5);
        Cape7.render(f5);
        Cape8.render(f5);
        Cape9.render(f5);
        Cape10.render(f5);
        Cape11.render(f5);
        Cape12.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float time, float limbSwingDistance, float p_78087_3_, float headYrotation, float headXrotation, float p_78087_6_, Entity entity) {
        time = 4F;
        this.head.rotateAngleX = headXrotation / (180F / (float) Math.PI);
        this.head.rotateAngleY = headYrotation / (180F / (float) Math.PI);

        /**this.Crown1.rotateAngleX = headXrotation/(180F / (float)Math.PI);
         this.Crown2.rotateAngleY = headYrotation/(180F / (float)Math.PI);
         this.Crown3.rotateAngleX = headXrotation/(180F / (float)Math.PI);
         this.Crown4.rotateAngleY = headYrotation/(180F / (float)Math.PI);
         this.CrownA.rotateAngleX = headXrotation/(180F / (float)Math.PI);
         this.CrownB.rotateAngleY = headYrotation/(180F / (float)Math.PI);
         this.CrownC.rotateAngleX = headXrotation/(180F / (float)Math.PI);
         this.CrownD.rotateAngleY = headYrotation/(180F / (float)Math.PI);
         this.CrownE.rotateAngleX = headXrotation/(180F / (float)Math.PI);
         this.CrownF.rotateAngleY = headYrotation/(180F / (float)Math.PI);
         this.CrownG.rotateAngleX = headXrotation/(180F / (float)Math.PI);
         this.CrownH.rotateAngleY = headYrotation/(180F / (float)Math.PI);
         this.CrownI.rotateAngleX = headXrotation/(180F / (float)Math.PI);
         this.CrownJ.rotateAngleY = headYrotation/(180F / (float)Math.PI);
         this.CrownK.rotateAngleX = headXrotation/(180F / (float)Math.PI);
         this.CrownL.rotateAngleY = headYrotation/(180F / (float)Math.PI);
         this.CrownM.rotateAngleY = headYrotation/(180F / (float)Math.PI);
         **/

        this.rightarm.rotateAngleX = MathHelper.cos(time * 0.662F + (float) Math.PI) * 1.4F * limbSwingDistance;
        this.leftarm.rotateAngleX = MathHelper.cos(time * 0.662F) * 1.4F * limbSwingDistance;

        this.rightleg.rotateAngleX = MathHelper.cos(time * 0.662F + (float) Math.PI) * 1.4F * limbSwingDistance;
        this.leftleg.rotateAngleX = MathHelper.cos(time * 0.662F) * 1.4F * limbSwingDistance;


    }

}

