package com.codecool.kruppa.pet;

import java.util.ArrayList;
import java.util.List;

public class Storage {

    private static List<String> trees  = new ArrayList<>();
    private static List<String> savedTrees = new ArrayList<>();
    private static List<String> savedIds = new ArrayList<>();


    public static void addToStorage(String json){
        trees.add(json);
    }

    public static String getTreeByNumber(int number){
        return trees.get(number);
    }

    public static int getStorageSize(){
        return trees.size();
    }

    public static void addToSavedTrees(String savedTree){ savedTrees.add(savedTree); }

    public static void addToSavedIds(String savedid){ savedIds.add(savedid); }

    public static int getSavedTreesSize(){
        return savedTrees.size();
    }

    public static List<String> getAll() {
        return savedTrees;
    }

    public static List<String> getAllId() {
        return savedIds;
    }
}
