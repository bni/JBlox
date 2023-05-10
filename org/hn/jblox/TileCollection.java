/*
 * JBlox, Copyright (C) 2001 Björn Nilsson
 */
package org.hn.jblox;

import java.applet.Applet;
import java.awt.Graphics;
import java.util.Random;

// Handles logic and drawing for a collection of tiles
public class TileCollection
{
    private Applet m_parent;
    private Graphics m_graphics;

    private GraphicsLoader m_graphicsLoader;
    private PlayFieldGrid m_playFieldGrid;

    private int m_type;
    private int m_state;

    private Tile[] m_tileArray;

    // Constructor
    public TileCollection(Applet a, Graphics g, GraphicsLoader gl, PlayFieldGrid pfg)
    {
        // Get the parent applet reference
        this.m_parent = a;

        // Get the offscreen graphics reference
        this.m_graphics = g;

        // Get the graphics loader reference
        this.m_graphicsLoader = gl;

        // Get the Playfield grid reference
        this.m_playFieldGrid = pfg;

        // Create an array of tiles
        this.m_tileArray = new Tile[4];

        this.restart();
    }

    public int getCurrentTileRow(int index)
    {
        return this.m_tileArray[index].getCurrentRow();
    }

    public int getCurrentTileColumn(int index)
    {
        return this.m_tileArray[index].getCurrentColumn();
    }

    public int getTileXCoord(int index)
    {
        return this.m_tileArray[index].getXCoord();
    }

    public int getTileYCoord(int index)
    {
        return this.m_tileArray[index].getYCoord();
    }

    // Resets the tilecollection
    public int restart()
    {
        // Remove completed rows and count up scores accordingly
        this.m_playFieldGrid.removeCompletedRows();
        this.m_playFieldGrid.invalidate();

        try
        {
            Thread.sleep(200);
        }
        catch (InterruptedException e)
        {
        }

        this.m_playFieldGrid.redrawEntireGrid();

        // Set the current tilecollection state
        this.m_state = 0;

        // Generate a random number
        Random random = new Random();
        m_type = Math.abs(random.nextInt())%7;

        int offset = 14*7;

        // Determine the type of tilecollection and initialize the tilearray
        if (m_type == 0)
        {
            this.m_tileArray[0] = new Tile(this.m_parent, this.m_graphics, this.m_graphicsLoader, this.m_playFieldGrid, 0, offset+2, 2);
            this.m_tileArray[1] = new Tile(this.m_parent, this.m_graphics, this.m_graphicsLoader, this.m_playFieldGrid, 0, offset+16, 2);
            this.m_tileArray[2] = new Tile(this.m_parent, this.m_graphics, this.m_graphicsLoader, this.m_playFieldGrid, 0, offset+2, 16);
            this.m_tileArray[3] = new Tile(this.m_parent, this.m_graphics, this.m_graphicsLoader, this.m_playFieldGrid, 0, offset+16, 16);
        }
        else if (m_type == 1)
        {
            this.m_tileArray[0] = new Tile(this.m_parent, this.m_graphics, this.m_graphicsLoader, this.m_playFieldGrid, 1, offset+2, 2);
            this.m_tileArray[1] = new Tile(this.m_parent, this.m_graphics, this.m_graphicsLoader, this.m_playFieldGrid, 1, offset+2, 16);
            this.m_tileArray[2] = new Tile(this.m_parent, this.m_graphics, this.m_graphicsLoader, this.m_playFieldGrid, 1, offset+2, 30);
            this.m_tileArray[3] = new Tile(this.m_parent, this.m_graphics, this.m_graphicsLoader, this.m_playFieldGrid, 1, offset+16, 30);
        }
        else if (m_type == 2)
        {
            this.m_tileArray[0] = new Tile(this.m_parent, this.m_graphics, this.m_graphicsLoader, this.m_playFieldGrid, 2, offset+16, 2);
            this.m_tileArray[1] = new Tile(this.m_parent, this.m_graphics, this.m_graphicsLoader, this.m_playFieldGrid, 2, offset+16, 16);
            this.m_tileArray[2] = new Tile(this.m_parent, this.m_graphics, this.m_graphicsLoader, this.m_playFieldGrid, 2, offset+2, 30);
            this.m_tileArray[3] = new Tile(this.m_parent, this.m_graphics, this.m_graphicsLoader, this.m_playFieldGrid, 2, offset+16, 30);
        }
        else if (m_type == 3)
        {
            this.m_tileArray[0] = new Tile(this.m_parent, this.m_graphics, this.m_graphicsLoader, this.m_playFieldGrid, 3, offset+2, 2);
            this.m_tileArray[1] = new Tile(this.m_parent, this.m_graphics, this.m_graphicsLoader, this.m_playFieldGrid, 3, offset+2, 16);
            this.m_tileArray[2] = new Tile(this.m_parent, this.m_graphics, this.m_graphicsLoader, this.m_playFieldGrid, 3, offset+16, 16);
            this.m_tileArray[3] = new Tile(this.m_parent, this.m_graphics, this.m_graphicsLoader, this.m_playFieldGrid, 3, offset+16, 30);
        }
        else if (m_type == 4)
        {
            this.m_tileArray[0] = new Tile(this.m_parent, this.m_graphics, this.m_graphicsLoader, this.m_playFieldGrid, 4, offset+16, 2);
            this.m_tileArray[1] = new Tile(this.m_parent, this.m_graphics, this.m_graphicsLoader, this.m_playFieldGrid, 4, offset+2, 16);
            this.m_tileArray[2] = new Tile(this.m_parent, this.m_graphics, this.m_graphicsLoader, this.m_playFieldGrid, 4, offset+16, 16);
            this.m_tileArray[3] = new Tile(this.m_parent, this.m_graphics, this.m_graphicsLoader, this.m_playFieldGrid, 4, offset+2, 30);
        }
        else if (m_type == 5)
        {
            this.m_tileArray[0] = new Tile(this.m_parent, this.m_graphics, this.m_graphicsLoader, this.m_playFieldGrid, 5, offset+16, 2);
            this.m_tileArray[1] = new Tile(this.m_parent, this.m_graphics, this.m_graphicsLoader, this.m_playFieldGrid, 5, offset+2, 16);
            this.m_tileArray[2] = new Tile(this.m_parent, this.m_graphics, this.m_graphicsLoader, this.m_playFieldGrid, 5, offset+16, 16);
            this.m_tileArray[3] = new Tile(this.m_parent, this.m_graphics, this.m_graphicsLoader, this.m_playFieldGrid, 5, offset+30, 16);
        }
        else
        {
            this.m_tileArray[0] = new Tile(this.m_parent, this.m_graphics, this.m_graphicsLoader, this.m_playFieldGrid, 6, offset+2, 2);
            this.m_tileArray[1] = new Tile(this.m_parent, this.m_graphics, this.m_graphicsLoader, this.m_playFieldGrid, 6, offset+2, 16);
            this.m_tileArray[2] = new Tile(this.m_parent, this.m_graphics, this.m_graphicsLoader, this.m_playFieldGrid, 6, offset+2, 30);
            this.m_tileArray[3] = new Tile(this.m_parent, this.m_graphics, this.m_graphicsLoader, this.m_playFieldGrid, 6, offset+2, 44);
        }

        // Check if the level is completed
        if (this.m_playFieldGrid.isLevelCompleted())
        {
            if (this.m_playFieldGrid.isGameComplete())
            {
                System.out.println("Tilecollection reporting that the game is complete");
                return UpdateRunner.GAMECOMPLETE;
            }

            System.out.println("Tilecollection reporting goto next level");
            return UpdateRunner.NEXTLEVEL;
        }

        // Check if upmost row is hit
        if (this.m_playFieldGrid.checkForGameOver())
        {
            return UpdateRunner.GAMEOVER;
        }

        return UpdateRunner.RUNNING;
    }

    // Determine the type of tilecollection and state, rotate accordingly
    public void rotate()
    {
        // Is true if clear to commit rotate
        boolean all_clear = true;
        
        // Temporary array to test if we can rotate
        Tile temp[] = new Tile[4];
        
        // Save state if we need to rollback
        int rollback_state = m_state;

        // Initiate temporary array
        for (int i = 0; i < 4; i++)
        {
            temp[i] = new Tile(this.m_playFieldGrid, this.m_tileArray[i].getXCoord(), this.m_tileArray[i].getYCoord());
        }

        // Blue Tilecollection
        if (m_type == 0)
        {
        }
        // Yellow tilecollection
        else if (m_type == 1)
        {
            if (m_state == 0)
            {
                temp[1].up();
                temp[1].right();
                temp[2].up();
                temp[2].up();
                temp[2].right();
                temp[2].right();
                temp[3].up();
                temp[3].left();

                m_state = 1;
            }
            else if (m_state == 1)
            {
                temp[2].down();
                temp[2].left();
                temp[3].down();
                temp[3].right();

                m_state = 2;
            }
            else if (m_state == 2)
            {
                temp[0].right();
                temp[0].right();
                temp[1].down();
                temp[1].left();
                temp[3].up();
                temp[3].right();

                m_state = 3;
            }
            else
            {
                temp[0].left();
                temp[0].left();
                temp[2].down();
                temp[2].left();
                temp[3].down();
                temp[3].left();

                m_state = 0;
            }
        }
        // Purple tilecollection
        else if (m_type == 2)
        {
            if (m_state == 0)
            {
                temp[0].left();
                temp[1].left();
                temp[2].up();
                temp[2].right();
                temp[3].up();
                temp[3].right();
                m_state = 1;
            }
            else if (m_state == 1)
            {
                temp[1].up();
                temp[1].right();
                temp[2].left();
                temp[3].down();
                temp[3].left();
                temp[3].left();
                m_state = 2;
            }
            else if (m_state == 2)
            {
                temp[2].up();
                temp[2].right();
                temp[2].right();
                temp[3].up();
                temp[3].right();
                temp[3].right();
                m_state = 3;
            }
            else
            {
                temp[0].right();
                temp[1].down();
                temp[2].down();
                temp[2].down();
                temp[2].left();
                temp[2].left();
                temp[3].down();
                temp[3].left();
                m_state = 0;
            }
        }
        // Green tilecollection
        else if (m_type == 3)
        {
            if (m_state == 0)
            {
                temp[0].right();
                temp[1].up();
                temp[1].right();
                temp[1].right();
                temp[2].left();
                temp[3].up();
                m_state = 1;
            }
            else
            {
                temp[0].left();
                temp[1].down();
                temp[1].left();
                temp[1].left();
                temp[2].right();
                temp[3].down();
                m_state = 0;
            }
        }
        // Cyan tilecollection
        else if (m_type == 4)
        {
            if (m_state == 0)
            {
                temp[0].left();
                temp[1].up();
                temp[1].right();
                temp[3].up();
                temp[3].right();
                temp[3].right();
                m_state = 1;
            }
            else
            {
                temp[0].right();
                temp[1].down();
                temp[1].left();
                temp[3].down();
                temp[3].left();
                temp[3].left();
                m_state = 0;
            }
        }
        // Gray tilecollection
        else if (m_type == 5)
        {
            if (m_state == 0)
            {
                temp[0].left();
                temp[3].down();
                temp[3].left();
                temp[3].left();
                m_state = 1;
            }
            else if (m_state == 1)
            {
                temp[1].up();
                temp[1].right();
                temp[2].up();
                temp[2].right();
                temp[3].up();
                temp[3].right();
                m_state = 2;
            }
            else if (m_state == 2)
            {
                temp[0].right();
                temp[1].down();
                temp[1].left();
                temp[2].down();
                temp[2].left();
                temp[3].down();
                m_state = 3;
            }
            else
            {
                temp[3].up();
                temp[3].right();
                m_state = 0;
            }
        }
        // Red tilecollection
        else
        {
            if (m_state == 0)
            {
                temp[1].up();
                temp[1].right();
                temp[2].up();
                temp[2].up();
                temp[2].right();
                temp[2].right();
                temp[3].up();
                temp[3].up();
                temp[3].up();
                temp[3].right();
                temp[3].right();
                temp[3].right();
                m_state = 1;
            }
            else
            {
                temp[1].down();
                temp[1].left();
                temp[2].down();
                temp[2].down();
                temp[2].left();
                temp[2].left();
                temp[3].down();
                temp[3].down();
                temp[3].down();
                temp[3].left();
                temp[3].left();
                temp[3].left();
                m_state = 0;
            }
        }

        // Check if all was clear to move
        for (int i = 0; i < 4; i++)
        {
            if (!temp[i].canExistAt(temp[i]))
            {
                all_clear = false;
            }
        }

        // Update coordinates if all was clear, else rollback state change
        if (all_clear)
        {
            for (int i = 0; i < 4; i++)
            {
                this.m_tileArray[i].setCoordinates(temp[i]);
            }
        }
        else
        {
            m_state = rollback_state;
        }

        // Instruct the applet parent to repaint itself
        this.m_parent.repaint();
    }

    // Move the tilecollection to the left
    public void left()
    {
        if (this.canMoveLeft())
        {
            for (int i = 0; i < 4; i++)
            {
                this.m_tileArray[i].left();
            }

            // Instruct the applet parent to repaint itself
            this.m_parent.repaint();
        }
    }

    // Move the tilecollection to the right
    public void right()
    {
        if (this.canMoveRight())
        {
            for (int i = 0; i < 4; i++)
            {
                this.m_tileArray[i].right();
            }

            // Instruct the applet parent to repaint itself
            this.m_parent.repaint();
        }
    }

    // Indicate wether the tilecollection can move left
    private boolean canMoveLeft()
    {
        for (int i = 0; i < 4; i++)
        {
            if (!this.m_tileArray[i].canMoveLeft())
            {
                return false;
            }
        }

        // We are clear
        return true;
    }

    // Indicate wether the tilecollection can move right
    private boolean canMoveRight()
    {
        for (int i = 0; i < 4; i++)
        {
            if (!this.m_tileArray[i].canMoveRight())
            {
                return false;
            }
        }
        
        // We are clear
        return true;
    }

    // Indicate wether the tilecollection can increment
    private boolean canIncrement()
    {
        // Check if all tiles in the array can increment
        for (int i = 0; i < 4; i++)
        {
            if (!this.m_tileArray[i].canIncrement())
            {
                // Give player a short while to "slide" the tilecollection
                try
                {
                    Thread.sleep(150);
                }
                catch (InterruptedException e)
                {
                }

                // Do the same check again after the short while
                for (int x = 0; x < 4; x++)
                {
                    if (!this.m_tileArray[x].canIncrement())
                    {
                        // Set the playfield grid
                        for (int j = 0; j < 4; j++)
                        {
                            this.m_playFieldGrid.setOccupied(this.m_tileArray[j].getCurrentColumn(), this.m_tileArray[j].getCurrentRow(), this.m_tileArray[j].getColor());
                        }

                        return false;
                    }
                }
            }
        }

        // We are clear
        return true;
    }

    // Increment the tilecollection downward, returns false if stop
    public boolean inc()
    {
        // Check if we can increment
        if (this.canIncrement())
        {
            for (int i = 0; i < 4; i++)
            {
                this.m_tileArray[i].inc();
            }

            // Instruct the applet parent to repaint itself
            this.m_parent.repaint();
            
            return true;
        }
        else
        {
            return false;
        }
    }

    // Draw the tilecollection onto the graphics context
    public void put()
    {
        for (int i = 0; i < 4; i++)
        {
            this.m_tileArray[i].put();
        }
    }

    // Remove the tilecollection fully from the graphics context
    public void erase()
    {
        for (int i = 0; i < 4; i++)
        {
            this.m_tileArray[i].erase();
        }
    }
}
