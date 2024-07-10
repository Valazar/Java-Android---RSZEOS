package com.example.twoactivities;


import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.content.Intent;
import java.util.HashMap;

public class MainActivity2 extends AppCompatActivity {

    int numRows = 8;
    int numColumns = 8;
    private int blackPuckRow, blackPuckColumn;
    private int redPuckRow1, redPuckColumn1;
    private int redPuckRow2, redPuckColumn2;
    private int redPuckRow3, redPuckColumn3;
    private int redPuckRow4, redPuckColumn4;
    private int selectedPuckRow = -1, selectedPuckColumn = -1;
    private int clickedRow;
    private int clickedColumn;
    private boolean playerA;
    private boolean playerB;

    HashMap<String, ImageView> images;
    Intent intent;
    public static String RESPONSE_MESSAGE = "Response_text";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        intent = getIntent();
        String message = (String) intent.getExtras().getString(MainActivity.REQUEST_MESSAGE);

        if (message.equals("")) {
            playerA = true;
            playerB = false;
        }
        else{
            playerA = false;
            playerB = true;
        }

        initializeGameBoard();
        setFieldClickListeners();
    }


    //Metoda za kreiranje table sa pakovima
    private void initializeGameBoard() {
        images = new HashMap<>();
        LinearLayout llmain = findViewById(R.id.lvmain);
        boolean isBrown = false;

        for (int row = 1; row <= numRows; row++) {
            LinearLayout llrow = new LinearLayout(this);
            llrow.setOrientation(LinearLayout.HORIZONTAL);

            for (int col = 1; col <= numColumns; col++) {
                ImageView iv = new ImageView(this);
                iv.setTag(row + "," + col);
                images.put(row + "," + col, iv);

                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, 200);
                layoutParams.weight = 1;
                iv.setLayoutParams(layoutParams);

                if (isBrown) {
                    iv.setImageResource(R.drawable.brown);

                    if (row == 1 && (col == 2 || col == 4 || col == 6 || col == 8)) {
                        // Postavi crveni pak na smedja polja prvog reda
                        iv.setImageResource(R.drawable.red_puck);

                        switch (col) {
                            case 2:
                                redPuckRow1 = row;
                                redPuckColumn1 = col;
                                break;
                            case 4:
                                redPuckRow2 = row;
                                redPuckColumn2 = col;
                                break;
                            case 6:
                                redPuckRow3 = row;
                                redPuckColumn3 = col;
                                break;
                            case 8:
                                redPuckRow4 = row;
                                redPuckColumn4 = col;
                                break;
                        }
                    }

                    if (row == 8 && col == 5) {
                        // Postavi crni pak na jedno od smedjih polja u poslednjem redu table
                        placeBlackPuckRandomly();

                    }
                } else {
                    iv.setImageResource(R.drawable.white);
                }

                llrow.addView(iv);
                isBrown = !isBrown; // Promjeni boju za sledeci ImageView
            }

            // Ako je neparan red, pocni sa suprotnom bojom
            isBrown = !isBrown;
            llmain.addView(llrow);
        }
    }

    //Postavljanje crnog pucka na neku od 4 rendom pozicije
    private void placeBlackPuckRandomly() {
        Random random = new Random();
        int randomColumn = random.nextInt(4) * 2 + 1; // slucajan broj: 1, 3, 5 ili 7
        blackPuckRow = numRows; // Crni puck se nalazi u poslednjem redu
        blackPuckColumn = randomColumn;

        // Pronadji ImageView za odabranu poziciju i postavi crni puck na nju
        ImageView blackPuckField = images.get(blackPuckRow + "," + blackPuckColumn);
        blackPuckField.setImageResource(R.drawable.black_puck);
    }

    // Postavljanje OnClickListener za polja na tabli
    private void setFieldClickListeners() {
        for (ImageView field : images.values()) {
            field.setOnClickListener(v -> onFieldClicked((ImageView) v));
        }
    }

    // Metoda koja se poziva kada je polje kliknuto
    private void onFieldClicked(ImageView field) {
        // Dobijanje oznake (Tag) pritisnutog polja
        String tag = (String) field.getTag();

        // Razdvajanje reda i kolone iz oznake
        String[] position = tag.split(",");
        clickedRow = Integer.parseInt(position[0]);
        clickedColumn = Integer.parseInt(position[1]);

        // Prikazivanje Toast poruke s informacijama o kliknutom polju
        Toast.makeText(MainActivity2.this, "Kliknuli ste na polje (" + clickedRow + ", " + clickedColumn + ")", Toast.LENGTH_SHORT).show();

        if (playerA) {
            // Igrač A igra sa crnim pakom
            if (isDiagonal()) {
                moveBlackPuck(field);
                switchPlayer();
                checkEndGame();
            } else {
                Toast.makeText(MainActivity2.this, "Morate odabrati polje koje je dijagonalno od crnog pucka.", Toast.LENGTH_SHORT).show();
            }
        } else if (playerB) {
            // Igrač B igra sa crvenim pakom
            if (isRedPuck(clickedRow, clickedColumn)) {
                selectedPuckRow = clickedRow;
                selectedPuckColumn = clickedColumn;
                Toast.makeText(MainActivity2.this, "Izabrali ste crveni pak.", Toast.LENGTH_SHORT).show();
            } else if (selectedPuckRow != -1 && isSelectedDiagonal()) {
                moveRedPuck(field);
                switchPlayer();
                checkEndGame();
            } else {
                Toast.makeText(MainActivity2.this, "Morate odabrati polje koje je dijagonalno od izabranog crvenog paka.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // Metoda za premestanje crnog paka
    private void moveBlackPuck(ImageView field) {
        // Postavljanje slike crnog paka na novo polje
        field.setImageResource(R.drawable.black_puck);

        // Postavljanje smedjeg polja na staru poziciju crnog paka
        ImageView oldBlackPuckField = images.get(blackPuckRow + "," + blackPuckColumn);
        oldBlackPuckField.setImageResource(R.drawable.brown);

        // Ažuriranje pozicije crnog paka na novo odabrano polje
        blackPuckRow = clickedRow;
        blackPuckColumn = clickedColumn;
    }


    // Metoda za premestanje crvenog paka
    private void moveRedPuck(ImageView field) {
        // Postavljanje slike crvenog paka na novo polje
        field.setImageResource(R.drawable.red_puck);

        // Postavljanje smedjeg polja na staru poziciju crvenog paka
        ImageView oldRedPuckField = images.get(selectedPuckRow + "," + selectedPuckColumn);
        oldRedPuckField.setImageResource(R.drawable.brown);

        // Azuriranje pozicije crvenog paka na novo odabrano polje
        if (selectedPuckRow == redPuckRow1 && selectedPuckColumn == redPuckColumn1) {
            redPuckRow1 = clickedRow;
            redPuckColumn1 = clickedColumn;
        } else if (selectedPuckRow == redPuckRow2 && selectedPuckColumn == redPuckColumn2) {
            redPuckRow2 = clickedRow;
            redPuckColumn2 = clickedColumn;
        } else if (selectedPuckRow == redPuckRow3 && selectedPuckColumn == redPuckColumn3) {
            redPuckRow3 = clickedRow;
            redPuckColumn3 = clickedColumn;
        } else if (selectedPuckRow == redPuckRow4 && selectedPuckColumn == redPuckColumn4) {
            redPuckRow4 = clickedRow;
            redPuckColumn4 = clickedColumn;
        }

        // Resetovanje izabranog crvenog paka
        selectedPuckRow = -1;
        selectedPuckColumn = -1;
    }

    // Metoda za provjeru da li je  odabrano polje dijagonalno od izabranog crvenog pucka
    // Metoda za provjeru da li je odabrano polje dijagonalno od crnog pucka
    private boolean isDiagonal() {
        int rowDiff = Math.abs(blackPuckRow - clickedRow);
        int colDiff = Math.abs(blackPuckColumn - clickedColumn);

        // Proveri da li je ciljano polje slobodno
        if (isOccupied(clickedRow, clickedColumn)) {
            return false;
        }

        return rowDiff == 1 && colDiff == 1;
    }

    // Metoda za provjeru da li je odabrano polje dijagonalno od izabranog crvenog pucka
    private boolean isSelectedDiagonal(){
        int rowDiff = Math.abs(selectedPuckRow - clickedRow);
        int colDiff = Math.abs(selectedPuckColumn - clickedColumn);

        // Crveni pak može ići samo napred dijagonalno
        if (selectedPuckRow >= clickedRow) {
            return false;
        }

        // Proveri da li je ciljano polje slobodno
        if (isOccupied(clickedRow, clickedColumn)) {
            return false;
        }

        return rowDiff == 1 && colDiff == 1;
    }

    // Metoda za provjeru da li je pritisnut crveni pak
    private boolean isRedPuck(int row, int col) {
        return (row == redPuckRow1 && col == redPuckColumn1) ||
                (row == redPuckRow2 && col == redPuckColumn2) ||
                (row == redPuckRow3 && col == redPuckColumn3) ||
                (row == redPuckRow4 && col == redPuckColumn4);
    }

    // Metoda za provjeru da li je polje zauzeto
    private boolean isOccupied(int row, int col) {
        if (row == blackPuckRow && col == blackPuckColumn) {
            return true;
        }

        if (isRedPuck(row, col)) {
            return true;
        }

        return false;
    }

    // Metoda za promjenu igraca
    private void switchPlayer() {
        playerA = !playerA;
        playerB = !playerB;
    }

    // Metoda za provjeru kraja igre
    private void checkEndGame() {
        if (blackPuckRow == 1) {
            Toast.makeText(this, "Igra je zavrsena! Crni puck je stigao do kraja.", Toast.LENGTH_LONG).show();
            endCurrentGame();
            finish();;
            // Ovde mozete implementirati logiku za kraj igre (npr. onemogućiti dalju igru)
            return;
        }

        boolean noMoves = true;
        int[] dRows = {-1, -1, 1, 1};
        int[] dCols = {-1, 1, -1, 1};

        for (int i = 0; i < 4; i++) {
            int newRow = blackPuckRow + dRows[i];
            int newCol = blackPuckColumn + dCols[i];
            if (newRow >= 1 && newRow <= numRows && newCol >= 1 && newCol <= numColumns && !isOccupied(newRow, newCol)) {
                noMoves = false;
                break;
            }
        }

        if (noMoves) {
            Toast.makeText(this, "Igra je zavrsena! Crni puck je blokiran.", Toast.LENGTH_LONG).show();
            endCurrentGame();
            finish();;
        }
    }

    // Metoda koja se poziva kada se zavrsi trenutna partija
    private void endCurrentGame() {
        // Prikazivanje Toast poruke za kraj igre
        Toast.makeText(this, "Igrac " + (playerA ? "B" : "A") + " je pobjedio!", Toast.LENGTH_LONG).show();

        // Slanje poruke o pobedniku nazad u MainActivity
        Intent intent = new Intent();
        intent.putExtra(RESPONSE_MESSAGE, "Igrac " + (playerA ? "B" : "A") + " je pobjedio!");
        setResult(RESULT_OK, intent);

        // Resetovanje igre za sledeću partiju
        resetGame();

    }

    // Metoda za resetovanje igre nakon zavrsetka trenutne partije
    private void resetGame() {
        // Resetovanje pozicija crnog paka
        blackPuckRow = 0;
        blackPuckColumn = 0;

        // Resetovanje pozicija crvenih pakova
        redPuckRow1 = 0;
        redPuckColumn1 = 0;
        redPuckRow2 = 0;
        redPuckColumn2 = 0;
        redPuckRow3 = 0;
        redPuckColumn3 = 0;
        redPuckRow4 = 0;
        redPuckColumn4 = 0;

        // Resetovanje izabranog crvenog paka
        selectedPuckRow = -1;
        selectedPuckColumn = -1;

        // Ponovno postavljanje table sa pakovima
        initializeGameBoard();
    }

}
