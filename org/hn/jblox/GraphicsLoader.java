/*
 * JBlox, Copyright (C) 2001 Björn Nilsson
 */
package org.hn.jblox;

import java.applet.Applet;
import java.awt.MediaTracker;
import java.awt.Image;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;

import java.awt.Toolkit; 
import java.io.InputStream; 

// Loads the graphics and gives references to them
public class GraphicsLoader
{
    private Applet m_parent;
    private MediaTracker m_mediaTracker;
    private Image[] m_mediaArray;

    // Constructor
    public GraphicsLoader(Applet a)
    {
        // Get the parent applet reference
        this.m_parent = a;

        // Create an array of images 7 + 78
        this.m_mediaArray = new Image[85];

        // Try to initialize mediatracking
        try
        {
            this.m_mediaTracker = new MediaTracker(this.m_parent);
        }
        catch (Exception exc)
        {
            System.err.println("Error initializing mediatracking: " + exc.toString());
        }

        InputStream in = null; 
        byte buffer[] = null; 

        // Try to load the graphics
        try
        {
            in = getClass().getResourceAsStream("/org/hn/jblox/img/blue.gif"); 
            buffer = new byte[in.available()]; 
            in.read(buffer); 
            this.m_mediaArray[0] = Toolkit.getDefaultToolkit().createImage(buffer); 
            //this.m_mediaArray[0] = m_parent.getImage(m_parent.getDocumentBase(), "org/hn/jblox/img/blue.gif");
            this.m_mediaTracker.addImage(m_mediaArray[0], 0);

            in = getClass().getResourceAsStream("/org/hn/jblox/img/yellow.gif"); 
            buffer = new byte[in.available()]; 
            in.read(buffer); 
            this.m_mediaArray[1] = Toolkit.getDefaultToolkit().createImage(buffer); 
            //this.m_mediaArray[1] = m_parent.getImage(m_parent.getDocumentBase(), "org/hn/jblox/img/yellow.gif");
            this.m_mediaTracker.addImage(m_mediaArray[1], 1);

            in = getClass().getResourceAsStream("/org/hn/jblox/img/purple.gif"); 
            buffer = new byte[in.available()]; 
            in.read(buffer); 
            this.m_mediaArray[2] = Toolkit.getDefaultToolkit().createImage(buffer); 
            //this.m_mediaArray[2] = m_parent.getImage(m_parent.getDocumentBase(), "org/hn/jblox/img/purple.gif");
            this.m_mediaTracker.addImage(m_mediaArray[2], 2);

            in = getClass().getResourceAsStream("/org/hn/jblox/img/green.gif"); 
            buffer = new byte[in.available()]; 
            in.read(buffer); 
            this.m_mediaArray[3] = Toolkit.getDefaultToolkit().createImage(buffer); 
            //this.m_mediaArray[3] = m_parent.getImage(m_parent.getDocumentBase(), "org/hn/jblox/img/green.gif");
            this.m_mediaTracker.addImage(m_mediaArray[3], 3);

            in = getClass().getResourceAsStream("/org/hn/jblox/img/cyan.gif"); 
            buffer = new byte[in.available()]; 
            in.read(buffer); 
            this.m_mediaArray[4] = Toolkit.getDefaultToolkit().createImage(buffer); 
            //this.m_mediaArray[4] = m_parent.getImage(m_parent.getDocumentBase(), "org/hn/jblox/img/cyan.gif");
            this.m_mediaTracker.addImage(m_mediaArray[4], 4);

            in = getClass().getResourceAsStream("/org/hn/jblox/img/gray.gif"); 
            buffer = new byte[in.available()]; 
            in.read(buffer); 
            this.m_mediaArray[5] = Toolkit.getDefaultToolkit().createImage(buffer); 
            //this.m_mediaArray[5] = m_parent.getImage(m_parent.getDocumentBase(), "org/hn/jblox/img/gray.gif");
            this.m_mediaTracker.addImage(m_mediaArray[5], 5);

            in = getClass().getResourceAsStream("/org/hn/jblox/img/red.gif"); 
            buffer = new byte[in.available()]; 
            in.read(buffer); 
            this.m_mediaArray[6] = Toolkit.getDefaultToolkit().createImage(buffer); 
            //this.m_mediaArray[6] = m_parent.getImage(m_parent.getDocumentBase(), "org/hn/jblox/img/red.gif");
            this.m_mediaTracker.addImage(m_mediaArray[6], 6);

            MediaTracker temp_mediaTracker = new MediaTracker(this.m_parent);

            in = getClass().getResourceAsStream("/org/hn/jblox/img/font.gif"); 
            buffer = new byte[in.available()]; 
            in.read(buffer); 
            Image temp_Image = Toolkit.getDefaultToolkit().createImage(buffer); 
            //Image temp_Image = m_parent.getImage(ClassLoader.getSystemResource("org/hn/jblox/img/font.gif"));
            temp_mediaTracker.addImage(temp_Image, 0);

            try
            {
                temp_mediaTracker.waitForID(0);
            }
            catch(InterruptedException e)
            {
            }

            int x = 0;
            for (int i = 7; i < 85; i++)
            {
                this.m_mediaArray[i] = this.m_parent.createImage(new FilteredImageSource(temp_Image.getSource(), new CropImageFilter(8*x,0,8,8)));
                this.m_mediaTracker.addImage(this.m_mediaArray[i], i);
                x++;
            }
        }
        catch (Exception exc)
        {
            System.err.println("Error loading graphics: " + exc.toString());
        }

        // Wait for all the images to finish loading
        for (int i = 0; i < 85; i++)
        {
            try
            {
                this.m_mediaTracker.waitForID(i);
            }
            catch (InterruptedException iexc)
            {
                System.err.println("The mediatracking was interrupted: " + iexc.toString());
            }
        }
    }

    // returns requested media as indicated by index
    public Image getMedia(int index)
    {
        // try to return media
        try
        {
            // Return the requested image
            return this.m_mediaArray[index];
        }
        catch (ArrayIndexOutOfBoundsException aexc)
        {
            System.err.println("You requested an image that was not loaded: " + aexc.toString());
        }

        // Return the last image if index out of bounds
        return this.m_mediaArray[84];
    }
}
