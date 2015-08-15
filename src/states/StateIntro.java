package states;

import app.Display;
import app.Engine;
import blocks.app.ProgressBar;
import debug.ObjectData;
import gfx.Colour;
import gfx.Drawing;
import gfx.Fonts;
import gfx.Text;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class StateIntro extends State
{
    private ProgressBar loadBar;
    private int loadTask, loadTick, loadTickNext;
    private String loadDetail;
    private boolean loadWait;
    
    public StateIntro()
    {
        this.loadBar = new ProgressBar(483, 400, 400, "GREEN_1", "BLACK", "GREEN_2", "GREEN_3");
        this.loadTask = 0;
        this.loadDetail = "INITIALISING APPLICATION";
        this.loadWait = false;
        this.loadTick = 0;
        this.loadTickNext = 30;
    }

    public void inputKeyPress(String key)
    {
        //
    }

    public void inputKeyRelease(String key)
    {
        //
    }

    public void inputKeyType(String key)
    {
        //
    }

    public void inputMouseClickL(MouseEvent e)
    {
        //
    }

    public void inputMouseClickR(MouseEvent e)
    {
        //
    }

    public void inputMouseMove(MouseEvent e)
    {
        //
    }
    
    private void loadNetwork()
    {
        // Loading Done
        this.loadWait = false;
    }
    
    private void loadSaveData()
    {
        // Loading Done
        this.loadWait = false;
    }
    
    private void loadResources()
    {
        // Blank Clipboard
        Engine.newClipboard();
        
        // Add Images
        Engine.getClipboard().addImage("APPLE", Drawing.resize(Drawing.getImage("fruit/apple.png"), 100, 100));
        Engine.getClipboard().addImage("APPLE2", Drawing.resize(Drawing.getImage("fruit/apple2.png"), 100, 100));
        Engine.getClipboard().addImage("BANANA", Drawing.resize(Drawing.getImage("fruit/banana.png"), 100, 100));
        Engine.getClipboard().addImage("CHERRIES", Drawing.resize(Drawing.getImage("fruit/cherries.png"), 100, 100));
        Engine.getClipboard().addImage("GRAPES", Drawing.resize(Drawing.getImage("fruit/grapes.png"), 100, 100));
        Engine.getClipboard().addImage("GRAPES2", Drawing.resize(Drawing.getImage("fruit/grapes2.png"), 100, 100));
        Engine.getClipboard().addImage("PINEAPPLE", Drawing.resize(Drawing.getImage("fruit/pineapple.png"), 100, 100));
        Engine.getClipboard().addImage("STRAWBERRY", Drawing.resize(Drawing.getImage("fruit/strawberry.png"), 100, 100));
        
        // TEMP
        Engine.getClipboard().addImage("NULL", Drawing.resize(Drawing.getImage("fruit/temp2.png"), 100, 100));
        
        // Loading Done
        this.loadWait = false;
    }
    
    public void render(Graphics g)
    {
        this.renderTitle(g);
        this.renderBar(g);
    }
    
    private void renderBar(Graphics g)
    {
        // Detail
        g.setColor(Colour.getColour("GREEN_1"));
        g.setFont(Fonts.getFont("TITLE_LOADING"));
        Text.writeShadow(g, this.loadDetail, 683, 360, "CENTER", Colour.getColour("GREEN_3"));
        
        // Progress Bar
        this.loadBar.render(g);
    }
    
    private void renderTitle(Graphics g)
    {
        // Background
        g.setColor(Colour.getColour("BLACK"));
        g.fillRect(0, 0, 1366, 768);
        
        // Logo
        g.drawImage(Drawing.getImage("logo/title2.png"), 333, 140, null);
    }
    
    public void tick()
    {
        if(!this.loadWait) {this.tickLoad();}
    }

    private void tickLoad()
    {
        this.loadTick ++;
        if(this.loadTick >= this.loadTickNext)
        {
            this.loadTick = 0;
            if(this.loadTask == 0)
            {
                this.loadTask = 1;
                this.loadDetail = "PREPARING RESOURCES";
                this.loadBar.setValue(25);
                this.loadWait = true;
                this.loadResources();
            }
            else if(this.loadTask == 1)
            {
                this.loadTask = 2;
                this.loadDetail = "LOCATING SAVE DATA";
                this.loadBar.setValue(50);
                this.loadWait = true;
                this.loadSaveData();
            }
            else if(this.loadTask == 2)
            {
                this.loadTask = 3;
                this.loadDetail = "CONTACTING FRUITDROID SERVER";
                this.loadBar.setValue(75);
                this.loadWait = true;
                this.loadNetwork();
            }
            else if(this.loadTask == 3)
            {
                this.loadTask = 4;
                this.loadDetail = "COMPLETE";
                this.loadBar.setValue(100);
                this.loadTickNext = 30;
            }
            else if(this.loadTask == 4)
            {
                Engine.setState(new StateTitle());
            }
        }
    }
    
}