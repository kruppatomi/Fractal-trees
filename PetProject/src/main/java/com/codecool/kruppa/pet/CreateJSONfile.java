package com.codecool.kruppa.pet;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class CreateJSONfile extends Tree{


    public CreateJSONfile(int id, int length, int angle, int newLength, String path) {
        super(id, length, angle, newLength, path);
    }

    @SuppressWarnings("unchecked")
    public String makeJSON(){
        JSONObject TreeDetails = new JSONObject();
        TreeDetails.put("id", id);
        TreeDetails.put("length", length);
        TreeDetails.put("angle", angle);
        TreeDetails.put("newLength", newLength);
        TreeDetails.put("path", path);

        JSONObject treeObject = new JSONObject();
        treeObject.put("Tree", TreeDetails);


        //Add employees to list
        /**
        JSONArray treeList = new JSONArray();
        treeList.add(treeObject);
**/
        //Write JSON file
        return treeObject.toJSONString();

    }
}
