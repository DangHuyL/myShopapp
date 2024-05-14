package com.example.myshopapp.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Status {
    /**
     * The operation completed successfully.
     */
    OK(0, HttpStatus.OK),

    /**
     * The operation was cancelled (typically by the caller).
     */
    CANCELLED(1, HttpStatus.BAD_REQUEST),

    /**
     * Unknown error.  An example of where this error may be returned is
     * if a Status value received from another address space belongs to
     * an error-space that is not known in this address space.  Also
     * errors raised by APIs that do not return enough error information
     * may be converted to this error.
     */
    UNKNOWN(2, HttpStatus.BAD_REQUEST),

    /**
     * Client specified an invalid argument.  Note that this differs
     * from FAILED_PRECONDITION.  INVALID_ARGUMENT indicates arguments
     * that are problematic regardless of the state of the system
     * (e.g., a malformed file name).
     */
    INVALID_ARGUMENT(3, HttpStatus.BAD_REQUEST),

    /**
     * Deadline expired before operation could complete.  For operations
     * that change the state of the system, this error may be returned
     * even if the operation has completed successfully.  For example, a
     * successful response from a server could have been delayed long
     * enough for the deadline to expire.
     */
    DEADLINE_EXCEEDED(4, HttpStatus.BAD_REQUEST),

    /**
     * Some requested entity (e.g., file or directory) was not found.
     */
    NOT_FOUND(5, HttpStatus.NOT_FOUND),

    /**
     * Some entity that we attempted to create (e.g., file or directory) already exists.
     */
    ALREADY_EXISTS(6, HttpStatus.FOUND),

    /**
     * The caller does not have permission to execute the specified
     * operation.  PERMISSION_DENIED must not be used for rejections
     * caused by exhausting some resource (use RESOURCE_EXHAUSTED
     * instead for those errors).  PERMISSION_DENIED must not be
     * used if the caller cannot be identified (use UNAUTHENTICATED
     * instead for those errors).
     */
    PERMISSION_DENIED(7, HttpStatus.FORBIDDEN),

    /**
     * Internal errors.  Means some invariants expected by underlying
     * system has been broken.  If you see one of these errors,
     * something is very broken.
     */
    INTERNAL(13, HttpStatus.INTERNAL_SERVER_ERROR),

    /**
     * The service is currently unavailable.  This is a most likely a
     * transient condition and may be corrected by retrying with
     * a backoff. Note that it is not always safe to retry
     * non-idempotent operations.
     *
     * <p>See litmus test above for deciding between FAILED_PRECONDITION,
     * ABORTED, and UNAVAILABLE.
     */
    UNAVAILABLE(14, HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS),

    /**
     * The request does not have valid authentication credentials for the
     * operation.
     */
    UNAUTHENTICATED(16, HttpStatus.UNAUTHORIZED);

    private int value;
    private HttpStatus httpStatusCode;
}
