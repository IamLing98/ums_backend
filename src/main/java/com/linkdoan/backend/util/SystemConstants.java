package com.linkdoan.backend.util;


public interface SystemConstants {

    public static final int MIN_PASSWORD_LENGTH = 8;

    public static final String PARAM_IS_REQUIRED = "param.is-required";
    public static final String PARAM_IS_MAXLENGTH = "param.is-maxlength";
    public static final String PARAM_IS_FORMAT = "param.is-format";
    public static final String PARAM_IS_NOT_FOUND = "param.is-notfound";
    public static final String UPLOAD_FILE_LIMITED = "upload-file-limited";

    public static final long LOCK = 2L;
    public static final long ACTIVE = 1L;
    public static final long INACTIVE = 0L;


    public static final int PHASE_TYPE_LEAD = 0;
    public static final int PHASE_TYPE_DEAL = 1;
    public static final long WON_LEAD = 10L;

}