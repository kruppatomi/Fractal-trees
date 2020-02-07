package com.codecool.kruppa.pet.controller;


import com.codecool.kruppa.pet.CreateJSONfile;
import com.codecool.kruppa.pet.Storage;
import org.json.simple.JSONArray;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;



@WebServlet(urlPatterns = {"/JSON"})
public class JSONController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        out.print("[");
        for (int i = 0; i<Storage.getStorageSize(); i++){
            if (i < Storage.getStorageSize()-1){
                out.print(Storage.getTreeByNumber(i));
                out.print(",");
            }
            else {
                out.print(Storage.getTreeByNumber(i));
            }

        }
        out.print("]");


        out.flush();
    }
}
