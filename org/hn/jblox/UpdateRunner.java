/*
 * JBlox, Copyright (C) 2001 Björn Nilsson
 */
package org.hn.jblox;

import java.applet.Applet;
import java.awt.Graphics;

// Runs the main game thread
public class UpdateRunner extends Thread
{
    private Applet m_parent;
    private Graphics m_graphics;

    private TileCollection m_tileCollection;
    private GraphicsLoader m_graphicsLoader;
    private PlayFieldGrid m_playFieldGrid;
    private ScoreBoard m_scoreBoard;

    private boolean m_isRunning = false;

    public static final int NOGAMESYET = 0;
    public static final int RUNNING = 1;
    public static final int NEXTLEVEL = 2;
    public static final int PAUSED = 3;
    public static final int GAMECOMPLETE = 4;
    public static final int GAMEOVER = 5;

    private int m_gameState = UpdateRunner.NOGAMESYET;

    private int m_interval = 0;
    private int m_debug_interval = 0;
    private boolean m_debug_mode = false;

    // Constructor
    public UpdateRunner(Applet a, Graphics g, TileCollection tc, GraphicsLoader gl, PlayFieldGrid pfg)
    {
        // Get the parent applet reference
        this.m_parent = a;

        // Get the offscreen graphics reference
        this.m_graphics = g;

        this.m_tileCollection = tc;

        // Get the graphics loader reference
        this.m_graphicsLoader = gl;

        // Get the Playfield grid reference
        this.m_playFieldGrid = pfg;

        // Construct the scoreboard
        this.m_scoreBoard = new ScoreBoard(this.m_parent, this.m_graphics, this, this.m_graphicsLoader, this.m_playFieldGrid);
        
        this.m_interval = 10;
        this.m_debug_interval = 25;
    }

    // Starts the thread
    public void startThread()
    {
        // Start this thread
        this.start();
        this.m_isRunning = true;
    }

    // Stops the thread
    public void stopThread()
    {
        this.m_isRunning = false;
    }

    // Starts a new game
    public void startNewGame()
    {
        this.m_playFieldGrid.resetWholeGame();
        this.m_playFieldGrid.redrawEntireGrid();
        this.setDefaultSpeed();

        this.m_gameState = UpdateRunner.RUNNING;
        this.m_scoreBoard.invalidate();
        this.m_scoreBoard.startTiming();
    }

    // Begin next level
    public void beginNextLevel()
    {
        this.m_playFieldGrid.prepareNextLevel();
        this.m_playFieldGrid.redrawEntireGrid();
        this.setDefaultSpeed();

        this.m_gameState = UpdateRunner.RUNNING;
        this.m_scoreBoard.invalidate();
        this.m_scoreBoard.startTiming();
    }
    
    // Ask the user for his name
    public void askUserForName()
    {
        // Not implemented yet
    }

    // Pauses a running game
    public void pauseRunningGame()
    {
        this.m_gameState = UpdateRunner.PAUSED;
        this.m_scoreBoard.invalidate();
    }

    // Resumes a running game
    public void resumeRunningGame()
    {
        this.m_gameState = UpdateRunner.RUNNING;
        this.m_scoreBoard.invalidate();
        this.m_scoreBoard.startTiming();
    }

    // Stops a running game
    public void stopGameOver()
    {
        this.m_gameState = UpdateRunner.GAMEOVER;
        this.m_scoreBoard.invalidate();
    }

    // Stops a running game
    public void stopGameComplete()
    {
        this.m_gameState = UpdateRunner.GAMECOMPLETE;
        this.m_scoreBoard.invalidate();
    }

    public int getGameState()
    {
        return this.m_gameState;
    }

    // Increase update interval
    public void incDebugSpeed()
    {
        if (this.m_debug_interval > 0)
        {
            this.m_debug_interval--;
        }

        this.setDefaultSpeed();
    }

    // Increase update interval
    public void decDebugSpeed()
    {
        if (this.m_debug_interval < 50)
        {
            this.m_debug_interval++;
        }

        this.setDefaultSpeed();
    }

    // set update interval
    public void setReallyFastSpeed()
    {
        this.m_interval = 4;
    }

    // set update interval
    public void setDefaultSpeed()
    {
        if (this.m_debug_mode)
        {
            this.m_interval = this.m_debug_interval;
        }
        else
        {
            this.m_interval = this.m_playFieldGrid.getCurrentLevelSpeed();
        }
    }

    // Get the update speed
    public int getUpdateSpeed()
    {
        return m_interval;
    }

    // Toggles debug mode
    public void toggleDebugMode()
    {
        if (this.m_debug_mode)
        {
            this.m_debug_mode = false;
        }
        else
        {
            this.m_debug_mode = true;
        }
    }

    // Gives debug state
    public boolean isInDebugMode()
    {
        return this.m_debug_mode;
    }

    // Runs the thread
    public void run()
    {
        while (this.m_isRunning)
        {
            this.m_scoreBoard.update();

            if (this.getGameState() == UpdateRunner.RUNNING)
            {
                // Increment the tile collection, restart if hit signaled                    
                if (!this.m_tileCollection.inc())
                {
                    // restart the tilecollection, get result
                    int result = this.m_tileCollection.restart();

                    if (result == UpdateRunner.GAMEOVER)
                    {
                        // Game over, Stop the game in progress
                        this.stopGameOver();

                        // Ask the user for his name
                        this.askUserForName();
                    }
                    else if (result == UpdateRunner.GAMECOMPLETE)
                    {
                        // Game over, Stop the game in progress
                        this.stopGameComplete();

                        // Ask the user for his name
                        this.askUserForName();
                    }
                    else if (result == UpdateRunner.NEXTLEVEL)
                    {
                        // Begin the next level
                        this.beginNextLevel();
                    }
                    else
                    {
                        // Do nothing
                    }
                }
            }

            try
            {
                Thread.sleep(this.m_interval);
            }
            catch (InterruptedException e)
            {
            }
        }
    }
}
