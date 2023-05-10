/*
 * JBlox, Copyright (C) 2001 Björn Nilsson
 */
package org.hn.jblox;

public class Level
{
    private int[][] m_gridArray;

    private String m_levelName = null;
    private int m_levelSpeed = 0;
    private int m_levelTreshold = 0;

    public Level(int[][] ga, String ln, int ls, int lt)
    {
        this.m_gridArray = ga;

        this.m_levelName = ln;
        this.m_levelSpeed = ls;
        this.m_levelTreshold = lt;
    }

    public int[][] getGridArray()
    {
        return this.m_gridArray;
    }

    public String getLevelName()
    {
        return this.m_levelName;
    }

    public int getLevelSpeed()
    {
        return this.m_levelSpeed;
    }

    public int getLevelTreshold()
    {
        return this.m_levelTreshold;
    }
}
