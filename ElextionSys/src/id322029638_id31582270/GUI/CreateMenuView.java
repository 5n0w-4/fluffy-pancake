package id322029638_id31582270.GUI;

import java.util.ArrayList;

import id322029638_id31582270.GUI.Buttons.addOptionButton;
import id322029638_id31582270.interfaces.ElectionUIListenable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class CreateMenuView {
	VBox vbMenu;
	GridPane gpMenu;
	ArrayList<ElectionUIListenable> listners;
	ArrayList<Node> menu = new ArrayList<>();

	public CreateMenuView(ArrayList<ElectionUIListenable> listners, StackPane bgPane) {
		this.listners = listners;

		vbMenu = new VBox();
		gpMenu = new GridPane();
	    ColumnConstraints colConstraint = new ColumnConstraints(190);
	    gpMenu.getColumnConstraints().addAll(colConstraint,colConstraint,colConstraint);
	    gpMenu.setHgap(10);
	    gpMenu.setVgap(10);
		vbMenu.setSpacing(15);
		vbMenu.setAlignment(Pos.CENTER);
		vbMenu.setPrefWidth(100);

		Label lblTitle = new Label("Elections 2020");
		lblTitle.setFont(new Font("Curve", 35));

		


		menu.add(new addOptionButton("Add Ballot Box", bgPane, listners));
		menu.add(new addOptionButton("Add Citizen", bgPane, listners));
		menu.add(new addOptionButton("Add Party", bgPane, listners));
		menu.add(new addOptionButton("Add Representative", bgPane, listners));
		menu.add(new addOptionButton("Show Ballot Boxes", bgPane, listners));
		menu.add(new addOptionButton("Show Citizens", bgPane, listners));
		menu.add(new addOptionButton("Show Partys", bgPane, listners));
		menu.add(new addOptionButton("Start Voting", bgPane, listners));
		menu.add(new addOptionButton("Show Results", bgPane, listners));
		menu.add(new addOptionButton("Quit", bgPane, listners));
		
		
		gpMenu.add(menu.get(0), 0, 0);
		gpMenu.add(menu.get(1), 1, 0);
		gpMenu.add(menu.get(2), 2, 0);
		gpMenu.add(menu.get(3), 0, 1);
		gpMenu.add(menu.get(4), 1, 1);
		gpMenu.add(menu.get(5), 2, 1);
		gpMenu.add(menu.get(6), 0, 2);
		gpMenu.add(menu.get(7), 1, 2);
		gpMenu.add(menu.get(8), 2, 2);

		vbMenu.getChildren().add(lblTitle);
		vbMenu.getChildren().add(gpMenu);
//		gpMenu.getChildren().addAll(menu);
		bgPane.setMargin(vbMenu, new Insets(50, 50, 50, 50));
	}

	public VBox getPane() {
		return this.vbMenu;
	}

}
