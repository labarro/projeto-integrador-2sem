/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yourcad;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import yourcad.DBConexao.*;
import yourcad.Form_instalacaoAguaController.*;
import yourcad.Form_instalacaoEnergiaController.*;

/**
 * FXML Controller class
 *
 * @author mateu
 */
public class Form_CadInstalacoesController implements Initializable {

    @FXML
    private MenuBar menuBar_TelaInicial;
    @FXML
    private ComboBox<String> cbox_tipoInstalacao;
    @FXML
    private Pane panel_01;
    @FXML
    private BorderPane mainPane;
    @FXML
    private AnchorPane ancPane_TelaInicio;
    @FXML
    private MenuItem menuItem_CadCliente;
    @FXML
    private MenuItem menuItem_CadConta;
    @FXML
    private MenuItem menuItem_CadConcessionaria;
    @FXML
    private MenuItem menuItem_PesqCliente;
    @FXML
    private MenuItem menuItem_PesqConta;
    @FXML
    private MenuItem menuItem_PesqConcessionaria;
    @FXML
    private TextField txt_idCliente;
    @FXML
    private TextField txt_numeroInstalacao;
    @FXML
    private TextField txt_apelidoInstalacao;
    @FXML
    private TextField txt_idInstalacao;
    @FXML
    private ComboBox<String> cbox_concessionariaInstalacao;
    @FXML
    private Button btn_limparInstalacao;
    @FXML
    private Button btn_salvarInstalacao;

    /**
     * Initializes the controller class.
     */
    private ObservableList<Concessionaria> linhas_banco;
    @Override
    public void initialize(URL url, ResourceBundle rb){
        // TODO
        // ComboBox Tipo de Instalação
        ObservableList<String> lista = FXCollections.observableArrayList("Agua e Esgoto","Energia");
        cbox_tipoInstalacao.setItems(lista);
     
        // ComboBox Concessionaria
        try{
            Connection conn = null;
            ResultSet resultadoBanco = null;
            conn = DBConexao.abrirConexao();
            Statement stm = conn.createStatement();
            resultadoBanco = stm.executeQuery("SELECT * FROM concessionaria;");
            
            cbox_concessionariaInstalacao.setValue("------------------");
            while(resultadoBanco.next()){
                    String name = resultadoBanco.getString("concessionaria_nome");
                    cbox_concessionariaInstalacao.setValue(name);
            }
        }catch (Exception ex) {
                Logger.getLogger(Form_CadClienteController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
     // INICIO MENU BAR //
    // FUNÇÃO PARA ABRIR TELA A PARTIR DE MENU BAR 
    @FXML
    public void gotoCliente(ActionEvent event) throws IOException{ 
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("Form_CadCliente.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) menuBar_TelaInicial.getScene().getWindow();  
        app_stage.hide();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }

    @FXML
    private void gotoConta(ActionEvent event) throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("Form_CadConta.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) menuBar_TelaInicial.getScene().getWindow();  
        app_stage.hide();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }

    @FXML
    private void gotoConcessionaria(ActionEvent event) throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("Form_CadConcessionaria.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) menuBar_TelaInicial.getScene().getWindow();  
        app_stage.hide();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }
    @FXML
    private void gotoPesqCliente(ActionEvent event) throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("PesqCliente.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) menuBar_TelaInicial.getScene().getWindow();  
        app_stage.hide();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }
    
    @FXML
    private void gotoPesqConta(ActionEvent event) throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("PesqConta.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) menuBar_TelaInicial.getScene().getWindow();  
        app_stage.hide();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }
    
    @FXML
    private void gotoPesqConcessionaria(ActionEvent event) throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("PesqConcessionaria.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) menuBar_TelaInicial.getScene().getWindow();  
        app_stage.hide();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }
    // FIM MENU BAR //

    @FXML
    private void chama_painel(ActionEvent event) throws IOException {
        String tipo = cbox_tipoInstalacao.getValue();
        
        if(tipo == "Agua e Esgoto")
        {
            panel_01.getChildren().clear();
            Pane newLoadedPane =  FXMLLoader.load(getClass().getResource("Form_instalacaoAgua2.fxml"));
            panel_01.getChildren().add(newLoadedPane);
        }
        else if(tipo == "Energia")
        {
            panel_01.getChildren().clear();
            Pane newLoadedPane =  FXMLLoader.load(getClass().getResource("Form_instalacaoEnergia.fxml"));
            panel_01.getChildren().add(newLoadedPane);
        }
    }

    @FXML
    private void salvarInstalacao(ActionEvent event) {
        
        
    }
        
    
}
