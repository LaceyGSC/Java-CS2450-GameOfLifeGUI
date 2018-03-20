package golguiltaylor;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Lacey
 */
public class MyGrid extends JFrame implements ActionListener
{
//Attributes

    private JButton arra[][];
    private GameOfLife gol;
    private int count, track;
    private JButton start, move;
    private JComboBox drop;
    private JPanel mypain;
    private JLabel gen;

//Constructors
    public MyGrid(String title, int coun)
    {
        super(title);

        track = 0;
        count = coun;
        start = new JButton("Clear");
        move = new JButton("Next Gen");
        mypain = new JPanel();
        arra = new JButton[count][count];
        gen = new JLabel("Gen: "+ track);


        this.setLayout(null);
        this.setSize(760, 650);
        this.setLocation(200, 10);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        mypain.setLayout(new GridLayout(count, count));
        mypain.setSize(600, 500);
        mypain.setLocation(50, 20);
        this.add(mypain);

        String[] starter = new String[5];
        starter[0] = "Line";
        starter[1] = "Glider";
        starter[2] = "Toad";
        starter[3] = "Exploder";
        starter[4] = "Nine Cell";

        drop = new JComboBox(starter);
        drop.setSize(100, 30);
        drop.setLocation(140, 530);
        this.add(drop);

        gen.setSize(50, 50);
        gen.setLocation(50, 520);
        this.add(gen);

        move.setSize(120, 50);
        move.setLocation(280, 520);
        this.add(move);
        
        start.setSize(120, 50);
        start.setLocation(420, 520);
        this.add(start);

        for (int y = 0; y < count; y++)
        {
            for (int x = 0; x < count; x++)
            {

                arra[x][y] = new JButton();
                arra[x][y].setBackground(Color.gray);
                arra[x][y].addActionListener(new Clicker());
                mypain.add(arra[x][y]);
            }
        }

        move.addActionListener(new Next());
        drop.addActionListener(new Pickone());
        start.addActionListener(new Clearout());
    }

    public void where(int sele)
    {
        switch (sele)
        {
            case 0:
                arra[20][15].setBackground(Color.white);
                arra[20][16].setBackground(Color.white);
                arra[20][17].setBackground(Color.white);
                break;
            case 1:
                arra[15][20].setBackground(Color.white);
                arra[16][20].setBackground(Color.white);
                arra[17][20].setBackground(Color.white);
                arra[17][19].setBackground(Color.white);
                arra[16][18].setBackground(Color.white);
                break;
            case 2:
                arra[20][15].setBackground(Color.white);
                arra[21][15].setBackground(Color.white);
                arra[22][15].setBackground(Color.white);
                arra[21][14].setBackground(Color.white);
                arra[22][14].setBackground(Color.white);
                arra[23][14].setBackground(Color.white);

                break;
            case 3:
                arra[23][20].setBackground(Color.white);
                arra[23][21].setBackground(Color.white);
                arra[23][22].setBackground(Color.white);
                arra[23][23].setBackground(Color.white);
                arra[23][24].setBackground(Color.white);
                arra[27][20].setBackground(Color.white);
                arra[27][21].setBackground(Color.white);
                arra[27][22].setBackground(Color.white);
                arra[27][23].setBackground(Color.white);
                arra[27][24].setBackground(Color.white);
                arra[25][20].setBackground(Color.white);
                arra[25][24].setBackground(Color.white);

                break;
            case 4:
                arra[20][15].setBackground(Color.white);
                arra[20][16].setBackground(Color.white);
                arra[20][17].setBackground(Color.white);
                arra[20][18].setBackground(Color.white);
                arra[20][19].setBackground(Color.white);
                arra[20][20].setBackground(Color.white);
                arra[20][21].setBackground(Color.white);
                arra[20][22].setBackground(Color.white);
                arra[20][23].setBackground(Color.white);
                break;
            default:
                throw new AssertionError();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
    }

    private class Clearout implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e)
        {
            for (int y = 0; y < count; y++)
            {
                for (int x = 0; x < count; x++)
                {
                    arra[x][y].setBackground(Color.gray);
                }
            }
            
           track = 0;
           gen.setText("Gen: 0");
        }
    }

    private class Clicker implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (((JButton) e.getSource()).getBackground().equals((Color.GRAY)))
            {
                ((JButton) e.getSource()).setBackground(Color.white);
            }
            else
            {
                ((JButton) e.getSource()).setBackground(Color.GRAY);
            }

        }
    }

    private class Next implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e)
        {
            gol = new GameOfLife(arra, count);
            gol.initialize();
            gol.nextGeneration();
            gen.setText("Gen: " + track++);
        }
    }


    private class Pickone implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e)
        {
            for (int y = 0; y < count; y++)
            {
                for (int x = 0; x < count; x++)
                {
                    arra[x][y].setBackground(Color.gray);
                }
            }

            where(drop.getSelectedIndex());
            track = 1;
        }
    }
}
