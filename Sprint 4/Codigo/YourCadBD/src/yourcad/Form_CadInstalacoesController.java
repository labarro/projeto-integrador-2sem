/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yourcad;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import yourcad.DBConexao.*;
import yourcad.Form_instalacaoAguaController;
import yourcad.Form_instalacaoEnergiaController;
import yourcad.Concessionaria;
import static yourcad.Form_CadClienteController.alterInstalacaoId;
import static yourcad.Form_instalacaoAguaController.*;

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
    private ComboBox<Concessionaria> cbox_concessionariaInstalacao;
    @FXML
    private Button btn_limparInstalacao;
    @FXML
    private Button btn_salvarInstalacao;
    
    private FXMLLoader fxmlLoader = new FXMLLoader();

    /**
     * Initializes the controller class.
     */
    private ObservableList<Concessionaria> linhas_banco;
    @FXML
    private Menu menu_Configuracao;
    @FXML
    private MenuItem menuItem_CadUsuarios;
    @FXML
    private MenuItem menuItem_PesqUsuarios;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        // TODO
        
        //Definindo que somente Administradores podem vizualizar meno de Configuração
        if("Administrador".equals(Form_LoginController.usuario_Nivel_Acesso)){ menu_Configuracao.setVisible(true);}
        
        int instalacao_id = Form_CadClienteController.alterInstalacaoId;
        
        if(instalacao_id != 0)
        {
            btn_limparInstalacao.setVisible(false);
            try{
                Connection conn = null;
                ResultSet resultadoBanco = null;
                conn = DBConexao.abrirConexao();
                Statement stm = conn.createStatement();

                //****** Selecionando tipo de instalação
                String sql0;
                sql0 = "SELECT * FROM instalacao WHERE instalacao_id = " + alterInstalacaoId +";";
                resultadoBanco = stm.executeQuery(sql0);
                String inst_tipo = null;
                String inst_numero = null;
                String inst_apelido = null;
                String inst_cliente = null;
                String inst_id = null;
                String inst_concessionaria = null;

                while(resultadoBanco.next())
                { 
                    inst_id = resultadoBanco.getString("instalacao_id"); 
                    inst_apelido = resultadoBanco.getString("instalacao_apelido"); 
                    inst_cliente = resultadoBanco.getString("cliente_id"); 
                    inst_concessionaria = resultadoBanco.getString("concessionaria_id"); 
                    inst_numero = resultadoBanco.getString("instalacao_numero"); 
                    inst_tipo = resultadoBanco.getString("instalacao_tipo"); 
                }
                
                ObservableList<String> lista = FXCollections.observableArrayList("Agua e Esgoto","Energia");
                cbox_tipoInstalacao.setItems(lista);
            
                txt_idCliente.setText(inst_cliente);
                txt_apelidoInstalacao.setText(inst_apelido);
                txt_numeroInstalacao.setText(inst_numero);
                txt_idInstalacao.setText(inst_id);
                //cbox_concessionariaInstalacao.setValue(inst_concessionaria);
                cbox_tipoInstalacao.getSelectionModel().select(inst_tipo);

                String tipo = cbox_tipoInstalacao.getValue();
        
                if(tipo.equals("Agua e Esgoto"))
                {
                    panel_01.getChildren().clear();
                    fxmlLoader.setLocation(getClass().getResource("Form_instalacaoAgua.fxml"));
                    //Pane newLoadedPane =  FXMLLoader.load(getClass().getResource("Form_instalacaoAgua.fxml"));
                    panel_01.getChildren().add(fxmlLoader.load());      
                }
                else if(tipo.equals("Energia"))
                {
                    panel_01.getChildren().clear();
                    fxmlLoader.setLocation(getClass().getResource("Form_instalacaoEnergia.fxml"));
                    //Pane newLoadedPane =  FXMLLoader.load(getClass().getResource("Form_instalacaoEnergia.fxml"));
                    panel_01.getChildren().add(fxmlLoader.load());
                }


            } catch (Exception ex) { Logger.getLogger(Form_CadClienteController.class.getName()).log(Level.SEVERE, null, ex);}
        }else{
        
            //Campo que recebe ID do cliente
             String clienteInstalacao = Form_CadClienteController.clienteInstalacao;
             txt_idCliente.setText(clienteInstalacao);

            // ComboBox Tipo de Instalação
            ObservableList<String> lista = FXCollections.observableArrayList("Agua e Esgoto","Energia");
            cbox_tipoInstalacao.setItems(lista);


            // ComboBox Concessionaria
//            try{          
//                Connection conn = null;
//                ResultSet resultadoBanco = null;
//                conn = DBConexao.abrirConexao();
//                Statement stm = conn.createStatement();
//                resultadoBanco = stm.executeQuery("SELECT * FROM concessionaria;");
//
//                linhas_banco = FXCollections.observableArrayList();
//
//                while(resultadoBanco.next())
//                {                
//                    linhas_banco.add(new Concessionaria(resultadoBanco.getInt(1), resultadoBanco.getString(2), resultadoBanco.getString(3),
//                      resultadoBanco.getString(4), resultadoBanco.getString(5), resultadoBanco.getString(6), resultadoBanco.getString(7),
//                      resultadoBanco.getString(8), resultadoBanco.getString(9), resultadoBanco.getString(10), resultadoBanco.getString(11), 
//                      resultadoBanco.getString(12), resultadoBanco.getString(13)));
//                }
//
//                    cbox_concessionariaInstalacao.setItems(null);
//                    cbox_concessionariaInstalacao.setItems(linhas_banco);
//
//                }   catch (Exception ex) {
//                    Logger.getLogger(Form_CadInstalacoesController.class.getName()).log(Level.SEVERE, null, ex);
//                }
        
        }
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
        PesqContaEnergiaController.contaAlterId = 0;
        PesqContaAguaController.contaAlterId = 0;
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
    
      @FXML
    private void gotoUsuarios(ActionEvent event) throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("Form_Usuarios.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) menuBar_TelaInicial.getScene().getWindow();  
        app_stage.hide();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }

    @FXML
    private void gotoPesqUsuarios(ActionEvent event) throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("PesqUsuarios.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) menuBar_TelaInicial.getScene().getWindow();  
        app_stage.hide();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }
    
    // FIM MENU BAR //
    @FXML
    private void chama_painel() throws IOException {
        String tipo = cbox_tipoInstalacao.getValue();
        
        if(tipo.equals("Agua e Esgoto"))
        {
            panel_01.getChildren().clear();
            fxmlLoader.setLocation(getClass().getResource("Form_instalacaoAgua.fxml"));
            //Pane newLoadedPane =  FXMLLoader.load(getClass().getResource("Form_instalacaoAgua.fxml"));
            panel_01.getChildren().add(fxmlLoader.load());      
        }
        else if(tipo.equals("Energia"))
        {
            panel_01.getChildren().clear();
            fxmlLoader.setLocation(getClass().getResource("Form_instalacaoEnergia.fxml"));
            //Pane newLoadedPane =  FXMLLoader.load(getClass().getResource("Form_instalacaoEnergia.fxml"));
            panel_01.getChildren().add(fxmlLoader.load());
        }
    }
    
    static int instalacao_id;
    @FXML
    private void salvarInstalacao(ActionEvent event) throws SQLException, Exception {
        
        Form_instalacaoAguaController controllerAgua = null;
        Form_instalacaoEnergiaController controllerEnergia = null;    
        
        if (cbox_tipoInstalacao.getSelectionModel().getSelectedIndex() == 0) {
           controllerAgua = fxmlLoader.getController();
        } else {
           controllerEnergia = fxmlLoader.getController();
        }
        
        int inst_id = Form_CadClienteController.alterInstalacaoId;
        
        String cliente_id = txt_idCliente.getText();
        String apelido_instalacao = txt_apelidoInstalacao.getText();
        String numero_Instalacao = txt_numeroInstalacao.getText();
        String tipo_instalacao = cbox_tipoInstalacao.getValue();
        String id_instalacao = txt_idInstalacao.getText();
        
        if(inst_id == 0)
        {
            Connection conn = null;
            ResultSet resultadoBanco = null;
            conn = DBConexao.abrirConexao();
            conn.setAutoCommit(false);
            
            String query3;
            String inst_num = null;
            query3 = "SELECT * FROM instalacao WHERE instalacao_numero = '"+numero_Instalacao+"'";
            Statement stm3 = conn.createStatement();
            resultadoBanco = stm3.executeQuery(query3);
            while(resultadoBanco.next()){ inst_num = resultadoBanco.getString("instalacao_numero");}
            
            if(inst_num != null)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERRO");
                alert.setHeaderText("Instalação ja cadastrada !");
                alert.showAndWait();
                
                txt_numeroInstalacao.requestFocus();
            }
            else{
                try {
                    PreparedStatement pstm = conn.prepareStatement("INSERT INTO instalacao(instalacao_numero, "
                            + "instalacao_apelido, instalacao_tipo, cliente_id) VALUES (?, ?, ?, ?)");
                    pstm.setString(1, numero_Instalacao);
                    pstm.setString(2, apelido_instalacao);
                    pstm.setString(3, tipo_instalacao);
                    pstm.setString(4, cliente_id);
                    
                    pstm.execute();
                    
                    Statement stm0 = conn.createStatement();
                    String query0;
                    query0 = "SELECT LAST_INSERT_ID();";
                    ResultSet resultado = stm0.executeQuery(query0);

                    while(resultado.next()){ instalacao_id = resultado.getInt("LAST_INSERT_ID()");}
                    
                    pstm.clearParameters();
                    
                    if (cbox_tipoInstalacao.getSelectionModel().getSelectedIndex() == 0) {
                        pstm = conn.prepareStatement("INSERT INTO instalacao_agua(instalacao_id, "
                                + "instalacao_agua_hidrometro, instalacao_agua_cod_sabesp, "
                                + "instalacao_agua_cod_cliente, instalacao_agua_economias, "
                                + "instalacao_agua_tipo_ligacao) VALUES (?, ?, ?, ?, ?, ?)");
                        pstm.setInt(1, instalacao_id);
                        pstm.setString(2, controllerAgua.getTxt_hidrometroAgua().getText());
                        pstm.setString(3, controllerAgua.getTxt_codsabespAgua().getText());
                        pstm.setString(4, controllerAgua.getTxt_codclienteAgua().getText());
                        pstm.setString(5, controllerAgua.getTxt_economiasAgua().getText());
                        pstm.setString(6, controllerAgua.getTxt_tipoLigacao().getText());
                        
                        pstm.execute();
                    } else {                                               
                        pstm = conn.prepareStatement("INSERT INTO instalacao_energia("
                                + "instalacao_id, instalacao_energia_codigo_identificacao, "
                                + "instalacao_energia_codigo_fiscal, instalacao_energia_grupo, "
                                + "instalacao_energia_subgrupo, instalacao_energia_classe, "
                                + "instalacao_energia_tipo_fornecimento, instalacao_energia_modalidade_tarifaria, "
                                + "instalacao_energia_tensao, instalacao_energia_roteiro_leitura, instalacao_energia_medidor) "
                                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                        pstm.setInt(1, Form_CadInstalacoesController.instalacao_id);
                        pstm.setString(2, controllerEnergia.getTxt_codIdentificacaoEnergia().getText());
                        pstm.setString(3, controllerEnergia.getTxt_codFiscalEnergia().getText());
                        pstm.setString(4, controllerEnergia.getTxt_grupoEnergia().getText());
                        pstm.setString(5, controllerEnergia.getTxt_subgrupoEnergia().getText());
                        pstm.setString(6, controllerEnergia.getTxt_classeEnergia().getText());
                        pstm.setString(7, controllerEnergia.getTxt_tipofornecimentoEnergia().getText());
                        pstm.setString(8, controllerEnergia.getTxt_modalidadeEnergia().getText());
                        pstm.setString(9, controllerEnergia.getTxt_tensaoEnergia().getText());
                        pstm.setString(10, controllerEnergia.getTxt_roteiroEnergia().getText());
                        pstm.setString(11, controllerEnergia.getTxt_medidorEnergia().getText());
                        
                        pstm.execute();
                                
                    }
                    
                    conn.commit();
                    
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Mensagem");
                    alert.setHeaderText("Dados salvos com sucesso !");
                    alert.showAndWait();
                    
                    Form_CadClienteController.alterInstalacaoId = 0;
        
                    Parent home_page_parent = FXMLLoader.load(getClass().getResource("Form_CadInstalacoes.fxml"));
                    Scene home_page_scene = new Scene(home_page_parent);
                    Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    app_stage.hide();
                    app_stage.setScene(home_page_scene);
                    app_stage.show();
                        
                } catch (SQLException e) {
                    e.printStackTrace();
                    conn.rollback();
                } finally {
                    conn.close();
                }
            }
            
        }else
        {
            Connection conn = null;
            ResultSet resultadoBanco = null;
            
            conn = DBConexao.abrirConexao();
            conn.setAutoCommit(false);   
                       
            try {
                PreparedStatement pstm = conn.prepareStatement("UPDATE instalacao SET "
                    + "instalacao_numero = ?, instalacao_apelido = ? WHERE instalacao_id = ?");
                pstm.setString(1, numero_Instalacao);
                pstm.setString(2, apelido_instalacao);
                pstm.setString(3, id_instalacao);

                pstm.execute();
                
                pstm.clearParameters();
                
                if (cbox_tipoInstalacao.getSelectionModel().getSelectedIndex() == 0) {
                    pstm = conn.prepareStatement("UPDATE instalacao_agua SET "
                            + "instalacao_agua_hidrometro = ?, instalacao_agua_cod_sabesp = ?, "
                            + "instalacao_agua_cod_cliente = ?, instalacao_agua_economias = ?, "
                            + "instalacao_agua_tipo_ligacao = ? WHERE instalacao_id = ?");
                    pstm.setString(1, controllerAgua.getTxt_hidrometroAgua().getText());
                    pstm.setString(2, controllerAgua.getTxt_codsabespAgua().getText());
                    pstm.setString(3, controllerAgua.getTxt_codclienteAgua().getText());
                    pstm.setString(4, controllerAgua.getTxt_economiasAgua().getText());
                    pstm.setString(5, controllerAgua.getTxt_tipoLigacao().getText());
                    pstm.setInt(6, inst_id);
                } else {
                    pstm = conn.prepareStatement("UPDATE instalacao_energia SET "
                            + "instalacao_energia_codigo_identificacao = ?, "
                            + "instalacao_energia_codigo_fiscal = ?, instalacao_energia_grupo = ?, "
                            + "instalacao_energia_subgrupo = ?, instalacao_energia_classe = ?, "
                            + "instalacao_energia_tipo_fornecimento = ?, instalacao_energia_modalidade_tarifaria = ?, "
                            + "instalacao_energia_tensao = ?, instalacao_energia_roteiro_leitura = ?, "
                            + "instalacao_energia_medidor = ? WHERE instalacao_id = ?");
                    pstm.setString(1, controllerEnergia.getTxt_codIdentificacaoEnergia().getText());
                    pstm.setString(2, controllerEnergia.getTxt_codFiscalEnergia().getText());
                    pstm.setString(3, controllerEnergia.getTxt_grupoEnergia().getText());
                    pstm.setString(4, controllerEnergia.getTxt_subgrupoEnergia().getText());
                    pstm.setString(5, controllerEnergia.getTxt_classeEnergia().getText());
                    pstm.setString(6, controllerEnergia.getTxt_tipofornecimentoEnergia().getText());
                    pstm.setString(7, controllerEnergia.getTxt_modalidadeEnergia().getText());
                    pstm.setString(8, controllerEnergia.getTxt_tensaoEnergia().getText());
                    pstm.setString(9, controllerEnergia.getTxt_roteiroEnergia().getText());
                    pstm.setString(10, controllerEnergia.getTxt_medidorEnergia().getText());
                    pstm.setInt(11, inst_id);
                }
                
                pstm.execute();
                conn.commit();
                
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Mensagem");
                alert.setHeaderText("Dados alterados com sucesso !");
                alert.showAndWait();
                        
            } catch (SQLException e) {
                e.printStackTrace();
                conn.rollback();
            } finally {
                conn.close();
            }
        }
    }
}
