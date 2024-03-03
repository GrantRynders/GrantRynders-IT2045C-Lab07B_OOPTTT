import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.*;
public class TicTacToeRunner
{
    private static TicTacToeFrame TTTF;
    private static final int ROW = 3;
    private static final int COL = 3;
    public static TicTacToeTile[][] tiles = new TicTacToeTile[ROW][COL];
    private String[] tileValues = {"O", "X", " "};
    public TicTacToeTile selectedTile;
    public boolean isTileSelected = false;
    public int playerInt;
    private int moveCount;
    private boolean finished = false;
    private boolean playing = true;

    public static void main(String[] args)
    {
        TTTF = new TicTacToeFrame();
    }

    public void playGame()
    {
        //begin a game
        playerInt = 1; //X
        moveCount = 0;
        clearBoard();
    }
    public void receiveInput(int row, int col)
    {
        if (isValidMove(row, col))
        {
            selectedTile.setContents(playerInt);
            isTileSelected = true;
            moveCount++;
            if(moveCount >= 5)
            {
                if(isWin(playerInt))
                {
                    display();
                    TTTF.winMessage(tileValues[playerInt]);
                    playing = false;
                    finished = TTTF.endGameQuery();
                    if (finished == false)
                    {
                        playGame();
                    }
                }
            }
            if(moveCount >= 7)
            {
                if(isTie())
                {
                    display();
                    TTTF.tieMessage();
                    playing = false;
                    finished = TTTF.endGameQuery();
                    if (finished == false)
                    {
                        playGame();
                    }
                }
            }
            if(playerInt == 1)
            {
                playerInt = 0;
            }
            else
            {
                playerInt = 1;
            }
        }
        else
        {
            isTileSelected = false;
        }
        display();
    }
    private static void clearBoard()
    {
        // sets all the board elements to a space
        for(int row=0; row < ROW; row++)
        {
            for(int col=0; col < COL; col++)
            {
                //board[row][col] = " ";
                tiles[row][col].setContents(2);
            }
        }
    }
    private void display()
    {
        // shows the Tic Tac Toe game
        for(int row=0; row < ROW; row++)
        {
            for(int col=0; col < COL; col++)
            {
                TicTacToeTile tile = tiles[row][col];
                tile.setText(tileValues[tile.getContents()]);
            }
        }

    }
    private boolean isValidMove(int row, int col)
    {
        boolean retVal = false;
        if(tiles[row][col].getContents() == 2)
        {
            retVal = true;
        }
        else {
            TTTF.InvalidMove();
        }

        return retVal;
    }
    public static boolean isWin(int playerInt)
    {
        if(isColWin(playerInt) || isRowWin(playerInt) || isDiagonalWin(playerInt))
        {
            return true;
        }
        if(isColWin(playerInt) || isRowWin(playerInt) || isDiagonalWin(playerInt))
        {
            return true;
        }

        return false;
    }
    private static boolean isColWin(int playerInt)
    {
        // checks for a col win for specified player
        for(int col=0; col < COL; col++)
        {
            if(tiles[0][col].getContents() == playerInt &&
                    tiles[1][col].getContents() == playerInt &&
                    tiles[2][col].getContents() == playerInt)
            {
                return true;
            }
        }
        return false; // no col win
    }
    private static boolean isRowWin(int playerInt)
    {
        // checks for a row win for the specified player
        for(int row=0; row < ROW; row++)
        {
            if(tiles[row][0].getContents() == playerInt &&
                    tiles[row][1].getContents() == playerInt &&
                    tiles[row][2].getContents() == playerInt)
            {
                return true;
            }
        }
        return false; // no row win
    }
    private static boolean isDiagonalWin(int playerInt)
    {
        if(tiles[0][0].getContents() == playerInt &&
                tiles[1][1].getContents() == playerInt &&
                tiles[2][2].getContents() == playerInt)
        {
            return true;
        }
        if(tiles[0][2].getContents() == playerInt &&
                tiles[1][1].getContents() == playerInt &&
                tiles[2][0].getContents() == playerInt)
        {
            return true;
        }
        return false;
    }

    // checks for a tie before board is filled.
    // check for the win first to be efficient
    private static boolean isTie()
    {
        boolean xFlag = false;
        boolean oFlag = false;
        // Check all 8 win vectors for an X and O so
        // no win is possible
        // Check for row ties
        for(int row=0; row < ROW; row++)
        {
            if(tiles[row][0].getContents() == 1 ||
                    tiles[row][1].getContents() == 1 ||
                    tiles[row][2].getContents() == 1)
            {
                xFlag = true; // there is an X in this row
            }
            if(tiles[row][0].getContents() == 0 ||
                    tiles[row][1].getContents() == 0 ||
                    tiles[row][2].getContents() == 0)
            {
                oFlag = true; // there is an O in this row
            }

            if(! (xFlag && oFlag) )
            {
                return false; // No tie can still have a row win
            }

            xFlag = oFlag = false;
        }
        // Now scan the columns
        for(int col=0; col < COL; col++)
        {
            if (tiles[0][col].getContents() == 1 ||
                    tiles[1][col].getContents() == 1 ||
                    tiles[2][col].getContents() == 1)
            {
                xFlag = true; // there is an X in this col
            }
            if (tiles[0][col].getContents() == 0 ||
                    tiles[1][col].getContents() == 0 ||
                    tiles[2][col].getContents() == 0)
            {
                oFlag = true; // there is an O in this col
            }

            if (!(xFlag && oFlag))
            {
                return false; // No tie can still have a col win
            }
        }
        // Now check for the diagonals
        xFlag = oFlag = false;

        if(tiles[0][0].getContents() == 1 ||
                tiles[1][1].getContents() == 1 ||
                tiles[2][2].getContents() == 1 )
        {
            xFlag = true;
        }
        if(tiles[0][0].getContents() == 0 ||
                tiles[1][1].getContents() == 0 ||
                tiles[2][2].getContents() == 0 )
        {
            oFlag = true;
        }
        if(! (xFlag && oFlag) )
        {
            return false; // No tie can still have a diagonal win
        }
        xFlag = oFlag = false;

        if(tiles[0][2].getContents() == 1 ||
                tiles[1][1].getContents() == 1 ||
                tiles[2][0].getContents() == 1 )
        {
            xFlag =  true;
        }
        if(tiles[0][2].getContents() == 0 ||
                tiles[1][1].getContents() == 0 ||
                tiles[2][0].getContents() == 0 )
        {
            oFlag =  true;
        }
        if(! (xFlag && oFlag) )
        {
            return false; // No tie can still have a diagonal win
        }

        // Checked every vector so I know I have a tie
        return true;
    }
}
