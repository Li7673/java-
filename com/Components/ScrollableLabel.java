package com.Components;

import javax.swing.*;
import java.awt.*;

public   class ScrollableLabel extends JLabel implements Scrollable {

        public Dimension getPreferredScrollableViewportSize() {
            return getPreferredSize();

        }
        public int getScrollableBlockIncrement(Rectangle r, int orietation, int direction) {
            return 10;

        }

        public boolean getScrollableTracksViewportHeight() {
            return false;

        }

        public boolean getScrollableTracksViewportWidth() {
            return false;

        }

        public int getScrollableUnitIncrement(Rectangle r, int orientation, int direction) {
            return 10;

        }

    }


