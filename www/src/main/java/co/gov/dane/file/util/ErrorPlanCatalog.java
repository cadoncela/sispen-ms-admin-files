package co.gov.dane.file.util;

import lombok.Getter;

/**
 * @author Oliver & Ragnar
 */
@Getter
public enum ErrorPlanCatalog {
    FILE_NOT_FOUND("ERR_FILE_001", "FILE not found."),
    INVALID_FILE("ERR_FILE_002", "Invalid FILE."),
    GENERIC_ERROR("ERR_GEN_001", "An unexpected error occurred.");

    private final String code;
    private final String message;

    ErrorPlanCatalog(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
