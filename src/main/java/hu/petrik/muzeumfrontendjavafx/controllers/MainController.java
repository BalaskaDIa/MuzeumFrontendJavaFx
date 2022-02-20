package hu.petrik.muzeumfrontendjavafx.controllers;

import hu.petrik.muzeumfrontendjavafx.Controller;
import hu.petrik.muzeumfrontendjavafx.Festmeny;
import hu.petrik.muzeumfrontendjavafx.Szobor;
import hu.petrik.muzeumfrontendjavafx.api.FestmenyApi;
import hu.petrik.muzeumfrontendjavafx.api.SzoborApi;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.List;

import java.io.IOException;

public class MainController extends Controller {

    @FXML
    private TableColumn <Festmeny, Integer> col_FId;
    @FXML
    private TableColumn <Szobor, Integer> col_SzId;
    @FXML
    private TableView <Szobor> table_Szobor;
    @FXML
    private TableView <Festmeny> table_Festmeny;
    @FXML
    private TableColumn <Szobor, Integer> col_SzAr;
    @FXML
    private TableColumn <Szobor, String> col_SzSzemely;
    @FXML
    private TableColumn <Szobor, Integer> col_SzMagas;
    @FXML
    private TableColumn <Festmeny, Integer> col_FEv;
    @FXML
    private TableColumn <Festmeny, Boolean> col_FKiallit;
    @FXML
    private TableColumn <Festmeny, String> col_FCim;
    @FXML
    private Button btn_FModosit;
    @FXML
    private Button btn_FTorol;
    @FXML
    private Button btn_SzModosit;
    @FXML
    private Button btn_SzHozzaad;
    @FXML
    private Button btn_SzTorol;
    @FXML
    private Button btn_FHozzaad;

    public void initialize() {
        col_FId.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_FCim.setCellValueFactory(new PropertyValueFactory<>("title"));
        col_FEv.setCellValueFactory(new PropertyValueFactory<>("year"));
        col_FKiallit.setCellValueFactory(new PropertyValueFactory<>("on_display"));

        col_SzId.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_SzSzemely.setCellValueFactory(new PropertyValueFactory<>("person"));
        col_SzMagas.setCellValueFactory(new PropertyValueFactory<>("height"));
        col_SzAr.setCellValueFactory(new PropertyValueFactory<>("price"));

        festmenyListaFeltolt();
        szoborListaFeltolt();
    }

    private void festmenyListaFeltolt() {
        try {
            List<Festmeny> festmenyList = FestmenyApi.get();
            table_Festmeny.getItems().clear();
            for (Festmeny festmeny : festmenyList) {
                table_Festmeny.getItems().add(festmeny);
            }
        } catch (IOException e) {
            hibaKiir(e);
        }
    }

    private void szoborListaFeltolt() {
        try {
            List<Szobor> szoborList = SzoborApi.get();
            table_Szobor.getItems().clear();
            for (Szobor szobor : szoborList) {
                table_Szobor.getItems().add(szobor);
            }
        } catch (IOException e) {
            hibaKiir(e);
        }
    }

    @FXML
    public void btn_SzHozzaad(ActionEvent actionEvent) {
        try {
            Controller hozzaad = ujAblak("szobor-felvetel-view.fxml", "Szobor felvétele", 300, 240);
            hozzaad.getStage().setOnCloseRequest(event -> szoborListaFeltolt());
            hozzaad.getStage().show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void btn_SzTorol(ActionEvent actionEvent) {
        Szobor torles = table_Szobor.getSelectionModel().getSelectedItem();
        if (!confirm("Biztos törölni szeretné?")) {
            return;
        }
        try {
            boolean siker = SzoborApi.delete(torles.getId());
            alert(siker ? "Sikeres törlés!" : "Sikertelen törlés!");
            szoborListaFeltolt();
        } catch (IOException e) {
            hibaKiir(e);
        }
    }

    @FXML
    public void btn_FHozzaad(ActionEvent actionEvent) {
        try {
            Controller hozzaad = ujAblak("festmeny-felvetel-view.fxml", "Festmény felvétele", 300, 240);
            hozzaad.getStage().setOnCloseRequest(event -> festmenyListaFeltolt());
            hozzaad.getStage().show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void btn_FModosit(ActionEvent actionEvent) {
    }

    @FXML
    public void btn_FTorol(ActionEvent actionEvent) {
        Festmeny torles = table_Festmeny.getSelectionModel().getSelectedItem();
        if (!confirm("Biztos törölni szeretné?")) {
            return;
        }
        try {
            boolean siker = SzoborApi.delete(torles.getId());
            alert(siker ? "Sikeres törlés!" : "Sikertelen törlés!");
            festmenyListaFeltolt();
        } catch (IOException e) {
            hibaKiir(e);
        }
    }

    @FXML
    public void btn_SzModosit(ActionEvent actionEvent) {
    }

    @FXML
    public void click_Festmeny(Event event) {
        int selectedIndex = table_Festmeny.getSelectionModel().getSelectedIndex();
        if (selectedIndex != -1) {
            btn_FModosit.setDisable(false);
            btn_FTorol.setDisable(false);
        }
    }

    @FXML
    public void click_Szobor(Event event) {
        int selectedIndex = table_Szobor.getSelectionModel().getSelectedIndex();
        if (selectedIndex != -1) {
            btn_SzModosit.setDisable(false);
            btn_SzTorol.setDisable(false);
        }
    }
}