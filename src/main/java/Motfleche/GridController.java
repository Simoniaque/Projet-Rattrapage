package Motfleche;

import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class GridController {

    private BorderPane rootPane;
    private GridPane gridPane;
    private CrosswordGrid gridModel;

    public GridController(BorderPane rootPane) {
        this.rootPane = rootPane;
        this.gridPane = new GridPane();
        initializeUI();
    }

    private void initializeUI() {
        // Configuration de la taille de la grille
        TextInputDialog dialog = new TextInputDialog("5");
        dialog.setTitle("Taille de la grille");
        dialog.setHeaderText("Entrer la taille de la grille (ex: 5 pour une grille 5x5)");
        dialog.setContentText("Taille:");

        int size = Integer.parseInt(dialog.showAndWait().orElse("5"));
        gridModel = new CrosswordGrid(size, size);

        // Création de la grille de boutons
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Button btn = new Button();
                btn.setMinSize(50, 50);
                int finalI = i;
                int finalJ = j;
                btn.setOnMouseClicked((MouseEvent event) -> handleCellClick(finalI, finalJ, btn));
                gridPane.add(btn, j, i);
            }
        }

        rootPane.setCenter(gridPane);
    }

    private void handleCellClick(int row, int col, Button btn) {
        ChoiceDialog<String> typeDialog = new ChoiceDialog<>("Blanche", "Blanche", "Noir", "Grise");
        typeDialog.setTitle("Type de cellule");
        typeDialog.setHeaderText("Choisissez le type de cellule:");
        typeDialog.setContentText("Type:");

        String choice = typeDialog.showAndWait().orElse("Blanche");
        gridModel.setCell(row, col, CaseType.valueOf(choice.toUpperCase()));

        // Créer un VBox pour contenir le bouton et l'image
        VBox vbox = new VBox();
        vbox.setSpacing(5);  // Espacement entre le bouton et l'image

        switch (choice) {
            case "Noir":
                btn.setStyle("-fx-background-color: black;");
                break;
            case "Blanche":
                btn.setStyle("-fx-background-color: white;");
                break;
            case "Grise":
                btn.setStyle("-fx-background-color: grey;");
                addDefinition(row, col, btn);
                break;
        }

        // Ajouter le bouton au VBox
        vbox.getChildren().add(btn);

        // Ajouter l'image si elle est nécessaire
        if (btn.getGraphic() != null) {
            String iconPath = getIconPathForDirection(gridModel.getCellDirection(row, col));
            ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream(iconPath)));
            imageView.setFitHeight(20);
            imageView.setFitWidth(20);
            vbox.getChildren().add(imageView);
        }

        // Ajouter le VBox à la grille
        gridPane.add(vbox, col, row);
    }

    private void addDefinition(int row, int col, Button btn) {
        TextInputDialog defDialog = new TextInputDialog();
        defDialog.setTitle("Ajouter une définition");
        defDialog.setHeaderText("Entrez la définition:");

        String definition = defDialog.showAndWait().orElse("");

        // Nettoyage de l'entrée de la définition
        definition = definition.trim();

        ChoiceDialog<Direction> dirDialog = new ChoiceDialog<>(Direction.droit_droit, Direction.values());
        dirDialog.setTitle("Direction de la définition");
        dirDialog.setHeaderText("Choisissez la direction:");
        Direction direction = dirDialog.showAndWait().orElse(Direction.droit_droit);

        gridModel.setCellDefinition(row, col, new Definition(definition, direction));

        // Ajouter le texte de la définition
        btn.setText(definition);

        // Mettre à jour l'icône pour la direction
        String iconPath = getIconPathForDirection(direction);
        ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream(iconPath)));
        imageView.setFitHeight(20);
        imageView.setFitWidth(20);
        btn.setGraphic(imageView);
    }

    private String getIconPathForDirection(Direction direction) {
        switch (direction) {
            case droit_droit:
                return "/resources/icons/arrow_right.png";
            case droit_bas:
                return "/resources/icons/arrow_left.png";
            case bas_bas:
                return "/resources/icons/arrow_down.png";
            case bas_droit:
                return "/resources/icons/arrow_up.png";
            default:
                return ""; // Cas par défaut, devrait être géré de manière appropriée
        }
    }
}
