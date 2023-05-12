/*
 * JBlox, Copyright (C) 2001 Björn Nilsson
 */
package org.hn.jblox;

// 212 x 422
// 15x12+16x2=212
// 30x12+31x2=422

import java.applet.Applet;
import java.awt.Graphics;

// Represents the playfield grid
public class PlayFieldGrid
{
    private Applet m_parent;
    private Graphics m_graphics;

    private GraphicsLoader m_graphicsLoader;
    private LevelLoader m_levelLoader;

    // Game properties
    private int m_reachedLevel = 0;
    private int m_completedLines = 0;
    private int m_score = 0;

    // Keep track of score changes for scoreHasChanged method
    private int m_oldScore = 0;

    // Level properties
    private int[][] m_gridArray;
    private String m_currentLevelName = null;
    private int m_currentLevelSpeed = 0;
    private int m_currentLevelThreshold = 0;

    private boolean m_isDirty;

    public PlayFieldGrid(Applet a, Graphics g, GraphicsLoader gl, LevelLoader ll)
    {
        // Get the graphics loader reference
        this.m_graphicsLoader = gl;

        // Get the parent applet reference
        this.m_parent = a;

        // Get the offscreen graphics reference
        this.m_graphics = g;

        this.m_levelLoader = ll;

        this.m_gridArray = new int[15][];

        for (int i = 0; i < 15; i++)
        {
            this.m_gridArray[i] = new int[30];
        }

        m_isDirty = true;

        this.loadLevel();
    }

    private void loadLevel()
    {
        Level temp = null;

        temp = this.m_levelLoader.getLevel(this.m_reachedLevel);

        for (int row = 0; row < 30; row++)
        {
            for (int column = 0; column < 15; column++)
            {
                this.m_gridArray[column][row] = temp.getGridArray()[column][row];
            }
        }
        this.m_currentLevelName = temp.getLevelName();
        this.m_currentLevelSpeed = temp.getLevelSpeed();
        this.m_currentLevelThreshold = temp.getLevelTreshold();
    }

    public boolean isLevelCompleted()
    {
        if (this.m_completedLines >= this.m_currentLevelThreshold)
        {
            return true;
        }

        return false;
    }

    public boolean isGameComplete()
    {
        if (this.m_reachedLevel == this.m_levelLoader.lastLoadedLevel())
        {
            return true;
        }

        return false;
    }

    public boolean checkForGameOver()
    {
        for (int column = 0; column < 15; column++)
        {
            if (this.m_gridArray[column][0] != 7)
            {
                return true;
            }
        }

        return false;
    }

    public void removeCompletedRows()
    {
        boolean full_row = false;
        int this_batch = 0;

        for (int row = 0; row < 30; row++)
        {
            full_row = true;
            for (int column = 0; column < 15; column++)
            {
                if (this.m_gridArray[column][row] == 7)
                {
                    full_row = false;
                }
            }

            if (full_row)
            {
                for (int y = row; y > 0; y--)
                {
                    for (int x = 0; x < 15; x++)
                    {
                        this.m_gridArray[x][y] = this.m_gridArray[x][y-1];
                    }
                }

                this_batch++;
                this.m_completedLines++;
            }
        }
        
        if (this_batch > 0)
        {
            //this.redrawEntireGrid();
            if (this_batch == 1)
            {
                this.m_score += 100;
            }
            else if (this_batch == 2)
            {
                this.m_score += (200*2);
            }
            else if (this_batch == 3)
            {
                this.m_score += (300*3);
            }
            else
            {
                this.m_score += (400*4);
            }
        }
    }

    public boolean isDirty()
    {
        if (this.m_isDirty)
        {
            this.m_isDirty = false;

            return true;
        }
        return false;
    }

    public void invalidate()
    {
        this.m_isDirty = true;
    }

    public void prepareNextLevel()
    {
        this.m_completedLines = 0;
        this.m_reachedLevel++;

        this.loadLevel();
    }

    public void resetWholeGame()
    {
        this.m_reachedLevel = 0;
        this.m_completedLines = 0;
        this.m_score = 0;

        this.loadLevel();
    }

    public void redrawEntireGrid()
    {
        this.m_graphics.clearRect(0, 12, 212, 422);

        for (int row = 0; row < 30; row++)
        {
            for (int column = 0; column < 15; column++)
            {
                if (this.m_gridArray[column][row] != 7)
                {
                    this.m_graphics.drawImage(this.m_graphicsLoader.getMedia(this.m_gridArray[column][row]), 2+column*14, 2+row*14, this.m_parent);
                }
            }
        }

        System.out.println("entire grid redrawn");

        // Instruct the applet parent to repaint itself
        this.m_parent.repaint();
    }

    public String getCurrentLevelName()
    {
        return this.m_currentLevelName;
    }

    public int getCurrentLevelSpeed()
    {
        return this.m_currentLevelSpeed;
    }

    public int getCurrentLevelThreshold()
    {
        return this.m_currentLevelThreshold;
    }

    public int getCompletedLines()
    {
        return this.m_completedLines;
    }

    public int getCurrentScore()
    {
        return this.m_score;
    }

    public boolean isOccupied(int column, int row)
    {
        try
        {
            if (this.m_gridArray[column][row] != 7)
            {
                return true;
            }

            return false;
        }
        catch (ArrayIndexOutOfBoundsException exc)
        {
            return true;
        }
    }

    public void setOccupied(int column, int row, int color)
    {
        this.m_gridArray[column][row] = color;
    }
}
