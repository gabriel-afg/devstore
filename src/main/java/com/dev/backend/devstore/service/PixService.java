package com.dev.backend.devstore.service;

import br.com.efi.efisdk.EfiPay;
import br.com.efi.efisdk.exceptions.EfiPayException;
import com.dev.backend.devstore.domain.pix.PixChargeRequestDTO;
import com.dev.backend.devstore.pix.Credentials;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PixService {

    @Value("${CLIENT_ID}")
    private String clientId;

    @Value("${CLIENT_SECRET}")
    private String clientSecret;

    public JSONObject pixCreateEVP(){

        JSONObject options = configuringJsonObject();

        try {
            EfiPay efi = new EfiPay(options);
            JSONObject response = efi.call("pixCreateEvp", new HashMap<String,String>(), new JSONObject());
            System.out.println(response.toString());
            return response;
        }catch (EfiPayException e){
            System.out.println(e.getError());
            System.out.println(e.getErrorDescription());
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public JSONObject pixCreateCharge(PixChargeRequestDTO pixChargeRequestDTO){
        JSONObject options = configuringJsonObject();

        JSONObject body = new JSONObject();
        body.put("calendario", new JSONObject().put("expiracao", 300)); //5 minutos
        //body.put("devedor", new JSONObject().put("cpf", "12345678909").put("nome", "Francisco da Silva"));
        body.put("valor", new JSONObject().put("original", pixChargeRequestDTO.valor()));
        body.put("chave", pixChargeRequestDTO.chave());

        //JSONArray infoAdicionais = new JSONArray();
        //infoAdicionais.put(new JSONObject().put("nome", "Campo 1").put("valor", "Informação Adicional1 do PSP-Recebedor"));
        //infoAdicionais.put(new JSONObject().put("nome", "Campo 2").put("valor", "Informação Adicional2 do PSP-Recebedor"));
        //body.put("infoAdicionais", infoAdicionais);

        try {
            EfiPay efi = new EfiPay(options);
            JSONObject response = efi.call("pixCreateImmediateCharge", new HashMap<String,String>(), body);
            int idFromJson= response.getJSONObject("loc").getInt("id");
            String qrCodeImage = pixGenerateQRCode(String.valueOf(idFromJson));

            response.put("qrCodeImage", qrCodeImage);

            return response;

        }catch (EfiPayException e){
            System.out.println(e.getError());
            System.out.println(e.getErrorDescription());
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private String pixGenerateQRCode(String id){
        JSONObject options = configuringJsonObject();

        HashMap<String, String> params = new HashMap<String, String>();
        params.put("id", id);

        try {
            EfiPay efi= new EfiPay(options);
            Map<String, Object> response = efi.call("pixGenerateQRCode", params, new HashMap<String, Object>());

            System.out.println(response);

            return (String) response.get("imagemQrcode");

        }catch (EfiPayException e){
            System.out.println(e.getError());
            System.out.println(e.getErrorDescription());
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public JSONObject listEvpKeys(){

        JSONObject options = configuringJsonObject();

        try {
            EfiPay efi = new EfiPay(options);
            JSONObject response = efi.call("pixListEvp", new HashMap<String,String>(), new JSONObject());
            System.out.println(response);

            return response;
        }catch (EfiPayException e){
            System.out.println(e.getError());
            System.out.println(e.getErrorDescription());
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void deleteKeyEVP(String chave){
        JSONObject options = configuringJsonObject();

        HashMap<String, String> params = new HashMap<String, String>();
        params.put("chave", chave);

        try {
            EfiPay efi = new EfiPay(options);

            efi.call("pixDeleteEvp", params, new HashMap<String, Object>());

        }catch (EfiPayException e){
            System.out.println(e.getError());
            System.out.println(e.getErrorDescription());
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Map<String, Object> pixListCharges(){
        JSONObject options = configuringJsonObject();

        HashMap<String, String> params = new HashMap<String, String>();
        params.put("inicio", "2024-01-01T16:01:35Z");
        params.put("fim", "2024-02-01T16:01:35Z");

        try {
            EfiPay efi= new EfiPay(options);
            Map<String, Object> response = efi.call("pixListCharges", params, new HashMap<String, Object>());

            return response;
        }catch (EfiPayException e){
            System.out.println(e.getError());
            System.out.println(e.getErrorDescription());
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Map<String, Object> pixChargeDetail(String txid){
        JSONObject options = configuringJsonObject();

        HashMap<String, String> params = new HashMap<String, String>();
        params.put("txid", txid);

        try {
            EfiPay efi= new EfiPay(options);
            Map<String, Object> response = efi.call("pixDetailCharge", params, new HashMap<String, Object>());

            String idFromJson = String.valueOf(((Map<String, Object>)response.get("loc")).get("id"));
            String qrCodeImage = pixGenerateQRCode(idFromJson);

            response.put("qrCodeImage", qrCodeImage);

            return response;
        }catch (EfiPayException e){
            System.out.println(e.getError());
            System.out.println(e.getErrorDescription());
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private JSONObject configuringJsonObject(){
        Credentials credentials = new Credentials();

        JSONObject options = new JSONObject();
        options.put("client_id", clientId);
        options.put("client_secret", clientSecret);
        options.put("certificate", credentials.getCertificate());
        options.put("sandbox", credentials.isSandbox());

        return options;
    }
}
