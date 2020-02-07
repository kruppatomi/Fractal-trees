package com.codecool.kruppa.pet.controller;


import com.codecool.kruppa.pet.CreateJSONfile;
import com.codecool.kruppa.pet.Storage;
import com.codecool.kruppa.pet.config.TemplateEngineUtil;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;


@WebServlet(urlPatterns = {"/"})
public class IndexPageController extends HttpServlet {



    int newAngle;
    int newLength;

    public void setNewAngle(int newAngle) {
        this.newAngle = newAngle;
    }

    public void setNewLength(int newLength) {
        this.newLength = newLength;
    }


    public void paint(Graphics2D g, int length){
        try{
            makeTree(length,0,684,453, g);
        }catch (InterruptedException ex){
            Logger.getLogger(IndexPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void makeTree(int length, int angle, int x, int y, Graphics2D g) throws InterruptedException {
        int xmove = (int) (Math.cos(Math.toRadians(angle + 90)) * length);
        int ymove = (int) (Math.sin(Math.toRadians(angle - 90)) * length);
        g.setStroke(new BasicStroke(2));
        g.drawLine(x,y,x+xmove,y+ymove);
        if(length>=1){
            makeTree(length-newLength,angle+newAngle,x+xmove,y+ymove,g);
            makeTree(length-newLength,angle-newAngle,x+xmove,y+ymove,g);
        }
    }





    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {


        String Sp = req.getParameter("HStoredPictures");
        if(Sp != null){

            Sp = Sp.replace("http://localhost:8888", "");
            Storage.addToSavedTrees(Sp);
        }

        String Spid = req.getParameter("HStoredPictures");
        if(Spid != null){

            Spid = Spid.replace("http://localhost:8888/static/img/trees/ImageName", "");
            Spid = Spid.replace(".PNG", "");
            Storage.addToSavedIds(Spid);
        }



        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("picId", Storage.getStorageSize());
        context.setVariable("savedTrees", Storage.getAll());
        context.setVariable("savedIds", Storage.getAllId());
        engine.process("index.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String reqlength = req.getParameter("length");
        String reqangle = req.getParameter("angle");
        String reqnewLength = req.getParameter("newLength");

        String Sp = req.getParameter("HStoredPictures");
        if(Sp != null){

            Sp = Sp.replace("http://localhost:8888", "");
            Storage.addToSavedTrees(Sp);
        }

        String Spid = req.getParameter("HStoredPictures");
        if(Spid != null){

            Spid = Spid.replace("http://localhost:8888/static/img/trees/ImageName", "");
            Spid = Spid.replace(".PNG", "");
            Storage.addToSavedIds(Spid);
        }


        if (reqlength != null && reqangle != null && reqnewLength != null) {
            int length = Integer.parseInt(reqlength);
            setNewAngle(Integer.parseInt(reqangle));
            setNewLength(Integer.parseInt(reqnewLength));

            CreateJSONfile tree = new CreateJSONfile(Storage.getStorageSize()+1, length, newAngle, newLength, "/static/img/trees/ImageName"+ (Storage.getStorageSize()+1) +".PNG");
            Storage.addToStorage(tree.makeJSON());

            BufferedImage bi = new BufferedImage(1400, 700, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = bi.createGraphics();
            g.setComposite(AlphaComposite.Clear);

            g.setComposite(AlphaComposite.Src);
            int alpha = 127; // 50% transparent
            g.setColor(new Color(255, 0, 0, alpha));

            g.setColor(Color.white);
            paint(g, length);
            g.dispose();

            ImageIO.write(bi, "png", new File("/home/kruppa/PetProject/src/main/webapp/static/img/trees/ImageName"+Storage.getStorageSize()+".PNG"));
        }


        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("picId", Storage.getStorageSize());
        context.setVariable("savedTrees", Storage.getAll());
        context.setVariable("savedIds", Storage.getAllId());
        engine.process("index.html", context, resp.getWriter());
    }
}
