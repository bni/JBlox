/*
 * JBlox, Copyright (C) 2001 Björn Nilsson
 */
package org.hn.jblox;

import java.applet.Applet;
import java.awt.Graphics;

// Handles logic and drawing for a single tile
public class Tile
{
    private Applet m_parent;
    private Graphics m_graphics;

    private GraphicsLoader m_graphicsLoader;
    private PlayFieldGrid m_playFieldGrid;

    private int m_xCoordNew;
    private int m_xCoordOld;
    private int m_yCoordNew;
    private int m_yCoordOld;

    private int m_Color;

    // Constructor
    public Tile(Applet a, Graphics g, GraphicsLoader gl, PlayFieldGrid pfg, int col, int x, int y)
    {
        // Get the parent applet reference
        this.m_parent = a;

        // Get the graphics handle from the image object
        this.m_graphics = g;

        // Get the graphics loader reference
        this.m_graphicsLoader = gl;

        // Get the Playfield grid reference
        this.m_playFieldGrid = pfg;

        // Initialize coordinates
        this.m_xCoordOld = x;
        this.m_xCoordNew = m_xCoordOld;
        this.m_yCoordOld = y;
        this.m_yCoordNew = m_yCoordOld;

        // Get what color this tile shall have
        this.m_Color = col;
    }

    // Simple constructor
    public Tile(PlayFieldGrid pfg, int x, int y)
    {
        // Get the Playfield grid reference
        this.m_playFieldGrid = pfg;

        // Initialize coordinates
        this.m_xCoordOld = x;
        this.m_xCoordNew = m_xCoordOld;
        this.m_yCoordOld = y;
        this.m_yCoordNew = m_yCoordOld;
    }

    public void setCoordinates(Tile tile)
    {
        this.m_xCoordNew = tile.getXCoord();
        this.m_yCoordNew = tile.getYCoord();
    }

    public int getXCoord()
    {
        return this.m_xCoordNew;
    }

    public int getYCoord()
    {
        return this.m_yCoordNew;
    }

    public int getColor()
    {
        return this.m_Color;
    }

    // Move the tile y-coordinate up
    public void up()
    {
        this.m_yCoordNew -= 14;
    }

    // Move the tile y-coordinate down
    public void down()
    {
        this.m_yCoordNew += 14;
    }

    // Move the tile x-coordinate to the left
    public void left()
    {
        this.m_xCoordNew -= 14;
    }

    // Move the tile x-coordinate to the right
    public void right()
    {
        this.m_xCoordNew += 14;
    }

    // Increment the tile y-coordinate by one
    public void inc()
    {
        //this.m_yCoordNew += 14;
        this.m_yCoordNew++;
    }

    // Draw the tile onto the graphics context
    public void put()
    {
        this.m_graphics.drawImage(this.m_graphicsLoader.getMedia(this.m_Color), this.m_xCoordNew, this.m_yCoordNew, this.m_parent);
    }

    // Remove the tile fully from the graphics context
    public void erase()
    {
        this.m_graphics.clearRect(this.m_xCoordOld, this.m_yCoordOld, 12, 12);

        // Update the old coordinates
        this.m_xCoordOld = this.m_xCoordNew;
        this.m_yCoordOld = this.m_yCoordNew;
    }

    // Indicate wether the tile can move left
    public boolean canMoveLeft()
    {
        if (!this.m_playFieldGrid.isOccupied(this.getCurrentColumn()-1, this.getCurrentRow()))
        {
            return true;
        }

        return false;
    }

    // Indicate wether the tile can move right
    public boolean canMoveRight()
    {
        if (!this.m_playFieldGrid.isOccupied(this.getCurrentColumn()+1, this.getCurrentRow()))
        {
            return true;
        }

        return false;
    }

    // Indicate wether the tile can increment
    public boolean canIncrement()
    {
        if (!this.m_playFieldGrid.isOccupied(this.getCurrentColumn(), this.getCurrentRow()+1))
        {
            return true;
        }

        return false;
    }

    // Indicate wether the tile can rotate to position indicated by tile
    public boolean canExistAt(Tile tile)
    {
        if (!this.m_playFieldGrid.isOccupied(tile.getCurrentColumn(), tile.getCurrentRow()))
        {
            return true;
        }

        return false;
    }
    
    public int getCurrentRow()
    {
        int row = 0;
        for (int i = 1; i < 30; i++)
        {
            if (this.getYCoord() < 2+(2+12)*i)
            {
                break;
            }
            row++;
        }
        return row;
    }

    public int getCurrentColumn()
    {
        int column = 0;
        for (int i = 1; i < 30; i++)
        {
            if (this.getXCoord() < 2+(2+12)*i)
            {
                break;
            }
            column++;
        }
        return column;
    }
}
