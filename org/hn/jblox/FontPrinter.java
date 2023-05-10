/*
 * JBlox, Copyright (C) 2001 Björn Nilsson
 */
package org.hn.jblox;

import java.applet.Applet;
import java.awt.Graphics;

// Prints strings using a bitmapped font
public class FontPrinter
{
    private Applet m_parent;
    private Graphics m_graphics;

    private GraphicsLoader m_graphicsLoader;

    private boolean temp = true;
    private int times = 0;

    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public static final int CENTER = 2;

    // Constructor
    public FontPrinter(Applet a, Graphics g, GraphicsLoader gl)
    {
        // Get the parent applet reference
        this.m_parent = a;

        // Get the offscreen graphics reference
        this.m_graphics = g;

        // Get the graphics loader reference
        this.m_graphicsLoader = gl;
    }

    // print text anywhere
    public void print(String s, int x, int y)
    {
        for (int i = 0; i < s.length(); i++)
        {
            this.m_graphics.drawImage(this.m_graphicsLoader.getMedia(this.translate(s.charAt(i))), x+8*i, y, this.m_parent);
        }
    }

    // Convenience method to print left, right or centered text
    public void print(String s, int p)
    {
        int x = 0, y = 3;
        if (p == FontPrinter.LEFT)
        {
            x = 3;
        }
        else if (p == FontPrinter.RIGHT)
        {
            x = 212 - 3 - s.length()*8;
        }
        else
        {
            x = 212/2 - (s.length()*8)/2;
        }

        this.print(s, x, y);
    }

    // Aa0;:,.-+*'\"!?/\\()

    // A=65
    // a=97
    // 0=48
    // ;=59
    // :=58
    // ,=44
    // .=46
    // -=45
    // +=43
    // *=42
    // '=39
    // "=34
    // !=33
    // ?=63
    // /=47
    // \=92
    // (=40
    // )=41
    //  =32

    private int translate(char c)
    {
        if ((int)c == 59)
        {
            return 69;
        }

        if ((int)c == 58)
        {
            return 70;
        }

        if ((int)c == 44)
        {
            return 71;
        }

        if ((int)c == 46)
        {
            return 72;
        }

        if ((int)c == 45)
        {
            return 73;
        }

        if ((int)c == 43)
        {
            return 74;
        }

        if ((int)c == 42)
        {
            return 75;
        }

        if ((int)c == 39)
        {
            return 76;
        }

        if ((int)c == 34)
        {
            return 77;
        }

        if ((int)c == 33)
        {
            return 78;
        }

        if ((int)c == 63)
        {
            return 79;
        }

        if ((int)c == 47)
        {
            return 80;
        }

        if ((int)c == 92)
        {
            return 81;
        }

        if ((int)c == 40)
        {
            return 82;
        }

        if ((int)c == 41)
        {
            return 83;
        }

        if ((int)c == 32)
        {
            return 84;
        }

        if ((int)c >= 97)
        {
            return 33+(int)c-97;
        }

        if ((int)c >= 65)
        {
            return 7+(int)c-65;
        }

        if ((int)c >= 48)
        {
            return 59+(int)c-48;
        }
        
        return 8;
    }
}
