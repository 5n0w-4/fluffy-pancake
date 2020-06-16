package id322029638_id31582270.GUI.ShowOptions.Tabels;

import id322029638_id31582270.population.Voter;
import id322029638_id31582270.interfaces.RepresentativeMarker;
import id322029638_id31582270.interfaces.SickMarker;
import id322029638_id31582270.interfaces.SoliderMarker;
import id322029638_id31582270.interfaces.VoterInterface;
import id322029638_id31582270.population.InfectedSolider;
import id322029638_id31582270.population.Representative;
import id322029638_id31582270.population.Solider;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ShowCitizenInfoTableView<T extends Voter> extends TableView<T> {
	TableColumn<T, String> name;
	TableColumn<T, String> id;
	TableColumn<T, String> birthYear;
	TableColumn<T, Boolean> infected;
	TableColumn<T, Integer> daysInfected;
	TableColumn<T, Boolean> protectiveMask;
	TableColumn<T, Boolean> carryWeapon;
	TableColumn<T, Boolean> solider;
	TableColumn<T, String> rep;

	public ShowCitizenInfoTableView(ObservableList<T> items) {
		super(items);
		name = new TableColumn<T, String>("Name");
		id = new TableColumn<T, String>("ID");
		birthYear = new TableColumn<T, String>("Birth year");
		infected = new TableColumn<T, Boolean>("Infected");
		daysInfected = new TableColumn<T, Integer>("Birth year");
		protectiveMask = new TableColumn<T, Boolean>("Mask");
		carryWeapon = new TableColumn<T, Boolean>("Weapon");
		solider = new TableColumn<T, Boolean>("Solider");
		rep = new TableColumn<T, String>("From Party");

		name.setCellValueFactory(new PropertyValueFactory<T, String>("name"));
		id.setCellValueFactory(new PropertyValueFactory<T, String>("id"));
		birthYear.setCellValueFactory(new PropertyValueFactory<T, String>("birthYear"));
		daysInfected.setCellValueFactory(new PropertyValueFactory<T, Integer>("daysInfected"));

		infected.setCellValueFactory(cellData -> {
			T v = cellData.getValue();
			Boolean val = v.isInfected();
			return new ReadOnlyBooleanWrapper(val);
		});
		solider.setCellValueFactory(cellData -> {
			T v = cellData.getValue();
			Boolean val = v.isInArmy();
			return new ReadOnlyBooleanWrapper(val);
		});
		carryWeapon.setCellValueFactory(cellData -> {//TODO: fix - issues with solider-representative
			T v = cellData.getValue();
			if (v instanceof SoliderMarker) {
				System.out.println(v.toString());
				SoliderMarker temp = (SoliderMarker) v;
				Boolean val = temp.isCarryWeapon();
				return new ReadOnlyBooleanWrapper(val);
			}
			return null;

		});
		protectiveMask.setCellValueFactory(cellData -> {
			T v = cellData.getValue();
			Boolean val = v.isProtectionGear();
			return new ReadOnlyBooleanWrapper(val);
		});
		rep.setCellValueFactory(cellData -> {
			T v = cellData.getValue();
			if (v instanceof RepresentativeMarker) {
				RepresentativeMarker vv = (RepresentativeMarker) v;
				String val = vv.getUnderParty().getName();
				return new ReadOnlyStringWrapper(val);
			}
			return null;
		});
		
		
		this.getColumns().setAll(name, id, birthYear, infected,daysInfected, protectiveMask, solider, carryWeapon, rep);
	}
	
	public void setVoterTableView() {
		this.getColumns().clear();
		this.getColumns().setAll(name,id,birthYear);
	}
	public void setInfectedTableView() {
		this.getColumns().clear();
		this.getColumns().setAll(name,id,birthYear,infected,daysInfected,protectiveMask);
	}
	public void setSoliderTableView() {
		this.getColumns().clear();
		this.getColumns().setAll(name,id,birthYear,solider,carryWeapon);
	}
	public void setRepresentativeTableView() {
		this.getColumns().clear();
		this.getColumns().setAll(name,id,birthYear,rep);
	}
	public void setAllTableView() {
		this.getColumns().clear();
		this.getColumns().setAll(name, id, birthYear, infected,daysInfected, protectiveMask, solider, carryWeapon, rep);
	}
}
