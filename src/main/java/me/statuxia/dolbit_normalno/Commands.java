package me.statuxia.dolbit_normalno;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Commands implements TabExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!sender.hasPermission("*"))
            return false;

        if (args.length < 1) {
            return false;
        }

        switch (args[0]) {
            case "start" -> {
                DOLBIT_NORMALNO.SWITCH = true;
            }
            case "stop" -> {
                DOLBIT_NORMALNO.SWITCH = false;
            }
        }
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        List<String> commands = Arrays.asList("start", "stop");

        if (!sender.isOp()) {
            return new ArrayList<>();
        }

        if (args.length == 1) {
            return commands;
        }
        return new ArrayList<>();
    }
}