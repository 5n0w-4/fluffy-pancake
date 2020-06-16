package id322029638_id31582270.GUI.ShowOptions.Tabels;

import id322029638_id31582270.logic.Party;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ShowPartyInfoTableView<T extends Party> extends TableView<T> {
	TableColumn<T, String> name;
	TableColumn<T, String> creationDate;
	TableColumn<T, Integer> id;
	TableColumn<T, String> wingDirect;

	public ShowPartyInfoTableView(ObservableList<T> items) {
		super(items);
		name = new TableColumn<T, String>("Name");
		creationDate = new TableColumn<T, String>("Created");
		id = new TableColumn<T, Integer>("ID");
		wingDirect = new TableColumn<T, String>("Wing");

		name.setCellValueFactory(new PropertyValueFactory<T, String>("name"));
		id.setCellValueFactory(new PropertyValueFactory<T, Integer>("thisId"));

		wingDirect.setCellValueFactory(cellData -> {
			T v = cellData.getValue();
			String val = v.getWingDirect();
			return new ReadOnlyStringWrapper(val);

		});
		creationDate.setCellValueFactory(cellData -> {
			T v = cellData.getValue();
			String val = v.getCreationDate().toString();
			return new ReadOnlyStringWrapper(val);

		});

		this.getColumns().setAll(name,id, creationDate,wingDirect);
	}

}
