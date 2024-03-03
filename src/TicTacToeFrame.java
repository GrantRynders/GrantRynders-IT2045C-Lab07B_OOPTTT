import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TicTacToeFrame extends JFrame
{
    public JOptionPane optionPane;
    private JPanel mainPanel;
    private JPanel gamePanel;
    public JButton quitButton;
    private TicTacToeRunner TTTR = new TicTacToeRunner();
    private ArrayList<TicTacToeTile> tiles = new ArrayList<>();
    public TicTacToeFrame()
    {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(2,1));
        gamePanel = new JPanel();
        gamePanel.setLayout(new GridLayout(3,3));
        quitButton = new JButton("QUIT");
        quitButton.addActionListener(e -> System.exit(0));
        setTitle("Tic-Tac-Toe");
        for (int i = 0; i < 3; i++)
        {
            TicTacToeTile tile = new TicTacToeTile(0, i);
            tiles.add(tile);
        }
        for (int i = 0; i < 3; i++)
        {
            TicTacToeTile tile = new TicTacToeTile(1, i);
            tiles.add(tile);
        }
        for (int i = 0; i < 3; i++)
        {
            TicTacToeTile tile = new TicTacToeTile(2, i);
            tiles.add(tile);
        }
        for (TicTacToeTile tile: tiles)
        {
            TTTR.tiles[tile.getRow()][tile.getCol()] = tile;
            tile.addActionListener(e ->
            {
                TTTR.selectedTile = tile;
                TTTR.isTileSelected = true;
                TTTR.receiveInput(tile.getRow(), tile.getCol());
            });
            gamePanel.add(tile);
        }
        mainPanel.add(gamePanel);
        mainPanel.add(quitButton);
        add(mainPanel);
        buildWindow();
        TTTR.playGame();
    }
    private void buildWindow()
    {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize((screenWidth / 2), screenHeight);
        setLocation(screenWidth / 4, 0);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    public boolean endGameQuery()
    {
        int selectedOption = JOptionPane.showConfirmDialog(null,
                "Do you want to stop playing?", "Choose", JOptionPane.YES_NO_OPTION);
        if (selectedOption == JOptionPane.YES_OPTION)
        {
            System.exit(0);
            return true;
        }
        else
        {
            return false;
        }
    }
    public void tieMessage()
    {
        optionPane.showInternalMessageDialog(null, "TIE",
                "TIE", JOptionPane.INFORMATION_MESSAGE);
    }
    public void winMessage(String player)
    {
        optionPane.showInternalMessageDialog(null, "Player " + player + " wins!",
                "WINNER", JOptionPane.INFORMATION_MESSAGE);
    }
    public void InvalidMove()
    {
        optionPane.showInternalMessageDialog(null, "Invalid Move",
                "INVALID MOVE", JOptionPane.INFORMATION_MESSAGE);
    }
}
