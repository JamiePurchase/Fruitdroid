package game;

import app.Engine;
import gfx.Colour;
import gfx.Drawing;
import gfx.Fonts;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import maths.Maths;

public class Grid
{
    private final int countX = 13;
    private final int countY = 7;
    private final int offsetX = 33;
    private final int offsetY = 54;
    private Fruit[][] fruit;
    private boolean selectCursor;
    private boolean selectActive;
    private GridNode selectNode;
    private ArrayList<GridNode> actionFruit;
    private ArrayList<Fruit> connectFruit;
    
    public Grid()
    {
        this.fruit = new Fruit[this.countX][this.countY];
        this.initFruit();
        this.selectCursor = true;
        this.selectActive = false;
        this.selectNode = null;
        //this.actionFruitClear();
        this.connectFruit = new ArrayList();
    }
    
    public void connectAdd(Fruit fruit)
    {
        this.connectFruit.add(fruit);
    }
    
    public void connectDone()
    {
        System.out.println("CONNECT SEARCH DONE: found " + this.connectFruit.size() + " connected fruit");
        if(this.connectFruit.size() >= 3)
        {
            System.out.println("Connection successful");
            for(int x = 0; x < this.connectFruit.size(); x++)
            {
                this.connectFruit.get(x).activate();
            }
        }
        else
        {
            // NOTE: need to mark all fruit as not connected
        }
        this.connectClear();
        this.selectCursor = true;
    }
    
    public void connectClear()
    {
        for(int x = 0; x < this.connectFruit.size(); x++)
        {
            this.connectFruit.get(x).setConnected(false);
        }
    }
    
    public void connectNew()
    {
        this.connectFruit = new ArrayList();
    }
    
    public int getCountX()
    {
        return this.countX;
    }
    
    public int getCountY()
    {
        return this.countY;
    }
    
    public Fruit getFruitAt(GridNode node)
    {
        return this.getFruitAt(node.getPosX(), node.getPosY());
    }
    
    public Fruit getFruitAt(int x, int y)
    {
        return this.fruit[x][y];
    }
    
    private FruitType getFruitRandom(ArrayList<Fruit> avoid)
    {
        int random = Maths.randomInt(0, 7);
        FruitType fruit = this.getFruitType(random);
        while(avoid.contains(fruit))
        {
            random ++;
            fruit = this.getFruitType(random);
        }
        return fruit;
    }
    
    private FruitType getFruitType(int id)
    {
        FruitType fruit = FruitType.APPLE;
        if(id == 1) {fruit = FruitType.APPLE2;}
        if(id == 2) {fruit = FruitType.BANANA;}
        if(id == 3) {fruit = FruitType.CHERRIES;}
        if(id == 4) {fruit = FruitType.GRAPES;}
        if(id == 5) {fruit = FruitType.GRAPES2;}
        if(id == 6) {fruit = FruitType.PINEAPPLE;}
        if(id == 7) {fruit = FruitType.STRAWBERRY;}
        return fruit;
    }
    
    public int getOffsetX()
    {
        return this.offsetX;
    }
    
    public int getOffsetY()
    {
        return this.offsetY;
    }
    
    public void initFruit()
    {        
        for(int x = 0; x < this.countX; x++)
        {
            for(int y = 0; y < this.countY; y++)
            {
                ArrayList<Fruit> avoid = new ArrayList();
                if(x > 0) {avoid.add(this.getFruitAt(x - 1, y));}
                if(y > 0) {avoid.add(this.getFruitAt(x, y - 1));}
                this.fruit[x][y] = new Fruit(this, new GridNode(this, x, y), getFruitRandom(avoid));
            }
        }
    }
    
    public void inputClick(MouseEvent e)
    {        
        for(int x = 0; x < this.countX; x++)
        {
            for(int y = 0; y < this.countY; y++)
            {
                if(new GridNode(this, x, y).getHover(e.getPoint()))
                {
                    if(this.selectCursor) {this.inputClickFruit(x, y);}
                    return;
                }
            }
        }
    }
    
    private void inputClickFruit(int x, int y)
    {
        // Nothing is selected
        if(!this.selectActive)
        {
            this.selectActive = true;
            this.selectNode = new GridNode(this, x, y);
        }
        
        // Selection already exists
        else   
        {
            // Clicked on selected fruit
            if(this.selectNode.isAt(x, y))
            {
                this.selectActive = false;
            }
            
            // Clicked on another fruit
            else
            {
                // Clicked on adjacent fruit
                if(this.selectNode.isAdjacent(x, y))
                {
                    // Swap Fruit
                    this.getFruitAt(this.selectNode).swap(new GridNode(this, x, y));
                    
                    // Clear Selection
                    this.selectActive = false;
                    this.selectCursor = false;
                    
                    // Connect Search
                    System.out.println("CONNECT SEARCH STARTING (" + this.getFruitAt(this.selectNode).getType().toString() + ")");
                    this.connectNew();
                    this.getFruitAt(this.selectNode).connect();
                    this.connectDone();
                    
                    // Connect Search again??
                    this.connectNew();
                    this.getFruitAt(new GridNode(this, x, y)).connect();
                    this.connectDone();
                }
            }
        }
    }
    
    public void render(Graphics g)
    {
        this.renderFruit(g);
    }
    
    private void renderFruit(Graphics g)
    {        
        for(int x = 0; x < this.countX; x++)
        {
            for(int y = 0; y < this.countY; y++)
            {
                boolean select = false;
                if(this.selectActive && this.selectNode.isAt(x, y)) {select = true;}
                this.fruit[x][y].render(g, this.selectCursor, select);
            }
        }
    }
    
    private void setFruitAt(GridNode node, Fruit fruit)
    {
        this.setFruitAt(node.getPosX(), node.getPosY(), fruit);
    }
    
    private void setFruitAt(int x, int y, Fruit fruit)
    {
        this.fruit[x][y] = fruit;
    }

}