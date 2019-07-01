package it.monaco.medical.service.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiParam;
import it.monaco.medical.service.controllers.dtos.*;
import it.monaco.medical.service.model.enums.EnumChannel;
import it.monaco.medical.service.model.enums.ErrorCode;
import it.monaco.medical.service.model.enums.ErrorType;
import it.monaco.medical.service.model.exceptions.LegacyException;
import it.monaco.medical.service.services.QuattroRuoteManualEntryService;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(value = "/manual-entry")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class QuattroRuoteManualEntryController extends LegacyBaseController{

    public static Logger logger = LoggerFactory.getLogger(QuattroRuoteController.class);

    @Autowired
    private QuattroRuoteManualEntryService quattroRuoteManualEntryService;

    @GetMapping({"/all-vehicle-makers"})
    public ResponseEntity<QuattroRuoteResponse> getAllVehicleMakers() {

        logger.info("into all-vehicle-makers");

        QuattroRuoteResponse response;
        List<VehicleMakerDTO> vehicleMakerDTOList;

        try {
            vehicleMakerDTOList = quattroRuoteManualEntryService.getAllVehicleMakers();

            if (CollectionUtils.isEmpty(vehicleMakerDTOList)) {
                throw new LegacyException("No vehicle maker found.", ErrorType.DATABASE,
                                          ErrorCode.QTR_003_VEHICLE_MAKERS_NOT_FOUND);
            }

            response = new QuattroRuoteResponse(HttpStatus.OK);
            response.setMakers(vehicleMakerDTOList);

        } catch (LegacyException lex) {
            logger.error(apiURL() + "/all-vehicle-makers" + lex.getMessage(), lex);
            response = new QuattroRuoteResponse(HttpStatus.INTERNAL_SERVER_ERROR);
            ErrorCode errorCode = lex.getErrorCode() != null ? lex.getErrorCode() : ErrorCode.LEG_999_UNEXPECTED_ERROR;
            ErrorDTO error = new ErrorDTO(errorCode, lex.getMessage(), lex.getLocalizedMessage());
            response.setError(error);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        } catch (Exception ex) {
            logger.error(apiURL() + "/all-vehicle-makers" + ex.getMessage(), ex);
            response = new QuattroRuoteResponse(HttpStatus.INTERNAL_SERVER_ERROR);
            ErrorDTO error = new ErrorDTO(ErrorCode.QTR_999_VEHICLE_MAKERS_UNEXPECTED_ERROR, ex.getMessage(),
                                          ex.getLocalizedMessage());
            response.setError(error);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = {"/vehicle-models", "/vehicle-models/{isInsert}"})
    public ResponseEntity<QuattroRuoteResponse> getVehicleModelsByMakerCode(
            @ApiParam(required = false, allowableValues = "WEB_SITE,MOBILE_SITE,POS_SITE,UIMX") @RequestParam("channel")
                    EnumChannel channel, @ApiParam(required = false, value = "host") @RequestParam("host") String host,
            @ApiParam(required = false, value = "sessionId") @RequestParam("session") String session,
            @ApiParam(required = true, value = "Codice marca") @RequestParam("makeCode") String makeCode,
            @PathVariable Optional<String> isInsert) {

        Boolean isInsertCall = isInsert.isPresent() ? Boolean.TRUE : Boolean.FALSE;
        setParamsForLog(session, channel, host);
        QuattroRuoteResponse response = null;
        List<VehicleModelDTO> vehicleModelDTOList = null;

        try {
            vehicleModelDTOList = quattroRuoteManualEntryService.getVehicleModelsByMakerCode(makeCode, isInsertCall);

            if (CollectionUtils.isEmpty(vehicleModelDTOList)) {
                throw new LegacyException("No vehicle model found for inputs.", ErrorType.DATABASE,
                                          ErrorCode.QTR_005_VEHICLE_MODEL_NOT_FOUND);
            }

            response = new QuattroRuoteResponse(HttpStatus.OK);
            response.setModels(vehicleModelDTOList);

        } catch (LegacyException lex) {
            logger.error(apiURL() + "/vehicle-models : " + lex.getMessage(), lex);
            response = new QuattroRuoteResponse(HttpStatus.INTERNAL_SERVER_ERROR);
            ErrorCode errorCode = lex.getErrorCode() != null ? lex.getErrorCode() : null;
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

    @PostMapping("/search-vehicle")
    public ResponseEntity<QuattroRuoteResponse> getVehicle(@RequestParam("channel") EnumChannel channel,
                                                           @RequestParam("host") String host,
                                                           @RequestParam("session") String session, @RequestBody
                                                                   ManualEntryVehicleReqDTO manualEntryVehicleReqDTO)
            throws JsonProcessingException {

        setParamsForLog(session, channel, host);

        logger.info(String.format(apiURL() + "/search-vehicle. Input Params : [sessionId = %s], [channel = %s] " +
                                  "[host = %s], [JSON Input = %s]", session, channel.getChannel(), host,
                                  new ObjectMapper().writeValueAsString(manualEntryVehicleReqDTO)));

        QuattroRuoteResponse response;

        List<ManualEntryVehicleResDTO> manualEntryVehicleDTOList;

        try {
            manualEntryVehicleDTOList = quattroRuoteManualEntryService.getManualEntryVehicles(manualEntryVehicleReqDTO);
            if (CollectionUtils.isEmpty(manualEntryVehicleDTOList)) {
                response = new QuattroRuoteResponse(HttpStatus.NOT_FOUND);
            } else {
                response = new QuattroRuoteResponse(HttpStatus.OK);
                response.setManualEntryVehicleList(manualEntryVehicleDTOList);
            }
        } catch (LegacyException lex) {
            logger.error(apiURL() + "/search-vehicle : " + lex.getMessage(), lex);
            response = new QuattroRuoteResponse(HttpStatus.INTERNAL_SERVER_ERROR);
            ErrorCode errorCode = lex.getErrorCode() != null ? lex.getErrorCode() : null;
            ErrorDTO error = new ErrorDTO(errorCode, lex.getMessage(), lex.getLocalizedMessage());
            response.setError(error);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception ex) {
            logger.error(apiURL() + "/search-vehicle :  " + ex.getMessage(), ex);
            response = new QuattroRuoteResponse(HttpStatus.INTERNAL_SERVER_ERROR);
            ErrorDTO error =
                    new ErrorDTO(ErrorCode.QTR_994_VEHICLE_UNEXPECTED_ERROR, ex.getMessage(), ex.getLocalizedMessage());
            response.setError(error);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("get-new-vehicle-id")
    public ResponseEntity<QuattroRuoteResponse> getNewVehicleId() {

        QuattroRuoteResponse response = null;
        try {
            String newVehicleId = quattroRuoteManualEntryService.getNewVehicleId();
            logger.info("New vehicle correctly saved on DB!");
            response = new QuattroRuoteResponse(HttpStatus.OK);
            response.setNewVehicleId(newVehicleId);
        } catch (Exception ex) {
            logger.error(apiURL() + "/get-new-vehicle-id:  " + ex.getMessage(), ex);
            response = new QuattroRuoteResponse(HttpStatus.INTERNAL_SERVER_ERROR);
            ErrorDTO error =
                    new ErrorDTO(ErrorCode.QTR_994_VEHICLE_UNEXPECTED_ERROR, ex.getMessage(), ex.getLocalizedMessage());
            response.setError(error);
            return new ResponseEntity<QuattroRuoteResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);

    }


    @PostMapping("/insert-new-vehicle")
    public ResponseEntity<QuattroRuoteResponse> insertNewVehicle(@RequestParam("channel") EnumChannel channel,
                                                                 @RequestParam("host") String host,
                                                                 @RequestParam("session") String session, @RequestBody
                                                                         ManualEntryNewVehicleDTO manualEntryNewVehicleDTO)
            throws JsonProcessingException {

        setParamsForLog(session, channel, host);

        logger.info(String.format(apiURL() + "/insert-new-vehicle. Input Params : [sessionId = %s], " +
                                  "[channel = %s], [host = %s], [JSON Input = %s]", session, channel.getChannel(), host,
                                  new ObjectMapper().writeValueAsString(manualEntryNewVehicleDTO)));

        QuattroRuoteResponse response = null;

        try {
            quattroRuoteManualEntryService.insertNewVehicle(manualEntryNewVehicleDTO);
            logger.info("New vehicle correctly saved on DB!");
            response = new QuattroRuoteResponse(HttpStatus.OK);
        } catch (Exception ex) {
            logger.error(apiURL() + "/insert-new-vehicle :  " + ex.getMessage(), ex);
            response = new QuattroRuoteResponse(HttpStatus.INTERNAL_SERVER_ERROR);
            ErrorDTO error =
                    new ErrorDTO(ErrorCode.QTR_994_VEHICLE_UNEXPECTED_ERROR, ex.getMessage(), ex.getLocalizedMessage());
            response.setError(error);
            return new ResponseEntity<QuattroRuoteResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }

        return new ResponseEntity<QuattroRuoteResponse>(response, HttpStatus.OK);

    }

    @Override
    public String apiURL() {

        return "/manual-entry";

    }

}
