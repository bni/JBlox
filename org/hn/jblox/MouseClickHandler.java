/*
 * JBlox, Copyright (C) 2001 Björn Nilsson
 */
package org.hn.jblox;

import java.applet.Applet;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseClickHandler extends MouseAdapter
{
    private Applet m_parent;
    private UpdateRunner m_updateRunner;

    public MouseClickHandler(Applet a, UpdateRunner ur)
    {
        // Get the parent applet reference
        this.m_parent = a;

        // Get the updaterunner reference
        this.m_updateRunner = ur;
    }

    public void mousePressed(MouseEvent e)
    {
        if (e.getSource() == this.m_parent)
        {
            System.out.println("mouse clicked!");

            if (this.m_updateRunner.getGameState() == this.m_updateRunner.RUNNING)
            {
                this.m_updateRunner.pauseRunningGame();
            }
            else if (this.m_updateRunner.getGameState() == this.m_updateRunner.PAUSED)
            {
                this.m_updateRunner.resumeRunningGame();
            }
            else
            {
                this.m_updateRunner.startNewGame();
            }
        }
    }
}
