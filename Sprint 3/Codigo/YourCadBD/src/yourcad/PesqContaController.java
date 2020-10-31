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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mateu
 */
public class PesqContaController implements Initializable {

    @FXML
    private MenuItem menuItem_CadCliente;
    @FXML
    private BorderPane mainPane;
    @FXML
    private MenuBar menuBar_TelaInicial;
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
    private Button btn_PesquisarCliente;
    @FXML
    private ScrollPane tbview_PesqContas;
    @FXML
    private TableColumn<String, ContaEnergia> col_NInstalacao;
    @FXML
    private TableColumn<String, ContaEnergia> col_ApelidoInstalacao;
    @FXML
    private TableColumn<String, ContaEnergia> col_cliente;
    @FXML
    private TableColumn<String, ContaEnergia> col_ValorConta;
    @FXML
    private TableColumn<String, ContaEnergia> col_CompetenciaConta;
    @FXML
    private TextField txt_NumInstalacao;
    @FXML
    private TableView<ContaEnergia> tbview_Contas;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }     
    
    
    // INICIO MENU BAR //
    // FUNÇÃO PARA ABRIR TELA A PARTIR DE MENU BAR 
    @FXML
    public void gotoCliente(ActionEvent event) throws IOException{
        PesqClienteController.alterClienteId = 0;
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
        PesqConcessionariaController.alterConcessionariaId = 0;
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
    
    private ObservableList<ContaEnergia> linhas_banco;
    @FXML
    private void pesquisarConta(ActionEvent event) throws Exception {

        String numInstalacao = txt_NumInstalacao.getText();
        String tipoConta = null;
        String contaId = null;
        
        Connection conn = null;
        ResultSet resultadoBanco = null;
        conn = DBConexao.abrirConexao();
        Statement stm = conn.createStatement();
                
        String sql0;
        sql0 = "SELECT * FROM conta WHERE conta_numero_instalacao = " + numInstalacao +";";
        resultadoBanco = stm.executeQuery(sql0);
        while(resultadoBanco.next())
        { 
            contaId = resultadoBanco.getString("conta_id"); 
            tipoConta = resultadoBanco.getString("conta_tipo"); 
        }
        
        if("Energia".equals(tipoConta))
        {
            //List<ContaEnergia> linhas_banco = new ArrayList<>();
            linhas_banco = FXCollections.observableArrayList();
              
            String sql1;
            sql1 = "SELECT conta.instalacao_id, instalacao.instalacao_apelido, cliente.cliente_nome, conta_energia_valor,"
                  + " conta_energia_competencia, conta_energia_consumo_mes, conta_energia_vencimento, conta_energia_bandeira_cor, "
                  + " conta_energia_bandeira_periodoini, conta_energia_bandeira_periodo_fim, conta_energia_faturamento_emissao, "
                  + " conta_energia_faturamento_leitatual, conta_energia_faturamento_dias, conta_energia_faturamento_cci, "
                  + " conta_energia_faturamento_produto, conta_energia_faturamento_qtd, conta_energia_faturamento_tarifa, "
                  + " conta_energia_faturamento_valorfornecido, conta_energia_faturamento_tarifaimposto, conta_energia_faturamento_baseicms, "
                  + " conta_energia_faturamento_aliqicms, conta_energia_faturamento_valoricms, conta_energia_faturamento_basepis, "  
                  + " conta_energia_faturamento_aliqpis, conta_energia_faturamento_valorpis, conta_energia_consumo_descricao, "
                  + " conta_energia_consumo_medidor FROM conta " 
                  + " INNER JOIN conta_energia ON conta.conta_id = conta_energia.conta_id "
                  + " INNER JOIN instalacao ON conta.instalacao_id = instalacao.instalacao_id "
                  + " INNER JOIN cliente ON conta.cliente_id = cliente.cliente_id "
                  + " WHERE conta.conta_id = " + contaId +";";
            resultadoBanco = stm.executeQuery(sql1);
           
            while(resultadoBanco.next())
            {
                linhas_banco.add(new ContaEnergia(resultadoBanco.getInt(1),resultadoBanco.getString(1),resultadoBanco.getString(2),resultadoBanco.getString(3),resultadoBanco.getString(4),
                resultadoBanco.getString(5),resultadoBanco.getString(6),resultadoBanco.getString(7),resultadoBanco.getString(8),
                resultadoBanco.getString(9),resultadoBanco.getString(10),resultadoBanco.getString(11),resultadoBanco.getString(12),
                resultadoBanco.getString(13),resultadoBanco.getString(14),resultadoBanco.getString(15),resultadoBanco.getString(16),
                resultadoBanco.getString(17),resultadoBanco.getString(18),resultadoBanco.getString(19),resultadoBanco.getString(20),
                resultadoBanco.getString(21),resultadoBanco.getString(22),resultadoBanco.getString(23),resultadoBanco.getString(24),
                resultadoBanco.getString(25),resultadoBanco.getString(26),resultadoBanco.getString(27)));
            }
            
            System.out.println(linhas_banco);
            
            col_NInstalacao.setCellValueFactory(new PropertyValueFactory<>("conta.instalacao_id"));
            col_ApelidoInstalacao.setCellValueFactory(new PropertyValueFactory<>("instalacao.instalacao_apelido"));
            col_cliente.setCellValueFactory(new PropertyValueFactory<>("cliente.cliente_nome"));
            col_ValorConta.setCellValueFactory(new PropertyValueFactory<>("conta_energia_valor"));
            col_CompetenciaConta.setCellValueFactory(new PropertyValueFactory<>("conta_energia_competencia"));

            tbview_Contas.setItems(null);
            tbview_Contas.setItems(linhas_banco);
            
        }
        
    }
}
 