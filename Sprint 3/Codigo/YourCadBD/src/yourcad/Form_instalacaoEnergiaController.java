/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yourcad;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import static yourcad.Form_CadClienteController.alterInstalacaoId;

/**
 * FXML Controller class
 *
 * @author MaXx
 */
public class Form_instalacaoEnergiaController implements Initializable {

    @FXML
    private TextField txt_codFiscalEnergia;
    @FXML
    private TextField txt_codIdentificacaoEnergia;
    @FXML
    private TextField txt_grupoEnergia;
    @FXML
    private TextField txt_subgrupoEnergia;
    @FXML
    private TextField txt_classeEnergia;
    @FXML
    private TextField txt_tipofornecimentoEnergia;
    @FXML
    private TextField txt_modalidadeEnergia;
    @FXML
    private TextField txt_roteiroEnergia;
    @FXML
    private TextField txt_tensaoEnergia;
    @FXML
    private TextField txt_medidorEnergia;
    @FXML
    private AnchorPane panelEnergia;
    @FXML
    private Button btn_salvarInstEnergia;
    @FXML
    private TextField txt_idInstalacao;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        int instalacao_id = Form_CadClienteController.alterInstalacaoId;
        
        if(instalacao_id != 0)
        {
            try{
                Connection conn = null;
                ResultSet resultadoBanco = null;
                conn = DBConexao.abrirConexao();
                Statement stm = conn.createStatement();

                //****** Selecionando tipo de instalação
                String sql0;
                sql0 = "SELECT * FROM instalacao_energia WHERE instalacao_id = " + alterInstalacaoId +";";
                resultadoBanco = stm.executeQuery(sql0);
                
                String inst_id = null;
                String inst_codIdentificacao = null;
                String inst_codFiscal = null;
                String inst_grupo = null;
                String inst_subGrupo = null;
                String inst_classe = null;
                String inst_tpFornecimento = null;
                String inst_modalidadeTarifa = null;
                String inst_tensao = null;
                String inst_roteiroLeitura = null;
                String inst_medidor = null;

                while(resultadoBanco.next())
                { 
                    inst_id = resultadoBanco.getString("instalacao_id"); 
                    inst_codIdentificacao = resultadoBanco.getString("instalacao_energia_codigo_identificacao"); 
                    inst_codFiscal = resultadoBanco.getString("instalacao_energia_codigo_fiscal"); 
                    inst_grupo = resultadoBanco.getString("instalacao_energia_grupo"); 
                    inst_subGrupo = resultadoBanco.getString("instalacao_energia_subgrupo"); 
                    inst_classe = resultadoBanco.getString("instalacao_energia_classe"); 
                    inst_tpFornecimento = resultadoBanco.getString("instalacao_energia_tipo_fornecimento"); 
                    inst_modalidadeTarifa = resultadoBanco.getString("instalacao_energia_modalidade_tarifaria"); 
                    inst_tensao = resultadoBanco.getString("instalacao_energia_tensao"); 
                    inst_roteiroLeitura = resultadoBanco.getString("instalacao_energia_roteiro_leitura"); 
                    inst_medidor = resultadoBanco.getString("instalacao_energia_medidor");
                }
                txt_classeEnergia.setText(inst_classe);
                txt_codFiscalEnergia.setText(inst_codFiscal);
                txt_codIdentificacaoEnergia.setText(inst_codIdentificacao);
                txt_grupoEnergia.setText(inst_grupo);
                txt_idInstalacao.setText(inst_id);
                txt_medidorEnergia.setText(inst_medidor);
                txt_modalidadeEnergia.setText(inst_modalidadeTarifa);
                txt_roteiroEnergia.setText(inst_roteiroLeitura);
                txt_subgrupoEnergia.setText(inst_subGrupo);
                txt_tensaoEnergia.setText(inst_tensao);
                txt_tipofornecimentoEnergia.setText(inst_tpFornecimento);
                                
            }catch (Exception ex) { Logger.getLogger(Form_CadClienteController.class.getName()).log(Level.SEVERE, null, ex);}
        }
        
    }    

    public TextField getTxt_codFiscalEnergia() {
        return txt_codFiscalEnergia;
    }

    public void setTxt_codFiscalEnergia(TextField txt_codFiscalEnergia) {
        this.txt_codFiscalEnergia = txt_codFiscalEnergia;
    }

    public TextField getTxt_codIdentificacaoEnergia() {
        return txt_codIdentificacaoEnergia;
    }

    public void setTxt_codIdentificacaoEnergia(TextField txt_codIdentificacaoEnergia) {
        this.txt_codIdentificacaoEnergia = txt_codIdentificacaoEnergia;
    }

    public TextField getTxt_grupoEnergia() {
        return txt_grupoEnergia;
    }

    public void setTxt_grupoEnergia(TextField txt_grupoEnergia) {
        this.txt_grupoEnergia = txt_grupoEnergia;
    }

    public TextField getTxt_subgrupoEnergia() {
        return txt_subgrupoEnergia;
    }

    public void setTxt_subgrupoEnergia(TextField txt_subgrupoEnergia) {
        this.txt_subgrupoEnergia = txt_subgrupoEnergia;
    }

    public TextField getTxt_classeEnergia() {
        return txt_classeEnergia;
    }

    public void setTxt_classeEnergia(TextField txt_classeEnergia) {
        this.txt_classeEnergia = txt_classeEnergia;
    }

    public TextField getTxt_tipofornecimentoEnergia() {
        return txt_tipofornecimentoEnergia;
    }

    public void setTxt_tipofornecimentoEnergia(TextField txt_tipofornecimentoEnergia) {
        this.txt_tipofornecimentoEnergia = txt_tipofornecimentoEnergia;
    }

    public TextField getTxt_modalidadeEnergia() {
        return txt_modalidadeEnergia;
    }

    public void setTxt_modalidadeEnergia(TextField txt_modalidadeEnergia) {
        this.txt_modalidadeEnergia = txt_modalidadeEnergia;
    }

    public TextField getTxt_roteiroEnergia() {
        return txt_roteiroEnergia;
    }

    public void setTxt_roteiroEnergia(TextField txt_roteiroEnergia) {
        this.txt_roteiroEnergia = txt_roteiroEnergia;
    }

    public TextField getTxt_tensaoEnergia() {
        return txt_tensaoEnergia;
    }

    public void setTxt_tensaoEnergia(TextField txt_tensaoEnergia) {
        this.txt_tensaoEnergia = txt_tensaoEnergia;
    }

    public TextField getTxt_medidorEnergia() {
        return txt_medidorEnergia;
    }

    public void setTxt_medidorEnergia(TextField txt_medidorEnergia) {
        this.txt_medidorEnergia = txt_medidorEnergia;
    }

    @FXML
    private void salvarInstEnergia(ActionEvent event) throws SQLException, Exception {
        
        int inst_id = Form_CadClienteController.alterInstalacaoId;
        
        String codigoIdentificacao = txt_codIdentificacaoEnergia.getText();
        String codigoFiscal = txt_codFiscalEnergia.getText();
        String grupo = txt_codIdentificacaoEnergia.getText();
        String subgrupo = txt_subgrupoEnergia.getText();
        String classe = txt_classeEnergia.getText();
        String tipoFornecimento = txt_tipofornecimentoEnergia.getText();
        String modalidade = txt_modalidadeEnergia.getText();
        String tensao = txt_tensaoEnergia.getText();
        String roteiro = txt_roteiroEnergia.getText();
        String medidor = txt_medidorEnergia.getText();
        String id_inst = txt_idInstalacao.getText();
       
        if(inst_id == 0)
        {
            Connection conn = null;
            conn = DBConexao.abrirConexao();
            Statement stm = conn.createStatement();
            String query1;
            query1 = "INSERT INTO instalacao_energia(instalacao_id," +
                         "instalacao_energia_codigo_identificacao,\n" +
                         "instalacao_energia_codigo_fiscal,\n" +
                         "instalacao_energia_grupo,\n" +
                         "instalacao_energia_subgrupo,\n" +
                         "instalacao_energia_classe,\n" +
                         "instalacao_energia_tipo_fornecimento,\n" +
                         "instalacao_energia_modalidade_tarifaria,\n" +
                         "instalacao_energia_tensao,\n" +
                         "instalacao_energia_roteiro_leitura,\n" +
                         "instalacao_energia_medidor) VALUES "
                        + "('"+ Form_CadInstalacoesController.instalacao_id +"','"
                        + codigoIdentificacao +"','"
                        + codigoFiscal +"','"
                        + grupo +"','"
                        + subgrupo +"','"
                        + classe +"','"
                        + tipoFornecimento +"','"
                        + modalidade +"','"
                        + tensao +"','"
                        + roteiro +"','"
                        + medidor +"');";
                stm.executeUpdate(query1);
        }else
        {
            Connection conn = null;
            ResultSet resultadoBanco = null;
                     
            conn = DBConexao.abrirConexao();
            Statement stm = conn.createStatement();

            String sql;
            sql = "UPDATE instalacao_energia SET "
                    + "instalacao_energia_codigo_identificacao = '"+ codigoIdentificacao +"', "
                    + "instalacao_energia_codigo_fiscal = '"+ codigoFiscal +"', "
                    + "instalacao_energia_grupo = '"+ grupo +"', "
                    + "instalacao_energia_subgrupo = '"+ subgrupo +"', "
                    + "instalacao_energia_classe = '"+ classe +"', "
                    + "instalacao_energia_tipo_fornecimento = '"+ tipoFornecimento +"', "
                    + "instalacao_energia_modalidade_tarifaria = '"+ modalidade +"', "
                    + "instalacao_energia_tensao = '"+ tensao +"', "
                    + "instalacao_energia_roteiro_leitura = '"+ roteiro +"', "
                    + "instalacao_energia_medidor = '"+ medidor +"' "            
                    + "WHERE instalacao_id = "+ id_inst +";";

            stm.executeUpdate(sql);
        }
    }
    
}
