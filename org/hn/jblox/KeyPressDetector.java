/*
 * JBlox, Copyright (C) 2001 Björn Nilsson
 */
package org.hn.jblox;

import java.applet.Applet;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

// Detects keypresses and handles logic for keypress events
public class KeyPressDetector extends KeyAdapter
{
    private Applet m_parent;

    private TileCollection m_tileCollection;
    private UpdateRunner m_updateRunner;

    private boolean m_rotateLock;

    // Constructor
    public KeyPressDetector(Applet a, TileCollection tc, UpdateRunner ur)
    {
        // Get the parent applet reference
        this.m_parent = a;

        // Get the tilecollection reference
        this.m_tileCollection = tc;

        // Get the updaterunner reference
        this.m_updateRunner = ur;

        // Rotate lock
        this.m_rotateLock = true;
    }

    // Runs when a key is pressed
    public void keyPressed(KeyEvent e)
    {
        // Get keypress
        int key = e.getKeyCode();

        // The left key was pressed
        if (key == KeyEvent.VK_LEFT)
        {
            // Move the tilecollection to the left
            this.m_tileCollection.left();
        }

        // The right key was pressed
        if (key == KeyEvent.VK_RIGHT)
        {
            // Move the tilecollection to the right
            this.m_tileCollection.right();
        }

        // The up key was pressed
        if (key == KeyEvent.VK_UP)
        {
            // Prevent the player to rotate continiously
            if (this.m_rotateLock)
            {
                // Rotate the tilecollection
                this.m_tileCollection.rotate();
            }

            this.m_rotateLock = false;
        }

        // The down key was pressed
        if (key == KeyEvent.VK_DOWN)
        {
            // Move the tile really fast
            this.m_updateRunner.setReallyFastSpeed();
        }

        // The A key was pressed
        if (key == KeyEvent.VK_A)
        {
            if (this.m_updateRunner.isInDebugMode())
            {
                // Inc the update speed
                this.m_updateRunner.incDebugSpeed();
            }
        }

        // The Z key was pressed
        if (key == KeyEvent.VK_Z)
        {
            if (this.m_updateRunner.isInDebugMode())
            {
                // Dec the update speed
                this.m_updateRunner.decDebugSpeed();
            }
        }

        // The TAB key was pressed
        if (key == KeyEvent.VK_TAB)
        {
            this.m_updateRunner.toggleDebugMode();
            
            this.m_updateRunner.setDefaultSpeed();
        }

        // The SPACE key was pressed
        if (key == KeyEvent.VK_SPACE)
        {
            if (this.m_updateRunner.isInDebugMode())
            {
                this.m_updateRunner.beginNextLevel();
            }
        }
    }

    // Runs when a key is released
    public void keyReleased(KeyEvent e)
    {
        // Get keypress
        int key = e.getKeyCode();

        // The up key was depressed
        if (key == KeyEvent.VK_UP)
        {
            this.m_rotateLock = true;
        }

        // The down key was depressed
        if (key == KeyEvent.VK_DOWN)
        {
            // Move the tile normal speed
            this.m_updateRunner.setDefaultSpeed();
        }
    }    
}
