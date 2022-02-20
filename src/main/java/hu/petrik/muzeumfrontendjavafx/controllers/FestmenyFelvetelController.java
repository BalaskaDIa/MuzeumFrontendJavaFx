package hu.petrik.muzeumfrontendjavafx.controllers;

import hu.petrik.muzeumfrontendjavafx.Controller;
import hu.petrik.muzeumfrontendjavafx.Festmeny;
import hu.petrik.muzeumfrontendjavafx.api.FestmenyApi;
import javafx.event.Event;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;

import java.io.IOException;

public class FestmenyFelvetelController extends Controller {
    @javafx.fxml.FXML
    private CheckBox inputKiallitva;
    @javafx.fxml.FXML
    private TextField inputCim;
    @javafx.fxml.FXML
    private Label lblEvHiba;
    @javafx.fxml.FXML
    private Label lblCimHiba;
    @javafx.fxml.FXML
    private Spinner <Integer> inputEv;

    @javafx.fxml.FXML
    public void btn_FestmenyFelvesz(Event event) {
        String cim = inputCim.getText();
        if (cim.isEmpty()) {
            lblCimHiba.setText("A cím megadása kötelező!");
            return;
        }
        lblCimHiba.setText("");

        boolean kiallitva = inputKiallitva.isSelected();

        int ev = 0;
        try {
            ev = inputEv.getValue();
        } catch (NullPointerException e) {
            lblEvHiba.setText("Az év megaádsa kötelező!");
            return;
        } catch (Exception e) {
            lblEvHiba.setText("Az év 1 és 2022 közötti lehet!");
            return;
        }

        if (ev < 1 || ev > 2022) {
            lblEvHiba.setText("Az év 1 és 2022 közötti lehet!");
            return;
        }
        lblEvHiba.setText("");

        try {
            Festmeny uj = new Festmeny(0, cim, ev, kiallitva);
            Festmeny letrehozott = FestmenyApi.post(uj);
            if (letrehozott != null) {
                alert("Sikeres hozzáadás!");
                inputCim.setText("");
                inputKiallitva.setSelected(false);
                inputEv.getValueFactory().setValue(1000);
            } else {
                alert("Sikertelen hozzáadás!");
            }
        } catch (IOException e) {
            hibaKiir(e);
        }
    }
}
