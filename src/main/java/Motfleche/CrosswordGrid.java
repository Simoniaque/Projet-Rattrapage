package Motfleche;

public class CrosswordGrid {
    private Case[][] grille;
    private int rows, cols;

    public CrosswordGrid(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        grille = new Case[rows][cols];
        initializeGrid();
    }

    private void initializeGrid() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grille[i][j] = new Case(CaseType.BLANCHE);
            }
        }
    }

    public void setCell(int row, int col, CaseType type) {
        if (isValidCell(row, col)) {
            grille[row][col].setType(type);
        }
    }

    public void setCellDefinition(int row, int col, Definition definition) {
        if (isValidCell(row, col) && grille[row][col].getType() == CaseType.GRISE) {
            grille[row][col].setDefinition(definition);
        }
    }

    public Case getCell(int row, int col) {
        if (isValidCell(row, col)) {
            return grille[row][col];
        }
        return null;
    }

    private boolean isValidCell(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    public Definition getCellDefinition(int row, int col) {
        // Méthode pour obtenir la définition d'une cellule
        return grille[row][col].getDefinition();
    }

    public Direction getCellDirection(int row, int col) {
        // Méthode pour obtenir la direction à partir de la définition de la cellule
        Definition definition = getCellDefinition(row, col);
        return (definition != null) ? definition.getDirection() : Direction.droit_droit; // Valeur par défaut
    }
}