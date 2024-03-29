package de.webcode.tchallenges.utils.menu.impl;

import com.destroystokyo.paper.profile.PlayerProfile;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import de.webcode.tchallenges.TChallenges;
import de.webcode.tchallenges.utils.ItemFactory;
import de.webcode.tchallenges.utils.menu.Menu;
import de.webcode.tchallenges.utils.menu.PlayerMenuUtilityManager;
import de.webcode.tchallenges.utils.menu.playermenuutilitys.PlayerMenuUtility;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.UUID;

public class ServerSettingsMenu extends Menu {

    public ServerSettingsMenu(PlayerMenuUtility playerMenuUtility){
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        return "§e§lServereinstellungen";
    }

    @Override
    public int getSlots() {
        return 9 * 3;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {
        switch(e.getCurrentItem().getType()){
            case BARRIER:
                new SettingMenu(playerMenuUtility).open();
                break;
            case PLAYER_HEAD:

                String displayName = e.getCurrentItem().getItemMeta().getDisplayName();
                if (displayName.contains("Spieler")) {
                    new PlayerMenu(playerMenuUtility).open();
                    return;
                }else if(displayName.contains("Welt-Einstellungen")){
                    new WorldSettingsMenu(playerMenuUtility).open();
                }
                break;
        }

    }

    @Override
    public void setMenuItems() {
        ItemFactory itemFactory = TChallenges.getInstance().getItemFactory();
        ItemStack worldSettings = itemFactory.getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzY5MTk2YjMzMGM2Yjg5NjJmMjNhZDU2MjdmYjZlY2NlNDcyZWFmNWM5ZDQ0Zjc5MWY2NzA5YzdkMGY0ZGVjZSJ9fX0=");
        itemFactory.setDisplayName(worldSettings, "§aWelt-Einstellungen");

        ItemStack players = itemFactory.create(Material.PLAYER_HEAD, "§aSpieler");
        ItemMeta itemMeta = players.getItemMeta();
        SkullMeta skullMeta = (SkullMeta) itemMeta;
        skullMeta.setOwningPlayer(playerMenuUtility.getOwner());
        players.setItemMeta(skullMeta);

        ItemStack itemStack = itemFactory.getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDZiYTYzMzQ0ZjQ5ZGQxYzRmNTQ4OGU5MjZiZjNkOWUyYjI5OTE2YTZjNTBkNjEwYmI0MGE1MjczZGM4YzgyIn19fQ==");
        itemStack = itemFactory.setDisplayName(itemStack, "§cComing Soon");

        ItemStack back = itemFactory.create(Material.BARRIER, "§cZurück");


        inventory.setItem(10, worldSettings);
        inventory.setItem(12, players);
        inventory.setItem(14, itemStack);
        inventory.setItem(16, itemStack);
        inventory.setItem(22, back);

    }
}
