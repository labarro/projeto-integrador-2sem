/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yourcad;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import static yourcad.Form_CadInstalacoesController.instalacao_id;



/**
 * FXML Controller class
 *
 * @author MaXx
 */
public class Form_cadContaEnergiaController implements Initializable {

    @FXML
    private Pane panel_contaEnergia;
    @FXML
    private TextField txt_EnergiaEmissao;
    @FXML
    private TextField txt_EnergiaLeituraAtual;
    @FXML
    private TextField txt_EnergiaDiasFaturamento;
    @FXML
    private TextField txt_EnergiaCor;
    @FXML
    private TextField txt_EnergiaPeriodo;
    @FXML
    private TextField txt_EnergiaPeriodo2;
    @FXML
    private TextField txt_EnergiaVencimento;
    @FXML
    private TextField txt_EnergiaConsumoMes;
    @FXML
    private TextField txt_EnergiaCompetencia;
    @FXML
    private TextField txt_EnergiaValor;
    @FXML
    private TextField txt_EnergiaQuantidade;
    @FXML
    private TextField txt_EnergiaTarifaImposto;
    @FXML
    private TextField txt_EnergiaValorIcms;
    @FXML
    private TextField txt_EnergiaValorPis;
    @FXML
    private TextField txt_EnergiaAliquotaPis;
    @FXML
    private TextField txt_EnergiaAliquotaIcms;
    @FXML
    private TextField txt_EnergiaValorFornecedor;
    @FXML
    private TextField txt_EnergiaDescricaoProduto;
    @FXML
    private TextField txt_EnergiaBasePisCofins;
    @FXML
    private TextField txt_EnergiaBaseICMS;
    @FXML
    private TextField txt_EnergiaTarifaAplicada;
    @FXML
    private TextField txt_EnergiaCci;
    @FXML
    private TextField txt_EnergiaKwhMes;
    @FXML
    private TextField txt_EnergiaConstMult;
    @FXML
    private TextField txt_EnergiaNMedidor;
    @FXML
    private TextField txt_EnergiaConsumoLeituraAtual;
    @FXML
    private TextField txt_EnergiaDescricao;
    @FXML
    private Button btn_salvarContaEnergia;
    @FXML
    private Button btn_limparContaEnergia;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
    }    

    @FXML
    private void salvarEnergia(ActionEvent event) throws Exception {
        
        String instalacao_id = Form_CadContaController.contaInstalacaoId;
        String instalacao_numero = Form_CadContaController.contaInstalacaoNum;
        String cliente_id = Form_CadContaController.clienteId;
        String conta_aliqIcms = txt_EnergiaAliquotaIcms.getText();
        String conta_aliqPis = txt_EnergiaAliquotaPis.getText();
        String conta_baseIcms = txt_EnergiaBaseICMS.getText();
        String conta_basePis = txt_EnergiaBasePisCofins.getText();
        String conta_cci = txt_EnergiaCci.getText();
        String conta_competencia = txt_EnergiaCompetencia.getText();
        String conta_constMult = txt_EnergiaConstMult.getText();
        String conta_consLeitAtual = txt_EnergiaConsumoLeituraAtual.getText();
        String conta_consMes = txt_EnergiaConsumoMes.getText();
        String conta_bandCor = txt_EnergiaCor.getText();
        String conta_consDescricao = txt_EnergiaDescricao.getText();
        String conta_fatDescProduto = txt_EnergiaDescricaoProduto.getText();
        String conta_fatDias = txt_EnergiaDiasFaturamento.getText();
        String conta_fatEmissao = txt_EnergiaEmissao.getText();
        String conta_consKwh = txt_EnergiaKwhMes.getText();
        String conta_fatLeitAtual = txt_EnergiaLeituraAtual.getText();
        String conta_consMedidor = txt_EnergiaNMedidor.getText();
        String conta_bandInicio = txt_EnergiaPeriodo.getText();
        String conta_bandFim = txt_EnergiaPeriodo2.getText();
        String conta_fatQtd = txt_EnergiaQuantidade.getText();
        String conta_fatTariAplicada = txt_EnergiaTarifaAplicada.getText();
        String conta_fatTariImposto = txt_EnergiaTarifaImposto.getText();
        String conta_valor = txt_EnergiaValor.getText();
        String conta_valorFornecido = txt_EnergiaValorFornecedor.getText();
        String conta_valorIcms = txt_EnergiaValorIcms.getText();
        String conta_valorPis = txt_EnergiaValorPis.getText();
        String conta_Vencimento = txt_EnergiaVencimento.getText();
        int conta_id = 0;
        
        Connection conn = null;
        conn = DBConexao.abrirConexao();
        
        Statement stm0 = conn.createStatement(); 
        String query0;
        query0 = "INSERT INTO conta(instalacao_id, cliente_id, conta_numero_instalacao, conta_tipo) VALUES "+
                "("+ instalacao_id +", "
                   + cliente_id +", '"
                   + instalacao_numero +"', '"
                + "Energia');";
        stm0.executeUpdate(query0);

        Statement stm1 = conn.createStatement();
        String query1;
        query1 = "SELECT LAST_INSERT_ID();";
        ResultSet resultado = stm1.executeQuery(query1);
        while(resultado.next()){ conta_id = resultado.getInt("LAST_INSERT_ID()"); }
  
        Statement stm = conn.createStatement();      
        String query;
        query = "INSERT INTO conta_energia(conta_id, conta_energia_valor, conta_energia_competencia, conta_energia_consumo_mes, " +
                    "conta_energia_vencimento, conta_energia_bandeira_cor, conta_energia_bandeira_periodoini, " +
                    "conta_energia_bandeira_periodo_fim, conta_energia_faturamento_emissao, conta_energia_faturamento_leitatual," +
                    "conta_energia_faturamento_dias, conta_energia_faturamento_cci, conta_energia_faturamento_produto, " +
                    "conta_energia_faturamento_qtd, conta_energia_faturamento_tarifa, conta_energia_faturamento_valorfornecido, " +
                    "conta_energia_faturamento_tarifaimposto, conta_energia_faturamento_baseicms, conta_energia_faturamento_aliqicms, " +
                    "conta_energia_faturamento_valoricms, conta_energia_faturamento_basepis, conta_energia_faturamento_aliqpis, " +
                    "conta_energia_faturamento_valorpis, conta_energia_consumo_descricao, conta_energia_consumo_medidor, " +
                    "conta_energia_consumo_leitatual, conta_energia_consumo_constmult, conta_energia_consumo_kwh) VALUES"
                  +  "("+ conta_id +",'"
                        + conta_valor +"','"
                        + conta_competencia +"','"
                        + conta_consMes +"','"
                        + conta_Vencimento +"','"
                        + conta_bandCor +"','"
                        + conta_bandInicio +"','"
                        + conta_bandFim +"','"
                        + conta_fatEmissao +"','"
                        + conta_fatLeitAtual +"','"
                        + conta_fatDias +"','"
                        + conta_cci +"','"
                        + conta_fatDescProduto +"','"
                        + conta_fatQtd +"','"
                        + conta_fatTariAplicada +"','"
                        + conta_valorFornecido +"','"
                        + conta_fatTariImposto +"','"
                        + conta_baseIcms +"','"
                        + conta_aliqIcms +"','"
                        + conta_valorIcms +"','"
                        + conta_basePis +"','"
                        + conta_aliqPis +"','"
                        + conta_valorPis +"','"
                        + conta_consDescricao +"','"
                        + conta_consMedidor +"','"
                        + conta_consLeitAtual +"','"
                        + conta_constMult +"','"
                        + conta_consKwh +"');";
        
//          System.out.println(query);  // TESTE DE FUNCIONAMENTO
            stm.executeUpdate(query);
            System.out.println("Dados Cadastrados com sucesso!!!");
              
        
    }

    @FXML
    private void limparContaEnergia(ActionEvent event) {
    }
    
}
