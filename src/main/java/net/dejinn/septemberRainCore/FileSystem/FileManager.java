package net.dejinn.septemberRainCore.FileSystem;

import net.dejinn.septemberRainCore.Main.Main;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileManager {

    public File MakeFolder(String FolderName,String FolderPath){
        File newFolder = new File(Main.getInstance().getDataFolder() + FolderPath, FolderName);
        if (!newFolder.exists()){
            boolean FolderMade = newFolder.mkdir();
            if (FolderMade){
                return newFolder;
            } else {
                Main.getInstance().getLogger().warning("Could not make folder: " + FolderPath + FolderName);
                return null;
            }

        } else {
            return newFolder;
        }

    }

    public File MakeNewFile(String FileName, String FilePath) {
        File newFile = new File(Main.getInstance().getDataFolder() + FilePath, FileName);
        if (!newFile.exists()){
            try {
                newFile.createNewFile();
                return newFile;
            } catch(IOException ex){
                Main.getInstance().getLogger().warning(ex.toString());
            }

        } else {
            return newFile;
        }
        return null;
    }

    public boolean CheckFile(String FileName, String FilePath){
        File newFile = new File(Main.getInstance().getDataFolder() + FilePath, FileName);
        return newFile.exists();
    }

    public void MoveFile(String FileName,String NewFileName, String OldFilePath, String NewFilePath){
        Path MoveSourcePath = Paths.get(Main.getInstance().getDataFolder() + OldFilePath + File.separatorChar + FileName);
        Path MoveTargetPath = Paths.get(Main.getInstance().getDataFolder() + NewFilePath + File.separatorChar + NewFileName);
        try {
            Files.move(MoveSourcePath,MoveTargetPath);
        } catch (IOException ex) {
            Main.getInstance().getLogger().warning(ex.toString());
        }

    }
}
