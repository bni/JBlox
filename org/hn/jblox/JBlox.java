/*
 * JBlox, Copyright (C) 2001 Björn Nilsson
 */
package org.hn.jblox;

import java.applet.Applet;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

// The entry point for the applet
public class JBlox extends Applet
{
    private Image m_offScreenImage;
    private Graphics m_offScreenGraphics;

    private GraphicsLoader m_graphicsLoader;
    private KeyPressDetector m_keyPressDetector;
    private UpdateRunner m_updateRunner;
    private TileCollection m_tileCollection;
    private PlayFieldGrid m_playFieldGrid;
    private MouseClickHandler m_mouseClickHandler;
    private LevelLoader m_levelLoader;

    // Called the first time the applet is loaded in a browser session
    public void init()
    {
        this.setBackground(Color.black);

        this.m_offScreenImage = this.createImage(212, 420);

        // Get the graphics handle from the image object
        this.m_offScreenGraphics = m_offScreenImage.getGraphics();
        this.m_offScreenGraphics.setColor(Color.white);
        this.m_offScreenGraphics.setFont(new Font("Dialog", Font.PLAIN, 8));

        // Construct a graphicsloader
        this.m_graphicsLoader = new GraphicsLoader(this);

        // Construct a levelloader
        this.m_levelLoader = new LevelLoader(this);

        //  Create a playfield grid
        this.m_playFieldGrid = new PlayFieldGrid(this, this.m_offScreenGraphics, this.m_graphicsLoader, this.m_levelLoader);

        // construct a tilecollection
        this.m_tileCollection = new TileCollection(this, this.m_offScreenGraphics, this.m_graphicsLoader, this.m_playFieldGrid);
    }

    // Called each time the applet is reloaded in a browser session
    public void start()
    {
        // Construct a updaterunner
        this.m_updateRunner = new UpdateRunner(this, this.m_offScreenGraphics, this.m_tileCollection, this.m_graphicsLoader, this.m_playFieldGrid);

        // Construct a keypressdetector and make it listen for key events
        this.m_keyPressDetector = new KeyPressDetector(this, this.m_tileCollection, this.m_updateRunner);
        this.addKeyListener(this.m_keyPressDetector);
        
        // Construct an mouselistener that listens for mouse events on this component
        this.m_mouseClickHandler = new MouseClickHandler(this, this.m_updateRunner);
        this.addMouseListener(this.m_mouseClickHandler);

        // start the threadrunner
        this.m_updateRunner.startThread();
    }

    // Called when the applet is unloaded in a browser session
    public void stop()
    {
        // stop the threadrunner
        this.m_updateRunner.stopThread();
    }

    // Called when the applet is instructed to repaint
    public void update(Graphics g)
    {
        if (this.m_updateRunner.getGameState() == UpdateRunner.RUNNING)
        {
            // Erase the tilecollection
            this.m_tileCollection.erase();

            //Draw the tilecollection
            this.m_tileCollection.put();
        }

        // blitt offscreen image to visible graphics object
        g.drawImage(m_offScreenImage, 0, 0, this);
    }

    // Called by the browser when the whole applet surface needs to be painted
    public void paint(Graphics g)
    {
        this.repaint();
    }
}
