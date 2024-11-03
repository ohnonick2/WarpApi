package net.ohnonick2.warpapi.warp;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.ohnonick2.warpapi.FileManger;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class WarpManager {

    private FileManger fileManger;


    public WarpManager(String path) {

        fileManger = new FileManger(path);

    }


    /**
     *
     * @param name
     * @param world
     * @param x
     * @param y
     * @param z
     * @param yaw
     * @param pitch
     * @param owner
     * @param isPublic
     * @return boolean
     * if result is true, the warp was created successfully
     * if result is false, the warp could not be created
     *
     *
     */
    public boolean addWarp(@NotNull String name, @NotNull String world, @NotNull double x, @NotNull double y, @NotNull double z, @NotNull float yaw, @NotNull float pitch, @NotNull UUID owner, @NotNull boolean isPublic) {
        if (exists(name)) {
            return false;
        }
        //Only allow a-z, A-Z, 0-9
        name = name.replaceAll("[^a-zA-Z0-9]", "");

        Warp warp = new Warp(name, world, x, y, z, yaw, pitch, owner, isPublic);
        return save(warp);
    }

    /**
     * @param name
     * @return boolean
     * if result is true, the warp was deleted successfully
     * if result is false, the warp could not be deleted
     */
    public boolean deleteWarp(String name) {
        return fileManger.removeFile(name + ".json");
    }

    /**
     * @param name
     * @return Warp
     * if result is null, the warp does not exist
     */
    public boolean exists(String name) {
        return fileManger.existFile(name + ".json");
    }

    /**
     * @return Warp
     * if result is null, the warp does not exist
     */
    public List<Warp> getWarps() {

        File[] files = fileManger.getFiles();

        if (files == null) {
            return null;
        }

        List<Warp> warps = new ArrayList<>();

        for (File file : files) {
            String name = file.getName().replace(".json", "");

            JsonObject warpJson = new JsonObject();

            try {
                warpJson = JsonParser.parseString(fileManger.readFile(name + ".json")).getAsJsonObject();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return warps;
    }


    private boolean save(@NotNull Warp warp) {

        JsonObject warpJson = new JsonObject();
        warpJson.addProperty("name", warp.name);
        warpJson.addProperty("world", warp.world);
        warpJson.addProperty("x", warp.x);
        warpJson.addProperty("y", warp.y);
        warpJson.addProperty("z", warp.z);
        warpJson.addProperty("yaw", warp.yaw);
        warpJson.addProperty("pitch", warp.pitch);
        warpJson.addProperty("owner", String.valueOf(warp.owner));
        warpJson.addProperty("isPublic", warp.isPublic);

        return fileManger.createFile(warp.name + ".json", warpJson.toString());


    }



    public class Warp {
        private String name;
        private String world;
        private double x;
        private double y;
        private double z;
        private float yaw;
        private float pitch;
        private UUID owner;
        private boolean isPublic;


        public Warp(String name, String world, double x, double y, double z, float yaw, float pitch, UUID owner, boolean isPublic) {
            this.name = name;
            this.world = world;
            this.x = x;
            this.y = y;
            this.z = z;
            this.yaw = yaw;
            this.pitch = pitch;
            this.owner = owner;
            this.isPublic = isPublic;
        }


    }


}
