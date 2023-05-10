/*
 * JBlox, Copyright (C) 2001 Björn Nilsson
 */
package org.hn.jblox;

import java.applet.Applet;
import java.awt.Graphics;

// Handels the upper scoreboard
public class ScoreBoard
{
    private Applet m_parent;
    private Graphics m_graphics;

    private UpdateRunner m_updateRunner;
    private GraphicsLoader m_graphicsLoader;
    private PlayFieldGrid m_playFieldGrid;
    private FontPrinter m_fontPrinter;

    private int m_displayLevelString = 300;

    private boolean m_isDirty;

    public ScoreBoard (Applet a, Graphics g, UpdateRunner ur, GraphicsLoader gl, PlayFieldGrid pfg)
    {
        // Get the parent applet reference
        this.m_parent = a;

        // Get the offscreen graphics reference
        this.m_graphics = g;

        // Get the updaterunner reference
        this.m_updateRunner = ur;

        // Get the graphics loader reference
        this.m_graphicsLoader = gl;

        // Get the Playfield grid reference
        this.m_playFieldGrid = pfg;

        // Construct a fontprinter
        this.m_fontPrinter = new FontPrinter(this.m_parent, this.m_graphics, this.m_graphicsLoader);

        m_isDirty = true;
    }

    public void startTiming()
    {
        this.m_displayLevelString = 0;
    }

    public void invalidate()
    {
        m_isDirty = true;
    }

    public void update()
    {
        // Check if score and completedlines have changed has changed
        if (this.m_playFieldGrid.isDirty())
        {
            this.invalidate();
        }

        if (this.m_displayLevelString < 300)
        {
            this.m_displayLevelString++;
            if (this.m_displayLevelString == 300)
            {
                this.invalidate();
            }
        }

        if (this.m_isDirty)
        {
            System.out.println("ritar om scoreboard");

            this.m_graphics.clearRect(0, 0, 212, 11);

            if (this.m_updateRunner.getGameState() == UpdateRunner.RUNNING)
            {
                if (this.m_displayLevelString < 300)
                {
                    // Draw string
                    this.m_fontPrinter.print(this.m_playFieldGrid.getCurrentLevelName(), FontPrinter.LEFT);
                }
                else
                {
                    // Draw score and lines
                    this.m_fontPrinter.print("Score:" + this.m_playFieldGrid.getCurrentScore(), FontPrinter.LEFT);
                    this.m_fontPrinter.print("Lines:" + this.m_playFieldGrid.getCompletedLines() + "/" + this.m_playFieldGrid.getCurrentLevelThreshold(), FontPrinter.RIGHT);
                }
            }
            else if (this.m_updateRunner.getGameState() == UpdateRunner.NOGAMESYET)
            {
                // Draw string
                this.m_fontPrinter.print("Click Here to Start", FontPrinter.CENTER);
            }
            else if (this.m_updateRunner.getGameState() == UpdateRunner.PAUSED)
            {
                // Draw string
                this.m_fontPrinter.print("Game Paused", FontPrinter.CENTER);
            }
            else if (this.m_updateRunner.getGameState() == UpdateRunner.GAMECOMPLETE)
            {
                // Draw string
                this.m_fontPrinter.print("Well Done!", FontPrinter.CENTER);
            }
            else
            {
                // Draw string
                this.m_fontPrinter.print("Game Over", FontPrinter.CENTER);
            }

            m_isDirty = false;

            // Instruct the applet parent to repaint itself
            this.m_parent.repaint();
        }
    }
}
