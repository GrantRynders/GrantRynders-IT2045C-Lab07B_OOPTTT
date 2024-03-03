
import javax.swing.JButton;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author wulft
 */
public class TicTacToeTile extends JButton
{
    private int row;
    private int col;
    private int contents = 2; // 0 = circle, 1 = X, 2 = empty
    public int getContents()
    {
        return contents;
    }

    public void setContents(int contents)
    {
        if (contents >= 0 && contents <= 2)
        {
            this.contents = contents;
        }
    }

    public TicTacToeTile(int row, int col) {
        super();
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
    
    
    
    
}
