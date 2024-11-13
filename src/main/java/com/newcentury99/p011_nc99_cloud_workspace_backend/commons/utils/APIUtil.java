package com.newcentury99.p011_nc99_cloud_workspace_backend.commons.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.base.DTOMetadata;
import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.base.GeneralResDTO;
import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.base.ThrowableSupplier;
import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.base.pagenation.GeneralPageableResDTO;
import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.messages.MessageConfig;
import feign.Response;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.function.Consumer;

public abstract class APIUtil {
    private static final MessageSource resMsgSrc = MessageConfig.getResponseMsgSrc();
    private static final MessageSource errMsgSrc = MessageConfig.getErrorMsgSrc();
    private static final Logger logger = LogManager.getLogger();
    private static final ObjectMapper objectMapper = JsonMapper.builder()
            .addModule(new JavaTimeModule())
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .build();

    public static <T extends GeneralResDTO> ResponseEntity<?> executeAPI(ThrowableSupplier<T> procedure, String successMsg) {
        try {
            T resDTO = procedure.get();
            resDTO.set_metadata(new DTOMetadata(resMsgSrc.getMessage(successMsg, null, Locale.ENGLISH)));
            return ResponseEntity.ok(resDTO);
        } catch (Exception e) {
            GeneralResDTO errorResDTO = new GeneralResDTO();
            String errorLog =
                    "[ERR]" + e.getLocalizedMessage()
                    + "-------------------------- Request Failure StackTrace --------------------------"
                    + APIUtil.getPrintStackTrace(e)
                    + "--------------------------------------------------------------------------------";
            logger.warn(errorLog);
            errorResDTO.set_metadata(new DTOMetadata(e.getMessage(), e.getClass().getName()));
            return ResponseEntity.status(400).body(errorResDTO);
        }
    }

    public static void executeCSVExportAPI(ThrowableSupplier<byte[]> procedure, HttpServletResponse response, String fileName) {
        try {
            byte[] resource = procedure.get();
            String contentDispositionHeaderContent = "attachment; filename=\"" + fileName + LocalDateTime.now(ZoneId.of("Asia/Seoul")).format(DateTimeFormatter.ofPattern("_yyyyMMdd_hhmm")) + ".csv\"";

            response.setContentType("application/octet-stream");
            response.setContentLength(resource.length);
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, contentDispositionHeaderContent);
            response.setHeader(HttpHeaders.TRANSFER_ENCODING, "binary");
            response.getOutputStream().write(resource);
            response.getOutputStream().flush();
            response.getOutputStream().close();
        } catch (Exception e) {
            GeneralResDTO errorResDTO = new GeneralResDTO();
            logger.warn(e.getLocalizedMessage());
            logger.warn("------------------------------ Request Process Failed | StackTrace ------------------------------");
            logger.warn(APIUtil.getPrintStackTrace(e));
            logger.warn("-------------------------------------------------------------------------------------------------");
            errorResDTO.set_metadata(new DTOMetadata(e.getMessage(), e.getClass().getName()));
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.setContentType("application/json");
            try {
                response.getWriter().write(objectMapper.writeValueAsString(errorResDTO));
            } catch (Exception ignored) {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.setContentType("text/plain");
                response.setContentLength(0);
            }
        }
    }

    public static <T extends GeneralPageableResDTO, E> void buildPageableResDTO(T resDTO, Page<E> page, Consumer<List<E>> contentSetter) {
        if (page.getPageable().isPaged()) {
            resDTO.setPageable(true);
            resDTO.setPageIdx(page.getNumber());
            resDTO.setTotalPage(page.getTotalPages());
            resDTO.setPageElementSize(page.getTotalElements());
        }
        contentSetter.accept(page.getContent());
    }

    public static String getBodyJSONString(InputStream inputStream) throws IOException {
        return StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
    }

    public static <T> T parseJSONString(String jsonStr, Class<T> cls) throws IOException {
        return objectMapper.readValue(jsonStr, cls);
    }

    public static <T> T getBodyJSON(InputStream inputStream, Class<T> cls) throws IOException {
        return parseJSONString(getBodyJSONString(inputStream), cls);
    }

    public static void toBodyJSON(PrintWriter writer, Object resDTO) throws IOException {
        writer.write(objectMapper.writeValueAsString(resDTO));
    }

    public static <T> T reqMsaAPICall(ThrowableSupplier<Response> api, Class<T> cls) throws Exception {
        try (Response response = api.get()) {
            return APIUtil.getBodyJSON(response.body().asInputStream(), cls);
        } catch (IOException e) {
            throw new IllegalStateException(errMsgSrc.getMessage("error.msa.serialize", null, Locale.ENGLISH));
        }
    }

    private static String getPrintStackTrace(Exception e) {
        StringWriter errorLogs = new StringWriter();
        e.printStackTrace(new PrintWriter(errorLogs));
        return errorLogs.toString();
    }
}
