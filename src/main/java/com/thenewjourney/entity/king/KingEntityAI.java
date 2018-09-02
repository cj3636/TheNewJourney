package com.thenewjourney.entity.king;

import net.minecraft.entity.ai.EntityAIAttackMelee;


public class KingEntityAI extends EntityAIAttackMelee
{
    private final KingEntity king;
    private int raiseArmTicks;

    public KingEntityAI(KingEntity kingIn, double speedIn, boolean longMemoryIn)
    {
        super(kingIn, speedIn, longMemoryIn);
        this.king = kingIn;
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting()
    {
        super.startExecuting();
        this.raiseArmTicks = 0;
    }

    /**
     * Resets the task
     */
    public void resetTask()
    {
        super.resetTask();
        this.king.setArmsRaised(false);
    }

    /**
     * Updates the task
     */
    public void updateTask()
    {
        super.updateTask();
        ++this.raiseArmTicks;

        if (this.raiseArmTicks >= 5 && this.attackTick < 10)
        {
            this.king.setArmsRaised(true);
        }
        else
        {
            this.king.setArmsRaised(false);
        }
    }
}