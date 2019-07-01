package it.monaco.medical.service.controllers;


import io.swagger.annotations.ApiParam;
import it.monaco.medical.service.controllers.dtos.*;
import it.monaco.medical.service.model.enums.EnumChannel;
import it.monaco.medical.service.model.enums.EnumTipiAlimentazione;
import it.monaco.medical.service.model.enums.ErrorCode;
import it.monaco.medical.service.model.enums.ErrorType;
import it.monaco.medical.service.model.exceptions.LegacyException;
import it.monaco.medical.service.services.QuattroRuoteService;
import it.monaco.medical.service.utils.LegacyDateUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping(value ="/quattroruote/vehicle")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class QuattroRuoteController extends LegacyBaseController{

    public static Logger logger = LoggerFactory.getLogger(QuattroRuoteController.class);

    @Autowired
    private QuattroRuoteService quattroRuoteService;

    @GetMapping({"/vehicle-makers"})
    public ResponseEntity<QuattroRuoteResponse> getVehicleMakers(
            @ApiParam(required = true, allowableValues = "WEB_SITE,MOBILE_SITE,POS_SITE,UIMX") @RequestParam("channel") EnumChannel channel,
            @ApiParam(required = true, value = "host") @RequestParam("host") String host,
            @ApiParam(required = true, value = "sessionId") @RequestParam("session") String session,
            @ApiParam(required = true, value = "Immatricolazione veicolo nel formato YYYYMM") @RequestParam("matriculation") Long matriculation,
            @ApiParam(required = true, value = "Data di riferimento nel formato YYYYMMDD") @RequestParam("referenceDate") String referenceDate,
            @ApiParam(required = false, value = "Codice omologazione letto da ANIA") @RequestParam(name="aniaOmologationCode",  required=false) String aniaOmologationCode
    )

    {
        setParamsForLog(session, channel, host);
        logger.info(String.format(apiURL()+"/vehicle-makers/. Input Params : [sessionId = %s], [channel = %s] [host = %s], [matriculation = %s], [referenceDate = %s], [aniaOmologationCode = %s] ", session, channel.getChannel(), host, matriculation, referenceDate, aniaOmologationCode));

        QuattroRuoteResponse response = null;
        List<VehicleMakerDTO> vehicleMakerDTOList = null;

        try
        {
            vehicleMakerDTOList =  quattroRuoteService.getVehicleMakers(matriculation, referenceDate, aniaOmologationCode);

            if(CollectionUtils.isEmpty(vehicleMakerDTOList)) {
                throw new LegacyException("No vehcile maker found for inputs: [matriculation = "+matriculation+"],[referenceDate = "+referenceDate+"],[aniaOmologationCode = "+aniaOmologationCode+"].",  ErrorType.DATABASE, ErrorCode.QTR_003_VEHICLE_MAKERS_NOT_FOUND);
            }

            response = new QuattroRuoteResponse(HttpStatus.OK);
            response.setMakers(vehicleMakerDTOList);

        }
        catch(LegacyException lex)
        {
            logger.error(apiURL()+"/vehicle-makers"+lex.getMessage(), lex);
            response = new QuattroRuoteResponse(HttpStatus.INTERNAL_SERVER_ERROR);
            ErrorCode errorCode = lex.getErrorCode()!= null ? lex.getErrorCode() : ErrorCode.LEG_999_UNEXPECTED_ERROR;
            ErrorDTO error = new ErrorDTO(errorCode, lex.getMessage(), lex.getLocalizedMessage());
            response.setError(error);
            return new ResponseEntity<QuattroRuoteResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }

        catch(Exception ex)
        {
            logger.error(apiURL()+"/vehicle-makers"+ex.getMessage(), ex);
            response = new QuattroRuoteResponse(HttpStatus.INTERNAL_SERVER_ERROR);
            ErrorDTO error = new ErrorDTO(ErrorCode.QTR_999_VEHICLE_MAKERS_UNEXPECTED_ERROR, ex.getMessage(), ex.getLocalizedMessage());
            response.setError(error);
            return new ResponseEntity<QuattroRuoteResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }

        return new ResponseEntity<QuattroRuoteResponse>(response, HttpStatus.OK);
    }


    @GetMapping("/vehicle-models")
    public ResponseEntity getVehicleModels(
            @ApiParam(required = false, allowableValues = "WEB_SITE,MOBILE_SITE,POS_SITE,UIMX") @RequestParam("channel") EnumChannel channel,
            @ApiParam(required = false, value = "host") @RequestParam("host") String host,
            @ApiParam(required = false, value = "sessionId") @RequestParam("session") String session,
            @ApiParam(required = true, value = "Codice marca") @RequestParam("makeCode") String makeCode,
            @ApiParam(required = true, value = "Immatricolazione veicolo nel formato YYYYMM") @RequestParam("matriculation") Long matriculation,
            @ApiParam(required = true, value = "Data di riferimento nel formato YYYYMMDD") @RequestParam("referenceDate") String referenceDate,
            @ApiParam(required = false, value = "Codice omologazione letto da ANIA") @RequestParam(value = "aniaOmologationCode", required = false) String aniaOmologationCode
    ) {

        setParamsForLog(session, channel, host);
        logger.info(String.format(apiURL()+"/vehicle-models/. Input Params : [sessionId = %s], [channel = %s] [host = %s], [matriculation = %s], [referenceDate = %s], [aniaOmologationCode = %s] ", session, channel.getChannel(), host, matriculation, referenceDate, aniaOmologationCode));

        QuattroRuoteResponse response = null;
        List<VehicleModelDTO> vehicleModelDTOList = null;

        try {
            vehicleModelDTOList = quattroRuoteService.getVehicleModels(matriculation, makeCode, LegacyDateUtils
                    .toDate(referenceDate, LegacyDateUtils.PATTERN_FROM), aniaOmologationCode);

            if (CollectionUtils.isEmpty(vehicleModelDTOList)) {
                throw new LegacyException("No vehicle model found for inputs.", ErrorType.DATABASE,
                                          ErrorCode.QTR_005_VEHICLE_MODEL_NOT_FOUND);
            }

            response = new QuattroRuoteResponse(HttpStatus.OK);
            response.setModels(vehicleModelDTOList);

        } catch (LegacyException lex) {
            logger.error(apiURL() + "/vehicle-models : " + lex.getMessage(), lex);
            response = new QuattroRuoteResponse(HttpStatus.INTERNAL_SERVER_ERROR);
            ErrorCode errorCode = lex.getErrorCode();
            ErrorDTO error = new ErrorDTO(errorCode, lex.getMessage(), lex.getLocalizedMessage());
            response.setError(error);
            return new ResponseEntity<QuattroRuoteResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        } catch (Exception ex) {
            logger.error(apiURL() + "/vehicle-models :  " + ex.getMessage(), ex);
            response = new QuattroRuoteResponse(HttpStatus.INTERNAL_SERVER_ERROR);
            ErrorDTO error = new ErrorDTO(ErrorCode.QTR_998_VEHICLE_MODELS_UNEXPECTED_ERROR, ex.getMessage(),
                                          ex.getLocalizedMessage());
            response.setError(error);
            return new ResponseEntity<QuattroRuoteResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }

        return new ResponseEntity<QuattroRuoteResponse>(response, HttpStatus.OK);

    }



    @GetMapping("/vehicle-supplies")
    public ResponseEntity<QuattroRuoteResponse> getVehicleSupplies(
            @ApiParam(required = true, allowableValues = "WEB_SITE,MOBILE_SITE,POS_SITE,UIMX") @Validated @RequestParam("channel") EnumChannel channel,
            @ApiParam(required = true, value = "host")@Validated @RequestParam("host") String host,
            @ApiParam(required = true, value = "sessionId") @Validated @RequestParam("session") String session,
            @ApiParam(required = true, value = "Codice marca") @Validated @RequestParam("makeCode") String makeCode,
            @ApiParam(required = true, value = "Descrizione modello") @Validated @RequestParam("modelName") String modelName,
            @ApiParam(required = true, value = "Immatricolazione veicolo nel formato YYYYMM") @Validated @RequestParam("matriculation") Long matriculation,
            @ApiParam(required = true, value = "Data di riferimento nel formato YYYYMMDD") @Validated @RequestParam("referenceDate") String referenceDate,
            @ApiParam(required = false, value = "Codice omologazione letto da ANIA") @Validated @RequestParam(value = "aniaOmologationCode", required = false) String aniaOmologationCode
    ) {

        setParamsForLog(session, channel, host);
        logger.info(String.format(apiURL() +
                                  "/vehicle-supplies. Input Params : [sessionId = %s], [channel = %s] [host = %s], [makeCode = %s], [modelName = %s], [matriculation = %s], [referenceDate = %s], [aniaOmologationCode = %s] ",
                                  session, channel.getChannel(), host, makeCode, modelName, matriculation,
                                  referenceDate, aniaOmologationCode));

        QuattroRuoteResponse response = null;
        List<FuelTypeDTO> fuelTypeDTOList = null;

        try {

            fuelTypeDTOList = quattroRuoteService.getFuelTypes(makeCode, modelName, matriculation, LegacyDateUtils
                    .toDate(referenceDate, LegacyDateUtils.PATTERN_FROM), aniaOmologationCode);
            response = new QuattroRuoteResponse(HttpStatus.OK);
            response.setSupplies(fuelTypeDTOList);

        } catch (LegacyException lex) {
            logger.error(apiURL() + "/vehicle-supplies" + lex.getMessage(), lex);
            response = new QuattroRuoteResponse(HttpStatus.INTERNAL_SERVER_ERROR);
            ErrorCode errorCode = lex.getErrorCode();
            ErrorDTO error = new ErrorDTO(errorCode, lex.getMessage(), lex.getLocalizedMessage());
            response.setError(error);
            return new ResponseEntity<QuattroRuoteResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);


        } catch (Exception ex) {
            logger.error(apiURL() + "/vehicle-supplies : " + ex.getMessage(), ex);
            response = new QuattroRuoteResponse(HttpStatus.INTERNAL_SERVER_ERROR);
            ErrorDTO error = new ErrorDTO(ErrorCode.QTR_997_VEHICLE_SUPPLIES_UNEXPECTED_ERROR, ex.getMessage(),
                                          ex.getLocalizedMessage());
            response.setError(error);
            return new ResponseEntity<QuattroRuoteResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<QuattroRuoteResponse>(response, HttpStatus.OK);
    }



    @GetMapping("/vehicle-data-optional")
    public ResponseEntity<QuattroRuoteResponse> getVehicleDataOptional(
            @ApiParam(required = true, allowableValues = "WEB_SITE,MOBILE_SITE,POS_SITE,UIMX") @Validated @RequestParam("channel") EnumChannel channel,
            @ApiParam(required = true, value = "host")  @Validated @RequestParam("host") String host,
            @ApiParam(required = true, value = "sessionId")  @Validated @RequestParam("session") String session,
            @ApiParam(required = true, value = "Infocar")  @Validated @RequestParam("infocar") Long infocar,
            @ApiParam(required = true, value = "Data inserimento in quattro ruote nel formato YYYYMM")  @Validated @RequestParam("qtrDate") Long qtrDate,
            @ApiParam(required = true, value = "Immatricolazione veicolo nel formato YYYYMM")  @Validated @RequestParam("matriculation") Long matriculation,
            @ApiParam(required = true, value = "Data di riferimento nel formato YYYYMMDD")  @Validated @RequestParam("referenceDate") String referenceDate
    ) {

        setParamsForLog(session, channel, host);
        logger.info(String.format(apiURL() +
                                  "/vehicle-data-optional. Input Params : [sessionId = %s], [channel = %s] [host = %s], [infocar = %s], [qtrDate = %s], [matriculation = %s], [referenceDate = %s]",
                                  session, channel.getChannel(), host, infocar, qtrDate, matriculation, referenceDate));

        QuattroRuoteResponse response = null;
        VehicleDTO vehicleDTO = null;

        try {
            vehicleDTO = quattroRuoteService.getVehicleDataOptional(infocar, qtrDate, LegacyDateUtils
                    .toDate(referenceDate, LegacyDateUtils.PATTERN_FROM), matriculation);
            response = new QuattroRuoteResponse(HttpStatus.OK);
            response.setVehicle(vehicleDTO);

        } catch (LegacyException lex) {
            logger.error(apiURL() + "/vehicle-data-optional" + lex.getMessage(), lex);
            response = new QuattroRuoteResponse(HttpStatus.INTERNAL_SERVER_ERROR);
            ErrorCode errorCode = lex.getErrorCode();
            ErrorDTO error = new ErrorDTO(errorCode, lex.getMessage(), lex.getLocalizedMessage());
            response.setError(error);
            return new ResponseEntity<QuattroRuoteResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        } catch (Exception ex) {
            logger.error(apiURL() + "/vehicle-data-optional" + ex.getMessage(), ex);
            response = new QuattroRuoteResponse(HttpStatus.INTERNAL_SERVER_ERROR);
            ErrorDTO error = new ErrorDTO(ErrorCode.QTR_995_VEHICLE_DATA_OPTIONAL_UNEXPECTED_ERROR, ex.getMessage(),
                                          ex.getLocalizedMessage());
            response.setError(error);
            return new ResponseEntity<QuattroRuoteResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<QuattroRuoteResponse>(response, HttpStatus.OK);
    }


    @GetMapping("/vehicle-setup")
    public ResponseEntity<QuattroRuoteResponse> getVehicleSetUp(
            @ApiParam(required = false, allowableValues = "WEB_SITE,MOBILE_SITE,POS_SITE,UIMX") @QueryParam("channel") EnumChannel channel,
            @ApiParam(required = false, value = "host") @RequestParam("host") String host,
            @ApiParam(required = false, value = "sessionId") @RequestParam("session") String session,
            @ApiParam(required = true, value = "Codice marca") @RequestParam("makeCode") String makeCode,
            @ApiParam(required = true, value = "Descrizione modello") @RequestParam("modelName") String modelName,
            @ApiParam(required = true, value = "Tipo alimentazione") @RequestParam("supplyCode") EnumTipiAlimentazione supplyCode,
            @ApiParam(required = false, value = "Potenza") @RequestParam(value = "kw", required = false) String kw,
            @ApiParam(required = false, value = "Cavalli fiscali") @RequestParam(value = "taxableHorsePower", required = false) String taxableHorsePower,
            @ApiParam(required = true, value = "Immatricolazione veicolo nel formato YYYYMM") @RequestParam("matriculation") Long matriculation,
            @ApiParam(required = true, value = "Data di riferimento nel formato YYYYMMDD") @RequestParam("referenceDate") String referenceDate,
            @ApiParam(required = false, value = "Codice omologazione letto da ANIA") @RequestParam(value = "aniaOmologationCode", required = false) String aniaOmologationCode
    ) {


        setParamsForLog(session, channel, host);
        logger.info(String.format(apiURL() +
                                  "/vehicle-setup. Input Params : [sessionId = %s], [channel = %s] [host = %s], [makeCode = %s], [modelName = %s], [supplyCode = %s], [referenceDate = %s], [aniaOmologationCode = %s] ",
                                  session, channel.getChannel(), host, makeCode, modelName, supplyCode, matriculation,
                                  referenceDate, aniaOmologationCode));

        QuattroRuoteResponse response = null;
        String cc = null;
        List<SetupDTO> setups;

        try {

            if (taxableHorsePower == null) {
                setups = quattroRuoteService.getSetups(modelName, makeCode, supplyCode, LegacyDateUtils
                        .toDate(referenceDate, LegacyDateUtils.PATTERN_FROM), matriculation, aniaOmologationCode);
            } else {
                setups = quattroRuoteService.getRenewalSetups(modelName, makeCode, supplyCode, kw, taxableHorsePower,
                                                              LegacyDateUtils.toDate(referenceDate,
                                                                                     LegacyDateUtils.PATTERN_FROM),
                                                              matriculation, aniaOmologationCode);
            }


            response = new QuattroRuoteResponse(HttpStatus.OK);
            response.setSetups(setups);


        }
        catch (LegacyException lex)
        {
            logger.error(apiURL()+"/vehicle-setup :"+lex.getMessage(), lex);
            response = new QuattroRuoteResponse(HttpStatus.INTERNAL_SERVER_ERROR);
            ErrorCode errorCode = lex.getErrorCode();
            ErrorDTO error = new ErrorDTO(errorCode, lex.getMessage(), lex.getLocalizedMessage());
            response.setError(error);
            return new ResponseEntity<QuattroRuoteResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (Exception ex)
        {
            logger.error(apiURL()+"/vehicle-setup : "+ex.getMessage(), ex);
            response = new QuattroRuoteResponse(HttpStatus.INTERNAL_SERVER_ERROR);
            ErrorDTO error = new ErrorDTO(ErrorCode.QTR_996_VEHICLE_SETUP_UNEXPECTED_ERROR, ex.getMessage(),
                                          ex.getLocalizedMessage());
            response.setError(error);
            return new ResponseEntity<QuattroRuoteResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }


        return new ResponseEntity<QuattroRuoteResponse>(response, HttpStatus.OK);

    }

    //TODO: In futuro verrà cancellato o spostato nel controller di manual entry
    @GetMapping({"/login"})
    public String getLogin(
        @ApiParam(value = "username") @RequestParam("username") String username,
        @ApiParam(value = "password") @RequestParam("password") String password
    ) {
    /*    LoginQRResponse response = null;
        try{
            response = quattroRuoteService.getLogin(username, password);

        } catch(Exception e) {
            logger.error("Login error!", e);
        }

        return new ResponseEntity<LoginQRResponse>(response, HttpStatus.OK); */

        return username;

    }

    @GetMapping("/vehicle-data-optional-by-omologation-code")
    public ResponseEntity<QuattroRuoteResponse> getVehicleDataOptionalByOmologationCode(
            @RequestParam("channel") EnumChannel channel, @RequestParam("host") String host,
            @RequestParam("session") String session, @RequestParam("omologationCode") String omologationCode,
            @RequestParam("matriculation") Long matriculation) {


        setParamsForLog(session, channel, host);

        logger.info(String.format(
                apiURL() + "/vehicle-data-optional. Input Params : [sessionId = %s], [channel = %s]" + " [host = %s]",
                session, channel.getChannel(), host));

        QuattroRuoteResponse response;

        List<VehicleDTO> vehicleList;

        try {
            vehicleList = quattroRuoteService.getOptionalByOmologationCode(omologationCode, matriculation);
            response = new QuattroRuoteResponse(HttpStatus.OK);
            response.setVehicleList(vehicleList);

        } catch (LegacyException lex) {
            logger.error(apiURL() + "/vehicle-data-optional-by-omologation-code" + lex.getMessage(), lex);
            response = new QuattroRuoteResponse(HttpStatus.INTERNAL_SERVER_ERROR);
            ErrorCode errorCode = lex.getErrorCode();
            ErrorDTO error = new ErrorDTO(errorCode, lex.getMessage(), lex.getLocalizedMessage());
            response.setError(error);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception ex) {
            logger.error(apiURL() + "/vehicle-data-optional-by-omologation-code" + ex.getMessage(), ex);
            response = new QuattroRuoteResponse(HttpStatus.INTERNAL_SERVER_ERROR);
            ErrorDTO error = new ErrorDTO(ErrorCode.QTR_995_VEHICLE_DATA_OPTIONAL_UNEXPECTED_ERROR, ex.getMessage(),
                                          ex.getLocalizedMessage());
            response.setError(error);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @ExceptionHandler({IllegalArgumentException.class, NullPointerException.class})
    void handleIllegalArgumentException(IllegalArgumentException e, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value());
    }

    @Override
    public String apiURL() {
        return "/quattroruote/vehicle";
    }

}
