package com.wyanbot.randomideas.item.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.io.Console;
import java.io.PrintStream;
import java.io.PrintWriter;

public class UnnamedScepterItem extends Item {

    private final int COOLDOWN = 30;
    private final int TELEPORT_DISTANCE = 10;

    public UnnamedScepterItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        if(interactionHand == InteractionHand.MAIN_HAND)
        {
            //for if the angle is a negative
            boolean xzNeg = false;
            boolean yNeg = false;

            //magnitude of the 3d vector
            double magnitude = player.getLookAngle().length();

            //horizontal line of the vector
            double xzLine = player.getLookAngle().horizontalDistance();

            //vertical looking angle
            double lookingAngle = Math.acos(xzLine / magnitude);

            //x side used for horizontal looking angle
            double xzAngle = player.getLookAngle().x;

            //checks if the angles are negative so it can be reversed back after calculations
            if(xzAngle < 0)
            {
                xzNeg = true;

                xzAngle *= -1;
            }

            player.sendSystemMessage(Component.literal("looking angle: " + lookingAngle));
            player.sendSystemMessage(Component.literal("x rot axis: " + player.getXRot()));
            if(player.getXRot() > 0)
            {
                yNeg = true;
            }

            //horizontal looking angle
            double horizontalAngle = Math.asin(xzAngle / xzLine);

            //new line based on teleport distance
            xzLine = (Math.cos(lookingAngle) * TELEPORT_DISTANCE);

            //calculates the increases needed to the players' positions for the teleport
            double yIncrease = (Math.sin(lookingAngle) * TELEPORT_DISTANCE);
            double xIncrease = (Math.sin(horizontalAngle) * xzLine);
            double zIncrease = (Math.cos(horizontalAngle) * xzLine);

            //changes x and z values back to negatives
            if(xzNeg)
            {
                xIncrease *= -1;
                zIncrease *= -1;
            }

            //changes y value back to negative
            if(yNeg)
            {
                yIncrease *= -1;
            }
            
            player.teleportTo((player.position().x + xIncrease), (player.position().y + yIncrease), (player.position().z + zIncrease));


            player.getCooldowns().addCooldown(this, COOLDOWN);
        }


        return super.use(level, player, interactionHand);
    }


}
