/*
 * JBlox, Copyright (C) 2001 Björn Nilsson
 */
package org.hn.jblox;

import java.applet.Applet;

import java.util.Vector;

import java.net.URL;
import java.net.MalformedURLException;
//import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

public class LevelLoader
{
    private Applet m_parent;

    private Vector m_allLoadedLevels;

    public LevelLoader(Applet a)
    {
        // Get the parent applet reference
        this.m_parent = a;

        this.m_allLoadedLevels = new Vector();

        // Create the URL object
        URL url = null;
        try
        {
            url = new URL(this.m_parent.getCodeBase() + "jblox.init");
        }
        catch (MalformedURLException e)
        {
            System.out.println("fel URL");
        }

        // Connect to the URL
        /*InputStream in = null;
        try
        {
            in = url.openConnection().getInputStream();
        }
        catch (IOException e)
        {
            System.out.println("io fel");
        }*/

        BufferedReader data = null;
        try
        {
            data = new BufferedReader(new InputStreamReader(url.openStream()));
        }
        catch (IOException e)
        {
            System.out.println("io fel");
        }

        try
        {
            String temp = "";
            while ((temp = data.readLine()) != null)
            {
                String level_name = temp;

                temp = data.readLine();
                int level_speed = Integer.parseInt(temp);

                temp = data.readLine();
                int level_threshold = Integer.parseInt(temp);

                System.out.println(level_name);
                System.out.println(level_speed);
                System.out.println(level_threshold);

                int[][] grid_array;
                grid_array = new int[15][];

                for (int i = 0; i < 15; i++)
                {
                    grid_array[i] = new int[30];
                }

                for (int row = 0; row < 30; row++)
                {
                    temp = data.readLine();
                    int column = 0;
                    for(int i = 0; i < temp.length(); i++)
                    {
                        grid_array[column][row] = Integer.parseInt(temp.substring(i, i+1));
                        column++;
                    }
                }
                data.readLine();

                // ****
                for (int row = 0; row < 30; row++)
                {
                    for (int column = 0; column < 15; column++)
                    {
                        System.out.print(grid_array[column][row]);
                    }
                    System.out.println("");
                }
                System.out.println("");
                // ****

                Level level = new Level(grid_array, level_name, level_speed, level_threshold);

                m_allLoadedLevels.addElement(level);
            }
        }
        catch (IOException e)
        {
        }



/*            int c = 0;
        try
        {
            String temp = "";
            while ((c = in.read()) != -1)
            {
                temp += (char)c;
                while ((c = in.read()) != 13)
                {
                    temp += (char)c;
                }
                String level_name = temp;
                in.read();

                temp = "";
                while ((c = in.read()) != 13)
                {
                    temp += (char)c;
                }
                int level_speed = Integer.parseInt(temp);
                in.read();

                temp = "";
                while ((c = in.read()) != 13)
                {
                    temp += (char)c;
                }
                int level_threshold = Integer.parseInt(temp);
                in.read();

                System.out.println(level_name);
                System.out.println(level_speed);
                System.out.println(level_threshold);

                int[][] grid_array;
                grid_array = new int[15][];

                for (int i = 0; i < 15; i++)
                {
                    grid_array[i] = new int[30];
                }

                temp = "";
                for (int row = 0; row < 30; row++)
                {
                    int column = 0;
                    while ((c = in.read()) != 13)
                    {
                        temp += (char)c;
                        grid_array[column][row] = Integer.parseInt(temp);
                        column++;
                        temp = "";
                    }
                    in.read();
                }
                in.read();
                in.read();
                temp = "";

                // ****
                for (int row = 0; row < 30; row++)
                {
                    for (int column = 0; column < 15; column++)
                    {
                        System.out.print(grid_array[column][row]);
                    }
                    System.out.println("");
                }
                System.out.println("");
                // ****

                Level level = new Level(grid_array, level_name, level_speed, level_threshold);

                m_allLoadedLevels.addElement(level);
            }
        }
        catch (IOException e)
        {
        }*/
    }

    public Level getLevel(int index)
    {
        return (Level)m_allLoadedLevels.elementAt(index);
    }

    public int lastLoadedLevel()
    {
        return m_allLoadedLevels.size()-1;
    }
}
