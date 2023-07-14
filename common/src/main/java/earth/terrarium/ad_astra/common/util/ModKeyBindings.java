package earth.terrarium.ad_astra.common.util;

import earth.terrarium.ad_astra.client.registry.ClientModKeybindings;
import earth.terrarium.ad_astra.common.networking.packet.messages.ServerboundKeybindPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;

import java.util.HashMap;
import java.util.UUID;

/**
 * Checks if a key is pressed, on both the client or server. On the server, it's saved for every player by mapping their UUID to an instance of this class. Then, when a specific player presses a key,
 * it's only pressed for that player's UUID.
 */
public class ModKeyBindings {
    public static final HashMap<UUID, ModKeyBindings> PLAYER_KEYS = new HashMap<>();

    private boolean clickingJump;
    private boolean clickingSprint;
    private boolean clickingForward;
    private boolean clickingBack;
    private boolean clickingLeft;
    private boolean clickingRight;

    public static boolean jumpKeyDown(Player player) {
        return player.level().isClientSide ? getClientKeyPressed(player, ServerboundKeybindPacket.Keybind.JUMP) : getServerKeyPressed(player, ServerboundKeybindPacket.Keybind.JUMP);
    }

    public static boolean sprintKeyDown(Player player) {
        return player.level().isClientSide ? getClientKeyPressed(player, ServerboundKeybindPacket.Keybind.SPRINT) : getServerKeyPressed(player, ServerboundKeybindPacket.Keybind.SPRINT);
    }

    public static boolean forwardKeyDown(Player player) {
        return player.level().isClientSide ? getClientKeyPressed(player, ServerboundKeybindPacket.Keybind.FORWARD) : getServerKeyPressed(player, ServerboundKeybindPacket.Keybind.FORWARD);
    }

    public static boolean backKeyDown(Player player) {
        return player.level().isClientSide ? getClientKeyPressed(player, ServerboundKeybindPacket.Keybind.BACK) : getServerKeyPressed(player, ServerboundKeybindPacket.Keybind.BACK);
    }

    public static boolean leftKeyDown(Player player) {
        return player.level().isClientSide ? getClientKeyPressed(player, ServerboundKeybindPacket.Keybind.LEFT) : getServerKeyPressed(player, ServerboundKeybindPacket.Keybind.LEFT);
    }

    public static boolean rightKeyDown(Player player) {
        return player.level().isClientSide ? getClientKeyPressed(player, ServerboundKeybindPacket.Keybind.RIGHT) : getServerKeyPressed(player, ServerboundKeybindPacket.Keybind.RIGHT);
    }

    private static boolean getServerKeyPressed(Player player, ServerboundKeybindPacket.Keybind key) {
        PLAYER_KEYS.putIfAbsent(player.getUUID(), new ModKeyBindings());
        return switch (key) {
            case JUMP -> PLAYER_KEYS.get(player.getUUID()).clickingJump;

            case SPRINT -> PLAYER_KEYS.get(player.getUUID()).clickingSprint;

            case FORWARD -> PLAYER_KEYS.get(player.getUUID()).clickingForward;

            case BACK -> PLAYER_KEYS.get(player.getUUID()).clickingBack;

            case LEFT -> PLAYER_KEYS.get(player.getUUID()).clickingLeft;

            case RIGHT -> PLAYER_KEYS.get(player.getUUID()).clickingRight;
        };
    }


    private static boolean getClientKeyPressed(Player player, ServerboundKeybindPacket.Keybind key) {
        if (!player.getUUID().equals(Minecraft.getInstance().player.getUUID())) {
            return false;
        }

        return switch (key) {
            case JUMP -> ClientModKeybindings.clickingJump;

            case SPRINT -> ClientModKeybindings.clickingSprint;

            case FORWARD -> ClientModKeybindings.clickingForward;

            case BACK -> ClientModKeybindings.clickingBack;

            case LEFT -> ClientModKeybindings.clickingLeft;

            case RIGHT -> ClientModKeybindings.clickingRight;
        };
    }

    public static void pressedKeyOnServer(UUID uuid, ServerboundKeybindPacket.Keybind key, boolean keyDown) {
        PLAYER_KEYS.putIfAbsent(uuid, new ModKeyBindings());

        switch (key) {
            case JUMP -> PLAYER_KEYS.get(uuid).clickingJump = keyDown;

            case SPRINT -> PLAYER_KEYS.get(uuid).clickingSprint = keyDown;

            case FORWARD -> PLAYER_KEYS.get(uuid).clickingForward = keyDown;

            case BACK -> PLAYER_KEYS.get(uuid).clickingBack = keyDown;

            case LEFT -> PLAYER_KEYS.get(uuid).clickingLeft = keyDown;

            case RIGHT -> PLAYER_KEYS.get(uuid).clickingRight = keyDown;
        }
    }

    public static void onPlayerQuit(Player player) {
        PLAYER_KEYS.remove(player.getUUID());
    }
}