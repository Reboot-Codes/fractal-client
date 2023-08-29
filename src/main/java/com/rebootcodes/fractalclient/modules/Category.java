package com.rebootcodes.fractalclient.modules;

import com.rebootcodes.fractalclient.utils.Color;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class Category {
    public final String name;
    public final String title;
    public final ItemStack icon;
    public final Color color;

    public Category(String name, String title, ItemStack icon, Color color) {
        this.name = name;
        this.title = title;
        this.icon = icon != null ? icon : Items.AIR.getDefaultStack();
        this.color = color != null ? color : new Color(255,255,255);
    }
}
