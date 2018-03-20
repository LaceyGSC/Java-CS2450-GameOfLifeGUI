package golguiltaylor;

import java.awt.Color;
import javax.swing.JButton;

/**
 *
 * @author Lacey Taylor
 */
public class GameOfLife
{

    protected JButton[][] arra;
    private String[][] fix;
    private int counter, count, gen, row, col;

    public void starting()
    {
        gen = 0;
        fix = new String[counter][counter];
        int count1 = 0;
        int count2 = 0;

        while (count1 < (counter))
        {

            while (count2 < (counter))
            {
                fix[count1][count2] = " ";
                count2++;
            }
            count2 = 0;
            count1++;

        }

    }

    public void initialize()
    {
        starting();

        for (int y = 0; y < counter; y++)
        {
            for (int x = 0; x < counter; x++)
            {
                if (arra[x][y].getBackground().equals(Color.white))
                {
                    fix[x][y] = "@";
                }
            }

        }
    }

    private int count(int x, int y)
    {

        //Tried to make an extra "frame", but how I have it set up just didn't mesh

        count = 0;

        x = col;
        y = row;

        //set if statements to alternate 9 -> 0 to ignore edges of board.

        if (x != 0)
        {
            if (fix[x - 1][y].contains("@"))
            {
                count++;
            }
        }
        if (y != 0)
        {
            if (fix[x][y - 1].contains("@"))
            {
                count++;
            }
        }
        if (x != 0 && y != 0)
        {

            if (fix[x - 1][y - 1].contains("@"))
            {
                count++;
            }
        }
        if (x != counter - 1)
        {

            if (fix[x + 1][y].contains("@"))
            {
                count++;
            }
        }
        if (y != counter - 1)
        {
            if (fix[x][y + 1].contains("@"))
            {
                count++;
            }
        }
        if (x != counter - 1 && y != counter - 1)
        {
            if (fix[x + 1][y + 1].contains("@"))
            {
                count++;
            }
        }
        if (x != 0 && y != counter - 1)
        {
            if (fix[x - 1][y + 1].contains("@"))
            {
                count++;
            }
        }
        if (x != counter - 1 && y != 0)
        {
            if (fix[x + 1][y - 1].contains("@"))
            {
                count++;
            }
        }
        return count;
    }

    public void nextGeneration()
    {
        initialize();

        col = 0;
        row = 0;
        String neigh[][] = new String[counter][counter]; //Stores the number of neighbors an arra has

        while (col < (counter))
        {
            while (row < (counter))
            {
                neigh[col][row] = count(col, row) + "";
                row++;
            }
            row = 0;
            col++;
        }

        col = 0;
        row = 0;

        while (col < (counter))
        {
            while (row < (counter))
            {
                switch (neigh[col][row])
                {
                    case "0":
                    case "1":
                        fix[col][row] = " ";
                        break;
                    case "2":
                        if (fix[col][row].contains("@"))
                        {
                            fix[col][row] = "@";
                        }
                        else
                        {
                            fix[col][row] = " ";
                        }
                        break;
                    case "3":
                        fix[col][row] = "@";
                        break;
                    case "4":
                    case "5":
                    case "6":
                    case "7":
                    case "8":
                        fix[col][row] = " ";
                        break;
                }
                row++;
            }

            row = 0;
            col++;
        }
        //gen++;

        rewind();
    }

    public JButton[][] rewind()
    {
        for (int y = 0; y < counter; y++)
        {
            for (int x = 0; x < counter; x++)
            {
                if (fix[x][y].contains("@"))
                {
                    arra[x][y].setBackground(Color.white);
                }
                else
                {
                    arra[x][y].setBackground(Color.gray);
                }
            }

        }

        return arra;
    }

    public int whatgen()
    {
        gen++;
        return gen;
    }

    public GameOfLife(JButton[][] thearra, int thecount)
    {
        counter = thecount;
        arra = thearra;
    }
    
}
