package net.dejinn.septemberRainCore.FileSystem;

import net.dejinn.septemberRainCore.Main.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class DisplayModels {
    public void MakeDisplayModelsFile()  {
        new FileManager().MakeNewFile("displayModels.yml", "");
        this.ReconcileDefaultData();
    }

    public void ReconcileDefaultData(){
        HashMap<String, Object> defaultData = new HashMap<String,Object>();

        defaultData.put("workstump.summon_command","/summon block_display %s %s %s {Tags:[\"%s\"],Passengers:[{id:\"minecraft:block_display\",block_state:{Name:\"minecraft:oak_log\",Properties:{axis:\"y\"}},transformation:[1f,0f,0f,-0.5f,0f,0.25f,0f,0.6875f,0f,0f,1f,-0.5f,0f,0f,0f,1f]},{id:\"minecraft:block_display\",block_state:{Name:\"minecraft:oak_wood\",Properties:{axis:\"y\"}},transformation:[1f,0f,0f,-0.5f,0f,0.0625f,0f,0.9375f,0f,0f,0.0625f,-0.5f,0f,0f,0f,1f]},{id:\"minecraft:block_display\",block_state:{Name:\"minecraft:oak_wood\",Properties:{axis:\"y\"}},transformation:[1f,0f,0f,-0.5f,0f,0.0625f,0f,0.9375f,0f,0f,0.0625f,0.4375f,0f,0f,0f,1f]},{id:\"minecraft:block_display\",block_state:{Name:\"minecraft:oak_wood\",Properties:{axis:\"y\"}},transformation:[0.0625f,0f,0f,-0.5f,0f,0.0625f,0f,0.9375f,0f,0f,0.875f,-0.4375f,0f,0f,0f,1f]},{id:\"minecraft:block_display\",block_state:{Name:\"minecraft:oak_wood\",Properties:{axis:\"y\"}},transformation:[0.0625f,0f,0f,0.4375f,0f,0.0625f,0f,0.9375f,0f,0f,0.875f,-0.4375f,0f,0f,0f,1f]},{id:\"minecraft:block_display\",block_state:{Name:\"minecraft:oak_log\",Properties:{axis:\"y\"}},transformation:[0.75f,0f,0f,-0.375f,0f,0.0625f,0f,0.625f,0f,0f,0.75f,-0.375f,0f,0f,0f,1f]},{id:\"minecraft:block_display\",block_state:{Name:\"minecraft:oak_log\",Properties:{axis:\"z\"}},transformation:[0.5f,0f,0f,-0.25f,0f,0.1294095226f,0.6640740056f,-0.0625f,0f,-0.4829629131f,0.1779380935f,0f,0f,0f,0f,1f]},{id:\"minecraft:block_display\",block_state:{Name:\"minecraft:oak_log\",Properties:{axis:\"z\"}},transformation:[-0.5f,0f,0f,0.25f,0f,0.1294095226f,0.7848147339f,-0.0625f,0f,0.4829629131f,-0.2102904741f,0f,0f,0f,0f,1f]},{id:\"minecraft:block_display\",block_state:{Name:\"minecraft:oak_log\",Properties:{axis:\"z\"}},transformation:[0f,-0.4829629131f,0.2102904741f,0f,0f,0.1294095226f,0.7848147339f,-0.0625f,-0.5f,0f,0f,0.25f,0f,0f,0f,1f]},{id:\"minecraft:block_display\",block_state:{Name:\"minecraft:oak_log\",Properties:{axis:\"z\"}},transformation:[0f,0.4829629131f,-0.2102904741f,0f,0f,0.1294095226f,0.7848147339f,-0.0625f,0.5f,0f,0f,-0.25f,0f,0f,0f,1f]},{id:\"minecraft:block_display\",block_state:{Name:\"minecraft:oak_log\",Properties:{axis:\"y\"}},transformation:[1f,0f,0f,-0.5f,0f,0.0625f,0f,0f,0f,0f,1f,-0.5f,0f,0f,0f,1f]},{id:\"minecraft:block_display\",block_state:{Name:\"minecraft:stripped_oak_wood\",Properties:{axis:\"x\"}},transformation:[0.875f,0f,0f,-0.4375f,0f,0.0625f,0f,0.9375f,0f,0f,0.875f,-0.4375f,0f,0f,0f,1f]}]}}");

        File DataFile = new FileManager().MakeNewFile("displayModels.yml","");
        FileConfiguration Data = YamlConfiguration.loadConfiguration(DataFile);
        // Reconcile new data
        for (String key : defaultData.keySet()){
            if (!Data.contains(key)){
                Data.set(key,defaultData.get(key));
            }
        }
        try {
            Data.save(DataFile);
        } catch (IOException ex) {
            Main.getInstance().getLogger().warning(ex.toString());
        }
    }

    public String getDisplayModelCommand(String modelId) {
        File displayModelsYML = new FileManager().MakeNewFile("displayModels.yml", "");
        FileConfiguration modelData = YamlConfiguration.loadConfiguration(displayModelsYML);

        return modelData.get(modelId + ".summon_command").toString();
    }
}
